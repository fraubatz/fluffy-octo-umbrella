import java.awt.*;
import java.awt.event.*;


public class DlgNewFilePnl extends Panel implements ActionListener,
													AdjustmentListener,
													ItemListener 
{
	private Label		_labelName;
	private Label		_labelType;
	private List		_symbolList;
	private List		_structureList;
	private TextField	_fieldName;
	private TextField	_fieldType;
	private Button		_buttonNew;
	private Button		_buttonCancel;
	private Button      _buttonNext;
	private SmBorder	_borderSymbolList;
	private SmBorder	_borderStructureList;
	private Dialog		_dlg;
	private SmMainFrame	_mainFrame;
	private SmPanelCenter _center;
	private String		_chosenName;
	private String      _chosenType;

	public DlgNewFilePnl(Dialog dlg, SmMainFrame frame) 
	{
		_dlg		= dlg;
		_mainFrame	= frame;
		
		setLayout (null);

		_symbolList = new List ();
		_symbolList.add("Komponenten-Symbol");
		_symbolList.add("Pin-Symbol");
		_symbolList.add("Netz-Symbol");
		_symbolList.addActionListener (this);
		_symbolList.addItemListener (this);
		_symbolList.setBounds (40, 105, 129, 66);
		add (_symbolList);

		_structureList = new List ();
		_structureList.add("Komponenten-Typ");
		_structureList.add("Pin-Typ");
		_structureList.add("Netz-Typ");
		_structureList.addActionListener (this);
		_structureList.addItemListener (this);
		_structureList.setBounds (199, 105, 129, 66);
		add (_structureList);

		_fieldName = new TextField ("");
		_fieldName.addActionListener (this);
		_fieldName.setBounds (82, 19, 260, 21);
		add (_fieldName);
	
		_fieldType = new TextField ("");
		_fieldType.addActionListener (this);
		_fieldType.setBounds (82, 49, 260, 21);
		add (_fieldType);

		_labelName = new Label("Name :");
		_labelName.setBounds(40, 19, 40, 21);
		add(_labelName);

		_labelType = new Label("Typ :");
		_labelType.setBounds(40, 49, 40, 21);
		add(_labelType);

		_buttonNext = new Button("Weiter");
		_buttonNext.addActionListener(this);
		_buttonNext.setBounds(354, 85, 65, 21);
		_buttonNext.setEnabled(false);
		add(_buttonNext);

		_buttonNew = new Button ("Neu");
		_buttonNew.addActionListener (this);
		_buttonNew.setBounds (354, 120, 65, 21);
		add (_buttonNew);

		_buttonCancel = new Button ("Cancel");
		_buttonCancel.addActionListener (this);
		_buttonCancel.setBounds (354, 155, 65, 21);
		add (_buttonCancel);

		_borderSymbolList = new SmBorder(this, "Symbol", 33, 85, 143, 95);
		_borderStructureList = new SmBorder(this, "Struktur", 192, 85, 143, 95);

		_fieldType.setText(_symbolList.getItem(0));
		_symbolList.select(0);
	}

	public void paint(Graphics g)
	{
		_borderSymbolList.paint(g);
		_borderStructureList.paint(g);
	}

	public Dimension getPreferredSize() 
	{
		return new Dimension (430, 197);
	}

	public void	actionPerformed(ActionEvent evt) 
	{
		if (evt.getSource () == _symbolList)
		{
			return;
		}
		if (evt.getSource () == _structureList) 
		{
			return;
		}
		if (evt.getSource () == _fieldName) 
		{
	        return;
		}
		if (evt.getSource () == _buttonNext) 
		{
			DlgPinAttribute d = new DlgPinAttribute(_mainFrame, true);
			d.show();
	        return;
		}
		if (evt.getSource () == _buttonNew) 
		{
			_chosenName = _fieldName.getText();
			_chosenType = _fieldType.getText();
			SmStateBar.setNameOfComponent(_chosenName);
			SmStateBar.setTypeOfComponent(_chosenType);
			_mainFrame.setNameOfCurrentObject(_chosenName);
			_mainFrame.setNameOfCurrentType(_chosenType);
			_center = new SmPanelCenter(_mainFrame);
			_mainFrame.setCurrentCenter(_center);
			_center.createWorkingArea();
			_mainFrame.add("Center", _center);
			_mainFrame.show();
			SmMenuBar _menuBar = _mainFrame.getMenubar();
			SmToolBar _toolBar = _mainFrame.getToolBar();
			_menuBar.setMenuItemProperties(SmMenuBar.ID_SAVE, true, _mainFrame);
			_menuBar.setMenuItemProperties(SmMenuBar.ID_CLOSE, true, _mainFrame);
			_menuBar.setMenuItemProperties(SmMenuBar.ID_RECT, true, _center.getWorkingArea());
		    _menuBar.setMenuItemProperties(SmMenuBar.ID_ELLIPSE, true, _center.getWorkingArea());
			_menuBar.setMenuItemProperties(SmMenuBar.ID_LINE, true, _center.getWorkingArea());
			_menuBar.setMenuItemProperties(SmMenuBar.ID_TEXT_STATIC, true, _mainFrame);
			_menuBar.setMenuItemProperties(SmMenuBar.ID_CUT, true, _center.getWorkingArea());
		    _menuBar.setMenuItemProperties(SmMenuBar.ID_COPY, true, _center.getWorkingArea());
			_menuBar.setMenuItemProperties(SmMenuBar.ID_PASTE, true, _center.getWorkingArea());
			_menuBar.setMenuItemProperties(SmMenuBar.IDCANCEL, true, _center.getWorkingArea());
			_menuBar.setMenuItemProperties(SmMenuBar.ID_SELECT_ALL, true, _center.getWorkingArea());
			_menuBar.setMenuItemProperties(SmMenuBar.ID_GROUP, true, _center.getWorkingArea());
			_menuBar.setMenuItemProperties(SmMenuBar.ID_UNGROUP, true, _center.getWorkingArea());
			_toolBar.setToolbarItemProperties(SmToolBar.button4, true, _center.getWorkingArea());
			_toolBar.setToolbarItemProperties(SmToolBar.button5, true, _center.getWorkingArea());
			_toolBar.setToolbarItemProperties(SmToolBar.button6, true, _center.getWorkingArea());
			_toolBar.setToolbarItemProperties(SmToolBar.button7, true, _center.getWorkingArea());
			_toolBar.setToolbarItemProperties(SmToolBar.button8, true, _center.getWorkingArea());
			_toolBar.setToolbarItemProperties(SmToolBar.button9, true, _center.getWorkingArea());
			_toolBar.setToolbarItemProperties(SmToolBar.button10, true, _center.getWorkingArea());
			_toolBar.setToolbarItemProperties(SmToolBar.button11, true, _center.getWorkingArea());
			_toolBar.setToolbarItemProperties(SmToolBar.button14, true, _center.getWorkingArea());
			_toolBar.setToolbarItemProperties(SmToolBar.button15, true, _center.getWorkingArea());
			_toolBar.setToolbarItemProperties(SmToolBar.button17, true, _center.getWorkingArea());
			_toolBar.setToolbarItemProperties(SmToolBar.button18, true, _center.getWorkingArea());
			_toolBar.setToolbarItemProperties(SmToolBar.button19, true, _center.getWorkingArea());
			_toolBar.setToolbarItemProperties(SmToolBar.button23, true, _center.getWorkingArea());
						
			_dlg.dispose();
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
		if (evt.getSource () == _symbolList) 
		{
			_buttonNext.setEnabled(false);
			_buttonNew.setEnabled(true);
			_structureList.deselect(_structureList.getSelectedIndex());
			_fieldType.setText(_symbolList.getSelectedItem());	
			return;
		}
		if (evt.getSource () == _structureList) 
		{
			_buttonNext.setEnabled(true);
			_buttonNew.setEnabled(false);
			_symbolList.deselect(_symbolList.getSelectedIndex());
			_fieldType.setText(_structureList.getSelectedItem());	
			return;
		}
	}
} 
