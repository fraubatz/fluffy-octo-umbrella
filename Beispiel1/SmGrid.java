import java.awt.*;


// ino.class.SmGrid.964.description type=line
// Die Klasse SmGrid erzeugt ein editierbares Raster mit ein -und ausschaltbaren   
// Fangbereich.
// ino.end
// ino.class.SmGrid.964.declaration 
public class SmGrid extends Component
// ino.end
// ino.class.SmGrid.964.body
{
	// ino.attribute._xDistance.1720.description type=line
	// Dieses Attribut beschreibt den Rasterabstand in X-Richtung.
	// ino.end
	// ino.attribute._xDistance.1720.declaration 
	private int _xDistance;
	// ino.end

	// ino.attribute._yDistance.1723.description type=line
	// Dieses Attribut beschreibt den Rasterabstand in Y-Richtung.
	// ino.end
	// ino.attribute._yDistance.1723.declaration 
	private int _yDistance;
	// ino.end

	// ino.attribute._zeroX.1726.description type=line
	// Dieses Attribut hält die X-Koordinate des Rasternullpunkts.
	// ino.end
	// ino.attribute._zeroX.1726.declaration 
	private int _zeroX;
	// ino.end
	
	// ino.attribute._zeroY.1729.description type=line
	// Dieses Attribut hält die Y-Koordinate des Rasternullpunkts.
	// ino.end
	// ino.attribute._zeroY.1729.declaration 
	private int _zeroY;
	// ino.end

	// ino.attribute._isVisible.1732.description type=line
	// Dieses Attribut bestimmt die Sichtbarkeit des Rasters. Sichtbar = TRUE.
	// ino.end
	// ino.attribute._isVisible.1732.declaration 
	private boolean _isVisible;
	// ino.end
	
	// ino.attribute._isCatched.1735.description type=line
	// Dieses Attribut bestimmt den Fangmodus des Rasters. Fangen ein = TRUE.
	// ino.end
	// ino.attribute._isCatched.1735.declaration 
	private boolean _isCatched;
	// ino.end

	// ino.attribute._gridWidth.4004.description type=line
	// Dieses Attribut hält die Rasterbreite.
	// ino.end
	// ino.attribute._gridWidth.4004.declaration 
	private int _gridWidth;
	// ino.end

	// ino.attribute._gridHeight.4007.description type=line
	// Dieses Attribut hält die Rasterhöhe.
	// ino.end
	// ino.attribute._gridHeight.4007.declaration 
	private int _gridHeight;
	// ino.end

	// ino.method.SmGrid.1207.description type=line
	// Der Standardkonstruktor setzt die Rasterattribute auf die Defaultwerte.
	// ino.end
	// ino.method.SmGrid.1207.definition 
	public SmGrid()
	// ino.end
	// ino.method.SmGrid.1207.body 
	{
		_xDistance  = 10;
		_yDistance  = 10;
		_zeroX      =  0;
		_zeroY		=  0;
		_isVisible  = false;
		_isCatched  = false;
		_gridWidth  = 800;
		_gridHeight = 600;
	}
	// ino.end

	// ino.method.SmGrid.1738.description type=line
	// Der Parameterkonstruktor setzt die Rasterattribute auf die ihm übergebenen      
	// Werte.
	// ino.end
	// ino.method.SmGrid.1738.definition 
	public SmGrid(int xDistance,int yDistance,int zeroX,int zeroY,boolean isVisible,boolean isCatched)
	// ino.end
	// ino.method.SmGrid.1738.body 
	{
		_xDistance  = xDistance;
		_yDistance  = yDistance;
		_zeroX      = zeroX;
		_zeroY		= zeroY;
		_isVisible  = isVisible;
		_isCatched  = isCatched;
	}
	// ino.end
	
	// ino.method.setXDistance.1741.description type=line
	// Diese Funktion setzt den Rasterabstand in X-Richtung.
	// ino.end
	// ino.method.setXDistance.1741.definition 
	public void setXDistance(int x)
	// ino.end
	// ino.method.setXDistance.1741.body 
	{
		_xDistance = x;
	}
	// ino.end

	// ino.method.setYDistance.1744.description type=line
	// Diese Funktion setzt den Rasterabstand in Y-Richtung.
	// ino.end
	// ino.method.setYDistance.1744.definition 
	public void setYDistance(int y)
	// ino.end
	// ino.method.setYDistance.1744.body 
	{
		_yDistance = y;
	}
	// ino.end

	// ino.method.setXZeroPoint.1747.description type=line
	// Diese Funktion setzt die X-Koordinate des Rasternullpunkts.
	// ino.end
	// ino.method.setXZeroPoint.1747.definition 
	public void setXZeroPoint(int zeroX)
	// ino.end
	// ino.method.setXZeroPoint.1747.body 
	{
		_zeroX = zeroX;
	}
	// ino.end
	
	// ino.method.setYZeroPoint.1750.description type=line
	// Diese Funktion setzt die Y-Koordinate des Rasternullpunkts.
	// ino.end
	// ino.method.setYZeroPoint.1750.definition 
	public void setYZeroPoint(int zeroY)
	// ino.end
	// ino.method.setYZeroPoint.1750.body 
	{
		_zeroY = zeroY;
	}
	// ino.end

	// ino.method.getXDistance.1753.description type=line
	// Diese Funktion liefert den Rasterabstand in X-Richtung zurück.
	// ino.end
	// ino.method.getXDistance.1753.definition 
	public int getXDistance()
	// ino.end
	// ino.method.getXDistance.1753.body 
	{
		return _xDistance;
	}
	// ino.end
    
	// ino.method.getYDistance.1756.description type=line
	// Diese Funktion liefert den Rasterabstand in Y-Richtung zurück.
	// ino.end
	// ino.method.getYDistance.1756.definition 
	public int getYDistance()
	// ino.end
	// ino.method.getYDistance.1756.body 
	{
		return _yDistance;
	}
	// ino.end
	
	// ino.method.setGridWidth.4010.description type=line
	// Diese Funktion setzt die Rasterbreite.
	// ino.end
	// ino.method.setGridWidth.4010.definition 
	public void setGridWidth(int gridWidth)
	// ino.end
	// ino.method.setGridWidth.4010.body 
	{
		_gridWidth = gridWidth;
	}
	// ino.end

	// ino.method.setGridHeight.4013.description type=line
	// Diese Funktion setzt die Rasterhöhe.
	// ino.end
	// ino.method.setGridHeight.4013.definition 
	public void setGridHeight(int gridHeight)
	// ino.end
	// ino.method.setGridHeight.4013.body 
	{
		_gridHeight = gridHeight;
	}
	// ino.end

	// ino.method.setVisibility.1759.description type=line
	// Diese Funktion setzt den Sichtbarkeitsstatus des Rasters.
	// ino.end
	// ino.method.setVisibility.1759.definition 
	public void setVisibility(boolean isVisible)
	// ino.end
	// ino.method.setVisibility.1759.body 
	{
		_isVisible = isVisible;
	}
	// ino.end
	
	// ino.method.getVisibility.1762.description type=line
	// Diese Funktion liefert den Sichtbarkeitsstatus zurück.
	// ino.end
	// ino.method.getVisibility.1762.definition 
	public boolean getVisibility()
	// ino.end
	// ino.method.getVisibility.1762.body 
	{
		return _isVisible;
	}
	// ino.end

	// ino.method.setCatchMode.1765.description type=line
	// Diese Funktion setzt den Fangmodus des Rasters.
	// ino.end
	// ino.method.setCatchMode.1765.definition 
	public void setCatchMode(boolean isCatched)
	// ino.end
	// ino.method.setCatchMode.1765.body 
	{
		_isCatched = isCatched;
	}
	// ino.end
	
	// ino.method.getCatchMode.1768.description type=line
	// Diese Funktion liefert den Fangmodus des Rasters zurück.
	// ino.end
	// ino.method.getCatchMode.1768.definition 
	public boolean getCatchMode()
	// ino.end
	// ino.method.getCatchMode.1768.body 
	{
		return _isCatched;
	}
	// ino.end

	// ino.method.paint.1204.description type=line
	// Diese Funktion zeichnet das Raster.
	// ino.end
	// ino.method.paint.1204.definition 
	public void paint(Graphics g)
	// ino.end
	// ino.method.paint.1204.body 
	{
		for (int i=1;i<= (int) _gridWidth/_xDistance;i++)
		{
			for (int j=1;j<= (int) _gridHeight/_yDistance;j++)
			{
				g.drawLine(_yDistance*j, _xDistance*i, _yDistance*j, _xDistance*i);
			}	
		}
	}
	// ino.end
}
// ino.end





