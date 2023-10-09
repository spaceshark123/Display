import java.util.*;
import java.lang.*;
import java.io.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class Main {
   public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);

	   //get input file as BufferedImage
	   	BufferedImage img = null;
		try {
			out("path to image: ");
			File imageFile = new File(scan.nextLine());
		    img = ImageIO.read(imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	   	if(img == null) {
			out("img is still null.");
		}

	   out("character width (2x): ");
	   int ASCIIwidth = Math.max(0,scan.nextInt());
	   out("color display? (y/n): ");
	   boolean isColor = scan.next().equals("y");
	   ImageDisplay imageDisplay = new ImageDisplay(img, isColor, ASCIIwidth);

	   //display ASCII image
	   String display = imageDisplay.toString();
	   out(display);

	   out("do you want to copy to clipboard (y/n): ");
	   boolean doCopy = scan.next().equals("y");
	   if(doCopy) {
		   StringSelection stringSelectionObj = new StringSelection(display);
			Clipboard clipboardObj = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboardObj.setContents(stringSelectionObj, null);
	   }

	   	scan.close();
   }

	public static void clear() {
		System.out.println(new String(new char[50]).replace("\0", "\r\n"));
	}

	public static void sleep(int ms) {
		try {
		    Thread.sleep(ms);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
	}

	public static void out(Object msg) {
		System.out.println(msg);
	}

	public static String[] in(Scanner scan) {
		return scan.nextLine().split(" ");
	}
}