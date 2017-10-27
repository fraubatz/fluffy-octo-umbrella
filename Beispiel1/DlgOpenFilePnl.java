import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class DlgOpenFilePnl extends Panel implements ActionListener,
													 AdjustmentListener,
												     ItemListener
{
	private Button			_buttonOK;
	private Button  		_buttonCancel;
	private CheckboxGroup	_group;
	private Checkbox 		_library;
	private Checkbox 		_folder;
	private SmBorder		_border;
	private Dialog			_dlg;
	private SmMainFrame		_mainFrame;
	private boolean			_isLibrary;

	private	String _defaultDir = System.getProperty("user.dir");

	public DlgOpenFilePnl(Dialog dlg, SmMainFrame frame) 
	{
		_dlg = dlg;
		_mainFrame = frame;
		_isLibrary = true;

		setLayout (null);
		_group = new CheckboxGroup();

		_buttonOK = new Button ("OK");
		_buttonOK.addActionListener (this);
		_buttonOK.setBounds (20, 135, 65, 21);
		add (_buttonOK);

		_buttonCancel = new Button ("Cancel");
		_buttonCancel.addActionListener (this);
		_buttonCancel.setBounds (110, 135, 65, 21);
		add (_buttonCancel);

		_library = new Checkbox ("Bibliothek", _group, true);
		_library.addItemListener (this);
		_library.setBounds (50, 49, 84, 23);
		add (_library);

		_folder = new Checkbox ("Verzeichnis", _group, false);
		_folder.addItemListener (this);
		_folder.setBounds (50, 82, 93, 23);
		add (_folder);

		_border = new SmBorder(this,"Laden aus :",35, 30, 120, 85);
	}

	public void paint(Graphics g)
	{
		_border.paint(g);
	}

	public Dimension getPreferredSize() 
	{
		return new Dimension (195, 175);
	}

	public void	actionPerformed(ActionEvent evt)
	{
		if (evt.getSource () == _buttonOK) 
		{
			  if (_isLibrary)
			  {
				  //Präsentant Laden
				  return;
			  }
			  else
			  {
				 FileDialog _fileDlg = new FileDialog(_mainFrame, "Laden", FileDialog.LOAD);
				 _fileDlg.setDirectory(_defaultDir);
				 _fileDlg.show();
				 
				 String _fileName = _fileDlg.getFile();
				 if (_fileName != null)
				 {
				   try
				   {
			   		  FileInputStream _fis = new FileInputStream(_fileDlg.getDirectory()+_fileName);
					  ObjectInputStream _in = new ObjectInputStream(_fis);
					  SmEladoObjectList _readBuffer = new SmEladoObjectList();
				      _readBuffer = (SmEladoObjectList) _in.readObject();
				      _in.close();
					  				  
				      if (_mainFrame.getCurrentCenter() != null)
					  {
				 		_mainFrame.remove(4);
					  }
				      SmPanelCenter _center = new SmPanelCenter(_mainFrame);
					  _mainFrame.setCurrentCenter(_center);
					  _center.createWorkingArea();
					  _center.setGraphicList(_readBuffer);
					  _mainFrame.add(_center);
					  _mainFrame.show();
					  SmMenuBar _menuBar = _mainFrame.getMenubar();
					  SmToolBar _toolBar = _mainFrame.getToolBar();
					  _menuBar.setMenuItemProperties(SmMenuBar.ID_SAVE, true, _mainFrame);
					  _menuBar.setMenuItemProperties(SmMenuBar.ID_CLOSE, true, _mainFrame);

					  _menuBar.setMenuItemProperties(SmMenuBar.ID_RECT, true, _center.getWorkingArea());
					  _menuBar.setMenuItemProperties(SmMenuBar.ID_ELLIPSE, true, _center.getWorkingArea());
					  _menuBar.setMenuItemProperties(SmMenuBar.ID_LINE, true, _center.getWorkingArea());
					  _menuBar.setMenuItemProperties(SmMenuBar.ID_CUT, true, _center.getWorkingArea());
					  _menuBar.setMenuItemProperties(SmMenuBar.ID_COPY, true, _center.getWorkingArea());
					  _menuBar.setMenuItemProperties(SmMenuBar.ID_PASTE, true, _center.getWorkingArea());
					  _menuBar.setMenuItemProperties(SmMenuBar.IDCANCEL, true, _center.getWorkingArea());
					  _menuBar.setMenuItemProperties(SmMenuBar.ID_SELECT_ALL, true, _center.getWorkingArea());
					  _menuBar.setMenuItemProperties(SmMenuBar.ID_GROUP, true, _center.getWorkingArea());
					  _menuBar.setMenuItemProperties(SmMenuBar.ID_UNGROUP, true, _center.getWorkingArea());
					  _toolBar.setToolbarItemProperties(SmToolBar.button8, true, _center.getWorkingArea());
					  _toolBar.setToolbarItemProperties(SmToolBar.button9, true, _center.getWorkingArea());
					  _toolBar.setToolbarItemProperties(SmToolBar.button10, true, _center.getWorkingArea());
					  _toolBar.setToolbarItemProperties(SmToolBar.button11, true, _center.getWorkingArea());
				      _toolBar.setToolbarItemProperties(SmToolBar.button14, true, _center.getWorkingArea());
					  _toolBar.setToolbarItemProperties(SmToolBar.button17, true, _center.getWorkingArea());
					  _toolBar.setToolbarItemProperties(SmToolBar.button18, true, _center.getWorkingArea());
					  _toolBar.setToolbarItemProperties(SmToolBar.button19, true, _center.getWorkingArea());
					  _toolBar.setToolbarItemProperties(SmToolBar.button23, true, _center.getWorkingArea());
					  
				   }
				   catch (Exception ex)
				   {
			   		 System.out.println(ex);
				   }
				 }
			  }
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
		if (evt.getSource () == _library) 
		{
			  _isLibrary = true;
			  return;
		}
		if (evt.getSource () == _folder) 
		{
			  _isLibrary = false;
			  return;
		}
	}
} 