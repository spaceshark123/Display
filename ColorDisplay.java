import java.util.*;
import java.lang.*;
import java.io.*;

public class ColorDisplay extends Display {
	protected int[][][] screen;

	protected final String ANSI_RESET = "\u001B[0m"; // Reset text color
	protected final String ANSI_RED = "\u001B[31m";   // Red text
	protected final String ANSI_GREEN = "\u001B[32m"; // Green text
	protected final String ANSI_BLUE = "\u001B[34m";  // Blue text

	public ColorDisplay(int width, int height) {
		screen = new int[height][width][3];
		for(int i = 0; i < screen.length; i++) {
			for(int j = 0; j < screen[0].length; j++) {
				screen[i][j][0] = 0;
				screen[i][j][1] = 0;
				screen[i][j][2] = 0;
			}
		}
		this.width = width;
		this.height = height;
	}

	public int[][][] getColorScreen() { return screen; }

	public void setPixelColor(int x, int y, int r, int g, int b) {
		screen[y][x][0] = r;
		screen[y][x][1] = g;
		screen[y][x][2] = b;
	}

	public int[] getPixel(int x, int y) {
		int[] color = new int[3];
		color[0] = screen[y][x][0];
		color[1] = screen[y][x][1];
		color[2] = screen[y][x][2];
		return color;
	}

	public String toString() {
		String print = "";
		for(int i = 0; i < screen.length; i++) {
			for(int j = 0; j < screen[0].length; j++) {
				int[] color = getPixel(j, i);
				String customColor = "\u001B[38;2;" + color[0] + ";" + color[1] + ";" + color[2] + "m";

        		// Print colored pixel
				print += customColor;
				print += "██";
				print += ANSI_RESET;
			}
			print += "\n";
		}
		return print;
	}
}