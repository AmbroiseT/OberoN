package OberoN;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

/**
 * C'est dans cet objet que l'utilisateur dessine. Il gere egalement les evenements generes par ce dernier. Il contient, entree autres, l'objet Scene.
 * @author Maxime et Ambroise
 *
 */
public class DrawPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	/*
	 * LinkedList<Figure> figures = new LinkedList<Figure>(); Repere repere =
	 * new Repere(25); double puissanceZoom = 0.9; Stack<Figure> selection=new
	 * Stack<Figure>(); int coordX, coordY;
	 */

	Scene scene = new Scene();
	
	boolean showGrid = true;
	
	// ACTIONS
	boolean aToCenter = true;

	// BRUSHES
	int style = 0;

	/**
	 * Construit et initialise un DrawPanel et son contenu. Il met egalement en place divers EventListeners afin de gerer le evenement de l'utilisateur.
	 */
	public DrawPanel() {
		setFocusable(true);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// ACTION - CLICKED
		addMouseListener(new MouseInputAdapter() {
			public void mousePressed(MouseEvent e){
				if(SwingUtilities.isLeftMouseButton(e)){
					if(style==0){
						scene.remember();
					}
				}
			}
			public void mouseClicked(MouseEvent e) {
				requestFocus();
				if (SwingUtilities.isRightMouseButton(e)) {
					if (style == 0) {
						if (e.isControlDown()) {
							scene.clicGaucheSourisCtrlSelection(e.getX(), e.getY());
						} else {
							scene.clicGaucheSourisSelection(e.getX(), e.getY());
						}
					}
				}
				if (SwingUtilities.isLeftMouseButton(e)) {
					if (style == 1) {
						scene.add(new CPoint(scene.repere.conversionXtheorique(e.getX()+12),
													 scene.repere.conversionYtheorique(e.getY()+12)));
					}
				}
				repaint();
			}
		});

		// MOVE (using the mouse)
		addMouseMotionListener(new MouseMotionListener() {

			// UPDATE MOUSE POSITION
			public void mouseMoved(MouseEvent e) {
				scene.deplacementSouris(e.getX(), e.getY());
			}

			public void mouseDragged(MouseEvent e) {
				// MOVE THE FRAME
				if (SwingUtilities.isMiddleMouseButton(e)) {
					scene.dragRepere(e.getX(), e.getY());
				}
				if (SwingUtilities.isLeftMouseButton(e) && !scene.selection.isEmpty()) {
					//scene.remember();
					scene.deplacerSelection(e.getX(), e.getY());
				}
				repaint();
			}
		});

		// ZOOM
		addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				int rot = e.getWheelRotation();
				if (rot == 1) {
					scene.dezoomerRepere(e.getX(), e.getY());
				} else if (rot == -1) {
					scene.zoomerRepere(e.getX(), e.getY());
				}
				repaint();
			}
		});

		// MOVE (using arrow keys)
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent k) {
				int decalage = 10;
				int code = k.getKeyCode();
				int x = scene.repere.getX0();
				int y = scene.repere.getY0();
				switch (code) {
				case KeyEvent.VK_LEFT:
					scene.moveRepere(decalage + x, y);
					break;
				case KeyEvent.VK_RIGHT:
					scene.moveRepere(-decalage + x, y);
					break;
				case KeyEvent.VK_UP:
					scene.moveRepere(x, decalage + y);
					break;
				case KeyEvent.VK_DOWN:
					scene.moveRepere(x, -decalage + y);
					break;
				}
				repaint();
			}

			@Override
			public void keyReleased(KeyEvent k) {
				
			}

			@Override
			public void keyTyped(KeyEvent k) {
				
			}
		});
	}

	/**
	 * Permet de recuperer la Scene courante de cet objet.
	 * @return La scene.
	 */
	public Scene getScene() {
		return scene;
	}

	@Override
	public void paintComponent(Graphics g1) {

		// requestFocus(); permet de reselectionner l'elt
		// ACTIONS?
		if (aToCenter) {
			Dimension size = getSize();
			// ne pas utiliser centrer(Graphics) mais centrer(JPanel)
			// repere.centrer(this);
			scene.moveRepere((int) (size.getWidth() / 2),
					(int) (size.getHeight() / 2));
			aToCenter = false; // done!
		}

		Graphics2D g = (Graphics2D) g1;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Dimension d = this.getSize();
		scene.draw(g, d, showGrid);

	}
}
