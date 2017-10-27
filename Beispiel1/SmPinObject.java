import java.awt.*;


// ino.class.SmPinObject.2270.description type=line
// Die Klasse SmPinObject ist die Basisklasse aller im Symboleditor erstellten     
// Pinobjekte und ist von SmObject abgeleitet. Sie deklariert zur Zeit nur         
// abstrakte Methoden, die alle abgeleiteten Objekte implementieren müssen. eine   
// weitere definition erfolgt zu einem späteren Zeitpunkt.
// ino.end
// ino.class.SmPinObject.2270.declaration 
public abstract class SmPinObject extends SmObject
// ino.end
// ino.class.SmPinObject.2270.body
{
  
   // ino.method.setForeground.4999.declaration 
   public abstract void setForeground(Color foreground);;
   // ino.end
   
   // ino.method.setBackground.5002.declaration 
   public abstract void setBackground(Color background);;
   // ino.end
   
   // ino.method.setFont.4617.declaration 
   public abstract void      setFont(Font font,FontMetrics metrics);
   // ino.end
   // ino.method.setText.4620.declaration 
   public abstract void    setText(String text);
   // ino.end
   // ino.method.paint.4623.declaration 
   public abstract void    paint(Graphics g);
   // ino.end
   // ino.method.contains.4626.declaration 
   public abstract boolean contains(int x,int y);
   // ino.end
   // ino.method.setX.4629.declaration 
   public abstract void      setX(int x);
   // ino.end
   // ino.method.setY.4632.declaration 
   public abstract void      setY(int y);
   // ino.end
   // ino.method.setXp1.4635.declaration 
   public abstract void      setXp1(int xp1);
   // ino.end
   // ino.method.setXp2.4638.declaration 
   public abstract void    setXp2(int xp2);
   // ino.end
   // ino.method.setYp1.4641.declaration 
   public abstract void    setYp1(int yp1);
   // ino.end
   // ino.method.setYp2.4644.declaration 
   public abstract void    setYp2(int yp2);
   // ino.end
   // ino.method.getX.4647.declaration 
   public abstract int     getX();
   // ino.end
   // ino.method.getY.4650.declaration 
   public abstract int     getY();
   // ino.end
   // ino.method.setWidth.4653.declaration 
   public abstract void    setWidth(int width);
   // ino.end
   // ino.method.setHeight.4656.declaration 
   public abstract void    setHeight(int height);
   // ino.end
   // ino.method.getWidth.4659.declaration 
   public abstract int     getWidth();
   // ino.end
   // ino.method.getHeight.4662.declaration 
   public abstract int     getHeight();
   // ino.end
   // ino.method.getShape.4665.declaration 
   public abstract SmShape getShape();
   // ino.end
   // ino.method.showShape.4668.declaration 
   public abstract void    showShape(Graphics g);
   // ino.end
   // ino.method.setIsSelected.4671.declaration 
   public abstract void    setIsSelected(boolean isselected);
   // ino.end
   // ino.method.getIsSelected.4674.declaration 
   public abstract boolean getIsSelected();
   // ino.end
   // ino.method.setGroupState.4677.declaration 
   public abstract void      setGroupState(boolean isMemberOfGroup);
   // ino.end
   // ino.method.getGroupState.4680.declaration 
   public abstract boolean getGroupState();
   // ino.end
   // ino.method.setIsVisible.4683.declaration 
   public abstract void    setIsVisible(boolean isVisible);
   // ino.end
   // ino.method.setIsEditable.4686.declaration 
   public abstract void    setIsEditable(boolean iseditable);
   // ino.end
   // ino.method.getIsVisible.4689.declaration 
   public abstract boolean getIsVisible();
   // ino.end
   // ino.method.getSelector.4692.declaration 
   public abstract SmSelector getSelector();
   // ino.end
   // ino.method.setNameOfGraphicObject.4695.declaration 
   public abstract void    setNameOfGraphicObject(String objectName);
   // ino.end
   // ino.method.getNameOfGraphicObject.4698.declaration 
   public abstract String  getNameOfGraphicObject();
   // ino.end
}
// ino.end





