import java.awt.*;
import java.io.*;

import java.util.zip.*;
import java.awt.datatransfer.*;	//Test auf jdk1.1.5


// ino.class.SampleDoc.3058.description type=line
// Ein Objekt dieser Klasse zeichnet ein ColorWheel-Objekt und liefert den         
// aktuellen Farbwert. SampleDoc ist eine fertige Komponente und wurde, leicht     
// modifiziert, in dieses Programm integriert. Aus diesem Grunde wird der Quelltext
// auch nicht weiter kommentiert.
// ino.end
// ino.class.SampleDoc.3058.declaration 
class SampleDoc extends Canvas
// ino.end
// ino.class.SampleDoc.3058.body
{
  // ino.attribute.imgw.3342.declaration 
  private int imgw =0;
  // ino.end
  
  // ino.attribute.imgh.3345.declaration 
  private int imgh =0;
  // ino.end
  // ino.attribute.mainImage.3348.declaration 
  private Image mainImage;
  // ino.end
  // ino.attribute.bgcolor.3351.declaration 
  private int bgcolor[];
  // ino.end
  // ino.attribute.tagtext.3354.declaration 
  private TextField tagtext;
  // ino.end

  // ino.method.SampleDoc.3357.definition 
  public SampleDoc(TextField tag)
  // ino.end
  // ino.method.SampleDoc.3357.body 
  {
    bgcolor = new int[3];
    
    tagtext = tag;

    bgcolor[0] = 0; bgcolor[1] = 128; bgcolor[2] = 0;
   
    setFont(new Font("Helvetica", Font.BOLD, 14));
  }
  // ino.end

  // ino.method.update.3360.definition 
  public void update(Graphics g)
  // ino.end
  // ino.method.update.3360.body 
  {
    paint(g);
  }
  // ino.end

  // ino.method.paint.3363.definition 
  public void paint(Graphics g)
  // ino.end
  // ino.method.paint.3363.body 
  {
    int cx, cy, step;    
    Rectangle boundrect = bounds();
    Graphics holdg;
    String rstr, gstr, bstr;
    String tagstr;

    tagstr = "COLOR= ";

    if(imgw!=bounds().width || imgh!=bounds().height)
    {
      mainImage=createImage(bounds().width, bounds().height);
      imgw = bounds().width;
      imgh = bounds().height;
    }

    holdg = g;

    if(mainImage!=null)
      g = mainImage.getGraphics();

    g.setColor(new Color(bgcolor[0], bgcolor[1], bgcolor[2])); // zeichnet das groﬂe Farbfenster
    g.fillRect(boundrect.x, boundrect.y, boundrect.width-2, boundrect.height-2);
    g.setColor(Color.black);    
    g.drawRect(boundrect.x, boundrect.y, boundrect.width-2, boundrect.height-2);
    step = (bounds().height-20)/5;

    cy = 20;

    rstr = Integer.toString( bgcolor[0], 16 ).toUpperCase();
    gstr = Integer.toString( bgcolor[1], 16 ).toUpperCase();
    bstr = Integer.toString( bgcolor[2], 16 ).toUpperCase();
    if( rstr.length() < 2 ) rstr = "0" + rstr;
    if( gstr.length() < 2 ) gstr = "0" + gstr;
    if( bstr.length() < 2 ) bstr = "0" + bstr;

    
    tagstr = tagstr + rstr + gstr + bstr;

        
    tagtext.setText(tagstr);

    if(mainImage!=null)
    {
      g = holdg;
      g.drawImage(mainImage, 0, 0, this);
     }
  }
  // ino.end

  // ino.method.setcolor.3366.defdescription type=line
  // Diese Funktion bestimmt den aktuellen Farbwert. Sie wird von ColorWheel         
  // aufgerufen. 
  // ino.end
  
  // ino.method.setcolor.3366.definition 
  public void setcolor(int which,int r,int g,int b)
  // ino.end
  // ino.method.setcolor.3366.body 
  {
    switch(which)
    {
      case 0:
        bgcolor[0] = r;
        bgcolor[1] = g;
        bgcolor[2] = b;
        break;
    }

    repaint();
  }
  // ino.end

  // ino.method.getcolor.3369.defdescription type=line
  // Diese Funktion liefert den aktuellen Farbwert
  // ino.end
  // ino.method.getcolor.3369.definition 
  public void getcolor(int which,int color[])
  // ino.end
  // ino.method.getcolor.3369.body 
  {
    switch(which)
    {
      case 0:
        color[0] = bgcolor[0];
        color[1] = bgcolor[1];
        color[2] = bgcolor[2];
        break;
    }
  }
  // ino.end

  // ino.method.getCurrentColor.3372.definition 
  public Color getCurrentColor()
  // ino.end
  // ino.method.getCurrentColor.3372.body 
  {
	  return new Color(bgcolor[0], bgcolor[1], bgcolor[2]);
  }
  // ino.end
}
// ino.end
