package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
 
public class Panneau extends JPanel
{
	public void paintComponent(Graphics g)
	{
		try
		{
	      Image img = ImageIO.read(new File("img/image.jpg"));
	      g.drawImage(img, 0, 0, this);
	      //Pour une image de fond
	      //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    }
		catch (IOException e)
		{
	      e.printStackTrace();
	    }                
	}
	
	public static void main(String[] args)
    {
		Panneau p = new Panneau();
		//p.paintComponent();
    }
}