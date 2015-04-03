package OberoN;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;


/**
 * Represente le curseur de la souris. Contient les icones pour les divers styles possibles du curseur.
 * @author Maxime et Ambroise
 *
 */
public class CCursor {
	
	public static java.awt.Cursor normal;
	public static java.awt.Cursor pen;
	
	/**
	 * Cree une instance de CCursor et charge les images pour les differents types de curseur en memoire.
	 */
	public CCursor() {
		
		//Get the default toolkit  
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		  
		//Load an image for the cursor  
		Image image0 = new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-cursor-4-icon-16.gif")).getImage();
		//Create the hotspot for the cursor  
		Point hotSpot = new Point(0,0);
		//Create the custom cursor  
		normal = toolkit.createCustomCursor(image0, hotSpot, "transparentCursor");
		
		Image image1 = new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-cursor-5-icon-16.gif")).getImage();
		pen = toolkit.createCustomCursor(image1, hotSpot, "transparentCursor");
	}
	
}
