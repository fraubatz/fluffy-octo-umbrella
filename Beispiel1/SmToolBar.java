import javax.swing*;
import java.awt.*;
import java.awt.event.*;

// ino.class.SmToolBar.454.description type=line
// Die Klasse SmToolBar ist die Werkzeugleiste des Symboleditors. Die              
// Werkzeugleiste ist eine Komponente von JAVA Swing und besteht aus einer         
// definierten Anzahl von Buttons. In die Buttons können .gif-Grafiken eingebunden 
// werden, die als Icons der Buttons definiert werden. Die Attribute sind          
// selbsterklärend, deshalb wird auf eine nähere Beschreibung verzichtet.
// ino.end
// ino.class.SmToolBar.454.declaration 
public class SmToolBar extends JPanel
// ino.end
// ino.class.SmToolBar.454.body
{
	// ino.attribute._frame.3565.declaration 
	Frame _frame;
	// ino.end

	// ino.attribute.icon1.749.declaration 
	protected static ImageIcon icon1 = new ImageIcon("new.gif");
	// ino.end
	
	// ino.attribute.icon2.752.declaration 
	protected static ImageIcon icon2 = new ImageIcon("open.gif");
	// ino.end
		
	// ino.attribute.icon3.755.declaration 
	protected static ImageIcon icon3 = new ImageIcon("save.gif");
	// ino.end
		
	// ino.attribute.icon4.758.declaration 
	protected static ImageIcon icon4 = new ImageIcon("cut.gif");
	// ino.end
		
	// ino.attribute.icon5.761.declaration 
	protected static ImageIcon icon5 = new ImageIcon("copy.gif");
	// ino.end
		
	// ino.attribute.icon6.764.declaration 
	protected static ImageIcon icon6 = new ImageIcon("paste.gif");
	// ino.end
	
	// ino.attribute.icon7.767.declaration 
	protected static ImageIcon icon7 = new ImageIcon("del.gif");
	// ino.end
	
	// ino.attribute.icon8.770.declaration 
	protected static ImageIcon icon8 = new ImageIcon("group.gif");
	// ino.end
	
	// ino.attribute.icon9.773.declaration 
	protected static ImageIcon icon9 = new ImageIcon("ungroup.gif");
	// ino.end
	
	// ino.attribute.icon10.776.declaration 
	protected static ImageIcon icon10 = new ImageIcon("foreground.gif");
	// ino.end

	// ino.attribute.icon11.779.declaration 
	protected static ImageIcon icon11 = new ImageIcon("background.gif");
	// ino.end

	// ino.attribute.icon12.782.declaration 
	protected static ImageIcon icon12 = new ImageIcon("gridon.gif");
	// ino.end
		
	// ino.attribute.icon13.785.declaration 
	protected static ImageIcon icon13 = new ImageIcon("shape.gif");
	// ino.end
	
	// ino.attribute.icon14.788.declaration 
	protected static ImageIcon icon14 = new ImageIcon("select.gif");
	// ino.end
	
	// ino.attribute.icon15.791.declaration 
	protected static ImageIcon icon15 = new ImageIcon("coloropt.gif");
	// ino.end
	
	// ino.attribute.icon16.794.declaration 
	protected static ImageIcon icon16 = new ImageIcon("linestyle.gif");
	// ino.end
	
	// ino.attribute.icon17.797.declaration 
	protected static ImageIcon icon17 = new ImageIcon("line.gif");
	// ino.end
		
	// ino.attribute.icon18.800.declaration 
	protected static ImageIcon icon18 = new ImageIcon("rect.gif");
	// ino.end
	
	// ino.attribute.icon19.803.declaration 
	protected static ImageIcon icon19 = new ImageIcon("ellipse.gif");
	// ino.end
	
	// ino.attribute.icon20.806.declaration 
	protected static ImageIcon icon20 = new ImageIcon("text.gif");
	// ino.end
	
	// ino.attribute.icon21.809.declaration 
	protected static ImageIcon icon21 = new ImageIcon("pin.gif");
	// ino.end
	
	// ino.attribute.icon22.3568.declaration 
	protected static ImageIcon icon22 = new ImageIcon("arrow.gif");
	// ino.end
		
	// ino.attribute.icon23.3571.declaration 
	protected static ImageIcon icon23 = new ImageIcon("graphics.gif");
	// ino.end
	
	// ino.attribute.button1.812.declaration 
	protected static JButton button1 = new JButton(icon1);
	// ino.end
	
	// ino.attribute.button2.815.declaration 
	protected static JButton button2 = new JButton(icon2);
	// ino.end
	
	// ino.attribute.button3.818.declaration 
	protected static JButton button3 = new JButton(icon3);
	// ino.end
	
	// ino.attribute.button4.821.declaration 
	protected static JButton button4 = new JButton(icon4);
	// ino.end
	
	// ino.attribute.button5.824.declaration 
	protected static JButton button5 = new JButton(icon5);
	// ino.end
	
	// ino.attribute.button6.827.declaration 
	protected static JButton button6 = new JButton(icon6);
	// ino.end
	
	// ino.attribute.button7.830.declaration 
	protected static JButton button7 = new JButton(icon7);
	// ino.end
	
	// ino.attribute.button8.833.declaration 
	protected static JButton button8 = new JButton(icon8);
	// ino.end
		
	// ino.attribute.button9.836.declaration 
	protected static JButton button9 = new JButton(icon9);
	// ino.end
		
	// ino.attribute.button10.839.declaration 
	protected static JButton button10 = new JButton(icon10);
	// ino.end

	// ino.attribute.button11.842.declaration 
	protected static JButton button11 = new JButton(icon11);
	// ino.end

	// ino.attribute.button12.845.declaration 
	protected static JButton button12 = new JButton(icon12);
	// ino.end
	
	// ino.attribute.button13.848.declaration 
	protected static JButton button13 = new JButton(icon13);
	// ino.end
	
	// ino.attribute.button14.851.declaration 
	protected static JButton button14 = new JButton(icon14);
	// ino.end
	
	// ino.attribute.button15.854.declaration 
	protected static JButton button15 = new JButton(icon15);
	// ino.end
	
	// ino.attribute.button16.857.declaration 
	protected static JButton button16 = new JButton(icon16);
	// ino.end

	// ino.attribute.button17.860.declaration 
	protected static JButton button17 = new JButton(icon17);
	// ino.end

	// ino.attribute.button18.863.declaration 
	protected static JButton button18 = new JButton(icon18);
	// ino.end
	
	// ino.attribute.button19.866.declaration 
	protected static JButton button19 = new JButton(icon19);
	// ino.end
		
	// ino.attribute.button20.869.declaration 
	protected static JButton button20 = new JButton(icon20);
	// ino.end
	
	// ino.attribute.button21.872.declaration 
	protected static JButton button21 = new JButton(icon21);
	// ino.end
	
	// ino.attribute.button22.3574.declaration 
	protected static JButton button22 = new JButton(icon22);
	// ino.end
		
	// ino.attribute.button23.3577.declaration 
	protected static JButton button23 = new JButton(icon23);
	// ino.end
	
	// ino.method.SmToolBar.3580.description type=line
	// Dem Parameterkonstruktor wird eine Referenz auf das Hauptfenster übergeben. Den 
	// Buttons wird ein Command-Name und ein Listener-Objekt für die                   
	// Ereignisverarbeitung zugewiesen und in die Werkzeugleiste eingefügt.
	// ino.end
	// ino.method.SmToolBar.3580.definition 
	public SmToolBar(Frame frame)
	// ino.end
	// ino.method.SmToolBar.3580.body 
	{
		_frame = frame;
		
		JToolBar Werkzeugleiste = new JToolBar();
		SmEventListener _eventListener 	= new SmEventListener();
		
		button1.setActionCommand("new");
		button1.addActionListener((ActionListener) _frame);
		button2.setActionCommand("open");
		button2.addActionListener((ActionListener) _frame);
		button3.setActionCommand("save");
		button3.addActionListener((ActionListener) _frame);
		button4.setActionCommand("cut");
		button4.setEnabled(false);
		button5.setActionCommand("copy");
		button5.setEnabled(false);
		button6.setActionCommand("paste");
		button6.setEnabled(false);
		button7.setActionCommand("delete");
		button7.setEnabled(false);
		button8.setActionCommand("group");
		button8.setEnabled(false);
		button9.setActionCommand("ungroup");
		button9.setEnabled(false);
		button10.setActionCommand("foreground");
		button10.setEnabled(false);
		button11.setActionCommand("background");
		button11.setEnabled(false);
		button14.setActionCommand("select");
		button14.setEnabled(false);
		button15.setActionCommand("colorfill");
		button15.setEnabled(false);
		button17.setActionCommand("line");
		button17.setEnabled(false);
		button18.setActionCommand("rect");
		button18.setEnabled(false);
		button19.setActionCommand("ellipse");
		button19.setEnabled(false);
		button23.setActionCommand("image");
		button23.setEnabled(false);
		

		Werkzeugleiste.addSeparator();
		Werkzeugleiste.add(button1);
		Werkzeugleiste.add(button2);
		Werkzeugleiste.add(button3);
		Werkzeugleiste.addSeparator();
		Werkzeugleiste.add(button4);
		Werkzeugleiste.add(button5);
		Werkzeugleiste.add(button6);
		Werkzeugleiste.add(button7);
		Werkzeugleiste.add(button8);
		Werkzeugleiste.add(button9);
		Werkzeugleiste.add(button10);
		Werkzeugleiste.add(button11);
	    Werkzeugleiste.add(button12);
		Werkzeugleiste.add(button13);
		Werkzeugleiste.add(button14);
		Werkzeugleiste.addSeparator();
		Werkzeugleiste.add(button15);
		Werkzeugleiste.add(button16);
	    Werkzeugleiste.addSeparator();
		Werkzeugleiste.addSeparator();
		Werkzeugleiste.add(button17);
	    Werkzeugleiste.add(button18);
		Werkzeugleiste.add(button19);
		Werkzeugleiste.add(button20);
		Werkzeugleiste.add(button21);
		Werkzeugleiste.add(button22);
		Werkzeugleiste.add(button23);

		Werkzeugleiste.setBackground(Color.lightGray);
		Werkzeugleiste.setBorderPainted(false);
		Werkzeugleiste.setFloatable(false);
			
		this.setLayout(new GridLayout(1,1));
		this.add(Werkzeugleiste);
		this.setBackground(Color.lightGray);
	}
	// ino.end

	// ino.method.setToolbarItemProperties.3583.description type=line
	// Diese Funktion setzt die Eigenschaften eines Buttons in der Werkzeugleiste. Als 
	// Parameter werden der Button selbst, der Enable-Status und das Listener-Objekt   
	// übergeben.
	// ino.end
	// ino.method.setToolbarItemProperties.3583.definition 
	public void setToolbarItemProperties(JButton button,boolean mode,ActionListener listener)
	// ino.end
	// ino.method.setToolbarItemProperties.3583.body 
	{
		try
		{
			button.setEnabled(mode);
			if (listener != null)
			{
				button.addActionListener(listener);
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
	}
	// ino.end
}
// ino.end



