import java.awt.*;
import java.util.*;


// ino.class.SmModify.1129.description type=line
// Die Klasse SmModify enthält eine Reihe von statischen Funktionen, die zur       
// Skalierung der erstellten Objekte dienen.
// Es müssen keine Instanzen dieser Klasse erzeugt werden. Sie ist lediglich eine  
// Sammlung von Routinen für die Skalierung von grafischen Objekten. Beim Aufruf   
// einer Funktion wird anstatt des Objektnamens, der Klassenname verwendet. Alle   
// Funktionen bekommen als Parameter eine Referenz auf den gültigen Arbeitsbereich,
// eine Referenzliste von Objekten, die modifiziert werden sollen, das 'Graphics   
// Object' des gültigen Arbeitsbereichs und die aktuellen Maus-Koordinaten. Es     
// können nur Objekte skaliert werden, die von der Klasse SmObject abgeleitet sind.
// ino.end
// ino.class.SmModify.1129.declaration 
public final class SmModify
// ino.end
// ino.class.SmModify.1129.body
{
	// ino.attribute._oldX.3175.description type=line
	// Dieses Attribut hält die letzte X-Position der Maus.
	// ino.end
	// ino.attribute._oldX.3175.declaration 
	private static int _oldX;
	// ino.end
	// ino.attribute._oldY.3178.description type=line
	// Dieses Attribut hält die letzte Y-Position der Maus.
	// ino.end
	// ino.attribute._oldY.3178.declaration 
	private static int _oldY;
	// ino.end

	// ino.method.moveObject.3181.description type=line
	// Diese Funktion verschiebt Objekte, wenn das Raster inaktiv ist.
	// ino.end
	// ino.method.moveObject.3181.definition 
	public static void moveObject(SmWorkingArea workingArea,Graphics g,SmEladoObjectList objectList,int x,int y)
	// ino.end
	// ino.method.moveObject.3181.body 
	{
		int ObjectCount = objectList.getObjectCount();
		int i;
		int offsetX = x - _oldX;
		int offsetY = y - _oldY;
		SmObject objectByList;
		Enumeration enum = objectList.getElementsOfList();

		for(i=0;i < ObjectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setIsSelected(false);
			
			workingArea.repaint(objectByList.getX()-4, objectByList.getY()-4,
								objectByList.getWidth()+8, objectByList.getHeight()+8);

			if(objectByList instanceof SmGroupObject)
			{
				objectByList.moveX(objectByList.getX()+offsetX);
				objectByList.moveY(objectByList.getY()+offsetY);
			}
			else
			{
				objectByList.setX(objectByList.getX()+offsetX);
				objectByList.setY(objectByList.getY()+offsetY);
			}

			workingArea.repaint(objectByList.getX()-4, objectByList.getY()-4,
								objectByList.getWidth()+8, objectByList.getHeight()+8);
		}
		_oldX = x;
		_oldY = y;
	}
	// ino.end

	// ino.method.moveObjectOnGrid.3184.description type=line
	// Diese Funktion verschiebt Objekte, wenn das Raster aktiv ist.
	// ino.end
	// ino.method.moveObjectOnGrid.3184.definition 
	public static void moveObjectOnGrid(SmWorkingArea workingArea,SmGrid grid,SmEladoObjectList objectList,int x,int y)
	// ino.end
	// ino.method.moveObjectOnGrid.3184.body 
	{
		int ObjectCount = objectList.getObjectCount();
		int i;
		int offsetX = ((x - _oldX)/grid.getXDistance())*grid.getXDistance();
		int offsetY = ((y - _oldY)/grid.getYDistance())*grid.getYDistance();
		SmObject objectByList;
		Enumeration enum = objectList.getElementsOfList();

		for(i=0;i < ObjectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setIsSelected(false);

			workingArea.repaint(objectByList.getX()-4, objectByList.getY()-4,
								objectByList.getWidth()+8, objectByList.getHeight()+8);


			if ((Math.abs(offsetX)>=grid.getXDistance()))
			{
				if(objectByList instanceof SmGroupObject)
				{
					objectByList.moveX(((objectByList.getX()/grid.getXDistance())*grid.getXDistance())+offsetX);
				}
				else
				{
					objectByList.setX(((objectByList.getX()/grid.getXDistance())*grid.getXDistance())+offsetX);
				}
				_oldX = x;
			}
			if ((Math.abs(offsetY)>=grid.getYDistance()))
			{
				if(objectByList instanceof SmGroupObject)
				{
					objectByList.moveY(((objectByList.getY()/grid.getYDistance())*grid.getYDistance())+offsetY);
				}
				else
				{
					objectByList.setY(((objectByList.getY()/grid.getYDistance())*grid.getYDistance())+offsetY);
				}
				_oldY = y;
			}
			workingArea.repaint(objectByList.getX()-4, objectByList.getY()-4,
								objectByList.getWidth()+8, objectByList.getHeight()+8);
		}
	}
	// ino.end

	// ino.method.scaleObjectNorth.3187.description type=line
	// Diese Funktion verändert(zieht) die Form eines Objektes in nördlicher Richtung, 
	// wenn das Raster inaktiv ist.
	// ino.end
	// ino.method.scaleObjectNorth.3187.definition 
	public static void scaleObjectNorth(SmWorkingArea workingArea,Graphics g,SmEladoObjectList objectList,int x,int y)
	// ino.end
	// ino.method.scaleObjectNorth.3187.body 
	{
		int ObjectCount = objectList.getObjectCount();
		int i;
		int offsetY = _oldY-y;
		SmObject objectByList;
		
		Enumeration enum = objectList.getElementsOfList();

		for(i=0;i < ObjectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setIsSelected(false);
			workingArea.repaint(objectByList.getX()-4,
								objectByList.getY()-4,
								objectByList.getWidth()+8,
								objectByList.getHeight()+8);

			g.setClip(objectByList.getX()-4,
					  y-40,
					  objectByList.getWidth()+8,
					  objectByList.getHeight()+offsetY+40+8);

			if(objectByList instanceof SmGroupObject)
			{
				objectByList.moveY(objectByList.getY()-offsetY);
				objectByList.setY(offsetY);
				objectByList.setHeight(offsetY);
			}
			else
			{
				objectByList.setHeight(objectByList.getHeight()+offsetY);
				objectByList.setY(objectByList.getY()-offsetY);
			}
			workingArea.update(g);
		}
		_oldY = y;
	}
	// ino.end

	// ino.method.scaleObjectNorthOnGrid.3190.description type=line
	// Diese Funktion verändert(zieht) die Form eines Objektes in nördlicher Richtung, 
	// wenn das Raster aktiv ist.
	// ino.end
	// ino.method.scaleObjectNorthOnGrid.3190.definition 
	public static void scaleObjectNorthOnGrid(SmWorkingArea workingArea,Graphics g,SmGrid grid,SmEladoObjectList objectList,int x,int y)
	// ino.end
	// ino.method.scaleObjectNorthOnGrid.3190.body 
	{
		int ObjectCount = objectList.getObjectCount();
		int i;
		int offsetY = ((_oldY-y)/grid.getYDistance())*grid.getYDistance();
		SmObject objectByList;
		
		Enumeration enum = objectList.getElementsOfList();

		for(i=0;i < ObjectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setIsSelected(false);
			workingArea.repaint(objectByList.getX()-4,
								objectByList.getY()-4,
								objectByList.getWidth()+8,
								objectByList.getHeight()+8);

			g.setClip(objectByList.getX()-4,
					  y-40,
					  objectByList.getWidth()+8,
					  objectByList.getHeight()+offsetY+40+8);

			if ((Math.abs(offsetY)>=grid.getYDistance()))
			{
				if(objectByList instanceof SmGroupObject)
				{
					objectByList.moveY(objectByList.getY()-offsetY);
					objectByList.setY(offsetY);
					objectByList.setHeight(offsetY);
				}
				else
				{
					int objectYOld = objectByList.getY();
					objectByList.setY(((objectByList.getY()/grid.getYDistance())*grid.getYDistance())-offsetY);
					objectByList.setHeight(objectByList.getHeight()+objectYOld-objectByList.getY());
				}
				_oldY = y;
			}
			workingArea.update(g);
		}
	}
	// ino.end

	// ino.method.scaleObjectSouth.3193.description type=line
	// Diese Funktion verändert(zieht) die Form eines Objektes in südlicher Richtung,  
	// wenn das Raster inaktiv ist.
	// ino.end
	// ino.method.scaleObjectSouth.3193.definition 
	public static void scaleObjectSouth(SmWorkingArea workingArea,Graphics g,SmEladoObjectList objectList,int x,int y)
	// ino.end
	// ino.method.scaleObjectSouth.3193.body 
	{
		int ObjectCount = objectList.getObjectCount();
		int i;
		int offsetY = y-_oldY;
		SmObject objectByList;
		
		Enumeration enum = objectList.getElementsOfList();

		for(i=0;i < ObjectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setIsSelected(false);
			workingArea.repaint(objectByList.getX()-4,
								objectByList.getY()-4,
								objectByList.getWidth()+8,
								objectByList.getHeight()+8);

			g.setClip(objectByList.getX()-4,
					  objectByList.getY()-4,
					  objectByList.getWidth()+8,
					  objectByList.getY()+offsetY+40);

			if(objectByList instanceof SmGroupObject)
			{
				objectByList.setY(offsetY);
				objectByList.setHeight(offsetY);
			}
			else
			{
				objectByList.setHeight(objectByList.getHeight()+offsetY);
			}
			workingArea.update(g);
		}
		_oldY = y;
	}
	// ino.end

	// ino.method.scaleObjectSouthOnGrid.3196.description type=line
	// Diese Funktion verändert(zieht) die Form eines Objektes in südlicher Richtung,  
	// wenn das Raster aktiv ist.
	// ino.end
	// ino.method.scaleObjectSouthOnGrid.3196.definition 
	public static void scaleObjectSouthOnGrid(SmWorkingArea workingArea,Graphics g,SmGrid grid,SmEladoObjectList objectList,int x,int y)
	// ino.end
	// ino.method.scaleObjectSouthOnGrid.3196.body 
	{
		int ObjectCount = objectList.getObjectCount();
		int i;
		int offsetY = ((y - _oldY)/grid.getYDistance())*grid.getYDistance();
		SmObject objectByList;
		
		Enumeration enum = objectList.getElementsOfList();

		for(i=0;i < ObjectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setIsSelected(false);
			workingArea.repaint(objectByList.getX()-4,
								objectByList.getY()-4,
								objectByList.getWidth()+8,
								objectByList.getHeight()+8);

			g.setClip(objectByList.getX()-4,
					  objectByList.getY()-4,
					  objectByList.getWidth()+8,
					  objectByList.getY()+offsetY+40);
			
			if ((Math.abs(offsetY)>=grid.getYDistance()))
			{
				if(objectByList instanceof SmGroupObject)
				{
					objectByList.setY(offsetY);
					objectByList.setHeight(offsetY);
				}
				else
				{
					objectByList.setHeight((((objectByList.getHeight()+objectByList.getY()+offsetY)/grid.getYDistance())*grid.getYDistance())-objectByList.getY());
				}
				_oldY = y;
			}
			workingArea.update(g);
		}
	}
	// ino.end

	// ino.method.scaleObjectEast.3199.description type=line
	// Diese Funktion verändert(zieht) die Form eines Objektes in östlicher Richtung,  
	// wenn das Raster inaktiv ist.
	// ino.end
	// ino.method.scaleObjectEast.3199.definition 
	public static void scaleObjectEast(SmWorkingArea workingArea,Graphics g,SmEladoObjectList objectList,int x,int y)
	// ino.end
	// ino.method.scaleObjectEast.3199.body 
	{
		int ObjectCount = objectList.getObjectCount();
		int i;
		int offsetX = x-_oldX;
		SmObject objectByList;
		
		Enumeration enum = objectList.getElementsOfList();

		for(i=0;i < ObjectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setIsSelected(false);
			workingArea.repaint(objectByList.getX()-4,
								objectByList.getY()-4,
								objectByList.getWidth()+8,
								objectByList.getHeight()+8);

			g.setClip(objectByList.getX()-4,
					  objectByList.getY()-4,
					  objectByList.getWidth()+offsetX+40,
					  objectByList.getHeight()+8);

			if(objectByList instanceof SmGroupObject)
			{
				objectByList.setX(offsetX);
				objectByList.setWidth(offsetX);
			}
			else
			{
				objectByList.setWidth(objectByList.getWidth()+offsetX);
			}
		}
		_oldX = x;
	}
	// ino.end

	// ino.method.scaleObjectEastOnGrid.3202.description type=line
	// Diese Funktion verändert(zieht) die Form eines Objektes in östlicher Richtung,  
	// wenn das Raster aktiv ist.
	// ino.end
	// ino.method.scaleObjectEastOnGrid.3202.definition 
	public static void scaleObjectEastOnGrid(SmWorkingArea workingArea,Graphics g,SmGrid grid,SmEladoObjectList objectList,int x,int y)
	// ino.end
	// ino.method.scaleObjectEastOnGrid.3202.body 
	{
		int ObjectCount = objectList.getObjectCount();
		int i;
		int offsetX = ((x - _oldX)/grid.getXDistance())*grid.getXDistance();
		SmObject objectByList;
		
		Enumeration enum = objectList.getElementsOfList();

		for(i=0;i < ObjectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setIsSelected(false);
			workingArea.repaint(objectByList.getX()-4,
								objectByList.getY()-4,
								objectByList.getWidth()+8,
								objectByList.getHeight()+8);

			g.setClip(objectByList.getX()-4,
					  objectByList.getY()-4,
					  objectByList.getWidth()+offsetX+40,
					  objectByList.getHeight()+8);

			if ((Math.abs(offsetX)>=grid.getXDistance()))
			{
				if(objectByList instanceof SmGroupObject)
				{
					objectByList.setX(offsetX);
					objectByList.setWidth(offsetX);
				}
				else
				{
					objectByList.setWidth((((objectByList.getWidth()+objectByList.getX()+offsetX)/grid.getXDistance())*grid.getXDistance())-objectByList.getX());
				}
				_oldX = x;
			}
		}
	}
	// ino.end

	// ino.method.scaleObjectWest.3205.description type=line
	// Diese Funktion verändert(zieht) die Form eines Objektes in westlicher Richtung, 
	// wenn das Raster inaktiv ist.
	// ino.end
	// ino.method.scaleObjectWest.3205.definition 
	public static void scaleObjectWest(SmWorkingArea workingArea,Graphics g,SmEladoObjectList objectList,int x,int y)
	// ino.end
	// ino.method.scaleObjectWest.3205.body 
	{
		int ObjectCount = objectList.getObjectCount();
		int i;
		int offsetX = _oldX-x;
		SmObject objectByList;
		
		Enumeration enum = objectList.getElementsOfList();

		for(i=0;i < ObjectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setIsSelected(false);
			workingArea.repaint(objectByList.getX()-4,
								objectByList.getY()-4,
								objectByList.getWidth()+8,
								objectByList.getHeight()+8);

			g.setClip(x-40,
					  objectByList.getY()-4,
					  objectByList.getWidth()+offsetX+40,
					  objectByList.getHeight()+8);

			if(objectByList instanceof SmGroupObject)
			{
				objectByList.moveX(objectByList.getX()-offsetX);
				objectByList.setX(offsetX);
				objectByList.setWidth(offsetX);
				
			}
			else
			{
				objectByList.setWidth(objectByList.getWidth()+offsetX);
				objectByList.setX(objectByList.getX()-offsetX);
			}
		}
		_oldX = x;
	}
	// ino.end

	// ino.method.scaleObjectWestOnGrid.3208.description type=line
	// Diese Funktion verändert(zieht) die Form eines Objektes in westlicher Richtung, 
	// wenn das Raster aktiv ist.
	// ino.end
	// ino.method.scaleObjectWestOnGrid.3208.definition 
	public static void scaleObjectWestOnGrid(SmWorkingArea workingArea,Graphics g,SmGrid grid,SmEladoObjectList objectList,int x,int y)
	// ino.end
	// ino.method.scaleObjectWestOnGrid.3208.body 
	{
		int ObjectCount = objectList.getObjectCount();
		int i;
		int offsetX = ((_oldX-x)/grid.getXDistance())*grid.getXDistance();
		SmObject objectByList;
		
		Enumeration enum = objectList.getElementsOfList();

		for(i=0;i < ObjectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setIsSelected(false);
			workingArea.repaint(objectByList.getX()-4,
								objectByList.getY()-4,
								objectByList.getWidth()+8,
								objectByList.getHeight()+8);

			g.setClip(x-40,
					  objectByList.getY()-4,
					  objectByList.getWidth()+offsetX+40,
					  objectByList.getHeight()+8);

			if ((Math.abs(offsetX)>=grid.getXDistance()))
			{
				if(objectByList instanceof SmGroupObject)
				{
					objectByList.moveX(objectByList.getX()-offsetX);
					objectByList.setX(offsetX);
					objectByList.setWidth(offsetX);
				}
				else
				{
					int objectXOld = objectByList.getX();
					objectByList.setX(((objectByList.getX()/grid.getXDistance())*grid.getXDistance())-offsetX);
					objectByList.setWidth(objectByList.getWidth()+objectXOld-objectByList.getX());
				}
				_oldX = x;
			}
		}
	}
	// ino.end

	// ino.method.scaleObjectNorthEast.3211.description type=line
	// Diese Funktion verändert(zieht) die Form eines Objektes in nord-östlicher       
	// Richtung, wenn das Raster inaktiv ist.
	// ino.end
	// ino.method.scaleObjectNorthEast.3211.definition 
	public static void scaleObjectNorthEast(SmWorkingArea workingArea,Graphics g,SmEladoObjectList objectList,int x,int y)
	// ino.end
	// ino.method.scaleObjectNorthEast.3211.body 
	{
		int ObjectCount = objectList.getObjectCount();
		int i;
		int offsetX = x-_oldX;
		int offsetY = _oldY-y;
		SmObject objectByList;
		
		Enumeration enum = objectList.getElementsOfList();

		for(i=0;i < ObjectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setIsSelected(false);
			workingArea.repaint(objectByList.getX()-4,
								objectByList.getY()-4,
								objectByList.getWidth()+8,
								objectByList.getHeight()+8);

			g.setClip(objectByList.getX()-4,
					  y-40,
					  objectByList.getWidth()+offsetX+40+8,
					  objectByList.getHeight()+offsetY+40+8);

			if(objectByList instanceof SmGroupObject)
			{
				objectByList.moveY(objectByList.getY()-offsetY);
				objectByList.setX(offsetX);
				objectByList.setY(offsetY);
				objectByList.setWidth(offsetX);
				objectByList.setHeight(offsetY);
			}
			else
			{
				objectByList.setY(objectByList.getY()-offsetY);
				objectByList.setWidth(objectByList.getWidth()+offsetX);
				objectByList.setHeight(objectByList.getHeight()+offsetY);
			}
		}
		_oldX = x;
		_oldY = y;
	}
	// ino.end

	// ino.method.scaleObjectNorthEastOnGrid.3214.description type=line
	// Diese Funktion verändert(zieht) die Form eines Objektes in nord-östlicher       
	// Richtung, wenn das Raster aktiv ist.
	// ino.end
	// ino.method.scaleObjectNorthEastOnGrid.3214.definition 
	public static void scaleObjectNorthEastOnGrid(SmWorkingArea workingArea,Graphics g,SmGrid grid,SmEladoObjectList objectList,int x,int y)
	// ino.end
	// ino.method.scaleObjectNorthEastOnGrid.3214.body 
	{
		int ObjectCount = objectList.getObjectCount();
		int i;
		int offsetX = ((x - _oldX)/grid.getXDistance())*grid.getXDistance();
		int offsetY = ((_oldY - y)/grid.getYDistance())*grid.getYDistance();
		SmObject objectByList;
		
		Enumeration enum = objectList.getElementsOfList();

		for(i=0;i < ObjectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setIsSelected(false);
			workingArea.repaint(objectByList.getX()-4,
								objectByList.getY()-4,
								objectByList.getWidth()+8,
								objectByList.getHeight()+8);

			g.setClip(objectByList.getX()-4,
					  y-40,
					  objectByList.getWidth()+offsetX+40+8,
					  objectByList.getHeight()+offsetY+40+8);

			if ((Math.abs(offsetX)>=grid.getXDistance()))
			{
				if(objectByList instanceof SmGroupObject)
				{
					objectByList.setX(offsetX);
					objectByList.setWidth(offsetX);
				}
				else
				{
					objectByList.setWidth((((objectByList.getWidth()+objectByList.getX()+offsetX)/grid.getXDistance())*grid.getXDistance())-objectByList.getX());
				}
				_oldX = x;
			}
			if ((Math.abs(offsetY)>=grid.getYDistance()))
			{
				if(objectByList instanceof SmGroupObject)
				{
					objectByList.moveY(objectByList.getY()-offsetY);
					objectByList.setY(offsetY);
					objectByList.setHeight(offsetY);
				}
				else
				{
					int objectYOld = objectByList.getY();
					objectByList.setY(((objectByList.getY()/grid.getYDistance())*grid.getYDistance())-offsetY);
					objectByList.setHeight(objectByList.getHeight()+objectYOld-objectByList.getY());
				}
				_oldY = y;
			}
		}
	}
	// ino.end

	// ino.method.scaleObjectNorthWest.3217.description type=line
	// Diese Funktion verändert(zieht) die Form eines Objektes in nord-westlicher      
	// Richtung, wenn das Raster inaktiv ist.
	// ino.end
	// ino.method.scaleObjectNorthWest.3217.definition 
	public static void scaleObjectNorthWest(SmWorkingArea workingArea,Graphics g,SmEladoObjectList objectList,int x,int y)
	// ino.end
	// ino.method.scaleObjectNorthWest.3217.body 
	{
		int ObjectCount = objectList.getObjectCount();
		int i;
		int offsetX = _oldX-x;
		int offsetY = _oldY-y;
		SmObject objectByList;
			
		Enumeration enum = objectList.getElementsOfList();

		for(i=0;i < ObjectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setIsSelected(false);
			workingArea.repaint(objectByList.getX()-4,
								objectByList.getY()-4,
								objectByList.getWidth()+8,
								objectByList.getHeight()+8);

			g.setClip(objectByList.getX()-offsetX-40,
					  objectByList.getY()-offsetY-40,
					  objectByList.getWidth()+offsetX+40+8,
					  objectByList.getHeight()+offsetY+40+8);

			if(objectByList instanceof SmGroupObject)
			{
				objectByList.moveX(objectByList.getX()-offsetX);
				objectByList.moveY(objectByList.getY()-offsetY);
				objectByList.setX(offsetX);
				objectByList.setY(offsetY);
				objectByList.setWidth(offsetX);
				objectByList.setHeight(offsetY);
			}
			else
			{
				if (objectByList instanceof SmLineObject)
				{
					objectByList.setXp2(objectByList.getX2()-offsetX);
					objectByList.setYp2(objectByList.getY2()-offsetY);
				}
				else
				{
					objectByList.setX(objectByList.getX()-offsetX);
					objectByList.setY(objectByList.getY()-offsetY);
					objectByList.setWidth(objectByList.getWidth()+offsetX);
					objectByList.setHeight(objectByList.getHeight()+offsetY);
				}
			}
		}
		_oldX = x;
		_oldY = y;
	}
	// ino.end

	// ino.method.scaleObjectNorthWestOnGrid.3220.description type=line
	// Diese Funktion verändert(zieht) die Form eines Objektes in nord-westlicher      
	// Richtung, wenn das Raster aktiv ist.
	// ino.end
	// ino.method.scaleObjectNorthWestOnGrid.3220.definition 
	public static void scaleObjectNorthWestOnGrid(SmWorkingArea workingArea,Graphics g,SmGrid grid,SmEladoObjectList objectList,int x,int y)
	// ino.end
	// ino.method.scaleObjectNorthWestOnGrid.3220.body 
	{
		int ObjectCount = objectList.getObjectCount();
		int i;
		int offsetX = ((_oldX - x)/grid.getXDistance())*grid.getXDistance();
		int offsetY = ((_oldY - y)/grid.getYDistance())*grid.getYDistance();
		SmObject objectByList;
		
		Enumeration enum = objectList.getElementsOfList();

		for(i=0;i < ObjectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setIsSelected(false);
			workingArea.repaint(objectByList.getX()-4,
								objectByList.getY()-4,
								objectByList.getWidth()+8,
								objectByList.getHeight()+8);

			g.setClip(objectByList.getX()-offsetX-40,
					  objectByList.getY()-offsetY-40,
					  objectByList.getWidth()+offsetX+40+8,
					  objectByList.getHeight()+offsetY+40+8);

			if ((Math.abs(offsetX)>=grid.getXDistance()))
			{
				int objectXOld = objectByList.getX();
				if (objectByList instanceof SmLineObject)
				{
					objectByList.setXp2(((objectByList.getX2()/grid.getXDistance())*grid.getXDistance())-offsetX);
				}
				else if(objectByList instanceof SmGroupObject)
				{
					objectByList.moveX(objectByList.getX()-offsetX);
					objectByList.setX(offsetX);
					objectByList.setWidth(offsetX);
				}
				else
				{
					objectByList.setX(((objectByList.getX()/grid.getXDistance())*grid.getXDistance())-offsetX);
					objectByList.setWidth(objectByList.getWidth()+objectXOld-objectByList.getX());
				}
				_oldX = x;
			}
			if ((Math.abs(offsetY)>=grid.getYDistance()))
			{
				int objectYOld = objectByList.getY();
				if (objectByList instanceof SmLineObject)
				{
					objectByList.setYp2(((objectByList.getY2()/grid.getYDistance())*grid.getYDistance())-offsetY);
				}
				else if(objectByList instanceof SmGroupObject)
				{
					objectByList.moveY(objectByList.getY()-offsetY);
					objectByList.setY(offsetY);
					objectByList.setHeight(offsetY);
				}
				else
				{
					objectByList.setY(((objectByList.getY()/grid.getYDistance())*grid.getYDistance())-offsetY);
					objectByList.setHeight(objectByList.getHeight()+objectYOld-objectByList.getY());
				}
				_oldY = y;
			}
		}
	}
	// ino.end

	// ino.method.scaleObjectSouthEast.3223.description type=line
	// Diese Funktion verändert(zieht) die Form eines Objektes in süd-östlicher        
	// Richtung, wenn das Raster inaktiv ist.
	// ino.end
	// ino.method.scaleObjectSouthEast.3223.definition 
	public static void scaleObjectSouthEast(SmWorkingArea workingArea,Graphics g,SmEladoObjectList objectList,int x,int y)
	// ino.end
	// ino.method.scaleObjectSouthEast.3223.body 
	{
		int ObjectCount = objectList.getObjectCount();
		int i;
		int offsetX = x-_oldX;
		int offsetY = y-_oldY;
		SmObject objectByList;
		
		Enumeration enum = objectList.getElementsOfList();

		for(i=0;i < ObjectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setIsSelected(false);
			workingArea.repaint(objectByList.getX()-4,
								objectByList.getY()-4,
								objectByList.getWidth()+8,
								objectByList.getHeight()+8);

			g.setClip(objectByList.getX()-4,
					  objectByList.getY()-4,
					  objectByList.getWidth()+offsetX+40,
					  objectByList.getHeight()+offsetY+40);

			if(objectByList instanceof SmGroupObject)
			{
				objectByList.setX(offsetX);
				objectByList.setY(offsetY);
				objectByList.setWidth(offsetX);
				objectByList.setHeight(offsetY);
			}
			else
			{
				objectByList.setWidth(objectByList.getWidth()+offsetX);
				objectByList.setHeight(objectByList.getHeight()+offsetY);
			}
		}
		_oldX = x;
		_oldY = y;
	}
	// ino.end
	
	// ino.method.scaleObjectSouthEastOnGrid.3226.description type=line
	// Diese Funktion verändert(zieht) die Form eines Objektes in süd-östlicher        
	// Richtung, wenn das Raster aktiv ist.
	// ino.end
	// ino.method.scaleObjectSouthEastOnGrid.3226.definition 
	public static void scaleObjectSouthEastOnGrid(SmWorkingArea workingArea,Graphics g,SmGrid grid,SmEladoObjectList objectList,int x,int y)
	// ino.end
	// ino.method.scaleObjectSouthEastOnGrid.3226.body 
	{
		int ObjectCount = objectList.getObjectCount();
		int i;
		int offsetX = ((x - _oldX)/grid.getXDistance())*grid.getXDistance();
		int offsetY = ((y - _oldY)/grid.getYDistance())*grid.getYDistance();
		SmObject objectByList;
		
		Enumeration enum = objectList.getElementsOfList();

		for(i=0;i < ObjectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setIsSelected(false);
			workingArea.repaint(objectByList.getX()-4,
								objectByList.getY()-4,
								objectByList.getWidth()+8,
								objectByList.getHeight()+8);

			g.setClip(objectByList.getX()-4,
					  objectByList.getY()-4,
					  objectByList.getWidth()+offsetX+40,
					  objectByList.getHeight()+offsetY+40);

			if ((Math.abs(offsetX)>=grid.getXDistance()))
			{
				if(objectByList instanceof SmGroupObject)
				{
					objectByList.setX(offsetX);
					objectByList.setWidth(offsetX);
				}
				else
				{
					objectByList.setWidth((((objectByList.getWidth()+objectByList.getX()+offsetX)/grid.getXDistance())*grid.getXDistance())-objectByList.getX());
				}
				_oldX = x;
			}
			if ((Math.abs(offsetY)>=grid.getYDistance()))
			{
				if(objectByList instanceof SmGroupObject)
				{
					objectByList.setY(offsetY);
					objectByList.setHeight(offsetY);
				}
				else
				{
					objectByList.setHeight((((objectByList.getHeight()+objectByList.getY()+offsetY)/grid.getYDistance())*grid.getYDistance())-objectByList.getY());
				}
				_oldY = y;
			}
		}
	}
	// ino.end

	// ino.method.scaleObjectSouthWest.3229.description type=line
	// Diese Funktion verändert(zieht) die Form eines Objektes in süd-westlicher       
	// Richtung, wenn das Raster inaktiv ist.
	// ino.end
	// ino.method.scaleObjectSouthWest.3229.definition 
	public static void scaleObjectSouthWest(SmWorkingArea workingArea,Graphics g,SmEladoObjectList objectList,int x,int y)
	// ino.end
	// ino.method.scaleObjectSouthWest.3229.body 
	{
		int ObjectCount = objectList.getObjectCount();
		int i;
		int offsetX = _oldX-x;
		int offsetY = y-_oldY;
		SmObject objectByList;
		
		Enumeration enum = objectList.getElementsOfList();

		for(i=0;i < ObjectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setIsSelected(false);
			workingArea.repaint(objectByList.getX()-4,
								objectByList.getY()-4,
								objectByList.getWidth()+8,
								objectByList.getHeight()+8);

			g.setClip(x-40,
					  objectByList.getY()-4,
					  objectByList.getWidth()+offsetX+40+8,
					  objectByList.getHeight()+offsetY+40+8);

			if(objectByList instanceof SmGroupObject)
			{
				objectByList.moveX(objectByList.getX()-offsetX);
				objectByList.setX(offsetX);
				objectByList.setWidth(offsetX);
				objectByList.setY(offsetY);
				objectByList.setHeight(offsetY);
			}
			else
			{
				if (objectByList instanceof SmLineObject)
				{
					objectByList.setXp1(objectByList.getX1()-offsetX);
					objectByList.setYp1(objectByList.getY1()+offsetY);
				}
				else
				{
					objectByList.setX(objectByList.getX()-offsetX);
					objectByList.setWidth(objectByList.getWidth()+offsetX);
					objectByList.setHeight(objectByList.getHeight()+offsetY);
				}
			}
			
		}
		_oldX = x;
		_oldY = y;
	}
	// ino.end

	// ino.method.scaleObjectSouthWestOnGrid.3232.description type=line
	// Diese Funktion verändert(zieht) die Form eines Objektes in süd-westlicher       
	// Richtung, wenn das Raster aktiv ist.
	// ino.end
	// ino.method.scaleObjectSouthWestOnGrid.3232.definition 
	public static void scaleObjectSouthWestOnGrid(SmWorkingArea workingArea,Graphics g,SmGrid grid,SmEladoObjectList objectList,int x,int y)
	// ino.end
	// ino.method.scaleObjectSouthWestOnGrid.3232.body 
	{
		int ObjectCount = objectList.getObjectCount();
		int i;
		int offsetX = ((_oldX - x)/grid.getXDistance())*grid.getXDistance();
		int offsetY = ((y - _oldY)/grid.getYDistance())*grid.getYDistance();
		SmObject objectByList;
		
		Enumeration enum = objectList.getElementsOfList();

		for(i=0;i < ObjectCount;i++)
		{
			objectByList = (SmObject)enum.nextElement();
			objectByList.setIsSelected(false);
			workingArea.repaint(objectByList.getX()-4,
								objectByList.getY()-4,
								objectByList.getWidth()+8,
								objectByList.getHeight()+8);

			g.setClip(x-40,
					  objectByList.getY()-4,
					  objectByList.getWidth()+offsetX+40+8,
					  objectByList.getHeight()+offsetY+40+8);
			
			if ((Math.abs(offsetX)>=grid.getXDistance()))
			{
				int objectXOld = objectByList.getX();
				if (objectByList instanceof SmLineObject)
				{
					objectByList.setXp1(((objectByList.getX1()/grid.getXDistance())*grid.getXDistance())-offsetX);
				}
				else if(objectByList instanceof SmGroupObject)
				{
					objectByList.moveX(objectByList.getX()-offsetX);
					objectByList.setX(offsetX);
					objectByList.setWidth(offsetX);
				}
				else
				{
					objectByList.setX(((objectByList.getX()/grid.getXDistance())*grid.getXDistance())-offsetX);
					objectByList.setWidth(objectByList.getWidth()+objectXOld-objectByList.getX());
				}
				_oldX = x;
			}
			if ((Math.abs(offsetY)>=grid.getYDistance()))
			{
				if (objectByList instanceof SmLineObject)
				{
					objectByList.setYp1(((objectByList.getY1()/grid.getYDistance())*grid.getYDistance())+offsetY);
				}
				else if(objectByList instanceof SmGroupObject)
				{
					objectByList.setY(offsetY);
					objectByList.setHeight(offsetY);
				}
				else
				{
					objectByList.setHeight((((objectByList.getHeight()+objectByList.getY()+offsetY)/grid.getYDistance())*grid.getYDistance())-objectByList.getY());
				}
				_oldY = y;
			}
		}
	}
	// ino.end

	// ino.method.setOldX.3235.description type=line
	// Diese Funktion setzt die letzte X-Position der Maus.
	// ino.end
	// ino.method.setOldX.3235.definition 
	public static void setOldX(int oldX)
	// ino.end
	// ino.method.setOldX.3235.body 
	{
		_oldX = oldX;
	}
	// ino.end

	// ino.method.setOldY.3238.description type=line
	// Diese Funktion setzt die letzte Y-Position der Maus.
	// ino.end
	// ino.method.setOldY.3238.definition 
	public static void setOldY(int oldY)
	// ino.end
	// ino.method.setOldY.3238.body 
	{
		_oldY = oldY;
	}
	// ino.end
}
// ino.end
