import java.awt.*;
import java.awt.event.*;



// ino.class.TextPreview.3129.description type=line
// Die Klasse TextPreview ist eine benutzerdefinierte GUI-komponente. Sie          
// repräsentiert ein Textfenster, welches die aktuelle Schriftart und Schriftgröße 
// darstellt. Ein Objekt dieser Klasse wird zur Gestaltung des Dialogs             
// 'Textattribute' verwendet(siehe auch DlgTextPnl).
// ino.end
// ino.class.TextPreview.3129.declaration 
public class TextPreview extends Canvas
// ino.end
// ino.class.TextPreview.3129.body
{
	// ino.attribute._color.3247.description type=line
	// Diese Attribut enthält die Farbe des Textes.
	// ino.end
	// ino.attribute._color.3247.declaration 
	private Color _color;
	// ino.end
	// ino.attribute._boundrect.3250.description type=line
	// Dieses Attribut ist ein Rahmen um das Textfeld.
	// ino.end
	// ino.attribute._boundrect.3250.declaration 
	private Rectangle _boundrect;
	// ino.end
	// ino.attribute._font.3253.description type=line
	// Dieses Attribut hält den aktuellen Zeichensatz, der im Textfenster erscheint.
	// ino.end
	// ino.attribute._font.3253.declaration 
	private Font _font;
	// ino.end

	// ino.method.TextPreview.3256.description type=line
	// Der Standardkonstruktor erzeugt ein Textfeld mit dem aktuellen Zeichensatz.
	// ino.end
	// ino.method.TextPreview.3256.definition 
	public TextPreview()
	// ino.end
	// ino.method.TextPreview.3256.body 
	{
		_color = Color.black;
		this.setBackground(Color.white);
		_font = new Font("Serif", Font.PLAIN, 12);
	}
	// ino.end

	// ino.method.update.3259.description type=line
	// Diese Funktion ruft die paint-Methode auf. Sie hat höhere Priorität.
	// ino.end
	// ino.method.update.3259.definition 
	public void update(Graphics g)
	// ino.end
	// ino.method.update.3259.body 
	{
		paint(g);
	}
	// ino.end

	// ino.method.paint.3262.description type=line
	// Diese Funktion zeichnet den Text und den Rahmen des Textfensters.
	// ino.end
	// ino.method.paint.3262.definition 
	public void paint(Graphics g)
	// ino.end
	// ino.method.paint.3262.body 
	{
		_boundrect = this.getBounds();
		g.clearRect(_boundrect.x, _boundrect.y, _boundrect.width, _boundrect.height);
		g.setColor(_color);
		g.setFont(_font);
		g.drawString("___ AaBbCcYyZz ___", _boundrect.x+5, _boundrect.y+_boundrect.height/2);
	}
	// ino.end

	// ino.method.setColor.3265.description type=line
	// Diese Funktion setzt die aktuelle Textfarbe.
	// ino.end
	// ino.method.setColor.3265.definition 
	public void setColor(Color color)
	// ino.end
	// ino.method.setColor.3265.body 
	{
		_color = color;
		repaint();
	}
	// ino.end

	// ino.method.setText.3268.description type=line
	// Diese Funktion setzt den aktuellen Zeichensatz im Textfenster.
	// ino.end
	// ino.method.setText.3268.definition 
	public void setText(String fontName,int style,int size)
	// ino.end
	// ino.method.setText.3268.body 
	{
		_font = new Font(fontName, style, size);
		repaint();
	}
	// ino.end
}
// ino.end

