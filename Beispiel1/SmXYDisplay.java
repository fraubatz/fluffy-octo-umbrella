import java.awt.*;
import java.awt.event.*;

// ino.class.SmXYDisplay.448.description type=line
// Die Klasse SmXYDisplay ist die linke Komponente des Hauptfensters und stellt    
// eine Koordinatenanzeige für das Zeichenfenster dar. Die Funktionen dieser Klasse
// sind statisch und können direkt mit dem Klassennamen aufgerufen werden. Eine    
// Instanziierung ist nicht erforderlich.
// ino.end
// ino.class.SmXYDisplay.448.declaration 
public class SmXYDisplay extends Panel
// ino.end
// ino.class.SmXYDisplay.448.body
{
	
	// ino.attribute.WestLayout.907.description type=line
	// Dieses Attribut bestimmt das Layout der Klasse SmXYDisplay.
	// ino.end
	// ino.attribute.WestLayout.907.declaration 
	private GridBagLayout WestLayout;
	// ino.end
	// ino.attribute.WestConstraints.910.description type=line
	// Dieses Attribut bestimmt die Anordnung der GUI-Komponenten in der Klasse        
	// SmXYDisplay. Es ist nur erforderlich in Verbindung mit dem GridBagLayout(siehe  
	// auch java.awt.GrigBagLayout).
	// ino.end
	// ino.attribute.WestConstraints.910.declaration 
	private GridBagConstraints WestConstraints;
	// ino.end
	// ino.attribute.IDC_EDIT2.913.description type=line
	// Dieses Attribut ist ein Textfeld zur Aufnahme der X-Koordinate.
	// ino.end
	// ino.attribute.IDC_EDIT2.913.declaration 
	private static TextField IDC_EDIT2;
	// ino.end
	// ino.attribute.IDC_EDIT3.916.description type=line
	// Dieses Attribut ist ein Textfeld zur Aufnahme der Y-Koordinate.
	// ino.end
	// ino.attribute.IDC_EDIT3.916.declaration 
	private static TextField IDC_EDIT3;
	// ino.end
	// ino.attribute.IDC_STATIC1.919.description type=line
	// Dieses Attribut enthält die Beschriftung des Textfeldes für die X-Koordinate.
	// ino.end
	// ino.attribute.IDC_STATIC1.919.declaration 
	private Label IDC_STATIC1;
	// ino.end
	// ino.attribute.IDC_STATIC2.922.description type=line
	// Dieses Attribut enthält die Beschriftung des Textfeldes für die Y-Koordinate.
	// ino.end
	// ino.attribute.IDC_STATIC2.922.declaration 
	private Label IDC_STATIC2;
	// ino.end

	// ino.attribute._x.925.description type=line
	// Dieses Attribut hält die X-Koordinate.
	// ino.end
	// ino.attribute._x.925.declaration 
	private static int _x;
	// ino.end
	// ino.attribute._y.928.description type=line
	// Dieses Attribut hält die Y-Koordinate.
	// ino.end
	// ino.attribute._y.928.declaration 
	private static int _y;
	// ino.end

	
	// ino.method.SmXYDisplay.931.description type=line
	// Der Standardkonstruktor erzeugt die Komponenten des Panels und ordnet diese an.
	// ino.end
	// ino.method.SmXYDisplay.931.definition 
	public SmXYDisplay ()
	// ino.end
	// ino.method.SmXYDisplay.931.body 
	{
			
	
		WestLayout = new GridBagLayout();
		WestConstraints = new GridBagConstraints();
				
		
		this.setLayout(WestLayout);
		this.setBackground(Color.lightGray);
		

	
		IDC_STATIC1 = new Label ("X-Pos", Label.LEFT);
		WestConstraints.anchor = GridBagConstraints.NORTH;
		WestConstraints.gridwidth = GridBagConstraints.REMAINDER;
		WestConstraints.insets = new Insets(0,0,0,20);
				
		this.add(IDC_STATIC1,WestConstraints);


		IDC_EDIT2 = new TextField ("       ");
		WestConstraints.anchor = GridBagConstraints.NORTH;
		WestConstraints.gridwidth = GridBagConstraints.REMAINDER;
		WestConstraints.insets = new Insets(0,10,0,10);
		this.add(IDC_EDIT2,WestConstraints);
		
		
		IDC_STATIC2 = new Label ("Y-Pos", Label.LEFT);
		WestConstraints.anchor = GridBagConstraints.NORTH;
		WestConstraints.gridwidth = GridBagConstraints.REMAINDER;
		WestConstraints.insets = new Insets(0,0,0,20);
		
		this.add(IDC_STATIC2,WestConstraints);

	
		IDC_EDIT3 = new TextField ("       ");
		WestConstraints.anchor = GridBagConstraints.NORTH;
		WestConstraints.gridwidth = GridBagConstraints.REMAINDER;
		WestConstraints.weighty = 1.0;
		WestConstraints.insets = new Insets(0,10,0,10);
		
		this.add(IDC_EDIT3,WestConstraints);

		_x = 0;
		_y = 0;

		IDC_EDIT2.setText(String.valueOf(_x));
		IDC_EDIT3.setText(String.valueOf(_y));
		
	   
	}
	// ino.end

	// ino.method.setX.934.description type=line
	// Diese Funktion setzt die X-Koordinate.
	// ino.end
	// ino.method.setX.934.definition 
	public static void setX(int x)
	// ino.end
	// ino.method.setX.934.body 
	{
		_x = x;
		IDC_EDIT2.setText(String.valueOf(x));
	}
	// ino.end

	// ino.method.setY.937.description type=line
	// Diese Funktion setzt die Y-Koordinate.
	// ino.end
	// ino.method.setY.937.definition 
	public static void setY(int y)
	// ino.end
	// ino.method.setY.937.body 
	{
		_y = y;
		IDC_EDIT3.setText(String.valueOf(y));
	}
	// ino.end
	
}
// ino.end



