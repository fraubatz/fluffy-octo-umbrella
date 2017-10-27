import java.awt.*;
import java.awt.event.*;


public class DlgPinAttributePnl extends Panel implements ActionListener,
													     AdjustmentListener,
		                                                 ItemListener 
{
	private CheckboxGroup	_group1 = new CheckboxGroup();
	private CheckboxGroup	_group2 = new CheckboxGroup();
	private Checkbox		_pinFixed;
	private Checkbox		_pinContinueFree;
	private Checkbox		_pinFree;
    private Checkbox		_pinIn;
	private Checkbox		_pinOut;
	private Checkbox		_pinNoDirection;
	private Checkbox		_pinAppearance;
	private List			_pinAppearanceList;
	private Button			_buttonOK;
	private Button			_buttonBack;
	private Button			_buttonCancel;
	private SmBorder		_pinAttributeBorder;
	private SmBorder		_pinGeographicBorder;
	private SmBorder		_pinAppearanceBorder;

	public DlgPinAttributePnl() 
	{
		setLayout (null);

		_pinFixed = new Checkbox ("fest", _group1, true);
		_pinFixed.addItemListener (this);
		_pinFixed.setBounds (40, 50, 63, 23);
		add (_pinFixed);

		_pinContinueFree = new Checkbox ("frei(kontinuierlich)", _group1, false);
		_pinContinueFree.addItemListener (this);
		_pinContinueFree.setBounds (40, 80, 128, 23);
		add (_pinContinueFree);
	
		_pinFree = new Checkbox ("frei(diskret)", _group1, false);
		_pinFree.addItemListener (this);
		_pinFree.setBounds (40, 110, 92, 23);
		add (_pinFree);

		_pinIn = new Checkbox ("Eingang", _group2, false);
		_pinIn.addItemListener (this);
		_pinIn.setBounds (40, 180, 76, 23);
		add (_pinIn);

		_pinOut = new Checkbox ("Ausgang", _group2, false);
		_pinOut.addItemListener (this);
		_pinOut.setBounds (40, 210, 79, 23);
		add (_pinOut);
	
		_pinNoDirection = new Checkbox ("keine", _group2, true);
		_pinNoDirection.addItemListener (this);
		_pinNoDirection.setBounds (40, 240, 63, 23);
		add (_pinNoDirection);

		_pinAppearance = new Checkbox ("Symbol");
		_pinAppearance.addItemListener (this);
		_pinAppearance.setBounds (206, 53, 65, 23);
		add (_pinAppearance);

		_pinAppearanceList = new List ();
		_pinAppearanceList.addActionListener (this);
		_pinAppearanceList.addItemListener (this);
		_pinAppearanceList.setBounds (207, 89, 124, 64);
		_pinAppearanceList.add("UND_GAT_USA_OUT");
		_pinAppearanceList.add("ODER_GAT_DIN_IN");
        _pinAppearanceList.setBackground(this.getBackground());
		_pinAppearanceList.setEnabled(false);
		
		add (_pinAppearanceList);

		_buttonOK = new Button ("OK");
		_buttonOK.addActionListener (this);
		_buttonOK.setBounds (45, 300, 65, 21);
		add (_buttonOK);

		_buttonBack = new Button ("Zurück");
		_buttonBack.addActionListener (this);
		_buttonBack.setBounds (150, 300, 65, 21);
		add (_buttonBack);

		_buttonCancel = new Button ("Cancel");
		_buttonCancel.addActionListener (this);
		_buttonCancel.setBounds (255, 300, 65, 21);
		add (_buttonCancel);

		_pinGeographicBorder = new SmBorder(this,"Position :",30, 35, 140, 110);
		_pinAttributeBorder  = new SmBorder(this,"Flußrichtung :",30, 165 , 140, 110);
		_pinAppearanceBorder = new SmBorder(this,"Ausprägung : ", 192 , 35, 150, 240);
	}

	public Dimension getPreferredSize() 
	{
		return new Dimension (365, 349);
	}

	public void paint(Graphics g)
	{
		_pinGeographicBorder.paint(g);
		_pinAttributeBorder.paint(g);
		_pinAppearanceBorder.paint(g);
	}

	public void	actionPerformed(ActionEvent evt) 
	{
		if (evt.getSource () == _pinAppearanceList) 
		{
			  return;
		}
		if (evt.getSource () == _buttonOK) 
		{
		  return;
		}
		if (evt.getSource () == _buttonBack) 
		{
			  return;
		}
		if (evt.getSource () == _buttonCancel) 
		{
			  return;
		}
	}
	
	public void	adjustmentValueChanged(AdjustmentEvent evt) 
	{

	}

	public void	itemStateChanged(ItemEvent evt) 
	{
		if (evt.getSource () == _pinFixed) 
		{
			 return;
		}
		if (evt.getSource () == _pinContinueFree) 
		{
			 return;
		}
		if (evt.getSource () == _pinFree) 
		{
			  return;
		}
		if (evt.getSource () == _pinIn) 
		{
			  return;
		}
		if (evt.getSource () == _pinOut) 
		{
			  return;
		}
		if (evt.getSource () == _pinNoDirection) 
		{
			  return;
		}
		if (evt.getSource () == _pinAppearance) 
		{
			  if (_pinAppearance.getState()) 
			  {
				  _pinAppearanceList.setEnabled(true);
				  _pinAppearanceList.setBackground(Color.white);
			  }
			  else
			  {
				  _pinAppearanceList.setEnabled(false);
				  _pinAppearanceList.setBackground(this.getBackground());
			  }
			  return;
		}
		if (evt.getSource () == _pinAppearanceList) 
		{
			  return;
		}
	}
} 

