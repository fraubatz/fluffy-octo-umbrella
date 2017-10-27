import java.awt.*;


// ino.class.SmText.278.description type=line
// Diese Klasse ist für feste Textobjekte innerhalb von Symbolen vorgesehen und    
// hält eine Zeichenkette auf den darzustellenden Text.
// ino.end
// ino.class.SmText.278.declaration 
public class SmText extends SmGraphBase
// ino.end
// ino.class.SmText.278.body
{
	// ino.attribute._className.2869.description type=line
	// Dieses statische Attribut hält den Klassennamen.
	// ino.end
	// ino.attribute._className.2869.declaration 
	private static String _className;
	// ino.end

	// ino.attribute._text.2872.description type=line
	// Dieses Attribut beinhaltet die Zeichenkette des Textes.
	// ino.end
	// ino.attribute._text.2872.declaration 
	private String _text;
	// ino.end

	// ino.method.SmText.2875.description type=line
	// Der Standardkonstruktor weist dem Text eine leere Zeichenkette zu.
	// ino.end
	// ino.method.SmText.2875.definition 
	public SmText()
	// ino.end
	// ino.method.SmText.2875.body 
	{
		_text = "";
	}
	// ino.end

	// ino.method.SmText.2878.description type=line
	// Der Copykonstruktor weist dem Text durch Kopieren den Wert des Textes des       
	// übergebenen Objektes zu.
	// ino.end
	// ino.method.SmText.2878.definition 
	public SmText(SmText copy)
	// ino.end
	// ino.method.SmText.2878.body 
	{
		_text = copy._text;
	}
	// ino.end

	// ino.method.SmText.2881.description type=line
	// Der Parameterkonstruktor weist dem Text durch Kopieren den Wert des Textes des  
	// übergebenen Parameters zu.
	// ino.end
	// ino.method.SmText.2881.definition 
	public SmText(String text)
	// ino.end
	// ino.method.SmText.2881.body 
	{
		_text = text;
	}
	// ino.end

	// ino.method.setText.2884.description type=line
	// Diese Funktion setzt den Text auf eine Kopie der übergebenen Zeichenkette.
	// ino.end
	// ino.method.setText.2884.definition 
	public void setText(String text)
	// ino.end
	// ino.method.setText.2884.body 
	{
		_text = text;
	}
	// ino.end

	// ino.method.getText.2887.description type=line
	// Diese Funktion liefert als Zeichenkette den aktuellen Inhalt des Textfeldes     
	// zurück.
	// ino.end
	// ino.method.getText.2887.definition 
	public String getText()
	// ino.end
	// ino.method.getText.2887.body 
	{
		return _text;
	}
	// ino.end

	// ino.method.getClassName.4436.description type=line
	// Diese Funktion gibt den Klassennamen zurück.
	// ino.end
	// ino.method.getClassName.4436.definition 
	public String getClassName()
	// ino.end
	// ino.method.getClassName.4436.body 
	{
		return _className;
	}
	// ino.end

	// ino.method.toString.2890.description type=line
	// Diese Funktion liefert den aktuellen Inhalt des Objekts als einen String zurück.
	// ino.end
	// ino.method.toString.2890.definition 
	public String toString()
	// ino.end
	// ino.method.toString.2890.body 
	{
			return    "SmText"					+ '\n'
			  + '0'								+ '\n'
			  + '{'								+ '\n'
			  +  super.toString()
		      +  "string" + "\"" + _text + "\""	+ '\n' 
			  + '}'								+ '\n';
	}
	// ino.end
	
}
// ino.end





