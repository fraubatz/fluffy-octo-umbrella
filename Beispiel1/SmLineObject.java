import java.awt.*;
import java.io.*;


// ino.class.SmLineObject.1033.description type=line
// Die Klasse SmLineObject repräsentiert eine Linie und ist von der Klasse         
// SmGraphicObject abgeleitet. Die Linie hat als einziges Objekt einen anderen     
// Selektor und besitzt 2 Positionen(Anfang, Ende) die modifiziert werden können.
// ino.end
// ino.class.SmLineObject.1033.declaration 
public class SmLineObject extends SmGraphicObject implements Serializable
// ino.end
// ino.class.SmLineObject.1033.body
{
	// ino.attribute._x1.1773.description type=line
	// Dieses Attribut hält die 1. X-Position der Linie.
	// ino.end
	// ino.attribute._x1.1773.declaration 
	private int _x1;
	// ino.end
	// ino.attribute._x2.1776.description type=line
	// Dieses Attribut hält die 2. X-Position der Linie.
	// ino.end
	// ino.attribute._x2.1776.declaration 
	private int _x2;
	// ino.end
	// ino.attribute._y1.1779.description type=line
	// Dieses Attribut hält die 1. Y-Position der Linie.
	// ino.end
	// ino.attribute._y1.1779.declaration 
	private int _y1;
	// ino.end
	// ino.attribute._y2.1782.description type=line
	// Dieses Attribut hält die 2. Y-Position der Linie.
	// ino.end
	// ino.attribute._y2.1782.declaration 
	private int _y2;
	// ino.end
	// ino.attribute._width.4121.description type=line
	// Dieses Attribut hält die Breite einer Linie(Differenz zwischen 1. und 2.        
	// X-Position).
	// ino.end
	// ino.attribute._width.4121.declaration 
	private int _width;
	// ino.end
	// ino.attribute._height.4124.description type=line
	// Dieses Attribut hält die Höhe einer Linie(Differenz zwischen 1. und 2.          
	// Y-Position).
	// ino.end
	// ino.attribute._height.4124.declaration 
	private int _height;
	// ino.end
	private SmShape _shape;

	private SmSelector _selector;

	// ino.method.SmLineObject.4137.description type=line
	// Der Parameterkonstruktor setzt die Positionen, Höhe und Breite der Linie auf    
	// die Werte der ihm übergebenen Parameter. Es wird das Shape des Linie erzeugt.
	// ino.end
	// ino.method.SmLineObject.4137.definition 
	public SmLineObject(int x1,int y1,int x2,int y2,int width,int height)
	// ino.end
	// ino.method.SmLineObject.4137.body 
	{
		_x1		= x1;
		_y1		= y1;
		_x2		= x2;
		_y2		= y2;
		_width  = width;
		_height = height;
		_shape	= new SmShape(_x1 < _x2 ? _x1 : _x2,
							  _y1 < _y2 ? _y1 : _y2, _width, _height);
	}
	// ino.end

	// ino.method.SmLineObject.4140.description type=line
	// Der Copykonstruktor kopiert alle Variablen des übergebenen Objekts und          
	// verschiebt die Linie um den übergebenen Offset.
	// ino.end
	// ino.method.SmLineObject.4140.definition 
	public SmLineObject(SmLineObject copy,int offsetX,int offsetY)
	// ino.end
	// ino.method.SmLineObject.4140.body 
	{
		_x1		= copy._x1+offsetX;
		_y1		= copy._y1+offsetY;
		_x2		= copy._x2+offsetX;
		_y2		= copy._y2+offsetY;
		_width  = copy._width;
		_height = copy._height;
		_shape	= new SmShape(_x1 < _x2 ? _x1 : _x2,
							  _y1 < _y2 ? _y1 : _y2, _width, _height);
		_selector			=   copy._selector;
		_isEditable			=   copy._isEditable;
		_isMemberOfGroup	=   copy._isMemberOfGroup;
		_isVisible			=   copy._isVisible;
		_background			=   copy._background;
		_foreground			=   copy._foreground;
	}
	// ino.end
	
	// ino.method.setX.1798.description type=line
	// Diese Funktion setzt die X-Position, bezogen auf das Shape der Linie.
	// ino.end
	// ino.method.setX.1798.definition 
	public void setX(int x)
	// ino.end
	// ino.method.setX.1798.body 
	{
		int offsetX = _shape.getX()-x;
		_shape.setX(x);
		if (_x1 < _x2) 
		{
			_x1 = x;
			_x2 = _x2 - offsetX;
		}
		else 
		{
			_x2 = x;
			_x1 = _x1 - offsetX;
		}
	}
	// ino.end

	
	// ino.method.setY.1804.description type=line
	// Diese Funktion setzt die Y-Position, bezogen auf das Shape der Linie.
	// ino.end
	// ino.method.setY.1804.definition 
	public void setY(int y)
	// ino.end
	// ino.method.setY.1804.body 
	{
		int offsetY = _shape.getY()-y;
		_shape.setY(y);
		if (_y1 < _y2)
		{
			_y1 = y;
			_y2 = _y2 - offsetY;
		}
		else
		{
			_y2 = y;
			_y1 = _y1 - offsetY;
		}
	}
	// ino.end

	// ino.method.setXp1.4143.description type=line
	// Diese Funktion setzt die 1. X-Position der Linie.
	// ino.end
	// ino.method.setXp1.4143.definition 
	public void setXp1(int xp1)
	// ino.end
	// ino.method.setXp1.4143.body 
	{
		_x1 = xp1;
		_width = Math.abs(_x2 - _x1);
		_shape.setWidth(_width);

		if (_x1 < _x2) 
		{
			_shape.setX(_x1);
		}
		else 
		{
			_shape.setX(_x2);
		}
	}
	// ino.end

	// ino.method.setXp2.4146.description type=line
	// Diese Funktion setzt die 2. X-Position der Linie.
	// ino.end
	// ino.method.setXp2.4146.definition 
	public void setXp2(int xp2)
	// ino.end
	// ino.method.setXp2.4146.body 
	{
		_x2 = xp2;
		_width = Math.abs(_x2 - _x1);
		_shape.setWidth(_width);

		if (_x1 < _x2) 
		{
			_shape.setX(_x1);
		}
		else 
		{
			_shape.setX(_x2);
		}
	}
	// ino.end

	// ino.method.setYp1.4149.description type=line
	// Diese Funktion setzt die 1. Y-Position der Linie.
	// ino.end
	// ino.method.setYp1.4149.definition 
	public void setYp1(int yp1)
	// ino.end
	// ino.method.setYp1.4149.body 
	{
		_y1 = yp1;
		_height = Math.abs(_y2 - _y1);
		_shape.setHeight(_height);

		if (_y1 < _y2) 
		{
			_shape.setY(_y1);
		}
		else 
		{
			_shape.setY(_y2);
		}
	}
	// ino.end

	// ino.method.setYp2.4152.description type=line
	// Diese Funktion setzt die 2. Y-Position der Linie.
	// ino.end
	// ino.method.setYp2.4152.definition 
	public void setYp2(int yp2)
	// ino.end
	// ino.method.setYp2.4152.body 
	{
		_y2 = yp2;
		_height = Math.abs(_y2 - _y1);
		_shape.setHeight(_height);

		if (_y1 < _y2) 
		{
			_shape.setY(_y1);
		}
		else 
		{
			_shape.setY(_y2);
		}
	}
	// ino.end

    // ino.method.getX.4155.description type=line
    // Diese Funktion gibt die X-Position der Linie zurück(bezogen auf das Shape).
    // ino.end
    // ino.method.getX.4155.definition 
    public int getX()
    // ino.end
    // ino.method.getX.4155.body 
    {
	   return _shape.getX();
    }
    // ino.end

    // ino.method.getY.4158.description type=line
    // Diese Funktion gibt die Y-Position der Linie zurück(bezogen auf das Shape).
    // ino.end
    // ino.method.getY.4158.definition 
    public int getY()
    // ino.end
    // ino.method.getY.4158.body 
    {
	   return _shape.getY();
    }
    // ino.end

    // ino.method.getX1.4161.description type=line
    // Diese Funktion liefert die 1. X-Position der Linie zurück.
    // ino.end
    // ino.method.getX1.4161.definition 
    public int getX1()
    // ino.end
    // ino.method.getX1.4161.body 
    {
	   return _x1;
    }
    // ino.end

    // ino.method.getY1.4164.description type=line
    // Diese Funktion liefert die 1. Y-Position der Linie zurück.
    // ino.end
    // ino.method.getY1.4164.definition 
    public int getY1()
    // ino.end
    // ino.method.getY1.4164.body 
    {
	   return _y1;
    }
    // ino.end

    // ino.method.getX2.4167.description type=line
    // Diese Funktion liefert die 2. X-Position der Linie zurück.
    // ino.end
    // ino.method.getX2.4167.definition 
    public int getX2()
    // ino.end
    // ino.method.getX2.4167.body 
    {
	   return _x2;
    }
    // ino.end

    // ino.method.getY2.4170.description type=line
    // Diese Funktion liefert die 2. Y-Position der Linie zurück.
    // ino.end
    // ino.method.getY2.4170.definition 
    public int getY2()
    // ino.end
    // ino.method.getY2.4170.body 
    {
	   return _y2;
    }
    // ino.end

    // ino.method.setWidth.4173.description type=line
    // Diese Funktion setzt die Breite der Linie.
    // ino.end
    // ino.method.setWidth.4173.definition 
    public void setWidth(int width)
    // ino.end
    // ino.method.setWidth.4173.body 
    {
	    _width = width;

		if (_x1 < _x2) 
		{
			_x2 = _x1 + _width;
		}
		else 
		{
			_x1 = _x2 + _width;
		}

		_shape.setWidth(_width);
    }
    // ino.end

    // ino.method.setHeight.4176.description type=line
    // Diese Funktion setzt die Höhe der Linie.
    // ino.end
    // ino.method.setHeight.4176.definition 
    public void setHeight(int height)
    // ino.end
    // ino.method.setHeight.4176.body 
    {
	   _height = height;

	   if (_y1 < _y2) 
		{
			_y2 = _y1 + _height;
		}
		else 
		{
			_y1 = _y2 + _height;
		}
	  
	   _shape.setHeight(_height);
    }
    // ino.end

    // ino.method.getWidth.4179.description type=line
    // Diese Funktion gibt die Breite der Linie zurück.
    // ino.end
    // ino.method.getWidth.4179.definition 
    public int getWidth()
    // ino.end
    // ino.method.getWidth.4179.body 
    {
	   return _shape.getWidth();
    }
    // ino.end

    // ino.method.getHeight.4182.description type=line
    // Diese Funktion gibt die Höhe der Linie zurück.
    // ino.end
    // ino.method.getHeight.4182.definition 
    public int getHeight()
    // ino.end
    // ino.method.getHeight.4182.body 
    {
	   return _shape.getHeight();
    }
    // ino.end

    // ino.method.paint.1320.description type=line
    // Diese Funktion zeichnet die Linie.
    // ino.end
    // ino.method.paint.1320.definition 
    public void paint(Graphics g)
    // ino.end
    // ino.method.paint.1320.body 
    {
	   if (_isVisible)
	   {
			g.setColor(_foreground);
			g.drawLine(_x1, _y1, _x2, _y2);
			
			if (_isSelected) 
			{
				//showShape(g);
				_selector = new SmSelector(_x1, _y1, _x2, _y2);
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

    // ino.method.setIsSelected.1810.description type=line
    // Diese Funktion setzt den Selektstatus.
    // ino.end
    // ino.method.setIsSelected.1810.definition 
    public void setIsSelected(boolean isselected)
    // ino.end
    // ino.method.setIsSelected.1810.body 
    {
	   _isSelected = isselected;
    }
    // ino.end

    // ino.method.getIsSelected.1813.description type=line
    // Diese Funktion gibt den Selektstatus zurück.
    // ino.end
    // ino.method.getIsSelected.1813.definition 
    public boolean getIsSelected()
    // ino.end
    // ino.method.getIsSelected.1813.body 
    {
	   return _isSelected;
    }
    // ino.end

    // ino.method.setGroupState.4185.description type=line
    // Diese Funktion setzt den Gruppenstatus der Linie.
    // ino.end
    // ino.method.setGroupState.4185.definition 
    public void setGroupState(boolean isMemberOfGroup)
    // ino.end
    // ino.method.setGroupState.4185.body 
    {
	   _isMemberOfGroup = isMemberOfGroup;
    }
    // ino.end

    // ino.method.getGroupState.4188.description type=line
    // Diese Funktion gibt den Gruppenstatus der Linie zurück.
    // ino.end
    // ino.method.getGroupState.4188.definition 
    public boolean getGroupState()
    // ino.end
    // ino.method.getGroupState.4188.body 
    {
	   return _isMemberOfGroup;
    }
    // ino.end

    // ino.method.contains.1317.description type=line
    // Diese Funktion prüft, ob der Mauszeiger sich innerhalb des Shapes der Linie     
    // befindet.
    // ino.end
    // ino.method.contains.1317.definition 
    public boolean contains(int x,int y)
    // ino.end
    // ino.method.contains.1317.body 
    {
	   if ((x <= (_shape.getX() + _shape.getWidth())) & (x >= _shape.getX()) & (y <= (_shape.getY() + _shape.getHeight())) & (y >= _shape.getY())) return true;
	   else return false;
    }
    // ino.end

    // ino.method.getShape.1816.description type=line
    // Diese Funktion gibt das Shape der Linie zurück.
    // ino.end
    // ino.method.getShape.1816.definition 
    public SmShape getShape()
    // ino.end
    // ino.method.getShape.1816.body 
    {
	   return _shape;
    }
    // ino.end

    // ino.method.showShape.1819.description type=line
    // Diese Funktion macht das Shape der Linie sichtbar.
    // ino.end
    // ino.method.showShape.1819.definition 
    public void showShape(Graphics g)
    // ino.end
    // ino.method.showShape.1819.body 
    {
	   _shape.paint(g);
    }
    // ino.end

    // ino.method.getSelector.1822.description type=line
    // Diese Funktion liefert den Selektor der Linie zurück.
    // ino.end
    // ino.method.getSelector.1822.definition 
    public SmSelector getSelector()
    // ino.end
    // ino.method.getSelector.1822.body 
    {
	   return _selector;
    }
    // ino.end

    // ino.method.setNameOfGraphicObject.1825.description type=line
    // Diese Funktion setzt den Namen der Linie.
    // ino.end
    // ino.method.setNameOfGraphicObject.1825.definition 
    public void setNameOfGraphicObject(String _objectName)
    // ino.end
    // ino.method.setNameOfGraphicObject.1825.body 
    {
	   _nameOfGraphicObject = _objectName;
    }
    // ino.end

    // ino.method.getNameOfGraphicObject.1828.description type=line
    // Diese Funktion gibt den Namen der Linie zurück.
    // ino.end
    // ino.method.getNameOfGraphicObject.1828.definition 
    public String getNameOfGraphicObject()
    // ino.end
    // ino.method.getNameOfGraphicObject.1828.body 
    {
	  return _nameOfGraphicObject;
    }
    // ino.end

    // ino.method.setIsVisible.4191.description type=line
    // Diese Funktion setzt die Sichtbarkeit der Linie.
    // ino.end
    // ino.method.setIsVisible.4191.definition 
    public void setIsVisible(boolean isVisible)
    // ino.end
    // ino.method.setIsVisible.4191.body 
    {
	   _isVisible = isVisible;
    }
    // ino.end

    // ino.method.setIsEditable.4194.description type=line
    // Diese Funktion bestimmt, ob eine Linie selektiert werden kann.
    // ino.end
    // ino.method.setIsEditable.4194.definition 
    public void setIsEditable(boolean iseditable)
    // ino.end
    // ino.method.setIsEditable.4194.body 
    {
	   _isEditable = iseditable;
    }
    // ino.end

    // ino.method.getIsVisible.4197.description type=line
    // Diese Funktion gibt die Sichtbarkeit der Linie zurück.
    // ino.end
    // ino.method.getIsVisible.4197.definition 
    public boolean getIsVisible()
    // ino.end
    // ino.method.getIsVisible.4197.body 
    {
	   return _isVisible;
    }
    // ino.end

    // ino.method.setFont.4200.description type=line
    // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.setFont.4200.definition 
    public void   setFont(Font font,FontMetrics metrics)
    // ino.end
    // ino.method.setFont.4200.body 
    {;}
    // ino.end
    // ino.method.setText.4203.description type=line
    // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.setText.4203.definition 
    public void setText(String text)
    // ino.end
    // ino.method.setText.4203.body 
    {;}
    // ino.end
}
// ino.end




