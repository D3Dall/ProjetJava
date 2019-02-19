package ihm;
import javax.swing.*;

public class FenetreErr extends Fenetre{
	private JPanelErr jperr;
	
	public FenetreErr(String erreur) {
		
		super((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4, (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4, "ERREUR");
		jperr = new JPanelErr(erreur, this);
		this.add(jperr);
		this.setVisible(true);
	}
	
}
