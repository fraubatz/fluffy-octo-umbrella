import java.awt.*;

// ino.class.SmRectangle.230.description type=line
// Diese Klasse beschreibt ein Rechteck und definiert dazu einen x- bzw. y-Wert    
// für die linke, untere Ecke des Rechtecks sowie eine Breite und eine Höhe. Durch 
// Angabe der Attribute isFilled und fillColor kann das Ausfüllen mit einer        
// bestimmten Farbe veranlaßt werden.
// ino.end
// ino.class.SmRectangle.230.declaration 
class SmRectangle extends SmGraphBase
// ino.end
// ino.class.SmRectangle.230.body
{
	// ino.attribute._className.320.description type=line
	// Dieses statische Attribut hält den Klassennamen.
	// ino.end
	// ino.attribute._className.320.declaration 
	private static final String _className = "SmRectangle";
	// ino.end

	// ino.attribute._width.234.description type=line
	// Dieses Attribut bestimmt die Breite des Rechtecks.
	// ino.end
	// ino.attribute._width.234.declaration 
	private int _width;
	// ino.end
	// ino.attribute._height.237.description type=line
	// Dieses Attribut bestimmt die Höhe des Rechtecks.
	// ino.end
	// ino.attribute._height.237.declaration 
	private int _height;
	// ino.end
	// ino.attribute._x.240.description type=line
	// Dieses Attribut bestimmt die x-Koordinate der linken, unteren Ecke des          
	// Rechtecks.
	// ino.end
	// ino.attribute._x.240.declaration 
	private int _x;
	// ino.end
	// ino.attribute._y.243.description type=line
	// Dieses Attribut bestimmt die y-Koordinate der linken, unteren Ecke des          
	// Rechtecks.
	// ino.end
	// ino.attribute._y.243.declaration 
	private int _y;
	// ino.end

	// ino.attribute._isFilled.323.description type=line
	// Dieses Attribut bestimmt, ob das Rechteck gefült ist oder nicht. Wenn das       
	// Attribut einen Wert ungleich 0 hat, ist eine Füllfarbe vorhanden.
	// ino.end
	// ino.attribute._isFilled.323.declaration 
	private int _isFilled;
	// ino.end

	// ino.attribute._fillColor.326.description type=line
	// Dieses Attribut bestimmt die Füllfarbe des Rechtecks.
	// ino.end
	// ino.attribute._fillColor.326.declaration 
	private String _fillColor;
	// ino.end


    // ino.method.SmRectangle.246.description type=line
    // Der Standardkonstruktor initialisiert die Variablen für den linken, unteren     
    // Punkt, die Breite, die Höhe und das Füllflag mit 0. Zusätzlich wird die         
    // Füllfarbe auf weiß gesetzt.
    // ino.end
    // ino.method.SmRectangle.246.definition 
    public SmRectangle()
    // ino.end
    // ino.method.SmRectangle.246.body 
    {
		_x			=	0;
		_y			=	0;
		_width		=	0;
		_height		=	0;			
		_isFilled	=	0;
		_fillColor	=	"white";
    }
    // ino.end

	// ino.method.SmRectangle.329.description type=line
	// Der Kopierkonstruktor kopiert alle Werte des übergebenen Objektes. Zusätzlich   
	// werden alle Werte der abgeleiteten Klasse SmGraphBase kopiert.
	// ino.end
	// ino.method.SmRectangle.329.definition 
	public SmRectangle(SmRectangle copy)
	// ino.end
	// ino.method.SmRectangle.329.body 
	{
		_x			=	copy._x;
		_y			=	copy._y;
		_width		=	copy._width;
		_height		=	copy._height;
		_isFilled	=	copy._isFilled;
		_fillColor	=	copy._fillColor;
	}
	// ino.end


	// ino.method.SmRectangle.249.description type=line
	// Der Parameterkonstruktor initialisiert die Werte für den Startpunkt und die     
	// Abmessungen des Rechtecks aus den übergebenen Werten. Das Füllflag wird auf 0   
	// gesetzt, die Füllfarbe mit weiß initialisiert.
	// ino.end
	// ino.method.SmRectangle.249.definition 
	public SmRectangle(int x,int y,int width,int height)
	// ino.end
	// ino.method.SmRectangle.249.body 
	{
		_x			=	x;
		_y			=	y;
		_width		=	width;
		_height		=	height;
		_fillColor	=	"white";
	}
	// ino.end

	// ino.method.setX.252.description type=line
	// Diese Funktion setzt den Wert der x-Koordinate der linken, unteren Ecke des     
	// Rechtecks auf den übergebenen Wert.
	// ino.end
	// ino.method.setX.252.definition 
	public void setX(int x)
	// ino.end
	// ino.method.setX.252.body 
	{
		_x = x;
		
	}
	// ino.end

	// ino.method.setY.255.description type=line
	// Diese Funktion setzt den Wert der y-Koordinate der linken, unteren Ecke des     
	// Rechtecks auf den übergebenen Wert.
	// ino.end
	// ino.method.setY.255.definition 
	public void setY(int y)
	// ino.end
	// ino.method.setY.255.body 
	{
		_y = y;
		
	}
	// ino.end

	// ino.method.getX.258.description type=line
	// Diese Funktion liefert den Wert der x-Koordinate der linken, unteren Ecke des   
	// Rechtecks zurück.
	// ino.end
	// ino.method.getX.258.definition 
	public int getX()
	// ino.end
	// ino.method.getX.258.body 
	{
		return _x;
	}
	// ino.end

	// ino.method.getY.261.description type=line
	// Diese Funktion liefert den Wert der y-Koordinate der linken, unteren Ecke des   
	// Rechtecks zurück.
	// ino.end
	// ino.method.getY.261.definition 
	public int getY()
	// ino.end
	// ino.method.getY.261.body 
	{
		return _y;
	}
	// ino.end

	// ino.method.setWidth.264.description type=line
	// Diese Funktion setzt den Wert der Breite des Rechtecks auf den übergebenen      
	// Wert. Wird ein Wert kleiner als 0 übergeben, so wird der neue Wert nicht        
	// gesetzt.
	// ino.end
	// ino.method.setWidth.264.definition 
	public void setWidth(int width)
	// ino.end
	// ino.method.setWidth.264.body 
	{
		if(width < 0) _width = width;
	}
	// ino.end

	// ino.method.setHeight.267.description type=line
	// Diese Funktion setzt den Wert die Höhe des Rechtecks auf den übergebenen Wert.  
	// Wird ein Wert kleiner als 0 übergeben, so wird der neue Wert nicht gesetzt.
	// ino.end
	// ino.method.setHeight.267.definition 
	public void setHeight(int height)
	// ino.end
	// ino.method.setHeight.267.body 
	{
		if(height < 0) _height = height;
	}
	// ino.end

	// ino.method.getWidth.270.description type=line
	// Diese Funktion liefert den Wert der Breite des Rechtecks zurück.
	// ino.end
	// ino.method.getWidth.270.definition 
	public int getWidth()
	// ino.end
	// ino.method.getWidth.270.body 
	{
		return _width;
	}
	// ino.end

	// ino.method.getHeight.273.description type=line
	// Diese Funktion liefert den Wert der Höhe des Rechtecks zurück.
	// ino.end
	// ino.method.getHeight.273.definition 
	public int getHeight()
	// ino.end
	// ino.method.getHeight.273.body 
	{
		return _height;
	}
	// ino.end

	// ino.method.setIsFilled.332.description type=line
	// Diese Funktion setzt den Wert des Füllflags des Rechtecks auf den übergebenen   
	// Wert.
	// ino.end
	// ino.method.setIsFilled.332.definition 
	public void setIsFilled(int isFilled)
	// ino.end
	// ino.method.setIsFilled.332.body 
	{
		_isFilled = isFilled;
	}
	// ino.end

	// ino.method.getIsFilled.335.description type=line
	// Diese Funktion liefert den Wert des Füllflags des Rechtecks zurück.
	// ino.end
	// ino.method.getIsFilled.335.definition 
	public int getIsFilled()
	// ino.end
	// ino.method.getIsFilled.335.body 
	{
		return _isFilled;
	}
	// ino.end

	// ino.method.setFillColor.338.description type=line
	// Diese Funktion setzt den wert der Füllfarbe des Rechtecks auf den übergebenen   
	// Wert. Wird ein Nullzeiger übergeben, so wird der Wert nicht gesetzt.
	// ino.end
	// ino.method.setFillColor.338.definition 
	public void setFillColor(String fillColor)
	// ino.end
	// ino.method.setFillColor.338.body 
	{
		if(fillColor.length() > 0) _fillColor = fillColor;
	}
	// ino.end

	// ino.method.getFillColor.341.description type=line
	// Diese Funktion liefert die aktuelle Füllfarbe des Rechtecks zurück.
	// ino.end
	// ino.method.getFillColor.341.definition 
	public String getFillColor()
	// ino.end
	// ino.method.getFillColor.341.body 
	{
		return _fillColor;
	}
	// ino.end
	// ino.method.getClassName.4421.definition 
	public final String getClassName()
	// ino.end
	// ino.method.getClassName.4421.body 
	{
		return _className;
	}
	// ino.end
		
	// ino.method.toString.344.description type=line
	// Diese Funktion gibt den Inhalt des Objektes als einen String aus.
	// ino.end
	// ino.method.toString.344.definition 
	public String toString()
	// ino.end
    // ino.method.toString.344.body 
    {
       return    "SmRectangle"				+ '\n'
			  + '0'							+ '\n'
			  + '{'							+ '\n'
			  +  super.toString()
		      + "x "		 + _x			+ '\n' 
			  + "y "		 + _y			+ '\n'
			  + "width "	 + _width		+ '\n'
			  + "height "	 + _height		+ '\n'
			  + "isFilled "  + _isFilled	+ '\n'
			  + "fillColor " + _fillColor	+ '\n'
			  + '}'							+ '\n';
			  
           
    }
    // ino.end
}
// ino.end
