import java.awt.*;


// ino.class.SmLine.281.description type=line
// Diese Klasse implementiert eine Linie in Form einer Strecke und definiert       
// hierzu die Koordinaten eines Anfangs- und eines Endpunktes.
// ino.end
// ino.class.SmLine.281.declaration 
public class SmLine extends SmGraphBase
// ino.end
// ino.class.SmLine.281.body
{
	// ino.attribute._className.2796.description type=line
	// Dieses statische Attribut hält den Klassennamen.
	// ino.end
	// ino.attribute._className.2796.declaration 
	private static String _className;
	// ino.end

	// ino.attribute._x1.2746.description type=line
	// Dieses Attribut bestimmt die x-Koordinate des ersten Linienpunktes.
	// ino.end
	// ino.attribute._x1.2746.declaration 
	private int _x1;
	// ino.end

	// ino.attribute._y1.2749.description type=line
	// Dieses Attribut bestimmt die y-Koordinate des ersten Linienpunktes.
	// ino.end
	// ino.attribute._y1.2749.declaration 
	private int _y1;
	// ino.end
	
	// ino.attribute._x2.2752.description type=line
	// Dieses Attribut bestimmt die x-Koordinate des zweiten Linienpunktes.
	// ino.end
	// ino.attribute._x2.2752.declaration 
	private int _x2;
	// ino.end
	
	// ino.attribute._y2.2755.description type=line
	// Dieses Attribut bestimmt die y-Koordinate des zweiten Linienpunktes.
	// ino.end
	// ino.attribute._y2.2755.declaration 
	private int _y2;
	// ino.end

	// ino.method.SmLine.2759.description type=line
	// Der Standardkonstruktor initialisiert die Werte der Koordinaten der Endpunkte   
	// mit 0.
	// ino.end
	// ino.method.SmLine.2759.definition 
	public SmLine()
	// ino.end
	// ino.method.SmLine.2759.body 
	{
		_x1 = 0;
		_x2 = 0;
		_y1 = 0;
		_y2 = 0;
	}
	// ino.end

	// ino.method.SmLine.2762.description type=line
	// Der Copykonstruktor kopiert die Werte der Koordinaten der Endpunkte aus dem     
	// übergebenen Objekt. Weiterhin werden alle Werte der abgeleiteten Klasse         
	// SmGraphBase kopiert.
	// ino.end
	// ino.method.SmLine.2762.definition 
	public SmLine(SmLine copy)
	// ino.end
	// ino.method.SmLine.2762.body 
	{
		_x1 = copy._x1;
		_x2 = copy._x2;
		_y1 = copy._y1;
		_y2 = copy._y2;
	}
	// ino.end

	// ino.method.SmLine.2765.description type=line
	// Der Parameterkonstruktor initialisiert die Werte der Koordinaten der Endpunkte  
	// mit den übergebenen Parametern.
	// ino.end
	// ino.method.SmLine.2765.definition 
	public SmLine(int x1,int y1,int x2,int y2)
	// ino.end
	// ino.method.SmLine.2765.body 
	{
		_x1 = x1;
		_x2 = x2;
		_y1 = y1;
		_y2 = y2;
	}
	// ino.end

	// ino.method.setX1.2769.description type=line
	// Diese Funktion setzt den Wert der x-Koordinate des ersten Punktes auf den       
	// übergebenen Wert.
	// ino.end
	// ino.method.setX1.2769.definition 
	public void setX1(int x1)
	// ino.end
	// ino.method.setX1.2769.body 
	{
		_x1 = x1;
	}
	// ino.end

	// ino.method.getX1.2772.description type=line
	// Diese Funktion liefert den Wert der x-Koordinate des ersten Punktes zurück.
	// ino.end
	// ino.method.getX1.2772.definition 
	public int getX1()
	// ino.end
	// ino.method.getX1.2772.body 
	{
		return _x1;
	}
	// ino.end

	// ino.method.setY1.2775.description type=line
	// Diese Funktion setzt den Wert der y-Koordinate des ersten Punktes auf den       
	// übergebenen Wert.
	// ino.end
	// ino.method.setY1.2775.definition 
	public void setY1(int y1)
	// ino.end
	// ino.method.setY1.2775.body 
	{
		_y1 = y1;
	}
	// ino.end

	// ino.method.getY1.2778.description type=line
	// Diese Funktion liefert den Wert der y-Koordinate des ersten Punktes zurück.
	// ino.end
	// ino.method.getY1.2778.definition 
	public int getY1()
	// ino.end
	// ino.method.getY1.2778.body 
	{
		return _y1;
	}
	// ino.end

	// ino.method.setX2.2781.description type=line
	// Diese Funktion setzt den Wert der x-Koordinate des zweiten Punktes auf den      
	// übergebenen Wert.
	// ino.end
	// ino.method.setX2.2781.definition 
	public void setX2(int x2)
	// ino.end
	// ino.method.setX2.2781.body 
	{
		_x2 = x2;
	}
	// ino.end
	
	// ino.method.getX2.2784.description type=line
	// Diese Funktion liefert den Wert der x-Koordinate des zweiten Punktes zurück.
	// ino.end
	// ino.method.getX2.2784.definition 
	public int getX2()
	// ino.end
	// ino.method.getX2.2784.body 
	{
		return _x2;
	}
	// ino.end

	// ino.method.setY2.2787.description type=line
	// Diese Funktion setzt den Wert der y-Koordinate des zweiten Punktes auf den      
	// übergebenen Wert.
	// ino.end
	// ino.method.setY2.2787.definition 
	public void setY2(int y2)
	// ino.end
	// ino.method.setY2.2787.body 
	{
		_y2 = y2;
	}
	// ino.end

	// ino.method.getY2.2790.description type=line
	// Diese Funktion liefert den Wert der y-Koordinate des zweiten Punktes zurück.
	// ino.end
	// ino.method.getY2.2790.definition 
	public int getY2()
	// ino.end
	// ino.method.getY2.2790.body 
	{
		return _y2;
	}
	// ino.end

	// ino.method.getClassName.4433.description type=line
	// Diese Funktion gibt den Klassennamen zurück.
	// ino.end
	// ino.method.getClassName.4433.definition 
	public String getClassName()
	// ino.end
	// ino.method.getClassName.4433.body 
	{
		return _className;
	}
	// ino.end

	// ino.method.toString.2793.description type=line
	// Diese Funktion gibt den Inhalt des Objektes als einen String aus.
	// ino.end
	// ino.method.toString.2793.definition 
	public String toString()
	// ino.end
	// ino.method.toString.2793.body 
	{
		return    "SmLine"					+ '\n'
			  + '0'							+ '\n'
			  + '{'							+ '\n'
			  +  super.toString()
		      + "x1 "		 + _x1			+ '\n' 
			  + "y1 "		 + _y1			+ '\n'
			  + "x2 "		 + _x2			+ '\n'
			  + "y2 "   	 + _y2			+ '\n'
			  + '}'							+ '\n';
	}
	// ino.end

}
// ino.end





