package OberoN;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Cet objet est central au fonctionnement du programme, elle contient toutes les figures dans une LinkedList, le Repere, 
 * la Selection des objets par l'utilisateur...
 * Elle gere egalement tout ce qui concerne l'undo/redo (pas encore au point) et la sauvegarde des dessins sous la forme de fichiers.
 * @author Maxime et Ambroise
 *
 */
public class Scene {
	
	LinkedList<Figure> figures = new LinkedList<Figure>();
	Repere repere = new Repere(25);
	double puissanceZoom = 0.9;
	Stack<Figure> selection=new Stack<Figure>();
	int coordX, coordY;
	//int decalageRepereX = 0;
	//int decalageRepereY = 0;
	//int decalageRepere;
	public String name;
	int memory;
	int mem;
	
	/**
	 * Initialise une Scene avec des parametres par defaut.
	 */
	public Scene(){
		
		memory = 0;
		mem = 0;
		
		
		/*CPoint p1=new CPoint(-3,1);
		CPoint p2=new CPoint(-5,10);
		CPoint p3=new CPoint(-10,2);
		figures.add(p1);
		figures.add(p2);
		figures.add(p3);
		CPoint[] table1 = {p1, p2, p3};
		Triangle t1 = new Triangle(table1);
		figures.addFirst(t1);

		figures.addFirst(new Circumcircle(t1));

		p1=new CPoint(0,5);
		p2=new CPoint(5,8);
		p3=new CPoint(6,5);
		CPoint p4=new CPoint(1,3);
		figures.addFirst(new Circumcircle(t1));
		*/
		//CPoint [] tab={p1,p2,p3};
		/*PointBarycentre bary=PointBarycentre.getBarycentre(tab);
		System.out.println(bary);
		if(bary!=null){
			figures.add(bary);
		}*/
		/*
		CPoint a = new CPoint(8,-4);
		CPoint b = new CPoint(8,-1);
		CPoint c = new CPoint(8,4);
		CPoint d = new CPoint(10,6);
		CPoint e=new CPoint(1,1);
		try {
			DroiteDeuxPoints d1 = new DroiteDeuxPoints(a, b);
			DroiteDeuxPoints d2 = new DroiteDeuxPoints(c, d);
			DroitePerpendiculaire d3=new DroitePerpendiculaire(d1,e);
			figures.add(d3);
			figures.add(d1);
			figures.add(d2);
			figures.add(new PointIntersection(d1, d2));
		}
		catch(IllegalArgumentException exception){
			System.out.println("Souci");
		}
		figures.add(a);
		figures.add(b);
		figures.add(e);
		
		a = new CPoint(-10, -5);
		b = new CPoint(-11, -7);
		figures.add(new Circle(a, b));
		figures.add(a);
		figures.add(b);
		figures.add(p1);
		figures.add(p2);
		figures.add(p3);
		figures.add(p4);
		CPoint[] table2 = {p1, p2, p3, p4};
		figures.addLast(new Polygon(table2));
		*/
	}
	
	/**
	 * Permet de se stocker l'etat actuel de la scene afin de pouvoir utiliser les fonctions undo/redo plus tard.
	 */
	public void remember() {
		save(".cache/"+memory + ".ser");
		if (memory != mem) {
			for (int i = memory+1; ;i++) {
				try{
					if (!(new File(i + ".ser").delete())) {
						break;
					}
				} catch (Exception e) {
					break;
				}
			}
		}
		
		memory++;
		mem = memory;
	}
	
	/**
	 * Ajoute simplement la figure passee en argument a la scene, tout en stockant l'etat de la scene avant l'ajout.
	 * @param e Une figure quelconque.
	 */
	public void add(Figure e) {
		remember();
		figures.add(e);
	}
	/**
	 * Ajoute la figure en argument a la scene a un index particulier dans la LinkedList de stockage.
	 * @param index La position a laquelle on veut mettre la figure.
	 * @param e Une figure quelconque
	 */
	public void add(int index, Figure e) {
		remember();
		figures.add(index, e);
	}
	
