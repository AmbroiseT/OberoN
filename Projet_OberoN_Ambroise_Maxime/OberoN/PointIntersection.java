package OberoN;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Definit l'intersection de deux droites. Petite subtilite : dans le cas de deux droites paralleles la figure existe toujours,
 *  elle est cependant consideree comme "non reelle" c'est a dire qu'elle ne s'affichera pas. Cela permet d'eviter que l'utilisateur detruise l'intersection 
 *  qu'il avait cree ainsi que tout ses enfants en deplacant une des droites de facon malavisee.
 * @author Ambroise et Maxime
 *
 */
public class PointIntersection extends CPoint{
	
	private static final long serialVersionUID = 1L;
	
	Droite droite1;
	Droite droite2;
	private static final int size=10;//taille en pixels du cercle representant un point quel que soit le zoom
	
	/**
	 * Construit le point d'intersection des deux droites en argument
	 * @param d1 Une droite
	 * @param d2 Encore une droite
	 */
	public PointIntersection(Droite d1, Droite d2){
		
		CPoint p=Equation.intersection(d1.getEquation(), d2.getEquation());
		if(p!=null){
			this.x=p.x;
			this.y=p.y;
		}
		else{
			reelle=false;
		}
		d1.addEnfant(this);
		d2.addEnfant(this);
		droite1=d1;
		droite2=d2;
		
		contenu = new Color(204,0,51);
		bordure = new Color(0, 0, 0, 50);
	}									
	
	@Override
	public void draw(Graphics g, Repere r,Dimension d){
		g.setColor(contenu);
		int coordX=r.conversionXreel(x);
		int coordY=r.conversionYreel(y);
		
		g.fillOval(coordX-size/2, coordY-size/2, size, size);
		g.setColor(bordure);
		g.drawOval(coordX-size/2, coordY-size/2, size, size);
	}
	
	@Override
	public void update(){
		CPoint p=Equation.intersection(droite1.getEquation(), droite2.getEquation());
		if(p==null){
			reelle=false;
		}
		else{
			reelle=true;
			this.x=p.x;
			this.y=p.y;
		}
	}
	/**
	 * Change la couleur de la figure pour la mettre de la couleur de selection.
	 */
	@Override
	public void mettreCouleurSelection() {
		contenu = new Color(204,0,51);
		bordure = new Color(0, 0, 0, 50);
	}
	/**
	 * Change la couleur de la figure pour la mettre de la couleur normale.
	 */
	@Override
	public void mettreCouleurNormale() {
		contenu = new Color(204,0,51);
		bordure = new Color(0, 0, 0, 50);
	}
}
