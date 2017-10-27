import java.awt.*;
import java.awt.event.*;


public class DlgOpenFile extends Dialog
{
	private Dimension _parentFrameSize;
	private Dimension _dialogFrameSize;
	private Point	  _parentFrameLocation;

	public DlgOpenFile(Frame f, boolean mode)
	{
		super (f, "Laden", mode);
		Panel pnl = new DlgOpenFilePnl(this, (SmMainFrame) f);
		add ("Center", pnl);
		setResizable (false);
		_parentFrameSize = f.getSize();
		_dialogFrameSize = pnl.getPreferredSize();
		_parentFrameLocation = f.getLocationOnScreen();
		setLocation(_parentFrameLocation.x+_parentFrameSize.width/2-_dialogFrameSize.width/2,
		     		_parentFrameLocation.y+_parentFrameSize.height/2-_dialogFrameSize.height/2);
		pack ();
		enableEvents (AWTEvent.WINDOW_EVENT_MASK);
	}

	public void	processEvent(AWTEvent evt)
	{
		if (evt.getID () == WindowEvent.WINDOW_CLOSING) 
		{
			  dispose ();
			  return;
		}
		super.processEvent (evt);
	}
} 





