import java.awt.*;
import SmEladoObjectList;


// ino.class.SmNetLayer.1330.description type=line
// Die Klasse SmNetLayer ist nur der Form halber erwähnt. Sie wird zu einem        
// späteren Zeitpunkt genauer definiert.
// ino.end
// ino.class.SmNetLayer.1330.declaration 
public class SmNetLayer extends SmLayer
// ino.end
// ino.class.SmNetLayer.1330.body
{
	// ino.attribute._netList.2387.declaration 
	private SmEladoObjectList _netList;
	// ino.end
	// ino.attribute._selectList.4056.declaration 
	private SmEladoObjectList _selectList;
	// ino.end
	

	
	// ino.attribute._isActivated.2397.declaration 
	private boolean _isActivated;
	// ino.end

	
	// ino.attribute._enableStateColor.2628.declaration 
	private Color _enableStateColor;
	// ino.end

	// ino.attribute._selectState.3862.declaration 
	private boolean _selectState;
	// ino.end

	// ino.method.SmNetLayer.2403.definition 
	public SmNetLayer()
	// ino.end
	// ino.method.SmNetLayer.2403.body 
	{
	   _netList			= new SmEladoObjectList();
	   _isActivated		= true;
	}
	// ino.end
	
	// ino.method.selectObject.2634.definition 
	public void selectObject(int x,int y)
	// ino.end
	// ino.method.selectObject.2634.body 
	{}
	// ino.end
	
	// ino.method.removeObject.3865.definition 
	public void removeObject(SmObject object)
	// ino.end
	// ino.method.removeObject.3865.body 
	{
		_netList.removeObjectByObject(object);	
	}
	// ino.end
	
	// ino.method.removeAllObjects.3868.definition 
	public void removeAllObjects()
	// ino.end
    // ino.method.removeAllObjects.3868.body 
    {
	    _netList.removeAllObjects();
    }
    // ino.end
		
	// ino.method.setEnableState.2649.definition 
	public void setEnableState(boolean isactive)
	// ino.end
	// ino.method.setEnableState.2649.body 
	{}
	// ino.end

	// ino.method.paint.2652.definition 
	public void paint(Graphics g)
	// ino.end
	// ino.method.paint.2652.body 
	{}
	// ino.end

	// ino.method.setSelectState.3871.definition 
	public void setSelectState(boolean mode)
	// ino.end
	// ino.method.setSelectState.3871.body 
	{}
	// ino.end
	
	// ino.method.getSelectState.3874.definition 
	public boolean getSelectState()
	// ino.end
	// ino.method.getSelectState.3874.body 
	{
		return _selectState;
	}
	// ino.end

	// ino.method.setObjectList.3877.definition 
	public void setObjectList(SmEladoObjectList objectList)
	// ino.end
	// ino.method.setObjectList.3877.body 
	{}
	// ino.end

	// ino.method.getObjectList.3880.definition 
	public SmEladoObjectList getObjectList()
	// ino.end
	// ino.method.getObjectList.3880.body 
	{
		return _netList;
	}
	// ino.end
	
	// ino.method.getSelectedObject.2667.definition 
	public SmEladoObjectList getSelectedObject()
	// ino.end
	// ino.method.getSelectedObject.2667.body 
	{
		return _netList;
	}
	// ino.end
	
	// ino.method.addObject.2670.definition 
	public void addObject(Object object)
	// ino.end
	// ino.method.addObject.2670.body 
	{}
	// ino.end
	
	// ino.method.getMinPoint.3883.definition 
	public Point getMinPoint()
	// ino.end
	// ino.method.getMinPoint.3883.body 
	{
		return new Point(800,800);
	}
	// ino.end

	// ino.method.getMaxPoint.3886.definition 
	public Point getMaxPoint()
	// ino.end
	// ino.method.getMaxPoint.3886.body 
	{
		return new Point(0,0);
	}
	// ino.end
}
// ino.end




