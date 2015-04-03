package OberoN;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Represente un segment definit par deux points
 * 
 * @author Ambroise et Maxime
 *
 */
public class Segment extends DroiteDeuxPoints{

	public Segment(CPoint p1, CPoint p2) throws IllegalArgumentException {
		super(p1, p2);
		
	}

	private static final long serialVersionUID = 1L;
	
	@Override
	public void draw(Graphics g, Repere r, Dimension d){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(204, 0, 51));
		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(couleur);
		CPoint[] tab = new CPoint[2];
		tab[0]=point1;
		tab[1]=point2;
		g.drawLine(r.conversionXreel(tab[0].getX()),
				r.conversionYreel(tab[0].getY()),
				r.conversionXreel(tab[1].getX()),
				r.conversionYreel(tab[1].getY()));
		g2d.setStroke(new BasicStroke(1));
	}
}
