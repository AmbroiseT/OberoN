package OberoN;



import java.awt.Color;
import java.util.LinkedList;


/**
 * Definit l'isobarycentre d'un nombre illimite de points. 
 * @author Ambroise et Maxime
 *
 */
public class PointBarycentre extends CPoint{
	
	private static final long serialVersionUID = 1L;
	private LinkedList<CPoint> points=new LinkedList<CPoint>();
	
	
	/**
	 * Methode permettant d'obtenir le barycentre des points contenus dans le tableau en parametre.
	 * Dans le cas ou les donnees fournies n'auraient pas de sens (tableau vide...) la methode renvoie NULL.
	 * @param tab Tableau de points, il doit au moins contenir deux points pour que cela fonctionne.
	 * @return
	 */
	
	public PointBarycentre() {
		contenu = new Color(204,0,51);
		bordure = new Color(0, 0, 0, 50);
	}
	
	public static PointBarycentre getBarycentre(CPoint[] tab){
		if(tab==null || tab.length<2){
			return null;
		}
		else{
			PointBarycentre point=new PointBarycentre();
			double x=0;
			double y=0;
			int compt=tab.length;
			for(CPoint p : tab){
				x=x+p.getX();
				y=y+p.getY();
				point.points.add(p);
				p.addEnfant(point);
			}
			point.x=x/compt;
			point.y=y/compt;
			return point;
		}
	}
	
	@Override
	public void update(){
		double x=0;
		double y=0;
		int compt=points.size();
		for(CPoint p : points){
			x=x+p.getX();
			y=y+p.getY();
		}
		this.x=x/compt;
		this.y=y/compt;
		super.update();
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
