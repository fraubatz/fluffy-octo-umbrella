import java.awt.*;
import java.util.*;


// ino.class.SmSymbolLayer.1181.description type=line
// Die Klasse SmSymbolLayer ist die Layerschicht für alle grafischen               
// Grundelemente(Rechtecke, Linien, Ellipsen).
// ino.end
// ino.class.SmSymbolLayer.1181.declaration 
public class SmSymbolLayer extends SmLayer
// ino.end
// ino.class.SmSymbolLayer.1181.body
{
   // ino.attribute._graphicList.1546.description type=line
   // Dieses Attribut ist eine Liste aller erstellten grafischen Grundelemente.
   // ino.end
   // ino.attribute._graphicList.1546.declaration 
   private SmEladoObjectList _graphicList;
   // ino.end
  
  
   // ino.attribute._selectList.3927.description type=line
   // Dieses Attribut ist eine Liste aller selektierten Objekte.
   // ino.end
   // ino.attribute._selectList.3927.declaration 
   private SmEladoObjectList _selectList;
   // ino.end
 
   // ino.attribute._isActivated.1551.description type=line
   // Dieses Attribut bestimmt, ob die grafischen Grundelemente im Zeichenfenster     
   // visualisiert werden. Sichtbar = TRUE.
   // ino.end
   // ino.attribute._isActivated.1551.declaration 
   private boolean _isActivated;
   // ino.end
 
   
   // ino.attribute._enableStateColor.1554.description type=line
   // Dieses Attribut bestimmt die Farbe aller grafischen Grundelemente, wenn diese   
   // passiv sind(_selectState = FALSE) und die Objekte in transparenter Darstellung  
   // erscheinen sollen.
   // ino.end
   // ino.attribute._enableStateColor.1554.declaration 
   private final Color _enableStateColor = Color.lightGray;
   // ino.end
   
   // ino.attribute._selectState.3930.description type=line
   // Dieses Attribut bestimmt, ob ein grafisches Grundelement selektiert werden darf.
   // ino.end
   // ino.attribute._selectState.3930.declaration 
   private boolean _selectState;
   // ino.end
  
   // ino.method.SmSymbolLayer.1541.description type=line
   // Der Standardkonstruktor erzeugt alle Listen und initialisiert alle anderen      
   // Attribute mit TRUE.
   // ino.end
   // ino.method.SmSymbolLayer.1541.definition 
   public SmSymbolLayer()
   // ino.end
   // ino.method.SmSymbolLayer.1541.body 
   {
	   _isActivated    = true;
	   _graphicList    = new SmEladoObjectList();
	   _selectList	   = new SmEladoObjectList();
	   _selectState	   = true;	
   }
   // ino.end
  
   // ino.method.setSelectState.3933.description type=line
   // Diese Funktion setzt den Selektstatus für alle grafischen Grundelemente in      
   // diesem Layer.
   // ino.end
   // ino.method.setSelectState.3933.definition 
   public void setSelectState(boolean mode)
   // ino.end
   // ino.method.setSelectState.3933.body 
   {
	   _selectState = mode;
   }
   // ino.end
 
   // ino.method.getSelectState.3936.description type=line
   // Diese Funktion gibt den Selektstatus zurück.
   // ino.end
   // ino.method.getSelectState.3936.definition 
   public boolean getSelectState()
   // ino.end
   // ino.method.getSelectState.3936.body 
   {
	   return _selectState;
   }
   // ino.end
   
   // ino.method.addObject.2519.description type=line
   // Diese Funktion fügt ein grafisches Grundelement in den Layer ein.
   // ino.end
   // ino.method.addObject.2519.definition 
   public void addObject(Object object)
   // ino.end
   // ino.method.addObject.2519.body 
   {
	   _graphicList.appendObject(object);
   }
   // ino.end

   // ino.method.selectObject.1510.description type=line
   // Diese Funktion selektiert ein Objekt aus der Grafikliste.
   // ino.end
   // ino.method.selectObject.1510.definition 
   public void selectObject(int x,int y)
   // ino.end
   // ino.method.selectObject.1510.body 
   {
	   if (_selectState)
	   {
		 if(_selectList.getObjectCount() > 0) _selectList.removeAllObjects();
	     int i;
	     int ObjectCount = _graphicList.getObjectCount();
	     SmGraphicObject G;
	     Enumeration enum = _graphicList.getElementsOfList();
	   
	     for(i=0;i < ObjectCount;i++)
		 {
		      G = (SmGraphicObject) enum.nextElement();
		      if ((G.contains(x,y)) & (!G.getGroupState())) _selectList.appendObject(G); 
		 }
	     
	   }
   }
   // ino.end
    
   // ino.method.selectObject.3939.description type=line
   // Diese Funktion selektiert mehrere Objekte in einem rechteckigen Bereich aus der 
   // Grafikliste.
   // ino.end
   // ino.method.selectObject.3939.definition 
   public void selectObject(int startX,int startY,int x,int y)
   // ino.end
   // ino.method.selectObject.3939.body 
   {
	   if(_selectList.getObjectCount() > 0) _selectList.removeAllObjects();
	   int i;
	   int ObjectCount = _graphicList.getObjectCount();
	   SmGraphicObject G;
	   Enumeration enum = _graphicList.getElementsOfList();
	   
	   for(i=0;i < ObjectCount;i++)
	   {
		    G = (SmGraphicObject) enum.nextElement();
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

   // ino.method.selectObject.3942.description type=line
   // Diese Funktion selektiert alle Objekte aus der Grafikliste.
   // ino.end
   // ino.method.selectObject.3942.definition 
   public void selectObject()
   // ino.end
   // ino.method.selectObject.3942.body 
   {
		 if(_selectList.getObjectCount() > 0) _selectList.removeAllObjects();
	     int i;
	     int ObjectCount = _graphicList.getObjectCount();
	     SmGraphicObject G;
	     Enumeration enum = _graphicList.getElementsOfList();
	   
	     for(i=0;i < ObjectCount;i++)
		 {
		      G = (SmGraphicObject) enum.nextElement();
		      if (!G.getGroupState()) _selectList.appendObject(G); 
		 }
   }
   // ino.end
  
   // ino.method.removeObject.3945.description type=line
   // Diese Funktion löscht ein Objekt unter Angabe des Objektnamens aus der          
   // Grafikliste.
   // ino.end
   // ino.method.removeObject.3945.definition 
   public void removeObject(SmObject object)
   // ino.end
   // ino.method.removeObject.3945.body 
   {
	   _graphicList.removeObjectByObject(object);	
   }
   // ino.end
  
   // ino.method.removeAllObjects.3948.description type=line
   // Diese Funktion löscht alle Objekte aus der Grafikliste.
   // ino.end
   // ino.method.removeAllObjects.3948.definition 
   public void removeAllObjects()
   // ino.end
   // ino.method.removeAllObjects.3948.body 
   {
	   _graphicList.removeAllObjects();
   }
   // ino.end

   // ino.method.setEnableState.1560.description type=line
   // Diese Funktion setzt den Sichtbarkeitsstatus für alle grafischen Grundelemente  
   // in diesem Layer.
   // ino.end
   // ino.method.setEnableState.1560.definition 
   public void setEnableState(boolean isactive)
   // ino.end
   // ino.method.setEnableState.1560.body 
   {
	   _isActivated = isactive;
   }
   // ino.end

   // ino.method.setObjectList.3951.description type=line
   // Diese Funktion fügt eine bereits bestehende Grafikliste in den Layer ein(laden).
   // ino.end
   // ino.method.setObjectList.3951.definition 
   public void setObjectList(SmEladoObjectList objectList)
   // ino.end
   // ino.method.setObjectList.3951.body 
   {
	   _graphicList = objectList;
   }
   // ino.end
  
   // ino.method.getObjectList.3954.description type=line
   // Diese Funktion gibt die Liste aller grafischen Grundelemente in diesem Layer    
   // zurück(speichern).
   // ino.end
   // ino.method.getObjectList.3954.definition 
   public SmEladoObjectList getObjectList()
   // ino.end
   // ino.method.getObjectList.3954.body 
   {
	   return _graphicList;
   }
   // ino.end
   
   // ino.method.getSelectedObject.2141.description type=line
   // Diese Funktion gibt die Liste aller selektierten grafischen Grundelemente       
   // zurück.
   // ino.end
   // ino.method.getSelectedObject.2141.definition 
   public SmEladoObjectList getSelectedObject()
   // ino.end
   // ino.method.getSelectedObject.2141.body 
   {
	   return _selectList;
   }
   // ino.end

   // ino.method.getMinPoint.3957.description type=line
   // Diese Funktion ermittelt die kleinste Objektposition aus der Liste der          
   // grafischen Grundelemente und gibt diese zurück.
   // ino.end
   // ino.method.getMinPoint.3957.definition 
   public Point getMinPoint()
   // ino.end
   // ino.method.getMinPoint.3957.body 
   {
	    Point minPoint = new Point(800,800);
	    int i;
		int ObjectCount = _graphicList.getObjectCount();
		Enumeration enum = _graphicList.getElementsOfList();
		for(i=0;i < ObjectCount;i++)
		{
			SmGraphicObject G = (SmGraphicObject) enum.nextElement();
			if (G.getX() < minPoint.x) minPoint.x = G.getX();  
			if (G.getY() < minPoint.y) minPoint.y = G.getY();  
		}
		return minPoint;
   }
   // ino.end

   // ino.method.getMaxPoint.3960.description type=line
   // Diese Funktion ermittelt die größte Objektposition aus der Liste der grafischen 
   // Grundelemente und gibt diese zurück.
   // ino.end
   // ino.method.getMaxPoint.3960.definition 
   public Point getMaxPoint()
   // ino.end
   // ino.method.getMaxPoint.3960.body 
   {
	    Point maxPoint = new Point(0,0);
		int i;
		int ObjectCount = _graphicList.getObjectCount();
		Enumeration enum = _graphicList.getElementsOfList();
		for(i=0;i < ObjectCount;i++)
		{
			SmGraphicObject G = (SmGraphicObject) enum.nextElement();
			if ((G.getX()+G.getWidth()) > maxPoint.x) maxPoint.x = G.getX()+G.getWidth();  
			if ((G.getY()+G.getHeight()) > maxPoint.y) maxPoint.y = G.getY()+G.getHeight();  
		}
		return maxPoint;
   }
   // ino.end

   // ino.method.paint.1563.description type=line
   // Diese Funktion zeichnet alle grafischen Grundelemente.
   // ino.end
   // ino.method.paint.1563.definition 
   public void paint(Graphics g)
   // ino.end
   // ino.method.paint.1563.body 
   {
      if ((_graphicList.getObjectCount() > 0) & (_isActivated))
	  {
 		int i;
		int ObjectCount = _graphicList.getObjectCount();
		Enumeration enum = _graphicList.getElementsOfList();
		for(i=0;i < ObjectCount;i++)
		{
			SmGraphicObject R = (SmGraphicObject) enum.nextElement();
			R.paint(g);
		}
	  }
   }
   // ino.end
}
// ino.end





















