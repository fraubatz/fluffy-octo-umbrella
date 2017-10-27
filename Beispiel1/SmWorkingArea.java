import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.util.*;
import java.lang.Math;



// ino.class.SmWorkingArea.955.description type=line
// Die Klasse SmWorkingArea ist das Zeichenfenster des Symboleditors. In diesem    
// Fenster werden alle Objekte erstellt und bearbeitet, unabhängig davon um welchen
// Typ es sich handelt. Die Klasse ist Listener-Objekt für GUIEvents, MouseEvents  
// und TastaturEvents. 
// ino.end
// ino.class.SmWorkingArea.955.declaration 
public class SmWorkingArea extends Canvas implements MouseListener, MouseMotionListener, ActionListener
// ino.end
// ino.class.SmWorkingArea.955.body
{
	// ino.attribute.SINGLE_SELECT.2899.description type=line
	// Diese Konstante definiert das Selektieren einzelner Objekte mittels Mausklick.
	// ino.end
	// ino.attribute.SINGLE_SELECT.2899.declaration 
	private static final int SINGLE_SELECT = 0;
	// ino.end

	// ino.attribute.MULTI_SELECT.2902.description type=line
	// Diese Konstante definiert das Selektieren mehrerer einzelner Objekte mittels    
	// CTRL-Taste und Mausklick.
	// ino.end
	// ino.attribute.MULTI_SELECT.2902.declaration 
	private static final int MULTI_SELECT = 1;
	// ino.end

	// ino.attribute.AREA_SELECT.2905.description type=line
	// Diese Konstante definiert das Selektieren mehrerer Objekte mittels Aufziehen    
	// eines Rechtecks.
	// ino.end
	// ino.attribute.AREA_SELECT.2905.declaration 
	private static final int AREA_SELECT = 2;
	// ino.end

	// ino.attribute.SELECT_ALL.2908.description type=line
	// Dieses Attribut definiert das Selektieren aller Objekte mittels Menüleiste.
	// ino.end
	// ino.attribute.SELECT_ALL.2908.declaration 
	private static final int SELECT_ALL = 3;
	// ino.end


	// ino.attribute._selectMode.2914.description type=line
	// Dieses Attribut bestimmt die Selekt_Option in Form einer Integerkonstante.
	// ino.end
	// ino.attribute._selectMode.2914.declaration 
	private int _selectMode;
	// ino.end
	
	// ino.attribute._cursorPosX.1444.description type=line
	// Diese Attribut hält die X-Position des Cursors.
	// ino.end
	// ino.attribute._cursorPosX.1444.declaration 
	private int _cursorPosX;
	// ino.end
	
	// ino.attribute._cursorPosY.1447.description type=line
	// Diese Attribut hält die Y-Position des Cursors.
	// ino.end
	// ino.attribute._cursorPosY.1447.declaration 
	private int _cursorPosY;
	// ino.end

	// ino.attribute._startX.1358.description type=line
	// Dieses Attribut erhält die X-Koordinate des Mauszeigers, wenn die linke         
	// Maustaste gedrückt wurde.
	// ino.end
	// ino.attribute._startX.1358.declaration 
	private int _startX;
	// ino.end
	
	// ino.attribute._startY.1361.description type=line
	// Dieses Attribut erhält die Y-Koordinate des Mauszeigers, wenn die linke         
	// Maustaste gedrückt wurde.
	// ino.end
	// ino.attribute._startY.1361.declaration 
	private int _startY;
	// ino.end
	
	// ino.attribute._XPositionAfterDrawing.2917.description type=line
	// Dieses Attribut hält die aktuelle X-Position eines erstellten Objekts, vor der  
	// Aufnahme in einen Layer.
	// ino.end
	// ino.attribute._XPositionAfterDrawing.2917.declaration 
	private int _XPositionAfterDrawing;
	// ino.end

	// ino.attribute._YPositionAfterDrawing.2920.description type=line
	// Dieses Attribut hält die aktuelle Y-Position eines erstellten Objekts, vor der  
	// Aufnahme in einen Layer.
	// ino.end
	// ino.attribute._YPositionAfterDrawing.2920.declaration 
	private int _YPositionAfterDrawing;
	// ino.end
	
	
	
	// ino.attribute._width.1370.description type=line
	// Dieses Attribut hält die aktuelle Breite eines erstellten Objekts, vor der      
	// Aufnahme in einen Layer.
	// ino.end
	// ino.attribute._width.1370.declaration 
	private int _width;
	// ino.end
	
	// ino.attribute._height.1373.description type=line
	// Dieses Attribut hält die aktuelle Höhe eines erstellten Objekts, vor der        
	// Aufnahme in einen Layer.
	// ino.end
	// ino.attribute._height.1373.declaration 
	private int _height;
	// ino.end
	

	// ino.attribute._isDrawn.1379.description type=line
	// Dieses Attribut wird gesetzt(TRUE), wenn eine Zeichenoperation stattgefunden    
	// hat. Nach dem Zuordnen des gezeichneten Elements an einen Layer, wird dieses    
	// Attribut zurückgesetzt(FALSE).
	// ino.end
	// ino.attribute._isDrawn.1379.declaration 
	private boolean _isDrawn;
	// ino.end

	// ino.attribute._drawEnable.2923.description type=line
	// Dieses Attribut wird gesetzt(TRUE) wenn der Mauszeiger die Arbeitsfläche        
	// betreten hat. Beim Verlassen der Arbeitsfläche ist der Wert FALSE.
	// ino.end
	// ino.attribute._drawEnable.2923.declaration 
	private boolean _drawEnable;
	// ino.end

	// ino.attribute._gridOn.2154.description type=line
	// Dieses Attribut wird gesetzt(TRUE), wenn das Raster aktiv sein soll.
	// ino.end
	// ino.attribute._gridOn.2154.declaration 
	private boolean _gridOn;
	// ino.end

	// ino.attribute._isCatched.2157.description type=line
	// Dieses Attribut wird gesetzt(TRUE), wenn die gezeichneten Objekte vom Raster    
	// gefangen werden sollen.
	// ino.end
	// ino.attribute._isCatched.2157.declaration 
	private boolean _isCatched;
	// ino.end
	
	// ino.attribute._cursor.1382.description type=line
	// Dieses Attribut bestimmt den Cursor für die Zeichenfläche.
	// ino.end
	// ino.attribute._cursor.1382.declaration 
	private SmCursor _cursor;
	// ino.end
	// ino.attribute._pointer.1385.description type=line
	// Dieses Attribut bestimmt den Mauszeiger.
	// ino.end
	// ino.attribute._pointer.1385.declaration 
	private Cursor _pointer;
	// ino.end
	

	// ino.attribute._defaultPointer.2926.description type=line
	// Dieses Attribut bestimmt den Standard-Mauszeiger in Form eines Pfeils.
	// ino.end
	// ino.attribute._defaultPointer.2926.declaration 
	private Cursor _defaultPointer;
	// ino.end
	
	// ino.attribute._fillColor.1388.description type=line
	// Dieses Attribut bestimmt die Füllfarbe des Arbeitsbereiches und bezieht sich    
	// auf alle nachfolgenden Zeichenoperationen.
	// ino.end
	// ino.attribute._fillColor.1388.declaration 
	private Color _fillColor;
	// ino.end
	
	// ino.attribute._foreground.1391.description type=line
	// Dieses Attribut bestimmt die Vordergrundfarbe und bezieht sich auf alle         
	// nachfolgenden Zeichenoperationen.
	// ino.end
	// ino.attribute._foreground.1391.declaration 
	private Color _foreground;
	// ino.end
	
	// ino.attribute._background.1394.description type=line
	// Dieses Attribut bestimmt die Hintergrundfarbe und bezieht sich auf alle         
	// nachfolgenden Zeichenoperationen.
	// ino.end
	// ino.attribute._background.1394.declaration 
	private Color _background;
	// ino.end
	
	// ino.attribute._symbolLayer.2160.description type=line
	// Dieses Attribut ist der SymbolLayer des Editors. Er enthält Listen von allen    
	// grafischen Ausprägungen.
	// ino.end
	// ino.attribute._symbolLayer.2160.declaration 
	private SmSymbolLayer _symbolLayer;
	// ino.end

	// ino.attribute._pinLayer.2929.description type=line
	// Dieses Attribut ist der PinLayer des Editors. Er enthält Listen von allen       
	// Pin-Objekten.
	// ino.end
	// ino.attribute._pinLayer.2929.declaration 
	private SmPinLayer _pinLayer;
	// ino.end

	// ino.attribute._textLayer.2934.description type=line
	// Dieses Attribut ist der Textlayer des Editors. Er enthält Listen von allen      
	// statischen Texten und Annotationen.
	// ino.end
	// ino.attribute._textLayer.2934.declaration 
	private SmTextLayer _textLayer;
	// ino.end

	// ino.attribute._layoutLayer.2937.description type=line
	// Dieses Attribut ist der Layoutlayer des Editors. Er enthält Listen von Shapes   
	// aller Objekte.
	// ino.end
	// ino.attribute._layoutLayer.2937.declaration 
	private SmLayoutLayer _layoutLayer;
	// ino.end

	// ino.attribute._groupLayer.2942.description type=line
	// Dieses Attribut ist der Grouplayer des Editors. Er enthält Listen von allen     
	// gruppierten Objekten.
	// ino.end
	// ino.attribute._groupLayer.2942.declaration 
	private SmGroupLayer _groupLayer;
	// ino.end

	// ino.attribute._layerManager.2945.description type=line
	// Dieses Attribut ist der Layermanager des Symboleditors. Er pflegt eine Liste    
	// von allen Layern.
	// ino.end
	// ino.attribute._layerManager.2945.declaration 
	private SmLayerManager _layerManager;
	// ino.end

	// ino.attribute._grid.2166.description type=line
	// Dieses Attribut ist das Raster des Editors.
	// ino.end
	// ino.attribute._grid.2166.declaration 
	private SmGrid _grid;
	// ino.end

	// ino.attribute._symLayerOn.2948.description type=line
	// Dieses Attribut wird gesetzt(TRUE) wenn die grafischen Grundelemente sichtbar   
	// und editierbar sein sollen.
	// ino.end
	// ino.attribute._symLayerOn.2948.declaration 
	private boolean _symLayerOn;
	// ino.end

	// ino.attribute._pinLayerOn.2951.description type=line
	// Dieses Attribut wird gesetzt(TRUE) wenn die Pin-Objekte sichtbar und editierbar 
	// sein sollen.
	// ino.end
	// ino.attribute._pinLayerOn.2951.declaration 
	private boolean _pinLayerOn;
	// ino.end

	// ino.attribute._netLayerOn.2954.description type=line
	// Dieses Attribut wird gesetzt(TRUE) wenn die Netz-Objekte sichtbar und           
	// editierbar sein sollen.
	// ino.end
	// ino.attribute._netLayerOn.2954.declaration 
	private boolean _netLayerOn;
	// ino.end

	// ino.attribute._textLayerOn.2957.description type=line
	// Dieses Attribut wird gesetzt(TRUE) wenn die statischen Texte und Annotationen   
	// sichtbar und editierbar sein sollen.
	// ino.end
	// ino.attribute._textLayerOn.2957.declaration 
	private boolean _textLayerOn;
	// ino.end

	// ino.attribute._layoutLayerOn.2960.description type=line
	// Dieses Attribut wird gesetzt(TRUE) wenn die Shapes aller Objekte sichtbar sein  
	// sollen.
	// ino.end
	// ino.attribute._layoutLayerOn.2960.declaration 
	private boolean _layoutLayerOn;
	// ino.end

	// ino.attribute._command.2169.description type=line
	// Dieses Attribut enthält die 'commands' der ausgelösten Events von Werkzeug -und 
	// Menüleiste und wird beim Auftreten eines Events aktualisiert.
	// ino.end
	// ino.attribute._command.2169.declaration 
	private String _command;
	// ino.end

	// ino.attribute._pointerType.2172.description type=line
	// Dieses Attribut hält den Typ des Mauszeigers. Dieser wird in Form von           
	// Integer-Konstanten angegeben.
	// ino.end
	// ino.attribute._pointerType.2172.declaration 
	private int _pointerType;
	// ino.end
	
	// ino.attribute._gridPaintBuffer.2963.description type=line
	// Dieses Attribut ist der Rasterpuffer. Das Raster wird hier erst angelegt und    
	// dann gezeichnet.
	// ino.end
	// ino.attribute._gridPaintBuffer.2963.declaration 
	private Image _gridPaintBuffer;
	// ino.end
	
	// ino.attribute._objectPaintBuffer.2966.description type=line
	// Dieses Attribut ist der Zeichenpuffer, in dem alle grafischen Grundelemente     
	// erstmalig erstellt werden.
	// ino.end
	// ino.attribute._objectPaintBuffer.2966.declaration 
	private Image _objectPaintBuffer = null;
	// ino.end

	// ino.attribute._gridBuffer.2969.description type=line
	// Dieses Attribut ist das Graphics Object für den Rasterpuffer.
	// ino.end
	// ino.attribute._gridBuffer.2969.declaration 
	private Graphics _gridBuffer;
	// ino.end

	// ino.attribute._objectBuffer.2972.description type=line
	// Dieses Attribut ist das Graphics Object für den Zeichenpuffer.
	// ino.end
	// ino.attribute._objectBuffer.2972.declaration 
	private Graphics _objectBuffer = null;
	// ino.end


	// ino.attribute._listOfObjectsToModify.2980.description type=line
	// Dieses Attribut führt eine Liste aller selektierten Objekte.
	// ino.end
	// ino.attribute._listOfObjectsToModify.2980.declaration 
	private SmEladoObjectList _listOfObjectsToModify;
	// ino.end

	// ino.attribute._shapeOfComponent.2985.description type=line
	// Dieses Attribut ist das Shape aller erstellten Objekte(z.B. Shape eines         
	// Komponenten-Typs).
	// ino.end
	// ino.attribute._shapeOfComponent.2985.declaration 
	private SmShape _shapeOfComponent;
	// ino.end

	
	// ino.attribute._appearance.2995.description type=line
	// Dieses Attribut ist die grafische Ausprägung einer Komponente.
	// ino.end
	// ino.attribute._appearance.2995.declaration 
	private SmAppearance _appearance;
	// ino.end

	// ino.attribute._font.3000.description type=line
	// Dieses Attribut bestimmt den Zeichensatz und bezieht sich auf alle              
	// nachfolgenden Zeichenoperationen.
	// ino.end
	// ino.attribute._font.3000.declaration 
	private Font _font;
	// ino.end

	// ino.attribute._clipboardBuffer.3003.description type=line
	// Dieses Attribut ist ein selbstdefiniertes Clipboard-Objekt zur Ausführung der   
	// cut(), copy(), paste() und delete() Operationen.
	// ino.end
	// ino.attribute._clipboardBuffer.3003.declaration 
	private SmEladoObjectList _clipboardBuffer;
	// ino.end

	// ino.method.SmWorkingArea.1405.description type=line
	// Der Standardkonstruktor initialisiert alle Variablen die eine Position erhalten 
	// mit 0, Hintergrund- und Füllfarbe mit weiß, Vordergrundfarbe mit schwarz und    
	// alle Flags mit FALSE.
	// Außerdem werden die Layer-Objekte und ein Curser-Objekt erzeugt, der Mauszeiger 
	// bestimmt und die Arbeitsfläche für das Empfangen der Maus-Events registriert.
	// ino.end
	// ino.method.SmWorkingArea.1405.definition 
	public SmWorkingArea()
	// ino.end
	// ino.method.SmWorkingArea.1405.body 
	{
		_selectMode				= SINGLE_SELECT;
		_cursorPosX				= 0;
		_cursorPosY				= 0;
		_startX					= 0;
		_startY					= 0;
		_XPositionAfterDrawing	= 0;
		_YPositionAfterDrawing	= 0;
		_width					= 0;
		_height					= 0;
		_isDrawn				= false;
		_drawEnable				= false;
		_gridOn					= false;
		_isCatched				= false;
		_fillColor				= Color.white;
		_foreground				= Color.black;
		_background				= Color.white;

		_symbolLayer			= new SmSymbolLayer();
		_pinLayer				= new SmPinLayer(); 
		_textLayer				= new SmTextLayer();
		_layoutLayer			= new SmLayoutLayer();
		_groupLayer				= new SmGroupLayer();
		_layerManager			= new SmLayerManager();

		_layerManager.addLayer(_symbolLayer);
		_layerManager.addLayer(_pinLayer);
		_layerManager.addLayer(_textLayer);
		_layerManager.addLayer(_layoutLayer);
		_layerManager.addLayer(_groupLayer);

		_grid					= new SmGrid();
		_cursor					= new SmCursor();
		_font					= new Font("SansSerif", Font.PLAIN, 12);
		_appearance				= new SmAppearance();
		_shapeOfComponent		= new SmShape(0,0,0,0);
		_listOfObjectsToModify  = new SmEladoObjectList();

		_pointer				= new Cursor(Cursor.DEFAULT_CURSOR);
		_defaultPointer			= new Cursor(Cursor.DEFAULT_CURSOR);
		setCursor(_pointer);
					
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setBackground(Color.white);
	}
	// ino.end

	// ino.method.getPreferredSize.2191.description type=line
	// Diese Funktion bestimmt die Größe des Zeichenfensters.
	// ino.end
	// ino.method.getPreferredSize.2191.definition 
	public Dimension getPreferredSize()
	// ino.end
	// ino.method.getPreferredSize.2191.body 
	{
		return new Dimension (800, 800);
	}
	// ino.end

	// ino.method.mouseMoved.1408.description type=line
	// Diese Funktion aktualisiert die Maus-Koodinaten wen der Mauszeiger bewegt wird  
	// und bestimmt das Aussehen des Mauszeigers, wenn dieser sich auf ein selektiertes
	// Objekt bewegt.
	// ino.end
	// ino.method.mouseMoved.1408.definition 
	public void mouseMoved(MouseEvent e)
	// ino.end
	// ino.method.mouseMoved.1408.body 
	{
				
		SmXYDisplay.setX(e.getX());
		SmXYDisplay.setY(e.getY());
		
		_listOfObjectsToModify = _layerManager.getSelectedObjectList();
		int ObjectCount = _listOfObjectsToModify.getObjectCount();
		int i;
	    SmObject SelectedObjectByList;
	    Enumeration enum = _listOfObjectsToModify.getElementsOfList();
	    
		if (ObjectCount != 0)
		{
			for(i=0;i < ObjectCount;i++)
			{
				SelectedObjectByList = (SmObject)enum.nextElement();
				SmSelector _selector = SelectedObjectByList.getSelector();
				SmGraphicObject _presenter = _selector.getSelectedPresenter(e.getX(),e.getY());
				if (_presenter != null)
				{
					if (_presenter.getNameOfGraphicObject().equals("north"))
					{
						_pointer = new Cursor(Cursor.N_RESIZE_CURSOR);
						setCursor(_pointer);
						return;
					}
					else if (_presenter.getNameOfGraphicObject().equals("south"))
					{
						_pointer = new Cursor(Cursor.S_RESIZE_CURSOR);
						setCursor(_pointer);
						return;
					}
					else if (_presenter.getNameOfGraphicObject().equals("east"))
					{
						_pointer = new Cursor(Cursor.E_RESIZE_CURSOR);
						setCursor(_pointer);
						return;
					}
					else if (_presenter.getNameOfGraphicObject().equals("west"))
					{
						_pointer = new Cursor(Cursor.W_RESIZE_CURSOR);
						setCursor(_pointer);
						return;
					}
					else if (_presenter.getNameOfGraphicObject().equals("northEast"))
					{
						_pointer = new Cursor(Cursor.NE_RESIZE_CURSOR);
						setCursor(_pointer);
						return;
					}
					else if (_presenter.getNameOfGraphicObject().equals("northWest"))
					{
						_pointer = new Cursor(Cursor.NW_RESIZE_CURSOR);
						setCursor(_pointer);
						return;
					}
					else if (_presenter.getNameOfGraphicObject().equals("southEast"))
					{
						_pointer = new Cursor(Cursor.SE_RESIZE_CURSOR);
						setCursor(_pointer);
						return;
					}
					else if (_presenter.getNameOfGraphicObject().equals("southWest"))
					{
						_pointer = new Cursor(Cursor.SW_RESIZE_CURSOR);
						setCursor(_pointer);
						return;
					}
					else if (_presenter.getNameOfGraphicObject().equals("point1"))
					{
						_pointer = new Cursor(Cursor.SW_RESIZE_CURSOR);
						setCursor(_pointer);
						return;
					}
					else if (_presenter.getNameOfGraphicObject().equals("point2"))
					{
						_pointer = new Cursor(Cursor.NW_RESIZE_CURSOR);
						setCursor(_pointer);
						return;
					}
				
				}
				else if (SelectedObjectByList.contains(e.getX(),e.getY()))
				{
					_pointer = new Cursor(Cursor.MOVE_CURSOR);
					setCursor(_pointer);
					return;
				}
				else setCursor(_pointer = new Cursor(_pointerType));
			}
		}
	
	}
	// ino.end

	// ino.method.mouseDragged.1411.description type=line
	// Diese Funktion aktualisiert die Maus-Koodinaten wen der Mauszeiger bei          
	// gedrückter linker Maustaste bewegt wird und leitet die Zeichenoperationen für   
	// ein gewähltes grafisches Grundelement ein.
	// ino.end
	// ino.method.mouseDragged.1411.definition 
	public void mouseDragged(MouseEvent e)
	// ino.end
	// ino.method.mouseDragged.1411.body 
	{
		SmXYDisplay.setX(e.getX());
		SmXYDisplay.setY(e.getY());

		int mouseButton = e.getModifiers();
				
		if(mouseButton == 0)
		{
			Graphics g = getGraphics();

			if (_pointer.getType() == Cursor.CROSSHAIR_CURSOR)
			{
				if (_command == "ellipse")
				{
					createEllipse(g, _startX, _startY, e.getX(), e.getY());
				}
				else if (_command == "rect")
				{
					createRectangle(g, _startX, _startY, e.getX(), e.getY());
				}
				else if (_command == "line")
				{
					createLine(g, _startX, _startY, e.getX(), e.getY());
				}
				_isDrawn = true;
			return;
			}
			else 
			{			
				if (_pointer.getType() == Cursor.MOVE_CURSOR)
				{
					if (_isCatched)
					{
						 SmModify.moveObjectOnGrid(this , _grid, _listOfObjectsToModify, e.getX(), e.getY());
					}
					else
					{
						SmModify.moveObject(this, g, _listOfObjectsToModify, e.getX(), e.getY());
					}
									
				}	
				else if (_pointer.getType() == Cursor.N_RESIZE_CURSOR)
				{
					if (_isCatched)
					{
						SmModify.scaleObjectNorthOnGrid(this, g, _grid, _listOfObjectsToModify, e.getX(), e.getY());
					}
					else
					{
						SmModify.scaleObjectNorth(this, g, _listOfObjectsToModify, e.getX(), e.getY());
					}
					
				}
				else if (_pointer.getType() == Cursor.S_RESIZE_CURSOR)
				{
					
					if (_isCatched)
					{
						SmModify.scaleObjectSouthOnGrid(this, g, _grid, _listOfObjectsToModify, e.getX(), e.getY());
					}
					else
					{
						SmModify.scaleObjectSouth(this, g, _listOfObjectsToModify, e.getX(), e.getY());
					}
				}
				else if (_pointer.getType() == Cursor.E_RESIZE_CURSOR)
				{
					if (_isCatched)
					{
						SmModify.scaleObjectEastOnGrid(this, g, _grid, _listOfObjectsToModify, e.getX(), e.getY());
					}
					else
					{
						SmModify.scaleObjectEast(this, g, _listOfObjectsToModify, e.getX(), e.getY());
					}
				}
				else if (_pointer.getType() == Cursor.W_RESIZE_CURSOR)
				{
					
					if (_isCatched)
					{
						SmModify.scaleObjectWestOnGrid(this, g, _grid, _listOfObjectsToModify, e.getX(), e.getY());
					}
					else
					{
						SmModify.scaleObjectWest(this, g, _listOfObjectsToModify, e.getX(), e.getY());
					}
				}
				else if (_pointer.getType() == Cursor.NE_RESIZE_CURSOR)
				{
					if (_isCatched)
					{
						SmModify.scaleObjectNorthEastOnGrid(this, g, _grid, _listOfObjectsToModify, e.getX(), e.getY());
					}
					else
					{
						SmModify.scaleObjectNorthEast(this, g, _listOfObjectsToModify, e.getX(), e.getY());
					}
				}
				else if (_pointer.getType() == Cursor.SE_RESIZE_CURSOR)
				{
					if (_isCatched)
					{
						SmModify.scaleObjectSouthEastOnGrid(this, g, _grid, _listOfObjectsToModify, e.getX(), e.getY());
					}
					else
					{
						SmModify.scaleObjectSouthEast(this, g, _listOfObjectsToModify, e.getX(), e.getY());
					}
				}
				else if (_pointer.getType() == Cursor.NW_RESIZE_CURSOR)
				{
					if (_isCatched)
					{
						SmModify.scaleObjectNorthWestOnGrid(this, g, _grid, _listOfObjectsToModify, e.getX(), e.getY());
					}
					else
					{
						SmModify.scaleObjectNorthWest(this, g, _listOfObjectsToModify, e.getX(), e.getY());
					}
				}
				else if (_pointer.getType() == Cursor.SW_RESIZE_CURSOR)
				{
					if (_isCatched)
					{
						SmModify.scaleObjectSouthWestOnGrid(this, g, _grid, _listOfObjectsToModify, e.getX(), e.getY());
					}
					else
					{
						SmModify.scaleObjectSouthWest(this, g, _listOfObjectsToModify, e.getX(), e.getY());
					}
				}
				else if (_pointer.getType() == Cursor.DEFAULT_CURSOR)
				{

					createRectangle(g, _startX, _startY, e.getX(), e.getY());
				}
			}
		}
	}
	// ino.end
	
	// ino.method.mouseEntered.1414.description type=line
	// Diese Funktion ermöglicht das Erzeugen und Ablegen eines grafischen             
	// Grundelements, wenn sich der Mauszeiger innerhalb der Zeichenfläche befindet.
	// ino.end
	// ino.method.mouseEntered.1414.definition 
	public void mouseEntered(MouseEvent e)
	// ino.end
	// ino.method.mouseEntered.1414.body 
	{
		_drawEnable = true;
	}
	// ino.end
	
	// ino.method.mouseExited.1417.description type=line
	// Diese Funktion verbietet das Ablegen eines grafischen Grundelements, wenn sich  
	// der Mauszeiger außerhalb der Zeichenfläche befindet.
	// ino.end
	// ino.method.mouseExited.1417.definition 
	public void mouseExited(MouseEvent e)
	// ino.end
	// ino.method.mouseExited.1417.body 
	{
		_drawEnable = false;
	}
	// ino.end
	
	// ino.method.mousePressed.1420.description type=line
	// Diese Funktion setzt die Startkoordinaten des Mauszeigers, wenn die linke       
	// Maustaste gedrückt wurde.
	// ino.end
	// ino.method.mousePressed.1420.definition 
	public void mousePressed(MouseEvent e)
	// ino.end
	// ino.method.mousePressed.1420.body 
	{
		int mouseButton = e.getModifiers();
		if(mouseButton == InputEvent.BUTTON1_MASK)
		{
			_startX = e.getX();
			_startY = e.getY();
			SmModify.setOldX(e.getX());
			SmModify.setOldY(e.getY());
		}
	}
	// ino.end
	
	// ino.method.mouseReleased.1423.description type=line
	// Diese Funktion legt ein erzeugtes grafisches Grundelement in den entsprechenden 
	// Layer ab und vergibt den Focus an das erstellte Objekt.
	// ino.end
	// ino.method.mouseReleased.1423.definition 
	public void mouseReleased(MouseEvent e)
	// ino.end
	// ino.method.mouseReleased.1423.body 
	{
		int mouseButton = e.getModifiers();
		if(mouseButton == InputEvent.BUTTON1_MASK)
		{
			Graphics g = getGraphics();

			int ObjectCount = _listOfObjectsToModify.getObjectCount();
			int i;
			SmObject SelectedObjectByList;
			Enumeration enum = _listOfObjectsToModify.getElementsOfList();

			if (_drawEnable)
			{
				if (_isDrawn)
				{
					if (_command == "rect")
					{
						appendObjectToLayer(_symbolLayer, new SmRectangleObject(_XPositionAfterDrawing,
																				_YPositionAfterDrawing,
																				_width,
																				_height));
						repaint(_startX,_startY,_width+1,_height+1);
					}
					else if (_command == "ellipse")
					{
						appendObjectToLayer(_symbolLayer, new SmEllipseObject(_XPositionAfterDrawing,
																			  _YPositionAfterDrawing,
																			  _width,
																			  _height));
						repaint(_startX,_startY,_width+1,_height+1);
					}
					else if (_command == "line") 
					{
						appendObjectToLayer(_symbolLayer, new SmLineObject(_startX, _startY, 
																		   e.getX(), e.getY(), 
																		   _width, _height));
						repaint(_startX,_startY,e.getX()+1,e.getY()+1);
					}
					_isDrawn = false;
					_selectMode = SINGLE_SELECT;
					getFocusOnObject(_startX, _startY); //Objekt ist nach dem Zeichnen selektiert
				}
		
				if ((_pointer.getType() == Cursor.MOVE_CURSOR) ||
					(_pointer.getType() == Cursor.N_RESIZE_CURSOR) ||
					(_pointer.getType() == Cursor.S_RESIZE_CURSOR) ||
					(_pointer.getType() == Cursor.E_RESIZE_CURSOR) ||
					(_pointer.getType() == Cursor.W_RESIZE_CURSOR) ||
					(_pointer.getType() == Cursor.NE_RESIZE_CURSOR)||
					(_pointer.getType() == Cursor.NW_RESIZE_CURSOR)||
					(_pointer.getType() == Cursor.SE_RESIZE_CURSOR)||
					(_pointer.getType() == Cursor.SW_RESIZE_CURSOR))
				{
					for(i=0;i < ObjectCount;i++)
					{
						SelectedObjectByList = (SmObject)enum.nextElement();
						SelectedObjectByList.setIsSelected(true);
						repaint(SelectedObjectByList.getX()-4, SelectedObjectByList.getY()-4,
							    SelectedObjectByList.getWidth()+8, SelectedObjectByList.getHeight()+8); 
					}
				}
				if (_pointer.getType() == Cursor.DEFAULT_CURSOR)
				{
					_selectMode = AREA_SELECT;
					getFocusOnObject(e.getX(), e.getY());
				}
			}
			else repaint();

			_shapeOfComponent = _layerManager.getShapeOfComponent();
			_layoutLayer.removeAllObjects();
			_layoutLayer.addObject(_shapeOfComponent);
			update(g);
		}	
	}
	// ino.end
	
	// ino.method.mouseClicked.1426.description type=line
	// Dieses Funktion positioniert den Cursor auf der Zeichenfläche und selektiert    
	// ein Objekt, wenn der Mauszeiger darauf zeigt.
	// ino.end
	// ino.method.mouseClicked.1426.definition 
	public void mouseClicked(MouseEvent e)
	// ino.end
	// ino.method.mouseClicked.1426.body 
	{
		int mouseButton = e.getModifiers();
		if(mouseButton == InputEvent.BUTTON1_MASK)
		{
			_selectMode = SINGLE_SELECT;
			_cursor.setCoordinates(e.getX(), e.getY());
			_cursorPosX = e.getX();
			_cursorPosY = e.getY();
			repaint();
			if (_symbolLayer.getSelectState())
			{
				getFocusOnObject(e.getX(), e.getY());
			}
		}
	}
	// ino.end
	
	// ino.method.setAreaForeground.1429.description type=line
	// Diese Funktion setzt die Fordergrundfarbe, welche für den ganzen Arbeitsbereich 
	// gültig ist und sich auf alle nachfolgenden und momentan selektierte Objekte     
	// bezieht.
	// ino.end
	// ino.method.setAreaForeground.1429.definition 
	public void setAreaForeground(Color foreground)
	// ino.end
	// ino.method.setAreaForeground.1429.body 
	{
		_foreground = foreground;

		_listOfObjectsToModify = _layerManager.getSelectedObjectList();
		int ObjectCount = _listOfObjectsToModify.getObjectCount();
		int i;
	    SmObject SelectedObjectByList;
	    Enumeration enum = _listOfObjectsToModify.getElementsOfList();
	   	if (ObjectCount != 0)
		{
			for(i=0;i < ObjectCount;i++)
			{
				SelectedObjectByList = (SmObject)enum.nextElement();
				SelectedObjectByList.setForeground(_foreground);
				repaint(SelectedObjectByList.getX()-4,SelectedObjectByList.getY()-4,
						SelectedObjectByList.getWidth()+8,SelectedObjectByList.getHeight()+8);
			}
		}
	}
	// ino.end

	// ino.method.setAreaBackground.1432.description type=line
	// Diese Funktion setzt die Hintergrundfarbe, welche für den ganzen Arbeitsbereich 
	// gültig ist und sich auf alle nachfolgenden und momentan selektierte Objekte     
	// bezieht.
	// ino.end
	// ino.method.setAreaBackground.1432.definition 
	public void setAreaBackground(Color background)
	// ino.end
	// ino.method.setAreaBackground.1432.body 
	{
		_background = background;
	}
	// ino.end
	
	// ino.method.setAreaFillColor.1435.description type=line
	// Diese Funktion setzt die Füllfarbe, welche für den ganzen Arbeitsbereich gültig 
	// ist und sich auf alle nachfolgenden und momentan selektierte Objekte bezieht.
	// ino.end
	// ino.method.setAreaFillColor.1435.definition 
	public void setAreaFillColor(Color fillColor)
	// ino.end
	// ino.method.setAreaFillColor.1435.body 
	{
		_fillColor = fillColor;

		_listOfObjectsToModify = _layerManager.getSelectedObjectList();
		int ObjectCount = _listOfObjectsToModify.getObjectCount();
		int i;
	    SmObject SelectedObjectByList;
	    Enumeration enum = _listOfObjectsToModify.getElementsOfList();
	    
		if (ObjectCount != 0)
		{
			for(i=0;i < ObjectCount;i++)
			{
				SelectedObjectByList = (SmObject)enum.nextElement();
				SelectedObjectByList.setBackground(_fillColor);
				repaint(SelectedObjectByList.getX()-4,SelectedObjectByList.getY()-4,
						SelectedObjectByList.getWidth()+8,SelectedObjectByList.getHeight()+8);
			}
		}
		
	}
	// ino.end
	
	// ino.method.setFont.3008.description type=line
	// Diese Funktion setzt den Zeichensatz, welcher für den ganzen Arbeitsbereich     
	// gültig ist und sich auf alle nachfolgenden und momentan selektierte Text-Objekte
	// bezieht.
	// ino.end
	// ino.method.setFont.3008.definition 
	public void setFont(Font font)
	// ino.end
	// ino.method.setFont.3008.body 
	{
		_font = font;

		_listOfObjectsToModify = _layerManager.getSelectedObjectList();
		int ObjectCount = _listOfObjectsToModify.getObjectCount();
		int i;
	    SmObject SelectedObjectByList;
	    Enumeration enum = _listOfObjectsToModify.getElementsOfList();
	    
		if (ObjectCount != 0)
		{
			for(i=0;i < ObjectCount;i++)
			{
				SelectedObjectByList = (SmObject)enum.nextElement();
				SelectedObjectByList.setFont(_font, this.getFontMetrics(_font));
				repaint(SelectedObjectByList.getX()-4,SelectedObjectByList.getY()-4,
						SelectedObjectByList.getWidth()+8,SelectedObjectByList.getHeight()+8);
			}
		}
	}
	// ino.end

	// ino.method.setText.3011.description type=line
	// Diese Funktion setzt den Text für einen statischen Text oder für eine           
	// Annotation.
	// ino.end
	// ino.method.setText.3011.definition 
	public void setText(String text)
	// ino.end
	// ino.method.setText.3011.body 
	{
		_listOfObjectsToModify = _layerManager.getSelectedObjectList();
		int ObjectCount = _listOfObjectsToModify.getObjectCount();
		int i;
	    SmObject SelectedObjectByList;
	    Enumeration enum = _listOfObjectsToModify.getElementsOfList();
	    
		if (ObjectCount != 0)
		{
			for(i=0;i < ObjectCount;i++)
			{
				SelectedObjectByList = (SmObject)enum.nextElement();
				SelectedObjectByList.setText(text);
				repaint(SelectedObjectByList.getX()-4,SelectedObjectByList.getY()-4,
						SelectedObjectByList.getWidth()+8,SelectedObjectByList.getHeight()+8);
			}
		}
	}
	// ino.end


	// ino.method.appendObjectToLayer.3014.description type=line
	// Diese Funktion legt ein erstelltes Objekt in den ihr als Parameter übergebenen  
	// Layer ab.
	// ino.end
	// ino.method.appendObjectToLayer.3014.definition 
	public void appendObjectToLayer(SmLayer layer,SmObject object)
	// ino.end
	// ino.method.appendObjectToLayer.3014.body 
	{
		if (_isDrawn)
		{   
			object.setForeground(_foreground);
			object.setBackground(_fillColor);
			layer.addObject(object);
			_isDrawn = false;
		}
	}
	// ino.end

	// ino.method.paint.1441.description type=line
	// Diese Funktion zeichnet den Inhalt der Listen aller aktivierten Layer und das   
	// Raster, wenn es aktiviert wurde.
	// ino.end
	// ino.method.paint.1441.definition 
	public void paint(Graphics g)
	// ino.end
	// ino.method.paint.1441.body 
	{
		FontMetrics fontmetrics = g.getFontMetrics();

		if (_gridOn)
		{
			g.drawImage(_gridPaintBuffer,0,0,this);
		}
		_symbolLayer.paint(g);
		_pinLayer.paint(g);
		_textLayer.paint(g);
		_layoutLayer.paint(g);
		_groupLayer.paint(g);
		_cursor.paint(g);
		
	}
	// ino.end

	// ino.method.setGraphicList.2194.description type=line
	// Diese Funktion importiert eine Liste von grafischen Objekten, in den            
	// SymbolLayer (wird zum Laden eines Symbols benötigt!).
	// ino.end
	// ino.method.setGraphicList.2194.definition 
	public void setGraphicList(SmEladoObjectList objectList)
	// ino.end
	// ino.method.setGraphicList.2194.body 
	{
		_symbolLayer.setObjectList(objectList);
	}
	// ino.end

	// ino.method.getGraphicList.2197.description type=line
	// Diese Funktion liefert die aktuelle Liste der grafischen Objekte zurück (wird   
	// zum Speichern eines Symbols benötigt!).
	// ino.end
	// ino.method.getGraphicList.2197.definition 
	public SmEladoObjectList getGraphicList()
	// ino.end
	// ino.method.getGraphicList.2197.body 
	{
		return _symbolLayer.getObjectList();
	}
	// ino.end


	// ino.method.copyGraphicListsToAppearance.3023.description type=line
	// Diese Funktion transferiert alle erstellten grafischen Grundelemente in ein     
	// Objekt der Klasse SmAppearance.
	// ino.end
	// ino.method.copyGraphicListsToAppearance.3023.definition 
	public void copyGraphicListsToAppearance()
	// ino.end
	// ino.method.copyGraphicListsToAppearance.3023.body 
	{
		SmObject object;
		SmEladoObjectList graphicList = getGraphicList();
		
		int i;
	    int ObjectCount = graphicList.getObjectCount();
		int zeroPointX = _shapeOfComponent.getX();
		int zeroPointY = _shapeOfComponent.getY()+_shapeOfComponent.getHeight();
			    
	    Enumeration enum = graphicList.getElementsOfList();
		_appearance.clearAppearance();
		
		for(i=0;i < ObjectCount;i++)
		{
		      object = (SmObject) enum.nextElement();
			  if (object instanceof SmRectangleObject)
			  {	
				 
				  _appearance.appendRectangle(new SmRectangle(object.getX()-zeroPointX,
															 zeroPointY-object.getY()-object.getHeight(),
															 object.getWidth(),
															 object.getHeight()));
			  }
			  else if (object instanceof SmLineObject)
			  {
				  _appearance.appendLine(new SmLine(object.getX()-zeroPointX,
													zeroPointY-object.getY(),
													object.getX2()-zeroPointX,
													zeroPointY-object.getY2()));
			  }
			  else if (object instanceof SmEllipseObject)
			  {
				  _appearance.appendEllipse(new SmEllipse((object.getX()+object.getWidth()/2)-zeroPointX,
														  zeroPointY-(object.getY()+object.getHeight()/2),
														  object.getWidth()/2,
														  object.getHeight()/2));
			  }
			  else if (object instanceof SmTextObject)
			  {
				  _appearance.appendText(new SmText(object.getText()));
			  }
		}
		
	}
	// ino.end

	// ino.method.getAppearance.3026.description type=line
	// Diese Funktion liefert das Appearance-Objekt zurück.
	// ino.end
	// ino.method.getAppearance.3026.definition 
	public SmAppearance getAppearance()
	// ino.end
	// ino.method.getAppearance.3026.body 
	{
		return _appearance;
	}
	// ino.end
	
	// ino.method.setGrid.2200.description type=line
	// Diese Funktion setzt das Raster mit den übergebenen Parametern.
	// ino.end
	// ino.method.setGrid.2200.definition 
	public void setGrid(int Column,int row,int zeroX,int zeroY,boolean isVisible,boolean isCatched)
	// ino.end
	// ino.method.setGrid.2200.body 
	{
		_gridOn = isVisible;
		_isCatched = isCatched;
		_grid.setXDistance(row);
		_grid.setYDistance(Column);
		_grid.setXZeroPoint(zeroX);
		_grid.setYZeroPoint(zeroY);
		_grid.setGridWidth(this.getPreferredSize().width);
		_grid.setGridHeight(this.getPreferredSize().height);
		_gridPaintBuffer = this.createImage(this.getPreferredSize().width,this.getPreferredSize().height);
		_gridBuffer = _gridPaintBuffer.getGraphics();
		_grid.paint(_gridBuffer);

		repaint();
	}
	// ino.end

	// ino.method.actionPerformed.2203.description type=line
	// Diese Funktion wird beim Auslösen eines Events(Toolbar, Menüleiste) aufgerufen. 
	// Sie setzt das Attribut _command mit dem aktuellen Event.
	// ino.end
	// ino.method.actionPerformed.2203.definition 
	public void actionPerformed(ActionEvent e)
	// ino.end
	// ino.method.actionPerformed.2203.body 
	{
		_command = e.getActionCommand();
		if (_command.equals("select")) 
		{
			_pointer = new Cursor(Cursor.DEFAULT_CURSOR);
			_pointerType = Cursor.DEFAULT_CURSOR;
			setCursor(_pointer);
			_symbolLayer.setSelectState(true);
			_pinLayer.setSelectState(true);
			_groupLayer.setSelectState(true);
		}
		else if (_command.equals("rect"))
		{
			_pointer = new Cursor(Cursor.CROSSHAIR_CURSOR);
			_pointerType = Cursor.CROSSHAIR_CURSOR;
			setCursor(_pointer);
		}
		else if (_command.equals("ellipse"))
		{
			_pointer = new Cursor(Cursor.CROSSHAIR_CURSOR);
			_pointerType = Cursor.CROSSHAIR_CURSOR;
			setCursor(_pointer);
		}
		else if (_command.equals("line"))
		{
			_pointer = new Cursor(Cursor.CROSSHAIR_CURSOR);
			_pointerType = Cursor.CROSSHAIR_CURSOR;
			setCursor(_pointer);
		}
		else if (_command.equals("selectall"))
		{
			_selectMode = SELECT_ALL;
			getFocusOnObject(0,0);
		}
		else if (_command.equals("foreground"))
		{
			System.out.println("Objekt in den Vordergrund");
		}
		else if (_command.equals("background"))
		{
			System.out.println("Objekt in den Hintergrund");
		}
		else if (_command.equals("group"))
		{
			_selectMode = SINGLE_SELECT;
			_isDrawn = true;
			SmGroupObject groupObject = new SmGroupObject(_listOfObjectsToModify);
			appendObjectToLayer(_groupLayer, groupObject);
			getFocusOnObject(groupObject.getX(), groupObject.getY());
		}
		else if (_command.equals("ungroup"))
		{
			_selectMode = SELECT_ALL;
			_layerManager.ungroupObjects();
			getFocusOnObject(0,0);
		}
		else if (_command.equals("cut"))
		{
			_clipboardBuffer = new SmEladoObjectList();
			_listOfObjectsToModify = _layerManager.getSelectedObjectList();
			int ObjectCount = _listOfObjectsToModify.getObjectCount();
			int i;
			SmObject SelectedObjectByList;
			Enumeration enum = _listOfObjectsToModify.getElementsOfList();
			for(i=0;i < ObjectCount;i++)
			{
				SelectedObjectByList = (SmObject)enum.nextElement();
				_clipboardBuffer.appendObject(SelectedObjectByList);
			}
			recursiveDelete(_clipboardBuffer);

		}
		else if (_command.equals("copy"))
		{
			_clipboardBuffer = new SmEladoObjectList();
			_listOfObjectsToModify = _layerManager.getSelectedObjectList();
			int ObjectCount = _listOfObjectsToModify.getObjectCount();
			int i;
			SmObject SelectedObjectByList;
			Enumeration enum = _listOfObjectsToModify.getElementsOfList();
			for(i=0;i < ObjectCount;i++)
			{
				SelectedObjectByList = (SmObject)enum.nextElement();
				_clipboardBuffer.appendObject(SelectedObjectByList);
			}
		}
		else if (_command.equals("paste"))
		{
			int ObjectCount = _clipboardBuffer.getObjectCount();
			int i;
			int tempX = 10000;
			int tempY = 10000;
			SmObject SelectedObjectByList;
			Enumeration enum = _clipboardBuffer.getElementsOfList();
			for(i=0;i < ObjectCount;i++)
			{
				SelectedObjectByList = (SmObject)enum.nextElement();
				if(SelectedObjectByList.getX() < tempX) tempX = SelectedObjectByList.getX();
				if(SelectedObjectByList.getY() < tempY) tempY = SelectedObjectByList.getY();
			}
			recursiveCopy(_clipboardBuffer, _cursorPosX-tempX, _cursorPosY-tempY);
		}
		else if (_command.equals("delete"))
		{
			recursiveDelete(_listOfObjectsToModify);
		}
	}
	// ino.end

	// ino.method.setLayer.3029.description type=line
	// Diese Funktion schaltet, entsprechend den ihr übergebenen Parameter, die Layer  
	// Ein oder Aus.
	// ino.end
	// ino.method.setLayer.3029.definition 
	public void setLayer(boolean symLayerOn,boolean netLayerOn,boolean pinLayerOn,boolean textLayerOn,boolean layoutLayerOn)
	// ino.end
	// ino.method.setLayer.3029.body 
	{
		_symLayerOn = symLayerOn;
		_netLayerOn = netLayerOn;
		_pinLayerOn = pinLayerOn;
		_textLayerOn = textLayerOn;
		_layoutLayerOn = layoutLayerOn;

		if (!_symLayerOn)
		{
			_listOfObjectsToModify = _layerManager.getSelectedObjectList();
			int ObjectCount = _listOfObjectsToModify.getObjectCount();
			int i;
			SmObject SelectedObjectByList;
			Enumeration enum = _listOfObjectsToModify.getElementsOfList();
			_symbolLayer.setEnableState(false);

			for(i=0;i < ObjectCount;i++)
			{
				SelectedObjectByList = (SmObject)enum.nextElement();
				SelectedObjectByList.setIsSelected(false);
				repaint();
			}
		}
		else
		{
			_symbolLayer.setEnableState(true);
			repaint();
		}

		if (!_layoutLayerOn)
		{
			_layoutLayer.setEnableState(false);
			repaint();
		}
		else
		{
			_layoutLayer.setEnableState(true);
			repaint();
		}

		if (!_pinLayerOn)
		{
			_pinLayer.setEnableState(false);
			repaint();
		}
		else
		{
			_pinLayer.setEnableState(true);
			repaint();
		}

		if (!_textLayerOn)
		{
			_textLayer.setEnableState(false);
			repaint();
		}
		else
		{
			_textLayer.setEnableState(true);
			repaint();
		}
		if (!_netLayerOn)
		{
			repaint();
		}
		else
		{
			repaint();
		}
	}
	// ino.end
		
	// ino.method.createRectangle.2206.description type=line
	// Diese Funktion zeichnet ein Rechteck in den Zeichenpuffer und schreibt Position 
	// und Abmessungen in die entsprechenden Klassenmember, zur Aufnahme des Objekts in
	// den Symbollayer.
	// ino.end
	// ino.method.createRectangle.2206.definition 
	public void createRectangle(Graphics g,int x,int y,int dx,int dy)
	// ino.end
	// ino.method.createRectangle.2206.body 
	{
		g.setColor(_foreground);
		if (_objectBuffer == null)
		{
			_objectPaintBuffer = this.createImage(this.getPreferredSize().width,this.getPreferredSize().height);
			_objectBuffer = _objectPaintBuffer.getGraphics();
		}

		update(_objectBuffer);  
		_objectBuffer.setColor(g.getColor());

		if (((dx-x) > 0) & ((dy-y) > 0))
		{
			update(_objectBuffer);
			_objectBuffer.drawRect(x,y,dx-x,dy-y);
			_XPositionAfterDrawing = x;
			_YPositionAfterDrawing = y;
			_width = dx - x;
			_height = dy - y;
		}
		if (((dx-x) < 0) & ((dy-y) < 0))
		{
			update(_objectBuffer);
			_objectBuffer.drawRect(dx,dy,x-dx,y-dy);
			_XPositionAfterDrawing = dx;
			_YPositionAfterDrawing = dy;
			_width = x - dx;
			_height = y - dy;
		}
		if (((dx-x) > 0) & ((dy-y) < 0))
		{
			update(_objectBuffer);
			_objectBuffer.drawRect(x,dy,dx-x,y-dy);
			_XPositionAfterDrawing = x;
			_YPositionAfterDrawing = dy;
			_width = dx - x;
			_height = y - dy;
		}
		if (((dx-x) < 0) & ((dy-y) > 0))
		{
			update(_objectBuffer);
			_objectBuffer.drawRect(dx,y,x-dx,dy-y);
			_XPositionAfterDrawing = dx;
			_YPositionAfterDrawing = y;
			_width = x - dx;
			_height = dy - y;
		}
		g.drawImage(_objectPaintBuffer,0,0,this);
		
	}
	// ino.end
	
	// ino.method.createEllipse.2209.description type=line
	// Diese Funktion zeichnet eine Ellipse in den Zeichenpuffer und schreibt Position 
	// und Abmessungen in die entsprechenden Klassenmember, zur Aufnahme des Objekts in
	// den Symbollayer.
	// ino.end
	// ino.method.createEllipse.2209.definition 
	public void createEllipse(Graphics g,int x,int y,int dx,int dy)
	// ino.end
	// ino.method.createEllipse.2209.body 
	{
		g.setColor(_foreground);
		if (_objectBuffer == null)
		{
			_objectPaintBuffer = this.createImage(this.getPreferredSize().width,this.getPreferredSize().height);
			_objectBuffer = _objectPaintBuffer.getGraphics();
		}

		update(_objectBuffer);  
		_objectBuffer.setColor(g.getColor());

		if (((dx-x) > 0) & ((dy-y) > 0))
		{
			update(_objectBuffer);
			_objectBuffer.drawOval(x,y,dx-x,dy-y);
			_XPositionAfterDrawing = x;
			_YPositionAfterDrawing = y;
			_width = dx - x;
			_height = dy - y;
		}
		if (((dx-x) < 0) & ((dy-y) < 0))
		{
			update(_objectBuffer);
			_objectBuffer.drawOval(dx,dy,x-dx,y-dy);
			_XPositionAfterDrawing = dx;
			_YPositionAfterDrawing = dy;
			_width = x - dx;
			_height = y - dy;
		}
		if (((dx-x) > 0) & ((dy-y) < 0))
		{
			update(_objectBuffer);
			_objectBuffer.drawOval(x,dy,dx-x,y-dy);
			_XPositionAfterDrawing = x;
			_YPositionAfterDrawing = dy;
			_width = dx - x;
			_height = y - dy;
		}
		if (((dx-x) < 0) & ((dy-y) > 0))
		{
			update(_objectBuffer);
			_objectBuffer.drawOval(dx,y,x-dx,dy-y);
			_XPositionAfterDrawing = dx;
			_YPositionAfterDrawing = y;
			_width = x - dx;
			_height = dy - y;
		}
		g.drawImage(_objectPaintBuffer,0,0,this);
	}
	// ino.end
	
	// ino.method.createLine.2212.description type=line
	// Diese Funktion zeichnet eine Linie in den Zeichenpuffer und schreibt Position   
	// und Abmessungen in die entsprechenden Klassenmember, zur Aufnahme des Objekts in
	// den Symbollayer.
	// ino.end
	// ino.method.createLine.2212.definition 
	public void createLine(Graphics g,int x,int y,int dx,int dy)
	// ino.end
	// ino.method.createLine.2212.body 
	{
		g.setColor(_foreground);
		if (_objectBuffer == null)
		{
			_objectPaintBuffer = this.createImage(this.getPreferredSize().width,this.getPreferredSize().height);
			_objectBuffer = _objectPaintBuffer.getGraphics();
		}

		update(_objectBuffer);  
		_objectBuffer.setColor(g.getColor());

		if (((dx-x) > 0) & ((dy-y) > 0))
		{
			update(_objectBuffer);
			_width = dx - x;
			_height = dy - y;
		}
		if (((dx-x) < 0) & ((dy-y) < 0))
		{
			update(_objectBuffer);
			_width = x - dx;
			_height = y - dy;
		}
		if (((dx-x) > 0) & ((dy-y) < 0))
		{
			update(_objectBuffer);
			_width = dx - x;
			_height = y - dy;
		}
		if (((dx-x) < 0) & ((dy-y) > 0))
		{
			update(_objectBuffer);
			_width = x - dx;
			_height = dy - y;
		}
		
		_objectBuffer.drawLine(x,y,dx,dy);
		
		g.drawImage(_objectPaintBuffer,0,0,this);
	}
	// ino.end
	
	// ino.method.createText.3032.description type=line
	// Diese Funktion legt ein erstelltes Text-Objekt in den Textlayer ab.
	// ino.end
	// ino.method.createText.3032.definition 
	public void createText(String text)
	// ino.end
	// ino.method.createText.3032.body 
	{
		_isDrawn = true;
		appendObjectToLayer(_textLayer, new SmTextObject(text, _font, this.getFontMetrics(_font),
														 _cursor.getXCoordinate(), _cursor.getYCoordinate()));
		_selectMode = SINGLE_SELECT;
		getFocusOnObject(_cursor.getXCoordinate(), _cursor.getYCoordinate());
	}
	// ino.end
	
	// ino.method.getFocusOnObject.3035.description type=line
	// Diese Funktion löscht eine alte Selektierung und selektiert neue Objekte,       
	// anhand der ihr übergebenen Mauskoordinaten und dem Selektmodus.
	// ino.end
	// ino.method.getFocusOnObject.3035.definition 
	public void getFocusOnObject(int x,int y)
	// ino.end
	// ino.method.getFocusOnObject.3035.body 
	{
		SmObject SelectedObjectByList;

		_listOfObjectsToModify = _layerManager.getSelectedObjectList();
		int ObjectCount = _listOfObjectsToModify.getObjectCount();
		int i;
	    Enumeration enum = _listOfObjectsToModify.getElementsOfList();
		if (ObjectCount > 0)
		{
			for(i=0;i < ObjectCount;i++)
			{
				SelectedObjectByList = (SmObject)enum.nextElement();
				SelectedObjectByList.setIsSelected(false);
				repaint(SelectedObjectByList.getX()-4 ,SelectedObjectByList.getY()-4, 
					    SelectedObjectByList.getWidth()+8, SelectedObjectByList.getHeight()+8);
			}
		}
		_layerManager.selectObject(_startX, _startY, x, y, _selectMode);

		_listOfObjectsToModify = _layerManager.getSelectedObjectList();
		ObjectCount = _listOfObjectsToModify.getObjectCount();
		enum = _listOfObjectsToModify.getElementsOfList();
			
		if (ObjectCount > 0)
		{
			for(i=0;i < ObjectCount;i++)
			{
				SelectedObjectByList = (SmObject)enum.nextElement();
				SelectedObjectByList.setIsSelected(true);
				repaint(SelectedObjectByList.getX()-4,SelectedObjectByList.getY()-4,
						SelectedObjectByList.getWidth()+8,SelectedObjectByList.getHeight()+8);
			}
		}
	}
	// ino.end

	// ino.attribute.list.3038.declaration 
	SmEladoObjectList list = new SmEladoObjectList();
	// ino.end

	// ino.method.recursiveCopy.3043.description type=line
	// Diese Funktion kopiert alle Objekte aus dem Clipboard und fügt sie an der       
	// aktuellen Cursorposition ein.
	// ino.end
	// ino.method.recursiveCopy.3043.definition 
	public void recursiveCopy(SmEladoObjectList objectList,int offsetX,int offsetY)
	// ino.end
	// ino.method.recursiveCopy.3043.body 
	{
		SmEladoObjectList tempGroupCopyList = new SmEladoObjectList();
	
		int ObjectCount = objectList.getObjectCount();
		int i;
		SmObject SelectedObjectByList;
		Enumeration enum = objectList.getElementsOfList();
	    if (ObjectCount != 0)
		{
			tempGroupCopyList.removeAllObjects();
			//list.removeAllObjects();
			for(i=0;i < ObjectCount;i++)
			{
				_isDrawn = true;
				SelectedObjectByList = (SmObject) enum.nextElement();
				if (SelectedObjectByList instanceof SmRectangleObject)
				{
					SmRectangleObject rectangleObject = new SmRectangleObject((SmRectangleObject)SelectedObjectByList, offsetX, offsetY);
					appendObjectToLayer(_symbolLayer, rectangleObject);
					list.appendObject(rectangleObject);
				}
				if (SelectedObjectByList instanceof SmEllipseObject)
				{
					SmEllipseObject ellipseObject = new SmEllipseObject((SmEllipseObject)SelectedObjectByList, offsetX, offsetY);
					appendObjectToLayer(_symbolLayer, ellipseObject);
				}
				if (SelectedObjectByList instanceof SmLineObject)
				{
					SmLineObject lineObject = new SmLineObject((SmLineObject)SelectedObjectByList, offsetX, offsetY);
					appendObjectToLayer(_symbolLayer, lineObject);
				}
				if (SelectedObjectByList instanceof SmTextObject)
				{
					SmTextObject textObject = new SmTextObject((SmTextObject)SelectedObjectByList, offsetX, offsetY);
					appendObjectToLayer(_textLayer, textObject);
				}
				if (SelectedObjectByList instanceof SmGroupObject)
				{
					SmGroupObject groupObject = (SmGroupObject)SelectedObjectByList;
					tempGroupCopyList = groupObject.getGroupList();

					recursiveCopy(tempGroupCopyList, offsetX, offsetY);
					_isDrawn = true;
					
					appendObjectToLayer(_groupLayer, new SmGroupObject(list));
				}
				if (SelectedObjectByList instanceof SmPinObject)
				{
					appendObjectToLayer(_pinLayer, SelectedObjectByList);
				}
			}
		}
		_shapeOfComponent = _layerManager.getShapeOfComponent();
		_layoutLayer.removeAllObjects();
		_layoutLayer.addObject(_shapeOfComponent);
		repaint();
	}
	// ino.end

	// ino.method.recursiveDelete.3046.description type=line
	// Diese Funktion löscht alle Objekte aus dem Clipboard.
	// ino.end
	// ino.method.recursiveDelete.3046.definition 
	public void recursiveDelete(SmEladoObjectList objectList)
	// ino.end
	// ino.method.recursiveDelete.3046.body 
	{
		SmEladoObjectList tempGroupDeleteList = new SmEladoObjectList();
	
		int ObjectCount = objectList.getObjectCount();
		int i;
		SmObject SelectedObjectByList;
		Enumeration enum = objectList.getElementsOfList();
	    if (ObjectCount != 0)
		{
			for(i=0;i < ObjectCount;i++)
			{
				SelectedObjectByList = (SmObject) enum.nextElement();
				if (SelectedObjectByList instanceof SmRectangleObject)
				{
					_symbolLayer.removeObject(SelectedObjectByList);
				}
				if (SelectedObjectByList instanceof SmEllipseObject)
				{
					_symbolLayer.removeObject(SelectedObjectByList);
				}
				if (SelectedObjectByList instanceof SmLineObject)
				{
					_symbolLayer.removeObject(SelectedObjectByList);
				}
				if (SelectedObjectByList instanceof SmTextObject)
				{
					_textLayer.removeObject(SelectedObjectByList);
				}
				if (SelectedObjectByList instanceof SmGroupObject)
				{
					SmGroupObject groupObject = (SmGroupObject)SelectedObjectByList;
					tempGroupDeleteList = groupObject.getGroupList();
					recursiveDelete(tempGroupDeleteList);
					_groupLayer.removeObject(SelectedObjectByList);
				}
				if (SelectedObjectByList instanceof SmPinObject)
				{
					_pinLayer.removeObject(SelectedObjectByList);
				}
			}
		}
		_shapeOfComponent = _layerManager.getShapeOfComponent();
		_layoutLayer.removeAllObjects();
		_layoutLayer.addObject(_shapeOfComponent);
		repaint();
	}
	// ino.end

}
// ino.end






