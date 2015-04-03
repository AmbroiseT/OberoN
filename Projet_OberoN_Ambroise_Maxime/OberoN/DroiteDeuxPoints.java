package OberoN;

/**
 * Cette figure est une droite dependant de deux point, c'est a dire que si l'un des points en question est deplace, la droite sera recalculee en consequence.
 * 
 * @author Ambroise et Maxime
 * 
 * 
 *
 *
 *
 * @author Maxime et Ambroise
 *
 */
public class DroiteDeuxPoints extends Droite {
	
	private static final long serialVersionUID = 1L;

	protected CPoint point1;
	protected CPoint point2;

	/**
	 * Cree une droite dependant de deux points, renvoie une exception en cas de donnees fausses.
	 * @param p1 Premier point
	 * @param p2 Second point
	 * @throws IllegalArgumentException
	 */
	public DroiteDeuxPoints(CPoint p1, CPoint p2) throws IllegalArgumentException {
		Equation e = Equation.creeEquation(p1, p2);
		if (e != null) {
			this.equation = e;
			this.point1 = p1;
			this.point2 = p2;
			this.point1.addEnfant(this);
			this.point2.addEnfant(this);
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void update() {
		Equation e = Equation.creeEquation(point1, point2);
		if (e != null) {
			equation = e;
		}
		super.update();
	}
	
	/**
	 * Regarde si la droite est bien composee des deux points en parametre
	 *
	 * @param p1 Un point
	 * @param p2 Un point
	 * @return Un booleen
	 */
	public boolean estSelection(CPoint p1, CPoint p2){
		return (p1==point1 && p2==point2) || (p1==point2 && p2==point1);
	}
}
