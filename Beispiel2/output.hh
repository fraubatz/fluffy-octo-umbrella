#ifndef OUTPUT_HH
#define OUTPUT_HH

#include<fstream>

template <typename G>
void output(G& grid, int index, std::string target, double level=0.0)
{
    // get the instance of the LeafGridView
    typedef typename G::LeafGridView LeafGridView;
    LeafGridView leafView = grid.leafGridView();

    int vertexCount = leafView.size(2);
    Dune::VTKWriter<LeafGridView> vtkWriter(leafView);

    std::vector<double> solutionVector(vertexCount,level);

    vtkWriter.addVertexData(solutionVector, "solution");

    std::string filename = target + std::to_string(index);
    vtkWriter.write(filename);
}

bool outputLocation(const std::string& filename)
{
    bool done = true;
    struct stat st;

    if( stat( filename.c_str(), &st ) != 0 )
    {
        int stat = 0;
        stat = mkdir(filename.c_str(),S_IRWXU|S_IRWXG|S_IRWXO);
        if( stat != 0 && stat != -1)
        {
            std::cout << "Error: Cannot create directory " << filename << std::endl;
            done = false;
        }
    }
    return done;
}

template <typename V>
std::string buildResultID(const std::string& cell, const Dune::ParameterTree& ptree, const V& Cellpos)
{
    std::string filename = cell+"_";


    // cell
    std::ostringstream strsX;
    strsX << std::fixed << std::setprecision(2) << Cellpos[0];
    std::string x = strsX.str();
    std::ostringstream strsY;
    strsY << std::fixed << std::setprecision(2) << Cellpos[1];
    std::string y = strsY.str();
    std::ostringstream strsZ;
    strsZ << std::fixed << std::setprecision(2) << Cellpos[2];
    std::string z = strsZ.str();
    std::string mesh = ptree.get<std::string>("global.meshname");
    std::string r = ptree.get<std::string>("cell_"+cell+".radius");
    std::string D_a = ptree.get<std::string>("cell_"+cell+".DMem");
    std::string kappa = ptree.get<std::string>("cell_"+cell+".Kappa");
    std::string km = ptree.get<std::string>("cell_"+cell+".Km");

    // signal
    std::string theta_s = ptree.get<std::string>("signal_"+cell+".theta");
    std::string phi_s = ptree.get<std::string>("signal_"+cell+".phi");
    std::string D_s = ptree.get<std::string>("signal_"+cell+".D");
    std::string cb = ptree.get<std::string>("signal_"+cell+".CB");
    std::string kb = ptree.get<std::string>("signal_"+cell+".kB");

    filename += "mesh" + mesh
             + "_X" + x
             + "_Y" + y
             + "_Z" + z
             + "_R" + r
             + "_D" + D_a
             + "_Kappa" + kappa
             + "_Km" + km
             + "_ThetaS" + theta_s
             + "_PhiS" + phi_s
             + "_Ds" + D_s
             + "_Bar1" + cb
             + "_kB" + kb
             + ".csv";
    return filename;
}

template <typename V, typename GV>
void buildOutputDataHeader(std::ofstream& filehandle, const std::string& cell, const V& PosCell, const V& PosSignal, const Dune::ParameterTree& ptree, const GV& gridview)
{
    std::string mesh = ptree.get<std::string>("global.meshname");

    // cell
    double x = PosCell[0];
    double y = PosCell[1];
    double z = PosCell[2];
    double r = ptree.get<double>("cell_"+cell+".radius");
    double D_a = ptree.get<double>("cell_"+cell+".DMem");
    double kappa = ptree.get<double>("cell_"+cell+".Kappa");
    double km = ptree.get<double>("cell_"+cell+".Km");

    // signal
    double xs = PosSignal[0];
    double ys = PosSignal[1];
    double zs = PosSignal[2];
    //double theta_s = ptree.get<double>("signal_"+cell+".theta");
    //double phi_s = ptree.get<double>("signal_"+cell+".phi");
    double D_s = ptree.get<double>("signal_"+cell+".D");
    double cb = ptree.get<double>("signal_"+cell+".CB");
    double kb = ptree.get<double>("signal_"+cell+".kB");

    filehandle << "GENERAL:\n";
    filehandle << "Grids both cells:," << mesh << "\n";
    filehandle << "Grid nodes:," << gridview.size(2) << "\n";
    filehandle << "Grid elements:," << gridview.size(0) << "\n";
    //filehandle << "Cell distance in um (center to center):," << d << "\n";
    //filehandle << "Cell distance in um (edge to edge):," << d-r_a-r_alpha << "\n";
    filehandle << "\n";
    filehandle << "CELL:\n";
    filehandle << "Position (X Y Z):," << x << "," << y << "," << z << "\n";
    filehandle << "Radius:," << r << "\n";
    filehandle << "Difussion Constant:," << D_a << "\n";
    filehandle << "Receptor amplification Kappa:," << kappa << "\n";
    filehandle << "Receptor kinetics Km:," << km << "\n";
    filehandle << "\n";
    filehandle << "SIGNAL:\n";
    filehandle << "Position (X Y Z):," << xs << "," << ys << "," << zs << "\n";
    //filehandle << "Radius:," << r_alpha << "\n";
    filehandle << "Difussion Constant:," << D_s << "\n";
    filehandle << "Bar Concentration:," << cb << "\n";
    filehandle << "Bar degradation rate:," << kb << "\n";
    filehandle << "\n";
    filehandle << "SIMULATION_DATA:\n";
    filehandle << "time,highestC,XhighestC,YhighestC,ZhighestC,XSignal,YSignal,ZSignal,elementID,num_cdc42,lambda,transitionTime\n";
}

template <typename V>
void outputData(std::ofstream& filehandle, double time, double highC, V highCPos, V sigPos, int sigEleId, double numcdc42, double lambda, double transitionTime)
{
    //filehandle << "time,highestC,XhighestC,YhighestC,ZhighestC,XSignal,YSignal,ZSignal,elementID\n";
    filehandle << time
               << ","
               << highC << std::fixed << std::setprecision(4)
               << ","
               << highCPos[0]
               << ","
               << highCPos[1]
               << ","
               << highCPos[2]
               << ","
               << sigPos[0]
               << ","
               << sigPos[1]
               << ","
               << sigPos[2]
               << ","
               << sigEleId
               << ","
               << numcdc42
               << ","
               << lambda
               << ","
               << transitionTime
               << '\n';
}

#endif // OUTPUT_HH

