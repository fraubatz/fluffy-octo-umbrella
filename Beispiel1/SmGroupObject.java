import java.awt.*;
import java.util.*;
import java.io.*;


// ino.class.SmGroupObject.5010.description type=line
// Die Klasse SmGroupObject ist von SmObject abgeleitet und stellt ein             
// Gruppenobjekt dar. Ein Gruppenobjekt hält eine Referenzliste auf alle Objekte,  
// die dieser Gruppe angehören. Diese Liste kann wiederum gruppierte Objekte       
// enthalten.
// ino.end
// ino.class.SmGroupObject.5010.declaration 
public class SmGroupObject extends SmObject implements Serializable
// ino.end
// ino.class.SmGroupObject.5010.body
{
	// ino.attribute._groupList.5240.description type=line
	// Dieses Attribut hält eine Liste von Referenzen auf Objekte, die diesem          
	// Gruppenobjekt angehören.
	// ino.end
	// ino.attribute._groupList.5240.declaration 
	private SmEladoObjectList _groupList;
	// ino.end

	// ino.attribute._foreground.5033.description type=line
	// Dieses Attribut bestimmt die Vordergrundfarbe aller gruppierten Objekte.
	// ino.end
	// ino.attribute._foreground.5033.declaration 
	private Color _foreground;
	// ino.end

	// ino.attribute._background.5218.description type=line
	// Dieses Attribut bestimmt die Hintergrundfarbe aller gruppierten Objekte.
	// ino.end
	// ino.attribute._background.5218.declaration 
	private Color _background;
	// ino.end

	// ino.attribute._fillColor.5036.description type=line
	// Dieses Attribut bestimmt die Füllfarbe aller gruppierten Objekte.
	// ino.end
	// ino.attribute._fillColor.5036.declaration 
	private Color _fillColor;
	// ino.end

	// ino.attribute._isFilled.5051.description type=line
	// Dieses Flag ist gesetzt(TRUE), wenn für alle gruppierten Objekte eine Füllfarbe 
	// existiert.
	// ino.end
	// ino.attribute._isFilled.5051.declaration 
	private boolean _isFilled;
	// ino.end
	
	// ino.attribute._isVisible.5054.description type=line
	// Dieses Flag ist gesetzt(TRUE), wenn das gruppierte Objekt sichtbar ist.
	// ino.end
	// ino.attribute._isVisible.5054.declaration 
	private boolean _isVisible;
	// ino.end

	// ino.attribute._isSelected.5057.description type=line
	// Dieses Flag ist gesetzt(TRUE), wenn das gruppierte Objekt selektiert werden     
	// soll.
	// ino.end
	// ino.attribute._isSelected.5057.declaration 
	private boolean _isSelected;
	// ino.end

	// ino.attribute._isEditable.5060.description type=line
	// Dieses Flag ist gesetzt(TRUE), wenn das Gruppenobjekt selektiert werden darf.
	// ino.end
	// ino.attribute._isEditable.5060.declaration 
	private boolean _isEditable;
	// ino.end

	// ino.attribute._isMemberOfGroup.5063.description type=line
	// Dieses Flag ist gesetzt(TRUE), wenn das Gruppenobjekt selbst Teil einer         
	// Gruppierung ist.
	// ino.end
	// ino.attribute._isMemberOfGroup.5063.declaration 
	private boolean _isMemberOfGroup;
	// ino.end

	// ino.attribute._shape.5245.description type=line
	// Dieses Attribut ist das Shape des Gruppenobjekts.
	// ino.end
	// ino.attribute._shape.5245.declaration 
	private SmShape _shape;
	// ino.end
	
	// ino.attribute._selector.5250.description type=line
	// Dieses Attribut ist der Selector des Gruppenobjekts.
	// ino.end
	// ino.attribute._selector.5250.declaration 
	private SmRectSelector _selector;
	// ino.end

	// ino.method.SmGroupObject.5076.description type=line
	// Der Parameterkonstruktor erhält eine Liste von Referenzen auf die Objekte, die  
	// gruppiert werden sollen und fügt diese in die Gruppenliste ein. Bei allen       
	// Objekten auf die die Elemente der Gruppenliste verweisen, wird die Selektierung 
	// aufgehoben und das Flag '_isMemberOfGroup' gesetzt. Dadurch sind diese Objekte  
	// nicht mehr einzeln selektierbar bis die Gruppierung wieder aufgehoben wird.     
	// Folgende Eigenschaften des Gruppenobjekts werden gesetzt: Das Gruppenobjekt ist 
	// sichtbar, nicht selektiert und ist kein Mitglied einer Gruppe. Ferner wird das  
	// Shape des Gruppenobjekts ermittelt.
	// ino.end
	// ino.method.SmGroupObject.5076.definition 
	public SmGroupObject(SmEladoObjectList objectList)
	// ino.end
	// ino.method.SmGroupObject.5076.body 
	{
		_groupList = new SmEladoObjectList();
		_isVisible = true;
		_isSelected = false;
		_isMemberOfGroup = false;
		
		int objectCount = objectList.getObjectCount();
		int i;
		SmObject objectByList;
		Enumeration enum = objectList.getElementsOfList();
		for(i=0;i < objectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setGroupState(true);
			_groupList.appendObject(objectByList);
			objectByList.setIsSelected(false);
		}
		_shape = new SmShape(getMinPoint().x, getMinPoint().y,
							 getMaxPoint().x-getMinPoint().x,
							 getMaxPoint().y-getMinPoint().y);
	}
	// ino.end

	// ino.method.SmGroupObject.5079.description type=line
	// Der Copykonstruktor kopiert alle Attribute des üergebenen Objekts und           
	// verschiebt alle Objekte der Gruppenliste um den übergebenen Offset.
	// ino.end
	// ino.method.SmGroupObject.5079.definition 
	public SmGroupObject(SmGroupObject copy,int offsetX,int offsetY)
	// ino.end
	// ino.method.SmGroupObject.5079.body 
	{
		_groupList = new SmEladoObjectList();
		_groupList = copy._groupList;
		
		_isFilled = copy._isFilled;
		_isVisible = copy._isVisible;
		_isSelected = copy._isSelected;
		_isEditable = copy._isEditable;
		_isMemberOfGroup = copy._isMemberOfGroup;

		int objectCount = _groupList.getObjectCount();
		int i;
		SmObject objectByList;
		Enumeration enum = _groupList.getElementsOfList();

		for(i=0;i < objectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setX(objectByList.getX()+offsetX);
			objectByList.setY(objectByList.getY()+offsetY);
		}
		_shape = new SmShape(getMinPoint().x, getMinPoint().y,
							 getMaxPoint().x-getMinPoint().x,
							 getMaxPoint().y-getMinPoint().y);
	}
	// ino.end

	// ino.method.setForeground.5082.description type=line
	// Diese Funktion setzt die Vordergrundfarbe aller in der Gruppenliste             
	// referenzierten Objekte.
	// ino.end
	// ino.method.setForeground.5082.definition 
	public void	setForeground(Color foreground)
	// ino.end
	// ino.method.setForeground.5082.body 
	{
		_foreground = foreground;

		int objectCount = _groupList.getObjectCount();
		int i;
		SmObject objectByList;
		Enumeration enum = _groupList.getElementsOfList();

		for(i=0;i < objectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setForeground(_foreground);
		}
	}
	// ino.end

    // ino.method.setBackground.5085.description type=line
    // Diese Funktion setzt die Hintergrundfarbe aller in der Gruppenliste             
    // referenzierten Objekte.
    // ino.end
    // ino.method.setBackground.5085.definition 
    public void   setBackground(Color background)
    // ino.end
   	// ino.method.setBackground.5085.body 
   	{
		_background = background;
	}
   	// ino.end

    // ino.method.paint.5088.description type=line
    // Diese Funktion zeichnet den Selektor des Gruppenobjekts.
    // ino.end
    // ino.method.paint.5088.definition 
    public void paint(Graphics g)
    // ino.end
    // ino.method.paint.5088.body 
    {
	   if (_isVisible)
	   {
			g.setColor(_foreground);
			if (_isSelected) 
			{
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

    // ino.method.contains.5091.description type=line
    // Diese Funktion überprüft, ob sich der Mauszeiger innerhalb des Shapes des       
    // Gruppenobjekts befindet.
    // ino.end
    // ino.method.contains.5091.definition 
    public boolean contains(int x,int y)
    // ino.end
 	// ino.method.contains.5091.body 
 	{
		if ((x <= (_shape.getX() + _shape.getWidth()))  & (x >= _shape.getX()) & 
			(y <= (_shape.getY() + _shape.getHeight())) & (y >= _shape.getY())) return true;
	    else return false;
	}
 	// ino.end

	// ino.method.moveX.5094.description type=line
	// Diese Funktion verschiebt alle gruppierten Objekte aus der Gruppenliste um den  
	// übergebenen Offset in X-Richtung.
	// ino.end
	// ino.method.moveX.5094.definition 
	public void moveX(int x)
	// ino.end
	// ino.method.moveX.5094.body 
	{
		int g_objectCount = _groupList.getObjectCount();
		int g_i;
		int g_offsetX = _shape.getX()-x;
		SmObject g_objectByList;
		Enumeration g_enum = _groupList.getElementsOfList();

		for(g_i=0;g_i < g_objectCount;g_i++)
		{
			g_objectByList = (SmObject)g_enum.nextElement();
			if (g_objectByList instanceof SmGroupObject)
			{
				g_objectByList.moveX(x);
			}
			else
			{
				g_objectByList.setX(g_objectByList.getX()-g_offsetX);
			}
		}
		_shape.setX(x);
	}
	// ino.end

    // ino.method.moveY.5097.description type=line
    // Diese Funktion verschiebt alle gruppierten Objekte aus der Gruppenliste um den  
    // übergebenen Offset in Y-Richtung.
    // ino.end
    // ino.method.moveY.5097.definition 
    public void moveY(int y)
    // ino.end
  	// ino.method.moveY.5097.body 
  	{
		int g_objectCount = _groupList.getObjectCount();
		int g_i;
		int g_offsetY = _shape.getY()-y;
		SmObject g_objectByList;
		Enumeration g_enum = _groupList.getElementsOfList();

		for(g_i=0;g_i < g_objectCount;g_i++)
		{
			g_objectByList = (SmObject)g_enum.nextElement();
			if (g_objectByList instanceof SmGroupObject)
			{
				g_objectByList.moveY(y);
			}
			else
			{
				g_objectByList.setY(g_objectByList.getY()-g_offsetY);
			}
		}
		_shape.setY(y);
	}
  	// ino.end

    // ino.method.setX.5100.description type=line
    // Diese Funkton setzt die X-Position aller gruppierten Objekte aus der            
    // Gruppenliste um einen berechneten Skalierungsfaktor, welcher aus dem übergebenen
    // Offset ermittelt wird. Dabei wird der Nullpunkt des Koordinatensystems auf die  
    // X-Position des Gruppierten Objekts verschoben. Jede X-Position der gruppierten  
    // Objekte wird dann, bezogen auf den neuen Nullpunkt, mit dem Skalierungsfaktor   
    // multipliziert. Der Skalierungsfaktor berechnet sich aus dem Verhältnis          
    // (OffsetX+Breite des Gruppenobjekts)/Breite des Gruppenobjekts. Ist ein Objekt   
    // aus der Gruppenliste ebenfalls ein Gruppenobjekt, so wird diesem inneren        
    // Gruppenobjekt der übergebene Offset in modifizierter Form übergeben, da sich der
    // Skalierungsfaktor nun auf das Shape des inneren Gruppenobjekts bezieht und nicht
    // auf das Shape des darüberliegenden Gruppenobjekts.
    // ino.end
    // ino.method.setX.5100.definition 
    public void setX(int offsetX)
    // ino.end
 	// ino.method.setX.5100.body 
 	{
		int g_objectCount = _groupList.getObjectCount();
		int g_i;
		float scaleFactorX = (float)offsetX/(float)_shape.getWidth()+1;
		SmObject g_objectByList;
		Enumeration g_enum = _groupList.getElementsOfList();
		for(g_i=0;g_i < g_objectCount;g_i++)
		{
			g_objectByList = (SmObject)g_enum.nextElement();
			if (g_objectByList instanceof SmGroupObject)
			{
				g_objectByList.setX(Math.round(g_objectByList.getWidth()*offsetX/_shape.getWidth()));
			}
			else
			{
				g_objectByList.setX(Math.round((g_objectByList.getX()-_shape.getX())*scaleFactorX+_shape.getX()));
			}
		}
		_shape.setX(getMinPoint().x);
	}
 	// ino.end

    // ino.method.setY.5103.description type=line
    // Diese Funkton setzt die Y-Position aller gruppierten Objekte aus der            
    // Gruppenliste um einen berechneten Skalierungsfaktor, welcher aus dem übergebenen
    // Offset ermittelt wird. Dabei wird der Nullpunkt des Koordinatensystems auf die  
    // Y-Position des Gruppierten Objekts verschoben. Jede Y-Position der gruppierten  
    // Objekte wird dann, bezogen auf den neuen Nullpunkt, mit dem Skalierungsfaktor   
    // multipliziert. Der Skalierungsfaktor berechnet sich aus dem Verhältnis          
    // (OffsetY+Höhe des Gruppenobjekts)/Höhe des Gruppenobjekts. Ist ein Objekt aus   
    // der Gruppenliste ebenfalls ein Gruppenobjekt, so wird diesem inneren            
    // Gruppenobjekt der übergebene Offset in modifizierter Form übergeben, da sich der
    // Skalierungsfaktor nun auf das Shape des inneren Gruppenobjekts bezieht und nicht
    // auf das Shape des darüberliegenden Gruppenobjekts.
    // ino.end
    // ino.method.setY.5103.definition 
    public void setY(int offsetY)
    // ino.end
	// ino.method.setY.5103.body 
	{
		int g_objectCount = _groupList.getObjectCount();
		int g_i;
		float scaleFactorY = (float)offsetY/(float)_shape.getHeight()+1;
		SmObject g_objectByList;
		Enumeration g_enum = _groupList.getElementsOfList();

		for(g_i=0;g_i < g_objectCount;g_i++)
		{
			g_objectByList = (SmObject)g_enum.nextElement();
			if (g_objectByList instanceof SmGroupObject)
			{
				g_objectByList.setY(offsetY);
			}
			else
			{
				g_objectByList.setY(Math.round((g_objectByList.getY()-_shape.getY())*scaleFactorY+_shape.getY()));
			}
		}
		_shape.setY(getMinPoint().y);
	}
	// ino.end

    // ino.method.setWidth.5106.description type=line
    // Diese Funkton setzt die Breite aller gruppierten Objekte aus der Gruppenliste   
    // um einen berechneten Skalierungsfaktor, welcher aus dem übergebenen Offset      
    // ermittelt wird. Jede Breite der gruppierten Objekte wird dann mit dem           
    // Skalierungsfaktor multipliziert. Der Skalierungsfaktor berechnet sich aus dem   
    // Verhältnis (OffsetX+Breite des Gruppenobjekts)/Breite des Gruppenobjekts. Ist   
    // ein Objekt aus der Gruppenliste ebenfalls ein Gruppenobjekt, so wird diesem     
    // inneren Gruppenobjekt der übergebene Offset in modifizierter Form übergeben, da 
    // sich der Skalierungsfaktor nun auf das Shape des inneren Gruppenobjekts bezieht 
    // und nicht auf das Shape des darüberliegenden Gruppenobjekts.
    // ino.end
    // ino.method.setWidth.5106.definition 
    public void setWidth(int offsetWidth)
    // ino.end
   	// ino.method.setWidth.5106.body 
   	{
		int objectCount = _groupList.getObjectCount();
		int i;
		float scaleFactorWidth = (float)offsetWidth/(float)_shape.getWidth()+1;
		SmObject objectByList;
		Enumeration enum = _groupList.getElementsOfList();
		for(i=0;i < objectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			if (objectByList instanceof SmGroupObject)
			{
				
				objectByList.setWidth(Math.round(objectByList.getWidth()*offsetWidth/_shape.getWidth()));
			}
			else
			{
				float newWidth = objectByList.getWidth()*scaleFactorWidth;
				objectByList.setWidth(Math.round(newWidth));
			}
		}
		_shape.setWidth(_shape.getWidth()+offsetWidth);
	}
   	// ino.end

    // ino.method.setHeight.5109.description type=line
    // Diese Funkton setzt die Höhe aller gruppierten Objekte aus der Gruppenliste um  
    // einen berechneten Skalierungsfaktor, welcher aus dem übergebenen Offset         
    // ermittelt wird. Jede Höhe der gruppierten Objekte wird dann mit dem             
    // Skalierungsfaktor multipliziert. Der Skalierungsfaktor berechnet sich aus dem   
    // Verhältnis (OffsetY+Höhe des Gruppenobjekts)/Höhe des Gruppenobjekts. Ist ein   
    // Objekt aus der Gruppenliste ebenfalls ein Gruppenobjekt, so wird diesem inneren 
    // Gruppenobjekt der übergebene Offset in modifizierter Form übergeben, da sich der
    // Skalierungsfaktor nun auf das Shape des inneren Gruppenobjekts bezieht und nicht
    // auf das Shape des darüberliegenden Gruppenobjekts.
    // ino.end
    // ino.method.setHeight.5109.definition 
    public void setHeight(int offsetHeight)
    // ino.end
 	// ino.method.setHeight.5109.body 
 	{
		int objectCount = _groupList.getObjectCount();
		int i;
		float scaleFactorHeight = (float)offsetHeight/(float)_shape.getHeight()+1;
		SmObject objectByList;
		Enumeration enum = _groupList.getElementsOfList();

		for(i=0;i < objectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			if (objectByList instanceof SmGroupObject)
			{
				objectByList.setHeight(offsetHeight);
			}
			else
			{
				float newHeight = objectByList.getHeight()*scaleFactorHeight;
				objectByList.setHeight(Math.round(newHeight));
			}
		}
		_shape.setHeight(_shape.getHeight()+offsetHeight);
	}
 	// ino.end

    // ino.method.getX.5112.description type=line
    // Diese Funktion liefert die X-Position des Gruppenobjekts zurück.
    // ino.end
    // ino.method.getX.5112.definition 
    public int getX()
    // ino.end
 	// ino.method.getX.5112.body 
 	{
		return _shape.getX();
	}
 	// ino.end

    // ino.method.getY.5115.description type=line
    // Diese Funktion liefert die Y-Position des Gruppenobjekts zurück.
    // ino.end
    // ino.method.getY.5115.definition 
    public int getY()
    // ino.end
 	// ino.method.getY.5115.body 
 	{
		return _shape.getY();
	}
 	// ino.end

    // ino.method.getWidth.5118.description type=line
    // Diese Funktion liefert die Breite des Gruppenobjekts zurück.
    // ino.end
    // ino.method.getWidth.5118.definition 
    public int getWidth()
    // ino.end
 	// ino.method.getWidth.5118.body 
 	{
		return _shape.getWidth();
	}
 	// ino.end

    // ino.method.getHeight.5121.description type=line
    // Diese Funktion liefert die Höhe des Gruppenobjekts zurück.
    // ino.end
    // ino.method.getHeight.5121.definition 
    public int getHeight()
    // ino.end
 	// ino.method.getHeight.5121.body 
 	{
		return _shape.getHeight();
	}
 	// ino.end

    // ino.method.getShape.5124.description type=line
    // Diese Funktion liefert das Shape des Gruppenobjekts zurück.
    // ino.end
    // ino.method.getShape.5124.definition 
    public SmShape getShape()
    // ino.end
 	// ino.method.getShape.5124.body 
 	{
		return _shape;
	}
 	// ino.end

    // ino.method.showShape.5127.description type=line
    // Diese Funktion macht das Shape des Gruppenobjekts sichtbar.
    // ino.end
    // ino.method.showShape.5127.definition 
    public void showShape(Graphics g)
    // ino.end
 	// ino.method.showShape.5127.body 
 	{
		_shape.paint(g);
	}
 	// ino.end

    // ino.method.setIsSelected.5130.description type=line
    // Diese Funktion setzt den Selektstatus des Gruppenobjekts.
    // ino.end
    // ino.method.setIsSelected.5130.definition 
    public void setIsSelected(boolean isselected)
    // ino.end
 	// ino.method.setIsSelected.5130.body 
 	{
		_isSelected = isselected;
	}
 	// ino.end

    // ino.method.getIsSelected.5133.description type=line
    // Diese Funktion liefert den Selektstatus des Gruppenobjekts zurück.
    // ino.end
    // ino.method.getIsSelected.5133.definition 
    public boolean getIsSelected()
    // ino.end
	// ino.method.getIsSelected.5133.body 
	{
		return _isSelected;
	}
	// ino.end

	// ino.method.setGroupState.5136.description type=line
	// Diese Funktion setzt den Gruppenstatus des Grupenobjekts.
	// ino.end
	// ino.method.setGroupState.5136.definition 
	public void setGroupState(boolean isMemberOfGroup)
	// ino.end
    // ino.method.setGroupState.5136.body 
    {
	   _isMemberOfGroup = isMemberOfGroup;
    }
    // ino.end
  
    // ino.method.getGroupState.5139.description type=line
    // Diese Funktion liefert den Gruppenstatus des Gruppenobjekts zurück.
    // ino.end
    // ino.method.getGroupState.5139.definition 
    public boolean getGroupState()
    // ino.end
    // ino.method.getGroupState.5139.body 
    {
	   return _isMemberOfGroup;
    }
    // ino.end
  
    // ino.method.setIsVisible.5142.description type=line
    // Diese Funktion setzt die Sichtbarkeit des Gruppenobjekts.
    // ino.end
    // ino.method.setIsVisible.5142.definition 
    public void setIsVisible(boolean isVisible)
    // ino.end
 	// ino.method.setIsVisible.5142.body 
 	{
		_isVisible = isVisible;
	}
 	// ino.end

    // ino.method.setIsEditable.5145.description type=line
    // Diese Funktion bestimmt, ob ein Gruppenobjekt selektiert werden kann.
    // ino.end
    // ino.method.setIsEditable.5145.definition 
    public void   setIsEditable(boolean iseditable)
    // ino.end
	// ino.method.setIsEditable.5145.body 
	{
		_isEditable = iseditable;
	}
	// ino.end

    // ino.method.getIsVisible.5148.description type=line
    // Diese Funktion liefert die Sichtbarkeit des Gruppenobjekts zurück.
    // ino.end
    // ino.method.getIsVisible.5148.definition 
    public boolean getIsVisible()
    // ino.end
   	// ino.method.getIsVisible.5148.body 
   	{
		return _isVisible;
	}
   	// ino.end

    // ino.method.getSelector.5151.description type=line
    // Diese Funktion liefert den Selektor des Gruppenobjekts zurück.
    // ino.end
    // ino.method.getSelector.5151.definition 
    public SmSelector getSelector()
    // ino.end
 	// ino.method.getSelector.5151.body 
 	{
		return _selector;
	}
 	// ino.end

	// ino.method.getGroupList.5154.description type=line
	// Die Funktion liefert die Liste aller gruppierten Objekte zurück.
	// ino.end
	// ino.method.getGroupList.5154.definition 
	public SmEladoObjectList getGroupList()
	// ino.end
	// ino.method.getGroupList.5154.body 
	{
		return _groupList;
	}
	// ino.end

    // ino.method.setNameOfGraphicObject.5157.description type=line
    // Diese Funktion setzt den Namen des Gruppenobjekts.
    // ino.end
    // ino.method.setNameOfGraphicObject.5157.definition 
    public void setNameOfGraphicObject(String objectName)
    // ino.end
 	// ino.method.setNameOfGraphicObject.5157.body 
 	{

	}
 	// ino.end

    // ino.method.getNameOfGraphicObject.5160.description type=line
    // Diese Funktion liefert den Namen des Gruppenobjekts zurück.
    // ino.end
    // ino.method.getNameOfGraphicObject.5160.definition 
    public String getNameOfGraphicObject()
    // ino.end
  	// ino.method.getNameOfGraphicObject.5160.body 
  	{
		return " ";
	}
  	// ino.end

	// ino.method.getMinPoint.5163.description type=line
	// Diese Funktion liefert die kleinste Position eines gruppierten Objekts,         
	// ermittelt aus der Gruppenliste, zurück.
	// ino.end
	// ino.method.getMinPoint.5163.definition 
	public Point getMinPoint()
	// ino.end
    // ino.method.getMinPoint.5163.body 
    {
	    Point minPoint = new Point(800,800);
	    int i;
		int objectCount = _groupList.getObjectCount();
		SmObject objectByList;
		Enumeration enum = _groupList.getElementsOfList();
		for(i=0;i < objectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			if (objectByList.getX() < minPoint.x) minPoint.x = objectByList.getX();  
			if (objectByList.getY() < minPoint.y) minPoint.y = objectByList.getY();  
		}
		return minPoint;
   }
    // ino.end
 
   // ino.method.getMaxPoint.5166.description type=line
   // Diese Funktion liefert die größte Position eines gruppierten Objekts, ermittelt 
   // aus der Gruppenliste, zurück.
   // ino.end
   // ino.method.getMaxPoint.5166.definition 
   public Point getMaxPoint()
   // ino.end
   // ino.method.getMaxPoint.5166.body 
   {
	    Point maxPoint = new Point(0,0);
		int i;
		int objectCount = _groupList.getObjectCount();
		SmObject objectByList;
		Enumeration enum = _groupList.getElementsOfList();
		for(i=0;i < objectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			if ((objectByList.getX()+objectByList.getWidth()) > maxPoint.x) maxPoint.x = objectByList.getX()+objectByList.getWidth();  
			if ((objectByList.getY()+objectByList.getHeight()) > maxPoint.y) maxPoint.y = objectByList.getY()+objectByList.getHeight();  
		}
		return maxPoint;
   }
   // ino.end

   // ino.method.setFont.5169.description type=line
   // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden, da sie  
   // in der abstrakten Basisklasse deklariert wurde.
   // ino.end
   // ino.method.setFont.5169.definition 
   public void setFont(Font font,FontMetrics metrics)
   // ino.end
   // ino.method.setFont.5169.body 
   {;}
   // ino.end
 
   // ino.method.setText.5172.description type=line
   // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden, da sie  
   // in der abstrakten Basisklasse deklariert wurde.
   // ino.end
   // ino.method.setText.5172.definition 
   public void setText(String text)
   // ino.end
   // ino.method.setText.5172.body 
   {;}
   // ino.end
 
   // ino.method.setXp1.5175.description type=line
   // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden, da sie  
   // in der abstrakten Basisklasse deklariert wurde.
   // ino.end
   // ino.method.setXp1.5175.definition 
   public void setXp1(int xp1)
   // ino.end
   // ino.method.setXp1.5175.body 
   {;}
   // ino.end

   // ino.method.setXp2.5178.description type=line
   // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden, da sie  
   // in der abstrakten Basisklasse deklariert wurde.
   // ino.end
   // ino.method.setXp2.5178.definition 
   public void setXp2(int xp2)
   // ino.end
   // ino.method.setXp2.5178.body 
   {;}
   // ino.end

   // ino.method.setYp1.5181.description type=line
   // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden, da sie  
   // in der abstrakten Basisklasse deklariert wurde.
   // ino.end
   // ino.method.setYp1.5181.definition 
   public void setYp1(int yp1)
   // ino.end
   // ino.method.setYp1.5181.body 
   {;}
   // ino.end

   // ino.method.setYp2.5184.description type=line
   // Diese Funktion hat hier keine Bedeutung, muß aber implementiert werden, da sie  
   // in der abstrakten Basisklasse deklariert wurde.
   // ino.end
   // ino.method.setYp2.5184.definition 
   public void setYp2(int yp2)
   // ino.end
   // ino.method.setYp2.5184.body 
   {;}
   // ino.end
  
}
// ino.end


