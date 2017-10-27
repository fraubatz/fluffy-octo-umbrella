import java.awt.*;
import java.awt.event.*;


public class DlgPinAttribute extends Dialog
{
	public DlgPinAttribute(Frame f,	boolean mode) 
	{
		super (f, "Pin Attribute", mode);
		Panel pnl = new DlgPinAttributePnl ();
		add ("Center", pnl);
		setResizable (false);
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

