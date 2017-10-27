import java.awt.*;


// ino.class.SmLayer.1976.description type=line
// Die Klasse SmLayer ist die abstrakte Basisklasse aller Layer. Ein Layer pflegt  
// eine Liste von Objekten eines bestimmten Typs und stellt folgende    
// Funktionen bereit: Einfügen und Entfernen von Objekten, Selektieren von Objekten
// nach verschiedenen Kriterien, Bestimmung der Sichtbarkeit und Selektierbarkeit  
// von Objekten in einem Layer. Die Klasse SmLayer deklariert diese Funktionen, die
// von den abgeleiteten Klassen implementiert werden müssen.
// ino.end
// ino.class.SmLayer.1976.declaration 
public abstract class SmLayer extends Object
// ino.end
// ino.class.SmLayer.1976.body
{
	// ino.method.addObject.2346.description type=line
	// Diese Funktion fügt ein Objekt in den Layer ein.
	// ino.end
	// ino.method.addObject.2346.declaration 
	public abstract void addObject(Object object);
	// ino.end
	
	// ino.method.removeObject.5188.description type=line
	// Diese Funktion löscht ein Objekt unter Angabe des Objektnamens aus der Liste    
	// des Layers.
	// ino.end
	// ino.method.removeObject.5188.declaration 
	public abstract void removeObject(SmObject object);
	// ino.end
	
	// ino.method.removeAllObjects.5191.description type=line
	// Diese Funktion löscht alle Objekte aus der Liste des Layers.
	// ino.end
	// ino.method.removeAllObjects.5191.declaration 
	public abstract void removeAllObjects();
	// ino.end

	// ino.method.setSelectState.5194.description type=line
	// Diese Funktion setzt den Selektstatus für alle Objekte aus der Liste des Layers.
	// ino.end
	// ino.method.setSelectState.5194.declaration 
	public abstract void setSelectState(boolean mode);
	// ino.end
	
	// ino.method.getSelectState.5197.description type=line
	// Diese Funktion gibt den Selektstatus zurück.
	// ino.end
	// ino.method.getSelectState.5197.declaration 
	public abstract boolean getSelectState();
	// ino.end
	
	// ino.method.selectObject.2349.description type=line
	// Diese Funktion selektiert ein Objekt aus der Liste des Layers.
	// ino.end
	// ino.method.selectObject.2349.declaration 
	public abstract void selectObject(int x,int y);
	// ino.end

	// ino.method.selectObject.5200.description type=line
	// Diese Funktion selektiert mehrere Objekte in einem rechteckigen Bereich aus der 
	// Liste des Layers.
	// ino.end
	// ino.method.selectObject.5200.declaration 
	public abstract void selectObject(int startX,int startY,int x,int y);
	// ino.end

	// ino.method.selectObject.5203.description type=line
	// Diese Funktion selektiert alle Objekte aus der Liste des Layers.
	// ino.end
	// ino.method.selectObject.5203.declaration 
	public abstract void selectObject();
	// ino.end
	
	// ino.method.getSelectedObject.2352.description type=line
	// Diese Funktion gibt die Liste aller selektierten Objekte zurück.
	// ino.end
	// ino.method.getSelectedObject.2352.declaration 
	public abstract SmEladoObjectList getSelectedObject();
	// ino.end

	// ino.method.getObjectList.5206.description type=line
	// Diese Funktion gibt die Liste aller Objekte in dem entsprechenden Layer         
	// zurück(speichern).
	// ino.end
	// ino.method.getObjectList.5206.declaration 
	public abstract SmEladoObjectList getObjectList();
	// ino.end

	// ino.method.setObjectList.5209.description type=line
	// Diese Funktion fügt eine bereits bestehende Objektliste in den entsprechenden   
	// Layer ein(laden).
	// ino.end
	// ino.method.setObjectList.5209.declaration 
	public abstract void setObjectList(SmEladoObjectList objectList);
	// ino.end

	// ino.method.getMinPoint.5212.description type=line
	// Diese Funktion ermittelt die kleinste Objektposition aus der Liste des Layers   
	// und gibt diese zurück.
	// ino.end
	// ino.method.getMinPoint.5212.declaration 
	public abstract Point getMinPoint();
	// ino.end

	// ino.method.getMaxPoint.5215.description type=line
	// Diese Funktion ermittelt die größte Objektposition aus der Liste des Layers und 
	// gibt diese zurück.
	// ino.end
	// ino.method.getMaxPoint.5215.declaration 
	public abstract Point getMaxPoint();
	// ino.end
}
// ino.end


