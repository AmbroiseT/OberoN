package OberoN;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * La classe principale : c'est elle qui contient le main. Elle etend JFrame et son constructeur se charge d'initialiser tous les elements tels que la scene
 * , la barre de menu, l'icone... 
 * @author Maxime Gourgoulhon et Ambroise Thomine
 *
 */
public class Main_Window extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Construit et initialise l'application.
	 */
	public Main_Window() {
		new CTheme();
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("OberoN");
		ImageIcon i=new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-tools-8-icon-24.png"));
		setIconImage(i.getImage());
		
		DrawPanel dp = new DrawPanel();
		MenuBar m = new MenuBar(dp);
		setJMenuBar(m);
		ToolBar b=new ToolBar(dp);
		b.setFocusable(false);
		dp.add(b);
		setContentPane(dp);
		
		new CCursor();
		setCursor(CCursor.normal);
		
		try {
			new File(".cache").mkdir();
		} catch (Exception e){
			e.printStackTrace();
		}
				
	}

	/**
	 * Cree, via Runnable, une instance de Main_Window et la lance.
	 * @param args rien
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Main_Window w = new Main_Window();
				w.setVisible(true);
			}
		});

	}
}
