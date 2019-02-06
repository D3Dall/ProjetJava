package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.ChaineProduction;

public class PanelChaineProd extends JPanel{
	
	private FenetreApplication fenetre;
	
	private ChaineProduction chaine_production;
	
	private JLabel code;
	private JLabel nom;
	private JLabel temps;
	private JLabel niveauActivite;
	
	private JButton detail;
	
	
	public PanelChaineProd(ChaineProduction chaine_production, FenetreApplication fenetre) {
		this.chaine_production = chaine_production;
		this.code = new JLabel(chaine_production.getCodeChaineProduction());
		this.nom = new JLabel(chaine_production.getNom());
		this.temps = new JLabel(chaine_production.getTemps()+"");
		this.niveauActivite = new JLabel(chaine_production.getNiveauActivitee()+"");
		this.detail = new JButton("Détail");
		
		this.add(code);
		this.add(nom);
		this.add(temps);
		this.add(niveauActivite);
		this.add(detail);
		
		this.detail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changer();
			}
		});
	}
	
	public void changer() {
		//this.fenetre.setPanel();
	}
	

}
