#ifndef EVALUATOR_HH
#define EVALUATOR_HH

template<typename V, typename GV, typename GFS, typename Z, typename ZDGF>
class Evaluator
{
private:
    V _vertexPeakPos;                   // held the position of the vertex with the highest concentration value u_h
    V _signalPosition;                  // held the position of the own pheromone signal source
    V _signalNextPosition;              // held the position of the own pheromone signal source AFTER the transition
    V _signalNeighbourPropabilities;    // the transition probabilities to the 3 neighboures of an element, depending on concentration gradient
    V _signalNeighbourElementIds;       // the element identifier of all neighbours of the current element which held the signal

    double _highestValue;               // the highest concentration value of u_h
    double _lambda;                     // the lambda factor for the exponential distribution in driver, calculated length between signal pre- and post position
    double _difussionConstant;          // diffussion constant for lambda calculation
    double _num_cdc42;

    ZDGF* _discretGridFunction;
    GFS  _gfs;
    GV   _gridView;

    int  _elementID;                    // element identifier for own pheromone signal source
    int  _surface_adjustment;           // Adjustment for the point in triangle function in case of low grid resolution
    bool _elementFound;                 // indicates if the point in triangle function was successful
    const double M2MOLCONVERTER  = 1e-21;
    const double AVOGADRO        = 6.022e23;

public:
    Evaluator(const GV& gv, GFS& gfs, ZDGF* zdgf, Dune::ParameterTree& ptree, double diffConst) : _gridView(gv),
                                                                                                _gfs(gfs),
                                                                                                _discretGridFunction(zdgf),
                                                                                                _difussionConstant(diffConst),
                                                                                                _highestValue(0.0),
                                                                                                _lambda(0.1),
                                                                                                _elementID(0),
                                                                                                _num_cdc42(0),
                                                                                                _elementFound(false)
    {
        int adjustment   = ptree.get("global.surfaceadjustment",(int)0);
        //double diffConst = ptree.get("problem.D",(double)1.0);
        _surface_adjustment = adjustment;
        //_difussionConstant  = diffConst;
        std::cout << "surface adjustment is: " << _surface_adjustment << std::endl;
    }

    void evaluate()
    {
        const int dim = GV::dimension;

        std::vector<unsigned int> subidx(3);
        const typename GV::IndexSet& indexSet = _gridView.indexSet();

        typedef typename Dune::PDELab::DiscreteGridFunction< GFS, Z >::Traits::RangeType RT;
        RT value0, value1, value2;
        typedef typename GV::template Codim<0>::Entity element;

        double surface_area = 0.0;
        double average = 0.0;

        for(const auto& e : elements(_discretGridFunction->getGridView()))
        {
            Dune::GeometryType gt = e.type();
            const Dune::FieldVector<int,dim> inside_local0 = Dune::ReferenceElements<int,dim>::general(gt).position(0,2);
            const Dune::FieldVector<int,dim> inside_local1 = Dune::ReferenceElements<int,dim>::general(gt).position(1,2);
            const Dune::FieldVector<int,dim> inside_local2 = Dune::ReferenceElements<int,dim>::general(gt).position(2,2);
            _discretGridFunction->evaluate(e, inside_local0, value0);
            _discretGridFunction->evaluate(e, inside_local1, value1);
            _discretGridFunction->evaluate(e, inside_local2, value2);

            double meanval = mean(value0, value1, value2);
            double volume = e.geometry().volume();

            average += volume*meanval;
            surface_area += volume;

            if(meanval - _highestValue > 1e-08)
            {
                _highestValue = meanval;
                for(int i=0; i < dim+1; ++i)
                    _vertexPeakPos[i] = e.geometry().corner(0)[i];
            }
        }

        _num_cdc42 = average*M2MOLCONVERTER*AVOGADRO;//surface_area;
        std::cout << "--average:" << average << "-------surface area: " << surface_area << "----- cdc42: " << _num_cdc42 << "---------------------------" << std::endl;
    }

