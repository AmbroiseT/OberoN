package OberoN;


import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Cette classe abstraite represente une figure elementaire, qu'elle soit un point, une droite, un triangle... 
 * Ainsi, la Scene du logiciel contiendra un tableau de figures qu'il pourra dessinner grace a leur methode draw.
 * 
 * @author Ambroise et Maxime
 *
 */
public abstract class Figure implements Cloneable, Serializable {
	
	private static final long serialVersionUID = 1L;
	protected LinkedList<Figure> group=new LinkedList<Figure>();
	protected LinkedList<Figure> enfants = new LinkedList<Figure>();
	protected boolean reelle = true;

	/**
	 * Permet de grouper la figure courante avec les autres figures contenues dans la pile fournie en argument.
	 * @param selection Pile contenant des figures.
	 */
	public void grouper(Stack<Figure> selection){
		for(Figure f : selection){
			
			if(!group.contains(f) && f!=this){
				group.add(f);
			}
		}
	}
	
	/**
	 * Retire la figure courante des groupes dans lesquels elle etait. 
	 */
	public void degrouper(){
		
		for(Figure f : group){
			f.group.remove(this);
		}
		this.group=new LinkedList<Figure>();
	}
	/**
	 * Cette fonction sera invoquee par l'objet Scene sur chacune des figure qu'elle contient. 
	 * Elle permet, comme son nom l'indique de dessinner la figure sur la sortie d'affichage.
	 * 
	 * @param g L'objet Graphics qui sera affiche et sur lequel nous allons dessiner.
	 * 
	 * @param r Le repere theorique qui permet de passer des coordonnees sur le Graphics aux coordonees theoriques et vice versa.
	 * 
	 * @param d Cet objet permet simplement de donner la largeur et la hauteur de la fentre sur laquelle nous allons dessiner. 
	 * Il est en effet inutile de dessiner des objets qui ne seront pas affiches.
	 */
	public abstract void draw(Graphics g, Repere r, Dimension d);

	/**
	 * Cette methode force la figure a se recalculer (ses points, son equation...). 
	 * Elle sera invoquee si on la deplace ou si on deplace une figure a laquelle elle est liee.
	 */
	public abstract void update();

	/**
	 * Une methode permettant de faire une tranformation geometrique basique :  la translation.
	 * @param depX Le deplacement, positif ou negatif horizontal et theorique de la figure.
	 * @param depY	Le deplacement, positif ou negatif vertical et theorique de la figure.
	 */
	public abstract void translation(double depX, double depY);

	/**Cas particulier de translation qui ne vas pas appeller translation sur les autres membres du groupe. Cela permet d'eviter une boucle infinie.
	 * @param depX Deplacement horizontal
	 * @param depY deplacement vertical
	 */
	public abstract void translationLibre(double depX, double depY);
	/**
	 * Autre methode, proche de la translation, qui est fort utile pour permettre a l'utilisateur de deplacer une figure.
	 * @param x La nouvelle coordonee horizontale de la figure.
	 * 
	 * @param y La nouvelle coordonee verticale de la figure
	 */
	public abstract void teleportation(double x, double y);

	/**
	 * Methode non abstraite car la meme pour toutes les figures. Elle se contente d'ajouter un enfant a la dite figure.
	 * @param f L'enfant a ajouter
	 */
	public void addEnfant(Figure f) {
		if (f != null)
			enfants.add(f);
	}

	/**
	 * Lance la methode update sur tous les enfants de la figure. Cette methode est utilisee a chaque deplacement ou modification de la figure.
	 */
	public void miseAJourEnfants() {
		for (Figure f : enfants) {
			f.update();
		}
	}

	/**
	 * Permet de savoir si la figure definie est reelle c'est a dire possible. Par exemple, l'intersection de 2 droites paralleles n'est pas reelle, 
	 * mais on stocke quand meme cet objet au cas ou on deplacerait une des droites de sorte qu'elles ne soient plus paralleles.
	 * @return Un booleen.
	 */
	public boolean estReelle() {
		return reelle;
	}
	
	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	/**
	 * Cette methode regarde si le point decrit par les coordonnees theoriques x et y est dans la
	 * zone de selection de la figure
	 * @param x Coordonnee horizontale
	 * @param y Coordonnee verticale
	 * @param r Repere dans lequel on se place pour les calculs.
	 * @return Un booleen.
	 */
	public abstract boolean estDansSelection(double x, double y, Repere r);

	/**
	 * Change la couleur de la figure pour la mettre de la couleur d'un objet selectionne.
	 */
	public abstract void mettreCouleurSelection();

	/**
	 * Change la couleur de la figure pour la mettre de la couleur normale.
	 */
	public abstract void mettreCouleurNormale();
}
