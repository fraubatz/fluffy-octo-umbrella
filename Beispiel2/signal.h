#ifndef SIGNAL
#define SIGNAL

template<typename Coord>
class Signal
{
    Coord _source;  /* signal location */
    double _kb;     /* bar1 degradation rate */
    double _D_s;    /* difussion constant */
    double _amp;    /* signal power */

    Signal(const Coord& s, const double kb, const double ds, const double amp) : _source(s), _kb(kb), _D_s(ds), _amp(amp){}

    const double degradation(){return _kb;}
    const double amplifikation(){return _amp;}
    const double difussionRate(){return _D_s;}
};

#endif // SIGNAL

