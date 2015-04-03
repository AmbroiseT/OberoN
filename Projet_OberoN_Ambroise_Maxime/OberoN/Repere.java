package OberoN;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Cet objet permet de faire la transition entre le repere theorique dans lequel sont definies les figures et les coordonees graphiques de la fenetre sur laquelle on 
 * dessine. Il est succeptible de changer a chaque fois que l'utilisateur fait un zoom.
 * @author Ambroise et Maxime
 *
 */
public class Repere {
	private int x0, y0;// CPoint d'origine par rapport au repere de la
						// fenetre(Centre de la fenetre)
	private double echelle;// on multiplie les distances en pixel par ce nombre
							// pour avoir la taille d'une figure
	
	private final double echelle_ref; // to reset the zoom
	private static final double max_echelle=1000;
	private static final double min_echelle=0.06;

	/**
	 * Construit un repere basique et centre au milieu de la fenetre.
	 * @param echelle L'echelle definit le niveu de zoom.
	 */
	public Repere(double echelle) {
		x0 = 0;
		y0 = 0;
		this.echelle = echelle;
		this.echelle_ref = echelle;
	}

	/**
	 * Cette methode effectue un zoom du repere centre a la position de la souris
	 * @param xSouris Position horizontale reelle de la souris
	 * @param ySouris Position verticale reelle de la souris
	 * @param facteur Facteur de zoom positif, s'il est inferieur a 1 on dezoome, sinon on zoome. 
	 */
	public void zoomer(int xSouris, int ySouris, double facteur) {
		if(echelle*facteur<min_echelle){
			return;
		}
		if(echelle*facteur>max_echelle){
			return;
		}
		x0 = (int) (x0 - (facteur - 1) * (xSouris - x0));
		y0 = (int) (y0 - (facteur - 1) * (ySouris - y0));
		echelle *= facteur;
	}

	/**
	 * Centre le repere a la position reelle en parametre.
	 * @param x Position horizontale reelle.
	 * @param y Position verticale reelle.
	 */
	public void placer(int x, int y) {
		x0 = x;
		y0 = y;
	}
	
	/**
	 * Place le repere au centre du JPanel fourni en argument.
	 * @param jp Le JPanel dans lequel le repere est dessine
	 */
	public void centrer(JPanel jp) {
		Dimension dim = jp.getSize();
		x0 = (int) (dim.getWidth()/2);
		y0 = (int) (dim.getHeight()/2);
	}

	/**
	 * Trace le repere et son quadrillage.
	 * @param g Le graphics sur lequel on trace.
	 * @param d La dimension de la fenetre.
	 */
	public void draw(Graphics g, Dimension d) {
		
		g.setColor(new Color(75,91,110, 125));
		
		int height = (int) d.getHeight();
		int width = (int) d.getWidth();

		g.drawLine(x0, 0, x0, height);
		g.drawLine(0, y0, width, y0);
		
		// START DRAW THE FRAME
		if (echelle > 200){
			quadrillage(g, echelle/10, d);
		}
		else if (echelle > 5) {
			quadrillage(g, echelle, d);
			
			
		}
		//Cas ou le dezoom est trop important
		else if (echelle<=5 && echelle>0.5){
			quadrillage(g, echelle*10, d);
		}
		else if(echelle<=0.5){
			quadrillage(g, echelle*100, d);
		}
		// END
	}

