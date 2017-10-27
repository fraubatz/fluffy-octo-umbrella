import java.awt.*;
import java.awt.event.*;


public class SmScrollPane extends ScrollPane 
{
	public SmScrollPane()
	{
		this.setSize(500,500);
	}
	
	public void paint(Graphics g)
	{
		System.out.println("ScrollPane wird aktualisiert");
	}
}

