import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.zip.*;

import javax.swing.JPanel;
import javax.swing.JComponent;




// ino.class.SmMainFrame.463.description type=line
// Die Klasse SmMainFrame ist das Hauptfenster des Symboleditors. In dem           
// Hauptfenster sind 5 Panels und eine Menuleiste integriert. Die Panels sind nach 
// dem Borderlayout angeordnet(siehe auch java.awt.BorderLayout).
// Die 5 Panels sind : ToolBar, Statuszeile, Koordinatenanzeige, linker Rand und   
// das Zentrum, welches zur Aufnahme des Zeichenfensters dient.
// Die Klasse SmMainFrame implementiert das ActionListener Interface und ist somit 
// berechtigt Ereignisse von anderen GUI-Komponenten zu Empfangen. Es werden hier  
// ankommende Ereignisse von der Menu -und Werkzeugleiste verarbeitet, die einen   
// Aufruf eines Dialogs zur Folge haben.
// ino.end
// ino.class.SmMainFrame.463.declaration 
public class SmMainFrame extends Frame implements ActionListener
// ino.end
// ino.class.SmMainFrame.463.body
{
	

	// ino.attribute._actionCommand.2031.description type=line
	// Dieses Attribut empfängt ein ankommendes Ereignis von der Menu -oder            
	// Werkzeugleiste, welches als String übergeben wird.
	// ino.end
	// ino.attribute._actionCommand.2031.declaration 
	private String _actionCommand;
	// ino.end

	// ino.attribute._nameOfCurrentObject.2034.description type=line
	// Dieses Attribut hält den Namen eines im Symboleditor erstellten Objekts.
	// ino.end
	// ino.attribute._nameOfCurrentObject.2034.declaration 
	private String _nameOfCurrentObject;
	// ino.end

	// ino.attribute._nameOfCurrentType.2037.description type=line
	// Dieses Attribut hält den Namen eines im Symboleditors erstellten Typs. Typen    
	// können sein : Komponenten-Symbol, Pin-Symbol, Netz-Symbol, Komponenten-Typ,     
	// Pin-Typ oder Netz-Typ.
	// ino.end
	// ino.attribute._nameOfCurrentType.2037.declaration 
	private String _nameOfCurrentType;
	// ino.end


	// ino.attribute._currentCenter.2045.description type=line
	// Dieses Attribut erhält das aktuelle Panel, welches zur Aufnahme eines           
	// Zeichenfensters dient.
	// ino.end
	// ino.attribute._currentCenter.2045.declaration 
	private SmPanelCenter _currentCenter;
	// ino.end

	// ino.attribute._menu.3603.description type=line
	// Dieses Attribut repräsentiert die Menüleiste.
	// ino.end
	// ino.attribute._menu.3603.declaration 
	private SmMenuBar _menu;
	// ino.end

	// ino.attribute._toolBar.3606.description type=line
	// Dieses Attribut repräsentiert die Werkzeugleiste.
	// ino.end
	// ino.attribute._toolBar.3606.declaration 
	private SmToolBar _toolBar;
	// ino.end

	// ino.attribute._dlgGrid.2058.description type=line
	// Dieses Attribut hält das Raster-Dialogfenster und dessen Informationen, die     
	// auch nach dem Schließen des Dialogs nicht verloren gehen dürfen.
	// ino.end
	// ino.attribute._dlgGrid.2058.declaration 
	private DlgGrid _dlgGrid;
	// ino.end

	// ino.attribute._dlgLayer.3634.description type=line
	// Dieses Attribut hält das Layer-Dialogfenster und dessen Informationen, die auch 
	// nach dem Schließen des Dialogs nicht verloren gehen dürfen.
	// ino.end
	// ino.attribute._dlgLayer.3634.declaration 
	private DlgLayer _dlgLayer;
	// ino.end

	// ino.attribute._dlgColor.3639.description type=line
	// Dieses Attribut hält das Farbattribute-Dialogfenster und dessen Informationen,  
	// die auch nach dem Schließen des Dialogs nicht verloren gehen dürfen.
	// ino.end
	// ino.attribute._dlgColor.3639.declaration 
	private DlgColor _dlgColor;
	// ino.end
	
	// ino.attribute._dlgText.3644.description type=line
	// Dieses Attribut hält das Textattribute-Dialogfenster und dessen Informationen,  
	// die auch nach dem Schließen des Dialogs nicht verloren gehen dürfen.
	// ino.end
	// ino.attribute._dlgText.3644.declaration 
	private DlgText _dlgText;
	// ino.end
	
	// ino.method.SmMainFrame.731.description type=line
	// Der Standardkonstruktor initialisiert die Attribute _nameOfCurrentObject,       
	// _nameOfCurrentType und _actionCommand mit null. Die Menuleiste, Werkzeugleiste  
	// und die 5 Panels werden erzeugt und in das Hauptfenster integriert. 
	// ino.end
	// ino.method.SmMainFrame.731.definition 
	public SmMainFrame(String str)
	// ino.end
	// ino.method.SmMainFrame.731.body 
	{
		super (str);

		_nameOfCurrentObject = null;
		_nameOfCurrentType   = null;
		_currentCenter		 = new SmPanelCenter(this);
		_currentCenter		 = null;
		this.setLayout(new BorderLayout());
		
		_menu = new SmMenuBar(this);
		_menu.CreateMenu();
		
		SmXYDisplay west   = new SmXYDisplay();
		SmPanelEast east   = new SmPanelEast();
		_toolBar			   = new SmToolBar(this);
		SmStateBar south   = new SmStateBar();
			
		this.setBackground(Color.gray);
			
		this.add ("North",_toolBar);
		this.add ("South",south);
		this.add ("West",west);
		this.add ("East",east);

		this.enableEvents(AWTEvent.WINDOW_EVENT_MASK);

		init();
	}
	// ino.end
	
	// ino.method.setNameOfCurrentObject.2061.description type=line
	// Diese Funktion setzt den Namen des aktuell zu erstellenden Objekts.
	// ino.end
	// ino.method.setNameOfCurrentObject.2061.definition 
	public void setNameOfCurrentObject(String objectName)
	// ino.end
	// ino.method.setNameOfCurrentObject.2061.body 
	{
		_nameOfCurrentObject = objectName;
	}
	// ino.end

	// ino.method.setNameOfCurrentType.2064.description type=line
	// Diese Funktion setzt den Namen des Typs des aktuell zu erstellenden Objekts.
	// ino.end
	// ino.method.setNameOfCurrentType.2064.definition 
	public void setNameOfCurrentType(String objectType)
	// ino.end
	// ino.method.setNameOfCurrentType.2064.body 
	{
		_nameOfCurrentType = objectType;
	}
	// ino.end

	// ino.method.getNameOfCurrentObject.2067.description type=line
	// Diese Funktion liefert den Namen des aktuell zu erstellenden Objekts zurück.
	// ino.end
	// ino.method.getNameOfCurrentObject.2067.definition 
	public String getNameOfCurrentObject()
	// ino.end
	// ino.method.getNameOfCurrentObject.2067.body 
	{
		return _nameOfCurrentObject;
	}
	// ino.end
	
	// ino.method.getNameOfCurrentType.2070.description type=line
	// Diese Funktion liefert den Namen des Typs des aktuell zu erstellenden Objekts   
	// zurück.
	// ino.end
	// ino.method.getNameOfCurrentType.2070.definition 
	public String getNameOfCurrentType()
	// ino.end
	// ino.method.getNameOfCurrentType.2070.body 
	{
		return _nameOfCurrentType;
	}
	// ino.end
	
	// ino.method.setCurrentCenter.2073.description type=line
	// Diese Funktion setzt den aktuellen Panel, welcher zur Aufnahme des              
	// Zeichenfensters dient.
	// ino.end
	// ino.method.setCurrentCenter.2073.definition 
	public void setCurrentCenter(SmPanelCenter center)
	// ino.end
	// ino.method.setCurrentCenter.2073.body 
	{
		_currentCenter = center;
	}
	// ino.end

	// ino.method.getCurrentCenter.2076.description type=line
	// Diese Funktion liefert eine Referenz auf den aktuellen Panel des                
	// Zeichenfensters zurück.
	// ino.end
	// ino.method.getCurrentCenter.2076.definition 
	public SmPanelCenter getCurrentCenter()
	// ino.end
	// ino.method.getCurrentCenter.2076.body 
	{
		return _currentCenter;
	}
	// ino.end

	// ino.method.getMenubar.2079.description type=line
	// Diese Funktion liefert eine Referenz auf die Menüleiste zurück.
	// ino.end
	// ino.method.getMenubar.2079.definition 
	public SmMenuBar getMenubar()
	// ino.end
	// ino.method.getMenubar.2079.body 
	{
		return _menu;
	}
	// ino.end

	// ino.method.getToolBar.2082.description type=line
	// Diese Funktion liefert eine Referenz auf die Werkzeugleiste zurück.
	// ino.end
	// ino.method.getToolBar.2082.definition 
	public SmToolBar getToolBar()
	// ino.end
	// ino.method.getToolBar.2082.body 
	{
		return _toolBar;
	}
	// ino.end

	// ino.method.init.2085.description type=line
	// Diese Funktion initialisiert die Menü -und Werkzeugleiste beim Start des        
	// Symboleditors.
	// ino.end
	// ino.method.init.2085.definition 
	public void init()
	// ino.end
	// ino.method.init.2085.body 
	{
		SmXYDisplay.setX(0);
		SmXYDisplay.setY(0);
		_nameOfCurrentObject = null;
		_nameOfCurrentType   = null;
		_currentCenter		 = null;

		_menu.setMenuItemProperties(SmMenuBar.ID_SAVE, false, this);
		_menu.setMenuItemProperties(SmMenuBar.ID_CLOSE, false, this);
		_menu.setMenuItemProperties(SmMenuBar.ID_RECT, false, null);
	    _menu.setMenuItemProperties(SmMenuBar.ID_ELLIPSE, false, null);
		_menu.setMenuItemProperties(SmMenuBar.ID_LINE, false, null);
		_menu.setMenuItemProperties(SmMenuBar.ID_CUT, false, null);
		_menu.setMenuItemProperties(SmMenuBar.ID_COPY, false, null);
		_menu.setMenuItemProperties(SmMenuBar.ID_PASTE, false, null);
		_menu.setMenuItemProperties(SmMenuBar.IDCANCEL, false, null);
		_toolBar.setToolbarItemProperties(SmToolBar.button15, false, null);
		_toolBar.setToolbarItemProperties(SmToolBar.button16, false, null);
		_toolBar.setToolbarItemProperties(SmToolBar.button17, false, null);
		_toolBar.setToolbarItemProperties(SmToolBar.button21, false, null);
	}
	// ino.end

	// ino.method.actionPerformed.2088.description type=line
	// Diese Funktion verarbeitet die ankommenden Ereignisse von Menüleiste und        
	// Werkzeugleiste.
	// ino.end
	// ino.method.actionPerformed.2088.definition 
	public void actionPerformed(ActionEvent e)
	// ino.end
	// ino.method.actionPerformed.2088.body 
	{
		String command = e.getActionCommand();
		if (command.equals("new"))
		{
			DlgNewFile _dlg = new DlgNewFile(this, true);
			_dlg.show();
			return;
		}
		if (command.equals("close"))
		{
			remove(4);
			init();
			return;
		}
		if (command.equals("quit"))
		{
			dispose();
			System.exit(0);
			return;
		}
		if (command.equals("image"))
		{
			FileDialog _dlg = new FileDialog(this, "Laden", FileDialog.LOAD);
			_dlg.show();

			return;
		}
		if (command.equals("open"))
		{
			DlgOpenFile _dlg = new DlgOpenFile(this, true);
			_dlg.show();
			this.invalidate();
			return;
		}
		if (command.equals("save") & _nameOfCurrentObject != null & _nameOfCurrentType != null)
		{

			if ((_nameOfCurrentType.equals("Komponenten-Symbol")) ||
				(_nameOfCurrentType.equals("Pin-Symbol"))		  ||
				(_nameOfCurrentType.equals("Netz-Symbol")))
			{
				DlgSaveFile _dlg = new DlgSaveFile(this, true);
				_dlg.show();
				return;
			}
			else
			{
				
			}
					
		}

		if (command.equals("grid"))
		{
			if (_dlgGrid == null)
			{
				_dlgGrid = new DlgGrid(this, true);
				
			}
			_dlgGrid.show();
			this.invalidate();
			return;
		}

		if (command.equals("layer"))
		{
			if (_dlgLayer == null)
			{
				_dlgLayer = new DlgLayer(this, true);
			}
			_dlgLayer.show();
			this.invalidate();
			return;
		}

		if (command.equals("zoom"))
		{
			DlgZoom _dlg = new DlgZoom(this, true);
			_dlg.show();
			this.invalidate();
			return;
		}

		if (command.equals("colorfill"))
		{
			if (_dlgColor == null)
			{
				_dlgColor = new DlgColor(this, false);
			}
			_dlgColor.show();
			this.invalidate();
			return;
		}

		if (command.equals("textopt"))
		{
			if (_dlgText == null)
			{
				_dlgText = new DlgText(this, false);
			}
			_dlgText.show();
			this.invalidate();
			return;
		}
		if (command.equals("textstatic"))
		{
			DlgTextInput _dlg = new DlgTextInput(this, true);
			_dlg.show();
			return;
		}
	}	
	// ino.end

	public void	processEvent(AWTEvent evt)
	{
		if (evt.getID () == WindowEvent.WINDOW_CLOSING)
		{
			  this.dispose();
			  System.exit(0);
			  return;
		}
		super.processEvent (evt);
	}

}
// ino.end


