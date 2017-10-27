import java.awt.*;

// ino.class.SmBorder.1468.description type=line
// Diese Klasse ist für die Erzeugung von Rahmen um GUI-Komponeneten, insbesondere 
// um Checkboxen und Radiobuttons, vorgesehen.Diese Rahmen können optional einen   
// Titel erhalten.
// ino.end
// ino.class.SmBorder.1468.declaration 
public class SmBorder extends Panel
// ino.end
// ino.class.SmBorder.1468.body
{
	// ino.attribute._x.1473.description type=line
	// Dieses Attribut bestimmt die X-Koordinate der rechten oberen Ecke des Rahmens.
	// ino.end
	// ino.attribute._x.1473.declaration 
	private int _x;
	// ino.end
	// ino.attribute._y.1476.description type=line
	// Dieses Attribut bestimmt die Y-Koordinate der rechten oberen Ecke des Rahmens.
	// ino.end
	// ino.attribute._y.1476.declaration 
	private int _y;
	// ino.end
	// ino.attribute._width.1479.description type=line
	// Dieses Attribut bestimmt die Breite des Rahmens.
	// ino.end
	// ino.attribute._width.1479.declaration 
	private int _width;
	// ino.end
	// ino.attribute._height.1482.description type=line
	// Dieses Attribut bestimmt die Höhe des Rahmens.
	// ino.end
	// ino.attribute._height.1482.declaration 
	private int _height;
	// ino.end
	// ino.attribute._panel.1485.description type=line
	// Dieses Attribut bestimmt den Container, in dem der Rahmen gezeichnet werden     
	// soll.
	// ino.end
	// ino.attribute._panel.1485.declaration 
	private Panel _panel;
	// ino.end
	// ino.attribute._title.1488.description type=line
	// Dieses Attribut beinhaltet die Zeichenkette des Titels.
	// ino.end
	// ino.attribute._title.1488.declaration 
	private String _title;
	// ino.end

	// ino.method.SmBorder.1491.description type=line
	// Der Parameterkonstruktor initialisiert den Startpunkt und die Abmessungen des   
	// Rahmens.Im wird auch ein Titel und ein Container zugewiesen.
	// ino.end
	// ino.method.SmBorder.1491.definition 
	public SmBorder(Panel panel,String title,int x,int y,int width,int height)
	// ino.end
	// ino.method.SmBorder.1491.body 
	{
		_x		= x;
		_y		= y;
		_width  = width;
		_height = height;
		_panel  = panel;
		_title  = title;
	}
	// ino.end

	// ino.method.paint.1494.description type=line
	// Diese Methode zeichnet den Rahmen.
	// ino.end
	// ino.method.paint.1494.definition 
	public void paint(Graphics g)
	// ino.end
	// ino.method.paint.1494.body 
	{
		g.setColor(Color.gray);
		g.drawRect(_x,_y,_width,_height);
		g.setColor(Color.white);
		g.drawLine(_x+1,_y+1,_width+_x-1,_y+1);
		g.drawLine(_x+1,_y+1,_x+1,_height+_y-1);
		g.drawLine(_x,_height+_y+1,_x+_width+1,_height+_y+1);
		g.drawLine(_x+_width+1,_y,_x+_width+1,_y+_height+1);
		if (_title != null)
		{
			Label label = new Label(_title, Label.CENTER);
			FontMetrics _fontmetrics = _panel.getFontMetrics(_panel.getFont());
			label.setBounds(_x+10,_y-6,_fontmetrics.stringWidth(_title)+10,15);
			_panel.add(label);
		}
	}
	// ino.end


}
// ino.end




