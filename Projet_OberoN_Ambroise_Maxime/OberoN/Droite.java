package OberoN;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


/**
 * 
 * Cette classe represente une droite definie uniquement par son equation. 
 * C'est de cette classe dont vont heriter toutes les autres droites telles que DroiteDeuxPoints. 
 * C'est un choix logique car la definition la plus simple d'une droite est sont equation dans son repere.
 * 
 * @author Ambroise et Maxime
 * 
 *
 */
public class Droite extends Figure {
	
	private static final long serialVersionUID = 1L;
	
	protected Equation equation;
	protected Color couleur = new Color(0, 68, 125);

	/**
	 * Permet simplement de creer une droite a partir d'une Equation.
	 * @param e L'equation de la droite.
	 */
	public Droite(Equation e) {
		this.equation = e;
	}

	/**
	 * Construit une droite par defaut.
	 */
	public Droite() {
		this.equation = new Equation(0, 0, 0);
	}

	/**
	 * Cree une droite a partir de deux point. Attention, si on deplace les points en question, la droite restera la meme.
	 * Cette fonction renvoie NULL dans le cas ou les parametre seraient errones(deux point identiques, des donnees nulles...)
	 * @param p1 Premier point
	 * @param p2 Second point
	 * @return La nouvelle droite cree, ou NULL.
	 */
	public static Droite creerDroite(CPoint p1, CPoint p2) {
		Equation e = Equation.creeEquation(p1, p2);
		if (e == null) {
			return null;
		}
		return new Droite(e);
	}

	@Override
	public void draw(Graphics g, Repere r, Dimension d) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(204, 0, 51));
		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(couleur);
		CPoint[] tab = equation.calculPointsExtremes(d, r);
		g.drawLine(r.conversionXreel(tab[0].getX()),
				r.conversionYreel(tab[0].getY()),
				r.conversionXreel(tab[1].getX()),
				r.conversionYreel(tab[1].getY()));
		g2d.setStroke(new BasicStroke(1));
	}

	@Override
	public void update() {
		super.miseAJourEnfants();
	}

	/**
	 * Renvoie l'equation de la droite
	 * @return L'equation de la droite.
	 */
	public Equation getEquation() {
		return equation;
	}

	@Override
	public void translation(double depX, double depY) {
		CPoint[] points = equation.deuxPoints();
		points[0].translation(depX, depY);
		points[1].translation(depX, depY);
		equation = Equation.creeEquation(points[0], points[1]);
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
		couleur = Color.red;
	}

	@Override
	public void mettreCouleurNormale() {
		couleur = new Color(0, 68, 125);
	}

	@Override
	public void translationLibre(double depX, double depY) {
		
	}
}
