package ihm;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;

public class FenetrePrevision extends Fenetre {
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFichier = new JMenu("Imprimer");	
	
	private JPrevision panelprevision;
	private JScrollPane scroll;
	
	
	public FenetrePrevision() {
		super("Prévision");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.scroll = new JScrollPane();
		this.panelprevision = new JPrevision();
		
		this.menuBar.add(this.menuFichier);
		this.add(this.menuBar);
		
		this.scroll.setViewportView(this.panelprevision);
		this.add(this.scroll);
		
	}
	
	
	
	
	
	

}
