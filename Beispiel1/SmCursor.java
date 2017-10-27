import java.awt.*;


public class SmCursor extends Component 
{
	private int _oldMouseX;
	private int _oldMouseY;
	private int _newMouseX;
	private int _newMouseY;

	public static final int CURSOR_BOUNDS = 4;

	public SmCursor()
	{
		_oldMouseX = 0;
		_oldMouseY = 0;
		_newMouseX = 0;
	    _newMouseY = 0;
	}

	public void setCoordinates(int x,int y)
	{
		_oldMouseX = _newMouseX;
		_oldMouseY = _newMouseY;
		_newMouseX = x;
		_newMouseY = y;
	}

	public int getXCoordinate()
	{
		return _newMouseX;
	}

	public int getYCoordinate()
	{
		return _newMouseY;
	}

	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.drawLine(_newMouseX-CURSOR_BOUNDS,_newMouseY,_newMouseX+CURSOR_BOUNDS,_newMouseY);
		g.drawLine(_newMouseX,_newMouseY-CURSOR_BOUNDS,_newMouseX,_newMouseY+CURSOR_BOUNDS);
		g.clearRect(_oldMouseX-CURSOR_BOUNDS,_oldMouseY-CURSOR_BOUNDS,2*CURSOR_BOUNDS+2, 2*CURSOR_BOUNDS+2);
	}
}

