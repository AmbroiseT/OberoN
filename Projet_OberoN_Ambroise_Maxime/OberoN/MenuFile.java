package OberoN;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

/**
 * Le menu deroulant contenant les actions gerant les operations telles que save, open file, new, exit...
 * @author Maxime et Ambroise
 *
 */
public class MenuFile extends JMenu {
	
	private static final long serialVersionUID = 1L;
	
	DrawPanel dp;
	
	/**
	 * Cree et initailise le menu deroulant MenuFile et le lie au DrawPanel en argument.
	 * @param dp Le DrawPanel
	 */
	public MenuFile(final DrawPanel dp) {
		setText("File");
		setBorder(BorderFactory.createCompoundBorder(this.getBorder(),BorderFactory.createEmptyBorder(4, 0,4, 0)));
		setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-blank-file-icon-16.png")));
		this.dp = dp;

		Border border = BorderFactory.createCompoundBorder(this.getBorder(),BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		ImageIcon icon0 = new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-logout-2-icon-16.png"));
		
		JMenuItem fileExit = new JMenuItem("Exit", icon0);
		fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		fileExit.setToolTipText("Exit application");
		fileExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				for (int i = 0; i < dp.scene.mem;i++) {
					try {
						new File(".cache/"+i+".ser").delete();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.exit(0);
			}
		});
		fileExit.setBorder(border);
		
		ImageIcon icon1 = new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-add-file-2-icon-16.png"));
		
		JMenuItem newFile = new JMenuItem("New", icon1);
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newFile.setToolTipText("Create a new document");
		newFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				dp.scene.clear();
				dp.scene.iniRepere(dp);
				dp.repaint();
			}
		});
		newFile.setBorder(border);
		
		ImageIcon icon2 = new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-save-icon-16.png"));
		
		JMenuItem save = new JMenuItem("Save", icon2);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		save.setToolTipText("Save the current scene");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				dp.scene.save();
			}
		});
		save.setBorder(border);
		
		/*ImageIcon icon3 = new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-gear-icon-16.png"));
		
		JMenuItem options = new JMenuItem("Options", icon3);
		options.setAccelerator(KeyStroke.getKeyStroke("control alt O"));
		options.setToolTipText("Configure the software");
		options.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, "Not supported yet", "Warning" , JOptionPane.WARNING_MESSAGE);
			}
		});
		options.setBorder(border);*/
		
		ImageIcon icon4 = new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-open-folder-icon-16.png"));
		
		JMenuItem openFile = new JMenuItem("Open", icon4);
		openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		openFile.setToolTipText("Open a scene");
		openFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				dp.scene.openFile();
				dp.repaint();
			}
		});
		openFile.setBorder(border);
		
		ImageIcon icon5 = new ImageIcon(getClass().getClassLoader().getResource("img/iconmonstr-save-7-icon-16.png"));
		
		JMenuItem saveAs = new JMenuItem("Save As", icon5);
		saveAs.setAccelerator(KeyStroke.getKeyStroke("control alt S"));
		saveAs.setToolTipText("Save the current scene as a new File");
		saveAs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				dp.scene.saveAs();
			}
		});
		saveAs.setBorder(border);

		add(newFile);
		add(openFile);
		addSeparator();
		add(save);
		add(saveAs);
		addSeparator();
		//add(options);
		add(fileExit);
	}

}


