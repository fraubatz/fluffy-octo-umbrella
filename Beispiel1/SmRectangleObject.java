import java.awt.*;
import java.io.*;


// ino.class.SmRectangleObject.1030.description type=line
// Die Klasse SmRectangleObject repräsentiert ein Rechteck und ist von der Klasse  
// SmGraphicObject abgeleitet.
// ino.end
// ino.class.SmRectangleObject.1030.declaration 
public class SmRectangleObject extends SmGraphicObject implements Serializable
// ino.end
// ino.class.SmRectangleObject.1030.body
{
   // ino.attribute._x.1088.description type=line
   // Dieses Attribut hält die X-Position eines Rechtecks.
   // ino.end
   // ino.attribute._x.1088.declaration 
   private int _x;
   // ino.end
   
   // ino.attribute._y.1091.description type=line
   // Dieses Attribut hält die Y-Position eines Rechtecks.
   // ino.end
   // ino.attribute._y.1091.declaration 
   private int _y;
   // ino.end
   
   // ino.attribute._width.1094.description type=line
   // Dieses Attribut hält die Breite eines Rechtecks.
   // ino.end
   // ino.attribute._width.1094.declaration 
   private int _width;
   // ino.end
   
   // ino.attribute._height.1097.description type=line
   // Dieses Attribut hält die Höhe eines Rechtecks.
   // ino.end
   // ino.attribute._height.1097.declaration 
   private int _height;
   // ino.end
   
   // ino.attribute._isFilled.1120.description type=line
   // Dieses Flag ist gesetzt(TRUE), wenn für das Rechteck eine Füllfarbe existiert.
   // ino.end
   // ino.attribute._isFilled.1120.declaration 
   private boolean _isFilled;
   // ino.end
  
   // ino.attribute._fillColor.1117.description type=line
   // Dieses Attribut hält die Füllfarbe eines Rechtecks.
   // ino.end
   // ino.attribute._fillColor.1117.declaration 
   private Color _fillColor;
   // ino.end
 
   private SmShape _shape;

   private SmRectSelector _selector;
      
   // ino.method.SmRectangleObject.1276.description type=line
   // Der Parameterkonstruktor setzt die Position, Höhe und Breite des Rechtecks auf  
   // die Werte der ihm übergebenen Parameter. Es wird das Shape des Rechtecks erzeugt
   // und eine weiße Füllfarbe definiert.
   // ino.end
   // ino.method.SmRectangleObject.1276.definition 
   public SmRectangleObject(int x,int y,int width,int height)
   // ino.end
   // ino.method.SmRectangleObject.1276.body 
   {
	   _x = x;
	   _y = y;
	   _height = height;
	   _width = width;
	   _isFilled = true;
	   _fillColor = Color.white;
	   _shape = new SmShape(_x, _y, _width, _height);
   }
   // ino.end

   // ino.method.SmRectangleObject.4334.description type=line
   // Der Copykonstruktor kopiert alle Variablen des übergebenen Objekts und          
   // verschiebt das Rechteck um den übergebenen Offset.
   // ino.end
   // ino.method.SmRectangleObject.4334.definition 
   public SmRectangleObject(SmRectangleObject copy,int offsetX,int offsetY)
   // ino.end
   // ino.method.SmRectangleObject.4334.body 
   {
		_x			=	copy._x+offsetX;
		_y			=	copy._y+offsetY;
		_width		=	copy._width;
		_height		=	copy._height;
		_isFilled	=	copy._isFilled;
		_fillColor	=	copy._fillColor;
		_shape		= new SmShape(_x, _y, _width, _height);
		_selector	=   copy._selector;
		_isEditable =   copy._isEditable;
		_isMemberOfGroup   =   copy._isMemberOfGroup;
		_isVisible  =   copy._isVisible;
		_background =   copy._background;
		_foreground =   copy._foreground;
		_isFilled   =   true;
   }
   // ino.end

   // ino.method.setX.1100.description type=line
   // Diese Funktion setzt die X-Position des Rechtecks.
   // ino.end
   // ino.method.setX.1100.definition 
   public void setX(int x)
   // ino.end
   // ino.method.setX.1100.body 
   {
	   _x = x;
	   _shape.setX(x);
   }
   // ino.end
  
   // ino.method.setY.1103.description type=line
   // Diese Funktion setzt die Y-Position des Rechtecks.
   // ino.end
   // ino.method.setY.1103.definition 
   public void setY(int y)
   // ino.end
   // ino.method.setY.1103.body 
   {
	   _y = y;
	   _shape.setY(y);
   }
   // ino.end
   
   // ino.method.setWidth.1106.description type=line
   // Diese Funktion setzt die Breite des Rechtecks.
   // ino.end
   // ino.method.setWidth.1106.definition 
   public void setWidth(int width)
   // ino.end
   // ino.method.setWidth.1106.body 
   {
	   if(width > 0) _width = width;
	   _shape.setWidth(_width);
   }
   // ino.end
  
   // ino.method.setHeight.1109.description type=line
   // Diese Funktion setzt die Höhe des Rechtecks.
   // ino.end
   // ino.method.setHeight.1109.definition 
   public void setHeight(int height)
   // ino.end
   // ino.method.setHeight.1109.body 
   {
	   if(height > 0) _height = height;
	   _shape.setHeight(_height);
   }
   // ino.end
  
   // ino.method.getX.4337.description type=line
   // Diese Funktion gibt die X-Position des Rechtecks zurück.
   // ino.end
   // ino.method.getX.4337.definition 
   public int getX()
   // ino.end
   // ino.method.getX.4337.body 
   {
	   return _x;
   }
   // ino.end

   // ino.method.getY.4340.description type=line
   // Diese Funktion gibt die Y-Position des Rechtecks zurück.
   // ino.end
   // ino.method.getY.4340.definition 
   public int getY()
   // ino.end
   // ino.method.getY.4340.body 
   {
	   return _y;
   }
   // ino.end

   // ino.method.getWidth.4343.description type=line
   // Diese Funktion gibt die Breite des Rechtecks zurück.
   // ino.end
   // ino.method.getWidth.4343.definition 
   public int getWidth()
   // ino.end
   // ino.method.getWidth.4343.body 
   {
	   return _width;
   }
   // ino.end

   // ino.method.getHeight.4346.description type=line
   // Diese Funktion gibt die Höhe des Rechtecks zurück.
   // ino.end
   // ino.method.getHeight.4346.definition 
   public int getHeight()
   // ino.end
   // ino.method.getHeight.4346.body 
   {
	   return _height;
   }
   // ino.end

   // ino.method.paint.1279.description type=line
   // Diese Funktion zeichnet das Rechteck.
   // ino.end
   // ino.method.paint.1279.definition 
   public void paint(Graphics g)
   // ino.end
   // ino.method.paint.1279.body 
   {
	   if (_isVisible)
	   {
			g.setColor(_foreground);
			g.drawRect(_x, _y, _width, _height);
			g.setColor(_background);
			if (_isFilled)
			{
				g.fillRect(_x+_lineWidth, _y+_lineWidth, _width-_lineWidth, _height-_lineWidth);
			}
			if (_isSelected) 
			{
				//showShape(g);
				_selector = new SmRectSelector(_shape.getX(),_shape.getY(),
				 					       _shape.getWidth(),_shape.getHeight());
				_selector.paintSelector(g);
			}
			if (_isEditable)
			{
			    showShape(g);
			}
	   }
	   return;
   }
   // ino.end

   // ino.method.setIsSelected.1843.description type=line
   // Diese Funktion setzt den Selektstatus.
   // ino.end
   // ino.method.setIsSelected.1843.definition 
   public void setIsSelected(boolean isselected)
   // ino.end
   // ino.method.setIsSelected.1843.body 
   {
	   _isSelected = isselected;
   }
   // ino.end

   // ino.method.getIsSelected.1846.description type=line
   // Diese Funktion gibt den Selektstatus zurück.
   // ino.end
   // ino.method.getIsSelected.1846.definition 
   public boolean getIsSelected()
   // ino.end
   // ino.method.getIsSelected.1846.body 
   {
	   return _isSelected;
   }
   // ino.end

   // ino.method.setGroupState.4349.description type=line
   // Diese Funktion setzt den Gruppenstatus des Rechtecks.
   // ino.end
   // ino.method.setGroupState.4349.definition 
   public void setGroupState(boolean isMemberOfGroup)
   // ino.end
   // ino.method.setGroupState.4349.body 
   {
	   _isMemberOfGroup = isMemberOfGroup;
   }
   // ino.end

   // ino.method.getGroupState.4352.description type=line
   // Diese Funktion gibt den Gruppenstatus des Rechtecks zurück.
   // ino.end
   // ino.method.getGroupState.4352.definition 
   public boolean getGroupState()
   // ino.end
   // ino.method.getGroupState.4352.body 
   {
	   return _isMemberOfGroup;
   }
   // ino.end

   // ino.method.contains.1302.description type=line
   // Diese Funktion prüft, ob der Mauszeiger sich innerhalb des Rechtecks befindet.
   // ino.end
   // ino.method.contains.1302.definition 
   public boolean contains(int x,int y)
   // ino.end
   // ino.method.contains.1302.body 
   {
	   if ((x <= (_x + _width)) & (x >= _x) & (y <= (_y + _height)) & (y >= _y)) return true;
	   else return false;
   }
   // ino.end

   // ino.method.getShape.1849.description type=line
   // Diese Funktion gibt das Shape des Rechtecks zurück.
   // ino.end
   // ino.method.getShape.1849.definition 
   public SmShape getShape()
   // ino.end
   // ino.method.getShape.1849.body 
   {
	   return _shape;
   }
   // ino.end

   // ino.method.showShape.1852.description type=line
   // Diese Funktion macht das Shape des Rechtecks sichtbar.
   // ino.end
   // ino.method.showShape.1852.definition 
   public void showShape(Graphics g)
   // ino.end
   // ino.method.showShape.1852.body 
   {
	   _shape.paint(g);
   }
   // ino.end

   // ino.method.getSelector.1855.description type=line
   // Diese Funktion liefert den Selektor des Rechtecks zurück.
   // ino.end
   // ino.method.getSelector.1855.definition 
   public SmSelector getSelector()
   // ino.end
   // ino.method.getSelector.1855.body 
   {
	   return _selector;
   }
   // ino.end

   // ino.method.setNameOfGraphicObject.1858.description type=line
   // Diese Funktion setzt den Namen des Rechtecks.
   // ino.end
   // ino.method.setNameOfGraphicObject.1858.definition 
   public void setNameOfGraphicObject(String objectName)
   // ino.end
   // ino.method.setNameOfGraphicObject.1858.body 
   {
	   _nameOfGraphicObject = objectName;
   }
   // ino.end

   // ino.method.getNameOfGraphicObject.1861.description type=line
   // Diese Funktion gibt den Namen des Rechtecks zurück.
   // ino.end
   // ino.method.getNameOfGraphicObject.1861.definition 
   public String getNameOfGraphicObject()
   // ino.end
   // ino.method.getNameOfGraphicObject.1861.body 
   {
	  return _nameOfGraphicObject;
   }
   // ino.end

   // ino.method.setIsVisible.4355.description type=line
   // Diese Funktion setzt die Sichtbarkeit des Rechtecks.
   // ino.end
   // ino.method.setIsVisible.4355.definition 
   public void setIsVisible(boolean isVisible)
   // ino.end
   // ino.method.setIsVisible.4355.body 
   {
	   _isVisible = isVisible;
   }
   // ino.end

   // ino.method.setIsEditable.4358.description type=line
   // Diese Funktion bestimmt, ob ein Rechteck selektiert werden kann.
   // ino.end
   // ino.method.setIsEditable.4358.definition 
   public void setIsEditable(boolean iseditable)
   // ino.end
   // ino.method.setIsEditable.4358.body 
   {
	   _isEditable = iseditable;
   }
   // ino.end

   // ino.method.getIsVisible.4361.description type=line
   // Diese Funktion gibt die Sichtbarkeit des Rechtecks zurück.
   // ino.end
   // ino.method.getIsVisible.4361.definition 
   public boolean getIsVisible()
   // ino.end
   // ino.method.getIsVisible.4361.body 
   {
	   return _isVisible;
   }
   // ino.end

   // ino.method.setFont.4364.description type=line
   // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden da sie   
   // in der abstrakten Basisklasse deklariert wurde.
   // ino.end
   // ino.method.setFont.4364.definition 
   public void setFont(Font font,FontMetrics metrics)
   // ino.end
   // ino.method.setFont.4364.body 
   {;}
   // ino.end
   // ino.method.setText.4367.description type=line
   // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden da sie   
   // in der abstrakten Basisklasse deklariert wurde.
   // ino.end
   // ino.method.setText.4367.definition 
   public void setText(String text)
   // ino.end
   // ino.method.setText.4367.body 
   {;}
   // ino.end
   // ino.method.setXp1.4370.description type=line
   // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden da sie   
   // in der abstrakten Basisklasse deklariert wurde.
   // ino.end
   // ino.method.setXp1.4370.definition 
   public void setXp1(int xp1)
   // ino.end
   // ino.method.setXp1.4370.body 
   {;}
   // ino.end
   // ino.method.setXp2.4373.description type=line
   // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden da sie   
   // in der abstrakten Basisklasse deklariert wurde.
   // ino.end
   // ino.method.setXp2.4373.definition 
   public void setXp2(int xp2)
   // ino.end
   // ino.method.setXp2.4373.body 
   {;}
   // ino.end
   // ino.method.setYp1.4376.description type=line
   // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden da sie   
   // in der abstrakten Basisklasse deklariert wurde.
   // ino.end
   // ino.method.setYp1.4376.definition 
   public void setYp1(int yp1)
   // ino.end
   // ino.method.setYp1.4376.body 
   {;}
   // ino.end
   // ino.method.setYp2.4379.description type=line
   // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden da sie   
   // in der abstrakten Basisklasse deklariert wurde.
   // ino.end
   // ino.method.setYp2.4379.definition 
   public void setYp2(int yp2)
   // ino.end
   // ino.method.setYp2.4379.body 
   {;}
   // ino.end
}
// ino.end







