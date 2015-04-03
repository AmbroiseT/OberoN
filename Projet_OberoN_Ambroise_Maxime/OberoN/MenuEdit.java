package OberoN;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.border.Border;


/**
 *  Le menu deroulant qui contient les fonctionnalites d'edition telles que undo et redo ou encore reset. Tout est gere via des ActionListener.
 * @author Maxime et Ambroise
 *
 */
public class MenuEdit extends JMenu {
	private static final long serialVersionUID = 1L;
	
	DrawPanel dp;
	
	/**
	 * Cree et initialise le MenuEdit de sorte a ce qu'il soit lie au DrawPanel en argument.
	 * @param dp Le DrawPanel.
	 */
	public MenuEdit(final DrawPanel dp) {
		setText("Edit");
		setBorder(BorderFactory.createCompoundBorder(this.getBorder(),BorderFactory.createEmptyBorder(4, 0,4, 0)));
		setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-pencil-10-icon-16.png")));
		this.dp = dp;

		Border border = BorderFactory.createCompoundBorder(this.getBorder(),BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		ImageIcon icon0 = new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-undo-2-icon-16.png"));
		
		JMenuItem undo = new JMenuItem("Undo", icon0);
		undo.setAccelerator(KeyStroke.getKeyStroke("control Z"));
		undo.setToolTipText("Undo one action");
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				dp.scene.undo();
				dp.repaint();
			}
		});
		undo.setBorder(border);
		
		ImageIcon icon1 = new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-redo-2-icon-16.png"));
		
		JMenuItem redo = new JMenuItem("Redo", icon1);
		redo.setAccelerator(KeyStroke.getKeyStroke("control Y"));
		redo.setToolTipText("Redo one action");
		redo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				dp.scene.redo();
				dp.repaint();
			}
		});
		redo.setBorder(border);
		/*
		ImageIcon icon2 = new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-layertop-icon-16.png"));
		
		JMenuItem moveUp = new JMenuItem("Move Up", icon2);
		moveUp.setAccelerator(KeyStroke.getKeyStroke("alt P"));
		moveUp.setToolTipText("Redo one action");
		moveUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, "Not supported yet", "Warning" , JOptionPane.WARNING_MESSAGE);
				dp.repaint();
			}
		});
		moveUp.setBorder(border);
		
		ImageIcon icon3 = new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-layerbot-icon-16.png"));
		
		JMenuItem moveDown = new JMenuItem("Move Down", icon3);
		moveDown.setAccelerator(KeyStroke.getKeyStroke("alt M"));
		moveDown.setToolTipText("Redo one action");
		moveDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, "Not supported yet", "Warning" , JOptionPane.WARNING_MESSAGE);
				dp.repaint();
			}
		});
		moveDown.setBorder(border);
		*/
		
		/*add(moveUp);
		add(moveDown);
		addSeparator();*/
		add(undo);
		add(redo);
	}
}
