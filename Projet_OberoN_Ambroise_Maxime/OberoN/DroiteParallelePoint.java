package OberoN;


/**
 * 
 * 
 * Droite parallele a une autre et passant par un point du repere.
 * 
 * @author Ambroise et Maxime
 *
 */
public class DroiteParallelePoint extends DroiteParallele{
	
	private static final long serialVersionUID = 1L;
	
	protected CPoint point;
	
	/**
	 * Construit la droite parallele et passant par le point donne en parametre.
	 * @param d Droite a laquelle l'objet construit sera parallele
	 * @param p Point par lequelle la droite construite devra passer.
	 */
	public DroiteParallelePoint(Droite d,CPoint p) {
		d.addEnfant(this);
		p.addEnfant(this);
		point=p;
		droite=d;
		calculEq();
	}
	
	@Override
	public void calculEq(){
		this.equation=droite.equation.equationDroiteParallele(point);
	}
	
	@Override
	public void update(){
		calculEq();
	}
}
