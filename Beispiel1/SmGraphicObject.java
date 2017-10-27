import java.awt.*;


// ino.class.SmGraphicObject.1027.description type=line
// Die Klasse SmGraphicObject ist die Basisklasse aller im Symboleditor erstellten 
// grafischen Grundelemente, statische Texte und Annotationen und ist von SmObject 
// abgeleitet. Sie deklariert abstrakte Methoden, die alle abgeleiteten Objekte    
// implementieren müssen und normale Methoden, die implementiert werden können.
// ino.end
// ino.class.SmGraphicObject.1027.declaration 
public abstract class SmGraphicObject extends SmObject
// ino.end
// ino.class.SmGraphicObject.1027.body
{
   // ino.attribute._nameOfGraphicObject.1692.description type=line
   // Dieses Attribut hält den Namen eines grafischen Grundelements.
   // ino.end
   // ino.attribute._nameOfGraphicObject.1692.declaration 
   protected String _nameOfGraphicObject;
   // ino.end
  
   // ino.attribute._lineWidth.1123.description type=line
   // Dieses Attribut hält die Linienbreite aller grafischen Grundelemente.
   // ino.end
   // ino.attribute._lineWidth.1123.declaration 
   protected int _lineWidth;
   // ino.end
   
   // ino.attribute._lineStyle.1126.description type=line
   // Dieses Attribut hält die Linienart aller grafischen Grundelemente.
   // ino.end
   // ino.attribute._lineStyle.1126.declaration 
   protected int _lineStyle;
   // ino.end
  
   // ino.attribute._isSelected.1079.description type=line
   // Dieses Flag ist gesetzt(TRUE), wenn ein ein grafisches Grundelement selektiert  
   // ist.
   // ino.end
   // ino.attribute._isSelected.1079.declaration 
   protected boolean _isSelected;
   // ino.end
   
   // ino.attribute._isEditable.1068.description type=line
   // Dieses Flag ist gesetzt(TRUE), wenn ein ein grafisches Grundelement selektiert  
   // werden darf.
   // ino.end
   // ino.attribute._isEditable.1068.declaration 
   protected static boolean _isEditable;
   // ino.end
 
   // ino.attribute._isMemberOfGroup.4210.description type=line
   // Dieses Flag ist gesetzt(TRUE), wenn das grafische Grundelement Bestandteil      
   // einer Gruppierung ist.
   // ino.end
   // ino.attribute._isMemberOfGroup.4210.declaration 
   protected boolean _isMemberOfGroup;
   // ino.end

   // ino.attribute._isVisible.4213.description type=line
   // Dieses Flag ist gesetzt(TRUE), wenn das grafische Grundelement sichtbar sein    
   // soll.
   // ino.end
   // ino.attribute._isVisible.4213.declaration 
   protected boolean _isVisible;
   // ino.end
 
   // ino.attribute._background.1050.description type=line
   // Dieses Attribut ist die Hintergrundfarbe aller grafischen Grundelemente.
   // ino.end
   // ino.attribute._background.1050.declaration 
   protected Color _background;
   // ino.end
 
   // ino.attribute._foreground.1053.description type=line
   // Dieses Attribut ist die Vordergrundfarbe aller grafischen Grundelemente.
   // ino.end
   // ino.attribute._foreground.1053.declaration 
   protected Color _foreground;
   // ino.end
    
   // ino.method.SmGraphicObject.1269.description type=line
   // Der Standardkonstruktor initialisiert das grafische Grundelement                
   // folgendermaßen: Das Objekt ist sichtbar, nicht selektiert, editierbar und ist   
   // kein Member einer Gruppierung. Die Vordergrundfarbe ist schwarz, die            
   // Hintergrundfarbe ist weiß und die Linienbreite ist 1.
   // ino.end
   // ino.method.SmGraphicObject.1269.definition 
   public SmGraphicObject()
   // ino.end
   // ino.method.SmGraphicObject.1269.body 
   {
	   _lineWidth = 1;
	   _background = Color.white;
	   _foreground = Color.black;
	   _isSelected = false;
	   _isVisible  = true;
	   _isEditable = false;
	   _isMemberOfGroup = false;
   }
   // ino.end
       
   // ino.method.setBackground.1062.description type=line
   // Diese Funktion setzt die Hintergrundfarbe eines grafischen Grundelements.
   // ino.end
   // ino.method.setBackground.1062.definition 
   public void setBackground(Color background)
   // ino.end
   // ino.method.setBackground.1062.body 
   {
	   _background = background;
   }
   // ino.end

   // ino.method.setForeground.1059.description type=line
   // Diese Funktion setzt die Vordergrundfarbe eines grafischen Grundelements.
   // ino.end
   // ino.method.setForeground.1059.definition 
   public void setForeground(Color foreground)
   // ino.end
   // ino.method.setForeground.1059.body 
   {
	   _foreground = foreground;
   }
   // ino.end
   
   // ino.method.setFont.4216.description type=line
   // Diese Funktion setzt den Zeichensatz eines grafischen Grundelements(TextObjekt).
   // ino.end
   // ino.method.setFont.4216.declaration 
   public abstract void   		setFont(Font font,FontMetrics metrics);
   // ino.end
   // ino.method.setText.4219.description type=line
   // Diese Funktion setzt den Text eines grafischen Grundelements(TextObjekt).
   // ino.end
   // ino.method.setText.4219.declaration 
   public abstract void   		setText(String text);
   // ino.end
   // ino.method.paint.1272.description type=line
   // Diese Funktion zeichnet ein grafisches Grundelement.
   // ino.end
   // ino.method.paint.1272.declaration 
   public abstract void   		paint(Graphics g);
   // ino.end
   // ino.method.contains.1290.description type=line
   // Diese Funktion prüft, ob der Mauszeiger sich innerhalb eines grafischen         
   // Grundelements befindet.
   // ino.end
   // ino.method.contains.1290.declaration 
   public abstract boolean   	contains(int x,int y);
   // ino.end
   // ino.method.setX.1695.description type=line
   // Diese Funktion setzt die X-Position eines grafischen Grundelements.
   // ino.end
   // ino.method.setX.1695.declaration 
   public abstract void   		setX(int x);
   // ino.end
   // ino.method.setY.1698.description type=line
   // Diese Funktion setzt die Y-Position eines grafischen Grundelements.
   // ino.end
   // ino.method.setY.1698.declaration 
   public abstract void   		setY(int y);
   // ino.end
   // ino.method.setXp1.4222.description type=line
   // Diese Funktion setzt die 1. X-Position eines grafischen Grundelements(Linie).
   // ino.end
   // ino.method.setXp1.4222.declaration 
   public abstract void   		setXp1(int xp1);
   // ino.end
   // ino.method.setXp2.4225.description type=line
   // Diese Funktion setzt die 2. X-Position eines grafischen Grundelements(Linie).
   // ino.end
   // ino.method.setXp2.4225.declaration 
   public abstract void   		setXp2(int xp2);
   // ino.end
   // ino.method.setYp1.4228.description type=line
   // Diese Funktion setzt die 1. Y-Position eines grafischen Grundelements(Linie).
   // ino.end
   // ino.method.setYp1.4228.declaration 
   public abstract void   		setYp1(int yp1);
   // ino.end
   // ino.method.setYp2.4231.description type=line
   // Diese Funktion setzt die 2. Y-Position eines grafischen Grundelements(Linie).
   // ino.end
   // ino.method.setYp2.4231.declaration 
   public abstract void   		setYp2(int yp2);
   // ino.end
   // ino.method.setWidth.4234.description type=line
   // Diese Funktion setzt die Breite eines grafischen Grundelements.
   // ino.end
   // ino.method.setWidth.4234.declaration 
   public abstract void   		setWidth(int width);
   // ino.end
   // ino.method.setHeight.4237.description type=line
   // Diese Funktion setzt die Höhe eines grafischen Grundelements.
   // ino.end
   // ino.method.setHeight.4237.declaration 
   public abstract void   		setHeight(int height);
   // ino.end
   // ino.method.getX.4240.description type=line
   // Diese Funktion liefert die X-Position eines grafischen Grundelements zurück.
   // ino.end
   // ino.method.getX.4240.declaration 
   public abstract int   		getX();
   // ino.end
   // ino.method.getY.4243.description type=line
   // Diese Funktion liefert die Y-Position eines grafischen Grundelements zurück.
   // ino.end
   // ino.method.getY.4243.declaration 
   public abstract int   		getY();
   // ino.end
   // ino.method.getWidth.4246.description type=line
   // Diese Funktion liefert die Breite eines grafischen Grundelements zurück.
   // ino.end
   // ino.method.getWidth.4246.declaration 
   public abstract int   		getWidth();
   // ino.end
   // ino.method.getHeight.4249.description type=line
   // Diese Funktion liefert die Höhe eines grafischen Grundelements zurück.
   // ino.end
   // ino.method.getHeight.4249.declaration 
   public abstract int   		getHeight();
   // ino.end
   // ino.method.getShape.1701.description type=line
   // Diese Funktion liefert das Shape eines grafischen Grundelements zurück.
   // ino.end
   // ino.method.getShape.1701.declaration 
   public abstract SmShape   	getShape();
   // ino.end
   // ino.method.showShape.1704.description type=line
   // Diese Funktion macht das Shape eines grafischen Grundelements sichtbar.
   // ino.end
   // ino.method.showShape.1704.declaration 
   public abstract void   		showShape(Graphics g);
   // ino.end
   // ino.method.setIsSelected.1082.description type=line
   // Diese Funktion setzt den Selektstatus eines grafischen Grundelements.
   // ino.end
   // ino.method.setIsSelected.1082.declaration 
   public abstract void   		setIsSelected(boolean isselected);
   // ino.end
   // ino.method.getIsSelected.1707.description type=line
   // Diese Funktion gibt den Selektstatus eines grafischen Grundelements zurück.
   // ino.end
   // ino.method.getIsSelected.1707.declaration 
   public abstract boolean   	getIsSelected();
   // ino.end
   // ino.method.setGroupState.4252.description type=line
   // Diese Funktion setzt den Gruppenstatus eines grafischen Grundelements.
   // ino.end
   // ino.method.setGroupState.4252.declaration 
   public abstract void   		setGroupState(boolean isMemberOfGroup);
   // ino.end
   // ino.method.getGroupState.4255.description type=line
   // Diese Funktion liefert den Gruppenstatus eines grafischen Grundelements zurück.
   // ino.end
   // ino.method.getGroupState.4255.declaration 
   public abstract boolean   	getGroupState();
   // ino.end
   // ino.method.setIsVisible.4258.description type=line
   // Diese Funktion setzt die Sichtbarkeit eines grafischen Grundelements.
   // ino.end
   // ino.method.setIsVisible.4258.declaration 
   public abstract void   		setIsVisible(boolean isVisible);
   // ino.end
   // ino.method.setIsEditable.4261.description type=line
   // Diese Funktion bestimmt, ob ein grafisches Grundelement selektiert werden kann.
   // ino.end
   // ino.method.setIsEditable.4261.declaration 
   public abstract void   		setIsEditable(boolean iseditable);
   // ino.end
   // ino.method.getIsVisible.4264.description type=line
   // Diese Funktion liefert die Sichtbarkeit eines grafischen Grundelements zurück.
   // ino.end
   // ino.method.getIsVisible.4264.declaration 
   public abstract boolean   	getIsVisible();
   // ino.end
   // ino.method.getSelector.1710.description type=line
   // Diese Funktion gibt den Selektor eines grafischen Grundelements zurück.
   // ino.end
   // ino.method.getSelector.1710.declaration 
   public abstract SmSelector   getSelector();
   // ino.end
   // ino.method.setNameOfGraphicObject.1713.description type=line
   // Diese Funktion setzt den Namen eines grafischen Grundelements.
   // ino.end
   // ino.method.setNameOfGraphicObject.1713.declaration 
   public abstract void   		setNameOfGraphicObject(String objectName);
   // ino.end
   // ino.method.getNameOfGraphicObject.1716.description type=line
   // Diese Funktion liefert den Namen eines grafischen Grundelements zurück.
   // ino.end
   // ino.method.getNameOfGraphicObject.1716.declaration 
   public abstract String   	getNameOfGraphicObject();
   // ino.end
}
// ino.end





