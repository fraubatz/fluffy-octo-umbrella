import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

// ino.class.SmStateBar.451.description type=line
// Die Klasse SmStateBar ist die Statuszeile im unteren Teil des Hauptfensters.    
// Sie enthält Informationen über den Objektnamen und den Objekttyp des im         
// Zeichenfenster erstellten oder geladenen Objekts. Die Funktionen dieser Klasse  
// sind statisch und können direkt mit dem Klassennamen aufgerufen werden. Eine    
// Instanziierung ist nicht erforderlich.
// ino.end
// ino.class.SmStateBar.451.declaration 
public class SmStateBar extends Panel
// ino.end
// ino.class.SmStateBar.451.body
{
	

	// ino.attribute._nameOfComponent.882.description type=line
	// Dieses Attribut enthält den Namen des erstellten Objekts.
	// ino.end
	// ino.attribute._nameOfComponent.882.declaration 
	private static String _nameOfComponent;
	// ino.end
	// ino.attribute._typeOfComponent.885.description type=line
	// Dieses Attribut enthält den Typ des erstellten Objekts.
	// ino.end
	// ino.attribute._typeOfComponent.885.declaration 
	private static String _typeOfComponent;
	// ino.end
	// ino.attribute._nameField.888.description type=line
	// Dieses Attribut ist das Textfeld zur Aufnahme des Objektnamens.
	// ino.end
	// ino.attribute._nameField.888.declaration 
	private static TextField _nameField;
	// ino.end
	// ino.attribute._typeField.891.description type=line
	// Dieses Attribut ist das Textfeld zur Aufnahme des Objekttyps.
	// ino.end
	// ino.attribute._typeField.891.declaration 
	private static TextField _typeField;
	// ino.end
	// ino.attribute.SouthLayout.894.description type=line
	// Dieses Attribut bestimmt das Layout der Statusleiste.
	// ino.end
	// ino.attribute.SouthLayout.894.declaration 
	private FlowLayout SouthLayout;
	// ino.end
	// ino.method.SmStateBar.897.description type=line
	// Der Standardkonstruktor erzeugt die Komponenten des Panels und ordnet diese an.
	// ino.end
	// ino.method.SmStateBar.897.definition 
	public SmStateBar()
	// ino.end
	// ino.method.SmStateBar.897.body 
	{
		SouthLayout = new FlowLayout();
	
		this.setLayout(SouthLayout);
		this.setBackground(Color.lightGray);
				
		_nameField = new TextField("Symbol                                    ");
		_nameField.setEditable(false);
		this.add(_nameField);
		_typeField = new     TextField("Layer                                     ");
		_typeField.setEditable(false);
		this.add(_typeField);
	}
	// ino.end

	// ino.method.setNameOfComponent.900.description type=line
	// Diese Funktion setzt den Namen des zu erstellenden Objekts.
	// ino.end
	// ino.method.setNameOfComponent.900.definition 
	public static void setNameOfComponent(String name)
	// ino.end
	// ino.method.setNameOfComponent.900.body 
	{
		_nameOfComponent = name;
		_nameField.setText(_nameOfComponent);
	}
	// ino.end

	// ino.method.setTypeOfComponent.903.description type=line
	// Diese Funktion setzt den Typ des zu erstellenden Objekts.
	// ino.end
	// ino.method.setTypeOfComponent.903.definition 
	public static void setTypeOfComponent(String name)
	// ino.end
	// ino.method.setTypeOfComponent.903.body 
	{
		_typeOfComponent = name;
		_typeField.setText(_typeOfComponent);
	}
	// ino.end
}
// ino.end



