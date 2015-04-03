package OberoN;



import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

/**
 * Menu de vue, qui contient des fonctions telles que zoom et dezoomou encore reset
 * @author Maxime et Ambroise
 *
 */
public class MenuView extends JMenu {
	
	private static final long serialVersionUID = 1L;
	
	public DrawPanel dp;
	
	public MenuView(final DrawPanel dp) {
		this.dp = dp;
		setText("View");
		setBorder(BorderFactory.createCompoundBorder(this.getBorder(),BorderFactory.createEmptyBorder(4, 0,4, 0)));
		setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-eye-6-icon-16.png")));
		
		Border border = BorderFactory.createCompoundBorder(this.getBorder(),BorderFactory.createEmptyBorder(0, 0, 0, 0));

		ImageIcon icon0 = new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-magnifier-6-icon-16.png"));

		JMenuItem zoomIn = new JMenuItem("Zoom+", icon0);
		zoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ADD,
				ActionEvent.CTRL_MASK));
		zoomIn.setToolTipText("Zoom In");
		zoomIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Dimension d = dp.getSize();
				dp.getScene().zoomerRepere(d.width/2, d.height/2);
				dp.repaint();
			}
		});
		zoomIn.setBorder(border);
		
		ImageIcon icon1 = new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-magnifier-7-icon-16.png"));

		JMenuItem zoomOut = new JMenuItem("Zoom--", icon1);
		zoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT,
				ActionEvent.CTRL_MASK));
		zoomOut.setToolTipText("Zoom Out");
		zoomOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				Dimension d = dp.getSize();
				dp.getScene().dezoomerRepere(d.width/2, d.height/2);
				dp.repaint();
			}
		});
		zoomOut.setBorder(border);
		
		ImageIcon icon2 = new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-fullscreen-10-icon-16.png"));

		JMenuItem resetView = new JMenuItem("Reset", icon2);
		resetView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0,
				ActionEvent.CTRL_MASK));
		resetView.setToolTipText("Reset zoom and center the frame");
		resetView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				dp.getScene().iniRepere(dp);
				dp.repaint();
			}
		});
		resetView.setBorder(border);
		
		ImageIcon icon3 = new ImageIcon(getClass().getClassLoader().getResource("img/icon-grid-icon-16.png"));

		JMenuItem grid = new JMenuItem("Grid", icon3);
		grid.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD5,
				ActionEvent.CTRL_MASK));
		grid.setToolTipText("Enable or Disable the grid");
		grid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				dp.showGrid = !dp.showGrid;
				dp.repaint();
			}
		});
		grid.setBorder(border);

		add(resetView);
		addSeparator();
		add(zoomIn);
		add(zoomOut);
		addSeparator();
		add(grid);
	}
}
