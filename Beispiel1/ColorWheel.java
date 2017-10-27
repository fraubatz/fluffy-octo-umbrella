import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.util.zip.*;
import java.awt.datatransfer.*;	//Test auf jdk1.1.5

// ino.class.ColorWheel.3055.description type=line
// Diese Klasse repräsentiert einen Farbmischer und wird für die Vergabe der       
// Farbattribute verwendet. Der Farbmischer ist eine fertige Komponente und wurde, 
// leicht modifiziert, in dieses Programm integriert. Aus diesem Grunde wird der   
// Quelltext auch nicht weiter kommentiert.
// ino.end
// ino.class.ColorWheel.3055.declaration 
class ColorWheel extends Canvas
// ino.end
// ino.class.ColorWheel.3055.body
{
  // ino.attribute.brightness.3376.declaration 
  private int brightness =100;
  // ino.end
  // ino.attribute.pthue.3488.declaration 
  private float pthue =(float)0.0;
  // ino.end
  // ino.attribute.ptsat.3491.declaration 
  private float ptsat =(float)0.0;
  // ino.end
  // ino.attribute.holdbright.3385.declaration 
  private int holdbright =999;
  // ino.end
  // ino.attribute.wheelImage.3388.declaration 
  private Image wheelImage;
  // ino.end
 
  // ino.attribute.mainImage.3391.declaration 
  private Image mainImage;
  // ino.end
  // ino.attribute.imgw.3478.declaration 
  private int imgw =0;
  // ino.end
  // ino.attribute.imgh.3481.declaration 
  private int imgh =0;
  // ino.end
  // ino.attribute.sd.3394.declaration 
  private SampleDoc sd =null;
  // ino.end
  // ino.attribute.brightbar.3399.declaration 
  private Scrollbar brightbar =null;
  // ino.end
  // ino.attribute.brightlabel.3402.declaration 
  private Label brightlabel =null;
  // ino.end
  // ino.attribute.curtarget.3405.declaration 
  private int curtarget = 0;
  // ino.end
  // ino.attribute.rstep.3408.declaration 
  private int rstep =10;
  // ino.end
  
  // ino.attribute.astep.3411.declaration 
  private int astep =36;
  // ino.end
  // ino.attribute.polyx1.3414.declaration 
  private int polyx1[] = new int[(rstep+1)*(astep+1)];
  // ino.end
  // ino.attribute.polyx2.3417.declaration 
  private int polyx2[] = new int[(rstep+1)*(astep+1)];
  // ino.end
  // ino.attribute.polyx3.3420.declaration 
  private int polyx3[] = new int[(rstep+1)*(astep+1)];
  // ino.end
  // ino.attribute.polyx4.3423.declaration 
  private int polyx4[] = new int[(rstep+1)*(astep+1)];
  // ino.end
  // ino.attribute.polyy1.3426.declaration 
  private int polyy1[] = new int[(rstep+1)*(astep+1)];
  // ino.end
  // ino.attribute.polyy2.3429.declaration 
  private int polyy2[] = new int[(rstep+1)*(astep+1)];
  // ino.end
  // ino.attribute.polyy3.3432.declaration 
  private int polyy3[] = new int[(rstep+1)*(astep+1)];
  // ino.end
  // ino.attribute.polyy4.3435.declaration 
  private int polyy4[] = new int[(rstep+1)*(astep+1)];
  // ino.end
  // ino.attribute.polyr.3438.declaration 
  private int polyr[] = new int[(rstep+1)*(astep+1)];
  // ino.end
  // ino.attribute.polyg.3441.declaration 
  private int polyg[] = new int[(rstep+1)*(astep+1)];
  // ino.end
  // ino.attribute.polyb.3444.declaration 
  private int polyb[] = new int[(rstep+1)*(astep+1)];
  // ino.end

  // ino.method.ColorWheel.3447.definition 
  public ColorWheel(SampleDoc sampdoc,Scrollbar b,Label bl)
  // ino.end
  // ino.method.ColorWheel.3447.body 
  {
    sd = sampdoc;
    brightbar = b;
    brightlabel = bl;

    settarget(curtarget);
    buildpolygons(rstep, astep);

	this.setBackground(Color.lightGray);
  }
  // ino.end

  // ino.method.buildpolygons.3450.definition 
  private void buildpolygons(int raddiv,int anglediv)
  // ino.end
  // ino.method.buildpolygons.3450.body 
  {
    int index=0;
    int i, j, cx, cy, size;
    int step;
    float  radstep;
    Color tempcolor;
    int red, green, blue;
    int colorpack;
    float hue;

    float pt1[]          = new float[2];
    float pt2[]          = new float[2];
    float cur1[]         = new float[2];
    float cur2[]         = new float[2];
    float oldpt1[]       = new float[2];
    float oldpt2[]       = new float[2];
    int gray[]         = new int[3];
    int color[]        = new int[3];    
    float step1[]      = new float[2];
    float step2[]      = new float[2];
    float curfloat1[]  = new float[2];
    float curfloat2[]  = new float[2];
    float colorfloat[] = new float[3];
    float colorstep[]  = new float[3];

    step = 360/anglediv;
    radstep = (float)(1.0/(float)raddiv);

    gray[0] = gray[1] = gray[2] = 255;

    pt1[0] = (float)1.0;
    pt1[1] = (float)0.0;
    step1[0] = (float)(1.0/(float)raddiv);
    step1[1] = (float)0.0;

    for(i=0;i<=360;i+=step)    
    {
      hue = (float)i/(float)360.0;
      if(hue==1.0) hue = (float)0.0;
      colorpack = Color.HSBtoRGB(hue, (float)1.0, (float)1.0);
      color[0] = ((colorpack & 0x00ff0000) >> 16);
      color[1] = ((colorpack & 0x0000ff00) >> 8);
      color[2] = ((colorpack & 0x000000ff));

      pt2[0] = (float)(Math.cos((float)i*0.017453293));
      pt2[1] = (float)(Math.sin((float)i*0.017453293));

      step2[0] = (float)(pt2[0])/(float)(raddiv);
      step2[1] = (float)(pt2[1])/(float)(raddiv);
   
      colorstep[0] = (float)(color[0]-gray[0])/(float)(raddiv);      
      colorstep[1] = (float)(color[1]-gray[1])/(float)(raddiv);
      colorstep[2] = (float)(color[2]-gray[2])/(float)(raddiv);

      oldpt1[0] = (float)0.0;
      oldpt1[1] = (float)0.0;
      oldpt2[0] = (float)0.0;
      oldpt2[1] = (float)0.0;
      curfloat1[0] = (float)0.0+step1[0];
      curfloat1[1] = (float)0.0+step1[1];
      curfloat2[0] = (float)0.0+step2[0];
      curfloat2[1] = (float)0.0+step2[1];
      colorfloat[0] = (float)gray[0]+colorstep[0];      
      colorfloat[1] = (float)gray[1]+colorstep[1];
      colorfloat[2] = (float)gray[2]+colorstep[2];

      int temp = 1;
      temp = 1;

      for(j=0;j<raddiv;j++)
      {
        cur1[0] = curfloat1[0];
        cur1[1] = curfloat1[1];
        cur2[0] = curfloat2[0];
        cur2[1] = curfloat2[1];
        curfloat1[0] += step1[0];
        curfloat1[1] += step1[1];
        curfloat2[0] += step2[0];
        curfloat2[1] += step2[1];

        red = (int)colorfloat[0];
        green = (int)colorfloat[1];        
        blue = (int)colorfloat[2];

        colorfloat[0] += colorstep[0];
        colorfloat[1] += colorstep[1];
        colorfloat[2] += colorstep[2];

        polyx1[index] = (int)(oldpt1[0]*1000000.0); polyy1[index] = (int)(oldpt1[1]*1000000.0);
        polyx2[index] = (int)(cur1[0]*1000000.0); polyy2[index] = (int)(cur1[1]*1000000.0);
        polyx3[index] = (int)(cur2[0]*1000000.0); polyy3[index] = (int)(cur2[1]*1000000.0);
        polyx4[index] = (int)(oldpt2[0]*1000000.0); polyy4[index] = (int)(oldpt2[1]*1000000.0);

	if(red<0) red = 0; if(red>255) red = 255;
	if(green<0) green = 0; if(green>255) green = 255;
	if(blue<0) blue = 0; if(blue>255) blue = 255;

        polyr[index] = red*1000;
        polyg[index] = green*1000;
        polyb[index] = blue*1000;

        index++;

        oldpt1[0] = cur1[0];
        oldpt1[1] = cur1[1];
        oldpt2[0] = cur2[0];
        oldpt2[1] = cur2[1];
      }

      step1[0] = step2[0];
      step1[1] = step2[1];
      pt1[0] = pt2[0];
      pt1[1] = pt2[1];
    }
  }
  // ino.end

  // ino.method.update.3453.definition 
  public void update(Graphics g)
  // ino.end
  // ino.method.update.3453.body 
  {
    paint(g);
  }
  // ino.end

  // ino.method.paint.3456.definition 
  public void paint(Graphics g)
  // ino.end
  // ino.method.paint.3456.body 
  {
    int cx, cy;    
    int colorpack;
    int color[] = new int[3];
    int markx, marky;
    int rad, size;
    Graphics holdg;
	Rectangle boundrect = bounds();

    if(imgw!=bounds().width || imgh!=bounds().height)
    {
      mainImage=createImage(bounds().width, bounds().height);
      wheelImage=createImage(bounds().width, bounds().height);
      imgw = bounds().width;
      imgh = bounds().height;
      paintwheel(wheelImage.getGraphics());
      holdbright = brightness;
    }
    else if(holdbright!=brightness)    
    {
      paintwheel(wheelImage.getGraphics());
      holdbright = brightness;
    }

    holdg = g;

    if(mainImage!=null)
      g = mainImage.getGraphics();

    cx = boundrect.x + boundrect.width/2;
    cy = boundrect.y + boundrect.height/2;

    size = Math.min(boundrect.width, boundrect.height) - 4;
    rad = size/2;

    /*draw marker*/
    if(wheelImage!=null)
      g.drawImage(wheelImage, 0, 0, this);
    else
       paintwheel(g);

    markx = (int)((float)rad*ptsat*Math.cos(pthue*360.0*0.017453293)) + cx;    
    marky = (int)((float)rad*ptsat*Math.sin(pthue*360.0*0.017453293)) + cy;

    g.setColor(Color.white);
    g.drawOval(markx-3, marky-3, 6, 6);
    g.setColor(Color.black);
    g.drawOval(markx-4, marky-4, 8, 8);
    g.drawOval(markx-2, marky-2, 4, 4);

    /*draw swatch*/
    colorpack = Color.HSBtoRGB(pthue, ptsat, ((float)brightness/(float)100.0));
    color[0] = ((colorpack & 0x00ff0000) >> 16);
    color[1] = ((colorpack & 0x0000ff00) >> 8);
    color[2] = ((colorpack & 0x000000ff));
    
    if(mainImage!=null)
    {
      g = holdg;
      g.drawImage(mainImage, 0, 0, this);
    }
  }
  // ino.end

  // ino.method.paintwheel.3459.definition 
  public void paintwheel(Graphics g)
  // ino.end
  // ino.method.paintwheel.3459.body 
  {
    int i, cx, cy, size, rad;
    int red, green, blue;
    Rectangle boundrect = bounds();

    cx = boundrect.x + boundrect.width/2;    
    cy = boundrect.y + boundrect.height/2;

    size = Math.min(boundrect.width, boundrect.height) - 4;
    rad = size/2;

    for(i=0;i<(rstep+1)*(astep+1);i++)
    {
      Polygon p = new Polygon();

      p.addPoint(cx+(rad*polyx1[i])/1000000, cy+(rad*polyy1[i])/1000000);
      p.addPoint(cx+(rad*polyx2[i])/1000000, cy+(rad*polyy2[i])/1000000);
      p.addPoint(cx+(rad*polyx3[i])/1000000, cy+(rad*polyy3[i])/1000000);
      p.addPoint(cx+(rad*polyx4[i])/1000000, cy+(rad*polyy4[i])/1000000);

      red =   (polyr[i]*brightness)/100000;
      green = (polyg[i]*brightness)/100000;
      blue =  (polyb[i]*brightness)/100000;

      g.setColor(new Color(red, green, blue));      
      g.fillPolygon(p);
    }
  }
  // ino.end

  // ino.method.setbrightness.3462.definition 
  public void setbrightness(int b)
  // ino.end
  // ino.method.setbrightness.3462.body 
  {
    int colorpack;
    int color[] = new int[3];

    brightness = b; repaint();
    colorpack = Color.HSBtoRGB(pthue, ptsat, ((float)brightness/(float)100.0));
    color[0] = ((colorpack & 0x00ff0000) >> 16);
    color[1] = ((colorpack & 0x0000ff00) >> 8);
    color[2] = ((colorpack & 0x000000ff));

    sd.setcolor(curtarget, color[0], color[1], color[2]);
  }
  // ino.end

  // ino.method.settarget.3465.definition 
  public void settarget(int t)
  // ino.end
  // ino.method.settarget.3465.body 
  {
    curtarget = t;
    float hsbcolor[];    
    int color[] = new int[3];

    sd.getcolor(curtarget, color);
    hsbcolor = Color.RGBtoHSB(color[0], color[1], color[2], null);

    pthue = hsbcolor[0];
    ptsat = hsbcolor[1];
    brightness = (int)(hsbcolor[2]*100.0);

    brightbar.setValue(brightness);
    brightlabel.setText("Brightness " + brightness);

    repaint();
  }
  // ino.end

  // ino.method.mouseDown.3468.definition 
  public boolean mouseDown(Event evt,int x,int y)
  // ino.end
  // ino.method.mouseDown.3468.body 
  {
    pointplace(x, y);
    return true;
  }
  // ino.end

  // ino.method.mouseDrag.3471.definition 
  public boolean mouseDrag(Event evt,int x,int y)
  // ino.end
  // ino.method.mouseDrag.3471.body 
  {
    pointplace(x, y);    return true;
  }
  // ino.end

  // ino.method.pointplace.3474.definition 
  public void pointplace(int x,int y)
  // ino.end
  // ino.method.pointplace.3474.body 
  {
    int cx, cy;
    Rectangle boundrect = bounds();
    int size, rad;
    int dx, dy;
    int colorpack;
    int color[] = new int[3];

    cx = boundrect.x + boundrect.width/2;
    cy = boundrect.y + boundrect.height/2;

    size = Math.min(boundrect.width, boundrect.height) - 4;
    rad = size/2;

    dx = x-cx;
    dy = y-cy;

    ptsat = (float)((Math.sqrt((float)((dx)*(dx) + (dy)*(dy))))/((float)rad));
    if(ptsat<0.0) ptsat = (float)0.0;    if(ptsat>1.0) ptsat = (float)1.0;

    if(dx>=0 && dy>=0)
      pthue = (float)(Math.atan((float)(dy)/(float)(dx))*(180.0/Math.PI)/(360.0));
    else if(dx<0 && dy>=0)
      pthue = (float)(0.5-(Math.atan((float)(dy)/(float)(-dx))*(180.0/Math.PI)/(360.0)));
    else if(dx<0 && dy<0)
      pthue = (float)(0.5+(Math.atan((float)(dy)/(float)(dx))*(180.0/Math.PI)/(360.0)));
    else if(dx>=0 && dy<0)
      pthue = (float)(1.0-(Math.atan((float)(-dy)/(float)(dx))*(180.0/Math.PI)/(360.0)));    
    else
      pthue = (float)0.0;
      
    if(pthue<0.0) pthue = (float)0.0;
    if(pthue>1.0) pthue = (float)1.0;

    repaint();
 
    colorpack = Color.HSBtoRGB(pthue, ptsat, ((float)brightness/(float)100.0));
    color[0] = ((colorpack & 0x00ff0000) >> 16);
    color[1] = ((colorpack & 0x0000ff00) >> 8);
    color[2] = ((colorpack & 0x000000ff));

    sd.setcolor(curtarget, color[0], color[1], color[2]);
  }
  // ino.end
}
// ino.end
