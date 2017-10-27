import java.awt.*;
import java.awt.event.*;


// ino.class.SmPanelCenter.445.description type=line
// Die Klasse SmPanelCenter dient zur Aufnahme des Zeichenfensters mit             
// Scrollbereich. Sie stellt Funktionen bereit, die einen Scrollbereich und ein    
// Zeichenfenster erzeugen.
// ino.end
// ino.class.SmPanelCenter.445.declaration 
public class SmPanelCenter extends Panel implements Cloneable
// ino.end
// ino.class.SmPanelCenter.445.body
{
	// ino.attribute._mainFrame.2092.description type=line
	// Dieses Attribut ist eine Referenz auf das Hauptfenster.
	// ino.end
	// ino.attribute._mainFrame.2092.declaration 
	private Frame _mainFrame;
	// ino.end
	// ino.attribute._scrollPane.2095.description type=line
	// Dieses Attribut ist der Scrollbereich in dem das Zeichenfenster eingebettet     
	// wird.
	// ino.end
	// ino.attribute._scrollPane.2095.declaration 
	private SmScrollPane _scrollPane;
	// ino.end
	// ino.attribute._workingArea.738.description type=line
	// Dieses Attribut ist das Zeichenfenster des Symboleditors.
	// ino.end
	// ino.attribute._workingArea.738.declaration 
	private SmWorkingArea _workingArea;
	// ino.end

	// ino.method.SmPanelCenter.2098.description type=line
	// Der Parameterkonstruktor initialisiert das Attribut _mainFrame mit einer        
	// Referenz auf das Hauptfenster(SmMainFrame). Das Layout für die Anordnung der    
	// Zeichenfenster und die Hintergrundfarbe des Panels werden festgelegt.
	// ino.end
	// ino.method.SmPanelCenter.2098.definition 
	public SmPanelCenter(Frame mainframe)
	// ino.end
	// ino.method.SmPanelCenter.2098.body 
	{
		_mainFrame = mainframe;
		
		this.setLayout(new GridLayout());
		this.setBackground(Color.gray);
	}
	// ino.end

	// ino.method.createScrollPane.2101.description type=line
	// Diese Funktion erzeugt einen Scrollbereich und legt ihn auf das Panel.
	// ino.end
	// ino.method.createScrollPane.2101.definition 
	public void createScrollPane()
	// ino.end
	// ino.method.createScrollPane.2101.body 
	{
		_scrollPane = new SmScrollPane();
		this.add(_scrollPane);
	}
	// ino.end

	// ino.method.createWorkingArea.2104.description type=line
	// Diese Funktion erzeugt ein Zeichenfenster und bettet ihn in den Scrollbereich   
	// ein.
	// ino.end
	// ino.method.createWorkingArea.2104.definition 
	public void createWorkingArea()
	// ino.end
	// ino.method.createWorkingArea.2104.body 
	{
		createScrollPane();
		_workingArea = new SmWorkingArea();
		_workingArea.setSize(_workingArea.getPreferredSize());
		_scrollPane.add(_workingArea);
	}
	// ino.end

	// ino.method.setGraphicList.2107.description type=line
	// Diese Funktion übergibt dem Zeichenfenster eine Liste von Objekten, die zuvor   
	// aus einem Verzeichnis geladen wurden(Siehe auch DlgOpenFile). Das Laden ist zur 
	// Zeit noch auf grafische Primitive beschränkt.
	// ino.end
	// ino.method.setGraphicList.2107.definition 
	public void setGraphicList(SmEladoObjectList objectList)
	// ino.end
	// ino.method.setGraphicList.2107.body 
	{
		_workingArea.setGraphicList(objectList);
	}
	// ino.end

	// ino.method.getGraphicList.2110.description type=line
	// Diese Funktion liefert eine Referenz auf eine Liste von Objekten zurück, um     
	// diese ihn ein Verzeichnis ablegen zu können(Siehe auch DlgSaveFile). Das        
	// Speichern ist zur Zeit noch auf grafische Primitive beschränkt.
	// ino.end
	// ino.method.getGraphicList.2110.definition 
	public SmEladoObjectList getGraphicList()
	// ino.end
	// ino.method.getGraphicList.2110.body 
	{
		return _workingArea.getGraphicList();
	}
	// ino.end

	// ino.method.getWorkingArea.2113.description type=line
	// Diese Funktion liefert eine Referenz auf das Zeichenfenster zurück.
	// ino.end
	// ino.method.getWorkingArea.2113.definition 
	public SmWorkingArea getWorkingArea()
	// ino.end
	// ino.method.getWorkingArea.2113.body 
	{
		return _workingArea;
	}
	// ino.end
}
// ino.end

