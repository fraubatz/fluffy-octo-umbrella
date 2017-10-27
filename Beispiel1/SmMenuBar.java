import java.awt.*;
import java.awt.event.*;
import SmEventListener;
import SmMainFrame;




// ino.class.SmMenuBar.460.description type=line
// Die Klasse SmMenuBar ist die Menuleiste des Symboleditors. Die meisten          
// Attribute sind selbsterklärend, deshalb wird auf eine nähere Beschreibung       
// verzichtet.
// ino.end
// ino.class.SmMenuBar.460.declaration 
public class SmMenuBar
// ino.end
// ino.class.SmMenuBar.460.body
{
	// ino.attribute.m_Frame.473.description type=line
	// Dieses Attribut hält eine Referenz auf das Hauptfenster des Symboleditors.
	// ino.end
	// ino.attribute.m_Frame.473.declaration 
	SmMainFrame m_Frame = null;
	// ino.end
	// ino.attribute.m_fInitialized.476.description type=line
	// Dieses Attribut verhindert das mehrmalige erzeugen einer Menuleiste. Es wird    
	// beim Aufruf der Funktion CreateMenu() auf TRUE gesetzt.
	// ino.end
	// ino.attribute.m_fInitialized.476.declaration 
	boolean m_fInitialized = false;
	// ino.end
		
	// ino.attribute.mb.479.declaration 
	protected static MenuBar mb;
	// ino.end
	// ino.attribute.EventListener.3521.declaration 
	protected SmEventListener EventListener;
	// ino.end

	// ino.attribute.m1.485.declaration 
	protected static Menu m1;
	// ino.end
	// ino.attribute.ID_NEW.3529.declaration 
	protected static MenuItem ID_NEW;
	// ino.end
	// ino.attribute.ID_OPEN.3532.declaration 
	protected static MenuItem ID_OPEN;
	// ino.end
	// ino.attribute.ID_SAVE.515.declaration 
	protected static MenuItem ID_SAVE;
	// ino.end
	// ino.attribute.ID_SAVEAS.518.declaration 
	protected static MenuItem ID_SAVEAS;
	// ino.end
	// ino.attribute.ID_CLOSE.521.declaration 
	protected static MenuItem ID_CLOSE;
	// ino.end
	// ino.attribute.m14.524.declaration 
	protected static MenuItem m14;
	// ino.end
	// ino.attribute.ID_QUIT.527.declaration 
	protected static MenuItem ID_QUIT;
	// ino.end

	// ino.attribute.m16.530.declaration 
	protected static Menu m16;
	// ino.end
	// ino.attribute.ID_CUT.533.declaration 
	protected static MenuItem ID_CUT;
	// ino.end
	// ino.attribute.ID_COPY.536.declaration 
	protected static MenuItem ID_COPY;
	// ino.end
	// ino.attribute.ID_PASTE.539.declaration 
	protected static MenuItem ID_PASTE;
	// ino.end
	// ino.attribute.IDCANCEL.542.declaration 
	protected static MenuItem IDCANCEL;
	// ino.end
	// ino.attribute.m21.545.declaration 
	protected static MenuItem m21;
	// ino.end
	// ino.attribute.ID_SELECT_ALL.548.declaration 
	protected static MenuItem ID_SELECT_ALL;
	// ino.end
	// ino.attribute.m23.551.declaration 
	protected static MenuItem m23;
	// ino.end
	// ino.attribute.ID_GROUP.554.declaration 
	protected static MenuItem ID_GROUP;
	// ino.end
	// ino.attribute.ID_UNGROUP.557.declaration 
	protected static MenuItem ID_UNGROUP;
	// ino.end
	// ino.attribute.m26.560.declaration 
	protected static MenuItem m26;
	// ino.end
	// ino.attribute.ID_CHANGE.563.declaration 
	protected static MenuItem ID_CHANGE;
	// ino.end
	// ino.attribute.m28.566.declaration 
	protected static MenuItem m28;
	// ino.end
	// ino.attribute.m29.569.declaration 
	protected static Menu m29;
	// ino.end
	// ino.attribute.ID_CHANGE_SYMBOL.572.declaration 
	protected static MenuItem ID_CHANGE_SYMBOL;
	// ino.end
	// ino.attribute.ID_CHANGE_STRUCTURE.575.declaration 
	protected static MenuItem ID_CHANGE_STRUCTURE;
	// ino.end
	// ino.attribute.ID_PIN_COUNT.3535.declaration 
	protected static MenuItem ID_PIN_COUNT;
	// ino.end
	// ino.attribute.m32.578.declaration 
	protected static Menu m32;
	// ino.end
	// ino.attribute.ID_ADD_COMPONENT.584.declaration 
	protected static MenuItem ID_ADD_COMPONENT;
	// ino.end
	// ino.attribute.ID_PIN_PROPERTIES.596.declaration 
	protected static MenuItem ID_PIN_PROPERTIES;
	// ino.end
	// ino.attribute.m36.590.declaration 
	protected static Menu m36;
	// ino.end
	// ino.attribute.ID_SET_INSERT_POINT.3538.declaration 
	protected static MenuItem ID_SET_INSERT_POINT;
	// ino.end
	// ino.attribute.ID_CHANGE_SHAPE.3541.declaration 
	protected static MenuItem ID_CHANGE_SHAPE;
	// ino.end
	// ino.attribute.ID_SET_LAYOUTAREA.3544.declaration 
	protected static MenuItem ID_SET_LAYOUTAREA;
	// ino.end
	
	// ino.attribute.m39.599.declaration 
	protected static Menu m39;
	// ino.end
	// ino.attribute.ID_LAYER.3547.declaration 
	protected static MenuItem ID_LAYER;
	// ino.end
	// ino.attribute.ID_ZOOM.3550.declaration 
	protected static MenuItem ID_ZOOM;
	// ino.end

	// ino.attribute.m51.635.declaration 
	protected static Menu m51;
	// ino.end
	// ino.attribute.ID_LINE.638.declaration 
	protected static MenuItem ID_LINE;
	// ino.end
	// ino.attribute.ID_RECT.641.declaration 
	protected static MenuItem ID_RECT;
	// ino.end
	// ino.attribute.ID_ELLIPSE.644.declaration 
	protected static MenuItem ID_ELLIPSE;
	// ino.end
	// ino.attribute.m55.647.declaration 
	protected static MenuItem m55;
	// ino.end
	// ino.attribute.m56.650.declaration 
	protected static Menu m56;
	// ino.end
	// ino.attribute.ID_TEXT_STATIC.653.declaration 
	protected static MenuItem ID_TEXT_STATIC;
	// ino.end
	// ino.attribute.ID_TEXT_ANNOTATION.656.declaration 
	protected static MenuItem ID_TEXT_ANNOTATION;
	// ino.end
	// ino.attribute.ID_IMAGE.659.declaration 
	protected static MenuItem ID_IMAGE;
	// ino.end
	// ino.attribute.m60.662.declaration 
	protected static MenuItem m60;
	// ino.end
	// ino.attribute.ID_PIN.665.declaration 
	protected static MenuItem ID_PIN;
	// ino.end

	// ino.attribute.m62.668.declaration 
	protected static Menu m62;
	// ino.end
	// ino.attribute.ID_GRID.671.declaration 
	protected static MenuItem ID_GRID;
	// ino.end
	// ino.attribute.m64.674.declaration 
	protected static MenuItem m64;
	// ino.end
	// ino.attribute.ID_COLORFILL.677.declaration 
	protected static MenuItem ID_COLORFILL;
	// ino.end
	// ino.attribute.ID_LINE_OPT.680.declaration 
	protected static MenuItem ID_LINE_OPT;
	// ino.end
	// ino.attribute.ID_TEXT_OPT.683.declaration 
	protected static MenuItem ID_TEXT_OPT;
	// ino.end

	// ino.attribute.m68.686.declaration 
	protected static Menu m68;
	// ino.end
	// ino.attribute.m69.689.declaration 
	protected static Menu m69;
	// ino.end
	// ino.attribute.ID_ENGLISH.692.declaration 
	protected static MenuItem ID_ENGLISH;
	// ino.end
	// ino.attribute.ID_GERMAN.695.declaration 
	protected static MenuItem ID_GERMAN;
	// ino.end
	// ino.attribute.ID_FRENCH.698.declaration 
	protected static MenuItem ID_FRENCH;
	// ino.end
	// ino.attribute.m73.701.declaration 
	protected static Menu m73;
	// ino.end
	// ino.attribute.ID_WINDOWS.704.declaration 
	protected static MenuItem ID_WINDOWS;
	// ino.end
	// ino.attribute.ID_MOTIF.707.declaration 
	protected static MenuItem ID_MOTIF;
	// ino.end

	// ino.attribute.m76.710.declaration 
	protected static Menu m76;
	// ino.end
	// ino.attribute.VS_VERSION_INFO.713.declaration 
	protected static MenuItem VS_VERSION_INFO;
	// ino.end

	// ino.method.SmMenuBar.3553.description type=line
	// Der Parameterkonstruktor initialisiert das Attribut m_frame mit einer Referenz  
	// auf das Hauptfenster.
	// ino.end
	// ino.method.SmMenuBar.3553.definition 
	public SmMenuBar (SmMainFrame frame)
	// ino.end
	// ino.method.SmMenuBar.3553.body 
	{
		m_Frame = frame;
	}
	// ino.end

	// ino.method.CreateMenu.719.description type=line
	// Diese Funktion erzeugt die Menüleiste.
	// ino.end
	// ino.method.CreateMenu.719.definition 
	public boolean CreateMenu()
	// ino.end
	// ino.method.CreateMenu.719.body 
	{
		EventListener		= new SmEventListener();
		if (m_fInitialized || m_Frame == null)
			return false;

		mb = new MenuBar();
		m_Frame.setMenuBar(mb);

		m1 = new Menu("Datei");
		mb.add(m1);
				
			ID_NEW = new MenuItem("Neu", new MenuShortcut(KeyEvent.VK_N));
			ID_NEW.setActionCommand("new");
			ID_NEW.addActionListener((ActionListener) m_Frame);
			m1.add(ID_NEW);

			ID_OPEN = new MenuItem("open", new MenuShortcut(KeyEvent.VK_O));
			ID_OPEN.setActionCommand("open");
			ID_OPEN.addActionListener((ActionListener) m_Frame);
		    m1.add(ID_OPEN);	
				
			ID_SAVE = new MenuItem("Speichern",new MenuShortcut(KeyEvent.VK_S));
			ID_SAVE.setEnabled(false);
			ID_SAVE.setActionCommand("save");
			ID_SAVE.addActionListener((ActionListener) m_Frame);
			m1.add(ID_SAVE);

			ID_SAVEAS = new MenuItem("Speichern als");
			ID_SAVEAS.setEnabled(false);
			ID_SAVEAS.setActionCommand("saveas");
			ID_SAVEAS.addActionListener(EventListener);
			m1.add(ID_SAVEAS);

			ID_CLOSE = new MenuItem("Schliessen");
			ID_CLOSE.setEnabled(false);
			ID_CLOSE.setActionCommand("close");
			ID_CLOSE.addActionListener((ActionListener) m_Frame);
			m1.add(ID_CLOSE);

			m14 = new MenuItem("-");
			m1.add(m14);

			ID_QUIT = new MenuItem("Beenden");
			ID_QUIT.setActionCommand("quit");
			ID_QUIT.addActionListener((ActionListener) m_Frame);
			m1.add(ID_QUIT);

		m16 = new Menu("Bearbeiten");
		mb.add(m16);

			ID_CUT = new MenuItem("Ausschneiden",new MenuShortcut(KeyEvent.VK_X));
			ID_CUT.setActionCommand("cut");
			m16.add(ID_CUT);

			ID_COPY = new MenuItem("Kopieren",new MenuShortcut(KeyEvent.VK_C));
			ID_COPY.setActionCommand("copy");
			m16.add(ID_COPY);

			ID_PASTE = new MenuItem("Einfügen",new MenuShortcut(KeyEvent.VK_V));
			ID_PASTE.setActionCommand("paste");
			m16.add(ID_PASTE);

			IDCANCEL = new MenuItem("Löschen",new MenuShortcut(KeyEvent.VK_DELETE));
			IDCANCEL.setActionCommand("delete");
			m16.add(IDCANCEL);

			m21 = new MenuItem("-");
			m16.add(m21);

			ID_SELECT_ALL = new MenuItem("Alles Markieren",new MenuShortcut(KeyEvent.VK_A));
			ID_SELECT_ALL.setActionCommand("selectall");
			m16.add(ID_SELECT_ALL);

			m23 = new MenuItem("-");
			m16.add(m23);

			ID_GROUP = new MenuItem("Gruppieren",new MenuShortcut(KeyEvent.VK_G));
			ID_GROUP.setActionCommand("group");
			m16.add(ID_GROUP);

			ID_UNGROUP = new MenuItem("Gruppierung Auflösen",new MenuShortcut(KeyEvent.VK_U));
			ID_UNGROUP.setActionCommand("ungroup");
			m16.add(ID_UNGROUP);

			m26 = new MenuItem("-");
			m16.add(m26);

			ID_CHANGE = new MenuItem("Name ändern");
			ID_CHANGE.setActionCommand("change");
			ID_CHANGE.addActionListener(EventListener);
			m16.add(ID_CHANGE);

			m28 = new MenuItem("-");
			m16.add(m28);

			m29 = new Menu("Präsentant");
			m16.add(m29);

				ID_CHANGE_SYMBOL = new MenuItem("Symbolzuordnung ändern");
				ID_CHANGE_SYMBOL.setActionCommand("changesymbol");
				ID_CHANGE_SYMBOL.addActionListener(EventListener);
				m29.add(ID_CHANGE_SYMBOL);

				ID_CHANGE_STRUCTURE = new MenuItem("Strukturzuordnung ändern");
				ID_CHANGE_STRUCTURE.setActionCommand("changestructure");
				ID_CHANGE_STRUCTURE.addActionListener(EventListener);
				m29.add(ID_CHANGE_STRUCTURE);

				ID_PIN_COUNT = new MenuItem("Anzahl der Pins festlegen");
				ID_PIN_COUNT.setActionCommand("pincount");
				ID_PIN_COUNT.addActionListener(EventListener);
				m29.add(ID_PIN_COUNT);

			m32 = new Menu("Struktur");
			m16.add(m32);

				ID_ADD_COMPONENT = new MenuItem("Präsentant hinzufügen");
				ID_ADD_COMPONENT.setActionCommand("addcomponent");
				ID_ADD_COMPONENT.addActionListener(EventListener);
				m32.add(ID_ADD_COMPONENT);

				ID_PIN_PROPERTIES = new MenuItem("Pinattribute setzen");
				ID_PIN_PROPERTIES.setActionCommand("setpinproperties");
				ID_PIN_PROPERTIES.addActionListener(EventListener);
				m32.add(ID_PIN_PROPERTIES);

			m36 = new Menu("Layout");
			m16.add(m36);

				ID_SET_INSERT_POINT = new MenuItem("Einfügepunkt setzen");
				ID_SET_INSERT_POINT.setActionCommand("setinsertpoint");
				ID_SET_INSERT_POINT.addActionListener(EventListener);
				m36.add(ID_SET_INSERT_POINT);
				
				ID_CHANGE_SHAPE = new MenuItem("Shape ändern");
				ID_CHANGE_SHAPE.setActionCommand("maxcount");
				ID_CHANGE_SHAPE.addActionListener(EventListener);
				m36.add(ID_CHANGE_SHAPE);

				ID_SET_LAYOUTAREA = new MenuItem("Layoutgebiet setzen");
				ID_SET_LAYOUTAREA.setActionCommand("setlayoutarea");
				ID_SET_LAYOUTAREA.addActionListener(EventListener);
				m36.add(ID_SET_LAYOUTAREA);
			
		m39 = new Menu("Ansicht");
		mb.add(m39);

			ID_LAYER = new MenuItem("Layer");
			ID_LAYER.setActionCommand("layer");
			ID_LAYER.addActionListener((ActionListener) m_Frame);
			m39.add(ID_LAYER);

			ID_ZOOM = new MenuItem("Zoom");
			ID_ZOOM.setActionCommand("zoom");
			ID_ZOOM.addActionListener((ActionListener) m_Frame);
		    m39.add(ID_ZOOM);
			
		m51 = new Menu("Einfügen");
		mb.add(m51);

			ID_LINE = new MenuItem("Linie");
			ID_LINE.setActionCommand("line");
			m51.add(ID_LINE);

			ID_RECT = new MenuItem("Rechteck");
			ID_RECT.setActionCommand("rect");
			m51.add(ID_RECT);

			ID_ELLIPSE = new MenuItem("Ellipse");
			ID_ELLIPSE.setActionCommand("ellipse");
			m51.add(ID_ELLIPSE);

			m55 = new MenuItem("-");
			m51.add(m55);

			m56 = new Menu("Text");
			m51.add(m56);

				ID_TEXT_STATIC = new MenuItem("Statischer Text");
				ID_TEXT_STATIC.setActionCommand("textstatic");
				ID_TEXT_STATIC.addActionListener((ActionListener) m_Frame);
				m56.add(ID_TEXT_STATIC);
	
				ID_TEXT_ANNOTATION = new MenuItem("Annotation");
				ID_TEXT_ANNOTATION.setActionCommand("annotation");
				m56.add(ID_TEXT_ANNOTATION);

			ID_IMAGE = new MenuItem("Grafik");
			ID_IMAGE.setActionCommand("image");
			ID_IMAGE.addActionListener((ActionListener) m_Frame);
			m51.add(ID_IMAGE);

			m60 = new MenuItem("-");
			m51.add(m60);

			ID_PIN = new MenuItem("Pins");
			ID_PIN.setActionCommand("pin");
			ID_PIN.addActionListener(EventListener);
			m51.add(ID_PIN);

		m62 = new Menu("Optionen");
		mb.add(m62);

			ID_GRID = new MenuItem("Raster");
			//ID_GRID.setEnabled(false);
			ID_GRID.setActionCommand("grid");
			ID_GRID.addActionListener((ActionListener) m_Frame);
			m62.add(ID_GRID);

			m64 = new MenuItem("-");
			m62.add(m64);
			
			ID_COLORFILL = new MenuItem("Farbattribute");
			ID_COLORFILL.setActionCommand("colorfill");
			ID_COLORFILL.addActionListener((ActionListener) m_Frame);
			m62.add(ID_COLORFILL);

			ID_LINE_OPT = new MenuItem("Linienattribute");
			ID_LINE_OPT.setActionCommand("lineopt");
			ID_LINE_OPT.addActionListener((ActionListener) m_Frame);
			m62.add(ID_LINE_OPT);

			ID_TEXT_OPT = new MenuItem("Textattribute");
			ID_TEXT_OPT.setActionCommand("textopt");
			ID_TEXT_OPT.addActionListener((ActionListener) m_Frame);
			m62.add(ID_TEXT_OPT);

		m68 = new Menu("Konfiguration");
		mb.add(m68);

			m69 = new Menu("Sprache");
			m68.add(m69);

				ID_ENGLISH = new MenuItem("Englisch");
				ID_ENGLISH.setActionCommand("english");
				ID_ENGLISH.addActionListener(EventListener);
				m69.add(ID_ENGLISH);

				ID_GERMAN = new MenuItem("Deutsch");
				ID_GERMAN.setActionCommand("german");
				ID_GERMAN.addActionListener(EventListener);
				m69.add(ID_GERMAN);

				ID_FRENCH = new MenuItem("Französisch");
				ID_FRENCH.setActionCommand("french");
				ID_FRENCH.addActionListener(EventListener);
				m69.add(ID_FRENCH);

			m73 = new Menu("API");
			m68.add(m73);
				
				ID_WINDOWS = new MenuItem("Windows 95");
				ID_WINDOWS.setActionCommand("windows");
				ID_WINDOWS.addActionListener(EventListener);
				m73.add(ID_WINDOWS);
	
				ID_MOTIF = new MenuItem("Motif");
				ID_MOTIF.setActionCommand("motif");
				ID_MOTIF.addActionListener(EventListener);
				m73.add(ID_MOTIF);
		
		m76 = new Menu("Hilfe");
		mb.setHelpMenu(m76);

			VS_VERSION_INFO = new MenuItem("Info");
			VS_VERSION_INFO.setActionCommand("version");
			VS_VERSION_INFO.addActionListener(EventListener);
			m76.add(VS_VERSION_INFO);

		m_fInitialized = true;
		return true;
	}
	// ino.end

	// ino.method.setMenuItemProperties.3556.description type=line
	// Diese Funktion setzt die Eigenschaften eines MenüItems in der Menüleiste. Als   
	// Parameter werden das MenüItem selbst, der Enable-Status und das Listener-Objekt 
	// übergeben.
	// ino.end
	// ino.method.setMenuItemProperties.3556.definition 
	public void setMenuItemProperties(MenuItem menuitem,boolean mode,ActionListener listener)
	// ino.end
	// ino.method.setMenuItemProperties.3556.body 
	{
		try
		{
			menuitem.setEnabled(mode);
			if (listener != null)
			{
				menuitem.addActionListener(listener);
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
	}
	// ino.end
}
// ino.end
