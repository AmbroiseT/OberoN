package OberoN;


/**
 * Cette figure represente une droite parallele a une autre.
 * 
 * @author Ambroise et Maxime
 * 
 */
public class DroiteParallele extends Droite{
	
	private static final long serialVersionUID = 1L;
	
	protected Droite droite;
	
	/**
	 * Construit une droite parallele a la droite en parametre.
	 * @param d Droite
	 */
	public DroiteParallele(Droite d){
		droite=d;
		calculEq();
		d.addEnfant(this);
	}
	
	public DroiteParallele(){
		
	}
	
	@Override
	public void update(){
		calculEq();
	}
	
	/**
	 * Calcule et recacule au besoin l'equation de la droite
	 */
	public void calculEq(){
		this.equation=droite.equation.equationDroiteParallele();
	}

}
