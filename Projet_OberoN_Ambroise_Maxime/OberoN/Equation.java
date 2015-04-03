package OberoN;


import java.awt.Dimension;
import java.io.Serializable;

/**
 * Cette classe est principalement utilisee par la classe Droite. 
 * Elle definit l'equation d'une doite sous la forme ax+by+c=0.
 * @author Ambroise et Maxime
 */

public class Equation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// a * x + b * y + c = 0
	private double a, b, c;

	/**
	 * Ce constructeur cree une droite d'equation de la forme a*x+b*y+c=0, ce
	 * qui englobe toutes les droites, meme verticales.
	 * 
	 * @param a
	 *            Coefficient des abscisses
	 * @param b
	 *            Coefficient de ordonees
	 * @param c
	 *            Ordonnee a l'origine
	 */
	public Equation(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public String toString() {
		return a + "x+" + b + "y+" + c;
	}

	/**
	 * Permet de creer une equation a partir de deux points par laquelle elle
	 * passe, renvoie null si une des donnees est nulle ou incorrecte.
	 * 
	 * @param p1
	 *            Premier point.
	 * @param p2
	 *            Second point.
	 * @return Equation d'une droite passant par ces deux points.
	 */
	public static Equation creeEquation(CPoint p1, CPoint p2) {
		if (p1 == null || p2 == null || p1.equals(p2)) {
			//System.out.println("Souci");
			return null;
		}
		if (Math.abs(p1.getX() - p2.getX())<0.00001) { // Cas ou la droite est verticale
			Equation e=new Equation(1, 0, -p1.getX());
			//System.out.println(e);
			return e;
		} else { // Droite normale
			double a = (p1.getY() - p2.getY()) / (p2.getX() - p1.getX());
			Equation e=new Equation(a, 1, (-p1.getY() - a * p1.getX()));
			//System.out.println(e);
			
			return e;
		}
	}

	/**
	 * Cette methode renvoie deux points se trouvant sur la droite decrite par
	 * l'equation (simplifie les calculs)
	 * 
	 * @param x
	 * @return tableau de deux points.
	 */
	public CPoint[] deuxPoints() {
		CPoint[] points = new CPoint[2];
		if (a == 0) {// Si la droite est verticale
			points[0] = new CPoint(a / c, 0);
			points[1] = new CPoint(a / c, 1);
		} else {
			points[0] = new CPoint(0, f(0));
			points[1] = new CPoint(1, f(1));
		}
		return points;
	}

	/**
	 * Cette fonction renvoie, s'il existe, le point d'intersection des deux droites d'equations fournies en parametre
	 * @param e1 L'equation de la premiere droite
	 * @param e2 L'equation de la seconde droite
	 * @return Un CPoint situe a l'intersection des droites ou NULL si les droites sont paralleles.
	 */
	public static CPoint intersection(Equation e1, Equation e2) {
		if (e1.b == 0) {
			if (e2.b == 0) {
				return null;
			}
			double x = -e1.c / e1.a;
			double y = -(e2.a * x + e2.c) / e2.b;
			//System.out.println(e1 + "\n" + e2);
			//System.out.println("x=" + x + " y=" + y);
			return new CPoint(x, y);
		} else if (e2.b == 0) {
			if (e1.b == 0) {
				return null;
			}
			double x = -e2.c / e2.a;
			double y = -(e1.a * x + e1.c) / e1.b;
			return new CPoint(x, y);
		} else {
			if (e1.a / e1.b == e2.a / e2.b) {
				return null;
			}
			double a1 = -e1.a / e1.b;
			double b1 = -e1.c / e1.b;
			double a2 = -e2.a / e2.b;
			double b2 = -e2.c / e2.b;
			double x = (b2 - b1) / (a1 - a2);
			double y = a1 * x + b1;
			return new CPoint(x, y);
		}
	}

	
	private double f(double x) {// !!!!!!Cette fonction ne doit s'appeler que si
								// la droite n'est PAS verticale!!!!!
		return -x * (a / b) - c / b;
	}

	/**
	 * 
	 * Cette methode renvoie un tableau contenant les deux points se trouvant
	 * aux limites du panel, en coordonees theoriques. Cela permet ensuite de
	 * tracer une droite sans soucis.
	 * 
	 * @param d
	 *            La dimension du panel sur lequel il faut tracer la droite
	 * @param r
	 *            Le repere dans lequel se place la droite
	 * @return Un tableau de deux points au bords du panel
	 */
	public CPoint[] calculPointsExtremes(Dimension d, Repere r) {
		CPoint[] points = new CPoint[2];
		if (b < 0.0001) {// Si la droite est verticale
			if (c > 0.0001 || c < -0.0001) {
				points[0] = new CPoint(-c / a, r.conversionYtheorique(0));
				points[1] = new CPoint(-c / a, r.conversionYtheorique((int) d
						.getHeight()));
			}

		} else {
			points[0] = new CPoint(r.conversionXtheorique(0),
					f(r.conversionXtheorique(0)));
			points[1] = new CPoint(r.conversionXtheorique((int) d.getWidth()),
					f(r.conversionXtheorique((int) d.getWidth())));
		}
		return points;
	}
	
	/**
	 * Renvoie l'equation d'une droite quelconque parallele a celle representee par l'objet courant. 
	 * @return L'equation
	 */
	public Equation equationDroiteParallele(){
		Equation e=new Equation(this.a,this.b,this.c+2);
		return e;
	}
	
	/**
	 * Renvoie l'equation d'une droite parallele a celle representee par l'objet courant et qui passe par le point en parametre.
	 * @param p Un point quelconque
	 * @return L'equation voulue.
	 */
	public Equation equationDroiteParallele(CPoint p){
		double c=-this.a*p.getX()-this.b*p.getY();
		return new Equation(this.a,this.b,c);
	}
	
	/**
	 * Calcule et renvoie l'equation de la droite perpendiculaire a la droite representee par l'objet courant et passant par le point en parametre.
	 * @param p Un point quelconque
	 * @return L'equation voulue
	 */
	public Equation equationDroitePerpendiculaire(CPoint p){
		if(Math.abs(this.a)<0.00001){
			double a=1;
			double b=0;
			double c=-a*p.getX();
			return new Equation(a,b,c);
		}
		else if(Math.abs(this.b)<0.00001){
			double a=0;
			double b=1;
			double c=-b*p.getY();
			return new Equation(a,b,c);
		}
		else{
			double a=-this.b/this.a;
			double b=1;
			double c=-a*p.getX()-b*p.getY();
			return new Equation(a,b,c);
		}
		
	}

}
