import java.awt.*;
import java.awt.event.*;


// ino.class.ColorField.3052.description type=line
// Die Klasse Colorfield ist ein Benutzerdefinierte GUI-Komponente, die ein Button 
// mit Farbinformation darstellt. Bewegt sich der Mauszeiger über diese Komponente,
// so wird die Komponente grafisch hervorgehoben. Ein Mausklick auf diese          
// Komponente 'drückt' den Button und das Farbfeld in der Mitte, ist bereit für die
// Aufnahme einer neuen Farbinformation. Nach der Änderung der Farbinformation wird
// der Button wieder inaktiv. Objekte dieser Klasse werden für die Gestaltung des  
// Dialogs 'Farbattribute' verwendet(siehe DlgColorPnl). 
// ino.end
// ino.class.ColorField.3052.declaration 
public class ColorField extends Canvas implements MouseListener
// ino.end
// ino.class.ColorField.3052.body
{
	// ino.attribute._color.3273.description type=line
	// Dieses Attribut hält die aktuelle Farbinformation.
	// ino.end
	// ino.attribute._color.3273.declaration 
	private Color _color;
	// ino.end
	// ino.attribute._x.3276.description type=line
	// Dieses Attribut enthält die X-Koordinate des Farbfelds innerhalb des Buttons.
	// ino.end
	// ino.attribute._x.3276.declaration 
	private int _x;
	// ino.end
	// ino.attribute._y.3279.description type=line
	// Dieses Attribut enthält die Y-Koordinate des Farbfelds innerhalb des Buttons.
	// ino.end
	// ino.attribute._y.3279.declaration 
	private int _y;
	// ino.end
	// ino.attribute._width.3282.description type=line
	// Dieses Attribut enthält die Breite des Farbfelds innerhalb des Buttons.
	// ino.end
	// ino.attribute._width.3282.declaration 
	private int _width;
	// ino.end
	// ino.attribute._height.3285.description type=line
	// Dieses Attribut enthält Höhe des Farbfelds innerhalb des Buttons.
	// ino.end
	// ino.attribute._height.3285.declaration 
	private int _height;
	// ino.end
	// ino.attribute._isSelected.3288.description type=line
	// Diese Attribut enthält die Information, ob der Button gedrückt wurde(aktiv      
	// ist). Aktiv = TRUE, inaktiv = FALSE.
	// ino.end
	// ino.attribute._isSelected.3288.declaration 
	private boolean _isSelected;
	// ino.end
	// ino.attribute._isEntered.3291.description type=line
	// Diese Attribut enthält die Information, ob ein Mauszeiger sich innerhalb des    
	// Buttons befindet.Innerhalb = TRUE, Auserhalb = FALSE.
	// ino.end
	// ino.attribute._isEntered.3291.declaration 
	private boolean _isEntered;
	// ino.end
	// ino.attribute._isEditable.3294.description type=line
	// Diese Attribut bestimmt, ob die Farbinformation des Buttons geändert werden     
	// darf. Änderung erlaubt = TRUE, sonst = FALSE.
	// ino.end
	// ino.attribute._isEditable.3294.declaration 
	private boolean _isEditable;
	// ino.end
	// ino.attribute._boundrect.3297.description type=line
	// Dieses Attribut ist ein Rahmen um den Button.
	// ino.end
	// ino.attribute._boundrect.3297.declaration 
	private Rectangle _boundrect;
	// ino.end

	private DlgColorPnl _parentPanel;
	// ino.method.ColorField.3305.description type=line
	// Der Parameterkonstruktor erzeugt ein Colorfield-Objekt mit Bezug auf ein        
	// Mutter-Panel und gesetzten Bearbeitungsstatus.
	// ino.end
	// ino.method.ColorField.3305.definition 
	public ColorField(DlgColorPnl parentPanel,boolean isEditable)
	// ino.end
	// ino.method.ColorField.3305.body 
	{
	
		_color		= Color.white;
		_isEntered	= false;
		_isSelected = false;
		_isEditable = isEditable;

		this.addMouseListener(this);
		this.setBackground(Color.lightGray);

		_parentPanel = parentPanel;
	}
	// ino.end

	// ino.method.ColorField.3308.description type=line
	// Der Parameterkonstruktor erzeugt ein ColorField-Objekt ohne Bezug auf ein       
	// Mutter-Panel. Der Bearbeitungsstatus ist FALSE.
	// ino.end
	// ino.method.ColorField.3308.definition 
	public ColorField()
	// ino.end
	// ino.method.ColorField.3308.body 
	{
		_color		= Color.white;
		_isEntered	= false;
		_isSelected = false;
		_isEditable = false;

		this.addMouseListener(this);
		this.setBackground(Color.lightGray);

		_parentPanel = null;
	}
	// ino.end

	// ino.method.update.3311.description type=line
	// Diese Funktion ruft die paint-Methode auf. Sie hat höhere Priorität.
	// ino.end
	// ino.method.update.3311.definition 
	public void update(Graphics g)
	// ino.end
	// ino.method.update.3311.body 
	{
		paint(g);
	}
	// ino.end

	// ino.method.paint.3314.description type=line
	// Diese Funktion zeichnet den Button unter Berücksichtigung des Selektstatus und  
	// Lage des Mauszeigers.
	// ino.end
	// ino.method.paint.3314.definition 
	public void paint(Graphics g)
	// ino.end
	// ino.method.paint.3314.body 
	{
		_boundrect = this.getBounds();
		_x = _boundrect.x;
		_y = _boundrect.y;
		_width = _boundrect.width;
		_height = _boundrect.height;

		g.setColor(Color.lightGray);
		g.fillRect(_x, _y, _width, _height);
		g.setColor(_color);
		g.fillRect(_x+5, _y+5, _width-10, _height-10);
		g.setColor(Color.black);
		g.drawRect(_x+5, _y+5, _width-10, _height-10);

		if (_isSelected)
		{
			g.setColor(Color.gray);
			g.drawLine(_x, _y, _x+_width-2, _y              );
			g.drawLine(_x, _y, _x           , _y + _height-2);

			g.setColor(Color.black);
			g.drawLine(_x+1, _y+1, _x+_width-3, _y+1              );
			g.drawLine(_x+1, _y+1, _x+1           , _y + _height-3);

			g.setColor(Color.lightGray);
			g.drawLine(_x+1, _y+_height-2, _x+_width-2, _y+_height-2);
			g.drawLine(_x+_width-2, _y+1, _x+_width-2,  _y+_height-2);

			g.setColor(Color.white);
			g.drawLine(_x, _y+_height-1, _x+_width-1, _y+_height-1);
			g.drawLine(_x+_width-1, _y, _x+_width-1,  _y+_height-1);
		}
		
		if(_isEntered && !_isSelected) 
		{
			g.setColor(Color.white);
			g.drawLine(_x, _y, _x+_width-2, _y              );
			g.drawLine(_x, _y, _x           , _y + _height-2);
	
			g.setColor(Color.gray);
			g.drawLine(_x+1, _y+_height-2, _x+_width-2, _y+_height-2);
			g.drawLine(_x+_width-2, _y+1, _x+_width-2,  _y+_height-2);

			g.setColor(Color.black);
			g.drawLine(_x, _y+_height-1, _x+_width-1, _y+_height-1);
			g.drawLine(_x+_width-1, _y, _x+_width-1,  _y+_height-1);
		}
	}
	// ino.end

	// ino.method.setColor.3317.description type=line
	// Diese Funktion setzt die Farbinformation des Buttons.
	// ino.end
	// ino.method.setColor.3317.definition 
	public void setColor(Color color)
	// ino.end
	// ino.method.setColor.3317.body 
	{
		_color = color;
		_isSelected = false;
		repaint();
	}
	// ino.end

	// ino.method.getColor.3320.description type=line
	// Diese Funktion liefert die Farbinformation des Buttons zurück.
	// ino.end
	// ino.method.getColor.3320.definition 
	public Color getColor()
	// ino.end
	// ino.method.getColor.3320.body 
	{
		return _color;
	}
	// ino.end

	// ino.method.getIsSelected.3323.description type=line
	// Diese Funktion liefert den Selektstatus zurück.
	// ino.end
	// ino.method.getIsSelected.3323.definition 
	public boolean getIsSelected()
	// ino.end
	// ino.method.getIsSelected.3323.body 
	{
		return _isSelected;
	}
	// ino.end

	// ino.method.mouseClicked.3326.description type=line
	// Diese Funktion tut nichts, muß aber implementiert werden, da das                
	// MouseListener-Interface verwendet wurde.
	// ino.end
	// ino.method.mouseClicked.3326.definition 
	public void mouseClicked(MouseEvent e)
	// ino.end
	// ino.method.mouseClicked.3326.body 
	{
		
	}
	// ino.end

	// ino.method.mouseEntered.3329.description type=line
	// Diese Funktion setzt das Attribut _isEntered auf TRUE, wenn der Mauszeiger sich 
	// innerhalb des Buttons befindet.
	// ino.end
	// ino.method.mouseEntered.3329.definition 
	public void mouseEntered(MouseEvent e)
	// ino.end
	// ino.method.mouseEntered.3329.body 
	{
		Graphics g = getGraphics();
		_isEntered = true;
		update(g);
	}
	// ino.end

	// ino.method.mouseExited.3332.description type=line
	// Diese Funktion setzt das Attribut _isEntered auf FALSE, wenn der Mauszeiger     
	// sich auserhalb des Buttons befindet.
	// ino.end
	// ino.method.mouseExited.3332.definition 
	public void mouseExited(MouseEvent e)
	// ino.end
	// ino.method.mouseExited.3332.body 
	{
		Graphics g = getGraphics();
		_isEntered = false;
		update(g);
	}
	// ino.end

	// ino.method.mousePressed.3335.description type=line
	// Diese Funktion setzt _isSelected auf TRUE, wenn ein Mausklick auf den Button    
	// erfolgt.
	// ino.end
	// ino.method.mousePressed.3335.definition 
	public void mousePressed(MouseEvent e)
	// ino.end
	// ino.method.mousePressed.3335.body 
	{
		Graphics g = getGraphics();
		_isSelected = true;
		if(_parentPanel != null) _parentPanel.setCurrentColor(_color);
		update(g);
	}
	// ino.end

	// ino.method.mouseReleased.3338.description type=line
	// Diese Funktion tut nichts, muß aber implementiert werden, da das                
	// MouseListener-Interface verwendet wurde.
	// ino.end
	// ino.method.mouseReleased.3338.definition 
	public void mouseReleased(MouseEvent e)
	// ino.end
	// ino.method.mouseReleased.3338.body 
	{
		Graphics g = getGraphics();
		if(!_isEditable)	_isSelected = false;
		update(g);
	}
	// ino.end




}
// ino.end

