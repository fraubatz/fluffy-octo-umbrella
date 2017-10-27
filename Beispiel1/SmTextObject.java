import java.awt.*;
import SmShape;
import java.io.*;


// ino.class.SmTextObject.1039.description type=line
// Die Klasse SmTextObject ist von SmObjekt abgeleitet und beschreibt ein          
// statisches Textfeld.
// ino.end
// ino.class.SmTextObject.1039.declaration 
public class SmTextObject extends SmGraphicObject implements Serializable
// ino.end
// ino.class.SmTextObject.1039.body
{
	// ino.attribute._x.4868.description type=line
	// Dieses Attribut hält die X-Position des Textfeldes.
	// ino.end
	// ino.attribute._x.4868.declaration 
	private int _x;
	// ino.end
	// ino.attribute._y.4871.description type=line
	// Dieses Attribut hält die Y-Position des Textfeldes.
	// ino.end
	// ino.attribute._y.4871.declaration 
	private int _y;
	// ino.end
	// ino.attribute._width.4874.description type=line
	// Dieses Attribut hält die Breite des Textfeldes.
	// ino.end
	// ino.attribute._width.4874.declaration 
	private int _width;
	// ino.end
	// ino.attribute._height.4877.description type=line
	// Dieses Attribut hält die Höhe des Textfeldes.
	// ino.end
	// ino.attribute._height.4877.declaration 
	private int _height;
	// ino.end
	// ino.attribute._text.4880.description type=line
	// Dieses Attribut beschreibt den Text des Textfeldes.
	// ino.end
	// ino.attribute._text.4880.declaration 
	private String _text;
	// ino.end
	// ino.attribute._font.4883.description type=line
	// Dieses Attribut beschreibt den Zeichensatz des Textes.
	// ino.end
	// ino.attribute._font.4883.declaration 
	private Font _font;
	// ino.end
	// ino.attribute._isFilled.4886.description type=line
	// Dieses Flag ist gesetzt(TRUE), wenn für das Textfeld eine Füllfarbe existiert.
	// ino.end
	// ino.attribute._isFilled.4886.declaration 
	private boolean _isFilled;
	// ino.end
	// ino.attribute._shape.4889.description type=line
	// Dieses Attribut beschreibt den Rahmen des Textfeldes.
	// ino.end
	// ino.attribute._shape.4889.declaration 
	private SmShape _shape;
	// ino.end

	private SmRectSelector _selector;
	// ino.attribute._metrics.4899.description type=line
	// Dieses Attribut beschreibt die Metrik des aktuellen Zeichensatzes
	// ino.end
	// ino.attribute._metrics.4899.declaration 
	private FontMetrics _metrics;
	// ino.end

	// ino.method.SmTextObject.4902.description type=line
	// Der Parameterkonstruktor setzt alle Attribute auf die als Parameter übergebenen 
	// Werte und berechnet die Abmessungen des Textfeldes.
	// ino.end
	// ino.method.SmTextObject.4902.definition 
	public SmTextObject(String text,Font font,FontMetrics metrics,int  x,int y)
	// ino.end
	// ino.method.SmTextObject.4902.body 
	{
		_x			= x;
		_y			= y;
		_text		= text;
		_font		= font;
		_metrics    = metrics;
		_isFilled	= true;
		_width  = _metrics.stringWidth(_text);
		_height = _metrics.getHeight();
		_shape = new SmShape(_x, _y, _width, _height);
		setMetrics();
	}
	// ino.end

	// ino.method.SmTextObject.4905.description type=line
	// Der Copykonstruktor kopiert alle Variablen des übergebenen Objekts und          
	// verschiebt das Textfeld um den übergebenen Offset.
	// ino.end
	// ino.method.SmTextObject.4905.definition 
	public SmTextObject(SmTextObject copy,int offsetX,int offsetY)
	// ino.end
	// ino.method.SmTextObject.4905.body 
	{
		_x			= copy._x+offsetX;
		_y			= copy._y+offsetY;
		_text		= copy._text;
		_font		= copy._font;
		_metrics    = copy._metrics;
		_isFilled	= true;
		_width  = _metrics.stringWidth(_text);
		_height = _metrics.getHeight();
		_shape = new SmShape(_x, _y, _width, _height);
		setMetrics();
		_isFilled	=   true;
	}
	// ino.end

    // ino.method.paint.1326.description type=line
    // Diese Funktion zeichnet den Text.
    // ino.end
    // ino.method.paint.1326.definition 
    public void paint(Graphics g)
    // ino.end
	// ino.method.paint.1326.body 
	{
		if (_isVisible)
		{
			g.setColor(_foreground);
			g.setFont(_font);
			g.drawString(_text, _x, _y+_height-_metrics.getDescent());
			
			g.setColor(_background);
			if (_isFilled)
			{
				//g.fillRect(_x+_lineWidth, _y+_lineWidth, _width-_lineWidth, _height-_lineWidth);
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

    // ino.method.contains.1323.description type=line
    // Diese Funktion prüft, ob der Mauszeiger sich innerhalb des Textfeldes befindet.
    // ino.end
    // ino.method.contains.1323.definition 
    public boolean contains(int x,int y)
    // ino.end
	// ino.method.contains.1323.body 
	{
	   if ((x <= (_x + _width)) & (x >= _x) & (y <= (_y + _height)) & (y >= _y)) return true;
	   else return false;
	}
	// ino.end

    // ino.method.setX.4908.description type=line
    // Diese Funktion setzt die X-Position des Textfeldes.
    // ino.end
    // ino.method.setX.4908.definition 
    public void   setX(int x)
    // ino.end
	// ino.method.setX.4908.body 
	{
		_x = x;
	   _shape.setX(x);
	}
	// ino.end

    // ino.method.setY.4911.description type=line
    // Diese Funktion setzt die Y-Position des Textfeldes.
    // ino.end
    // ino.method.setY.4911.definition 
    public void   setY(int y)
    // ino.end
	// ino.method.setY.4911.body 
	{
		_y = y;
	   _shape.setY(y);
	}
	// ino.end

    // ino.method.setWidth.4914.description type=line
    // Diese Funktion setzt die Breite des Textfeldes.
    // ino.end
    // ino.method.setWidth.4914.definition 
    public void   setWidth(int width)
    // ino.end
	// ino.method.setWidth.4914.body 
	{
	   if(width > 0) _width = width;
	   _shape.setWidth(_width);
	}
	// ino.end

    // ino.method.setHeight.4917.description type=line
    // Diese Funktion setzt die Höhe des Textfeldes.
    // ino.end
    // ino.method.setHeight.4917.definition 
    public void setHeight(int height)
    // ino.end
	// ino.method.setHeight.4917.body 
	{
	   if(height > 0) _height = height;
	   _shape.setHeight(_height);
	}
	// ino.end

	// ino.method.setFont.4920.description type=line
	// Diese Funktion setzt den Zeichensatz des Textes.
	// ino.end
	// ino.method.setFont.4920.definition 
	public void setFont(Font font,FontMetrics metrics)
	// ino.end
	// ino.method.setFont.4920.body 
	{
		_font = font;
		_metrics = metrics;
		setMetrics();
	}
	// ino.end

	// ino.method.setText.4923.description type=line
	// Diese Funktion setzt den Text im Textfeld.
	// ino.end
	// ino.method.setText.4923.definition 
	public void setText(String text)
	// ino.end
	// ino.method.setText.4923.body 
	{
		_text = text;
	}
	// ino.end

	// ino.method.setMetrics.4926.description type=line
	// Diese Funktion bestimmt die Metrik des Zeichensatzes.
	// ino.end
	// ino.method.setMetrics.4926.definition 
	public void setMetrics()
	// ino.end
	// ino.method.setMetrics.4926.body 
	{
		_width  = _metrics.stringWidth(_text);
		_height = _metrics.getHeight();
		_shape.setWidth(_width);
		_shape.setHeight(_height);
	}
	// ino.end

	// ino.method.getX.4929.description type=line
	// Diese Funktion liefert die X-Position des Textfeldes zurück.
	// ino.end
	// ino.method.getX.4929.definition 
	public int getX()
	// ino.end
	// ino.method.getX.4929.body 
	{
		return _x;
	}
	// ino.end

    // ino.method.getY.4932.description type=line
    // Diese Funktion liefert die Y-Position des Textfeldes zurück.
    // ino.end
    // ino.method.getY.4932.definition 
    public int getY()
    // ino.end
	// ino.method.getY.4932.body 
	{
		return _y;
	}
	// ino.end

    // ino.method.getWidth.4935.description type=line
    // Diese Funktion liefert die Breite des Textfeldes zurück.
    // ino.end
    // ino.method.getWidth.4935.definition 
    public int getWidth()
    // ino.end
	// ino.method.getWidth.4935.body 
	{
		return _width;
	}
	// ino.end

    // ino.method.getHeight.4938.description type=line
    // Diese Funktion liefert die Höhe des Textfeldes zurück.
    // ino.end
    // ino.method.getHeight.4938.definition 
    public int getHeight()
    // ino.end
	// ino.method.getHeight.4938.body 
	{
		return _height;
	}
	// ino.end

	// ino.method.getShape.4941.description type=line
	// Diese Funktion gibt das Shape des Textfeldes zurück.
	// ino.end
	// ino.method.getShape.4941.definition 
	public SmShape getShape()
	// ino.end
	// ino.method.getShape.4941.body 
	{
		return _shape;
	}
	// ino.end

    // ino.method.showShape.4944.description type=line
    // Diese Funktion macht das Shape des Textfeldes sichtbar.
    // ino.end
    // ino.method.showShape.4944.definition 
    public void   showShape(Graphics g)
    // ino.end
	// ino.method.showShape.4944.body 
	{
		_shape.paint(g);
	}
	// ino.end

    // ino.method.setIsSelected.4947.description type=line
    // Diese Funktion setzt den Selektstatus.
    // ino.end
    // ino.method.setIsSelected.4947.definition 
    public void   setIsSelected(boolean isselected)
    // ino.end
	// ino.method.setIsSelected.4947.body 
	{
		_isSelected = isselected;
	}
	// ino.end

    // ino.method.getIsSelected.4950.description type=line
    // Diese Funktion gibt den Selektstatus zurück.
    // ino.end
    // ino.method.getIsSelected.4950.definition 
    public boolean getIsSelected()
    // ino.end
	// ino.method.getIsSelected.4950.body 
	{
		return _isSelected;
	}
	// ino.end

	// ino.method.setGroupState.4953.description type=line
	// Diese Funktion setzt den Gruppenstatus des Textfeldes.
	// ino.end
	// ino.method.setGroupState.4953.definition 
	public void	setGroupState(boolean isMemberOfGroup)
	// ino.end
	// ino.method.setGroupState.4953.body 
	{
		_isMemberOfGroup = isMemberOfGroup;
	}
	// ino.end

    // ino.method.getGroupState.4956.description type=line
    // Diese Funktion gibt den Gruppenstatus des Textfeldes zurück.
    // ino.end
    // ino.method.getGroupState.4956.definition 
    public boolean getGroupState()
    // ino.end
	// ino.method.getGroupState.4956.body 
	{
		return _isMemberOfGroup;
	}
	// ino.end

    // ino.method.setIsVisible.4959.description type=line
    // Diese Funktion setzt die Sichtbarkeit des Textfeldes.
    // ino.end
    // ino.method.setIsVisible.4959.definition 
    public void   setIsVisible(boolean isVisible)
    // ino.end
	// ino.method.setIsVisible.4959.body 
	{
		_isVisible = isVisible;
	}
	// ino.end

	// ino.method.getIsVisible.4962.description type=line
	// Diese Funktion gibt die Sichtbarkeit des Textes zurück.
	// ino.end
	// ino.method.getIsVisible.4962.definition 
	public boolean getIsVisible()
	// ino.end
	// ino.method.getIsVisible.4962.body 
	{
		return _isVisible;
	}
	// ino.end

	// ino.method.setIsEditable.4965.description type=line
	// Diese Funktion bestimmt, ob ein Textfeld selektiert werden kann.
	// ino.end
	// ino.method.setIsEditable.4965.definition 
	public void	setIsEditable(boolean iseditable)
	// ino.end
	// ino.method.setIsEditable.4965.body 
	{
		_isEditable = iseditable;
	}
	// ino.end
	
    // ino.method.getSelector.4968.description type=line
    // Diese Funktion liefert den Selektor des Textfeldes zurück.
    // ino.end
    // ino.method.getSelector.4968.definition 
    public SmSelector getSelector()
    // ino.end
	// ino.method.getSelector.4968.body 
	{
		return _selector;
	}
	// ino.end

    // ino.method.setNameOfGraphicObject.4971.description type=line
    // Diese Funktion setzt den Namen des Textobjekts.
    // ino.end
    // ino.method.setNameOfGraphicObject.4971.definition 
    public void   setNameOfGraphicObject(String objectName)
    // ino.end
	// ino.method.setNameOfGraphicObject.4971.body 
	{
		_nameOfGraphicObject = objectName;
	}
	// ino.end

    // ino.method.getNameOfGraphicObject.4974.description type=line
    // Diese Funktion gibt den Namen des Textfeldes zurück.
    // ino.end
    // ino.method.getNameOfGraphicObject.4974.definition 
    public String getNameOfGraphicObject()
    // ino.end
	// ino.method.getNameOfGraphicObject.4974.body 
	{
		return _nameOfGraphicObject;
	}
	// ino.end

	// ino.method.getText.4977.description type=line
	// Diese Funktion liefert den Text im Textfeld zurück.
	// ino.end
	// ino.method.getText.4977.definition 
	public String getText()
	// ino.end
	// ino.method.getText.4977.body 
	{
		return _text;
	}
	// ino.end

	// ino.method.getFont.4980.description type=line
	// Diese Funktion liefert den Zeichensatz des Textes zurück.
	// ino.end
	// ino.method.getFont.4980.definition 
	public Font getFont()
	// ino.end
	// ino.method.getFont.4980.body 
	{
		return _font;
	}
	// ino.end

	// ino.method.getMetrics.4983.description type=line
	// Diese Funktion liefert die Metrik des Zeichensatzes zurück.
	// ino.end
	// ino.method.getMetrics.4983.definition 
	public FontMetrics getMetrics()
	// ino.end
	// ino.method.getMetrics.4983.body 
	{
		return _metrics;
	}
	// ino.end

	// ino.method.setXp1.4986.description type=line
	// Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden da sie   
	// in der abstrakten Basisklasse deklariert wurde.
	// ino.end
	// ino.method.setXp1.4986.definition 
	public void		setXp1(int xp1)
	// ino.end
	// ino.method.setXp1.4986.body 
	{;}
	// ino.end
    // ino.method.setXp2.4989.description type=line
    // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.setXp2.4989.definition 
    public void   	setXp2(int xp2)
    // ino.end
    // ino.method.setXp2.4989.body 
    {;}
    // ino.end
    // ino.method.setYp1.4992.description type=line
    // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.setYp1.4992.definition 
    public void   	setYp1(int yp1)
    // ino.end
    // ino.method.setYp1.4992.body 
    {;}
    // ino.end
    // ino.method.setYp2.4995.description type=line
    // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.setYp2.4995.definition 
    public void   	setYp2(int yp2)
    // ino.end
    // ino.method.setYp2.4995.body 
    {;}
    // ino.end
}
// ino.end





