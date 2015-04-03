package OberoN;


import java.awt.Color;

import javax.swing.JMenuBar;


/**
 * Barre de menu contenant le MenuFile, le MenuEdit, MenuView, et le MenuDraw.
 * @author Maxime et Ambroise
 *
 */
public class MenuBar extends JMenuBar {
	
	private static final long serialVersionUID = 1L;
	
	public DrawPanel dp;

	/**
	 * Initialise la barre de menu et lie ses composants au DrawPanel en argument
	 * @param dp Le DrawPanel.
	 */
	public MenuBar(DrawPanel dp) {
		this.dp = dp;
		
		setBackground(new Color(255,255,255));
		
		// ADD MENUS
		add(new MenuFile(dp));
		add(new MenuEdit(dp));
		add(new MenuView(dp));
		add(new MenuDraw(dp));
	}

}