	/**
	 * Permet de revenir au dernier etat sauvegarde, c'est a dire annuler la derniere action faite par l'utilisateur.
	 */
	@SuppressWarnings("unchecked")
	public void undo() {
		ObjectInputStream o = null;

        if (memory > 0)
        	memory--;
        else {
        	JOptionPane.showMessageDialog(null, "Cannot undo more actions!", "Error" , JOptionPane.ERROR_MESSAGE);
        	return;
        }
		try {
			FileInputStream f = new FileInputStream(".cache/"+memory + ".ser");
			o = new ObjectInputStream(f);
			figures = (LinkedList<Figure>) o.readObject();
			selection = (Stack<Figure>) o.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Permet d'annuler l'action d'un undo
	 */
	@SuppressWarnings("unchecked")
	public void redo() {
		ObjectInputStream o = null;
        if (memory < mem-1) {
        	memory++;
	        try {
				FileInputStream f = new FileInputStream(".cache/"+memory + ".ser");
				o = new ObjectInputStream(f);
				figures = (LinkedList<Figure>) o.readObject();
				selection = (Stack<Figure>) o.readObject();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        else
        	JOptionPane.showMessageDialog(null, "Cannot redo action!", "Error" , JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Sauvegarde le dessin courant si il a deja ete enregistre dans un fichier. Sinon, la methode appelle saveAs().
	 */
	public void save() {
		if (name != null) {
			save(name);
		} else {
			saveAs();
		}
	}
	
	/**
	 * Sauvegarde la scene courante en un fichier .ser.
	 * @param name nom du fichier .ser
	 */
	public void save(String name) {
		if (!name.substring(name.lastIndexOf(".") + 1, name.length()).equals("ser"))
			name += ".ser";
		ObjectOutputStream o = null;
		try {
			FileOutputStream f = new FileOutputStream(name);
			o = new ObjectOutputStream(f);
			o.writeObject(figures);
			o.writeObject(selection);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error: File not found\n"+e.toString(), "Error" , JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error: IOExcepetion\n"+e.toString(), "Error" , JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				o.close();
			} catch (IOException e) {
				e.printStackTrace();
    			JOptionPane.showMessageDialog(null, "Error: IOExcepetion\n"+e.toString(), "Error" , JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Ouvre une boite de dialogue qui permet a l'utilisateur de sauvegarder le dessin actuel dans un fichier de son choix.
	 */
	public void saveAs() {
		
		JFileChooser fileopen = new JFileChooser(System.getProperty("user.home"));
		FileFilter filter = new FileNameExtensionFilter("ser files", "ser");
        fileopen.addChoosableFileFilter(filter);
        fileopen.setFileFilter(filter);
        
        int r = fileopen.showDialog(fileopen, "Save file");
        
        if (r == JFileChooser.APPROVE_OPTION) {
        	File file = fileopen.getSelectedFile();
            String filename = file.getPath();
            if (!filename.substring(filename.lastIndexOf(".") + 1, filename.length()).equals("ser"))
            	filename += ".ser";
            name = filename;
			save(name);
        }
	}
	
	/**
	 * Permet d'ouvrir une boite de dialogue dans laquelle l'utilisateur peut charger un dessin precedemment sauvegarde.
	 */
	@SuppressWarnings("unchecked")
	public void openFile() {
		
		JFileChooser fileopen = new JFileChooser(System.getProperty("user.home"));
		FileFilter filter = new FileNameExtensionFilter("ser files", "ser");
        fileopen.addChoosableFileFilter(filter);
        fileopen.setFileFilter(filter);
        
        int r = fileopen.showDialog(fileopen, "Open file");
        
        if (r == JFileChooser.APPROVE_OPTION) {
            
        	File file = fileopen.getSelectedFile();
            ObjectInputStream o = null;
            String filename = file.getName();
            
            if (filename.substring(filename.lastIndexOf(".") + 1, filename.length()).equals("ser")) {
        		try {
        			FileInputStream f = new FileInputStream(file);
        			o = new ObjectInputStream(f);
    				figures = (LinkedList<Figure>) o.readObject();
    				selection = (Stack<Figure>) o.readObject();
        			JOptionPane.showMessageDialog(null, "File Loaded", "Operation Finished" , JOptionPane.INFORMATION_MESSAGE);
        			name = filename;
        		} catch (FileNotFoundException e) {
        			JOptionPane.showMessageDialog(null, "Error: File not found\n"+e.toString(), "Error" , JOptionPane.ERROR_MESSAGE);
        			e.printStackTrace();
        		} catch (IOException e) {
        			JOptionPane.showMessageDialog(null, "Error: IOExecption\n"+e.toString()+"\nMaybe a too old file version...", "Error" , JOptionPane.ERROR_MESSAGE);
        			e.printStackTrace();
        		} catch (ClassNotFoundException e) {
        			JOptionPane.showMessageDialog(null, "Error: ClassNotFoundException\n"+e.toString(), "Error" , JOptionPane.ERROR_MESSAGE);
        			e.printStackTrace();
        		}
        	}
            else {
            	JOptionPane.showMessageDialog(null, "Error: Invalid file type " + filename, "Error" , JOptionPane.ERROR_MESSAGE);
            }
        }
	}
	
	/**
	 * Appelle la methode clear sur l'objet figures et sur l'objet selection ce qui a pour effet de reinitialiser le dessin.
	 */
	public void clear() {
		figures.clear();
		selection.clear();
		name = null;
	}
	
	/**
	 * Applique un dezoom sur le repere.
	 * @param xSouris La position horizontale du curseur
	 * @param ySouris La position verticale du curseur
	 */
	public void dezoomerRepere(int xSouris, int ySouris){
		repere.zoomer(xSouris, ySouris, puissanceZoom);
	}
	/**
	 * Applique un zoom sur le repere.
	 * @param xSouris La position horizontale du curseur
	 * @param ySouris La position verticale du curseur
	 */
	public void zoomerRepere(int xSouris, int ySouris){
		repere.zoomer(xSouris, ySouris, 1/puissanceZoom);
	}
	
	/**
	 * Vide la selection et remet leur couleur normale aux figures dans la selection et selectionne le point situe sur le curseur de la souris s'il y en a bien un.
	 * @param x La position horizontale du curseur
	 * @param y La position verticale du curseur
	 */
	public void clicGaucheSourisSelection(int x, int y){
		boolean selectionne=false;
		for(Figure f : figures){
			if(f.estDansSelection(x, y, repere)){
				if(!selection.empty()){
					for(Figure f2:selection){
						f2.mettreCouleurNormale();
					}
					selection=new Stack<Figure>();
				}
				if(!selection.contains(f)){
					selection.add(f);
				}
				f.mettreCouleurSelection();
				selectionne=true;
			}
		}
		if(!selectionne){
			if(!selection.empty()){
				for(Figure f2: selection){
					f2.mettreCouleurNormale();
				}
				selection=new Stack<Figure>();
			}
		}
		
	}
	
	/**
	 * Vide la selection et remet leur couleur normale aux figure qu'elle contenait.
	 */
	public void clearSelection() {
		for (Figure e : selection) {
			e.mettreCouleurNormale();
		}
		selection.removeAllElements();
	}
	
	/**
	 * Deplace le repere conformement au deplacement du curseur.
	 * @param x Deplacement horizontal du repere
	 * @param y Deplacement vertical du repere.
	 */
	public void dragRepere(int x, int y){
		int decX=-coordX+x;
		int decY=-coordY+y;
		repere.placer(repere.getX0()+decX,repere.getY0()+decY);
		coordX=x;
		coordY=y;
	}
	
	/**
	 * Retiens les nouvelles coordonnees de la souris apres un de ses deplacements
	 * @param x Position horizontale du curseur.
	 * @param y Position verticale du curseur.
	 */
	public void deplacementSouris(int x, int y){
		coordX=x;
		coordY=y;
		//int mousePositionX = 0;
		//int mousePositionY = 0;
	}
	/**
	 * Permet de recentrer le repere.
	 * @param jp Le JPanel dans lequel on dessine.
	 */
	public void iniRepere(JPanel jp){
		repere.centrer(jp);
		repere.resetEchelle();
	}
	
	/**
	 * Deplace tout les elements de la selection en fonction dela position du curseur
	 * @param x Nouvelle postion horizontale du curseur
	 * @param y Nouvelle position verticale du curseur
	 */
	public void deplacerSelection(int x, int y){
		int depX=x-coordX;
		int depY=y-coordY;
		coordX=x;
		coordY=y;
		if(!selection.empty()){
			for(Figure f: selection){
				f.translation(repere.conversionDecalageTheoriqueX(depX),repere.conversionDecalageTheoriqueY(depY));
			}
		}
	}
	
	/**
	 * Permet d'ajouter l'element sur lequel l'utilisateur a clique a la selection. Dans le cas ou l'utilisateur ne clique sur rien, la selection se vide.
	 * @param x
	 * @param y
	 */
	public void clicGaucheSourisCtrlSelection(int x, int y){
		boolean selectionne=false;
		for(Figure f : figures){
			if(f.estDansSelection(x, y, repere)){
				if(!selection.contains(f)){
					selection.add(f);
				}
				f.mettreCouleurSelection();
				selectionne=true;
			}
		}
		if(!selectionne){
			if(!selection.empty()){
				for(Figure f2: selection){
					f2.mettreCouleurNormale();
				}
				selection=new Stack<Figure>();
			}
		}
	}

	/**
	 * Deplace le centre du repere a la position en argument
	 * @param x
	 * @param y
	 */
	public void moveRepere(int x, int y){
		repere.placer(x, y);
	}
	/**
	 * Dessine les figures et le repere a l'ecran.
	 * @param g Le Graphics surlequel on dessine
	 * @param d La dimension de la fenetre
	 * @param grid Mettre true pour afficher la grille et false sinon.
	 */
	public void draw(Graphics g,Dimension d, boolean grid){
		g.setColor(new Color(255,255,255));
		g.fillRect(0, 0, (int)d.getWidth(), (int)d.getHeight());
		
		if (grid)
			repere.draw(g, d);
		for (Figure f : figures) {
			if(f.estReelle()){
				f.draw(g, repere, d);
			}
		}
	}
}
