package OberoN;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

/**
 * Represente un cercle par une serie de points : d'abord son centre puis un ou plus points qui sont sur le cercle.
 * 
 * @author Maxime et Ambroise
 *
 */
public class Circle extends Figure {

	private static final long serialVersionUID = 1L;
	
	// points[0]:center, points[1+] e circle
	private LinkedList<CPoint> points;
	private Color c1;
	private Color c2;

	/**
	 * Construit un cercle a partir de son centre et d'un point appartenant a ce cercle.
	 * @param center Le centre du cercle.
	 * @param radius Un point sur le cercle.
	 */
	public Circle(CPoint center, CPoint radius) {

		this.points = new LinkedList<CPoint>();
		this.points.add(center);
		this.points.add(radius);

		this.enfants = new LinkedList<>();
		this.group = new LinkedList<>();

		c1 = new Color(0, 119, 219, 150); // inside color
		c2 = new Color(0, 68, 125); // border color
	}

	@Override
	public void draw(Graphics g, Repere r, Dimension d) {

		int radius = (int) (getRadius() * r.getEchelle());
		int coordX = r.conversionXreel(this.points.get(0).getX()) - radius;
		int coordY = r.conversionYreel(this.points.get(0).getY()) - radius;

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(c1);
		g2d.fillOval(coordX, coordY, radius * 2, radius * 2);
		g2d.setColor(c2);
		g2d.setStroke(new BasicStroke(2));
		g2d.drawOval(coordX, coordY, radius * 2, radius * 2);
		g2d.setStroke(new BasicStroke(1));
		for (CPoint p : points)
			p.draw(g, r, d);
	}

	@Override
	public void update() {
		super.miseAJourEnfants();
	}

	@Override
	public void translation(double depX, double depY) {
		for (CPoint p : points)
			p.translation(depX, depY);
	}

	@Override
	public void teleportation(double x, double y) {
		CPoint center = points.get(0);
		double depX = - (center.getX() - x);
		double depY = - (center.getY() - y);
		center.teleportation(x, y);
		for (CPoint p : points)
			p.translation(depX, depY);
	}

	@Override
	public boolean estDansSelection(double x, double y, Repere r) {
		double cx = points.getFirst().getX();
		double cy = points.getFirst().getY();
		double distance = Math.sqrt((r.conversionXreel(cx) - x)
				* (r.conversionXreel(cx) - x) + (r.conversionYreel(cy) - y)
				* (r.conversionYreel(cy) - y));
		// System.out.println("d="+distance);*/
		return distance < getRadius() * r.getEchelle();
	}

	@Override
	public void mettreCouleurSelection() {

	}

	@Override
	public void mettreCouleurNormale() {

	}

	/**
	 * Renvoie sous la forme d'un reel la valeur du rayon du cercle en valeur theorique.
	 * @return Le rayon.
	 */
	public double getRadius() {
		return Math.sqrt(Math.pow(points.get(0).getX() - points.get(1).getX(),
				2) + Math.pow(points.get(0).getY() - points.get(1).getY(), 2));
	}

	@Override
	public void translationLibre(double depX, double depY) {

		
	}

}
