package OberoN;

/**
 * Cette classe, qui etend Polygone, represente un Triangle c'est a dire un polygone a 3 sommets.
 * @author Maxime et Ambroise
 *
 */
public class Triangle extends Polygon {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Ce contructeur cree un Triangle dont les sommets sont les 3 Points contenus dans le tableau en argument.
	 * @param points Tableau de 3 points.
	 */
	public Triangle(CPoint[] points) {
		super(points);
	}
}
