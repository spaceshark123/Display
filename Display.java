import java.util.*;
import java.lang.*;
import java.io.*;

public class Display {
	protected char[][] screen;
	protected int width, height;
	protected final String pixels = ".'`^\",:;Il!i><~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$";

	public Display() {
		
	}
	
	public Display(int width, int height) {
		screen = new char[height][width];
		for(int i = 0; i < screen.length; i++) {
			for(int j = 0; j < screen[0].length; j++) {
				screen[i][j] = pixelOfBrightness(0);
			}
		}
		this.width = width;
		this.height = height;
	}

	public final int getWidth() { return width; }
	public final int getHeight() { return height; }
	public final char[][] getScreen() { return screen; }
	
	public char pixelOfBrightness(double brightness) {
		//brightness is a double between 0 and 1
		brightness = brightness > 1 ? 1 : brightness < 0 ? 0 : brightness;

		int index = (int)(brightness * (pixels.length() - 1));
		return pixels.charAt(index);
	}

	public void setPixel(int x, int y, double brightness) {
		screen[y][x] = pixelOfBrightness(brightness);
	}

	public double getBrightness(int x, int y) {
		char p = screen[y][x];
		int index = -1;
		for(int i = 0; i < pixels.length(); i++) {
			if(pixels.charAt(i) == p) {
				index = i;
				break;
			}
		}
		return (double)index / pixels.length();
	}

	public String toString() {
		String print = "";
		for(int i = 0; i < screen.length; i++) {
			for(int j = 0; j < screen[0].length; j++) {
				print += screen[i][j];
				print += screen[i][j];
			}
			print += "\n";
		}
		return print;
	}
}