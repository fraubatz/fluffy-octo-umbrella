import java.awt.*;
import java.util.*;


// ino.class.SmPinLayer.1929.description type=line
// Die Klasse SmPinLayer ist die Layerschicht für alle Pin-Objekte. Hier werden    
// nur die Eigenschaften von Pins abgelegt und visualisiert. Die grafischen        
// Ausprägungen von Pins werden im SymbolLayer verwaltet.
// ino.end
// ino.class.SmPinLayer.1929.declaration 
public class SmPinLayer extends SmLayer
// ino.end
// ino.class.SmPinLayer.1929.body
{
	// ino.attribute._pinList.2472.description type=line
	// Dieses Attribut ist eine Liste aller erstellten Pin-Objekte.
	// ino.end
	// ino.attribute._pinList.2472.declaration 
	private SmEladoObjectList _pinList;
	// ino.end


	// ino.attribute._selectList.3890.description type=line
	// Dieses Attribut ist eine Liste aller selektierten Objekte.
	// ino.end
	// ino.attribute._selectList.3890.declaration 
	private SmEladoObjectList _selectList;
	// ino.end

	// ino.attribute._isActivated.2482.description type=line
	// Dieses Attribut bestimmt, ob die Pin-Objekte im Zeichenfenster visualisiert     
	// werden. Sichtbar = TRUE.
	// ino.end
	// ino.attribute._isActivated.2482.declaration 
	private boolean _isActivated;
	// ino.end


	// ino.attribute._enableStateColor.2488.description type=line
	// Dieses Attribut bestimmt die Farbe aller Pin-Objekte, wenn diese passiv         
	// sind(_selectState = FALSE) und die Objekte in transparenter Darstellung         
	// erscheinen sollen.
	// ino.end
	// ino.attribute._enableStateColor.2488.declaration 
	private final Color _enableStateColor = Color.lightGray;
	// ino.end
	
	// ino.attribute._selectState.3893.description type=line
	// Dieses Attribut bestimmt, ob ein Pin-Objekt selektiert werden darf.
	// ino.end
	// ino.attribute._selectState.3893.declaration 
	private boolean _selectState;
	// ino.end

	// ino.method.SmPinLayer.2494.description type=line
	// Der Standardkonstruktor erzeugt alle Listen und initialisiert alle anderen      
	// Attribute mit TRUE.
	// ino.end
	// ino.method.SmPinLayer.2494.definition 
	public SmPinLayer()
	// ino.end
	// ino.method.SmPinLayer.2494.body 
	{
		_isActivated	= true;
		_pinList		= new SmEladoObjectList();
		_selectList		= new SmEladoObjectList();
		_selectState		= true;	
	}
	// ino.end
	
	// ino.method.setSelectState.3896.description type=line
	// Diese Funktion setzt den Selektstatus für alle Pin-Objekte in diesem Layer.
	// ino.end
	// ino.method.setSelectState.3896.definition 
	public void setSelectState(boolean mode)
	// ino.end
    // ino.method.setSelectState.3896.body 
    {
	    _selectState = mode;
    }
    // ino.end
    
    // ino.method.getSelectState.3899.description type=line
    // Diese Funktion gibt den Selektstatus zurück.
    // ino.end
    // ino.method.getSelectState.3899.definition 
    public boolean getSelectState()
    // ino.end
    // ino.method.getSelectState.3899.body 
    {
	    return _selectState;
    }
    // ino.end
   
    // ino.method.setObjectList.3902.description type=line
    // Diese Funktion fügt eine bereits bestehende Pinliste in den Layer ein(laden).
    // ino.end
    // ino.method.setObjectList.3902.definition 
    public void setObjectList(SmEladoObjectList objectList)
    // ino.end
    // ino.method.setObjectList.3902.body 
    {}
    // ino.end
  
    // ino.method.getObjectList.3905.description type=line
    // Diese Funktion gibt die Liste aller Pin-Objekte in diesem Layer                 
    // zurück(speichern).
    // ino.end
    // ino.method.getObjectList.3905.definition 
    public SmEladoObjectList getObjectList()
    // ino.end
    // ino.method.getObjectList.3905.body 
    {
		return _pinList;
	}
    // ino.end
   
	// ino.method.addObject.2503.description type=line
	// Diese Funktion fügt ein Pin-Objekt in den Layer ein.
	// ino.end
	// ino.method.addObject.2503.definition 
	public void addObject(Object object)
	// ino.end
	// ino.method.addObject.2503.body 
	{
		_pinList.appendObject(object);
	}
	// ino.end

	// ino.method.getSelectedObject.2506.description type=line
	// Diese Funktion gibt die Liste aller selektierten Pin-Objekte zurück.
	// ino.end
	// ino.method.getSelectedObject.2506.definition 
	public SmEladoObjectList getSelectedObject()
	// ino.end
	// ino.method.getSelectedObject.2506.body 
	{
	  return _selectList;
	}
	// ino.end

	// ino.method.selectObject.2509.description type=line
	// Diese Funktion selektiert ein Objekt aus der Pinliste.
	// ino.end
	// ino.method.selectObject.2509.definition 
	public void selectObject(int x,int y)
	// ino.end
    // ino.method.selectObject.2509.body 
    {
	   if (_selectState)
	   {
		 if(_selectList.getObjectCount() > 0) _selectList.removeAllObjects();
	     int i;
	     int ObjectCount = _pinList.getObjectCount();
	     SmPinObject P;
	     Enumeration enum = _pinList.getElementsOfList();
	   
	     for(i=0;i < ObjectCount;i++)
		 {
		      P = (SmPinObject) enum.nextElement();
		      if ((P.contains(x,y)) & (!P.getGroupState())) _selectList.appendObject(P); 
		 }
	   }
    }
    // ino.end
     
    // ino.method.selectObject.3908.description type=line
    // Diese Funktion selektiert mehrere Objekte in einem rechteckigen Bereich aus der 
    // Pinliste.
    // ino.end
    // ino.method.selectObject.3908.definition 
    public void selectObject(int startX,int startY,int x,int y)
    // ino.end
    // ino.method.selectObject.3908.body 
    {
	   if(_selectList.getObjectCount() > 0) _selectList.removeAllObjects();
	   int i;
	   int ObjectCount = _pinList.getObjectCount();
	   SmPinObject P;
	   Enumeration enum = _pinList.getElementsOfList();
	   
	   for(i=0;i < ObjectCount;i++)
	   {
		    P = (SmPinObject) enum.nextElement();
		    if ( (  startX <  P.getX()				   ) &
				 (  startY <  P.getY()				   ) &
				 (       x > (P.getX() + P.getWidth()) ) &
				 (       y > (P.getY() + P.getHeight())) &
				 ( !P.getGroupState()                  )   )
			{
				_selectList.appendObject(P); 
			}
	   }
    }
    // ino.end

    // ino.method.selectObject.3911.description type=line
    // Diese Funktion selektiert alle Objekte aus der Pinliste.
    // ino.end
    // ino.method.selectObject.3911.definition 
    public void selectObject()
    // ino.end
    // ino.method.selectObject.3911.body 
    {
		 if(_selectList.getObjectCount() > 0) _selectList.removeAllObjects();
	     int i;
	     int ObjectCount = _pinList.getObjectCount();
	     SmPinObject P;
	     Enumeration enum = _pinList.getElementsOfList();
	   
	     for(i=0;i < ObjectCount;i++)
		 {
		      P = (SmPinObject) enum.nextElement();
		      if (!P.getGroupState()) _selectList.appendObject(P); 
		 }
    }
    // ino.end

    // ino.method.removeObject.3914.description type=line
    // Diese Funktion löscht ein Objekt unter Angabe des Objektnamens aus der Pinliste.
    // ino.end
    // ino.method.removeObject.3914.definition 
    public void removeObject(SmObject object)
    // ino.end
    // ino.method.removeObject.3914.body 
    {
		_pinList.removeObjectByObject(object);	
	}
    // ino.end
        
	// ino.method.removeAllObjects.3917.description type=line
	// Diese Funktion löscht alle Objekte aus der Pinliste.
	// ino.end
	// ino.method.removeAllObjects.3917.definition 
	public void removeAllObjects()
	// ino.end
    // ino.method.removeAllObjects.3917.body 
    {
	    _pinList.removeAllObjects();
    }
    // ino.end

	// ino.method.setEnableState.2512.description type=line
	// Diese Funktion setzt den Sichtbarkeitsstatus für alle Pin-Objekte in diesem     
	// Layer.
	// ino.end
	// ino.method.setEnableState.2512.definition 
	public void setEnableState(boolean isactive)
	// ino.end
    // ino.method.setEnableState.2512.body 
    {
	    _isActivated = isactive;
    }
    // ino.end
  
	// ino.method.paint.2515.description type=line
	// Diese Funktion zeichnet alle Pin-Objekte.
	// ino.end
	// ino.method.paint.2515.definition 
	public void paint(Graphics g)
	// ino.end
	// ino.method.paint.2515.body 
	{
		if ((_pinList.getObjectCount() > 0) & (_isActivated))
		{
 			int i;
			int ObjectCount = _pinList.getObjectCount();
			Enumeration enum = _pinList.getElementsOfList();
			for(i=0;i < ObjectCount;i++)
			{
				SmPinObject P = (SmPinObject) enum.nextElement();
				P.paint(g);
			}
		}
	}
	// ino.end
	
	// ino.method.getMinPoint.3920.description type=line
	// Diese Funktion ermittelt die kleinste Objektposition aus der Pinliste und gibt  
	// diese zurück.
	// ino.end
	// ino.method.getMinPoint.3920.definition 
	public Point getMinPoint()
	// ino.end
	// ino.method.getMinPoint.3920.body 
	{
		return new Point(800,800);
	}
	// ino.end

	// ino.method.getMaxPoint.3923.description type=line
	// Diese Funktion ermittelt die größte Objektposition aus der Pinliste und gibt    
	// diese zurück.
	// ino.end
	// ino.method.getMaxPoint.3923.definition 
	public Point getMaxPoint()
	// ino.end
	// ino.method.getMaxPoint.3923.body 
	{
		return  new Point(0,0);
	}
	// ino.end
}
// ino.end





