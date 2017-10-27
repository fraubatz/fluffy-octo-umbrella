import java.awt.*;
import java.util.*;


// ino.class.SmTextLayer.1958.description type=line
// Die Klasse SmTextLayer ist die Layerschicht für alle statischen Texte und       
// Annotationen.
// ino.end
// ino.class.SmTextLayer.1958.declaration 
public class SmTextLayer extends SmLayer
// ino.end
// ino.class.SmTextLayer.1958.body
{
	// ino.attribute._textList.2536.description type=line
	// Dieses Attribut ist eine Liste aller erstellten Texte.
	// ino.end
	// ino.attribute._textList.2536.declaration 
	private SmEladoObjectList _textList;
	// ino.end


	// ino.attribute._selectList.3964.description type=line
	// Dieses Attribut ist eine Liste aller selektierten Objekte.
	// ino.end
	// ino.attribute._selectList.3964.declaration 
	private SmEladoObjectList _selectList;
	// ino.end
	
	// ino.attribute._isActivated.2546.description type=line
	// Dieses Attribut bestimmt, ob die statischen Texte und Annotationen im           
	// Zeichenfenster visualisiert werden. Sichtbar = TRUE.
	// ino.end
	// ino.attribute._isActivated.2546.declaration 
	private boolean _isActivated;
	// ino.end

	
	// ino.attribute._enableStateColor.2694.description type=line
	// Dieses Attribut bestimmt die Farbe aller statischen Texte und Annotationen,     
	// wenn diese passiv sind(_selectState = FALSE) und die Objekte in transparenter   
	// Darstellung erscheinen sollen.
	// ino.end
	// ino.attribute._enableStateColor.2694.declaration 
	private final Color _enableStateColor = Color.lightGray;
	// ino.end

	// ino.attribute._selectState.3967.description type=line
	// Dieses Attribut bestimmt, ob ein Text-Objekt selektiert werden darf.
	// ino.end
	// ino.attribute._selectState.3967.declaration 
	private boolean _selectState;
	// ino.end
	
	// ino.method.SmTextLayer.2574.description type=line
	// Der Standardkonstruktor erzeugt alle Listen und initialisiert alle anderen      
	// Attribute mit TRUE.
	// ino.end
	// ino.method.SmTextLayer.2574.definition 
	public SmTextLayer()
	// ino.end
	// ino.method.SmTextLayer.2574.body 
	{
	   _isActivated		= true;
	   _textList		= new SmEladoObjectList();
	   _selectList		= new SmEladoObjectList();
	   _selectState		= true;
	}
	// ino.end

	// ino.method.setSelectState.3970.description type=line
	// Diese Funktion setzt den Selektstatus für alle statischen Texte und             
	// Annotationen in diesem Layer.
	// ino.end
	// ino.method.setSelectState.3970.definition 
	public void setSelectState(boolean mode)
	// ino.end
	// ino.method.setSelectState.3970.body 
	{
		 _selectState = mode;
	}
	// ino.end

	// ino.method.getSelectState.3973.description type=line
	// Diese Funktion gibt den Selektstatus zurück.
	// ino.end
	// ino.method.getSelectState.3973.definition 
	public boolean getSelectState()
	// ino.end
	// ino.method.getSelectState.3973.body 
	{
		return _selectState;
	}
	// ino.end

	// ino.method.addObject.3976.description type=line
	// Diese Funktion fügt ein Text-Objekt in den Layer ein.
	// ino.end
	// ino.method.addObject.3976.definition 
	public void addObject(Object object)
	// ino.end
	// ino.method.addObject.3976.body 
	{
		_textList.appendObject(object);
	}
	// ino.end
		
	// ino.method.selectObject.2700.description type=line
	// Diese Funktion selektiert ein Objekt aus der Textliste.
	// ino.end
	// ino.method.selectObject.2700.definition 
	public void selectObject(int x,int y)
	// ino.end
	// ino.method.selectObject.2700.body 
	{
	   if (_selectState)
	   {
		  if(_selectList.getObjectCount() > 0) _selectList.removeAllObjects();
	      int i;
	      int ObjectCount = _textList.getObjectCount();
	      SmGraphicObject T;
	      Enumeration enum = _textList.getElementsOfList();
	   
	      for(i=0;i < ObjectCount;i++)
		  {
		      T = (SmGraphicObject) enum.nextElement();
		      if ((T.contains(x,y)) & (!T.getGroupState())) _selectList.appendObject(T); 
		  }
	   }
	}
	// ino.end

    // ino.method.selectObject.3979.description type=line
    // Diese Funktion selektiert mehrere Objekte in einem rechteckigen Bereich aus der 
    // Textliste.
    // ino.end
    // ino.method.selectObject.3979.definition 
    public void selectObject(int startX,int startY,int x,int y)
    // ino.end
    // ino.method.selectObject.3979.body 
    {
	   if(_selectList.getObjectCount() > 0) _selectList.removeAllObjects();
	   int i;
	   int ObjectCount = _textList.getObjectCount();
	   SmGraphicObject T;
	   Enumeration enum = _textList.getElementsOfList();
	   
	   for(i=0;i < ObjectCount;i++)
	   {
		    T = (SmGraphicObject) enum.nextElement();
		    if ( (  startX <  T.getX()				   ) &
				 (  startY <  T.getY()				   ) &
				 (       x > (T.getX() + T.getWidth()) ) &
				 (       y > (T.getY() + T.getHeight())) &
				 ( !T.getGroupState()                  )   )
			{
				_selectList.appendObject(T); 
			}
	   }
    }
    // ino.end

	// ino.method.selectObject.3982.description type=line
	// Diese Funktion selektiert alle Objekte aus der Textliste.
	// ino.end
	// ino.method.selectObject.3982.definition 
	public void selectObject()
	// ino.end
    // ino.method.selectObject.3982.body 
    {
		 if(_selectList.getObjectCount() > 0) _selectList.removeAllObjects();
	     int i;
	     int ObjectCount = _textList.getObjectCount();
	     SmGraphicObject T;
	     Enumeration enum = _textList.getElementsOfList();
	   
	     for(i=0;i < ObjectCount;i++)
		 {
		      T = (SmGraphicObject) enum.nextElement();
		      if (!T.getGroupState()) _selectList.appendObject(T); 
		 }
    }
    // ino.end
	
	// ino.method.removeObject.3985.description type=line
	// Diese Funktion löscht ein Objekt unter Angabe des Objektnamens aus der          
	// Textliste.
	// ino.end
	// ino.method.removeObject.3985.definition 
	public void removeObject(SmObject object)
	// ino.end
	// ino.method.removeObject.3985.body 
	{
		_textList.removeObjectByObject(object);	
	}
	// ino.end

	// ino.method.removeAllObjects.3988.description type=line
	// Diese Funktion löscht alle Objekte aus der Textliste.
	// ino.end
	// ino.method.removeAllObjects.3988.definition 
	public void removeAllObjects()
	// ino.end
    // ino.method.removeAllObjects.3988.body 
    {
	    _textList.removeAllObjects();
    }
    // ino.end

	// ino.method.setEnableState.2715.description type=line
	// Diese Funktion setzt den Sichtbarkeitsstatus für alle statischen Texte und      
	// Annotationen in diesem Layer.
	// ino.end
	// ino.method.setEnableState.2715.definition 
	public void setEnableState(boolean isactive)
	// ino.end
	// ino.method.setEnableState.2715.body 
	{
		_isActivated = isactive;
	}
	// ino.end

	// ino.method.setObjectList.3991.description type=line
	// Diese Funktion fügt eine bereits bestehende Textliste in den Layer ein(laden).
	// ino.end
	// ino.method.setObjectList.3991.definition 
	public void setObjectList(SmEladoObjectList objectList)
	// ino.end
	// ino.method.setObjectList.3991.body 
	{
		_textList = objectList;
	}
	// ino.end

	// ino.method.getObjectList.3994.description type=line
	// Diese Funktion gibt die Liste aller statischen Texte und Annotationen in diesem 
	// Layer zurück(speichern).
	// ino.end
	// ino.method.getObjectList.3994.definition 
	public SmEladoObjectList getObjectList()
	// ino.end
	// ino.method.getObjectList.3994.body 
	{
		return _textList;
	}
	// ino.end

	// ino.method.getSelectedObject.2733.description type=line
	// Diese Funktion gibt die Liste aller selektierten statischen Texte und           
	// Annotationen zurück.
	// ino.end
	// ino.method.getSelectedObject.2733.definition 
	public SmEladoObjectList getSelectedObject()
	// ino.end
	// ino.method.getSelectedObject.2733.body 
	{
		return _selectList;
	}
	// ino.end

	// ino.method.getMinPoint.3997.description type=line
	// Diese Funktion ermittelt die kleinste Objektposition aus der Textliste und gibt 
	// diese zurück.
	// ino.end
	// ino.method.getMinPoint.3997.definition 
	public Point getMinPoint()
	// ino.end
	// ino.method.getMinPoint.3997.body 
	{
		Point minPoint = new Point(800,800);
	    int i;
		int ObjectCount = _textList.getObjectCount();
		Enumeration enum = _textList.getElementsOfList();
		for(i=0;i < ObjectCount;i++)
		{
			SmGraphicObject T = (SmGraphicObject) enum.nextElement();
			if (T.getX() < minPoint.x) minPoint.x = T.getX();  
			if (T.getY() < minPoint.y) minPoint.y = T.getY();  
		}
		return minPoint;
	}
	// ino.end

	// ino.method.getMaxPoint.4000.description type=line
	// Diese Funktion ermittelt die größte Objektposition aus der Textliste und gibt   
	// diese zurück.
	// ino.end
	// ino.method.getMaxPoint.4000.definition 
	public Point getMaxPoint()
	// ino.end
	// ino.method.getMaxPoint.4000.body 
	{
		Point maxPoint = new Point(0,0);
		int i;
		int ObjectCount = _textList.getObjectCount();
		Enumeration enum = _textList.getElementsOfList();
		for(i=0;i < ObjectCount;i++)
		{
			SmGraphicObject T = (SmGraphicObject) enum.nextElement();
			if ((T.getX()+T.getWidth()) > maxPoint.x) maxPoint.x = T.getX()+T.getWidth();  
			if ((T.getY()+T.getHeight()) > maxPoint.y) maxPoint.y = T.getY()+T.getHeight();  
		}
		return maxPoint;
	}
	// ino.end

	// ino.method.paint.2718.description type=line
	// Diese Funktion zeichnet alle statischen Texte und Annotationen.
	// ino.end
	// ino.method.paint.2718.definition 
	public void paint(Graphics g)
	// ino.end
	// ino.method.paint.2718.body 
	{
       if ((_textList.getObjectCount() > 0) & (_isActivated))
	   {
 		  int i;
		  int ObjectCount = _textList.getObjectCount();
		  Enumeration enum = _textList.getElementsOfList();
		  for(i=0;i < ObjectCount;i++)
		  {
		     SmGraphicObject T = (SmGraphicObject) enum.nextElement();
			 T.paint(g);
		  }
	   }
	}
	// ino.end
}
// ino.end





