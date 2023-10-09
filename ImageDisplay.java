import java.util.*;
import java.lang.*;
import java.io.*;
import java.io.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;

public class ImageDisplay {
	public Display display;
	public boolean isColor = false;
	protected BufferedImage img;

	public ImageDisplay(BufferedImage img, boolean isColor, int ASCIIwidth) {
		this.img = img;
		
		double aspectRatio = (double)img.getWidth() / img.getHeight();
		int ASCIIheight = (int)((1d/aspectRatio) * ASCIIwidth);
		
		//convert image to ASCII
		if(isColor) {
			display = new ColorDisplay(ASCIIwidth, ASCIIheight);
			this.isColor = true;
		} else {
			display = new Display(ASCIIwidth, ASCIIheight);
			this.isColor = false;
		}
		for(int i = 0; i < ASCIIheight; i++) {
			for(int j = 0; j < ASCIIwidth; j++) {
				//convert ASCII pixel pos to image pixel pos
				double normalizedX = (double)j / (ASCIIwidth-1);
				double normalizedY = (double)i / (ASCIIheight-1);
				int imgX = (int)(normalizedX * (img.getWidth()-1));
				int imgY = (int)(normalizedY * (img.getHeight()-1));
				
				//get pixel
				int pixel = img.getRGB(imgX, imgY);
				int alpha = img.getColorModel().hasAlpha() ? (pixel >> 24) & 0xFF : 255; // Alpha (transparency) value
				int red = (pixel >> 16) & 0xFF;   // Red color component
				int green = (pixel >> 8) & 0xFF;  // Green color component
				int blue = pixel & 0xFF;         // Blue color component

				if(isColor) {
					//set ASCII pixel to rgb
					((ColorDisplay)display).setPixelColor(j, i, red, green, blue);
				} else {
					//set ASCII pixel to brightness
					double brightness = (alpha / 255.0) * ((red * 0.2126 + green * 0.7152 + blue * 0.0722) / 255.0);
					display.setPixel(j, i, brightness);
				}
			}
		}
	}

	public String toString() {
		return display.toString();
	}
}