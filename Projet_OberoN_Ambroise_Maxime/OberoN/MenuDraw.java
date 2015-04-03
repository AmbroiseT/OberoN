package OberoN;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

/**
 * Le menu deroulant qui contient les fonctionnalites de dessin de figures telles que triangle, segment... Il gere cela via des ActionListener.
 * @author Maxime et Ambroise
 *
 */
public class MenuDraw extends JMenu {

	private static final long serialVersionUID = 1L;

	DrawPanel dp;

	/**
	 * Cree et initialise un MenuDraw lie au DrawPanel en argument.
	 * @param dp Le DrawPanel sur lequele on dessine.
	 */
	public MenuDraw(final DrawPanel dp) {
		setText("Draw");
		setBorder(BorderFactory.createCompoundBorder(this.getBorder(),
				BorderFactory.createEmptyBorder(4, 0, 4, 0)));
		setIcon(new ImageIcon(getClass().getClassLoader().getResource(
				"img/iconmonstr-pencil-9-icon-16.png")));
		this.dp = dp;

		Border border = BorderFactory.createCompoundBorder(this.getBorder(),
				BorderFactory.createEmptyBorder(0, 0, 0, 0));

		ImageIcon icon0 = new ImageIcon(getClass().getClassLoader()
				.getResource("img/iconmonstr-hexagon-icon-16.png"));

		JMenuItem polygon = new JMenuItem("Polygon", icon0);
		polygon.setAccelerator(KeyStroke.getKeyStroke("control alt P"));
		polygon.setToolTipText("Draw a Polygon");
		polygon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (dp.scene.selection.size() > 3) {
					CPoint[] c = new CPoint[dp.scene.selection.size()];
					for (int i = 0; i < c.length; i++)
						c[i] = (CPoint) dp.scene.selection.get(i);
					dp.scene.add(dp.scene.figures.size() - c.length,
							new Polygon(c));
				}
				dp.repaint();
			}
		});
		polygon.setBorder(border);

		ImageIcon icon1 = new ImageIcon(getClass().getClassLoader()
				.getResource("img/iconmonstr-triangle-icon-16.png"));

		JMenuItem triangle = new JMenuItem("Triangle", icon1);
		triangle.setAccelerator(KeyStroke.getKeyStroke("control alt T"));
		triangle.setToolTipText("Draw a Triangle");
		triangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int size = dp.scene.selection.size();
				if (size == 3) {
					CPoint[] c = new CPoint[3];
					for (int i = 0; i < c.length; i++)
						c[i] = (CPoint) dp.scene.selection.get(i);
					dp.scene.add(dp.scene.figures.size() - 3, new Triangle(c));
					dp.repaint();
				}

				else {
					String r = "Reason: ";
					if (size < 3)
						r += "not enought points (need " + (3 - size)
								+ " more)";
					else
						r += "too many points (need only 3 points)";
					JOptionPane.showMessageDialog(null,
							"Cannot draw this element\n" + r, "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		triangle.setBorder(border);
		

		ImageIcon icon2 = new ImageIcon(getClass().getClassLoader()
				.getResource("img/iconmonstr-circle-icon-16.png"));

		JMenuItem circle = new JMenuItem("Circle", icon2);
		circle.setAccelerator(KeyStroke.getKeyStroke("control alt C"));
		circle.setToolTipText("Draw a Circle");
		circle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int size = dp.scene.selection.size();
				if (size == 2) {
					dp.scene.add(dp.scene.figures.size() - 2, new Circle(
							(CPoint) dp.scene.selection.get(0),
							(CPoint) dp.scene.selection.get(1)));
					dp.repaint();
				} else {
					String r = "Reason: ";
					if (size < 2)
						r += "not enought points (need " + (2 - size)
								+ " more)";
					else
						r += "too many points (need only 2 points)";
					JOptionPane.showMessageDialog(null,
							"Cannot draw this element\n" + r, "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		circle.setBorder(border);

		JMenuItem circonscrit = new JMenuItem("Circumscribing circle", new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-circle-outline-icon-16.png")));
		circonscrit.setAccelerator(KeyStroke.getKeyStroke("control alt C"));
		circonscrit.setToolTipText("Draw a Triangle");
		circonscrit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int size = dp.scene.selection.size();
				if (size == 3) {
					CPoint[] c = new CPoint[3];
					for (int i = 0; i < c.length; i++)
						c[i] = (CPoint) dp.scene.selection.get(i);
					dp.scene.add(dp.scene.figures.size() - 3, new Circumcircle(
							c[0], c[1], c[2]));
					dp.repaint();
				}

				else {
					String r = "Reason: ";
					if (size < 3)
						r += "not enought points (need " + (3 - size)
								+ " more)";
					else
						r += "too many points (need only 3 points)";
					JOptionPane.showMessageDialog(null,
							"Cannot draw this element\n" + r, "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		circonscrit.setBorder(border);
		ImageIcon icon3 = new ImageIcon(getClass().getClassLoader()
				.getResource("img/icon-line2points-icon-16.png"));

		JMenuItem line = new JMenuItem("Line", icon3);
		line.setAccelerator(KeyStroke.getKeyStroke("control alt L"));
		line.setToolTipText("Draw a Line");
		line.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int size = dp.scene.selection.size();
				if (size == 2) {
					dp.scene.add(
							dp.scene.figures.size() - 2,
							new DroiteDeuxPoints((CPoint) dp.scene.selection
									.get(0), (CPoint) dp.scene.selection.get(1)));
					dp.repaint();
				} else {
					String r = "Reason: ";
					if (size < 2)
						r += "not enought points (need " + (2 - size)
								+ " more)";
					else
						r += "too many points (need only 2 points)";
					JOptionPane.showMessageDialog(null,
							"Cannot draw this element\n" + r, "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		line.setBorder(border);

		JMenuItem parallele = new JMenuItem("Parallel", new ImageIcon(getClass().getClassLoader().getResource("img/icon-paralleles-icon-16.png")));
		parallele.setAccelerator(KeyStroke.getKeyStroke("control alt shift P"));
		parallele.setToolTipText("Draw a parallel line");
		parallele.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int size = dp.scene.selection.size();
				if (size == 3) {
					Stack<Figure> selec = dp.scene.selection;
					LinkedList<Figure> figures=dp.scene.figures;
					int ind1=0;
					int ind2=0;
					int sizeSelec=selec.size();
					Droite d1=null;
					DroiteDeuxPoints temp;
						int sizeFigures=figures.size();
						for(int i=0;i<sizeFigures && d1==null;i++){
							Figure f=figures.get(i);
							if(f instanceof DroiteDeuxPoints){
								temp=(DroiteDeuxPoints)f;
								for(int j=0;j<sizeSelec-1;j++){
									for(int k=j+1;k<sizeSelec;k++){
										if(selec.get(j) instanceof CPoint && selec.get(k) instanceof CPoint){
											if(temp.estSelection((CPoint)selec.get(j), (CPoint) selec.get(k))){
												d1=temp;
												ind1=j;
												ind2=k;
											}
										}
									}
								}
							}
					
						}
						CPoint[] c = new CPoint[3];
						if(d1==null){
							for (int i = 0; i < c.length; i++)
								c[i] = (CPoint) dp.scene.selection.get(i);
							
						}
						else{
							if(ind1 !=0 && ind2!=0){
								c[0]=(CPoint) dp.scene.selection.get(1);
								c[1]=(CPoint) dp.scene.selection.get(2);
								c[2]=(CPoint) dp.scene.selection.get(0);
							}
							else if(ind1!=1 && ind2!=1){
								c[0]=(CPoint) dp.scene.selection.get(0);
								c[1]=(CPoint) dp.scene.selection.get(2);
								c[2]=(CPoint) dp.scene.selection.get(1);
							}
							else{
								c[0]=(CPoint) dp.scene.selection.get(0);
								c[1]=(CPoint) dp.scene.selection.get(1);
								c[2]=(CPoint) dp.scene.selection.get(2);
							}
						}
						dp.scene.add(dp.scene.figures.size() - 2,
								new DroiteParallelePoint(new DroiteDeuxPoints(
										c[0], c[1]), c[2]));
						dp.repaint();
						
					dp.repaint();
				}

				else if (size == 2) {
					CPoint[] c = new CPoint[2];
					for (int i = 0; i < c.length; i++)
						c[i] = (CPoint) dp.scene.selection.get(i);
					CPoint p = new CPoint(0, 0);
					dp.scene.add(dp.scene.figures.size() - 1,
							new DroiteParallelePoint(new DroiteDeuxPoints(c[0],
									c[1]), p));
					dp.scene.add(dp.scene.figures.size() - 1, p);
					dp.repaint();
				} else {
					String r = "Reason: ";
					if (size < 3)
						r += "not enought points (need 2 or 3 points)";
					else
						r += "too many points (need only 3 or 2 points)";
					JOptionPane.showMessageDialog(null,
							"Cannot draw this element\n" + r, "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		parallele.setBorder(border);

		JMenuItem perpendiculaire = new JMenuItem("Perpendicular", new ImageIcon(getClass().getClassLoader().getResource("img/icon-perpendiculaires-icon-16.png")));
		perpendiculaire.setAccelerator(KeyStroke.getKeyStroke("control alt M"));
		perpendiculaire.setToolTipText("Draw a perpendicular line");
		perpendiculaire.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Stack<Figure> selec = dp.scene.selection;
				LinkedList<Figure> figures=dp.scene.figures;
				int size = dp.scene.selection.size();
				int ind1=0;
				int ind2=0;
				int sizeSelec=selec.size();
				if (size == 3) {
					Droite d1=null;
					DroiteDeuxPoints temp;
					int sizeFigures=figures.size();
					for(int i=0;i<sizeFigures && d1==null;i++){
						Figure f=figures.get(i);
						if(f instanceof DroiteDeuxPoints){
							temp=(DroiteDeuxPoints)f;
							for(int j=0;j<sizeSelec-1;j++){
								for(int k=j+1;k<sizeSelec;k++){
									if(selec.get(j) instanceof CPoint && selec.get(k) instanceof CPoint){
										if(temp.estSelection((CPoint)selec.get(j), (CPoint) selec.get(k))){
											d1=temp;
											ind1=j;
											ind2=k;
										}
									}
								}
							}
						}
					}
					CPoint[] c = new CPoint[3];
					if(d1==null){
						for (int i = 0; i < c.length; i++)
							c[i] = (CPoint) dp.scene.selection.get(i);
						
					}
					else{
						if(ind1 !=0 && ind2!=0){
							c[0]=(CPoint) dp.scene.selection.get(1);
							c[1]=(CPoint) dp.scene.selection.get(2);
							c[2]=(CPoint) dp.scene.selection.get(0);
						}
						else if(ind1!=1 && ind2!=1){
							c[0]=(CPoint) dp.scene.selection.get(0);
							c[1]=(CPoint) dp.scene.selection.get(2);
							c[2]=(CPoint) dp.scene.selection.get(1);
						}
						else{
							c[0]=(CPoint) dp.scene.selection.get(0);
							c[1]=(CPoint) dp.scene.selection.get(1);
							c[2]=(CPoint) dp.scene.selection.get(2);
						}
					}
					dp.scene.add(dp.scene.figures.size() - 2,
							new DroitePerpendiculaire(new DroiteDeuxPoints(
									c[0], c[1]), c[2]));
					dp.repaint();
				}

				else if (size == 2) {
					CPoint[] c = new CPoint[2];
					for (int i = 0; i < c.length; i++)
						c[i] = (CPoint) dp.scene.selection.get(i);
					CPoint p = new CPoint(0, 0);
					dp.scene.add(dp.scene.figures.size() - 1,
							new DroitePerpendiculaire(new DroiteDeuxPoints(
									c[0], c[1]), p));
					dp.scene.add(dp.scene.figures.size() - 1, p);
					dp.repaint();
				} else {
					String r = "Reason: ";
					if (size < 2)
						r += "not enought points (need 2 or 3 points)";
					else
						r += "too many points (need only 3 or 2 points)";
					JOptionPane.showMessageDialog(null,
							"Cannot draw this element\n" + r, "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		perpendiculaire.setBorder(border);

		JMenuItem barycentre = new JMenuItem("Barycentre", new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-cursor-move-icon-16.png")));
		barycentre.setAccelerator(KeyStroke.getKeyStroke("control alt B"));
		barycentre.setToolTipText("Draw the barycentre");
		barycentre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int size = dp.scene.selection.size();
				if(size<2){
					String r = "Reason: ";
					r += "not enought points (need at least 2 points)";
					JOptionPane.showMessageDialog(null,
							"Cannot draw this element\n" + r, "Error",
							JOptionPane.WARNING_MESSAGE);
				}
				else  {
					CPoint[] c = new CPoint[size];
					for (int i = 0; i < c.length; i++)
						c[i] = (CPoint) dp.scene.selection.get(i);
					dp.scene.add(
							PointBarycentre.getBarycentre(c));
					dp.repaint();
				}

			}
		});
		perpendiculaire.setBorder(border);
		
		JMenuItem segment = new JMenuItem("Segment", new ImageIcon(getClass().getClassLoader().getResource("img/icon-segment2points-icon-16.png")));
		segment.setAccelerator(KeyStroke.getKeyStroke("alt S"));
		segment.setToolTipText("Draw a segment");
		segment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int size = dp.scene.selection.size();
				if (size != 2) {
					String r = "Reason: ";
					if(size<2){
						r += "not enought points (need 2 points)";
					}
					else{
						r+= "too much points (need two points)";
					}
					JOptionPane.showMessageDialog(null,
							"Cannot draw this element\n" + r, "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {
					CPoint[] c = new CPoint[size];
					for (int i = 0; i < c.length; i++)
						c[i] = (CPoint) dp.scene.selection.get(i);
					dp.scene.add(new Segment(c[0], c[1]));
					dp.repaint();
				}

			}
		});
		segment.setBorder(border);
		
		JMenuItem intersection = new JMenuItem("Intersection", new ImageIcon(getClass().getClassLoader().getResource("img/icon-intersection-icon-16.png")));
		intersection.setAccelerator(KeyStroke.getKeyStroke("control alt I"));
		intersection.setToolTipText("Draw the intersection");
		intersection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Stack<Figure> selec = dp.scene.selection;
				LinkedList<Figure> figures=dp.scene.figures;
				int sizeSelec=selec.size();
				if (sizeSelec != 4) {
					String r = "Reason: ";
					if (sizeSelec < 4) {
						r += "not enought points (need 4 points)";
					} else {
						r += "too much points (need 4 points)";
					}
					JOptionPane.showMessageDialog(null,
							"Cannot draw this element\n" + r, "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {
					Droite d1=null;
					Droite d2=null;
					DroiteDeuxPoints temp;
					int sizeFigures=figures.size();
					for(int i=0;i<sizeFigures && (d1==null || d2==null);i++){
						Figure f=figures.get(i);
						//Cas ou on selectione ne droite par deux points
						if(f instanceof DroiteDeuxPoints){
							temp=(DroiteDeuxPoints)f;
							for(int j=0;j<sizeSelec-1;j++){
								for(int k=j+1;k<sizeSelec;k++){
									if(selec.get(j) instanceof CPoint && selec.get(k) instanceof CPoint){
										if(temp.estSelection((CPoint)selec.get(j), (CPoint) selec.get(k))){
											if(d1==null){
												d1=temp;
											}
											else{
												d2=temp;
											}
										}
									}
								}
							}
						}
						//Faire les droites paralleles et perpendiculaire
					}
					if(d1==null || d2==null){
						dp.scene.add(new PointIntersection(new DroiteDeuxPoints((CPoint)selec.get(0), (CPoint)selec.get(1)),new DroiteDeuxPoints((CPoint)selec.get(2), (CPoint)selec.get(3))));
						//Ici c'est un choix :  soit on est laxistes avec l'utilisateur comme ici, soit on décide qu'il faut des droites (ou segements) pour faire une
						//intersection.
						/*JOptionPane.showMessageDialog(null,
								"Cannot draw this element\n" + r, "Error",
								JOptionPane.WARNING_MESSAGE);*/
					}
					else{
						dp.scene.add(new PointIntersection(d1,d2));
						dp.repaint();
					}
				}

			}
		});
		intersection.setBorder(border);
		

		JMenuItem groupe = new JMenuItem("Group", new ImageIcon(getClass().getClassLoader().getResource("img/icon-groupp-icon-16.png")));
		groupe.setAccelerator(KeyStroke.getKeyStroke("P"));
		groupe.setToolTipText("Group together the selected Points");
		groupe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int size = dp.scene.selection.size();
				if(size<2){
					JOptionPane.showMessageDialog(null,
							"You need at least two Points selected to make a group","Error",
							JOptionPane.WARNING_MESSAGE);
				}
				else{
					for(Figure f : dp.scene.selection){
						f.grouper(dp.scene.selection);
					}
				}
			}
		});
		groupe.setBorder(border);
		

		JMenuItem degroupe = new JMenuItem("Degroup", new ImageIcon(getClass().getClassLoader().getResource("img/icon-groupm-icon-16.png")));
		degroupe.setAccelerator(KeyStroke.getKeyStroke("control P"));
		degroupe.setToolTipText("un-group together the selected Points");
		degroupe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				for(Figure f : dp.scene.selection){
					f.degrouper();
				}
				
			}
		});
		groupe.setBorder(border);

		add(barycentre);
		add(intersection);
		addSeparator();
		add(line);
		add(segment);
		addSeparator();
		add(triangle);
		add(circle);
		add(circonscrit);
		add(polygon);
		add(parallele);
		add(perpendiculaire);
		addSeparator();
		add(groupe);
		add(degroupe);
		
	}

}
