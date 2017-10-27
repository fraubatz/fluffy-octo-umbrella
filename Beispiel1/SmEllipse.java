import java.awt.*;


// ino.class.SmEllipse.302.description type=line
// Diese Klasse beschreibt eine Ellipse und definiert dazu einen x- bzw. y-Wert    
// f�r die linke, untere Ecke des umschreibenden Rechtecks, sowie zwei Radien.     
// Durch Angabe der Attribute isFilled und fillColor kann das Ausf�llen mit einer  
// bestimmten Farbe veranla�t werden. Bei gleichen Werten f�r beide Radien lassen  
// sich mit dieser Klasse auch Kreise implementieren.
// ino.end
// ino.class.SmEllipse.302.declaration 
public class SmEllipse extends SmGraphBase
// ino.end
// ino.class.SmEllipse.302.body
{
	// ino.attribute._className.2799.description type=line
	// Dieses statische Attribut h�lt den Klassennamen.
	// ino.end
	// ino.attribute._className.2799.declaration 
	private static String _className;
	// ino.end
	
	// ino.attribute._x.2802.description type=line
	// Dieses Attribut bestimmt die x-Koordinate der linken, unteren Ecke des          
	// umschreibenden Rechtecks der Ellipse.
	// ino.end
	// ino.attribute._x.2802.declaration 
	private int _x;
	// ino.end

	// ino.attribute._y.2805.description type=line
	// Dieses Attribut bestimmt die y-Koordinate der linken, unteren Ecke des          
	// umschreibenden Rechtecks der Ellipse.
	// ino.end
	// ino.attribute._y.2805.declaration 
	private int _y;
	// ino.end

	// ino.attribute._radiusA.2808.description type=line
	// Dieses Attribut bestimmt den horizontalen Radius der Ellipse.
	// ino.end
	// ino.attribute._radiusA.2808.declaration 
	private int _radiusA;
	// ino.end
	
	// ino.attribute._radiusB.2811.description type=line
	// Dieses Attribut bestimmt den vertikalen Radius der Ellipse.
	// ino.end
	// ino.attribute._radiusB.2811.declaration 
	private int _radiusB;
	// ino.end
	
	// ino.attribute._isFilled.2814.description type=line
	// Dieses Attribut bestimmt, ob die Ellipse gef�llt ist oder nicht. Wenn das       
	// Attribut einen Wert ungleich 0 hat, ist eine F�llfarbe vorhanden.
	// ino.end
	// ino.attribute._isFilled.2814.declaration 
	private int _isFilled;
	// ino.end

	// ino.attribute._fillColor.2817.description type=line
	// Dieses Attribut bestimmt die F�llfarbe der Ellipse.
	// ino.end
	// ino.attribute._fillColor.2817.declaration 
	private String _fillColor;
	// ino.end

	// ino.method.SmEllipse.2821.description type=line
	// Der Standardkonstruktor initialisiert die Variablen f�r linken, unteren         
	// Randpunkt des umschreibenden Rechtecks und die Radien mit 0, setzt das F�llflag 
	// auf 0 und die F�llfarbe auf wei�.
	// ino.end
	// ino.method.SmEllipse.2821.definition 
	public SmEllipse()
	// ino.end
	// ino.method.SmEllipse.2821.body 
	{	
		_x				= 0;
		_y				= 0;
		_radiusA		= 0;
		_radiusB		= 0;
		_isFilled		= 0;
		_fillColor		= "white";
	}
	// ino.end

	// ino.method.SmEllipse.2824.description type=line
	// Der Copykonstruktor kopiert alle Variablen aus dem �bergebenen Objekt.          
	// Zus�tzlich werden alle Variablen der abgeleiteten Klasse SmGraphBase kopiert.
	// ino.end
	// ino.method.SmEllipse.2824.definition 
	public SmEllipse(SmEllipse copy)
	// ino.end
	// ino.method.SmEllipse.2824.body 
	{
		_x				= copy._x;
		_y				= copy._y;
		_radiusA		= copy._radiusA;
		_radiusB		= copy._radiusB;
		_isFilled		= copy._isFilled;
		_fillColor		= copy._fillColor;
	}
	// ino.end

	// ino.method.SmEllipse.2827.description type=line
	// Der Parameterkonstruktor �bernimmt die Werte f�r den Randpunkt und die Radien   
	// aus den �bergebenen Parametern und initialisiert die Variablen des F�llflags und
	// der F�llfarbe wie der Standardkonstruktor.
	// ino.end
	// ino.method.SmEllipse.2827.definition 
	public SmEllipse(int x,int y,int radiusA,int radiusB)
	// ino.end
	// ino.method.SmEllipse.2827.body 
	{
		_x				= x;
		_y				= y;
		_radiusA		= radiusA;
		_radiusB		= radiusB;
		_isFilled		= 0;
		_fillColor		= "white";
	}
	// ino.end

	// ino.method.setX.2830.description type=line
	// Diese Funktion setzt den Wert der x-Koordinate der linken, unteren Ecke des     
	// umschreibenden Rechtecks auf den �bergebenen Wert.
	// ino.end
	// ino.method.setX.2830.definition 
	public void setX(int x)
	// ino.end
	// ino.method.setX.2830.body 
	{
		_x = x;
	}
	// ino.end
	
	// ino.method.getX.2833.description type=line
	// Diese Funktion liefert den Wert der x-Koordinate der linken, unteren Ecke des   
	// umschreibenden Rechtecks zur�ck.
	// ino.end
	// ino.method.getX.2833.definition 
	public int getX()
	// ino.end
	// ino.method.getX.2833.body 
	{
		return _x;
	}
	// ino.end

	// ino.method.setY.2836.description type=line
	// Diese Funktion setzt den Wert der y-Koordinate der linken, unteren Ecke des     
	// umschreibenden Rechtecks auf den �bergebenen Wert.
	// ino.end
	// ino.method.setY.2836.definition 
	public void setY(int y)
	// ino.end
	// ino.method.setY.2836.body 
	{
		_y = y;
	}
	// ino.end
	
	// ino.method.getY.2839.description type=line
	// Diese Funktion liefert den Wert der y-Koordinate der linken, unteren Ecke des   
	// umschreibenden Rechtecks zur�ck.
	// ino.end
	// ino.method.getY.2839.definition 
	public int getY()
	// ino.end
	// ino.method.getY.2839.body 
	{
		return _y;
	}
	// ino.end

	// ino.method.setRadiusA.4452.description type=line
	// Diese Funktion setzt die Variable f�r den horizontalen Radius auf den           
	// �bergebenen Wert. Ist der �bergebene Wert kleiner als 0, wird er nicht gesetzt.
	// ino.end
	// ino.method.setRadiusA.4452.definition 
	public void setRadiusA(int radiusA)
	// ino.end
	// ino.method.setRadiusA.4452.body 
	{
		if(radiusA < 0) _radiusA = radiusA;
	}
	// ino.end
	
	// ino.method.getRadiusA.4455.description type=line
	// Diese Funktion liefert den Wert des horizontalen Radius der Ellipse zur�ck.
	// ino.end
	// ino.method.getRadiusA.4455.definition 
	public int getRadiusA()
	// ino.end
	// ino.method.getRadiusA.4455.body 
	{
		return _radiusA;
	}
	// ino.end

	// ino.method.setRadiusB.4458.description type=line
	// Diese Funktion setzt die Variable f�r den vertikalen Radius auf den �bergebenen 
	// Wert. Ist der �bergebene Wert kleiner als 0, wird er nicht gesetzt.
	// ino.end
	// ino.method.setRadiusB.4458.definition 
	public void setRadiusB(int radiusB)
	// ino.end
	// ino.method.setRadiusB.4458.body 
	{
		if(radiusB < 0) _radiusB = radiusB;
	}
	// ino.end

	// ino.method.getRadiusB.4461.description type=line
	// Diese Funktion liefert den Wert des vertikalen Radius der Ellipse zur�ck.
	// ino.end
	// ino.method.getRadiusB.4461.definition 
	public int getRadiusB()
	// ino.end
	// ino.method.getRadiusB.4461.body 
	{
		return _radiusB;
	}
	// ino.end

	// ino.method.setIsFilled.4464.description type=line
	// Diese Funktion setzt die Variable f�r das F�llflag auf den �bergebenen Wert.
	// ino.end
	// ino.method.setIsFilled.4464.definition 
	public void setIsFilled(int isFilled)
	// ino.end
	// ino.method.setIsFilled.4464.body 
	{
		_isFilled = _isFilled;
	}
	// ino.end
	
	// ino.method.getIsFilled.4467.description type=line
	// Diese Funktion liefert den Wert des F�llflags der Ellipse zur�ck.
	// ino.end
	// ino.method.getIsFilled.4467.definition 
	public int getIsFilled()
	// ino.end
	// ino.method.getIsFilled.4467.body 
	{
		return _isFilled;
	}
	// ino.end
	
	// ino.method.setFillColor.4470.description type=line
	// Diese Funktion setzt die Variable f�r die F�llfarbe auf den �bergebenen Wert.
	// ino.end
	// ino.method.setFillColor.4470.definition 
	public void setFillColor(String fillColor)
	// ino.end
	// ino.method.setFillColor.4470.body 
	{
		if(fillColor.length() > 0) _fillColor = fillColor;
	}
	// ino.end
	
	// ino.method.getFillColor.4473.description type=line
	// Diese Funktion liefert die aktuelle F�llfarbe der Ellipse zur�ck.
	// ino.end
	// ino.method.getFillColor.4473.definition 
	public String getFillColor()
	// ino.end
	// ino.method.getFillColor.4473.body 
	{
		return _fillColor;
	}
	// ino.end

	// ino.method.getClassName.4476.description type=line
	// Diese Funktion liefert den Klassennamen zur�ck.
	// ino.end
	// ino.method.getClassName.4476.definition 
	public String getClassName()
	// ino.end
	// ino.method.getClassName.4476.body 
	{
		return _className;
	}
	// ino.end

	// ino.method.toString.4479.description type=line
	// Diese Funktion gibt den Inhalt des Objektes als einen String aus.
	// ino.end
	// ino.method.toString.4479.definition 
	public String toString()
	// ino.end
	// ino.method.toString.4479.body 
	{
		return    "SmEllipse"				+ '\n'
			  + '0'							+ '\n'
			  + '{'							+ '\n'
			  +  super.toString()
		      + "x "		 + _x			+ '\n' 
			  + "y "		 + _y			+ '\n'
			  + "radiusA "	 + _radiusA		+ '\n'
			  + "radiusB "	 + _radiusB		+ '\n'
			  + "isFilled "  + _isFilled	+ '\n'
			  + "SmEladoString"				+ '\n'
			  + '0'							+ '\n'
			  + '{'							+ '\n'
			  + "string " + "\"" + _fillColor  + "\"" + '\n'
			  + '}'							+ '\n'
			  + '}'							+ '\n';
	}
	// ino.end
	
}
// ino.end





