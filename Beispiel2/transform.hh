#ifndef TRANSFORM_HH
#define TRANSFORM_HH

class CellTransformFunction: public Dune::AnalyticalCoordFunction< double, 3, 3, CellTransformFunction >
{
private:
  double tx;
  double ty;
  double tz;
  double radius;
  typedef CellTransformFunction This;
  typedef Dune::AnalyticalCoordFunction< double, 3, 3, This > Base;
public:
  CellTransformFunction() : tx(0.0),ty(0.0),tz(0.0), radius(1.0){}
  typedef Base::DomainVector DomainVector;
  typedef Base::RangeVector RangeVector;

  void evaluate ( const DomainVector &x, RangeVector &y ) const
  {
    y[0] = x[0]*radius + tx;
    y[1] = x[1]*radius + ty;
    y[2] = x[2]*radius + tz;
  }

  void move(const double x, const double y, const double z, const double r)
  {
    tx = x;
    ty = y;
    tz = z;
    radius = r;
  }
};

#endif // TRANSFORM_HH

