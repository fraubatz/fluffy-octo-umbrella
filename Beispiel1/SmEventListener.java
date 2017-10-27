import java.awt.event.*;
import java.awt.*;


public class SmEventListener implements ActionListener
{

	public static String _actionCommand;

	public SmEventListener()
	{
		_actionCommand = null;
	}

	public void actionPerformed(ActionEvent e)
	{
		_actionCommand = e.getActionCommand();		
	}

}

