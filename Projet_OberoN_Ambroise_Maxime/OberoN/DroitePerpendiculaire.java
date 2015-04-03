package OberoN;


/**
 * Cette classe, comme son nom l'indique, represente une droite perpendiculaire definie par rapport a une droite et un point.
 * 
 * @author Ambroise et Maxime
 *
 */
public class DroitePerpendiculaire extends Droite{
	
	private static final long serialVersionUID = 1L;
	
	CPoint point;
	Droite droite;
	
	/**
	 * Construit une droite perpendiculaire  a la droite en parametre et qui passe par le point en parametre.
	 * @param d La droite a laquelle l'objet sera perpendiculaire.
	 * @param p Le point par lequel la droite passe.
	 */
	public DroitePerpendiculaire(Droite d, CPoint p){
		d.addEnfant(this);
		p.addEnfant(this);
		point=p;
		droite=d;
		calculEq();
	}
	
	/**
	 * Calcule ou recacule l'equation de la droite.
	 */
	public void calculEq(){
		equation=droite.getEquation().equationDroitePerpendiculaire(point);
	}
	
	@Override
	public void update(){
		calculEq();
	}
}
