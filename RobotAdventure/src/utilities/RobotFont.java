package utilities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 
 * @author Jonathan
 *
 */

public class RobotFont {

	//Character string must have exact characters as font.png file even include the spaces
	private static String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ   " +
									   "0123456789.,!?\"'<>#/{}[]:;   ";
	private static int fontWidth, fontHeight;
	private static BufferedImage[][] fontMap;
	
	private RobotFont(){}
	
	public static int getFontWidth(String text)
	{
		return (text.length() * fontWidth );
	}
	
	public static int getHalfFontWidth(String text)
	{
		return getFontWidth(text)/2;
	}
	
	public static void drawString(String text, Graphics g, int x, int y)
	{
		drawString(text, g, x, y, 1, 1);
	}
	
	public static void drawString(String text, Graphics g, int x, int y, int xScale, int yScale)
	{
		text = text.toUpperCase();
		int length = text.length();
		for(int i = 0; i < length; i++)
		{
			char c = text.charAt(i);
			int index = characters.indexOf(c);
			if(index < 0) continue;
			
			g.drawImage(fontMap[index / 29][index % 29],x,y,xScale*fontWidth,yScale*fontHeight,null);
			x+=(fontWidth * xScale);
		}
	}
	
	public static void drawStringOverlapped(String text, Graphics g, int x, int y, int xScale, int yScale)
	{
		text = text.toUpperCase();
		int length = text.length();
		for(int i = 0; i < length; i++)
		{
			char c = text.charAt(i);
			int index = characters.indexOf(c);
			if(index < 0) continue;
			
			g.drawImage(fontMap[index / 29][index % 29],x,y,xScale*fontWidth,yScale*fontHeight,null);
			x+=(fontWidth * xScale) - (xScale * 2);
		}
	}
	
	public static void drawStringOverlapped(String text, Graphics g, int x, int y)
	{
		drawStringOverlapped(text, g, x, y, 1, 1);
	}
	
	public static void drawStringReversed(String text, Graphics g, int x, int y)
	{
		drawStringReversed(text, g, x, y, 1, 1);
	}
	
	public static void drawStringReversed(String text, Graphics g, int x, int y, int xScale, int yScale)
	{
		text = new StringBuffer(text).reverse().toString();
		drawString(text, g, x, y,xScale,yScale);
	}
	
	
	
	static{
		fontWidth = fontHeight = 16;
		fontMap = Utility.loadFont("font/font.png", 16, 16);
	}
}
