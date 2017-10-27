import java.awt.*;
import java.io.*;
import java.util.*;
import SmEladoObjectList;



// ino.class.SmSelector.1641.description type=line
// Die Klasse SmSelektor stellt die Skalierungspunkte eines Linienobjekts dar, die 
// hier Presenter genannt werden. Ein Objekt dieser Klasse bekommt die Koordinaten 
// des Anfangs -und Endpunktes eines Linienobjekts und bestimmt damit die          
// Positionen der Presenter. Presenter sind vom Typ SmRectangleObject.
// ino.end
// ino.class.SmSelector.1641.declaration 
public class SmSelector implements Serializable
// ino.end
// ino.class.SmSelector.1641.body
{

	// ino.attribute.SQUARE_WIDTH.4845.description type=line
	// Diese Konstante bestimmt die Breite eines Presenters.
	// ino.end
	// ino.attribute.SQUARE_WIDTH.4845.declaration 
	protected static final int SQUARE_WIDTH = 6;
	// ino.end
	// ino.attribute.SQUARE_HEIGHT.4848.description type=line
	// Diese Konstante bestimmt die Höhe eines Presenters.
	// ino.end
	// ino.attribute.SQUARE_HEIGHT.4848.declaration 
	protected static final int SQUARE_HEIGHT = 6;
	// ino.end

	// ino.attribute._x1.4851.description type=line
	// Dieses Attribut hält den Mittelpunkt der Breite des 1. Presenters.
	// ino.end
	// ino.attribute._x1.4851.declaration 
	private int _x1;
	// ino.end
	// ino.attribute._y1.4854.description type=line
	// Dieses Attribut hält den Mittelpunkt der Höhe des 1. Presenters.
	// ino.end
	// ino.attribute._y1.4854.declaration 
	private int _y1;
	// ino.end
	// ino.attribute._x2.4857.description type=line
	// Dieses Attribut hält den Mittelpunkt der Breite des 2. Presenters.
	// ino.end
	// ino.attribute._x2.4857.declaration 
	private int _x2;
	// ino.end
	// ino.attribute._y2.4860.description type=line
	// Dieses Attribut hält den Mittelpunkt der Breite des 2. Presenters.
	// ino.end
	// ino.attribute._y2.4860.declaration 
	private int _y2;
	// ino.end
	// ino.attribute._presenterList.1882.description type=line
	// Dieses Attribut enthält die Liste aller Presenter.
	// ino.end
	// ino.attribute._presenterList.1882.declaration 
	protected SmEladoObjectList _presenterList;
	// ino.end

	// ino.method.SmSelector.4863.description type=line
	// Der Standardkonstruktor tut nichts.
	// ino.end
	// ino.method.SmSelector.4863.definition 
	public SmSelector()
	// ino.end
	// ino.method.SmSelector.4863.body 
	{

	}
	// ino.end

	// ino.method.SmSelector.1887.description type=line
	// Der Parameterkonstruktor initialisiert die Positionen der Presenter mit den     
	// übergebenen Werten.
	// ino.end
	// ino.method.SmSelector.1887.definition 
	public SmSelector(int x1,int y1,int x2,int y2)
	// ino.end
	// ino.method.SmSelector.1887.body 
	{
		_x1 = x1;
		_y1 = y1;
		_x2 = x2;
		_y2 = y2;

		SmRectangleObject P1 = new SmRectangleObject(_x1-SQUARE_WIDTH/2, _y1-SQUARE_HEIGHT/2, SQUARE_WIDTH, SQUARE_HEIGHT);
		P1.setNameOfGraphicObject("point1");
		P1.setBackground(Color.black);
		SmRectangleObject P2 = new SmRectangleObject(_x2-SQUARE_WIDTH/2, _y2-SQUARE_HEIGHT/2, SQUARE_WIDTH, SQUARE_HEIGHT);
		P2.setNameOfGraphicObject("point2");
		P2.setBackground(Color.black);

		_presenterList = new SmEladoObjectList();
		_presenterList.appendObject(P1);
		_presenterList.appendObject(P2);

	}
	// ino.end

	// ino.method.paintSelector.1890.description type=line
	// Diese Funktion zeichnet die Presenter.
	// ino.end
	// ino.method.paintSelector.1890.definition 
	public void paintSelector(Graphics g)
	// ino.end
	// ino.method.paintSelector.1890.body 
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


	// ino.method.getSelectedPresenter.1896.description type=line
	// Diese Funktion bekommt als Parameter die Mauskoordinaten und liefert einen      
	// Presenter zurück, wenn die Position des Mauszeigers sich innerhalb dieses       
	// Presenters befindet. Zeigt die Maus auf keinen Presenter wird 'null'            
	// zurückgegeben.
	// ino.end
	// ino.method.getSelectedPresenter.1896.definition 
	public SmGraphicObject getSelectedPresenter(int x,int y)
	// ino.end
	// ino.method.getSelectedPresenter.1896.body 
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
