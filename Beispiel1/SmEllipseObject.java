import java.awt.*;
import SmShape;
import java.io.*;


// ino.class.SmEllipseObject.1036.description type=line
// Die Klasse SmEllipseObject repräsentiert eine Ellipse und ist von der Klasse    
// SmGraphicObject abgeleitet.
// ino.end
// ino.class.SmEllipseObject.1036.declaration 
public class SmEllipseObject extends SmGraphicObject implements Serializable
// ino.end
// ino.class.SmEllipseObject.1036.body
{
	// ino.attribute._x.1243.description type=line
	// Dieses Attribut hält die X-Position einer Ellipse.
	// ino.end
	// ino.attribute._x.1243.declaration 
	private int _x;
	// ino.end

	// ino.attribute._y.1246.description type=line
	// Dieses Attribut hält die Y-Position einer Ellipse.
	// ino.end
	// ino.attribute._y.1246.declaration 
	private int _y;
	// ino.end

	// ino.attribute._width.1249.description type=line
	// Dieses Attribut hält die Breite einer Ellipse.
	// ino.end
	// ino.attribute._width.1249.declaration 
	private int _width;
	// ino.end

	// ino.attribute._height.1252.description type=line
	// Dieses Attribut hält die Höhe einer Ellipse.
	// ino.end
	// ino.attribute._height.1252.declaration 
	private int _height;
	// ino.end

	// ino.attribute._isFilled.1255.description type=line
	// Dieses Flag ist gesetzt(TRUE), wenn für die Ellipse eine Füllfarbe existiert.
	// ino.end
	// ino.attribute._isFilled.1255.declaration 
	private boolean _isFilled;
	// ino.end

	// ino.attribute._fillColor.1258.description type=line
	// Dieses Attribut hält die Füllfarbe einer Ellipse.
	// ino.end
	// ino.attribute._fillColor.1258.declaration 
	private Color _fillColor;
	// ino.end

	private SmShape _shape;

	private SmRectSelector _selector;
	
	// ino.method.SmEllipseObject.1261.description type=line
	// Der Parameterkonstruktor setzt die Position, Höhe und Breite der Ellipse auf    
	// die Werte der ihm übergebenen Parameter. Es wird das Shape der Ellipse erzeugt  
	// und eine weiße Füllfarbe definiert.
	// ino.end
	// ino.method.SmEllipseObject.1261.definition 
	public SmEllipseObject(int x,int y,int width,int height)
	// ino.end
	// ino.method.SmEllipseObject.1261.body 
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

    // ino.method.SmEllipseObject.4071.description type=line
    // Der Copykonstruktor kopiert alle Variablen des übergebenen Objekts und          
    // verschiebt die Ellipse um den übergebenen Offset.
    // ino.end
    // ino.method.SmEllipseObject.4071.definition 
    public SmEllipseObject(SmEllipseObject copy,int offsetX,int offsetY)
    // ino.end
	// ino.method.SmEllipseObject.4071.body 
	{
		_x			=	copy._x+offsetX;
		_y			=	copy._y+offsetY;
		_width		=	copy._width;
		_height		=	copy._height;
		_isFilled	=	copy._isFilled;
		_fillColor	=	copy._fillColor;
		_shape		=	new SmShape(_x, _y, _width, _height);
		_selector	=   copy._selector;
		_isEditable =   copy._isEditable;
		_isMemberOfGroup   =   copy._isMemberOfGroup;
		_isVisible  =   copy._isVisible;
		_background =   copy._background;
		_foreground =   copy._foreground;
		_isFilled	=   true;
	}
	// ino.end

	// ino.method.setX.1305.description type=line
	// Diese Funktion setzt die X-Position der Ellipse.
	// ino.end
	// ino.method.setX.1305.definition 
	public void setX(int x)
	// ino.end
	// ino.method.setX.1305.body 
	{
		_x = x;
		_shape.setX(x);
	}
	// ino.end

	// ino.method.setY.1308.description type=line
	// Diese Funktion setzt die Y-Position der Ellipse.
	// ino.end
	// ino.method.setY.1308.definition 
	public void setY(int y)
	// ino.end
	// ino.method.setY.1308.body 
	{
		_y = y;
		_shape.setY(y);
	}
	// ino.end

    // ino.method.setWidth.1311.description type=line
    // Diese Funktion setzt die Breite der Ellipse.
    // ino.end
    // ino.method.setWidth.1311.definition 
    public void setWidth(int width)
    // ino.end
    // ino.method.setWidth.1311.body 
    {
	   if(width > 0) _width = width;
	   _shape.setWidth(_width);
    }
    // ino.end
 
    // ino.method.setHeight.1314.description type=line
    // Diese Funktion setzt die Höhe der Ellipse.
    // ino.end
    // ino.method.setHeight.1314.definition 
    public void setHeight(int height)
    // ino.end
    // ino.method.setHeight.1314.body 
    {
	   if(height > 0) _height = height;
	   _shape.setHeight(_height);
    }
    // ino.end

    // ino.method.getX.4074.description type=line
    // Diese Funktion gibt die X-Position der Ellipse zurück.
    // ino.end
    // ino.method.getX.4074.definition 
    public int getX()
    // ino.end
    // ino.method.getX.4074.body 
    {
	   return _x;
    }
    // ino.end

    // ino.method.getY.4077.description type=line
    // Diese Funktion gibt die Y-Position der Ellipse zurück.
    // ino.end
    // ino.method.getY.4077.definition 
    public int getY()
    // ino.end
    // ino.method.getY.4077.body 
    {
 	   return _y;
    }
    // ino.end

    // ino.method.getWidth.4080.description type=line
    // Diese Funktion gibt die Breite der Ellipse zurück.
    // ino.end
    // ino.method.getWidth.4080.definition 
    public int getWidth()
    // ino.end
    // ino.method.getWidth.4080.body 
    {
	   return _width;
    }
    // ino.end

    // ino.method.getHeight.4083.description type=line
    // Diese Funktion gibt die Höhe der Ellipse zurück.
    // ino.end
    // ino.method.getHeight.4083.definition 
    public int getHeight()
    // ino.end
    // ino.method.getHeight.4083.body 
    {
	   return _height;
    }
    // ino.end

    // ino.method.paint.1264.description type=line
    // Diese Funktion zeichnet die Ellipse.
    // ino.end
    // ino.method.paint.1264.definition 
    public void paint(Graphics g)
    // ino.end
    // ino.method.paint.1264.body 
    {
	   if (_isVisible)
	   {
			g.setColor(_foreground);
			g.drawOval(_x, _y, _width, _height);
			g.setColor(_background);
			if (_isFilled)
			{
				g.fillOval(_x+_lineWidth, _y+_lineWidth, _width-_lineWidth, _height-_lineWidth);
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

    // ino.method.setIsSelected.1668.description type=line
    // Diese Funktion setzt den Selektstatus.
    // ino.end
    // ino.method.setIsSelected.1668.definition 
    public void setIsSelected(boolean isselected)
    // ino.end
    // ino.method.setIsSelected.1668.body 
    {
	   _isSelected = isselected;
    }
    // ino.end

    // ino.method.getIsSelected.1671.description type=line
    // Diese Funktion gibt den Selektstatus zurück.
    // ino.end
    // ino.method.getIsSelected.1671.definition 
    public boolean getIsSelected()
    // ino.end
    // ino.method.getIsSelected.1671.body 
    {
	   return _isSelected;
    }
    // ino.end

    // ino.method.setGroupState.4086.description type=line
    // Diese Funktion setzt den Gruppenstatus der Ellipse.
    // ino.end
    // ino.method.setGroupState.4086.definition 
    public void setGroupState(boolean isMemberOfGroup)
    // ino.end
    // ino.method.setGroupState.4086.body 
    {
	   _isMemberOfGroup = isMemberOfGroup;
    }
    // ino.end

    // ino.method.getGroupState.4089.description type=line
    // Diese Funktion gibt den Gruppenstatus der Ellipse zurück.
    // ino.end
    // ino.method.getGroupState.4089.definition 
    public boolean getGroupState()
    // ino.end
    // ino.method.getGroupState.4089.body 
    {
	   return _isMemberOfGroup;
    }
    // ino.end

    // ino.method.contains.1299.description type=line
    // Diese Funktion prüft, ob der Mauszeiger sich innerhalb der Ellipse befindet.
    // ino.end
    // ino.method.contains.1299.definition 
    public boolean contains(int x,int y)
    // ino.end
    // ino.method.contains.1299.body 
    {
	   if ((x <= (_x + _width)) & (x >= _x) & (y <= (_y + _height)) & (y >= _y)) return true;
	   else return false;
    }
    // ino.end

    // ino.method.getShape.1674.description type=line
    // Diese Funktion gibt das Shape der Ellipse zurück.
    // ino.end
    // ino.method.getShape.1674.definition 
    public SmShape getShape()
    // ino.end
    // ino.method.getShape.1674.body 
    {
	   return _shape;
    }
    // ino.end

    // ino.method.showShape.1677.description type=line
    // Diese Funktion macht das Shape der Ellipse sichtbar.
    // ino.end
    // ino.method.showShape.1677.definition 
    public void showShape(Graphics g)
    // ino.end
    // ino.method.showShape.1677.body 
    {
	   _shape.paint(g);
    }
    // ino.end

    // ino.method.getSelector.1680.description type=line
    // Diese Funktion liefert den Selektor der Ellipse zurück.
    // ino.end
    // ino.method.getSelector.1680.definition 
    public SmSelector getSelector()
    // ino.end
    // ino.method.getSelector.1680.body 
    {
	   return _selector;
    }
    // ino.end

    // ino.method.setNameOfGraphicObject.1683.description type=line
    // Diese Funktion setzt den Namen der Ellipse.
    // ino.end
    // ino.method.setNameOfGraphicObject.1683.definition 
    public void setNameOfGraphicObject(String _objectName)
    // ino.end
    // ino.method.setNameOfGraphicObject.1683.body 
    {
	   _nameOfGraphicObject = _objectName;
    }
    // ino.end

    // ino.method.getNameOfGraphicObject.1686.description type=line
    // Diese Funktion gibt den Namen der Ellipse zurück.
    // ino.end
    // ino.method.getNameOfGraphicObject.1686.definition 
    public String getNameOfGraphicObject()
    // ino.end
    // ino.method.getNameOfGraphicObject.1686.body 
    {
	  return _nameOfGraphicObject;
    }
    // ino.end

    // ino.method.setIsVisible.4092.description type=line
    // Diese Funktion setzt die Sichtbarkeit der Ellipse.
    // ino.end
    // ino.method.setIsVisible.4092.definition 
    public void setIsVisible(boolean isVisible)
    // ino.end
    // ino.method.setIsVisible.4092.body 
    {
	   _isVisible = isVisible;
    }
    // ino.end

    // ino.method.setIsEditable.4095.description type=line
    // Diese Funktion bestimmt, ob eine Ellipse selektiert werden kann.
    // ino.end
    // ino.method.setIsEditable.4095.definition 
    public void setIsEditable(boolean iseditable)
    // ino.end
    // ino.method.setIsEditable.4095.body 
    {
	   _isEditable = iseditable;
    }
    // ino.end

    // ino.method.getIsVisible.4098.description type=line
    // Diese Funktion gibt die Sichtbarkeit der Ellipse zurück.
    // ino.end
    // ino.method.getIsVisible.4098.definition 
    public boolean getIsVisible()
    // ino.end
    // ino.method.getIsVisible.4098.body 
    {
	   return _isVisible;
    }
    // ino.end

    // ino.method.setFont.4101.description type=line
    // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.setFont.4101.definition 
    public void setFont(Font font,FontMetrics metrics)
    // ino.end
    // ino.method.setFont.4101.body 
    {;}
    // ino.end
    // ino.method.setText.4104.description type=line
    // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.setText.4104.definition 
    public void setText(String text)
    // ino.end
    // ino.method.setText.4104.body 
    {;}
    // ino.end
    // ino.method.setXp1.4107.description type=line
    // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.setXp1.4107.definition 
    public void setXp1(int xp1)
    // ino.end
    // ino.method.setXp1.4107.body 
    {;}
    // ino.end
    // ino.method.setXp2.4110.description type=line
    // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.setXp2.4110.definition 
    public void setXp2(int xp2)
    // ino.end
    // ino.method.setXp2.4110.body 
    {;}
    // ino.end
    // ino.method.setYp1.4113.description type=line
    // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.setYp1.4113.definition 
    public void setYp1(int yp1)
    // ino.end
    // ino.method.setYp1.4113.body 
    {;}
    // ino.end
    // ino.method.setYp2.4116.description type=line
    // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.setYp2.4116.definition 
    public void setYp2(int yp2)
    // ino.end
    // ino.method.setYp2.4116.body 
    {;}
    // ino.end
}
// ino.end




