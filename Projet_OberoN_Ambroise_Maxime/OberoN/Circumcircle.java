package OberoN;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Represente un cercle circonscrit a un triangle. C'est a dire un cercle qui passe par tous les points de ce triangle.
 * @author Maxime et Ambroise
 *
 */
public class Circumcircle extends Figure {
	
	private static final long serialVersionUID = 1L;
	
	private CPoint p1;
	private CPoint p2;
	private CPoint p3;
	
	/**
	 * Construit le cercle circonscrit au 3 points fournis en argument.
	 */
	public Circumcircle(CPoint p1,CPoint p2,CPoint p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}

	@Override
	public void draw(Graphics g, Repere r, Dimension dim) {
		// http://en.wikipedia.org/wiki/Circumscribed_circle
		
		CPoint a = p1;
		CPoint b = p2;
		CPoint c = p3;
		
		// on considere a comme le centre du repere
		
		double xb = b.getX() - a.getX();
		double yb = b.getY() - a.getY();
		double nb = xb * xb + yb * yb;
		
		double xc = c.getX() - a.getX();
		double yc = c.getY() - a.getY();
		double nc = xc * xc + yc * yc;
		
		// determinant du systeme lineaire
		double f = (xb * yc) - (xc * yb);
		
		// derterminant nul => pas de solution
		if (f == 0)
			return;
		else {
			// il existe une solution
			// formule de cramer
			// http://fr.wikipedia.org/wiki/R%C3%A8gle_de_Cramer
			double x0 = (nb * yc - nc * yb) / (2 * f);
			double y0 = (xb * nc - xc * nb) / (2 * f);
			
			int radius = (int) (Math.sqrt((x0 * x0 + y0 * y0)) * r.getEchelle());
			
			// a etait le centre du repere, il y a un decalage de (xa, ya)
			x0 += a.getX();
			y0 += a.getY();
			
			int coordX = (int) (r.conversionXreel(x0) - radius);
			int coordY = (int) (r.conversionYreel(y0) - radius);
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(new Color(204, 0, 51));
			g2d.setStroke(new BasicStroke(2));
			g2d.drawOval(coordX - 1, coordY - 1, radius * 2, radius * 2);
			g2d.setStroke(new BasicStroke(1));
		}
	}

	@Override
	public void update() {
		
	}

	@Override
	public void translation(double depX, double depY) {
		
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
