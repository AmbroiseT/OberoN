package OberoN;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * Barre d'outils contenant les differentes brosses.
 * 
 * @author Ambroise et Maxime
 *
 */
public class ToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
	DrawPanel panel = null;
	
	// Custom JButton
	class MyButton extends JButton {

		private static final long serialVersionUID = 1L;

		public MyButton(ImageIcon img) {
			setIcon(img);
			setContentAreaFilled(false);
			setFocusable(false);
		}

		@Override
		protected void paintComponent(Graphics g) {
			if (getModel().isPressed()) {
				g.setColor(new Color(255, 224, 104));
			} else if (getModel().isRollover()) {
				g.setColor(new Color(255, 216, 62));
			} else {
				g.setColor(new Color(250, 250, 250));
			}
			g.fillRect(0, 0, getWidth(), getHeight());
			super.paintComponent(g);
		}
	}

	public ToolBar(DrawPanel dp) {
		panel = dp;
		setOrientation(JToolBar.VERTICAL);
		setAlignmentX(FlowLayout.LEFT);

		JButton cursor = new MyButton(new ImageIcon(getClass().getClassLoader()
				.getResource("img/iconmonstr-cursor-4-icon-24.png")));
		cursor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setCursor(CCursor.normal);
				panel.style = 0;
			}
		});
		add(cursor, BorderLayout.EAST);
		
		JButton pen = new MyButton(new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-pen-icon-24.png")));
		pen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setCursor(CCursor.pen);
				panel.scene.clearSelection();
				panel.repaint();
				panel.style = 1;
			}
		});
		add(pen, BorderLayout.EAST);
		
	}

}
