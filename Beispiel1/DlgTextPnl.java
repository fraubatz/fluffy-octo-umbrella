import java.awt.*;
import java.awt.event.*;


public class DlgTextPnl	extends Panel implements ActionListener,
												 AdjustmentListener,
												 ItemListener
{
	private List			_fontList;
	private List			_styleList;
	private List			_sizeList;
	private CheckboxGroup	_orientation;
	private Checkbox		_chooseLeft;
	private Checkbox		_chooseRight;
	private Checkbox		_chooseCenter;
	private Checkbox		_chooseBlock;
	private Button			_buttonOK;
	private Button			_buttonApply;
	private Button			_buttonCancel;
	private SmBorder		_borderOrientation;
	private SmBorder		_borderPreview;
	private TextPreview		_textPreview;
	private ScrollPane		_textScrollPane;
	private Panel			_textPanel;
	private Dialog			_dlg;
	private SmMainFrame		_mainFrame;
	private String			_fontName;
	private int				_fontStyle;
	private int				_fontSize;
	
	public DlgTextPnl(Dialog dlg, SmMainFrame frame)
	{
		_dlg		= dlg;
		_mainFrame	= frame;
		setLayout(null);

		_fontList = new List ();
		_fontList.add("Serif");
		_fontList.add("SansSerif");
		_fontList.add("Monospaced");
		_fontList.add("Dialog");
		_fontList.add("DialogInput");
		_fontList.addActionListener (this);
		_fontList.addItemListener (this);

		_fontList.setBounds (15, 30, 110, 56);
		add(_fontList);

		_styleList = new List ();
		_styleList.add("Bold");
		_styleList.add("Italic");
		_styleList.add("Plain");
		_styleList.addActionListener (this);
		_styleList.addItemListener (this);

		_styleList.setBounds (151, 30, 68, 56);
		add(_styleList);

		_sizeList = new List ();
		_sizeList.add("8");
		_sizeList.add("10");
		_sizeList.add("11");
		_sizeList.add("12");
		_sizeList.add("14");
		_sizeList.add("16");
		_sizeList.add("18");
		_sizeList.add("20");
		_sizeList.add("22");
		_sizeList.add("24");
		_sizeList.add("26");
		_sizeList.add("28");
		_sizeList.add("36");
		_sizeList.add("48");
		_sizeList.add("72");
		_sizeList.addActionListener (this);
		_sizeList.addItemListener (this);

		_sizeList.setBounds (236, 30, 39, 56);
		add(_sizeList);

		_orientation = new CheckboxGroup();

		_chooseLeft = new Checkbox("linksbündig", _orientation, true);
		_chooseLeft.addItemListener (this);
		_chooseLeft.setBounds (35, 133, 83, 21);
		add(_chooseLeft);

		_chooseRight = new Checkbox("rechtsbündig", _orientation, false);
		_chooseRight.addItemListener (this);
		_chooseRight.setBounds (35, 153, 91, 21);
		add(_chooseRight);

		_chooseCenter = new Checkbox("zentriert", _orientation, false);
		_chooseCenter.addItemListener (this);
		_chooseCenter.setBounds (35, 173, 67, 21);
		add(_chooseCenter);

		_chooseBlock = new Checkbox("block", _orientation, false);
		_chooseBlock.addItemListener (this);
		_chooseBlock.setBounds (35, 193, 61, 21);
		add(_chooseBlock);

		_buttonOK = new Button("OK");
		_buttonOK.addActionListener (this);
		_buttonOK.setBounds (20, 250, 72, 21);
		add(_buttonOK);

		_buttonApply = new Button("Übernehmen");
		_buttonApply.addActionListener (this);
		_buttonApply.setBounds (113, 250, 75, 21);
		add(_buttonApply);

		_buttonCancel = new Button("Abbrechen");
		_buttonCancel.addActionListener (this);
		_buttonCancel.setBounds (209, 250, 72, 21);
		add(_buttonCancel);

		_textPreview = new TextPreview();
		_textPreview.setSize(120,84);

		_textScrollPane = new ScrollPane(ScrollPane.SCROLLBARS_NEVER);
		_textScrollPane.add(_textPreview);
		_textScrollPane.setSize(119, 83);

		_textPanel = new Panel();
		_textPanel.setBounds(150, 133, 120, 84);
		_textPanel.add(_textScrollPane);

		add(_textPanel);
	}

	public Dimension getPreferredSize()
	{
		return new Dimension (300, 291);
	}

	public void paint(Graphics g)
	{
		_textPreview.paint(g);
	}

	public void	actionPerformed(ActionEvent evt)
	{
		if (evt.getSource () == _fontList)
		{
			  return;
		}
		if (evt.getSource () == _styleList)
		{
			  return;
		}
		if (evt.getSource () == _sizeList)
		{
			  return;
		}
		if (evt.getSource () == _buttonOK)
		{
			SmPanelCenter _center = _mainFrame.getCurrentCenter();
			SmWorkingArea _workingArea = _center.getWorkingArea();
			_workingArea.setFont(new Font(_fontName, _fontStyle, _fontSize));
			 return;
		}
		if (evt.getSource () == _buttonApply)
		{
			SmPanelCenter _center = _mainFrame.getCurrentCenter();
			SmWorkingArea _workingArea = _center.getWorkingArea();
			_workingArea.setFont(new Font(_fontName, _fontStyle, _fontSize));
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
		if (evt.getSource () == _fontList)
		{
			  _fontName = _fontList.getSelectedItem();
			  
		}
		if (evt.getSource () == _styleList)
		{
			  _fontStyle = Integer.parseInt(_styleList.getSelectedItem());
			  
		}
		if (evt.getSource () == _sizeList)
		{
			  _fontSize = Integer.parseInt(_sizeList.getSelectedItem());
			  
		}
		if (evt.getSource () == _chooseLeft)
		{
			  return;
		}
		if (evt.getSource () == _chooseRight)
		{
			  return;
		}
		if (evt.getSource () == _chooseCenter)
		{
			  return;
		}
		if (evt.getSource () == _chooseBlock)
		{
			  return;
		}
		_textPreview.setText(_fontName, _fontStyle, _fontSize);
	}
} 