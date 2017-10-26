#ifndef GRIDVECTOR
#define GRIDVECTOR

#include<iostream>
#include<cstdlib>

template <class T, unsigned n>
class GridVector
{
private:
    T               _p1;
    T               _p2;
    double          _norm;
    unsigned int    _dim;
    double _theta;
    double _phi;

public:

    GridVector(T p1, T p2):_p1(p1), _p2(p2)
    {
        _dim = n;
        _norm = this->norm();

        double x = _p2[0]-_p1[0];
        double y = _p2[1]-_p1[1];

        if(_dim == 2)
            _phi = acos((_p2[0]-_p1[0])/_norm);

        else if(_dim == 3)
        {
            double z = _p2[2]-_p1[2];
            _theta = acos(z/_norm);
            if(x>0)
                _phi = atan(y/x);
            else if(x==0)
                _phi = sgn(y)*(M_PI/2);
            else if(x<0 && y >= 0)
                _phi = atan(y/x) + M_PI;
            else if(x<0 && y<0)
                _phi = atan(y/x) - M_PI;
            else
                _phi = 0.0;
        }
    }

    GridVector(T p1, double t, double p, double d):_p1(p1), _theta(t), _phi(p), _norm(d)
    {
        _dim = n;
        if(_dim == 2)
        {
            _p2[0] = _norm * cos(_phi) + _p1[0];
            _p2[1] = _norm * sin(_phi) + _p1[1];

        }
        else if(_dim == 3)
        {
            _p2[0] = _norm * sin(_theta) * cos(_phi) + _p1[0];
            _p2[1] = _norm * sin(_theta) * sin(_phi) + _p1[1];
            _p2[2] = _norm * cos(_theta) + _p1[2];
        }       
    }

    double operator*(const GridVector<T,n>& v)
    {
        double skalar_produkt = this->skalarproduct(this->target(), v.target());
        double norm_produkt = this->norm()*v.norm();
        double fraction = skalar_produkt/norm_produkt;
        //this->_theta = acos(fraction);
        return fraction;
    }

    GridVector<T,n>& operator-(const GridVector<T,n>& v)
    {
        T vRes;
        for(int i=0; i <= _dim; ++i)
            vRes[i] = this->target()[i] - v.target()[i];
        return vRes;
    }

    friend std::ostream& operator<<(std::ostream &os, const GridVector& gv)
    {
        os << "source:(";
        for(int i=0; i < gv._dim; ++i)
            os << gv._p1[i] << " ";
        os << ")";
        os << " target:(";
        for(int i=0; i < gv._dim; ++i)
            os << gv._p2[i] << " ";
        os << ")";
        os << " norm: " << gv.norm() << std::flush;

        return os;
    }

    const double norm() const
    {
        double sum_squared = 0.0;
        for(int i=0; i < _dim; ++i)
            sum_squared += pow((_p1[i]-_p2[i]),2);
        return sqrt(sum_squared);
    }

    const double skalarproduct(const T& p1, const T& p2) const
    {
        double sum = 0.0;
        for(int i=0; i < _dim; ++i)
            sum += p1[i]*p2[i];
        return sum;
    }

    const T& source() const{return _p1;}
    const T& target() const{return _p2;}

    //helper
private:
    template <typename V> int sgn(V val) {
        return (V(0) < val) - (val < V(0));
    }
};

#endif // GRIDVECTOR
