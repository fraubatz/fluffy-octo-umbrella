import java.awt.*;
import java.awt.event.*;


class DlgLayerPnl extends Panel implements ActionListener,
										   AdjustmentListener,
										   ItemListener
{
	private Checkbox	_symbolLayer;
	private Checkbox	_textLayer;
	private Checkbox	_pinLayer;
	private Checkbox	_layoutLayer;
	private Checkbox	_netLayer;
	private Checkbox	_userLayer;
	private Button		_buttonOK;
	private Button		_buttonCancel;
	private Dialog		_dlg;
	private SmMainFrame	_mainFrame;
	private SmBorder	_border;

	private boolean		_symbolLayerOn;
	private boolean		_layoutLayerOn;
	private boolean		_textLayerOn;
	private boolean		_netLayerOn;
	private boolean		_pinLayerOn;

	public DlgLayerPnl(Dialog dlg, SmMainFrame frame)
	{
		_dlg = dlg;
		_mainFrame = frame;

		setLayout (null);

		_pinLayer = new Checkbox ("Pins");
		_pinLayer.addItemListener (this);
		_pinLayer.setBounds (40, 100, 61, 21);
		add (_pinLayer);

		_symbolLayer = new Checkbox ("Symbole");
		_symbolLayer.addItemListener (this);
		_symbolLayer.setBounds (40, 40, 70, 21);
		add (_symbolLayer);

		_layoutLayer = new Checkbox ("Shapes");
		_layoutLayer.addItemListener (this);
		_layoutLayer.setBounds (40, 130, 66, 21);
		add (_layoutLayer);

		_textLayer = new Checkbox ("Annotationen");
		_textLayer.addItemListener (this);
		_textLayer.setBounds (40, 70, 93, 21);
		add (_textLayer);
	
		_netLayer = new Checkbox ("Netze");
		_netLayer.addItemListener (this);
		_netLayer.setBounds (40, 160, 61, 21);
		add (_netLayer);

		_userLayer = new Checkbox ("Benutzerdefiniert");
		_userLayer.addItemListener (this);
		_userLayer.setBounds (40, 206, 109, 21);
		add (_userLayer);

		_buttonOK = new Button ("OK");
		_buttonOK.addActionListener (this);
		_buttonOK.setBounds (20, 260, 65, 21);
		add (_buttonOK);

		_buttonCancel = new Button ("Abbrechen");
		_buttonCancel.addActionListener (this);
		_buttonCancel.setBounds (115, 260, 65, 21);
		add (_buttonCancel);

		_border = new SmBorder(this, "Layer :", 20, 20, 160, 220);

		_symbolLayerOn = true;
		_symbolLayer.setState(true);

		_layoutLayerOn = true;
		_layoutLayer.setState(true);

		_textLayerOn = true;
		_textLayer.setState(true);

		_netLayerOn = true;
		_netLayer.setState(true);

		_pinLayerOn = true;
		_pinLayer.setState(true);
	}

	public void paint(Graphics g)
	{
		_border.paint(g);
	}

	public Dimension getPreferredSize()
	{
		return new Dimension (201, 296);
	}

	public void	actionPerformed(ActionEvent evt)
	{
		if (evt.getSource () == _buttonOK)
		{
			  SmPanelCenter _currentCenter = _mainFrame.getCurrentCenter();
			  if (_currentCenter != null)
			  {
				  SmWorkingArea _workingArea = _currentCenter.getWorkingArea();
				  _workingArea.setLayer(_symbolLayerOn, _netLayerOn, _pinLayerOn,
										_textLayerOn, _layoutLayerOn);	
			  }
			  _dlg.hide();
			  return;
		}
		if (evt.getSource () == _buttonCancel)
		{
			  _dlg.hide();
			  return;
		}
	}

	public void	adjustmentValueChanged(AdjustmentEvent evt)
	{

	}

	public void	itemStateChanged(ItemEvent evt)
	{
		if (evt.getSource () == _pinLayer)
		{
			  if (_pinLayerOn)
			  {
				  _pinLayerOn = false;
				  _pinLayer.setState(false);
			  }
			  else
			  {
				  _pinLayerOn = true;
				  _pinLayer.setState(true);
			  }
			  return;
			  
		}
		if (evt.getSource () == _symbolLayer)
		{
			  if (_symbolLayerOn)
			  {
				  _symbolLayerOn = false;
				  _symbolLayer.setState(false);
			  }
			  else
			  {
				  _symbolLayerOn = true;
				  _symbolLayer.setState(true);
			  }
			  return;
		}
		if (evt.getSource () == _layoutLayer)
		{
			  if (_layoutLayerOn)
			  {
				  _layoutLayerOn = false;
				  _layoutLayer.setState(false);
			  }
			  else
			  {
				  _layoutLayerOn = true;
				  _layoutLayer.setState(true);
			  }
			  return;
		}
		if (evt.getSource () == _textLayer)
		{
			  if (_textLayerOn)
			  {
				  _textLayerOn = false;
				  _textLayer.setState(false);
			  }
			  else
			  {
				  _textLayerOn = true;
				  _textLayer.setState(true);
			  }
			  return;
		}
		if (evt.getSource () == _netLayer)
		{
			  if (_netLayerOn)
			  {
				  _netLayerOn = false;
				  _netLayer.setState(false);
			  }
			  else
			  {
				  _netLayerOn = true;
				  _netLayer.setState(true);
			  }
			  return;
		}
		if (evt.getSource () == _userLayer)
		{
			  
		}
	}
} 