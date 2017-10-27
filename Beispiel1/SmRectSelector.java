import java.awt.*;
import java.util.*;
import java.io.*;


// ino.class.SmRectSelector.3133.description type=line
// Die Klasse SmRectSelektor ist von SmSelektor abgeleitet und stellt die          
// Skalierungspunkte von jedem Objekt dar(außer Linienobjekt), die hier Presenter  
// genannt werden. Ein Objekt dieser Klasse bekommt das Shape eines Objekts und    
// bestimmt damit die Positionen der Presenter. Presenter sind vom Typ             
// SmRectangleObject.
// ino.end
// ino.class.SmRectSelector.3133.declaration 
public class SmRectSelector extends SmSelector
// ino.end
// ino.class.SmRectSelector.3133.body
{
	// ino.attribute._width.4815.description type=line
	// Dieses Attribut bekommt die Breite eines Objekts.
	// ino.end
	// ino.attribute._width.4815.declaration 
	private int _width;
	// ino.end
	// ino.attribute._height.4818.description type=line
	// Dieses Attribut bekommt die Höhe eines Objekts.
	// ino.end
	// ino.attribute._height.4818.declaration 
	private int _height;
	// ino.end
	// ino.attribute._x.4821.description type=line
	// Dieses Attribut bekommt die X-Position eines Objekts.
	// ino.end
	// ino.attribute._x.4821.declaration 
	private int _x;
	// ino.end
	// ino.attribute._y.4824.description type=line
	// Dieses Attribut bekommt die Y-Position eines Objekts.
	// ino.end
	// ino.attribute._y.4824.declaration 
	private int _y;
	// ino.end
	//private SmEladoObjectList _presenterList;
	
	// ino.method.SmRectSelector.4832.description type=line
	// Der Parameterkonstruktor erzeugt alle Presenter anhand der als Paramter         
	// übergebenen Abmessungen des Objekts. Außerdem initialisiert er die Attribute der
	// Basisklasse, indem er den Konstruktor der Basisklasse explizit aufruft.
	// ino.end
	// ino.method.SmRectSelector.4832.definition 
	public SmRectSelector(int x,int y,int width,int height)
	// ino.end
	// ino.method.SmRectSelector.4832.body 
	{
		super();
		_x = x;
		_y = y;
		_width = width;
		_height = height;
		
		SmRectangleObject N  = new SmRectangleObject(_x+_width/2-SQUARE_WIDTH/2, _y-SQUARE_HEIGHT/2,
													 SQUARE_WIDTH, SQUARE_HEIGHT);
		N.setNameOfGraphicObject("north");
		N.setBackground(Color.black);
		SmRectangleObject S  = new SmRectangleObject(_x+_width/2-SQUARE_WIDTH/2, _y+_height-SQUARE_HEIGHT/2,
													 SQUARE_WIDTH, SQUARE_HEIGHT);
		S.setNameOfGraphicObject("south");
		S.setBackground(Color.black);
		SmRectangleObject O  = new SmRectangleObject(_x+_width-SQUARE_WIDTH/2, _y+_height/2-SQUARE_HEIGHT/2,
													 SQUARE_WIDTH, SQUARE_HEIGHT);
		O.setNameOfGraphicObject("east");
		O.setBackground(Color.black);
		SmRectangleObject W  = new SmRectangleObject(_x-SQUARE_WIDTH/2, _y+_height/2-SQUARE_HEIGHT/2,
													 SQUARE_WIDTH, SQUARE_HEIGHT);
		W.setNameOfGraphicObject("west");
		W.setBackground(Color.black);
		SmRectangleObject NO = new SmRectangleObject(_x+_width-SQUARE_WIDTH/2, _y-SQUARE_HEIGHT/2,
													 SQUARE_WIDTH, SQUARE_HEIGHT);
		NO.setNameOfGraphicObject("northEast");
		NO.setBackground(Color.black);
		SmRectangleObject NW = new SmRectangleObject(_x-SQUARE_WIDTH/2, _y-SQUARE_HEIGHT/2,
													 SQUARE_WIDTH, SQUARE_HEIGHT);
		NW.setNameOfGraphicObject("northWest");
		NW.setBackground(Color.black);
		SmRectangleObject SO = new SmRectangleObject(_x+_width-SQUARE_WIDTH/2, _y+_height-SQUARE_HEIGHT/2,
													 SQUARE_WIDTH, SQUARE_HEIGHT);
		SO.setNameOfGraphicObject("southEast");
		SO.setBackground(Color.black);
		SmRectangleObject SW = new SmRectangleObject(_x-SQUARE_WIDTH/2, _y+_height-SQUARE_HEIGHT/2,
													 SQUARE_WIDTH, SQUARE_HEIGHT);
		SW.setNameOfGraphicObject("southWest");
		SW.setBackground(Color.black);

		_presenterList = new SmEladoObjectList();

		_presenterList.appendObject(N);
		_presenterList.appendObject(S);
		_presenterList.appendObject(O);
		_presenterList.appendObject(W);
		_presenterList.appendObject(NO);
		_presenterList.appendObject(NW);
		_presenterList.appendObject(SO);
		_presenterList.appendObject(SW);
	}
	// ino.end

	// ino.method.paintSelector.4835.description type=line
	// Diese Funktion zeichnet die Presenter.
	// ino.end
	// ino.method.paintSelector.4835.definition 
	public void paintSelector(Graphics g)
	// ino.end
	// ino.method.paintSelector.4835.body 
	{
      if (_presenterList.getObjectCount() != 0)
	  {
 		int i;
		int ObjectCount = _presenterList.getObjectCount();
		Enumeration enum = _presenterList.getElementsOfList();
		for(i=0;i < ObjectCount;i++)
		{
			SmGraphicObject G = (SmGraphicObject) enum.nextElement();
			G.paint(g);
		}
	  }
	}
	// ino.end


	// ino.method.getSelectedPresenter.4841.description type=line
	// Diese Funktion bekommt als Parameter die Mauskoordinaten und liefert einen      
	// Presenter zurück, wenn die Position des Mauszeigers sich innerhalb dieses       
	// Presenters befindet. Zeigt die Maus auf keinen Presenter wird 'null'            
	// zurückgegeben.
	// ino.end
	// ino.method.getSelectedPresenter.4841.definition 
	public SmGraphicObject getSelectedPresenter(int x,int y)
	// ino.end
	// ino.method.getSelectedPresenter.4841.body 
	{
		SmGraphicObject _selectedPresenter = null;
		if (_presenterList.getObjectCount() != 0)
		{
 			int i;
			int ObjectCount = _presenterList.getObjectCount();
			Enumeration enum = _presenterList.getElementsOfList();
			for(i=0;i < ObjectCount;i++)
			{
				SmGraphicObject G = (SmGraphicObject) enum.nextElement();
				if (G.contains(x,y)) _selectedPresenter = G; 
			}
		}
		return _selectedPresenter;
	}
	// ino.end
}
// ino.end

