package OberoN;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Cette classe represente un polygone, c'est a dire une figure fermee composee d'un nombre fini de points.
 * De cette classe heriteront les classes telles que Triangle ou encore Carre.
 * 
 * @author Maxime et Ambroise
 *
 */
public class Polygon extends Figure {

	private static final long serialVersionUID = 1L;

	protected CPoint[] points;

	/**
	 * Construit un Polygone dont les sommets sont contenus dans le tableau fourni en argument.
	 * @param p
	 */
	public Polygon(CPoint[] p) {
		this.points = p;
	}

	@Override
	public void draw(Graphics g, Repere r, Dimension d) {
		Graphics2D g2d = (Graphics2D) g;
		int[] px = new int[this.points.length + 1];
		int[] py = new int[this.points.length + 1];
		for (int i = 0; i < px.length; i++) {
			px[i] = r.conversionXreel(points[i % this.points.length].getX());
			py[i] = r.conversionYreel(points[i % this.points.length].getY());
		}
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(new Color(0, 119, 219, 150));
		g2d.fillPolygon(px, py, px.length);
		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(new Color(0, 68, 125));
		g2d.drawPolygon(px, py, px.length);
		g2d.setStroke(new BasicStroke(1));
	}
	
	/**
	 * Cette methode permet d'ajouter un sommet a la figure sous la forme d'un point.
	 * @param p Le point que l'on veut ajouter a l'objet courant.
	 */
	public void add(CPoint p) {
		CPoint[] t = new CPoint[points.length+1];
		for (int i = 0; i < points.length; i++)
			t[i] = points[i];
		t[t.length-1] = p;
		points = t;
	}

	@Override
	public void update() {
		super.miseAJourEnfants();
	}

	@Override
	public void translation(double depX, double depY) {
		for (CPoint p:points)
			p.translation(depX, depY);
	}

	@Override
	public void teleportation(double x, double y) {
	}

	@Override
	public boolean estDansSelection(double x, double y, Repere r) {
		return false;
	}

	@Override
	public void mettreCouleurSelection() {

	}

	@Override
	public void mettreCouleurNormale() {

	}

	@Override
	public void translationLibre(double depX, double depY) {
		
	}

}
