import java.awt.*;
import java.awt.event.*;


public class DlgGridPnl extends Panel implements ActionListener,
												 AdjustmentListener,
												 ItemListener
{
	private Checkbox 	_gridActive;
	private Checkbox	_gridCatch;
	private Choice		_gridColumn;
	private Choice		_gridRows;
	private TextField	_displayXZeroPoint;
	private TextField	_displayYZeroPoint;
	private Scrollbar	_modifyXZeroPoint;
	private Scrollbar	_modifyYZeroPoint;
	private Label		_labelColumn;
	private Label		_labelRows;
	private Label		_labelXZeroPoint;
	private Label		_labelYZeroPoint;
	private Button		_buttonApply;
	private Button		_buttonOK;
	private Button		_buttonCancel;

	private SmBorder	_borderDistance;
	private SmBorder	_borderZeroPoint;

	private Point		_zeroPoint;
	private Dialog		_dlg;
	private SmMainFrame	_mainFrame;

	private boolean		_gridOn;
	private boolean		_isCatched;

	public DlgGridPnl(Dialog dlg, SmMainFrame frame)
	{
		_dlg		= dlg;
		_mainFrame	= frame;
		_zeroPoint  = new Point(0,0);

		setLayout (null);

		_gridActive = new Checkbox ("Aktiv");
		_gridActive.addItemListener (this);
		_gridActive.setBounds (37, 35, 65, 23);
		add (_gridActive);

		_gridCatch = new Checkbox ("Fangen");
		_gridCatch.addItemListener (this);
		_gridCatch.setBounds (153, 35, 72, 23);
		add (_gridCatch);

		_gridColumn = new Choice ();
		_gridColumn.addItemListener (this);
		_gridColumn.addItem ("2");
		_gridColumn.addItem ("5");
		_gridColumn.addItem ("10");
		_gridColumn.addItem ("15");
		_gridColumn.addItem ("20");
		_gridColumn.addItem ("25");
		_gridColumn.addItem ("33");
		_gridColumn.addItem ("50");
		_gridColumn.addItem ("66");
		_gridColumn.addItem ("75");
		_gridColumn.addItem ("100");
		_gridColumn.setBounds (50, 93, 59, 21);
		add (_gridColumn);

		_gridRows = new Choice ();
		_gridRows.addItemListener (this);
		_gridRows.addItem ("2");
		_gridRows.addItem ("5");
		_gridRows.addItem ("10");
		_gridRows.addItem ("15");
		_gridRows.addItem ("20");
		_gridRows.addItem ("25");
		_gridRows.addItem ("33");
		_gridRows.addItem ("50");
		_gridRows.addItem ("66");
		_gridRows.addItem ("75");
		_gridRows.addItem ("100");
		_gridRows.setBounds (50, 121, 59, 21);
		add (_gridRows);

		_displayXZeroPoint = new TextField (String.valueOf(_zeroPoint.x));
		_displayXZeroPoint.addActionListener (this);
		_displayXZeroPoint.setBounds (198, 93, 41, 21);
		add (_displayXZeroPoint);

		_displayYZeroPoint = new TextField (String.valueOf(_zeroPoint.y));
		_displayYZeroPoint.addActionListener (this);
		_displayYZeroPoint.setBounds (198, 121, 41, 21);
		add (_displayYZeroPoint);

		_modifyXZeroPoint = new Scrollbar (Scrollbar.VERTICAL, 1000, 1, 0, 1000);
		_modifyXZeroPoint.addAdjustmentListener (this);
		_modifyXZeroPoint.setBounds (238, 93, 15, 21);
		add (_modifyXZeroPoint);

		_modifyYZeroPoint = new Scrollbar (Scrollbar.VERTICAL, 1000, 1,  0, 1000);
		_modifyYZeroPoint.addAdjustmentListener (this);
		_modifyYZeroPoint.setBounds (238, 121, 15, 21);
		add (_modifyYZeroPoint);

		_labelColumn = new Label ("X :");
		_labelColumn.setFont (new Font ("Dialog", Font.BOLD, 12));
		_labelColumn.setBounds (33, 93, 28, 23);
		add (_labelColumn);

		_labelRows = new Label ("Y :");
		_labelRows.setFont (new Font ("Dialog", Font.BOLD, 12));
		_labelRows.setBounds (33, 121, 27, 23);
		add (_labelRows);

		_labelXZeroPoint = new Label ("X :");
		_labelXZeroPoint.setFont (new Font ("Dialog", Font.BOLD, 12));
		_labelXZeroPoint.setBounds (181, 91, 17, 23);
		add (_labelXZeroPoint);

		_labelYZeroPoint = new Label ("Y :");
		_labelYZeroPoint.setFont (new Font ("Dialog", Font.BOLD, 12));
		_labelYZeroPoint.setBounds (181, 121, 17, 23);
		add (_labelYZeroPoint);

		_buttonApply = new Button ("Anwenden");
		_buttonApply.addActionListener (this);
		_buttonApply.setBounds (20, 180, 72, 21);
		add (_buttonApply);

		_buttonOK = new Button ("OK");
		_buttonOK.addActionListener (this);
		_buttonOK.setBounds (118, 180, 72, 21);
		add (_buttonOK);

		_buttonCancel = new Button ("Abbrechen");
		_buttonCancel.addActionListener (this);
		_buttonCancel.setBounds (216, 180, 72, 21);
		add (_buttonCancel);

		_borderDistance = new SmBorder(this, "Rasterabstand :", 20, 75, 120, 80);
		_borderZeroPoint = new SmBorder(this, "Nullpunkt :", 167, 75, 120, 80);
	}

	public void paint(Graphics g)
	{
		_borderDistance.paint(g);
		_borderZeroPoint.paint(g);
	}

	public Dimension getPreferredSize()
	{
		return new Dimension (305, 220);
	}

	public void	actionPerformed(ActionEvent evt)
	{
		if (evt.getSource () == _displayXZeroPoint)
		{
			  _modifyXZeroPoint.setValue(1000 - Integer.parseInt(_displayXZeroPoint.getText()));
			  return;
		}
		if (evt.getSource () == _displayYZeroPoint)
		{
			  _modifyYZeroPoint.setValue(1000 - Integer.parseInt(_displayXZeroPoint.getText()));
			  return;
		}
		if (evt.getSource () == _buttonApply)
		{
			  SmPanelCenter _center = _mainFrame.getCurrentCenter();
			  SmWorkingArea _workingArea = _center.getWorkingArea();
			  _workingArea.setGrid(Integer.parseInt(_gridColumn.getSelectedItem()),
								   Integer.parseInt(_gridRows.getSelectedItem())  ,
								   Integer.parseInt(_displayXZeroPoint.getText()) ,
								   Integer.parseInt(_displayXZeroPoint.getText()) ,
								   _gridOn										  ,
								   _isCatched
								  );
								  
			  return;
		}
		if (evt.getSource () == _buttonOK)
		{
			  SmPanelCenter _center = _mainFrame.getCurrentCenter();
			  SmWorkingArea _workingArea = _center.getWorkingArea();
			  _workingArea.setGrid(Integer.parseInt(_gridColumn.getSelectedItem()),
								   Integer.parseInt(_gridRows.getSelectedItem())  ,
								   Integer.parseInt(_displayXZeroPoint.getText()) ,
								   Integer.parseInt(_displayXZeroPoint.getText()) ,
								   _gridOn										  ,
								   _isCatched
								  );
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
		if (evt.getSource () == _modifyXZeroPoint)
		{
			  int value = _modifyXZeroPoint.getMaximum()-evt.getValue();
			  _displayXZeroPoint.setText(String.valueOf(value));
			  return;
		}
		if (evt.getSource () == _modifyYZeroPoint)
		{
			  int value = _modifyYZeroPoint.getMaximum()-evt.getValue();
			  _displayYZeroPoint.setText(String.valueOf(value));
			  return;
		}
	}

	public void	itemStateChanged(ItemEvent evt)
	{
		if (evt.getSource () == _gridActive)
		{
			  if (_gridOn)
			  {
				  _gridOn = false;
				  _gridActive.setState(false);
			  }
			  else
			  {
				  _gridOn = true;
				  _gridActive.setState(true);
			  }
			  return;
		}
		if (evt.getSource () == _gridCatch)
		{
			  if (_isCatched)
			  {
				  _isCatched = false;
				  _gridCatch.setState(false);
			  }
			  else 
			  {
				  _isCatched = true;
				  _gridCatch.setState(true);
			  }
			  return;
		}
		if (evt.getSource () == _gridRows)
		{
			  _gridRows.select(_gridRows.getSelectedItem());
			  return;
		}
		if (evt.getSource () == _gridColumn)
		{
			  _gridColumn.select(_gridColumn.getSelectedItem()); 
			  return;
		}
	}
} 