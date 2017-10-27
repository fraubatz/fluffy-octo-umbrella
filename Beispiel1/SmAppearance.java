import java.awt.*;

// ino.class.SmAppearance.284.description type=line
// Diese Klasse beschreibt die grafische Ausprägung einer Komponente und pflegt    
// Listen von allen grafischen Grundelementen.
// ino.end
// ino.class.SmAppearance.284.declaration 
public class SmAppearance
// ino.end
// ino.class.SmAppearance.284.body
{
	// ino.attribute._classname.4414.description type=line
	// Dieses Attribut hält den Klassennamen.
	// ino.end
	// ino.attribute._classname.4414.declaration 
	private static final String _classname = "SmAppearance";
	// ino.end
	
	// ino.attribute._lineList.4387.description type=line
	// Dieses Attribut stellt die Liste aller Linien der grafischen Ausprägung dar.
	// ino.end
	// ino.attribute._lineList.4387.declaration 
	private SmEladoObjectList _lineList;
	// ino.end

	// ino.attribute._textList.4392.description type=line
	// Dieses Attribut stellt die Liste aller Textfelder der grafischen Ausprägung dar.
	// ino.end
	// ino.attribute._textList.4392.declaration 
	private SmEladoObjectList _textList;
	// ino.end

	// ino.attribute._rectangleList.4397.description type=line
	// Dieses Attribut stellt die Liste aller Rechtecke der grafischen Ausprägung dar.
	// ino.end
	// ino.attribute._rectangleList.4397.declaration 
	private SmEladoObjectList _rectangleList;
	// ino.end

	// ino.attribute._ellipseList.4402.description type=line
	// Dieses Attribut stellt die Liste aller Ellipsen der grafischen Ausprägung dar.
	// ino.end
	// ino.attribute._ellipseList.4402.declaration 
	private SmEladoObjectList _ellipseList;
	// ino.end


	// ino.method.SmAppearance.401.description type=line
	// Der Standardkonstruktor erzeugt alle Listen.
	// ino.end
	// ino.method.SmAppearance.401.definition 
	public SmAppearance()
	// ino.end
	// ino.method.SmAppearance.401.body 
	{
		_lineList		= new SmEladoObjectList();
		_textList		= new SmEladoObjectList();
		_rectangleList	= new SmEladoObjectList();
		_ellipseList	= new SmEladoObjectList();
	}
	// ino.end

	// ino.method.appendLine.404.description type=line
	// Diese Funktion fügt das übergebene Linienobjekt der entsprechenden Liste hinzu.
	// ino.end
	// ino.method.appendLine.404.definition 
	public void appendLine(SmLine line)
	// ino.end
	// ino.method.appendLine.404.body 
	{
		_lineList.appendObject(line);
	}
	// ino.end

	// ino.method.getLineCount.407.description type=line
	// Diese Funktion liefert die Anzahl der Linien in der entsprechenden Liste zurück.
	// ino.end
	// ino.method.getLineCount.407.definition 
	public int getLineCount()
	// ino.end
	// ino.method.getLineCount.407.body 
	{
		return _lineList.getObjectCount();
	}
	// ino.end

	// ino.method.getLineByListNumber.410.description type=line
	// Diese Funktion liefert das Linienobjekt mit dem übergebenen Index aus der       
	// entsprechenden Liste zurück.
	// ino.end
	// ino.method.getLineByListNumber.410.definition 
	public SmLine getLineByListNumber(int number)
	// ino.end
	// ino.method.getLineByListNumber.410.body 
	{
		return (SmLine) _lineList.getObjectByListNumber(number);
	}
	// ino.end

	// ino.method.appendEllipse.413.description type=line
	// Diese Funktion fügt das übergebene Ellipsenobjekt der entsprechenden Liste      
	// hinzu.
	// ino.end
	// ino.method.appendEllipse.413.definition 
	public void appendEllipse(SmEllipse ellipse)
	// ino.end
	// ino.method.appendEllipse.413.body 
	{
		_ellipseList.appendObject(ellipse);
	}
	// ino.end

	// ino.method.getEllipseCount.416.description type=line
	// Diese Funktion liefert die Anzahl der Ellipsen in der entsprechenden Liste      
	// zurück.
	// ino.end
	// ino.method.getEllipseCount.416.definition 
	public int getEllipseCount()
	// ino.end
	// ino.method.getEllipseCount.416.body 
	{
		return _ellipseList.getObjectCount();
	}
	// ino.end

	// ino.method.getEllipseByListNumber.419.description type=line
	// Diese Funktion liefert das Ellipseobjekt mit dem übergebenen Index aus der      
	// entsprechenden Liste zurück.
	// ino.end
	// ino.method.getEllipseByListNumber.419.definition 
	public SmEllipse getEllipseByListNumber(int number)
	// ino.end
	// ino.method.getEllipseByListNumber.419.body 
	{
		return (SmEllipse) _ellipseList.getObjectByListNumber(number);
	}
	// ino.end

	// ino.method.appendText.422.description type=line
	// Diese Funktion fügt das übergebene Textobjekt der entsprechenden Liste hinzu.
	// ino.end
	// ino.method.appendText.422.definition 
	public void appendText(SmText text)
	// ino.end
	// ino.method.appendText.422.body 
	{
		_textList.appendObject(text);	
	}
	// ino.end

	// ino.method.getTextCount.425.description type=line
	// Diese Funktion liefert die Anzahl der Textfelder in der entsprechenden Liste    
	// zurück.
	// ino.end
	// ino.method.getTextCount.425.definition 
	public int getTextCount()
	// ino.end
	// ino.method.getTextCount.425.body 
	{
		return _textList.getObjectCount();
	}
	// ino.end

	// ino.method.getTextByListNumber.428.description type=line
	// Diese Funktion liefert das Textfeld mit dem übergebenen Index aus der           
	// entsprechenden Liste zurück.
	// ino.end
	// ino.method.getTextByListNumber.428.definition 
	public SmText getTextByListNumber(int number)
	// ino.end
	// ino.method.getTextByListNumber.428.body 
	{
		return (SmText) _textList.getObjectByListNumber(number);
	}
	// ino.end

	// ino.method.appendRectangle.431.description type=line
	// Diese Funktion fügt das übergebene Rechteckobjekt der entsprechenden Liste      
	// hinzu.
	// ino.end
	// ino.method.appendRectangle.431.definition 
	public void appendRectangle(SmRectangle rectangle)
	// ino.end
	// ino.method.appendRectangle.431.body 
	{
		_rectangleList.appendObject(rectangle);
	}
	// ino.end

	// ino.method.getRectangleCount.434.description type=line
	// Diese Funktion liefert die Anzahl der Rechtecke in der entsprechenden Liste     
	// zurück.
	// ino.end
	// ino.method.getRectangleCount.434.definition 
	public int getRectangleCount()
	// ino.end
	// ino.method.getRectangleCount.434.body 
	{
		return _rectangleList.getObjectCount();
	}
	// ino.end

	// ino.method.getRectangleByListNumber.437.description type=line
	// Diese Funktion liefert das Rechteckobjekt mit dem übergebenen Index aus der     
	// entsprechenden Liste zurück.
	// ino.end
	// ino.method.getRectangleByListNumber.437.definition 
	public SmRectangle getRectangleByListNumber(int number)
	// ino.end
	// ino.method.getRectangleByListNumber.437.body 
	{
		return (SmRectangle) _rectangleList.getObjectByListNumber(number);
	}
	// ino.end
	// ino.method.getClassName.4424.definition 
	public String getClassName()
	// ino.end
	// ino.method.getClassName.4424.body 
	{
		return _classname;
	}
	// ino.end
	

	// ino.method.clearAppearance.4407.description type=line
	// Diese Funktion löscht alle Listenelemente.
	// ino.end
	// ino.method.clearAppearance.4407.definition 
	public void clearAppearance()
	// ino.end
	// ino.method.clearAppearance.4407.body 
	{
		_lineList.removeAllObjects();
		_textList.removeAllObjects();
		_rectangleList.removeAllObjects();
		_ellipseList.removeAllObjects();
	}
	// ino.end

	// ino.method.toString.440.description type=line
	// Diese Funktion gibt den Inhalt aller Listen als einen String aus.
	// ino.end
	// ino.method.toString.440.definition 
	public String toString()
	// ino.end
	// ino.method.toString.440.body 
	{
		return	 "SmAppearance"			+'\n'
			   + '0'					+'\n'
			   + '{'					+'\n'
			   + "SmEladoObjectList"	+'\n'
			   + '0'					+'\n'
			   + '{'					+'\n'
			   + "List"					+'\n'
			   + '['					+'\n'
			   + _lineList.toString()
			   + ']'					+'\n'
			   + '}'					+'\n'
			   + "SmEladoObjectList"	+'\n'
			   + '0'					+'\n'
			   + '{'					+'\n'
			   + "List"					+'\n'
			   + '['					+'\n'
			   + _textList.toString()
			   + ']'					+'\n'
			   + '}'					+'\n'
			   + "SmEladoObjectList"	+'\n'
			   + '0'					+'\n'
			   + '{'					+'\n'
			   + "List"					+'\n'
			   + '['					+'\n'
			   + _rectangleList.toString()
			   + ']'					+'\n'
			   + '}'					+'\n'
			   + "SmEladoObjectList"	+'\n'
			   + '0'					+'\n'
			   + '{'					+'\n'
			   + "List"					+'\n'
			   + '['					+'\n'
			   + _ellipseList.toString()
			   + ']'					+'\n'
			   + '}'					+'\n'
			   + '}'					+'\n';
	}
	// ino.end
}
// ino.end




