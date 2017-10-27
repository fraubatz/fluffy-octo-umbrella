import java.awt.*;
import java.util.*;
import java.awt.datatransfer.*;
import SmEladoObjectList;
import SmGroupObject;



// ino.class.SmLayerManager.1295.description type=line
// Die Klasse SmLayerManager verwaltet hauptsächlich die Selektierung und          
// Gruppierung von Objekten.
// ino.end
// ino.class.SmLayerManager.1295.declaration 
public class SmLayerManager
// ino.end
// ino.class.SmLayerManager.1295.body
{
	// ino.attribute._layerList.2355.description type=line
	// Dieses Attribut führt eine Liste aller Layer.
	// ino.end
	// ino.attribute._layerList.2355.declaration 
	private SmEladoObjectList _layerList;
	// ino.end

	// ino.attribute._selectList.3751.description type=line
	// Dieses Attribut führt eine Liste aller selektierten Objekte.
	// ino.end
	// ino.attribute._selectList.3751.declaration 
	private SmEladoObjectList _selectList;
	// ino.end

	// ino.attribute._groupList.3756.description type=line
	// Dieses Attribut führt eine Liste aller gruppierten Objekte.
	// ino.end
	// ino.attribute._groupList.3756.declaration 
	private SmEladoObjectList _groupList;
	// ino.end


	// ino.attribute._selectState.3761.description type=line
	// Dieses Attribut wird gsetzt(TRUE), wenn das Selektieren von Objekten erlaubt    
	// ist.
	// ino.end
	// ino.attribute._selectState.3761.declaration 
	private boolean _selectState;
	// ino.end
	// ino.attribute._xOld.4045.description type=line
	// Diese Attribut hält die X-Koordinate des zuletzt selektierten Objekts.
	// ino.end
	// ino.attribute._xOld.4045.declaration 
	private int _xOld;
	// ino.end
	
	// ino.attribute._yOld.4048.description type=line
	// Diese Attribut hält die Y-Koordinate des zuletzt selektierten Objekts.
	// ino.end
	// ino.attribute._yOld.4048.declaration 
	private int _yOld;
	// ino.end
	



	// ino.attribute.SINGLE_SELECT.3770.description type=line
	// Diese Konstante definiert das Selektieren einzelner Objekte mittels Mausklick.
	// ino.end
	// ino.attribute.SINGLE_SELECT.3770.declaration 
	private static final int SINGLE_SELECT = 0;
	// ino.end
	// ino.attribute.MULTI_SELECT.3773.description type=line
	// Diese Konstante definiert das Selektieren mehrerer einzelner Objekte mittels    
	// CTRL-Taste und Mausklick.
	// ino.end
	// ino.attribute.MULTI_SELECT.3773.declaration 
	private static final int MULTI_SELECT = 1;
	// ino.end
	// ino.attribute.AREA_SELECT.3776.description type=line
	// Diese Konstante definiert das Selektieren mehrerer Objekte mittels Aufziehen    
	// eines Rechtecks.
	// ino.end
	// ino.attribute.AREA_SELECT.3776.declaration 
	private static final int AREA_SELECT = 2;
	// ino.end
	// ino.attribute.SELECT_ALL.3779.description type=line
	// Dieses Attribut definiert das Selektieren aller Objekte mittels Menüleiste.
	// ino.end
	// ino.attribute.SELECT_ALL.3779.declaration 
	private static final int SELECT_ALL = 3;
	// ino.end

	// ino.attribute._listIndex.3785.description type=line
	// Dieses Attribut hält die Anzahl mehrerer übereinander liegender Objekte für     
	// eine bestimmte Position des Mauszeigers.
	// ino.end
	// ino.attribute._listIndex.3785.declaration 
	private static int _listIndex;
	// ino.end

    // ino.method.SmLayerManager.2374.description type=line
    // Der Standardkonstruktor erzeugt alle Listen und initialisiert alle Attribute.
    // ino.end
    // ino.method.SmLayerManager.2374.definition 
    public SmLayerManager()
    // ino.end
    // ino.method.SmLayerManager.2374.body 
    {
		_layerList = new SmEladoObjectList();
		_selectList = new SmEladoObjectList();
		_groupList = new SmEladoObjectList();
		_selectState = true;
		_xOld = 0;
		_yOld = 0;
		_listIndex = 0;
	}
    // ino.end

	// ino.method.addLayer.2377.description type=line
	// Diese Funktion fügt einen Layer in die Layerliste ein.
	// ino.end
	// ino.method.addLayer.2377.definition 
	public void addLayer(SmLayer layer)
	// ino.end
	// ino.method.addLayer.2377.body 
	{
		_layerList.appendObject(layer);
	}
	// ino.end
	
	// ino.method.selectObject.3788.description type=line
	// Diese Funktion entscheidet, anhand des übergebenen Selektmodes, auf welche Art  
	// die Objekte selektiert werden sollen.
	// ino.end
	// ino.method.selectObject.3788.definition 
	public void selectObject(int startX,int startY,int x,int y,int selectMode)
	// ino.end
	// ino.method.selectObject.3788.body 
	{
		switch(selectMode)
		{
			case SINGLE_SELECT :	selectObjectByMouseClick(x, y); break;
			case MULTI_SELECT  :	selectObjectByKeyboard(x, y);	break;
			case AREA_SELECT   :	selectObjectByMouseDrag(startX, startY, x, y); break;
			case SELECT_ALL	   :	selectAllObjects(); break;	
		
			default :	break;
		}
	}
	// ino.end

	// ino.method.selectObjectByMouseClick.3791.description type=line
	// Diese Funktion selektiert einzelne Objekte per Mausklick und toggelt alle       
	// Objekte, die bei einer bestimmten Mausposition übereinander liegen.
	// ino.end
	// ino.method.selectObjectByMouseClick.3791.definition 
	public void selectObjectByMouseClick(int x,int y)
	// ino.end
	// ino.method.selectObjectByMouseClick.3791.body 
	{
		SmEladoObjectList tempList = new SmEladoObjectList();
		
		if (_selectList.getObjectCount() > 0) _selectList.removeAllObjects();
			
		int i;
		int layerCount = _layerList.getObjectCount();
		Enumeration enumLayer = _layerList.getElementsOfList();
		SmLayer L;
		for (i=0;i<layerCount;i++)
		{
			L = (SmLayer) enumLayer.nextElement();
			L.selectObject(x,y);
			if (L.getSelectedObject().getObjectCount() > 0)
			{
				int j;
				int objectCount = L.getSelectedObject().getObjectCount();
				Enumeration enumObject = L.getSelectedObject().getElementsOfList();
				SmObject O;
				for (j=0;j<objectCount;j++)
				{
					O = (SmObject)enumObject.nextElement();
					tempList.appendObject(O);
					
				}
			}
		}
				
		if ((_xOld != x) || (_yOld != y) || (_listIndex == tempList.getObjectCount()))
		{
			_listIndex = 0;
		}
		
		if (tempList.getObjectCount() > 0)
		{
			_selectList.appendObject(tempList.getObjectByListNumber(_listIndex));
		}
		_listIndex++;
		
		_xOld = x;
		_yOld = y;
	}
	// ino.end

	// ino.method.selectObjectByMouseDrag.3794.description type=line
	// Diese Funktion selektiert alle Objekte, die sich innerhalb eines aufgezogenen   
	// Rechtecks befinden.
	// ino.end
	// ino.method.selectObjectByMouseDrag.3794.definition 
	public void selectObjectByMouseDrag(int startX,int startY,int x,int y)
	// ino.end
	// ino.method.selectObjectByMouseDrag.3794.body 
	{
		SmEladoObjectList tempList = new SmEladoObjectList();
		if (_selectList.getObjectCount() > 0) _selectList.removeAllObjects();
			
		int i;
		int layerCount = _layerList.getObjectCount();
		Enumeration enumLayer = _layerList.getElementsOfList();
		SmLayer L;
		for (i=0;i<layerCount;i++)
		{
			L = (SmLayer) enumLayer.nextElement();
			L.selectObject(startX, startY, x, y);
			tempList = L.getSelectedObject();
			int j;
			int objectCount = tempList.getObjectCount();
			Enumeration enumObject = tempList.getElementsOfList();
			SmObject O;
			for (j=0;j<objectCount;j++)
			{
				O = (SmObject)enumObject.nextElement();
				_selectList.appendObject(O);
			}
		}
	}
	// ino.end
	
	// ino.method.selectObjectByKeyboard.3797.description type=line
	// Diese Funktion selektiert mehrere einzelne Objekte mittels CTRL-Taste.
	// ino.end
	// ino.method.selectObjectByKeyboard.3797.definition 
	public void selectObjectByKeyboard(int x,int y)
	// ino.end
	// ino.method.selectObjectByKeyboard.3797.body 
	{

	}
	// ino.end

	// ino.method.selectAllObjects.3800.description type=line
	// Diese Funktion selektiert alle Objekte.
	// ino.end
	// ino.method.selectAllObjects.3800.definition 
	public void selectAllObjects()
	// ino.end
	// ino.method.selectAllObjects.3800.body 
	{
		SmEladoObjectList tempList = new SmEladoObjectList();
		if (_selectList.getObjectCount() > 0) _selectList.removeAllObjects();
			
		int i;
		int layerCount = _layerList.getObjectCount();
		Enumeration enumLayer = _layerList.getElementsOfList();
		SmLayer L;
		for (i=0;i<layerCount;i++)
		{
			L = (SmLayer) enumLayer.nextElement();
			L.selectObject();
			tempList = L.getSelectedObject();
			int j;
			int objectCount = tempList.getObjectCount();
			Enumeration enumObject = tempList.getElementsOfList();
			SmObject O;
			for (j=0;j<objectCount;j++)
			{
				O = (SmObject)enumObject.nextElement();
				_selectList.appendObject(O);
			}
		}
	}
	// ino.end


	// ino.method.groupObjects.3806.description type=line
	// Diese Funktion gruppiert alle selektierten Objekte.
	// ino.end
	// ino.method.groupObjects.3806.definition 
	public void groupObjects()
	// ino.end
	// ino.method.groupObjects.3806.body 
	{
		SmGroupObject groupObject = new SmGroupObject(_selectList);
		_selectList.removeAllObjects();
		_selectList.appendObject(groupObject);
		_groupList.appendObject(groupObject);
	}
	// ino.end

	// ino.method.ungroupObjects.3809.description type=line
	// Diese Funktion macht eine Gruppierung wieder rückgängig.
	// ino.end
	// ino.method.ungroupObjects.3809.definition 
	public void ungroupObjects()
	// ino.end
	// ino.method.ungroupObjects.3809.body 
	{
		SmGroupObject objectToUngroup = null;
		SmLayer groupLayer = null;
		int i;
		int layerCount = _layerList.getObjectCount();
		Enumeration enumLayer = _layerList.getElementsOfList();
		SmLayer L;
		for (i=0;i<layerCount;i++)
		{
			L = (SmLayer) enumLayer.nextElement();
			if (L instanceof SmGroupLayer)
			{
				groupLayer = L;
			}
		}
		
		SmEladoObjectList groupList = groupLayer.getObjectList();
		int j;
		int objectCount = groupList.getObjectCount();
		Enumeration enumGroup = groupList.getElementsOfList();
		SmGroupObject G;
		for (j=0;j<objectCount;j++)
		{
			G = (SmGroupObject) enumGroup.nextElement();
			if (G.getIsSelected()) objectToUngroup = G;
		}

		SmEladoObjectList memberOfGroupObject = objectToUngroup.getGroupList();
		int k;
		int memberCount = memberOfGroupObject.getObjectCount();
		Enumeration enumMember = memberOfGroupObject.getElementsOfList();
		SmObject O;
		for (k=0;k<memberCount;k++)
		{
			O = (SmObject) enumMember.nextElement();
			O.setGroupState(false);
			//O.setIsSelected(true);
			_selectList.appendObject(O);
		}
		groupList.removeObjectByObject(objectToUngroup);
	}
	// ino.end
	
	// ino.method.getSelectedObjectList.3812.description type=line
	// Diese Funktion gibt eine Liste aller selektierten Objekte zurück.
	// ino.end
	// ino.method.getSelectedObjectList.3812.definition 
	public SmEladoObjectList getSelectedObjectList()
	// ino.end
	// ino.method.getSelectedObjectList.3812.body 
	{
		return _selectList;
	}
	// ino.end

		
	// ino.method.getSelectState.3815.description type=line
	// Diese Funktion liefert den Selektstatus zurück.
	// ino.end
	// ino.method.getSelectState.3815.definition 
	public boolean getSelectState()
	// ino.end
	// ino.method.getSelectState.3815.body 
	{
		return _selectState;
	}
	// ino.end


	// ino.method.getShapeOfComponent.3821.description type=line
	// Diese Funktion bestimmt das Shape aller erstellten Objekte und liefert es       
	// zurück.
	// ino.end
	// ino.method.getShapeOfComponent.3821.definition 
	public SmShape getShapeOfComponent()
	// ino.end
	// ino.method.getShapeOfComponent.3821.body 
	{
		Point minPoint = new Point(800,800);
		Point maxPoint = new Point(0,0);
		int i;
		int layerCount = _layerList.getObjectCount();
		Enumeration enumLayer = _layerList.getElementsOfList();
		SmLayer L;
		for (i=0;i<layerCount;i++)
		{
			L = (SmLayer) enumLayer.nextElement();
			if (L.getMinPoint().x < minPoint.x) minPoint.x = L.getMinPoint().x;
			if (L.getMinPoint().y < minPoint.y) minPoint.y = L.getMinPoint().y;
			if (L.getMaxPoint().x > maxPoint.x) maxPoint.x = L.getMaxPoint().x;
			if (L.getMaxPoint().y > maxPoint.y) maxPoint.y = L.getMaxPoint().y;
		}

		SmShape shapeOfComponent = new SmShape(minPoint.x,minPoint.y,maxPoint.x-minPoint.x,
												maxPoint.y-minPoint.y);
		shapeOfComponent.setForeground(Color.green);
		
		return shapeOfComponent;
	}
	// ino.end
}
// ino.end






