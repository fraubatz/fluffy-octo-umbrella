import java.util.*;
import java.io.*;


// ino.class.SmEladoObjectList.356.description type=line
// Ein Objekt der Klasse SmEladoObjectList repräsentiert einen Vektor dessen Größe 
// dynamisch mitwächst. Es können nur Objekte verwaltet werden, die vom Typ Object 
// sind oder von Object abgeleitet sind. Die Klasse Object ist die Basisklasse     
// aller JAVA-Klassen und die Basisklasse aller im Symboleditor erstellten         
// Objekte(siehe auch java.lang.Object).
// ino.end
// ino.class.SmEladoObjectList.356.declaration 
public class SmEladoObjectList extends Object implements Serializable, Cloneable
// ino.end
// ino.class.SmEladoObjectList.356.body
{
	// ino.attribute._objectList.363.description type=line
	// Diese Attribut ist ein dynamisches Array und enthält Elemente vom Typ           
	// Object(siehe auch java.util.vector).
	// ino.end
	// ino.attribute._objectList.363.declaration 
	private Vector _objectList;
	// ino.end

	// ino.method.SmEladoObjectList.366.description type=line
	// Der Standartkonstruktor erzeugt einen leeren Vector.
	// ino.end
	// ino.method.SmEladoObjectList.366.definition 
	public SmEladoObjectList()
	// ino.end
	// ino.method.SmEladoObjectList.366.body 
	{
		_objectList = new Vector();
	}
	// ino.end
	

	// ino.method.appendObject.369.description type=line
	// Diese Funktion fügt ein Objekt am Ende des Vektors an.
	// ino.end
	// ino.method.appendObject.369.definition 
	public void appendObject(Object object)
	// ino.end
	// ino.method.appendObject.369.body 
	{
		_objectList.addElement(object);
	}
	// ino.end

	// ino.method.getObjectByListNumber.372.description type=line
	// Diese Funktion liefert eine Referenz auf ein Object zurück unter Angabe der     
	// Feldnummer.
	// ino.end
	// ino.method.getObjectByListNumber.372.definition 
	public Object getObjectByListNumber(int number)
	// ino.end
	// ino.method.getObjectByListNumber.372.body 
	{
		return _objectList.elementAt(number);
	}
	// ino.end

	// ino.method.getListNumberByObject.3146.description type=line
	// Diese Funktion gibt die Feldposition zurück unter Angabe eines im Vektor        
	// enthaltenen Objekts.
	// ino.end
	// ino.method.getListNumberByObject.3146.definition 
	public int getListNumberByObject(Object object)
	// ino.end
	// ino.method.getListNumberByObject.3146.body 
	{
		return _objectList.indexOf(object);
	}
	// ino.end

	// ino.method.getObjectCount.375.description type=line
	// Diese Funktion liefert die Anzahl der im Vektor gespeicherten Objekte zurück.
	// ino.end
	// ino.method.getObjectCount.375.definition 
	public int getObjectCount()
	// ino.end
	// ino.method.getObjectCount.375.body 
	{
		return _objectList.size();
	}
	// ino.end
	
	// ino.method.getElementsOfList.3149.description type=line
	// Diese Funktion gibt ein Enumerations-Objekt zurück welches benötigt wird, um    
	// den Vector durchlaufen zu können. Er kann, laut JAVA-Spezifikation, nicht über  
	// den Index durchlaufen werden(Siehe auch java.util.Enumeration).
	// ino.end
	// ino.method.getElementsOfList.3149.definition 
	public Enumeration getElementsOfList()
	// ino.end
	// ino.method.getElementsOfList.3149.body 
	{
		return _objectList.elements();
	}
	// ino.end

	// ino.method.getLastElementOfList.3152.description type=line
	// Diese Funktion liefert eine Referenz auf das letzte Objekt im Vector zurück.
	// ino.end
	// ino.method.getLastElementOfList.3152.definition 
	public Object getLastElementOfList()
	// ino.end
	// ino.method.getLastElementOfList.3152.body 
	{
		return _objectList.lastElement();
	}
	// ino.end

	// ino.method.setObjectByListNumber.3155.description type=line
	// Diese Funktion fügt ein Objekt an der angegebene Position in den Vector ein.
	// ino.end
	// ino.method.setObjectByListNumber.3155.definition 
	public void setObjectByListNumber(Object object,int index)
	// ino.end
	// ino.method.setObjectByListNumber.3155.body 
	{
		_objectList.setElementAt(object, index);
	}
	// ino.end

	// ino.method.removeObjectByListNumber.3158.description type=line
	// Diese Funktion entfernt ein Objekt aus dem Vektor unter Angabe der Feldnummer.
	// ino.end
	// ino.method.removeObjectByListNumber.3158.definition 
	public void removeObjectByListNumber(int index)
	// ino.end
	// ino.method.removeObjectByListNumber.3158.body 
	{
		_objectList.removeElementAt(index);
	}
	// ino.end

	// ino.method.removeObjectByObject.3161.description type=line
	// Diese Funktion entfernt ein Objekt aus dem Vector unter Angabe seines Namens.
	// ino.end
	// ino.method.removeObjectByObject.3161.definition 
	public void removeObjectByObject(Object object)
	// ino.end
	// ino.method.removeObjectByObject.3161.body 
	{
		_objectList.removeElement(object);
	}
	// ino.end

	// ino.method.removeAllObjects.3164.description type=line
	// Diese Funktion entfernt alle Objekte aus dem Vector.
	// ino.end
	// ino.method.removeAllObjects.3164.definition 
	public void removeAllObjects()
	// ino.end
	// ino.method.removeAllObjects.3164.body 
	{
		_objectList.removeAllElements();
	}
	// ino.end

	// ino.method.clone.3167.description type=line
	// Diese Funktion liefert eine Kopie des Vectors zurück.
	// ino.end
	// ino.method.clone.3167.definition 
	protected Object clone()
	// ino.end
	// ino.method.clone.3167.body 
	{
		return _objectList.clone();
	}
	// ino.end

	// ino.method.toString.378.description type=line
	// Diese Funktion parst alle im Vektor enthaltenen Objekte und gibt alle           
	// Objektinformationen als String zurück.
	// ino.end
	// ino.method.toString.378.definition 
	public String toString()
	// ino.end
	// ino.method.toString.378.body 
	{
		String _listToString = "";
		int i;
	    int ObjectCount = _objectList.size();
	    Object ObjectToString;
	    Enumeration enum = _objectList.elements();
	   
	    for(i=0;i < ObjectCount;i++)
		{
	      ObjectToString = (Object) enum.nextElement();
	      _listToString += ObjectToString.toString();   	  
		}
		return _listToString;
	}
	// ino.end
}
// ino.end





