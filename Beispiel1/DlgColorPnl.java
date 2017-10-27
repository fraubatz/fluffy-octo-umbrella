import java.awt.*;
import java.awt.event.*;


class DlgColorPnl extends Panel implements ActionListener,
									       AdjustmentListener,
										   ItemListener
{

	private Label			_brightnessLabel;
	private Label			_defaultColors;
	private Label			_userColors;
	private Label			_preview;
	private Scrollbar		_brightness;
	private TextField		_tagText;
	private ColorWheel		_colorwheel;
	private Panel			_colorwheelPanel;
	private SampleDoc		_sampledoc;
	private Panel			_sampledocPanel; 
	private CheckboxGroup	_colorGroup;
	private Checkbox		_chooselineColor;
	private Checkbox		_choosefillColor;
	private Checkbox		_noFillColor;
	private ColorField		_defaultColorField[]		= new ColorField[13];
	private Panel			_defaultColorFieldPanel[]  = new Panel[13];
	private ColorField		_userColorField[]		= new ColorField[13];
	private Panel			_userColorFieldPanel[]  = new Panel[13];
	private ColorField		_currentLineColorField;
	private Panel			_currentLineColorFieldPanel;
	private ColorField		_currentFillColorField;
	private Panel			_currentFillColorFieldPanel;
	private Color			_currentLineColor;
	private Color			_currentFillColor;
	private Color			_savedColor;
	private Button			_buttonAdd;
	private Button			_buttonOK;
	private Button 			_buttonApply;
	private Button 			_buttonCancel;
	private Dialog			_dlg;
	private SmMainFrame		_mainFrame;

	private boolean			_colorState;


	public DlgColorPnl(Dialog dlg, SmMainFrame frame)
	{
		_dlg		= dlg;
		_mainFrame	= frame;
		setLayout (null);

		
		_brightnessLabel = new Label("Helligkeit 100");
		_brightnessLabel.setBounds(10, 163, 80, 21);
		add(_brightnessLabel);

		_defaultColors = new Label("Grundfarben :");
		_defaultColors.setBounds(300, 8, 80, 21);
		add(_defaultColors);

		_userColors = new Label("Benutzerdefinierte Farben :");
		_userColors.setBounds(300, 78, 130, 21);
		add(_userColors);

		_preview = new Label("Vorschau :");
		_preview.setBounds(180, 8, 100, 21);
		add(_preview);

		_brightness = new Scrollbar(Scrollbar.HORIZONTAL, 100, 10, 1, 100);
		_brightness.addAdjustmentListener(this);
		_brightness.setBounds(10, 185, 150, 21);
		add(_brightness);

		_tagText = new TextField();
		_tagText.addActionListener (this);
		_tagText.setBounds (10, 210, 150, 21);
		//add (_tagText);

		_sampledoc = new SampleDoc(_tagText);
		_sampledocPanel = new Panel();
		_sampledocPanel.setLayout(new GridLayout(1, 1));
		_sampledocPanel.add(_sampledoc);
		_sampledocPanel.setBounds(180,35, 100, 30);
		add(_sampledocPanel);

		_colorwheel = new ColorWheel(_sampledoc, _brightness, _brightnessLabel);
		_colorwheelPanel = new Panel();
		_colorwheelPanel.setLayout(new GridLayout(1, 1));
		_colorwheelPanel.add(_colorwheel);
		_colorwheelPanel.setBounds(0,0,150, 150);
		add(_colorwheelPanel);

		_colorGroup = new CheckboxGroup();

		_chooselineColor = new Checkbox ("Linienfarbe", _colorGroup, true);
		_chooselineColor.addItemListener (this);
		_chooselineColor.setBounds (180, 80, 80, 21);
		add (_chooselineColor);

		_choosefillColor = new Checkbox ("Füllfarbe", _colorGroup, false);
		_choosefillColor.addItemListener (this);
		_choosefillColor.setBounds (180, 110, 80, 21);
		add (_choosefillColor);

		_noFillColor = new Checkbox ("keine Füllfarbe", _colorGroup, false);
		_noFillColor.addItemListener (this);
		_noFillColor.setBounds (180, 140, 80, 21);
		add (_noFillColor);

		
		_defaultColorField[0] = new ColorField(this, false);
		_defaultColorField[0].setColor(Color.black);
		_defaultColorFieldPanel[0] = new Panel();
		_defaultColorFieldPanel[0].setLayout(new GridLayout(1, 1));
		_defaultColorFieldPanel[0].add(_defaultColorField[0]);
		_defaultColorFieldPanel[0].setBounds(300, 30, 20, 20);
		add(_defaultColorFieldPanel[0]);

		_defaultColorField[1] = new ColorField(this, false);
		_defaultColorField[1].setColor(Color.blue);
		_defaultColorFieldPanel[1] = new Panel();
		_defaultColorFieldPanel[1].setLayout(new GridLayout(1, 1));
		_defaultColorFieldPanel[1].add(_defaultColorField[1]);
		_defaultColorFieldPanel[1].setBounds(320, 30, 20, 20);
		add(_defaultColorFieldPanel[1]);

		_defaultColorField[2] = new ColorField(this, false);
		_defaultColorField[2].setColor(Color.cyan);
		_defaultColorFieldPanel[2] = new Panel();
		_defaultColorFieldPanel[2].setLayout(new GridLayout(1, 1));
		_defaultColorFieldPanel[2].add(_defaultColorField[2]);
		_defaultColorFieldPanel[2].setBounds(340, 30, 20, 20);
		add(_defaultColorFieldPanel[2]);

		_defaultColorField[3] = new ColorField(this, false);
		_defaultColorField[3].setColor(Color.darkGray);
		_defaultColorFieldPanel[3] = new Panel();
		_defaultColorFieldPanel[3].setLayout(new GridLayout(1, 1));
		_defaultColorFieldPanel[3].add(_defaultColorField[3]);
		_defaultColorFieldPanel[3].setBounds(360, 30, 20, 20);
		//add(_defaultColorFieldPanel[3]);

		_defaultColorField[4] = new ColorField(this, false);
		_defaultColorField[4].setColor(Color.gray);
		_defaultColorFieldPanel[4] = new Panel();
		_defaultColorFieldPanel[4].setLayout(new GridLayout(1, 1));
		_defaultColorFieldPanel[4].add(_defaultColorField[4]);
		_defaultColorFieldPanel[4].setBounds(380, 30, 20, 20);
		add(_defaultColorFieldPanel[4]);

		_defaultColorField[5] = new ColorField(this, false);
		_defaultColorField[5].setColor(Color.green);
		_defaultColorFieldPanel[5] = new Panel();
		_defaultColorFieldPanel[5].setLayout(new GridLayout(1, 1));
		_defaultColorFieldPanel[5].add(_defaultColorField[5]);
		_defaultColorFieldPanel[5].setBounds(400, 30, 20, 20);
		add(_defaultColorFieldPanel[5]);

		_defaultColorField[6] = new ColorField(this, false);
		_defaultColorField[6].setColor(Color.lightGray);
		_defaultColorFieldPanel[6] = new Panel();
		_defaultColorFieldPanel[6].setLayout(new GridLayout(1, 1));
		_defaultColorFieldPanel[6].add(_defaultColorField[3]);
		_defaultColorFieldPanel[6].setBounds(360, 30, 20, 20);
		add(_defaultColorFieldPanel[6]);

		_defaultColorField[7] = new ColorField(this, false);
		_defaultColorField[7].setColor(Color.magenta);
		_defaultColorFieldPanel[7] = new Panel();
		_defaultColorFieldPanel[7].setLayout(new GridLayout(1, 1));
		_defaultColorFieldPanel[7].add(_defaultColorField[7]);
		_defaultColorFieldPanel[7].setBounds(300, 50, 20, 20);
		add(_defaultColorFieldPanel[7]);

		_defaultColorField[8] = new ColorField(this, false);
		_defaultColorField[8].setColor(Color.orange);
		_defaultColorFieldPanel[8] = new Panel();
		_defaultColorFieldPanel[8].setLayout(new GridLayout(1, 1));
		_defaultColorFieldPanel[8].add(_defaultColorField[8]);
		_defaultColorFieldPanel[8].setBounds(320, 50, 20, 20);
		add(_defaultColorFieldPanel[8]);

		_defaultColorField[9] = new ColorField(this, false);
		_defaultColorField[9].setColor(Color.pink);
		_defaultColorFieldPanel[9] = new Panel();
		_defaultColorFieldPanel[9].setLayout(new GridLayout(1, 1));
		_defaultColorFieldPanel[9].add(_defaultColorField[9]);
		_defaultColorFieldPanel[9].setBounds(340, 50, 20, 20);
		add(_defaultColorFieldPanel[9]);

		_defaultColorField[10] = new ColorField(this, false);
		_defaultColorField[10].setColor(Color.red);
		_defaultColorFieldPanel[10] = new Panel();
		_defaultColorFieldPanel[10].setLayout(new GridLayout(1, 1));
		_defaultColorFieldPanel[10].add(_defaultColorField[10]);
		_defaultColorFieldPanel[10].setBounds(360, 50, 20, 20);
		add(_defaultColorFieldPanel[10]);

		_defaultColorField[11] = new ColorField(this, false);
		_defaultColorField[11].setColor(Color.white);
		_defaultColorFieldPanel[11] = new Panel();
		_defaultColorFieldPanel[11].setLayout(new GridLayout(1, 1));
		_defaultColorFieldPanel[11].add(_defaultColorField[11]);
		_defaultColorFieldPanel[11].setBounds(380, 50, 20, 20);
		add(_defaultColorFieldPanel[11]);

		_defaultColorField[12] = new ColorField(this, false);
		_defaultColorField[12].setColor(Color.yellow);
		_defaultColorFieldPanel[12] = new Panel();
		_defaultColorFieldPanel[12].setLayout(new GridLayout(1, 1));
		_defaultColorFieldPanel[12].add(_defaultColorField[12]);
		_defaultColorFieldPanel[12].setBounds(400, 50, 20, 20);
		add(_defaultColorFieldPanel[12]);

		_userColorField[0] = new ColorField(this, true);
		_userColorFieldPanel[0] = new Panel();
		_userColorFieldPanel[0].setLayout(new GridLayout(1, 1));
		_userColorFieldPanel[0].add(_userColorField[0]);
		_userColorFieldPanel[0].setBounds(300, 100, 20, 20);
		add(_userColorFieldPanel[0]);

		_userColorField[1] = new ColorField(this, true);
		_userColorFieldPanel[1] = new Panel();
		_userColorFieldPanel[1].setLayout(new GridLayout(1, 1));
		_userColorFieldPanel[1].add(_userColorField[1]);
		_userColorFieldPanel[1].setBounds(320, 100, 20, 20);
		add(_userColorFieldPanel[1]);

		_userColorField[2] = new ColorField(this, true);
		_userColorFieldPanel[2] = new Panel();
		_userColorFieldPanel[2].setLayout(new GridLayout(1, 1));
		_userColorFieldPanel[2].add(_userColorField[2]);
		_userColorFieldPanel[2].setBounds(340, 100, 20, 20);
		add(_userColorFieldPanel[2]);

		_userColorField[3] = new ColorField(this, true);
		_userColorFieldPanel[3] = new Panel();
		_userColorFieldPanel[3].setLayout(new GridLayout(1, 1));
		_userColorFieldPanel[3].add(_userColorField[3]);
		_userColorFieldPanel[3].setBounds(360, 100, 20, 20);
		//add(_userColorFieldPanel[3]);

		_userColorField[4] = new ColorField(this, true);
		_userColorFieldPanel[4] = new Panel();
		_userColorFieldPanel[4].setLayout(new GridLayout(1, 1));
		_userColorFieldPanel[4].add(_userColorField[4]);
		_userColorFieldPanel[4].setBounds(380, 100, 20, 20);
		add(_userColorFieldPanel[4]);

		_userColorField[5] = new ColorField(this, true);
		_userColorFieldPanel[5] = new Panel();
		_userColorFieldPanel[5].setLayout(new GridLayout(1, 1));
		_userColorFieldPanel[5].add(_userColorField[5]);
		_userColorFieldPanel[5].setBounds(400, 100, 20, 20);
		add(_userColorFieldPanel[5]);

		_userColorField[6] = new ColorField(this, true);
		_userColorFieldPanel[6] = new Panel();
		_userColorFieldPanel[6].setLayout(new GridLayout(1, 1));
		_userColorFieldPanel[6].add(_userColorField[3]);
		_userColorFieldPanel[6].setBounds(360, 100, 20, 20);
		add(_userColorFieldPanel[6]);

		_userColorField[7] = new ColorField(this, true);
		_userColorFieldPanel[7] = new Panel();
		_userColorFieldPanel[7].setLayout(new GridLayout(1, 1));
		_userColorFieldPanel[7].add(_userColorField[7]);
		_userColorFieldPanel[7].setBounds(300, 120, 20, 20);
		add(_userColorFieldPanel[7]);

		_userColorField[8] = new ColorField(this, true);
		_userColorFieldPanel[8] = new Panel();
		_userColorFieldPanel[8].setLayout(new GridLayout(1, 1));
		_userColorFieldPanel[8].add(_userColorField[8]);
		_userColorFieldPanel[8].setBounds(320, 120, 20, 20);
		add(_userColorFieldPanel[8]);

		_userColorField[9] = new ColorField(this, true);
		_userColorFieldPanel[9] = new Panel();
		_userColorFieldPanel[9].setLayout(new GridLayout(1, 1));
		_userColorFieldPanel[9].add(_userColorField[9]);
		_userColorFieldPanel[9].setBounds(340, 120, 20, 20);
		add(_userColorFieldPanel[9]);

		_userColorField[10] = new ColorField(this, true);
		_userColorFieldPanel[10] = new Panel();
		_userColorFieldPanel[10].setLayout(new GridLayout(1, 1));
		_userColorFieldPanel[10].add(_userColorField[10]);
		_userColorFieldPanel[10].setBounds(360, 120, 20, 20);
		add(_userColorFieldPanel[10]);

		_userColorField[11] = new ColorField(this, true);
		_userColorFieldPanel[11] = new Panel();
		_userColorFieldPanel[11].setLayout(new GridLayout(1, 1));
		_userColorFieldPanel[11].add(_userColorField[11]);
		_userColorFieldPanel[11].setBounds(380, 120, 20, 20);
		add(_userColorFieldPanel[11]);

		_userColorField[12] = new ColorField(this, true);
		_userColorFieldPanel[12] = new Panel();
		_userColorFieldPanel[12].setLayout(new GridLayout(1, 1));
		_userColorFieldPanel[12].add(_userColorField[12]);
		_userColorFieldPanel[12].setBounds(400, 120, 20, 20);
		add(_userColorFieldPanel[12]);

		_currentLineColorField = new ColorField();
		_currentLineColorFieldPanel = new Panel();
		_currentLineColorFieldPanel.setLayout(new GridLayout(1, 1));
		_currentLineColorFieldPanel.add(_currentLineColorField);
		_currentLineColorFieldPanel.setBounds(260, 80, 20, 20);
		add(_currentLineColorFieldPanel);

		_currentFillColorField = new ColorField();
		_currentFillColorFieldPanel = new Panel();
		_currentFillColorFieldPanel.setLayout(new GridLayout(1, 1));
		_currentFillColorFieldPanel.add(_currentFillColorField);
		_currentFillColorFieldPanel.setBounds(260, 110, 20, 20);
		add(_currentFillColorFieldPanel);


		
		_buttonAdd = new Button("Hinzufügen");
		_buttonAdd.addActionListener (this);
		_buttonAdd.setBounds (300, 150, 120, 21);
		add (_buttonAdd);

		_buttonOK = new Button("OK");
		_buttonOK.addActionListener (this);
		_buttonOK.setBounds (345, 180, 75, 21);
		add (_buttonOK);

		_buttonApply = new Button("Übernehmen");
		_buttonApply.addActionListener (this);
		_buttonApply.setBounds (245, 210, 75, 21);
		add (_buttonApply);

		_buttonCancel = new Button("Abbrechen");
		_buttonCancel.addActionListener (this);
		_buttonCancel.setBounds (345, 210, 75, 21);
		add (_buttonCancel);

		_colorState = true;
	}

	public Dimension getPreferredSize()
	{
		return new Dimension (435, 245);
	}

	public void setCurrentColor(Color color)
	{
		if(_colorState) _currentLineColor = color;
		else _currentFillColor = color;

		_currentLineColorField.setColor(_currentLineColor);
		_currentFillColorField.setColor(_currentFillColor);
		_currentLineColorField.repaint();
		_currentFillColorField.repaint();
	}

	/*public void paint(Graphics g)
	{
		
	}*/

	public void	actionPerformed(ActionEvent evt)
	{
		if (evt.getSource () == _buttonAdd)
		{
			for (int i=0; i<13 ; i++)
			{
				_savedColor = _sampledoc.getCurrentColor();
				if (_userColorField[i].getIsSelected())
				{
					_userColorField[i].setColor(_savedColor);
				}

			}
			return;
		}
		if (evt.getSource () == _buttonOK)
		{
			SmPanelCenter _center = _mainFrame.getCurrentCenter();
			SmWorkingArea _workingArea = _center.getWorkingArea();
			_workingArea.setAreaForeground(_currentLineColor);
			_workingArea.setAreaFillColor(_currentFillColor);
			_dlg.hide();
			return;
		}
		if (evt.getSource () == _buttonApply)
		{
			SmPanelCenter _center = _mainFrame.getCurrentCenter();
			SmWorkingArea _workingArea = _center.getWorkingArea();
			_workingArea.setAreaForeground(_currentLineColor);
			_workingArea.setAreaFillColor(_currentFillColor);
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
		if (evt.getSource () == _brightness)
		{
			  _brightnessLabel.setText("Brightness " + evt.getValue());
			  _colorwheel.setbrightness(evt.getValue());
			  
			  return;
		}
	}

	public void	itemStateChanged(ItemEvent evt)
	{
		if (evt.getSource () == _chooselineColor)
		{
			  _colorState = true;
			  return;
		}
		if (evt.getSource () == _choosefillColor)
		{
			  _colorState = false;
			  return;
		}
	}

} 