// -*- tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 2 -*-
// vi: set et ts=4 sw=2 sts=2:

//#include <QtGui>

#ifdef HAVE_CONFIG_H
# include "config.h"
#endif

#include <dune/common/parallel/mpihelper.hh> // An initializer of MPI
#include <dune/common/exceptions.hh> // We use exceptions
#include <dune/common/fvector.hh>
#include <dune/common/parametertreeparser.hh>


// grid definition
#include<dune/grid/io/file/gmshreader.hh>
#include<dune/alugrid/grid.hh>

// grid information
#include <dune/grid/common/gridinfo.hh>
#include <dune/grid/geometrygrid.hh>
#include <dune/grid/geometrygrid/coordfunction.hh>
#include <dune/grid/common/entitypointer.hh>

// C includes
#include<sys/stat.h>
// C++ includes
#include<math.h>
#include<iostream>
#include<fstream>

//pde lab
/* *************************************************
 * Dune::PDELab::PkLocalFiniteElementMap
 * *************************************************/
      #include<dune/pdelab/finiteelementmap/pkfem.hh>

/* *************************************************
 * makeInstationaryGridFunctionFromCallable
 * makeBoundaryConditionFromCallable
 * *************************************************/
      #include<dune/pdelab/function/callableadapter.hh>

/* *************************************************
 * Dune::PDELab::ConformingDirichletConstraints
 * *************************************************/
      #include<dune/pdelab/constraints/common/constraints.hh>
      #include<dune/pdelab/constraints/common/constraintsparameters.hh>
      #include<dune/pdelab/constraints/conforming.hh>
      #include<dune/pdelab/gridoperator/gridoperator.hh>

      #include<dune/pdelab/common/function.hh>


/* *************************************************
 * Dune::PDELab::istl::VectorBackend
 * *************************************************/
      #include<dune/pdelab/backend/istl.hh>

/* *************************************************
 * Dune::PDELab::makeZeroBasisFieldValue
 * *************************************************/
      #include<dune/pdelab/localoperator/variablefactories.hh>

/* *************************************************
 * Dune::PDELab::GridOperator
 * Dune::PDELab::OneStepGridOperator
 * *************************************************/
      #include<dune/pdelab/gridoperator/onestep.hh>

/* *************************************************
 * Dune::PDELab::Newton
 * *************************************************/
      #include<dune/pdelab/newton/newton.hh>

/* *************************************************
 * Dune::SubsamplingVTKWriter
 * *************************************************/
      #include<dune/grid/io/file/vtk.hh>

#include<dune/pdelab/gridfunctionspace/gridfunctionspaceutilities.hh>

#include "transform.hh"
#include "problem.hh"
#include "random.hh"
#include "output.hh"
#include "nonlinearheatfem.hh"
#include "gridvector.hh"
#include "gridData.hh"
#include "deformationFct_TRBS.hh"

double Pi_global = 0.2;

#include "TRBS_force_field.hh"

// environment
const int dimworld = 3;
const int dim = 2;


/* *************************************************
 * solver typedefs
 * *************************************************/

    typedef double RF;
    typedef typename Dune::ALUGrid<dim, dimworld, Dune::simplex, Dune::nonconforming> GridType;
    typedef typename Dune::GeometryGrid<GridType, CellTransformFunction> Geogridtype;
    typedef Geogridtype::ctype DF;
    typedef Geogridtype::LeafGridView GV;

    typedef GridType::ctype DFg;    // ##1
    typedef GridType::LeafGridView GVg; // ##1




