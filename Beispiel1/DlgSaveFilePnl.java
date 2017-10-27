import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class DlgSaveFilePnl extends Panel implements ActionListener,
													 AdjustmentListener,
												     ItemListener
{
	private Button			_buttonOK;
	private Button  		_buttonCancel;
	private CheckboxGroup	_group1;
	private Checkbox 		_library;
	private Checkbox 		_folder;
	private SmBorder		_border;
	private Dialog			_dlg;
	private SmMainFrame		_mainFrame;
	private boolean			_isLibrary;

	private	String _defaultDir = System.getProperty("user.dir");

	public DlgSaveFilePnl(Dialog dlg, SmMainFrame frame) 
	{
		_dlg = dlg;
		_mainFrame = frame;
		_isLibrary = true;

		setLayout (null);
		_group1 = new CheckboxGroup();

		_buttonOK = new Button ("OK");
		_buttonOK.addActionListener (this);
		_buttonOK.setBounds (20, 135, 65, 21);
		add (_buttonOK);

		_buttonCancel = new Button ("Cancel");
		_buttonCancel.addActionListener (this);
		_buttonCancel.setBounds (110, 135, 65, 21);
		add (_buttonCancel);

		_library = new Checkbox ("Bibliothek", _group1, true);
		_library.addItemListener (this);
		_library.setBounds (50, 49, 84, 23);
		add (_library);

		_folder = new Checkbox ("Verzeichnis", _group1, false);
		_folder.addItemListener (this);
		_folder.setBounds (50, 82, 93, 23);
		add (_folder);

		_border = new SmBorder(this,"Speichern in :",35, 30, 120, 85);
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
				  //Präsentant Speichern
				  //Symbol     Speichern
				  SmPanelCenter _currentCenter = _mainFrame.getCurrentCenter();
				  SmWorkingArea _workingArea = _currentCenter.getWorkingArea();
				  _workingArea.copyGraphicListsToAppearance();
				  try
				  {
					  FileOutputStream fos = new FileOutputStream("a:/appearance");
					  OutputStreamWriter out = new OutputStreamWriter(fos);
					  String appearanceString =  _workingArea.getAppearance().toString();

					  out.write(appearanceString,0,appearanceString.length());
				      out.flush();
					  out.close();
				  }
				  catch (IOException ex)
				  {
					  System.out.println(ex);
				  }
				  return;
			  }
			  else
				  //nur Symbol Speichern
			  {
				  FileDialog _dlg = new FileDialog(_mainFrame, "Speichern", FileDialog.SAVE);
				  _dlg.show();
				  String _fileName = _dlg.getFile();
				  if (_fileName != null)
				  {
					  try
					  {
						  FileOutputStream _fos = new FileOutputStream(_dlg.getDirectory()+_fileName);
						  ObjectOutputStream _out = new ObjectOutputStream(_fos);
						  SmPanelCenter _currentCenter = _mainFrame.getCurrentCenter();
						  _out.writeObject(_currentCenter.getGraphicList());
						  _out.flush();
						  _out.close();
					  }
					  catch (IOException ex)
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