    void evaluate(const V& signal, const double& time, const double& dt, const double& T)
    {
        const int dim = GV::dimension;

        for(int i=0; i<signal.size();++i)
            _signalPosition[i] = signal[i];

        std::vector<unsigned int> subidx(3);
        const typename GV::IndexSet& indexSet = _gridView.indexSet();

        typedef typename Dune::PDELab::DiscreteGridFunction< GFS, Z >::Traits::RangeType RT;
        RT value0, value1, value2;
        typedef typename GV::template Codim<0>::Entity element;
        //for (ElementLeafIterator it = _discretGridFunction->getGridView().template begin<0>(); it != _discretGridFunction->getGridView().template end<0>(); ++ it)
        for(const auto& e : elements(_discretGridFunction->getGridView()))
        {
            subidx[0] = indexSet.subIndex(e, 0, 2);
            subidx[1] = indexSet.subIndex(e, 1, 2);
            subidx[2] = indexSet.subIndex(e, 2, 2);

            Dune::GeometryType gt = e.type();
            const Dune::FieldVector<int,dim> inside_local0 = Dune::ReferenceElements<int,dim>::general(gt).position(0,2);
            const Dune::FieldVector<int,dim> inside_local1 = Dune::ReferenceElements<int,dim>::general(gt).position(1,2);
            const Dune::FieldVector<int,dim> inside_local2 = Dune::ReferenceElements<int,dim>::general(gt).position(2,2);
            _discretGridFunction->evaluate(e, inside_local0, value0);
            _discretGridFunction->evaluate(e, inside_local1, value1);
            _discretGridFunction->evaluate(e, inside_local2, value2);
            //std::cout << "y= " << value0 << ", " << value1 << ", "  << value2 << " at index (" << subidx[0] << "," << subidx[1] << "," << subidx[2] <<  ")" << " center = " << e.geometry().center() << std::endl;

            double meanval = mean(value0, value1, value2);
/*
            if(meanval - _highestValue > 1e-08)
            {
                _highestValue = meanval;
                for(int i=0; i < dim+1; ++i)
                    _vertexPeakPos[i] = e.geometry().corner(0)[i];
            }

*/
            if(isPointInElement3(signal, e.geometry().corner(0), e.geometry().corner(1), e.geometry().corner(2)))
            {
                _elementFound = true;
                std::cout << "--------------- _elementID " << indexSet.index(e) << " --- signal Z: " << signal[2] << " --- element Z: " << e.geometry().center()[2] << std::endl;

                int idx = indexSet.index(e);

                RT value_neighbour0, value_neighbour1, value_neighbour2;
                V signalNeighbourDistValues;

                double sumDistMeanVal = 0.0;
                double negativeGradOffset = 0.0;
                typedef typename Dune::FieldVector<Dune::FieldVector<double,dim+1>,dim+1 > SIGNALPOSITIONS;
                SIGNALPOSITIONS sp;
                int n = 0;

                for(const auto& i : intersections(_discretGridFunction->getGridView(),e))
                {
                    if(i.neighbor())
                    {
                        //std::cout << "huhu element " << _elementID << std::endl;
                        element e_neighbour = i.outside();

                        _discretGridFunction->evaluate(e_neighbour, inside_local0, value_neighbour0);
                        _discretGridFunction->evaluate(e_neighbour, inside_local1, value_neighbour1);
                        _discretGridFunction->evaluate(e_neighbour, inside_local2, value_neighbour2);
                        double meanval_neighbour = mean(value_neighbour0, value_neighbour1, value_neighbour2);
                        double puredist = meanval_neighbour-meanval;
                        double distToNeighbour = std::abs(meanval_neighbour-meanval);
                        if(puredist < 0.0 && distToNeighbour > negativeGradOffset)
                            negativeGradOffset = distToNeighbour;

                        signalNeighbourDistValues[n] = puredist;
                        sp[n] = e_neighbour.geometry().center();
                        _signalNeighbourElementIds[n] = indexSet.index(e_neighbour);
                    }
                    n++;
                }

                int neighbours = signalNeighbourDistValues.size();

                // add offset to remove negative gradients
                for(int i=0; i<neighbours; ++i)
                    signalNeighbourDistValues[i] += 2*negativeGradOffset;

                // calculate new meanvalue after offset
                for(int k=0; k<neighbours; ++k)
                    sumDistMeanVal +=signalNeighbourDistValues[k];

                // calculate new probabilities
                for(int j=0; j<neighbours; ++j)
                    signalNeighbourDistValues[j] /= sumDistMeanVal;

                std::cout << "probs= " << signalNeighbourDistValues << std::endl;

                for(int n=0; n<neighbours; ++n)
                {
                    double p = 0.0;
                    for(int m=0; m<=n; ++m)
                        p += signalNeighbourDistValues[m];
                    _signalNeighbourPropabilities[n] = p;
                }

                int moveIndex = diceNextMove();
                _signalNextPosition = sp[moveIndex];
                _elementID = _signalNeighbourElementIds[moveIndex];

                calcLambdaFactor();

                return;
            }

            //_elementID++;
        }

    }

private:
    double sign(const V& v1, const V& v2, const V& v3)
    {
        double res = (v1[0]- v3[0])* (v2[1]- v3[1]) - (v2[0]- v3[0])* (v1[1]- v3[1]);
        return res;
    }

