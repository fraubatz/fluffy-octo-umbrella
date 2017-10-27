import java.awt.*;
import java.awt.event.*;


public class DlgZoomPnl	extends Panel implements ActionListener,
												 AdjustmentListener,
												 ItemListener
{
	private CheckboxGroup	_group;
	private Checkbox		_percent25;
	private Checkbox		_percent50;
	private Checkbox		_percent75;
	private Checkbox		_percent100;
	private Checkbox		_percent200;
	private Checkbox		_percent400;
	private Button			_buttonOK;
	private Button			_buttonCancel;
	private Dialog			_dlg;
	private SmMainFrame		_mainFrame;
	private SmBorder		_border;

	public DlgZoomPnl(Dialog dlg, SmMainFrame frame)
	{
		_dlg = dlg;
		_mainFrame = frame;

		setLayout (null);
		_group = new CheckboxGroup();

		_percent25 = new Checkbox ("25 %", _group, false);
		_percent25.addItemListener (this);
		_percent25.setBounds (40, 40, 58, 21);
		add (_percent25);

		_percent50 = new Checkbox ("50 %", _group, false);
		_percent50.addItemListener (this);
		_percent50.setBounds (40, 70, 58, 21);
		add (_percent50);

		_percent75 = new Checkbox ("75 %", _group, false);
		_percent75.addItemListener (this);
		_percent75.setBounds (40, 100, 58, 21);
		add (_percent75);

		_percent100 = new Checkbox ("100 %", _group, false);
		_percent100.addItemListener (this);
		_percent100.setBounds (40, 130, 59, 21);
		add (_percent100);

		_percent200 = new Checkbox ("200 %", _group, false);
		_percent200.addItemListener (this);
		_percent200.setBounds (40, 160, 59, 21);
		add (_percent200);

		_percent400 = new Checkbox ("400 %", _group, false);
		_percent400.addItemListener (this);
		_percent400.setBounds (40, 190, 59, 21);
		add (_percent400);

		_buttonOK = new Button ("OK");
		_buttonOK.addActionListener (this);
		_buttonOK.setBounds (20, 245, 65, 21);
		add (_buttonOK);

		_buttonCancel = new Button ("Abbbrechen");
		_buttonCancel.addActionListener (this);
		_buttonCancel.setBounds (115, 245, 65, 21);
		add (_buttonCancel);

		_border = new SmBorder(this, "Zoomfaktor :", 20, 20, 160, 200);
	}

	public void paint(Graphics g)
	{
		_border.paint(g);
	}

	public Dimension getPreferredSize() 
	{
		return new Dimension (201, 286);
	}

	public void	actionPerformed(ActionEvent evt)
	{
		if (evt.getSource () == _buttonOK)
		{
			  
			  return;
		}
		if (evt.getSource () == _buttonCancel) 
		{
			  _dlg.dispose();
			  return;
		}
	}

	public void	adjustmentValueChanged(AdjustmentEvent evt)
	{

	}

	public void	itemStateChanged(ItemEvent evt)
	{
		if (evt.getSource () == _percent25)
		{
			  return;
		}
		if (evt.getSource () == _percent50)
		{
			  return;
		}
		if (evt.getSource () == _percent75)
		{
			  return;
		}
		if (evt.getSource () == _percent100)
		{
			  return;
		}
		if (evt.getSource () == _percent200)
		{
			 return;
		}
		if (evt.getSource () == _percent400)
		{
			 return;
		}
	}
} 