/* *************************************************
 * shmoo-project typedefs
 * *************************************************/

    typedef GridInfo<GVg> GI;
    typedef typename GVg::Grid::ctype Coord;
    typedef typename Dune::FieldVector<Coord,dimworld> DomainType;
    typedef typename Dune::GeometryGrid< GridType, DeformationFunctionTRBS<GVg,GI> > Geogridtype_shmoo;
    typedef typename Geogridtype_shmoo::LeafGridView GeoGV_shmoo;
    typedef typename GeoGV_shmoo::Grid::ctype GeoCoord;
    typedef typename Dune::FieldVector<GeoCoord,3> GeoDomainType;
    typedef GridData<GeoGV_shmoo,GI> GD;
    typedef typename GeoGV_shmoo::template Codim<0>::Iterator ElementIterator;

    typedef Dune::PDELab::PkLocalFiniteElementMap<GeoGV_shmoo,DF,double,1> FEM;
    typedef Dune::PDELab::istl::VectorBackend<> VBE;

    typedef Dune::PDELab::GridFunctionSpace<GeoGV_shmoo,FEM,VBE> GFS;
    typedef typename Dune::PDELab::Backend::Vector<GFS,RF> Z;
    typedef Dune::PDELab::DiscreteGridFunction<GFS,Z> ZDGF;
    typedef typename Dune::FieldVector<double, dimworld> SIGNAL;
    typedef Problem<RF, SIGNAL> P;

    typedef NonlinearHeatFEM<P,FEM> LOP;
    typedef Dune::PDELab::istl::BCRSMatrixBackend<> MBE;
    typedef Dune::PDELab::GridOperator<GFS,GFS,LOP,MBE, RF,RF,RF> GO0;
    typedef L2<FEM> TLOP;
    typedef Dune::PDELab::GridOperator<GFS,GFS,TLOP,MBE, RF,RF,RF> GO1;
    typedef Dune::PDELab::OneStepGridOperator<GO0,GO1> IGO;
    typedef Dune::PDELab::ISTLBackend_SEQ_CG_AMG_SSOR<IGO> LS;
    typedef Dune::PDELab::Newton<IGO,LS,Z> PDESOLVER;
    typedef Dune::PDELab::OneStepMethod<RF,IGO,PDESOLVER,Z,Z> OSM;

    typedef Dune::SubsamplingVTKWriter<GeoGV_shmoo> VTKWRITER;
    typedef Dune::VTKSequenceWriter<GeoGV_shmoo> VTKSEQUENCEWRITER;
    typedef Dune::PDELab::VTKGridFunctionAdapter<ZDGF> VTKF;




// for output
#include <dune/grid/io/file/vtk/vtkwriter.hh>

// user defined includes
#include "evaluator.hh"
#include "driver.hh"


