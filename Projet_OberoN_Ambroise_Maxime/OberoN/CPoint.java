package OberoN;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Cette classe represente un point, c'est la figure la plus basique qui soit.
 * Elle est particulierement importante du fait que tout le systeme de selection se base sur des points et non des figures plus complexes.
 * 
 * @author Ambroise et Maxime
 *
 */
public class CPoint extends Figure {
	
	private static final long serialVersionUID = 1L;
	
	// theoretical position
	protected double x, y;// Coordonees theoriques!
	protected Color contenu;
	protected Color bordure;
	// pixel size of a point
	private static final int size = 10;

	protected CPoint() {
		bordure = new Color(0, 0, 0, 225);
		contenu = new Color(1, 9, 19);
	}

	/**
	 * Construit un point aux coordonnees theoriques indiquees
	 * @param x Coordonnee horizontale
	 * @param y Coordonnee veryicale
	 */
	public CPoint(double x, double y) {
		this.x = x;
		this.y = y;
		bordure = new Color(0, 0, 0, 225);
		contenu = new Color(1, 9, 19);
	}

	/**
	 * Permet de comparer un point a un autre afin de savoir si leurs coordonnes sont egales.
	 * @param p Le point a comparer avec l'objet courant.
	 * @return Un booleen.
	 */
	public boolean equals(CPoint p) {
		return p.x == x && p.y == y;
	}

	/**
	 * 
	 * @return La coordonnee horizontale theorique
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return La coordonnee verticale theorique
	 */
	public double getY() {
		return y;
	}

	@Override
	public String toString() {
		return getClass() + " CPoint : x=" + x + " y=" + y;
	}

	/**
	 * Permet de recuperer la couleur du contenu de ce point.
	 * @return La couleur du contenu du point.
	 */
	public Color getContenu() {
		return contenu;
	}

	/**
	 * Permet de recuperer la couleur de la bordure de ce point.
	 * @return La couleur de la bordure du point.
	 */
	public Color getBordure() {
		return bordure;
	}

	@Override
	public void draw(Graphics g, Repere r, Dimension d) {
		g.setColor(contenu);
		int coordX = r.conversionXreel(x);
		int coordY = r.conversionYreel(y);

		g.fillOval(coordX - size / 2, coordY - size / 2, size, size);
		g.setColor(bordure);
		g.drawOval(coordX - size / 2, coordY - size / 2, size, size);
	}

	@Override
	public void update() {
		super.miseAJourEnfants();
	}

	@Override
	public void translation(double depX, double depY) {
		// theoretical position
		
		x = x + depX;
		y = y + depY;
		
		for(Figure f : group){
			f.translationLibre(depX, depY);
		}

		update();
	}
	
	
	public void translationLibre(double depX, double depY){
		x = x + depX;
		y = y + depY;
		
		update();
	}

	@Override
	public void teleportation(double x, double y) {
		// theoretical position

		this.x = x;
		this.y = y;
	}

	@Override
	public boolean estDansSelection(double x, double y, Repere r) {
		// x, y : @ real numbers
		double distance = Math.sqrt((r.conversionXreel(this.x) - x)
				* (r.conversionXreel(this.x) - x)
				+ (r.conversionYreel(this.y) - y)
				* (r.conversionYreel(this.y) - y));
		return distance < 10;
	}
	
	/**
	 * Change la couleur de la figure pour la mettre de la couleur de selection.
	 */
	@Override
	public void mettreCouleurSelection() {
		bordure = new Color(254, 204, 9);
		contenu = new Color(254, 204, 9);
	}
	/**
	 * Change la couleur de la figure pour la mettre de la couleur normale.
	 */
	public void mettreCouleurNormale() {
		bordure = new Color(0, 0, 0, 225);
		contenu = new Color(1, 9, 19);
	}
}
