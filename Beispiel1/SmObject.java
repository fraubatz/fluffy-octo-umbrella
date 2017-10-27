import java.lang.Object;
import java.awt.*;


// ino.class.SmObject.2267.description type=line
// Die Klasse SmObject ist die Basisklasse aller im Symboleditor erstellten        
// Objekte. Sie deklariert abstrakte Methoden, die alle abgeleiteten Objekte       
// implementieren müssen und normale Methoden, die implementiert werden können.
// ino.end
// ino.class.SmObject.2267.declaration 
public abstract class SmObject extends Object
// ino.end
// ino.class.SmObject.2267.body
{
   // ino.method.setForeground.2408.description type=line
   // Diese Funktion setzt die Vordergrundfarbe für ein Objekt.
   // ino.end
   // ino.method.setForeground.2408.declaration 
   public abstract void      	setForeground(Color foreground);
   // ino.end
  
   // ino.method.setBackground.2411.description type=line
   // Diese Funktion setzt die Hintergrundfarbe für ein Objekt.
   // ino.end
   // ino.method.setBackground.2411.declaration 
   public abstract void      	setBackground(Color background);
   // ino.end
   
   // ino.method.setFont.4268.description type=line
   // Diese Funktion setzt den Zeichensatz eines Text-Objekts.
   // ino.end
   // ino.method.setFont.4268.declaration 
   public abstract void   		setFont(Font font,FontMetrics metrics);
   // ino.end

   // ino.method.setText.4271.description type=line
   // Diese Funktion setzt den Text eines Text-Objekts.
   // ino.end
   // ino.method.setText.4271.declaration 
   public abstract void   		setText(String text);
   // ino.end
   
   // ino.method.paint.2414.description type=line
   // Diese Funktion zeichnet ein Objekt.
   // ino.end
   // ino.method.paint.2414.declaration 
   public abstract void      	paint(Graphics g);
   // ino.end
   
   // ino.method.contains.2417.description type=line
   // Diese Funktion prüft, ob der Mauszeiger sich innerhalb eines Objekts befindet.
   // ino.end
   // ino.method.contains.2417.declaration 
   public abstract boolean      contains(int x,int y);
   // ino.end
   
   // ino.method.setX.2420.description type=line
   // Diese Funktion setzt die X-Position eines Objekts.
   // ino.end
   // ino.method.setX.2420.declaration 
   public abstract void      	setX(int x);
   // ino.end
  
   // ino.method.setY.2423.description type=line
   // Diese Funktion setzt die Y-Position eines Objekts.
   // ino.end
   // ino.method.setY.2423.declaration 
   public abstract void      	setY(int y);
   // ino.end
  
   // ino.method.setXp1.4274.description type=line
   // Diese Funktion setzt die 1. X-Position einer Linie.
   // ino.end
   // ino.method.setXp1.4274.declaration 
   public abstract void   		setXp1(int xp1);
   // ino.end

   // ino.method.setXp2.4277.description type=line
   // Diese Funktion setzt die 2. X-Position einer Linie.
   // ino.end
   // ino.method.setXp2.4277.declaration 
   public abstract void   		setXp2(int xp2);
   // ino.end

   // ino.method.setYp1.4280.description type=line
   // Diese Funktion setzt die 1. Y-Position einer Linie.
   // ino.end
   // ino.method.setYp1.4280.declaration 
   public abstract void   		setYp1(int yp1);
   // ino.end

   // ino.method.setYp2.4283.description type=line
   // Diese Funktion setzt die 2. Y-Position einer Linie.
   // ino.end
   // ino.method.setYp2.4283.declaration 
   public abstract void   		setYp2(int yp2);
   // ino.end

   // ino.method.setWidth.2426.description type=line
   // Diese Funktion setzt die Breite eines Objekts.
   // ino.end
   // ino.method.setWidth.2426.declaration 
   public abstract void      	setWidth(int width);
   // ino.end
 
   // ino.method.setHeight.2429.description type=line
   // Diese Funktion setzt die Höhe eines Objekts.
   // ino.end
   // ino.method.setHeight.2429.declaration 
   public abstract void      	setHeight(int height);
   // ino.end
   
   // ino.method.getX.2432.description type=line
   // Diese Funktion gibt die X-Position eines Objekts zurück.
   // ino.end
   // ino.method.getX.2432.declaration 
   public abstract int      	getX();
   // ino.end
  
   // ino.method.getY.2435.description type=line
   // Diese Funktion gibt die Y-Position eines Objekts zurück.
   // ino.end
   // ino.method.getY.2435.declaration 
   public abstract int      	getY();
   // ino.end
   
   // ino.method.getWidth.2438.description type=line
   // Diese Funktion gibt die Breite eines Objekts zurück.
   // ino.end
   // ino.method.getWidth.2438.declaration 
   public abstract int      	getWidth();
   // ino.end
  
   // ino.method.getHeight.2441.description type=line
   // Diese Funktion gibt die Höhe eines Objekts zurück.
   // ino.end
   // ino.method.getHeight.2441.declaration 
   public abstract int      	getHeight();
   // ino.end
 
   // ino.method.getShape.2444.description type=line
   // Diese Funktion gibt das Shape eines Objekts zurück.
   // ino.end
   // ino.method.getShape.2444.declaration 
   public abstract SmShape      getShape();
   // ino.end
  
   // ino.method.showShape.2447.description type=line
   // Diese Funktion macht das Shape eines Objekts sichtbar.
   // ino.end
   // ino.method.showShape.2447.declaration 
   public abstract void      	showShape(Graphics g);
   // ino.end
   
   // ino.method.setIsSelected.2450.description type=line
   // Diese Funktion bestimmt den Selektstatus eines Objekts.
   // ino.end
   // ino.method.setIsSelected.2450.declaration 
   public abstract void      	setIsSelected(boolean isselected);
   // ino.end
   
   // ino.method.getIsSelected.2453.description type=line
   // Diese Funktion gibt den Selektstatus eines Objekts zurück.
   // ino.end
   // ino.method.getIsSelected.2453.declaration 
   public abstract boolean      getIsSelected();
   // ino.end
   
   // ino.method.setGroupState.4286.description type=line
   // Diese Funktion setzt den Gruppenstatus eines Objekts.
   // ino.end
   // ino.method.setGroupState.4286.declaration 
   public abstract void   		setGroupState(boolean isMemberOfGroup);
   // ino.end

   // ino.method.getGroupState.4289.description type=line
   // Diese Funktion liefert den Gruppenstatus eines Objekts zurück.
   // ino.end
   // ino.method.getGroupState.4289.declaration 
   public abstract boolean   	getGroupState();
   // ino.end
   
   // ino.method.setIsVisible.2456.description type=line
   // Diese Funktion bestimmt die Sichtbarkeit eines Objekts.
   // ino.end
   // ino.method.setIsVisible.2456.declaration 
   public abstract void      	setIsVisible(boolean isVisible);
   // ino.end
   
   // ino.method.setIsEditable.4292.description type=line
   // Diese Funktion bestimmt, ob ein Objekt selektiert werden kann.
   // ino.end
   // ino.method.setIsEditable.4292.declaration 
   public abstract void   		setIsEditable(boolean iseditable);
   // ino.end
   
   // ino.method.getIsVisible.2459.description type=line
   // Diese Funktion gibt die Sichtbarkeit zurück.
   // ino.end
   // ino.method.getIsVisible.2459.declaration 
   public abstract boolean      getIsVisible();
   // ino.end
  
   // ino.method.getSelector.2462.description type=line
   // Diese Funktion liefert den Selektor eines Objekts zurück.
   // ino.end
   // ino.method.getSelector.2462.declaration 
   public abstract SmSelector   getSelector();
   // ino.end
  
   // ino.method.setNameOfGraphicObject.2465.description type=line
   // Diese Funktion setzt den Namen eines Objekts.
   // ino.end
   // ino.method.setNameOfGraphicObject.2465.declaration 
   public abstract void      	setNameOfGraphicObject(String objectName);
   // ino.end
  
   // ino.method.getNameOfGraphicObject.2468.description type=line
   // Diese Funktion liefert den Namen eines Objekts zurück.
   // ino.end
   // ino.method.getNameOfGraphicObject.2468.declaration 
   public abstract String      getNameOfGraphicObject();
   // ino.end
  
   // ino.method.moveX.4295.description type=line
   // Diese Funktion verschiebt ein gruppiertes Objekt in X-Richtung. Der Offset wird 
   // als Parameter übergeben.
   // ino.end
   // ino.method.moveX.4295.definition 
   public void   		moveX(int x)
   // ino.end
   // ino.method.moveX.4295.body 
   {;}
   // ino.end

   // ino.method.moveY.4298.description type=line
   // Diese Funktion verschiebt ein gruppiertes Objekt in Y-Richtung. Der Offset wird 
   // als Parameter übergeben.
   // ino.end
   // ino.method.moveY.4298.definition 
   public void   		moveY(int y)
   // ino.end
   // ino.method.moveY.4298.body 
   {;}
   // ino.end

   // ino.method.getX1.4301.description type=line
   // Diese Funktion liefert die 1. X-Position einer Linie zurück.
   // ino.end
   // ino.method.getX1.4301.definition 
   public int   		getX1()
   // ino.end
   // ino.method.getX1.4301.body 
   {return 0;}
   // ino.end

   // ino.method.getY1.4304.description type=line
   // Diese Funktion liefert die 1. Y-Position einer Linie zurück.
   // ino.end
   // ino.method.getY1.4304.definition 
   public int   		getY1()
   // ino.end
   // ino.method.getY1.4304.body 
   {return 0;}
   // ino.end

   // ino.method.getX2.4307.description type=line
   // Diese Funktion liefert die 2. X-Position einer Linie zurück.
   // ino.end
   // ino.method.getX2.4307.definition 
   public int   		getX2()
   // ino.end
   // ino.method.getX2.4307.body 
   {return 0;}
   // ino.end

   // ino.method.getY2.4310.description type=line
   // Diese Funktion liefert die 2. Y-Position einer Linie zurück.
   // ino.end
   // ino.method.getY2.4310.definition 
   public int   		getY2()
   // ino.end
   // ino.method.getY2.4310.body 
   {return 0;}
   // ino.end

   // ino.method.getText.4313.description type=line
   // Diese Funktion liefert den Text eines Text-Objekts zurück.
   // ino.end
   // ino.method.getText.4313.definition 
   public String   	getText()
   // ino.end
   // ino.method.getText.4313.body 
   {return "";}
   // ino.end

   // ino.method.getFont.4316.description type=line
   // Diese Funktion liefert den Zeichensatz eines Text-Objekts zurück.
   // ino.end
   // ino.method.getFont.4316.definition 
   public Font   		getFont()
   // ino.end
   // ino.method.getFont.4316.body 
   {return new Font("",0,0);}
   // ino.end

   // ino.method.getMetrics.4319.description type=line
   // Diese Funktion liefert die Fontmetrics eines Text-Objekts zurück.
   // ino.end
   // ino.method.getMetrics.4319.definition 
   public FontMetrics   getMetrics()
   // ino.end
   // ino.method.getMetrics.4319.body 
   {return null;}
   // ino.end
}
// ino.end


