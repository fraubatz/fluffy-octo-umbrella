import java.awt.*;
import java.util.*;
import SmEladoObjectList;


// ino.class.SmLayoutLayer.1926.description type=line
// Die Klasse SmLayoutLayer ist die Layerschicht für die Shapes aller erstellten   
// Objekte.
// ino.end
// ino.class.SmLayoutLayer.1926.declaration 
public class SmLayoutLayer extends SmLayer
// ino.end
// ino.class.SmLayoutLayer.1926.body
{
	// ino.attribute._layoutList.2326.description type=line
	// Dieses Attribut ist eine Liste aller erstellten Shapes.
	// ino.end
	// ino.attribute._layoutList.2326.declaration 
	private SmEladoObjectList _layoutList;
	// ino.end


	// ino.attribute._selectList.3825.description type=line
	// Dieses Attribut ist eine Liste aller selektierten Objekte.
	// ino.end
	// ino.attribute._selectList.3825.declaration 
	private SmEladoObjectList _selectList;
	// ino.end

	// ino.attribute._isActivated.2336.description type=line
	// Dieses Attribut bestimmt, ob die Shapes im Zeichenfenster visualisiert werden.  
	// Sichtbar = TRUE.
	// ino.end
	// ino.attribute._isActivated.2336.declaration 
	private boolean _isActivated;
	// ino.end


	// ino.attribute._enableStateColor.2616.description type=line
	// Dieses Attribut bestimmt die Farbe aller Shapes, wenn diese passiv              
	// sind(_selectState = FALSE) und die Objekte in transparenter Darstellung         
	// erscheinen sollen.
	// ino.end
	// ino.attribute._enableStateColor.2616.declaration 
	private final Color _enableStateColor;
	// ino.end

	// ino.attribute._selectState.3828.description type=line
	// Dieses Attribut bestimmt, ob ein Shape-Objekt selektiert werden darf.
	// ino.end
	// ino.attribute._selectState.3828.declaration 
	private boolean _selectState;
	// ino.end

	// ino.method.SmLayoutLayer.2571.description type=line
	// Der Standardkonstruktor erzeugt alle Listen und initialisiert alle anderen      
	// Attribute mit TRUE.
	// ino.end
	// ino.method.SmLayoutLayer.2571.definition 
	public SmLayoutLayer()
	// ino.end
	// ino.method.SmLayoutLayer.2571.body 
	{
	   _layoutList		= new SmEladoObjectList();
	   _selectList		= new SmEladoObjectList();
	   _isActivated		= true;
	   _enableStateColor = Color.lightGray;
	}
	// ino.end
	
	// ino.method.selectObject.2598.description type=line
	// Diese Funktion selektiert ein Objekt aus der Shapeliste.
	// ino.end
	// ino.method.selectObject.2598.definition 
	public void selectObject(int x,int y)
	// ino.end
	// ino.method.selectObject.2598.body 
	{

	}
	// ino.end
	
	// ino.method.selectObject.3831.description type=line
	// Diese Funktion selektiert mehrere Objekte in einem rechteckigen Bereich aus der 
	// Shapeliste.
	// ino.end
	// ino.method.selectObject.3831.definition 
	public void selectObject(int startX,int startY,int x,int y)
	// ino.end
	// ino.method.selectObject.3831.body 
	{

	}
	// ino.end

	// ino.method.selectObject.3834.description type=line
	// Diese Funktion selektiert alle Objekte aus der Shapeliste.
	// ino.end
	// ino.method.selectObject.3834.definition 
	public void selectObject()
	// ino.end
	// ino.method.selectObject.3834.body 
	{

	}
	// ino.end

	// ino.method.removeObject.3837.description type=line
	// Diese Funktion löscht ein Objekt unter Angabe des Objektnamens aus der          
	// Shapeliste.
	// ino.end
	// ino.method.removeObject.3837.definition 
	public void removeObject(SmObject object)
	// ino.end
	// ino.method.removeObject.3837.body 
	{
		_layoutList.removeObjectByObject(object);	
	}
	// ino.end

	// ino.method.removeAllObjects.3840.description type=line
	// Diese Funktion löscht alle Objekte aus der Shapeliste.
	// ino.end
	// ino.method.removeAllObjects.3840.definition 
	public void removeAllObjects()
	// ino.end
    // ino.method.removeAllObjects.3840.body 
    {
	    _layoutList.removeAllObjects();
    }
    // ino.end

	// ino.method.setEnableState.2613.description type=line
	// Diese Funktion setzt den Sichtbarkeitsstatus für alle Shapes in diesem Layer.
	// ino.end
	// ino.method.setEnableState.2613.definition 
	public void setEnableState(boolean isActive)
	// ino.end
	// ino.method.setEnableState.2613.body 
	{
		_isActivated = isActive;
	}
	// ino.end

	// ino.method.paint.2589.description type=line
	// Diese Funktion zeichnet alle Shapes.
	// ino.end
	// ino.method.paint.2589.definition 
	public void paint(Graphics g)
	// ino.end
	// ino.method.paint.2589.body 
	{
		if ((_layoutList.getObjectCount() != 0) & (_isActivated))
		{
 			int i;
			int ObjectCount = _layoutList.getObjectCount();
			Enumeration enum = _layoutList.getElementsOfList();
			for(i=0;i < ObjectCount;i++)
			{
				
				SmLayoutObject R = (SmLayoutObject) enum.nextElement();
				R.paint(g);
			}
		}
	}
	// ino.end

	// ino.method.setSelectState.3843.description type=line
	// Diese Funktion setzt den Selektstatus für alle Shapes in diesem Layer.
	// ino.end
	// ino.method.setSelectState.3843.definition 
	public void setSelectState(boolean mode)
	// ino.end
	// ino.method.setSelectState.3843.body 
	{}
	// ino.end
	
	// ino.method.getSelectState.3846.description type=line
	// Diese Funktion gibt den Selektstatus zurück.
	// ino.end
	// ino.method.getSelectState.3846.definition 
	public boolean getSelectState()
	// ino.end
	// ino.method.getSelectState.3846.body 
	{
		return _selectState;
	}
	// ino.end
	
	// ino.method.setObjectList.3849.description type=line
	// Diese Funktion fügt eine bereits bestehende Shapeliste in den Layer ein(laden).
	// ino.end
	// ino.method.setObjectList.3849.definition 
	public void setObjectList(SmEladoObjectList objectList)
	// ino.end
	// ino.method.setObjectList.3849.body 
	{}
	// ino.end

	// ino.method.getObjectList.3852.description type=line
	// Diese Funktion gibt die Liste aller Shape-Objekte in diesem Layer               
	// zurück(speichern).
	// ino.end
	// ino.method.getObjectList.3852.definition 
	public SmEladoObjectList getObjectList()
	// ino.end
	// ino.method.getObjectList.3852.body 
	{
		return _layoutList;
	}
	// ino.end
	
	// ino.method.getSelectedObject.2601.description type=line
	// Diese Funktion gibt die Liste aller selektierten Shape-Objekte zurück.
	// ino.end
	// ino.method.getSelectedObject.2601.definition 
	public SmEladoObjectList getSelectedObject()
	// ino.end
	// ino.method.getSelectedObject.2601.body 
	{
		return _selectList;
	}
	// ino.end

	// ino.method.addObject.2610.description type=line
	// Diese Funktion fügt ein Shape-Objekt in den Layer ein.
	// ino.end
	// ino.method.addObject.2610.definition 
	public void addObject(Object object)
	// ino.end
	// ino.method.addObject.2610.body 
	{
		_layoutList.appendObject(object);
	}
	// ino.end
	
	// ino.method.getMinPoint.3855.description type=line
	// Diese Funktion ermittelt die kleinste Objektposition aus der Shapeliste und     
	// gibt diese zurück.
	// ino.end
	// ino.method.getMinPoint.3855.definition 
	public Point getMinPoint()
	// ino.end
	// ino.method.getMinPoint.3855.body 
	{
		return new Point(800,800);
	}
	// ino.end

	// ino.method.getMaxPoint.3858.description type=line
	// Diese Funktion ermittelt die größte Objektposition aus der Shapeliste und gibt  
	// diese zurück.
	// ino.end
	// ino.method.getMaxPoint.3858.definition 
	public Point getMaxPoint()
	// ino.end
	// ino.method.getMaxPoint.3858.body 
	{
		return new Point(0,0);
	}
	// ino.end
}
// ino.end