	private void quadrillage(Graphics g, double echelle, Dimension d){
		int size = 3;
		int height = (int) d.getHeight();
		int width = (int) d.getWidth();
		Color black = new Color(75,91,110, 125);
		Color blue1 = new Color(75,91,110, 70);
		Color blue2 = new Color(75,91,110, 40);
		
		for (double i = x0+echelle, j = 1; i < width; i += echelle, j++) {
			g.setColor(black);
			g.drawLine((int) i, y0, (int) i, (int) (y0 - size));
			g.drawLine((int) i, y0, (int) i, (int) (y0 + size));
			g.setColor(blue2);
			g.drawLine((int)i, 0, (int)i, height);
			if (j%5 == 0) {
				g.setColor(blue1);
				g.drawLine((int)i, 0, (int)i, height);
			}
		}
		for (double i = x0-echelle, j = 1; i >= 0; i -= echelle, j++) {
			g.setColor(black);
			g.drawLine((int) i, y0, (int) i, y0 - size);
			g.drawLine((int) i, y0, (int) i, y0 + size);
			g.setColor(blue2);
			g.drawLine((int)i, 0, (int)i, height);
			if (j%5 == 0) {
				g.setColor(blue1);
				g.drawLine((int)i, 0, (int)i, height);
			}
		}
		for (double i = y0+echelle, j = 1; i < height; i += echelle, j++) {
			g.setColor(black);
			g.drawLine(x0, (int) i, x0 + size, (int) i);
			g.drawLine(x0, (int) i, x0 - size, (int) i);
			g.setColor(blue2);
			g.drawLine(0, (int) i, width, (int) i);
			if (j%5 == 0) {
				g.setColor(blue1);
				g.drawLine(0, (int)i, width, (int)i);
			}
		}
		for (double i = y0-echelle, j = 1; i >= 0; i -= echelle, j++) {
			g.setColor(black);
			g.drawLine(x0, (int) i, x0 + size, (int) i);
			g.drawLine(x0, (int) i, x0 - size, (int) i);
			g.setColor(blue2);
			g.drawLine(0, (int) i, width, (int) i);
			if (j%5 == 0) {
				g.setColor(blue1);
				g.drawLine(0, (int)i, width, (int)i);
			}
		}
	}
	/**
	 * Renvoie la coordonnee horizontale reelle du centre du repere
	 * @return
	 */
	public int getX0() {
		return x0;
	}

	/**
	 * Renvoie la coordonnee verticale reelle du centre du repere
	 * @return
	 */
	public int getY0() {
		return y0;
	}

	/**
	 * Renvoie l'echelle du repere.
	 * @return
	 */
	public double getEchelle() {
		return echelle;
	}
	
	/**
	 * Permet de replacer horizontalement le centre du repere
	 * @param x0
	 */
	public void setX0(int x0) {
		this.x0 = x0;
	}

	/**
	 * Permet de replacer verticalement le centre du repere
	 * @param y0
	 */
	public void setY0(int y0) {
		this.y0 = y0;
	}
	
	/**
	 * Permet de changer le niveau de zoom. Le zoom n'est pas illimite, il y a une valeur maximale et une valeur minimale. 
	 * Cette methode verifie que ces limites ne sont pas depassees 
	 * @param d
	 */
	public void setEchelle(double d){
		if(d>max_echelle){
			echelle=max_echelle;
		}
		else if(d<min_echelle){
			echelle=min_echelle;
		}
		else{
			echelle=d;
		}
	}
	
	/**
	 * Remet le zoom au niveau de depart.
	 */
	public void resetEchelle() {
		echelle = echelle_ref;
	}

	/**
	 * Convertit une coordonnee horizontale theorique en coordonnee reelle.
	 * @param x
	 * @return
	 */
	public int conversionXreel(double x) {
		return (int) (x * getEchelle() + x0);
	}

	/**
	 * Convertit une coordonnee verticale theorique en coordonnee reelle.
	 * @param y
	 * @return
	 */
	public int conversionYreel(double y) {
		return (int) (-y * getEchelle() + y0);
	}

	/**
	 * Convertit une coordonnee verticale reelle en coordonne theorique
	 * @param x
	 * @return
	 */
	public double conversionXtheorique(int x) {
		return (x - x0) / getEchelle();
	}

	/**
	 * Convertit une coordonnee horizontale reelle en coordonne theorique
	 * @param y
	 * @return
	 */
	public double conversionYtheorique(int y) {
		return (y0 - y) / getEchelle(); // coords inversees en y
	}
	
	/**
	 * Convertit un decalage horizontal reel en decalage theoriques
	 * @param x
	 * @return
	 */
	public double conversionDecalageTheoriqueX(int x){
		return x/echelle;
	}
	
	/**
	 * Convertit un decalage vertical reel en decalage theoriques
	 * @param y
	 * @return
	 */
	public double conversionDecalageTheoriqueY(int y){
		return -y/echelle;
	}

}
