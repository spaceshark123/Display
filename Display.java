import java.util.*;
import java.lang.*;
import java.io.*;

public class Display {
	protected double[][] screen;
	protected int width, height;
	protected final String pixels = ".'`^\",:;Il!i><~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$";

	public Display() {
		
	}
	
	public Display(int width, int height) {
		screen = new double[height][width];
		for(int i = 0; i < screen.length; i++) {
			for(int j = 0; j < screen[0].length; j++) {
				screen[i][j] = 0;
			}
		}
		this.width = width;
		this.height = height;
	}

	public final int getWidth() { return width; }
	public final int getHeight() { return height; }
	public final double[][] getScreen() { return screen; }
	
	public char pixelOfBrightness(double brightness) {
		//brightness is a double between 0 and 1
		brightness = brightness > 1 ? 1 : brightness < 0 ? 0 : brightness;

		int index = (int)(brightness * (pixels.length() - 1));
		return pixels.charAt(index);
	}

	public void setPixel(int x, int y, double brightness) {
		screen[y][x] = brightness;
	}

	public double getBrightness(int x, int y) {
		return screen[y][x];
	}

	public String toString() {
		String print = "";
		for(int i = 0; i < screen.length; i++) {
			for(int j = 0; j < screen[0].length; j++) {
				char pixel = pixelOfBrightness(screen[i][j]);
				print += pixel;
				print += pixel;
			}
			print += "\n";
		}
		return print;
	}

	public void setScreen(double[][] newScreen) {
		assert newScreen.length == screen.length;
		assert newScreen[0].length == screen[0].length;

		for(int i = 0; i < screen.length; i++) {
			for(int j = 0; j < screen[0].length; j++) {
				setPixel(i,j,newScreen[i][j]);
			}
		}
	}
}