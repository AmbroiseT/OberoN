package OberoN;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.UIManager;


/**
 * Custom Theme
 * @author Maxime et Ambroise
 */
public class CTheme {
	
	public CTheme() {
		
		Color selection = new Color(255, 216, 62);
		Color gray = new Color(250, 250, 250);
		Color text = new Color(0, 0, 0);
		Color background = new Color(202,202,202);
		//Color test = new Color(255, 0, 0);
		
		// CUSTOM UI
		UIManager.put("TabbedPane.selected", selection);
		UIManager.put("MenuItem.selectionBackground", selection);
		UIManager.put("MenuItem.selectionForeground", text);
		UIManager.put("MenuItem.background", gray);
		UIManager.put("MenuItem.foreground", text);
		UIManager.put("MenuItem.border", BorderFactory.createLineBorder(gray, 0));
		UIManager.put("Menu.border", BorderFactory.createLineBorder(new Color(0,0,0), 0));
		UIManager.put("Menu.selectionBackground", selection);
		UIManager.put("Menu.selectionForeground", text);
		UIManager.put("MenuBar.selectionBackground", selection);
		UIManager.put("MenuBar.selectionForeground", text);
		UIManager.put("Separator.background", background);
		UIManager.put("Separator.foreground", background);
		UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(background, 3));
		UIManager.put("PopupMenu.background", background);
		UIManager.put("PopupMenu.foreground", background);
		UIManager.put("ToolBar.background", gray);
		UIManager.put("ToolBar.foreground", gray);
		UIManager.put("OptionPane.background", gray);
		//UIManager.put("OptionPane.foreground", test);
		UIManager.put("Panel.background", gray);
		//UIManager.put("Panel.foreground", gray);
		UIManager.put("Button.background", gray);
		UIManager.put("Button.select", selection);
	}
}
