/********************************************************/
// Beware of line number changes, they may corrupt docu!
//! \brief Driver function to set up and solve the problem
/********************************************************/


const RF calcNextSignalTransitionTime(const RF& lambda, const RF& time, const RF& dt, const RF& T)
{
    RF number = 0.0;
    //do number = pickAnExponentialDistributedNumber(lambda); while(number > T);#
    number = pickAnExponentialDistributedNumber(lambda);
    std::cout << "number: " << number << ", time: " << time << ", dt: " << dt << ", T: " << T << std::endl;
    return time + number;
}

template<typename Grid, typename GeoGrid, typename Grid_shmoo, typename CM, typename CD>
void driver(OSM& solver,
            GVg& gv,
            GeoGV_shmoo& geo_gv,
            GI& gi,
            GD& grid_data,
            GFS& gfs,
            ZDGF& zdgf,
            Z& z,
            P& problem,
            RF& time,
            RF dt,
            RF T,
            RF DMem,
            RF& nextTransitionTime,
            int& signalIndex,
            int& outputIndex,
            int& lastSignalElementId,
            GeoGrid& signalGrid,
            Grid_shmoo& geoGrid,
            //CM& cellTransformator,
            CM& signalTransformator,
            CD& discreteDeformation,
            SIGNAL& ownSignal,
            Dune::ParameterTree& ptree,
            VTKSEQUENCEWRITER& vtkSequenceWriter,
            std::vector<RF>& signalTrace,
            std::vector<RF>& pressure_f,
            std::vector<RF>& elasticity_f,
            std::vector<RF>& E_coeff,
            std::vector<RF>& stress_field,
            std::vector<RF>& growth_zone,
            std::vector<RF>& strain_field,
            std::vector<RF>& mass,
            std::vector<RF>& normal,
            std::vector<RF>& area_node,
            std::map<std::tuple<int,int>,RF>& l0_map,
            RF& surface_area,
            RF& cell_volume,
            RF& volumeAtShock,
            RF& energyAtShock,
            RF& t_adapt,
            std::string out,
            const int& shmooing,
            std::ofstream& outpos)
{
    int outputtrigger = ptree.get("output.trigger",(int)1);
    RF dt_adapt = ptree.get<RF>("problem.dt_adapt");
    RF dt_max = ptree.get<RF>("problem.dt_max");
    RF dt_min = ptree.get<RF>("problem.dt_min");

    RF t_shock  = ptree.get<RF>("physics.t_shock");
    RF t_trans  = ptree.get<RF>("physics.t_trans");
    RF t_endshock  = ptree.get<RF>("physics.t_endshock");
    RF pi_start  = ptree.get<RF>("physics.p_start");
    RF pi_shock  = ptree.get<RF>("physics.p_shock");
    RF V0_OS  = ptree.get<RF>("physics.V0_OS");
    RF shmoo_threshold = ptree.get<RF>("physics.shmoo_threshold");

    std::string shock_model = ptree.get<std::string>("physics.shock_model");

    /*************************************************************************
     * 1. solve receptor activation on cell surface
     * **********************************************************************/

    // assemble constraints for new time step
    problem.setTime(time+dt);

    // do time step
    Z znew(z);

    solver.apply(time,dt,z,znew);

    // accept time step
    z = znew;
    //time+=dt;

    Evaluator<Dune::FieldVector<RF, dim+1>,GeoGV_shmoo,GFS,Z,ZDGF> evaluator(geo_gv, gfs, &zdgf, ptree, DMem);
    evaluator.evaluate();
    problem.setMembraneCdc42(evaluator.cdc42Particles());
    if(time >= nextTransitionTime)
    {
        // evaluate result       
        std::cout << "---------- call evaluate at time " << time << " ----------" << std::endl;
        evaluator.evaluate(ownSignal, time, dt, T);
        std::cout << "---------- lambda value " << evaluator.lambda() << " ---------------" << std::endl;
        std::cout << std::endl;

        // set new signal pos from evaluation result
        ownSignal = evaluator.nextSignalPos();
        lastSignalElementId = evaluator.elementID();
        signalTrace[evaluator.elementID()] = evaluator.peakValue();

        // make new signal position visible. Has no influence to calculation.
        signalTransformator.move(evaluator.nextSignalPos()[0], evaluator.nextSignalPos()[1], evaluator.nextSignalPos()[2], 0.08);

        nextTransitionTime = calcNextSignalTransitionTime(evaluator.lambda(), time, dt, T);
        std::cout << nextTransitionTime << std::endl;

        TRBSForceField<GeoGV_shmoo,GI,GD> growth_gen(geo_gv,gi,grid_data,ptree,l0_map,mass,dt,time,ownSignal,true);
        //growth_gen.buildGrowthZone();
        //normal_gen.setShmooTip(ownSignal);
        growth_gen.getVertexFields(normal,area_node,pressure_f,elasticity_f,stress_field,strain_field,growth_zone);

    }

    /*************************************************************************
     * 2. solve shmoo
     * **********************************************************************/

    if(shmooing == 1 && evaluator.peakValue() >= shmoo_threshold)
    {
        std::cout << "Time: " << time << ", surface area: " << surface_area << ", cell volume: " << cell_volume << std::endl;
        std::cout << "Size of geoGrid: " << geo_gv.size(0) << std::endl;
        std::cout << "Size of grid: " << gv.size(0) << std::endl;

        // Also elasticity_f, pressure_f and length0 will be calculated here
        //ForceField<GeoGV> normal_gen(geo_gv,params,length0,l0_map,mass,dt,time);

        grid_data.construct_elasticityCoeff(time, ownSignal);
        //watch.reset();
        TRBSForceField<GeoGV_shmoo,GI,GD> normal_gen(geo_gv,gi,grid_data,ptree,l0_map,mass,dt,time,ownSignal);
        //normal_gen.setShmooTip(ownSignal);
        normal_gen.getVertexFields(normal,area_node,pressure_f,elasticity_f,stress_field,strain_field,growth_zone);

    //    std::cout << "time to get force field:\t" << watch.elapsed() << std::endl;
        std::cout << "Size of NormalField: " << normal.size() << std::endl;

        double lambda = std::max(std::min((time-t_shock)/t_trans,1.0),0.0);
        //Pi_global = lambda*pi_shock + (1.0 - lambda)*pi_start;
        double Pit = lambda*pi_shock + (1.0 - lambda)*pi_start;

        if (shock_model == "direct") {
            Pi_global = Pit;
        }

        if (shock_model == "volume_energy")
        {   if (time < t_shock)
            {
                energyAtShock = (cell_volume - V0_OS)*pi_start;
                volumeAtShock = cell_volume;
            }

            if (time > t_shock)
            {
                Pi_global = Pit*(volumeAtShock - V0_OS)/(cell_volume - V0_OS);
            }
        }

        for (int i = 0; i< geo_gv.size(0); i++)
            E_coeff[i] = grid_data.get_elasticityCoeff(i);
    }

    /*************************************************************************
     * 3. write graphic result
     * **********************************************************************/

    // output solution to VTK file
    if(signalIndex % outputtrigger == 0)
    {
        // write signal
        output(signalGrid, outputIndex, out + "signal", 1.0);
        // write solution
        vtkSequenceWriter.write(time,Dune::VTK::appendedraw);

        outputIndex++;
    }

    /*************************************************************************
     * 4. shmoo post processing
     * **********************************************************************/   

    surface_area = 0.0;
    cell_volume = 0.0;
    //volumeAtShock = 0.0;

    const typename GeoGV_shmoo::IndexSet& indexSet = geo_gv.indexSet();

    for (ElementIterator eit = geo_gv.template begin<0>();eit!= geo_gv.template end<0>(); ++eit)
    {

      int idx = indexSet.index(*eit);
      int sub_idx = indexSet.subIndex(*eit, 0,dim);
      surface_area += eit->geometry().volume();
      //double temp = normal[3*idx]*(eit->geometry().center()[0])*(eit->geometry().volume());
      // n_x*x*dA
      double temp = normal[3*sub_idx]*(eit->geometry().center()[0])*(eit->geometry().volume());
      cell_volume += temp; //normal[3*idx]*(eit->geometry().center()[0])*(eit->geometry().volume());
      //std::cout << "normal: " << normal[3*idx]  << " x: " << eit->geometry().center()[0] << " area: " <<  eit->geometry().volume() << " contribution: " << temp << std::endl;
      if (idx == lastSignalElementId)
      {
          //std::cout << "signal element ID: " << lastSignalElementId << ", old signal position: " << ownSignal << ", corrected signal position: " <<  eit->geometry().center() << std::endl;
          ownSignal = eit->geometry().center();
          //std::cout << "signal element ID: " << lastSignalElementId << ", old signal position: " << ownSignal << ", new signal position: " <<  eit->geometry().center() << std::endl;
      }      
    }
    problem.setVolume(abs(cell_volume));

    if(shmooing == 1 && evaluator.peakValue() >= shmoo_threshold)
    {
        //watch.reset();
        discreteDeformation.update(l0_map,normal,mass,pressure_f,elasticity_f,dt,time);
        //std::cout << "time for deformation update:\t" << watch.elapsed() << std::endl;

/*
        if ( t_adapt >= dt_adapt)
        {
          discreteDeformation.do_refine(geoGrid, l0_map, ptree.get<RF>("physics.max_edge"),ownSignal);
          gi.update_hangingNodes();
          grid_data.construct_elasticityCoeff(time, ownSignal);
          t_adapt = 0.0;

          gfs.update(true);


          //------------------------------------------------------------
          // Make grid function space

          int degree = ptree.get("fem.degree",(int)1);
          MBE mbe((int)pow(1+2*degree,dim));
          int torder = ptree.get("fem.torder",(int)1);

          // select and prepare time-stepping scheme
          Dune::PDELab::OneStepThetaParameter<RF> method1(1.0);
          Dune::PDELab::Alexander2Parameter<RF> method2;
          Dune::PDELab::Alexander3Parameter<RF> method3;

          Dune::PDELab::TimeSteppingParameterInterface<RF>* pmethod=&method1;
          if (torder==1) pmethod = &method1;
          if (torder==2) pmethod = &method2;
          if (torder==3) pmethod = &method3;
          if (torder<1||torder>3)
          std::cout<<"torder not in [1,3]"<<std::endl;

          FEM fem(geo_gv);
          GFS gfs(geo_gv,fem);
          gfs.name("Vh");

          // A coefficient vector
          //Z z(gfs); // initial value

          // Make a grid function out of it
          ZDGF zdgf(gfs,z);


          // Fill the coefficient vector
          //Dune::PDELab::interpolate(g, gfs,z);

          // Make instationary grid operator
          LOP lop(problem);
          GO0 go0(gfs,gfs,lop,mbe);
          TLOP tlop;
          GO1 go1(gfs,gfs,tlop,mbe);
          IGO igo(go0,go1);

          // Select a linear solver backend
          LS ls(100,0);

          // solve nonlinear problem
          PDESOLVER newton(igo,z,ls);
          newton.setReassembleThreshold(0.0);
          newton.setVerbosityLevel(0);
          newton.setReduction(1e-8);
          newton.setMinLinearReduction(1e-4);
          newton.setMaxIterations(25);
          newton.setLineSearchMaxIterations(10);

          // create solver object
          OSM solver(*pmethod,igo,newton);
          solver.setVerbosityLevel(0);
          //solver = osm;

          //--------------------------------------------------------------



        }
*/

        //time += dt;
        //t_output += dt;
        t_adapt += dt;

        double dynamic_dt = discreteDeformation.getMaxStepSize();


        std::cout << "turgor pressure\t" << Pi_global << "\n\n";
        std::cout << "dt_max estimate" << dynamic_dt << std::endl;
        std::cout << "step size" << dt << std::endl;

        if (dt < 0.025*dynamic_dt) dt = std::min(dt*2.0,dt_max);
        if (dt > 0.1*dynamic_dt) dt = std::max(dt/2.0,dt_min);
        if (dt > 0.4*dynamic_dt) dt = dt_min;
        //if (dt > 0.2*dynamic_dt) dt = dynamic_dt/(dt_adapt*dt_adapt*dt_adapt*dt_adapt);


        //dt = std::min(std::max(0.5*dynamic_dt,dt_min),dt_max);
        //dt = std::min(dt*dt_adapt,dt_max);
       // std::cout << "total time for this time step:\t" << watch2.elapsed() << "\n\n";

    }

    /*************************************************************************
     * 5. write simulation results
     * **********************************************************************/

     outputData(outpos, time, evaluator.peakValue(), evaluator.peakValuePos(), ownSignal, evaluator.elementID(), evaluator.cdc42Particles(), evaluator.lambda(), nextTransitionTime);

}




