template<typename Number, typename SignalSource>
class Problem
{
  Number _eta;
  Number _DMem;
  Number _DSig;
  Number _kB;
  Number _t;
  SignalSource _s;
  Number _sAmp;
  Number _hMem;
  Number _hRefVol;
  Number _hActVol;
  Number _Kappa;
  Number _k0; //basal cdc42 flow to membrane
  Number _v0; // Bifurcation parameter
  Number _Km;
  Number _Vref;
  Number _Vact;


public:
  typedef Number value_type;

  //! Constructor without arg sets nonlinear term to zero
  Problem() : _eta(0.0), _t(0.0) {}

  //! Constructor takes eta parameter
  Problem(const Number& eta,
          const SignalSource& s,
          const Number sAmp,
          const Number hmem,
          const Number hvol,
          const Number Dmem,
          const Number kappa,
          const Number k0,
          const Number v0,
          const Number km,
          const Number Dsig,    /*signal*/
          const Number kb,      /*signal*/
          const Number r_reference) : _eta(eta), _t(0.0), _s(s), _sAmp(sAmp), _hMem(hmem), _hRefVol(hvol), _DMem(Dmem), _Kappa(kappa), _k0(k0), _v0(v0), _Km(km), _DSig(Dsig), _kB(kb)
  {
      _Vref = 4.0/3.0*M_PI*pow(r_reference,3);
      _Vact = _Vref;
      _hActVol = _hRefVol;
  }

  //! nonlinearity
  template<typename E, typename X>
  Number q (const E& e, const X& x, Number u) const
  {


      Number sum_radicand = 0.0;
      auto global = e.geometry().global(x);
      for (std::size_t i=0; i<global.size(); i++)
        sum_radicand += pow((global[i]-_s[i]),2);
      Number distance = sqrt(sum_radicand);
      Number lam = sqrt(_DSig/_kB);
      // Pheromone concentration
      Number ph = _sAmp/distance * exp(-distance/lam);
      // Reaction kinetics, u is the concentration of activated signaling molecules
      Number f = (1.0 - _hMem)*(ph + _v0 * _k0 + _v0 * (_Kappa*u*u/(_Km*_Km + u*u))) - _eta*u;

      return f;
  }

  //! derivative of nonlinearity
//  Number qprime (Number u) const
//  {
//    return 2*_eta*u;
//  }

  //! right hand side
  template<typename E, typename X>
  Number f (const E& e, const X& x) const
  {
      return 0.0;
  }

  //! boundary condition type function (true = Dirichlet)
  template<typename I, typename X>
  bool b (const I& i, const X& x) const
  {
    auto global = i.geometry().global(x);
    return (global[0]<=1e-7) ? true : false;
  }

  //! Dirichlet extension
  template<typename E, typename X>
  Number g (const E& e, const X& x) const
  {
    return 0.02;
  }

  //! Neumann boundary condition
  template<typename I, typename X>
  Number j (const I& i, const X& x) const
  {
    return 0.0;
  }

  Number d()
  {
      return _DMem;
  }

  //! Set time in instationary case
  void setTime (Number t)
  {
    _t = t;
  }

  //! Set signal source
  void setSignal (SignalSource s)
  {
      _s = s;
  }

  //! Set number of cdc42 Molecules on membrane surface
  void setMembraneCdc42(const int h)
  {
      _hActVol = (_Vact/_Vref)*_hRefVol;
      _hMem = h/_hActVol;
      std::cout << "Vref: " << _Vref << " Vcat: " << _Vact << " max amount of cdc42 molecules: " << _hActVol << " hMem: " << _hMem << std::endl;
  }

  //! Set current Volume Vact
  void setVolume(Number vol)
  {
      _Vact = vol;
  }

  //! Get Cdc42 amount
  const Number getCdc42Amount()
  {
      return _hActVol;
  }

};
