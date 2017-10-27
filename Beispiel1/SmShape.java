import java.awt.*;
import java.io.*;


// ino.class.SmShape.1019.description type=line
// Die Klasse SmShape beschreibt die Abmessungen eines Objekts und hat die Form    
// eines umrahmenden Rechtecks. Auﬂerdem dient diese Klasse zur Aufnahme eines     
// Selektors. SmShape ist von SmLayoutObject abgeleitet und muﬂ daher alle         
// abstrakten Funktionen von SmLayoutObject definieren, auch wenn diese nicht      
// benˆtigt werden.
// ino.end
// ino.class.SmShape.1019.declaration 
public class SmShape extends SmLayoutObject implements Serializable
// ino.end
// ino.class.SmShape.1019.body
{
	// ino.attribute._x.4703.description type=line
	// Dieses Attribut h‰lt die X-Position des Shapes.
	// ino.end
	// ino.attribute._x.4703.declaration 
	private int _x;
	// ino.end
	// ino.attribute._y.4706.description type=line
	// Dieses Attribut h‰lt die Y-Position des Shapes.
	// ino.end
	// ino.attribute._y.4706.declaration 
	private int _y;
	// ino.end
	// ino.attribute._width.4709.description type=line
	// Dieses Attribut h‰lt die Breite des Shapes.
	// ino.end
	// ino.attribute._width.4709.declaration 
	private int _width;
	// ino.end
	// ino.attribute._height.4712.description type=line
	// Dieses Attribut h‰lt die Hˆhe des Shapes.
	// ino.end
	// ino.attribute._height.4712.declaration 
	private int _height;
	// ino.end

	// ino.attribute._foreground.4715.description type=line
	// Dieses Attribut bestimmt die Vordergrundfarbe des Shapes.
	// ino.end
	// ino.attribute._foreground.4715.declaration 
	private Color _foreground;
	// ino.end

	// ino.method.SmShape.4721.description type=line
	// Der Parameterkonstruktor initialisiert die Abmessungen des Shapes mit den       
	// ¸bergebenen Werten. Die Vordergrundfarbe wird auf schwarz gesetzt.
	// ino.end
	// ino.method.SmShape.4721.definition 
	public SmShape(int x,int y,int width,int height)
	// ino.end
	// ino.method.SmShape.4721.body 
	{
		_x      = x;
		_y      = y;
		_width  = width;
		_height = height;
		_foreground = Color.black;
	}
	// ino.end

	// ino.method.setX.4724.description type=line
	// Diese Funktion setzt die X-Position des Shapes.
	// ino.end
	// ino.method.setX.4724.definition 
	public void setX(int x)
	// ino.end
	// ino.method.setX.4724.body 
	{
		_x = x;
	}
	// ino.end

	// ino.method.setY.4727.description type=line
	// Diese Funktion setzt die Y-Position des Shapes.
	// ino.end
	// ino.method.setY.4727.definition 
	public void setY(int y)
	// ino.end
	// ino.method.setY.4727.body 
	{
		_y = y;
	}
	// ino.end

	// ino.method.setWidth.4730.description type=line
	// Diese Funktion setzt die Breite des Shapes.
	// ino.end
	// ino.method.setWidth.4730.definition 
	public void setWidth(int width)
	// ino.end
	// ino.method.setWidth.4730.body 
	{
		_width = width;
	}
	// ino.end

	// ino.method.setHeight.4733.description type=line
	// Diese Funktion setzt die Hˆhe des Shapes.
	// ino.end
	// ino.method.setHeight.4733.definition 
	public void setHeight(int height)
	// ino.end
	// ino.method.setHeight.4733.body 
	{
		_height = height;
	}
	// ino.end

	// ino.method.getX.4736.description type=line
	// Diese Funktion liefert die X-Position des Shapes zur¸ck.
	// ino.end
	// ino.method.getX.4736.definition 
	public int getX()
	// ino.end
	// ino.method.getX.4736.body 
	{
		return _x;
	}
	// ino.end

	// ino.method.getY.4739.description type=line
	// Diese Funktion liefert die Y-Position des Shapes zur¸ck.
	// ino.end
	// ino.method.getY.4739.definition 
	public int getY()
	// ino.end
	// ino.method.getY.4739.body 
	{
		return _y;
	}
	// ino.end

	// ino.method.getWidth.4742.description type=line
	// Diese Funktion liefert die Breite des Shapes zur¸ck.
	// ino.end
	// ino.method.getWidth.4742.definition 
	public int getWidth()
	// ino.end
	// ino.method.getWidth.4742.body 
	{
		return _width;
	}
	// ino.end

	// ino.method.getHeight.4745.description type=line
	// Diese Funktion liefert die Hˆhe des Shapes zur¸ck.
	// ino.end
	// ino.method.getHeight.4745.definition 
	public int getHeight()
	// ino.end
	// ino.method.getHeight.4745.body 
	{
		return _height;
	}
	// ino.end

	// ino.method.paint.4748.description type=line
	// Diese Funktion zeichnet das Shape.
	// ino.end
	// ino.method.paint.4748.definition 
	public void paint(Graphics g)
	// ino.end
	// ino.method.paint.4748.body 
	{
		g.setColor(_foreground);
		g.drawRect(_x, _y, _width, _height);
	}
	// ino.end

	// ino.method.setForeground.4751.description type=line
	// Diese Funktion setzt die Vordergrundfarbe des Shapes.
	// ino.end
	// ino.method.setForeground.4751.definition 
	public void setForeground(Color foreground)
	// ino.end
	// ino.method.setForeground.4751.body 
	{
		_foreground = foreground;
	}
	// ino.end
    // ino.method.setBackground.4754.description type=line
    // Diese Funktion hat hier keine Bedeutung, muﬂ aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.setBackground.4754.definition 
    public void setBackground(Color background)
    // ino.end
	// ino.method.setBackground.4754.body 
	{
		
	}
	// ino.end
	// ino.method.setFont.4757.description type=line
	// Diese Funktion hat hier keine Bedeutung, muﬂ aber implementiert werden da sie   
	// in der abstrakten Basisklasse deklariert wurde.
	// ino.end
	// ino.method.setFont.4757.definition 
	public void		setFont(Font font,FontMetrics metrics)
	// ino.end
	// ino.method.setFont.4757.body 
	{;}
	// ino.end
	// ino.method.setText.4760.description type=line
	// Diese Funktion hat hier keine Bedeutung, muﬂ aber implementiert werden da sie   
	// in der abstrakten Basisklasse deklariert wurde.
	// ino.end
	// ino.method.setText.4760.definition 
	public void		setText(String text)
	// ino.end
	// ino.method.setText.4760.body 
	{;}
	// ino.end
    // ino.method.contains.4763.description type=line
    // Diese Funktion hat hier keine Bedeutung, muﬂ aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.contains.4763.definition 
    public boolean      contains(int x,int y)
    // ino.end
    // ino.method.contains.4763.body 
    {return false;}
    // ino.end
	// ino.method.getShape.4766.description type=line
	// Diese Funktion hat hier keine Bedeutung, muﬂ aber implementiert werden da sie   
	// in der abstrakten Basisklasse deklariert wurde.
	// ino.end
	// ino.method.getShape.4766.definition 
	public SmShape   	getShape()
	// ino.end
	// ino.method.getShape.4766.body 
	{return new SmShape(0,0,0,0);}
	// ino.end
    // ino.method.showShape.4769.description type=line
    // Diese Funktion hat hier keine Bedeutung, muﬂ aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.showShape.4769.definition 
    public void      	showShape(Graphics g)
    // ino.end
    // ino.method.showShape.4769.body 
    {;}
    // ino.end
    // ino.method.setIsSelected.4772.description type=line
    // Diese Funktion hat hier keine Bedeutung, muﬂ aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.setIsSelected.4772.definition 
    public void      	setIsSelected(boolean isselected)
    // ino.end
    // ino.method.setIsSelected.4772.body 
    {;}
    // ino.end
    // ino.method.getIsSelected.4775.description type=line
    // Diese Funktion hat hier keine Bedeutung, muﬂ aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.getIsSelected.4775.definition 
    public boolean      getIsSelected()
    // ino.end
    // ino.method.getIsSelected.4775.body 
    {return false;}
    // ino.end
	// ino.method.setGroupState.4778.description type=line
	// Diese Funktion hat hier keine Bedeutung, muﬂ aber implementiert werden da sie   
	// in der abstrakten Basisklasse deklariert wurde.
	// ino.end
	// ino.method.setGroupState.4778.definition 
	public void		setGroupState(boolean isMemberOfGroup)
	// ino.end
	// ino.method.setGroupState.4778.body 
	{;}
	// ino.end
    // ino.method.getGroupState.4781.description type=line
    // Diese Funktion hat hier keine Bedeutung, muﬂ aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.getGroupState.4781.definition 
    public boolean   	getGroupState()
    // ino.end
    // ino.method.getGroupState.4781.body 
    {return false;}
    // ino.end
    // ino.method.setIsVisible.4784.description type=line
    // Diese Funktion hat hier keine Bedeutung, muﬂ aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.setIsVisible.4784.definition 
    public void      	setIsVisible(boolean isVisible)
    // ino.end
    // ino.method.setIsVisible.4784.body 
    {;}
    // ino.end
	// ino.method.setIsEditable.4787.description type=line
	// Diese Funktion hat hier keine Bedeutung, muﬂ aber implementiert werden da sie   
	// in der abstrakten Basisklasse deklariert wurde.
	// ino.end
	// ino.method.setIsEditable.4787.definition 
	public void		setIsEditable(boolean iseditable)
	// ino.end
	// ino.method.setIsEditable.4787.body 
	{;}
	// ino.end
    // ino.method.getIsVisible.4790.description type=line
    // Diese Funktion hat hier keine Bedeutung, muﬂ aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.getIsVisible.4790.definition 
    public boolean      getIsVisible()
    // ino.end
    // ino.method.getIsVisible.4790.body 
    {return false;}
    // ino.end
    // ino.method.getSelector.4793.description type=line
    // Diese Funktion hat hier keine Bedeutung, muﬂ aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.getSelector.4793.definition 
    public SmSelector  getSelector()
    // ino.end
    // ino.method.getSelector.4793.body 
    {return new SmSelector(0,0,0,0);}
    // ino.end
    // ino.method.setNameOfGraphicObject.4796.description type=line
    // Diese Funktion hat hier keine Bedeutung, muﬂ aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.setNameOfGraphicObject.4796.definition 
    public void      	setNameOfGraphicObject(String objectName)
    // ino.end
    // ino.method.setNameOfGraphicObject.4796.body 
    {;}
    // ino.end
    // ino.method.getNameOfGraphicObject.4799.description type=line
    // Diese Funktion hat hier keine Bedeutung, muﬂ aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.getNameOfGraphicObject.4799.definition 
    public String      getNameOfGraphicObject()
    // ino.end
    // ino.method.getNameOfGraphicObject.4799.body 
    {return "";}
    // ino.end
	// ino.method.setXp1.4802.description type=line
	// Diese Funktion hat hier keine Bedeutung, muﬂ aber implementiert werden da sie   
	// in der abstrakten Basisklasse deklariert wurde.
	// ino.end
	// ino.method.setXp1.4802.definition 
	public void setXp1(int xp1)
	// ino.end
	// ino.method.setXp1.4802.body 
	{;}
	// ino.end
    // ino.method.setXp2.4805.description type=line
    // Diese Funktion hat hier keine Bedeutung, muﬂ aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.setXp2.4805.definition 
    public void setXp2(int xp2)
    // ino.end
    // ino.method.setXp2.4805.body 
    {;}
    // ino.end
    // ino.method.setYp1.4808.description type=line
    // Diese Funktion hat hier keine Bedeutung, muﬂ aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.setYp1.4808.definition 
    public void setYp1(int yp1)
    // ino.end
    // ino.method.setYp1.4808.body 
    {;}
    // ino.end
    // ino.method.setYp2.4811.description type=line
    // Diese Funktion hat hier keine Bedeutung, muﬂ aber implementiert werden da sie   
    // in der abstrakten Basisklasse deklariert wurde.
    // ino.end
    // ino.method.setYp2.4811.definition 
    public void setYp2(int yp2)
    // ino.end
    // ino.method.setYp2.4811.body 
    {;}
    // ino.end
}
// ino.end