    bool isPointInElement(const V& v0, const V& v1, const V& v2, const V& v3)
    {
        bool b1 = sign(v0, v1, v2) <= 0.0;
        bool b2 = sign(v0, v2, v3) <= 0.0;
        bool b3 = sign(v0, v3, v1) <= 0.0;
        return (b1==b2) && (b2==b3);
    }

    bool isPointInElement2(const V& P, const V& A, const V& B, const V& C)
    {
        const int dim = 2;
        Dune::FieldVector<double,dim>v0, v1, v2;
        v0[0] = C[0]-A[0];
        v0[1] = C[1]-A[1];
        v1[0] = B[0]-A[0];
        v1[1] = B[1]-A[1];
        v2[0] = P[0]-A[0];
        v2[1] = P[1]-A[1];

        //const GridVector<Dune::FieldVector<double,dim>, dim> v0(vA, vC);
        //const GridVector<Dune::FieldVector<double,dim>, dim> v1(vA, vB);
        //const GridVector<Dune::FieldVector<double,dim>, dim> v2(vA, vP);

        // Compute dot products
        const double dot00 = v0[0]*v0[0]+v0[1]*v0[1];
        const double dot01 = v0[0]*v1[0]+v0[1]*v1[1];
        const double dot02 = v0[0]*v2[0]+v0[1]*v2[1];
        const double dot11 = v1[0]*v1[0]+v1[1]*v1[1];
        const double dot12 = v1[0]*v2[0]+v1[1]*v2[1];

        // Compute barycentric coordinates
        double invDenom = 1 / (dot00 * dot11 - dot01 * dot01);
        double u = (dot11 * dot02 - dot01 * dot12) * invDenom;
        double v = (dot00 * dot12 - dot01 * dot02) * invDenom;

        // Check if point is in triangle
        return (u >= 0) && (v >= 0);// && (u + v < 1);
    }
    bool isPointInElement3(const V& P, const V& A, const V& B, const V& C)
    {
        V v0, v1, v2;
        v0[0]=P[0]-A[0];
        v0[1]=P[1]-A[1];
        v0[2]=P[2]-A[2];
        double L0 = sqrt(pow(v0[0],2)+pow(v0[1],2)+pow(v0[2],2));

        v1[0]=P[0]-B[0];
        v1[1]=P[1]-B[1];
        v1[2]=P[2]-B[2];
        double L1 = sqrt(pow(v1[0],2)+pow(v1[1],2)+pow(v1[2],2));

        v2[0]=P[0]-C[0];
        v2[1]=P[1]-C[1];
        v2[2]=P[2]-C[2];
        double L2 = sqrt(pow(v2[0],2)+pow(v2[1],2)+pow(v2[2],2));

        double dot01=(v0[0]*v1[0]+v0[1]*v1[1]+v0[2]*v1[2])/(L0*L1);
        double dot12=(v1[0]*v2[0]+v1[1]*v2[1]+v1[2]*v2[2])/(L1*L2);
        double dot02=(v0[0]*v2[0]+v0[1]*v2[1]+v0[2]*v2[2])/(L0*L2);
        double anglesum=acos(dot01)+acos(dot12)+acos(dot02);

        //std::cout << anglesum << std::endl;

        if(anglesum < 6.29 && anglesum > (6.27 - _surface_adjustment))
            return true;
        else
            return false;
    }

    double mean(const double val0, const double val1, const double val2)
    {
        return (val0+val1+val2)/3;
    }

    const int diceNextMove()
    {
        double number = pickAnUniformDistributedNumber(0.0, 1.0);

        int moveIndex = 0;
        for(int i=0; i<_signalNeighbourPropabilities.size(); ++i)
        {
            if(number <= _signalNeighbourPropabilities[i])
                break;
            ++moveIndex;
        }
        std::cout << "sumprobs= " <<_signalNeighbourPropabilities << " number= " << number << " move index = " << moveIndex << std::endl;
        return moveIndex;
    }

    void calcLambdaFactor()
    {
        const int dim = GV::dimension;
        double msd = 0.0;
        for(int i=0; i<dim; ++i)
            msd += pow((_signalPosition[i] - _signalNextPosition[i]), 2);
        _lambda = (4*_difussionConstant)/(msd);
        std::cout << "difussion constant = " << _difussionConstant <<  ", msd = " << msd << std::endl;
    }

public:
    const int elementID(){return _elementID;}
    const double cdc42Particles(){return _num_cdc42;}
    double peakValue(){return _highestValue;}
    const V& peakValuePos(){return _vertexPeakPos;}
    const V& nextSignalPos()
    {
        if(_elementFound == true)
            return _signalNextPosition;
        else
            return _signalPosition;
    }
    const double lambda(){return _lambda;}
};

#endif // EVALUATOR_HH

