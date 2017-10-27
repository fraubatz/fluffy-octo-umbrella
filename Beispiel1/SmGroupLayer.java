import java.awt.*;
import java.util.*;


// ino.class.SmGroupLayer.3122.description type=line
// Die Klasse SmGroupLayer ist die Layerschicht für alle Gruppenobjekte.
// ino.end
// ino.class.SmGroupLayer.3122.declaration 
public class SmGroupLayer extends SmLayer
// ino.end
// ino.class.SmGroupLayer.3122.body
{
    // ino.attribute._groupList.3676.description type=line
    // Dieses Attribut ist eine Liste aller erstellten Gruppenobjekte.
    // ino.end
    // ino.attribute._groupList.3676.declaration 
    private SmEladoObjectList _groupList;
    // ino.end
    // ino.attribute._selectList.3686.description type=line
    // Dieses Attribut ist eine Liste aller selektierten Gruppenobjekte.
    // ino.end
    // ino.attribute._selectList.3686.declaration 
    private SmEladoObjectList _selectList;
    // ino.end
    // ino.attribute._isActivated.3691.description type=line
    // Dieses Attribut bestimmt, ob die Gruppenobjekte im Zeichenfenster visualisiert  
    // werden. Sichtbar = TRUE.
    // ino.end
    // ino.attribute._isActivated.3691.declaration 
    private boolean _isActivated;
    // ino.end
    // ino.attribute._enableStateColor.3697.description type=line
    // Dieses Attribut bestimmt die Farbe aller Gruppenobjekte, wenn diese passiv      
    // sind(_selectState = FALSE) und die Objekte in transparenter Darstellung         
    // erscheinen sollen.
    // ino.end
    // ino.attribute._enableStateColor.3697.declaration 
    private final Color _enableStateColor = Color.lightGray;
    // ino.end
    // ino.attribute._selectState.3700.description type=line
    // Dieses Attribut bestimmt, ob ein Gruppenobjekt selektiert werden darf.
    // ino.end
    // ino.attribute._selectState.3700.declaration 
    private boolean _selectState;
    // ino.end

	// ino.method.SmGroupLayer.3703.description type=line
	// Der Standardkonstruktor erzeugt alle Listen und initialisiert alle anderen      
	// Attribute mit TRUE.
	// ino.end
	// ino.method.SmGroupLayer.3703.definition 
	public SmGroupLayer()
	// ino.end
	// ino.method.SmGroupLayer.3703.body 
	{
	   _isActivated    = true;
	   _groupList    = new SmEladoObjectList();
	   _selectList	   = new SmEladoObjectList();
	   _selectState	   = true;
	}
	// ino.end
   
	// ino.method.addObject.3706.description type=line
	// Diese Funktion fügt ein Gruppenobjekt in den Layer ein.
	// ino.end
	// ino.method.addObject.3706.definition 
	public void addObject(Object object)
	// ino.end
	// ino.method.addObject.3706.body 
	{
		_groupList.appendObject(object);
	}
	// ino.end
	
	// ino.method.removeObject.3709.description type=line
	// Diese Funktion löscht ein Objekt unter Angabe des Objektnamens aus der          
	// Gruppierungsliste.
	// ino.end
	// ino.method.removeObject.3709.definition 
	public void removeObject(SmObject object)
	// ino.end
	// ino.method.removeObject.3709.body 
	{
		_groupList.removeObjectByObject(object);	
	}
	// ino.end
	
	// ino.method.removeAllObjects.3712.description type=line
	// Diese Funktion löscht alle Objekte aus der Gruppierungsliste.
	// ino.end
	// ino.method.removeAllObjects.3712.definition 
	public void removeAllObjects()
	// ino.end
	// ino.method.removeAllObjects.3712.body 
	{
		_groupList.removeAllObjects();
	}
	// ino.end
	
	// ino.method.setSelectState.3715.description type=line
	// Diese Funktion setzt den Selektstatus für alle Gruppenobjekte in diesem Layer.
	// ino.end
	// ino.method.setSelectState.3715.definition 
	public void setSelectState(boolean mode)
	// ino.end
	// ino.method.setSelectState.3715.body 
	{
	   _selectState = mode;
   	}
	// ino.end

	// ino.method.getSelectState.3718.description type=line
	// Diese Funktion gibt den Selektstatus zurück.
	// ino.end
	// ino.method.getSelectState.3718.definition 
	public boolean getSelectState()
	// ino.end
	// ino.method.getSelectState.3718.body 
	{
		return _selectState;
	}
	// ino.end
	
	// ino.method.selectObject.3721.description type=line
	// Diese Funktion selektiert ein Objekt aus der Gruppierungsliste.
	// ino.end
	// ino.method.selectObject.3721.definition 
	public void selectObject(int x,int y)
	// ino.end
	// ino.method.selectObject.3721.body 
	{
		if (_selectState)
		{
			if(_selectList.getObjectCount() > 0) _selectList.removeAllObjects();
			int i;
			int ObjectCount = _groupList.getObjectCount();
			SmGroupObject G;
			Enumeration enum = _groupList.getElementsOfList();
	   
			for(i=0;i < ObjectCount;i++)
			{
				  G = (SmGroupObject) enum.nextElement();
				  if ((G.contains(x,y)) & (!G.getGroupState())) _selectList.appendObject(G); 
			}
	   	}
	}
	// ino.end
	
	// ino.method.selectObject.3724.description type=line
	// Diese Funktion selektiert mehrere Objekte in einem rechteckigen Bereich aus der 
	// Gruppierungsliste.
	// ino.end
	// ino.method.selectObject.3724.definition 
	public void selectObject(int startX,int startY,int x,int y)
	// ino.end
	// ino.method.selectObject.3724.body 
	{
	   if(_selectList.getObjectCount() > 0) _selectList.removeAllObjects();
	   int i;
	   int ObjectCount = _groupList.getObjectCount();
	   SmGroupObject G;
	   Enumeration enum = _groupList.getElementsOfList();
	   
	   for(i=0;i < ObjectCount;i++)
	   {
		    G = (SmGroupObject) enum.nextElement();
		    if ( (  startX <  G.getX()				   ) &
				 (  startY <  G.getY()				   ) &
				 (       x > (G.getX() + G.getWidth()) ) &
				 (       y > (G.getY() + G.getHeight())) &
				 ( !G.getGroupState()                  )   )
			{
				_selectList.appendObject(G); 
			}
	   }
   }
	// ino.end
	
	// ino.method.selectObject.3727.description type=line
	// Diese Funktion selektiert alle Objekte aus der Gruppierungsliste.
	// ino.end
	// ino.method.selectObject.3727.definition 
	public void selectObject()
	// ino.end
	// ino.method.selectObject.3727.body 
	{
		 if(_selectList.getObjectCount() > 0) _selectList.removeAllObjects();
	     int i;
	     int ObjectCount = _groupList.getObjectCount();
	     SmGroupObject G;
	     Enumeration enum = _groupList.getElementsOfList();
	   
	     for(i=0;i < ObjectCount;i++)
		 {
		      G = (SmGroupObject) enum.nextElement();
		      if (!G.getGroupState()) _selectList.appendObject(G); 
		 }
    }
	// ino.end
		
	// ino.method.getSelectedObject.3730.description type=line
	// Diese Funktion gibt die Liste aller selektierten, Gruppenobjekte zurück.
	// ino.end
	// ino.method.getSelectedObject.3730.definition 
	public SmEladoObjectList getSelectedObject()
	// ino.end
	// ino.method.getSelectedObject.3730.body 
	{
		return _selectList;
	}
	// ino.end
	
	// ino.method.getObjectList.3733.description type=line
	// Diese Funktion gibt die Liste aller gruppierten Objekte in diesem Layer         
	// zurück(speichern).
	// ino.end
	// ino.method.getObjectList.3733.definition 
	public SmEladoObjectList getObjectList()
	// ino.end
	// ino.method.getObjectList.3733.body 
	{
		return _groupList;
	}
	// ino.end

	// ino.method.setObjectList.3736.description type=line
	// Diese Funktion fügt eine bereits bestehende Gruppierungsliste in den Layer      
	// ein(laden).
	// ino.end
	// ino.method.setObjectList.3736.definition 
	public void setObjectList(SmEladoObjectList objectList)
	// ino.end
	// ino.method.setObjectList.3736.body 
	{
		_groupList = objectList;
	}
	// ino.end

	// ino.method.setEnableState.3739.description type=line
	// Diese Funktion setzt den Sichtbarkeitsstatus für alle Gruppenobjekte in diesem  
	// Layer.
	// ino.end
	// ino.method.setEnableState.3739.definition 
	public void setEnableState(boolean isactive)
	// ino.end
    // ino.method.setEnableState.3739.body 
    {
	    _isActivated = isactive;
    }
    // ino.end

	// ino.method.getMinPoint.3742.description type=line
	// Diese Funktion ermittelt die größte Objektposition aus der Gruppierungsliste    
	// und gibt diese zurück.
	// ino.end
	// ino.method.getMinPoint.3742.definition 
	public Point getMinPoint()
	// ino.end
	// ino.method.getMinPoint.3742.body 
	{
	    Point minPoint = new Point(800,800);
	    int i;
		int ObjectCount = _groupList.getObjectCount();
		Enumeration enum = _groupList.getElementsOfList();
		for(i=0;i < ObjectCount;i++)
		{
			SmGroupObject G = (SmGroupObject) enum.nextElement();
			if (G.getX() < minPoint.x) minPoint.x = G.getX();  
			if (G.getY() < minPoint.y) minPoint.y = G.getY();  
		}
		return minPoint;
    }
	// ino.end

	// ino.method.getMaxPoint.3745.description type=line
	// Diese Funktion ermittelt die kleinste Objektposition aus der Gruppierungsliste  
	// und gibt diese zurück.
	// ino.end
	// ino.method.getMaxPoint.3745.definition 
	public Point getMaxPoint()
	// ino.end
	// ino.method.getMaxPoint.3745.body 
	{
	    Point maxPoint = new Point(0,0);
		int i;
		int ObjectCount = _groupList.getObjectCount();
		Enumeration enum = _groupList.getElementsOfList();
		for(i=0;i < ObjectCount;i++)
		{
			SmGroupObject G = (SmGroupObject) enum.nextElement();
			if ((G.getX()+G.getWidth()) > maxPoint.x) maxPoint.x = G.getX()+G.getWidth();  
			if ((G.getY()+G.getHeight()) > maxPoint.y) maxPoint.y = G.getY()+G.getHeight();  
		}
		return maxPoint;
    }
	// ino.end

	// ino.method.paint.3748.description type=line
	// Diese Funktion zeichnet alle Gruppenobjekte.
	// ino.end
	// ino.method.paint.3748.definition 
	public void paint(Graphics g)
	// ino.end
    // ino.method.paint.3748.body 
    {
		if ((_groupList.getObjectCount() > 0) & (_isActivated))
		{
 			int i;
			int ObjectCount = _groupList.getObjectCount();
			Enumeration enum = _groupList.getElementsOfList();
			for(i=0;i < ObjectCount;i++)
			{
				SmGroupObject G = (SmGroupObject) enum.nextElement();
				G.paint(g);
			}
		}
    }
    // ino.end

}
// ino.end

