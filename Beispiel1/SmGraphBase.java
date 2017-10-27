

// ino.class.SmGraphBase.181.description type=line
// Dies ist die gemeinsame Basisklasse aller grafischen Grundelementklassen. In    
// ihr werden einige grundlegende Attribute definiert. Dazu gehören eine           
// Vordergrund- und eine Hintergrundfarbe sowie Linienbreiten und -stile.
// ino.end
// ino.class.SmGraphBase.181.declaration 
public class SmGraphBase
// ino.end
// ino.class.SmGraphBase.181.body
{
   // ino.attribute._foreground.316.description type=line
   // Dieses Attribut beschreibt die Vordergrundfarbe des Elements.
   // ino.end
   // ino.attribute._foreground.316.declaration 
   private String _foreground;
   // ino.end
   
   // ino.attribute._background.313.description type=line
   // Dieses Attribut beschreibt die Hintergrundfarbe des Elements.
   // ino.end
   // ino.attribute._background.313.declaration 
   private String _background;
   // ino.end
   
   // ino.attribute._lineWidth.196.description type=line
   // Dieses Attribut beschreibt die Linienstärke.
   // ino.end
   // ino.attribute._lineWidth.196.declaration 
   private int _lineWidth;
   // ino.end
   
   

   // ino.attribute._lineStyle.221.description type=line
   // Dieses Attribut ist für die Linienstärke verantwortlich.
   // ino.end
   // ino.attribute._lineStyle.221.declaration 
   private String _lineStyle;
   // ino.end
   
   // ino.method.setBackground.208.description type=line
   // Diese Funktion setzt die Variable für die Hintergrundfarbe auf den übergebenen  
   // Wert. Wird ein leerer String übergeben, so wird der Wert nicht gesetzt.
   // ino.end
   // ino.method.setBackground.208.definition 
   public void setBackground(String background)
   // ino.end
   // ino.method.setBackground.208.body 
   {
	   if(background != null) _background = background;
   }
   // ino.end
   
   // ino.method.getBackground.211.description type=line
   // Diese Funktion liefert die Hintergrundfarbe als Zeichenkette zurück.
   // ino.end
   // ino.method.getBackground.211.definition 
   public String getBackground()
   // ino.end
   // ino.method.getBackground.211.body 
   {
	   return _background;
   }
   // ino.end
   
   // ino.method.setLineWidth.214.description type=line
   // Diese Funktion setzt die Variable für die Linienbreite auf den übergebenen      
   // Wert. Wird ein negativer Parameter oder eine 0 übergeben, wird der Wert nicht   
   // gesetzt.
   // ino.end
   // ino.method.setLineWidth.214.definition 
   public void setLineWidth(int lineWidth)
   // ino.end
   // ino.method.setLineWidth.214.body 
   {
	   if(lineWidth >1) _lineWidth = lineWidth;
   }
   // ino.end
   
   // ino.method.getLineWidth.217.description type=line
   // Diese Funktion liefert die aktuelle Linienbreite zurück.
   // ino.end
   // ino.method.getLineWidth.217.definition 
   public int getLineWidth()
   // ino.end
   // ino.method.getLineWidth.217.body 
   {
	   return _lineWidth;
   }
   // ino.end
   
   // ino.method.SmGraphBase.199.description type=line
   // Der Standardkonstruktor initialisiert die Variablen folgendermaßen :            
   // Vordergrund mit schwarz, Hintergrund mit weiß, Breite mit 1 und Linienstil mit  
   // SOLID.
   // ino.end
   // ino.method.SmGraphBase.199.definition 
   public SmGraphBase()
   // ino.end
   // ino.method.SmGraphBase.199.body 
   {
	   _foreground		= "black";
	   _background		= "white";
	   _lineStyle		= "SOLID";
	   _lineWidth		= 1;

   }
   // ino.end

   // ino.method.SmGraphBase.349.description type=line
   // Der Kopierkonstruktor kopiert alle Variablen des übergebenen Objektes.
   // ino.end
   // ino.method.SmGraphBase.349.definition 
   public SmGraphBase(SmGraphBase copy)
   // ino.end
   // ino.method.SmGraphBase.349.body 
   {
	   _foreground		= copy._foreground;
	   _background		= copy._background;
	   _lineStyle		= copy._lineStyle;
	   _lineWidth		= copy._lineWidth;
   }

   // ino.end
   
   // ino.method.setForeground.202.description type=line
   // Diese Funktion setzt die Variable für die Vordergrundfarbe auf den übergebenen  
   // Wert. Wird ein leerer String übergeben, so wird der Wert nicht gesetzt.
   // ino.end
   // ino.method.setForeground.202.definition 
   public void setForeground(String foreground)
   // ino.end
   // ino.method.setForeground.202.body 
   {
	   if(foreground.length() != 0) _foreground = foreground;
   }
   // ino.end
   
   // ino.method.getForeground.205.description type=line
   // Diese Funktion liefert die Vordergrundfarbe als Zeichenkette zurück.
   // ino.end
   // ino.method.getForeground.205.definition 
   public String getForeground()
   // ino.end
   // ino.method.getForeground.205.body 
   {
	   return _foreground;
   }
   // ino.end

   // ino.method.setLineStyle.227.description type=line
   // Diese Funktion setzt die Variable für den Linienstil auf den übergebenen Wert.
   // ino.end
   // ino.method.setLineStyle.227.definition 
   public void setLineStyle(String lineStyle)
   // ino.end
   // ino.method.setLineStyle.227.body 
   {
	   if(lineStyle != null) _lineStyle = lineStyle;
   }
   // ino.end

   // ino.method.getLineStyle.224.description type=line
   // Diese Funktion liefert den aktuellen Linienstil zurück.
   // ino.end
   // ino.method.getLineStyle.224.definition 
   public String getLineStyle()
   // ino.end
   // ino.method.getLineStyle.224.body 
   {
	   return _lineStyle;
   }
   // ino.end

   // ino.method.toString.352.description type=line
   // Diese Funktion gibt den Inhalt des Objektes als einen String aus.
   // ino.end
   // ino.method.toString.352.definition 
   public String toString()
   // ino.end
   // ino.method.toString.352.body 
   {
		return  "SmEladoString"				+ '\n'
			   + '0'						+ '\n'
			   + '{'						+ '\n'
			   + "string " + "\"" + _foreground  + "\"" + '\n'
			   + '}'						+ '\n'
			   + "SmEladoString"			+ '\n'
			   + '0'						+ '\n'
			   + '{'						+ '\n'
			   + "string " + "\"" + _background  + "\"" + '\n'
			   + '}'						+ '\n'
			   + "lineWidth "  + _lineWidth + '\n'
			   + "lineStyle "  + _lineStyle + '\n';
			 
   }
   // ino.end
}
// ino.end




