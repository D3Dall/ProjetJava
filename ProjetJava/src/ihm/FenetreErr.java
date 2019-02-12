package ihm;
import javax.swing.*;

public class FenetreErr extends Fenetre{
	private JPanelErr jperr;
	
	public FenetreErr(String name) {
		
		super((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4, (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4, "Fenêtre d'erreur");
		jperr = new JPanelErr(name, this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(jperr);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		Fenetre fer = new FenetreErr("Fenêtre");
	}
	
}
