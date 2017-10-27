import java.awt.*;

// ino.class.SmLayoutObject.2273.description type=line
// Die abstrakte Klasse SmLayoutObject ist von SmObject abgeleitet und ist die     
// Basisklasse von SmShape. Diese KLasse hat lediglich eine formelle Bedeutung(Sie 
// dient zur besseren Übersicht des Klassenbaums) und implementiert nur alle       
// Funktionen der Oberklasse. Zur Beschreibung der Funktionen, siehe SmObject.
// ino.end
// ino.class.SmLayoutObject.2273.declaration 
public abstract class SmLayoutObject extends SmObject
// ino.end
// ino.class.SmLayoutObject.2273.body
{
	// ino.method.setForeground.4487.declaration 
	public abstract void   		setForeground(Color foreground);
	// ino.end
    // ino.method.setBackground.4490.declaration 
    public abstract void      	setBackground(Color background);
    // ino.end
	// ino.method.setFont.4493.declaration 
	public abstract void		setFont(Font font,FontMetrics metrics);
	// ino.end
	// ino.method.setText.4496.declaration 
	public abstract void		setText(String text);
	// ino.end
    // ino.method.paint.4499.declaration 
    public abstract void      	paint(Graphics g);
    // ino.end
    // ino.method.contains.4502.declaration 
    public abstract boolean      contains(int x,int y);
    // ino.end
    // ino.method.setX.4505.declaration 
    public abstract void      	setX(int x);
    // ino.end
    // ino.method.setY.4508.declaration 
    public abstract void      	setY(int y);
    // ino.end
	// ino.method.setXp1.4511.declaration 
	public abstract void		setXp1(int xp1);
	// ino.end
    // ino.method.setXp2.4514.declaration 
    public abstract void   	setXp2(int xp2);
    // ino.end
    // ino.method.setYp1.4517.declaration 
    public abstract void   	setYp1(int yp1);
    // ino.end
    // ino.method.setYp2.4520.declaration 
    public abstract void   	setYp2(int yp2);
    // ino.end
    // ino.method.setWidth.4523.declaration 
    public abstract void      	setWidth(int width);
    // ino.end
    // ino.method.setHeight.4526.declaration 
    public abstract void      	setHeight(int height);
    // ino.end
    // ino.method.getX.4529.declaration 
    public abstract int      	getX();
    // ino.end
    // ino.method.getY.4532.declaration 
    public abstract int      	getY();
    // ino.end
    // ino.method.getWidth.4535.declaration 
    public abstract int      	getWidth();
    // ino.end
    // ino.method.getHeight.4538.declaration 
    public abstract int      	getHeight();
    // ino.end
    // ino.method.getShape.4541.declaration 
    public abstract SmShape      getShape();
    // ino.end
    // ino.method.showShape.4544.declaration 
    public abstract void      	showShape(Graphics g);
    // ino.end
    // ino.method.setIsSelected.4547.declaration 
    public abstract void      	setIsSelected(boolean isselected);
    // ino.end
    // ino.method.getIsSelected.4550.declaration 
    public abstract boolean      getIsSelected();
    // ino.end
	// ino.method.setGroupState.4553.declaration 
	public abstract void			setGroupState(boolean isMemberOfGroup);
	// ino.end
    // ino.method.getGroupState.4556.declaration 
    public abstract boolean   	getGroupState();
    // ino.end
    // ino.method.setIsVisible.4559.declaration 
    public abstract void      	setIsVisible(boolean isVisible);
    // ino.end
	// ino.method.setIsEditable.4562.declaration 
	public abstract void		setIsEditable(boolean iseditable);
	// ino.end
    // ino.method.getIsVisible.4565.declaration 
    public abstract boolean      getIsVisible();
    // ino.end
    // ino.method.getSelector.4568.declaration 
    public abstract SmSelector   getSelector();
    // ino.end
    // ino.method.setNameOfGraphicObject.4571.declaration 
    public abstract void      	setNameOfGraphicObject(String objectName);
    // ino.end
    // ino.method.getNameOfGraphicObject.4574.declaration 
    public abstract String      getNameOfGraphicObject();
    // ino.end
  
}
// ino.end