int main(int argc, char** argv)
{
    /*******************************************************************
     * init parallel computing. Not needed but required!
     * ****************************************************************/
    try
    {
       Dune::MPIHelper& helper = Dune::MPIHelper::instance(argc, argv);
    }
    catch (Dune::Exception &e)
    {
        std::cerr << "Dune reported error: " << e << std::endl;
    }

    /*******************************************************************
     * read simulation parameter
     * ****************************************************************/
    if (argc != 2)
    {
        std::cerr << "no ini file! Execution stopped.";
        return 1;
    }

    std::cerr << "reading parameters from " << argv[1] << "..." << std::endl;
    Dune::ParameterTree params;
    Dune::ParameterTreeParser::readINITree(argv[1],params);

    // solution output location
    std::string output_a = params.get<std::string>("cell_a.output");
    std::string output_alpha = params.get<std::string>("cell_alpha.output");
    int subsampling=params.get("output.subsampling",(int)0);

    // position of mat-a cell
    Dune::FieldVector<double,dimworld> position_a_cell;
    position_a_cell[0] = params.get<double>("cell_a.xPos");
    position_a_cell[1] = params.get<double>("cell_a.yPos");
    position_a_cell[2] = params.get<double>("cell_a.zPos");

    // position of mat-alpha cell
    double distance = params.get<double>("cell_alpha.distance");
    double theta = params.get<double>("cell_alpha.theta");
    double phi = params.get<double>("cell_alpha.phi");
    GridVector<Dune::FieldVector<double,dimworld>, dimworld> v_alpha(position_a_cell, theta, phi, distance);
    Dune::FieldVector<double,dimworld> position_alpha_cell;
    //std::cout << v_alpha << std::endl;
    position_alpha_cell[0] = v_alpha.target()[0];
    position_alpha_cell[1] = v_alpha.target()[1];
    position_alpha_cell[2] = v_alpha.target()[2];

    // signal source of mat-a cell
    double distance_sa = params.get<double>("signal_a.distance");
    double theta_sa = params.get<double>("signal_a.theta");
    double phi_sa = params.get<double>("signal_a.phi");
    GridVector<Dune::FieldVector<double,dimworld>, dimworld> v_signal_a(position_a_cell, theta_sa, phi_sa, distance_sa);
    Dune::FieldVector<double,dimworld> position_signal_a_cell;
    //std::cout << v_signal_a << std::endl;
    position_signal_a_cell[0] = v_signal_a.target()[0];
    position_signal_a_cell[1] = v_signal_a.target()[1];
    position_signal_a_cell[2] = v_signal_a.target()[2];

    // signal source of mat-alpha cell
    double distance_salpha = params.get<double>("signal_alpha.distance");
    double theta_salpha = params.get<double>("signal_alpha.theta");
    double phi_salpha = params.get<double>("signal_alpha.phi");
    GridVector<Dune::FieldVector<double,dimworld>, dimworld> v_signal_alpha(position_alpha_cell, theta_salpha, phi_salpha, distance_salpha);
    Dune::FieldVector<double,dimworld> position_signal_alpha_cell;
    //std::cout << v_signal_alpha << std::endl;
    position_signal_alpha_cell[0] = v_signal_alpha.target()[0];
    position_signal_alpha_cell[1] = v_signal_alpha.target()[1];
    position_signal_alpha_cell[2] = v_signal_alpha.target()[2];

    // cell radi
    double radius_a = params.get<double>("cell_a.radius");
    double radius_alpha = params.get<double>("cell_alpha.radius");
    double radius_signal_a = params.get<double>("signal_a.radius");
    double radius_signal_alpha = params.get<double>("signal_alpha.radius");

    // shmoo abilities
    const int shmooing_a = params.get<int>("cell_a.shmoo");
    const int shmooing_alpha = params.get<int>("cell_alpha.shmoo");

    /*******************************************************************
     * read cell and signal grids
     * ****************************************************************/

    std::string mat_a_file;
    mat_a_file = params.get<std::string>("global.mesh");
    Dune::GmshReader<GridType> gmshreader;

    std::shared_ptr<GridType> grid_a(Dune::GmshReader<GridType>::read(mat_a_file));
    std::shared_ptr<GridType> grid_alpha(Dune::GmshReader<GridType>::read(mat_a_file));
    std::shared_ptr<GridType> grid_signal_a(Dune::GmshReader<GridType>::read(mat_a_file));
    std::shared_ptr<GridType> grid_signal_alpha(Dune::GmshReader<GridType>::read(mat_a_file));

    //grid_a->globalRefine(1);
    //grid_alpha->globalRefine(1);

    /*******************************************************************
     * transform cell and signal grids
     * ****************************************************************/

    // create transform objects
//    CellTransformFunction cm_a;           ##1
//    CellTransformFunction cm_alpha;       ##1
    CellTransformFunction cm_signal_a;
    CellTransformFunction cm_signal_alpha;

    // create transform grids
//    Geogridtype geogrid_a(*grid_a,cm_a);  ##1
//    Geogridtype geogrid_alpha(*grid_alpha,cm_alpha);  ##1
    Geogridtype geogrid_signal_a(*grid_signal_a,cm_signal_a);
    Geogridtype geogrid_signal_alpha(*grid_signal_alpha,cm_signal_alpha);

    // apply transforming
//    cm_a.move(position_a_cell[0], position_a_cell[1], position_a_cell[2], radius_a);  ##1
//    cm_alpha.move(position_alpha_cell[0], position_alpha_cell[1], position_alpha_cell[2], radius_alpha);  ##1
    cm_signal_a.move(position_signal_a_cell[0], position_signal_a_cell[1], position_signal_a_cell[2], radius_signal_a);
    cm_signal_alpha.move(position_signal_alpha_cell[0], position_signal_alpha_cell[1], position_signal_alpha_cell[2], radius_signal_alpha);

    /*******************************************************************
     * generate gridViews
     * ****************************************************************/

//    GV gridView_a = geogrid_a.leafGridView(); ##1
//    GV gridView_alpha = geogrid_alpha.leafGridView(); ##1
      GVg gridView_a = grid_a->leafGridView(); // ##1
      GVg gridView_alpha = grid_alpha->leafGridView(); // ##1


    /*******************************************************************
     * init problem
     * ****************************************************************/

    // define problems
    RF time = 0.0;
    RF eta_a = params.get("cell_a.eta",(RF)1.0);
    RF hMem_a = params.get("cell_a.hMem", (RF)0.0);
    RF hVol_a = params.get("cell_a.hVol", (RF)1000.0);
    RF DMem_a = params.get("cell_a.Dmem", (RF)0.001);
    RF Kappa_a = params.get("cell_a.Kappa", (RF)0.5);
    RF k0_a = params.get("cell_a.k0", (RF)0.067);
    RF v0_a = params.get("cell_a.v0", (RF)0.2);
    RF Km_a = params.get("cell_a.Km", (RF)1.0);
    RF Dsig_a = params.get("signal_a.D", (RF)50.0);
    RF kB_a = params.get("signal_a.kB", (RF)1.0);
    RF r_ref_a = params.get("cell_a.ref_radius", (RF)5.0);
    RF sAmp_a = params.get("signal_a.signalPower", (RF)1.0);
    P problem_a(eta_a, position_signal_alpha_cell, sAmp_a, hMem_a, hVol_a, DMem_a, Kappa_a, k0_a, v0_a, Km_a, Dsig_a, kB_a, r_ref_a);
    problem_a.setTime(time);

    RF eta_alpha = params.get("cell_alpha.eta",(RF)1.0);
    RF hMem_alpha = params.get("cell_alpha.hMem", (RF)0.0);
    RF hVol_alpha = params.get("cell_alpha.hVol", (RF)1000.0);
    RF DMem_alpha = params.get("cell_alpha.Dmem", (RF)0.001);
    RF Kappa_alpha = params.get("cell_alpha.Kappa", (RF)0.5);
    RF k0_alpha = params.get("cell_alpha.k0", (RF)0.067);
    RF v0_alpha = params.get("cell_alpha.v0", (RF)0.2);
    RF Km_alpha = params.get("cell_alpha.Km", (RF)1.0);
    RF Dsig_alpha = params.get("signal_alpha.D", (RF)50.0);
    RF kB_alpha = params.get("signal_alpha.kB", (RF)1.0);
    RF r_ref_alpha = params.get("cell_alpha.ref_radius", (RF)5.0);
    RF sAmp_alpha = params.get("signal_alpha.signalPower", (RF)1.0);
    P problem_alpha(eta_alpha, position_signal_a_cell, sAmp_alpha, hMem_alpha, hVol_alpha, DMem_alpha, Kappa_alpha, k0_alpha, v0_alpha, Km_alpha, Dsig_alpha, kB_alpha, r_ref_alpha);
    problem_alpha.setTime(time);

    // define time settings
    RF T = params.get("problem.T",(RF)1.0);
    RF dt = params.get("problem.dt",(RF)0.1);
    RF nextTransitionTime_a = 0.0;
    RF nextTransitionTime_alpha = 0.0;

    RF t_adapt_a = params.get<RF>("problem.dt_adapt");
    RF t_adapt_alpha = params.get<RF>("problem.dt_adapt");


    /*******************************************************************
     * shmoo-project preparation for mat a cell
     * ****************************************************************/

    GI gi_a(gridView_a);
    DeformationFunctionTRBS<GVg,GI> discreteDeformation_a(gridView_a,gi_a,params, position_a_cell);
    Geogridtype_shmoo geogrid_a_shmoo(*grid_a, discreteDeformation_a);
    GeoGV_shmoo geo_gv_a_shmoo = geogrid_a_shmoo.leafGridView();
    GD grid_data_a(geo_gv_a_shmoo,gi_a,params);
    RF surface_area_a = 0.0;
    RF cell_volume_a = 0.0;
    RF volumeAtShock_a = 0.0;
    RF energyAtShock_a = 0.0;

    /*******************************************************************
     * shmoo-project preparation for mat alpha cell
     * ****************************************************************/

    GI gi_alpha(gridView_alpha);    
    DeformationFunctionTRBS<GVg,GI> discreteDeformation_alpha(gridView_alpha,gi_alpha,params, position_alpha_cell);
    Geogridtype_shmoo geogrid_alpha_shmoo(*grid_alpha, discreteDeformation_alpha);
    GeoGV_shmoo geo_gv_alpha_shmoo = geogrid_alpha_shmoo.leafGridView();
    GD grid_data_alpha(geo_gv_alpha_shmoo,gi_alpha,params);   
    RF surface_area_alpha = 0.0;    
    RF cell_volume_alpha = 0.0;    
    RF volumeAtShock_alpha = 0.0;    
    RF energyAtShock_alpha = 0.0;


    /*******************************************************************
     * shmoo-project Problem setup for the forces: Calculate length0 and forces
     * ****************************************************************/

    std::vector<RF> normal_a, normal_alpha;
    std::vector<RF> area_node_a, area_node_alpha;
    std::vector<RF> elasticity_f_a, elasticity_f_alpha;
    std::vector<RF> pressure_f_a, pressure_f_alpha;
    std::vector<RF> growth_zone_a, growth_zone_alpha;
    std::vector<RF> bending_f_a, bending_f_alpha;
    std::vector<RF> angle_vertex_a, angle_vertex_alpha;
    std::vector<RF> stress_field_a, stress_field_alpha;
    std::vector<RF> strain_field_a, strain_field_alpha;
    std::vector<RF> l0_matrix_a, l0_matrix_alpha;
    std::vector<RF> mass_a, mass_alpha;
    std::vector<RF> E_coeff_a(geo_gv_a_shmoo.size(0));
    std::vector<RF> E_coeff_alpha(geo_gv_alpha_shmoo.size(0));
    std::map<std::tuple<int,int>,RF> l0_map_a;
    std::map<std::tuple<int,int>,RF> l0_map_alpha;

    gi_a.update_hangingNodes();
    gi_alpha.update_hangingNodes();

    discreteDeformation_a.construct_l0(l0_map_a,mass_a);
    discreteDeformation_alpha.construct_l0(l0_map_alpha,mass_alpha);

    const typename GeoGV_shmoo::IndexSet& indexSet_a = geo_gv_a_shmoo.indexSet();
    const typename GeoGV_shmoo::IndexSet& indexSet_alpha = geo_gv_alpha_shmoo.indexSet();

    Dune::FieldVector<Coord,dimworld> point1_a, point2_a, point3_a;
    Dune::FieldVector<Coord,dimworld> point1_alpha, point2_alpha, point3_alpha;
    Dune::FieldVector<Coord,dimworld> edge1_a, edge2_a, edge3_a;
    Dune::FieldVector<Coord,dimworld> edge1_alpha, edge2_alpha, edge3_alpha;

    for (ElementIterator eit_a = geo_gv_a_shmoo.template begin<0>(); eit_a!= geo_gv_a_shmoo.template end<0>(); ++eit_a)
    {
      point1_a = eit_a->geometry().corner(0);
      point2_a = eit_a->geometry().corner(1);
      point3_a = eit_a->geometry().corner(2);

      edge1_a = point1_a; edge1_a -= point2_a;
      edge2_a = point2_a; edge2_a -= point3_a;
      edge3_a = point3_a; edge3_a -= point1_a;

      int idx = indexSet_a.index(*eit_a);

      surface_area_a += eit_a->geometry().volume();
    }

    for (ElementIterator eit_alpha = geo_gv_alpha_shmoo.template begin<0>(); eit_alpha!= geo_gv_alpha_shmoo.template end<0>(); ++eit_alpha)
    {
      point1_alpha = eit_alpha->geometry().corner(0);
      point2_alpha = eit_alpha->geometry().corner(1);
      point3_alpha = eit_alpha->geometry().corner(2);

      edge1_alpha = point1_alpha; edge1_alpha -= point2_alpha;
      edge2_alpha = point2_alpha; edge2_alpha -= point3_alpha;
      edge3_alpha = point3_alpha; edge3_alpha -= point1_alpha;

      int idx = indexSet_alpha.index(*eit_alpha);

      surface_area_alpha += eit_alpha->geometry().volume();
    }

    //if(shmooing_a == 1)
    //{
        grid_data_a.construct_elasticityCoeff(time, position_a_cell);
        TRBSForceField<GeoGV_shmoo,GI,GD> normal_gen_a(geo_gv_a_shmoo,gi_a,grid_data_a,params,l0_map_a,mass_a,dt,time,position_signal_a_cell);
        normal_gen_a.getVertexFields(normal_a,area_node_a,pressure_f_a,elasticity_f_a,stress_field_a,strain_field_a,growth_zone_a);
    //}

    //if(shmooing_alpha == 1)
    //{
        grid_data_alpha.construct_elasticityCoeff(time, position_alpha_cell);
        TRBSForceField<GeoGV_shmoo,GI,GD> normal_gen_alpha(geo_gv_alpha_shmoo,gi_alpha,grid_data_alpha,params,l0_map_alpha,mass_alpha,dt,time,position_signal_alpha_cell);
        normal_gen_alpha.getVertexFields(normal_alpha,area_node_alpha,pressure_f_alpha,elasticity_f_alpha,stress_field_alpha,strain_field_alpha,growth_zone_alpha);
    //}

    //Dune::PDELab::FilenameHelper fn(params.get<std::string>("global.output"));

    /*******************************************************************
     * general solver settings
     * ****************************************************************/

    int degree = params.get("fem.degree",(int)1);
    MBE mbe((int)pow(1+2*degree,dim));
    int torder = params.get("fem.torder",(int)1);

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

    /* *************************************************
     * init solver for mat a cell
     * *************************************************/

    // Make grid function space    
    FEM fem_a(geo_gv_a_shmoo);
    GFS gfs_a(geo_gv_a_shmoo,fem_a);
    gfs_a.name("Vh");

    // A coefficient vector
    Z z_a(gfs_a); // initial value

    // Make a grid function out of it
    ZDGF zdgf_a(gfs_a,z_a);

    /* später aktivieren für Rezeptor Anfangskonzentration */
    // Fill the coefficient vector
    //auto glambda_a = [&](const auto& e, const auto& x) {return problem_a.g(e,x);};
    //auto g_a = Dune::PDELab::makeInstationaryGridFunctionFromCallable(geo_gv_a_shmoo ,glambda_a, problem_a);
    //Dune::PDELab::interpolate(g_a, gfs_a,z_a);

    // Make instationary grid operator
    LOP lop_a(problem_a);
    GO0 go0_a(gfs_a,gfs_a,lop_a,mbe);
    TLOP tlop_a;
    GO1 go1_a(gfs_a,gfs_a,tlop_a,mbe);
    IGO igo_a(go0_a,go1_a);

    // Select a linear solver backend
    LS ls_a(100,0);

    // solve nonlinear problem
    PDESOLVER newton_a(igo_a,z_a,ls_a);
    newton_a.setReassembleThreshold(0.0);
    newton_a.setVerbosityLevel(0);
    newton_a.setReduction(1e-12);
    newton_a.setMinLinearReduction(1e-6);
    newton_a.setMaxIterations(25);
    newton_a.setLineSearchMaxIterations(10);

    // create solver object
    OSM  osm_a(*pmethod,igo_a,newton_a);
    osm_a.setVerbosityLevel(0);

    /* *************************************************
     * init solver for mat alpha cell
     * *************************************************/

    // Make grid function space    
    FEM fem_alpha(geo_gv_alpha_shmoo);
    GFS gfs_alpha(geo_gv_alpha_shmoo,fem_alpha);
    gfs_alpha.name("Vh");

    // A coefficient vector
    Z z_alpha(gfs_alpha); // initial value

    // Make a grid function out of it
    ZDGF zdgf_alpha(gfs_alpha,z_alpha);

    /* später aktivieren für Rezeptor Anfangskonzentration */
    // Fill the coefficient vector
    //auto glambda_alpha = [&](const auto& e, const auto& x) {return problem_alpha.g(e,x);};
    //auto g_alpha = Dune::PDELab::makeInstationaryGridFunctionFromCallable(geo_gv_alpha_shmoo ,glambda_alpha, problem_alpha);
    //Dune::PDELab::interpolate(g_alpha, gfs_alpha,z_alpha);


    // Make instationary grid operator
    LOP lop_alpha(problem_alpha);
    GO0 go0_alpha(gfs_alpha,gfs_alpha,lop_alpha,mbe);
    TLOP tlop_alpha;
    GO1 go1_alpha(gfs_alpha,gfs_alpha,tlop_alpha,mbe);
    IGO igo_alpha(go0_alpha,go1_alpha);

    // Select a linear solver backend
    LS ls_alpha(100,0);

    // solve nonlinear problem
    PDESOLVER newton_alpha(igo_alpha,z_alpha,ls_alpha);
    newton_alpha.setReassembleThreshold(0.0);
    newton_alpha.setVerbosityLevel(0);
    newton_alpha.setReduction(1e-8);
    newton_alpha.setMinLinearReduction(1e-4);
    newton_alpha.setMaxIterations(25);
    newton_alpha.setLineSearchMaxIterations(10);

    // create solver object
    OSM  osm_alpha(*pmethod,igo_alpha,newton_alpha);
    osm_alpha.setVerbosityLevel(0);

    /* *************************************************
     * init output location for mat a cell and write t0
     * *************************************************/

    // prepare VTK writer and write first file
    if(!outputLocation(output_a))
       return -1;

    VTKWRITER vtkwriter_a(geo_gv_a_shmoo,subsampling);
    VTKSEQUENCEWRITER vtkSequenceWriter_a(std::make_shared<VTKWRITER>(vtkwriter_a),output_a,output_a,"");
    vtkSequenceWriter_a.addVertexData(std::shared_ptr<VTKF>(new VTKF(zdgf_a,"solution_a")));

    // shmoo vector
    if(shmooing_a == 1)
    {
        vtkSequenceWriter_a.addVertexData (pressure_f_a, "pressure_f_a" , 3);
        vtkSequenceWriter_a.addVertexData (elasticity_f_a, "elasticity_f_a" , 3);
        vtkSequenceWriter_a.addCellData(E_coeff_a, "elasticity_coeff_a" , 1);
        vtkSequenceWriter_a.addCellData(stress_field_a, "stress_field_a" , 1);
        vtkSequenceWriter_a.addCellData(growth_zone_a, "growth_zone_a" , 1);
        vtkSequenceWriter_a.addCellData(strain_field_a, "strain_field_a" , 1);
    }

    std::vector<double> signalTrace_a(geo_gv_a_shmoo.size(0));
    vtkSequenceWriter_a.addCellData(signalTrace_a, "signal_trace_a");

    output(geogrid_signal_a, 0, output_a + "signal", 0.0);
    vtkSequenceWriter_a.write(time,Dune::VTK::appendedraw);

    /* *************************************************
     * init output location for mat alpha cell and write t0
     * *************************************************/

    // prepare VTK writer and write first file
    if(!outputLocation(output_alpha))
       return -1;

    VTKWRITER vtkwriter_alpha(geo_gv_alpha_shmoo,subsampling);
    VTKSEQUENCEWRITER vtkSequenceWriter_alpha(std::make_shared<VTKWRITER>(vtkwriter_alpha),output_alpha,output_alpha,"");
    vtkSequenceWriter_alpha.addVertexData(std::shared_ptr<VTKF>(new VTKF(zdgf_alpha,"solution_alpha")));

    // shmoo vector
    if(shmooing_alpha == 1)
    {
        vtkSequenceWriter_alpha.addVertexData (pressure_f_alpha, "pressure_f_alpha" , 3);
        vtkSequenceWriter_alpha.addVertexData (elasticity_f_alpha, "elasticity_f_alpha" , 3);
        vtkSequenceWriter_alpha.addCellData(E_coeff_alpha, "elasticity_coeff_alpha" , 1);
        vtkSequenceWriter_alpha.addCellData(stress_field_alpha, "stress_field_alpha" , 1);
        vtkSequenceWriter_alpha.addCellData(growth_zone_alpha, "growth_zone_alpha" , 1);
        vtkSequenceWriter_alpha.addCellData(strain_field_alpha, "strain_field_alpha" , 1);
    }

    std::vector<double> signalTrace_alpha(geo_gv_alpha_shmoo.size(0));
    vtkSequenceWriter_alpha.addCellData(signalTrace_alpha, "signal_trace_alpha");

    output(geogrid_signal_alpha, 0, output_alpha + "signal", 0.0);
    vtkSequenceWriter_alpha.write(time,Dune::VTK::appendedraw);

    /* *************************************************
     * init output location for a cell simulation data
     * *************************************************/

    std::string param_output_a = buildResultID("a", params, position_a_cell);
    std::ofstream results_a;
    results_a.open(param_output_a, std::ios::binary);
    buildOutputDataHeader(results_a,"a", position_a_cell, position_signal_a_cell, params,geo_gv_a_shmoo);

    /* *************************************************
     * init output location for alpha cell simulation data
     * *************************************************/

    std::string param_output_alpha = buildResultID("alpha", params, position_alpha_cell);
    std::ofstream results_alpha;
    results_alpha.open(param_output_alpha, std::ios::binary);
    buildOutputDataHeader(results_alpha, "alpha", position_alpha_cell, position_signal_alpha_cell, params,geo_gv_alpha_shmoo);

    /* *************************************************
     * finally - main solver loop
     * *************************************************/

    int signalIndex = 1;
    int outputIndex_a = 1;
    int outputIndex_alpha = 1;
    int lastSignalElementId_a = 0;
    int lastSignalElementId_alpha = 0;

    while (time<T-1e-8)
    {
        time += dt;


                   std::cout << "----------------- solving equation on mat a cell--------------------------------" << std::endl;
                   driver<GridType, Geogridtype, Geogridtype_shmoo, CellTransformFunction, DeformationFunctionTRBS<GVg,GI> >
                         (osm_a,                    // solver                                   - call by reference
                          gridView_a,               // GeoGrid view of cell, 1.level transform  - call by reference
                          geo_gv_a_shmoo,           // GeoGrid view of cell, 2.level transform  - const reference
                          gi_a,                     // GridInfo of shmoo                        - call by reference
                          grid_data_a,              // GridData of shmoo                        - call by reference
                          gfs_a,                    // Grid function space                      - call by reference
                          zdgf_a,                   //                                          - call by reference
                          z_a,
                          problem_a,                // difussion equation and boundaries        - call by reference
                          time,                     // current time                             - call by reference
                          dt,                       // current time step                        - call by reference
                          T,                        // simulation time                          - call by reference
                          DMem_a,                   // Membran difussion constant               - call by reference
                          nextTransitionTime_a,     // time when next signal transition on cell surface starts - call by reference
                          signalIndex,              // simulation time step                     - call by reference
                          outputIndex_a,            // output file number, incremented after each solver step - call by reference
                          lastSignalElementId_a,    // element ID of the current Signalposition, will be corrected after shmooing step - call by reference
                          geogrid_signal_a,         // GeoGrid of signal , 1.level transform    - call by reference
                          geogrid_a_shmoo,          // Geogrid of cell, 2.level transform       - call by reference
                          //cm_a,                     // cell transform function, 1.level         - call by reference
                          cm_signal_a,              // signal transform function, 1.level       - call by reference
                          discreteDeformation_a,    // cell transform function, 2.level         - call by reference
                          position_signal_a_cell,   // global coordinates of own cell signal    - call by reference
                          params,                   // parameter tree of init file              - call by reference
                          vtkSequenceWriter_a,      // cell writer obect                        - call by reference
                          signalTrace_a,            // vector for the signal trace on cell surface - call by reference
                          pressure_f_a,             // vector for pressure                      - call by reference
                          elasticity_f_a,           // vector for elasticity                    - call by reference
                          E_coeff_a,                // vector for coefficiants                  - call by reference
                          stress_field_a,           // vector for stress field                  - call by reference
                          growth_zone_a,            // vector for growth zone                   - call by reference
                          strain_field_a,           // vector for strains                       - call by reference
                          mass_a,                   //                                          - call by reference
                          normal_a,                 //                                          - call by reference
                          area_node_a,              //                                          - call by reference
                          l0_map_a,                 //                                          - call by reference
                          surface_area_a,           //                                          - call by reference
                          cell_volume_a,            //                                          - call by reference
                          volumeAtShock_a,          //                                          - call by reference
                          energyAtShock_a,          //                                          - call by reference
                          t_adapt_a,                //                                          - call by reference
                          output_a,                 // output file name for the cell            - call by value
                          shmooing_a,               // switches shmooing on/off                 - const reference
                          results_a);

                   std::cout << "----------------- solving equation on mat alpha cell----------------------------" << std::endl;

                   driver<GridType, Geogridtype, Geogridtype_shmoo, CellTransformFunction, DeformationFunctionTRBS<GVg,GI> >
                         (osm_alpha,
                          gridView_alpha,
                          geo_gv_alpha_shmoo,           // GeoGrid of cell, 2.level transform       - const reference
                          gi_alpha,                     // GridInfo of shmoo                        - call by reference
                          grid_data_alpha,              // GridData of shmoo                        - call by reference
                          gfs_alpha,
                          zdgf_alpha,
                          z_alpha,
                          problem_alpha,
                          time,
                          dt,
                          T,
                          DMem_alpha,
                          nextTransitionTime_alpha,
                          signalIndex,
                          outputIndex_alpha,
                          lastSignalElementId_alpha,
                          geogrid_signal_alpha,
                          geogrid_alpha_shmoo,          // Geogrid of cell, 2.level transform       - call by reference
                          //cm_alpha,
                          cm_signal_alpha,
                          discreteDeformation_alpha,    // cell transform function, 2.level         - call by reference
                          position_signal_alpha_cell,
                          params,
                          vtkSequenceWriter_alpha,
                          signalTrace_alpha,
                          pressure_f_alpha,             // vector for pressure                      - call by reference
                          elasticity_f_alpha,           // vector for elasticity                    - call by reference
                          E_coeff_alpha,                // vector for coefficiants                  - call by reference
                          stress_field_alpha,           // vector for stress field                  - call by reference
                          growth_zone_alpha,            // vector for growth zone                   - call by reference
                          strain_field_alpha,           // vector for strains                       - call by reference
                          mass_alpha,                   //                                          - call by reference
                          normal_alpha,                 //                                          - call by reference
                          area_node_alpha,              //                                          - call by reference
                          l0_map_alpha,                 //                                          - call by reference
                          surface_area_alpha,           //                                          - call by reference
                          cell_volume_alpha,            //                                          - call by reference
                          volumeAtShock_alpha,          //                                          - call by reference
                          energyAtShock_alpha,          //                                          - call by reference
                          t_adapt_alpha,                //                                          - call by reference
                          output_alpha,
                          shmooing_alpha,               // switches shmooing on/off                 - const reference
                          results_alpha);
                   std::cout << "==================================================================================" << std::endl;


        problem_alpha.setSignal(position_signal_a_cell);
        problem_a.setSignal(position_signal_alpha_cell);        


        signalIndex++;

    }

    // close output file
    results_a.close();
    results_alpha.close();
    return 0;

}
