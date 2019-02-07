package ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class JRechercheChaineProduction extends JPanel{
	private JChaineProduction panelchaineprod;
	
	private JLabel code;
	private JTextField codefield;
	
	private JLabel nom;
	private JTextField nomfield;
	
	private JLabel temps;
	private JSpinner tempsfield;
	
	private JButton rechercher;
	private JButton acceder;
	
	public JRechercheChaineProduction(JChaineProduction panelchaineprod) {
		this.panelchaineprod=panelchaineprod;
		
		this.rechercher = new JButton("Rechercher");
		this.acceder = new JButton("Acceder");
		
		this.code = new JLabel("Code : ");
		this.codefield = new JTextField();
		this.nom = new JLabel("Nom : ");
		this.nomfield = new JTextField();
		this.temps = new JLabel("Temps : ");
		this.tempsfield = new JSpinner();
		this.tempsfield.setValue(0);
		
		this.rechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	String code = codefield.getText().toString(); 
        		String nom = nomfield.getText().toString(); 
        		int temps = (int)tempsfield.getValue();
        		/*
        		 * A continuer
        		 * 
        		 */
            }
        });
		
		
		this.setLayout(new GridLayout(2,4));
		
		this.add(this.code);
		this.add(this.codefield);
		this.add(this.nom);
		this.add(this.nomfield);
		this.add(this.temps);
		this.add(this.tempsfield);
		this.add(this.rechercher);
		this.add(this.acceder);
	}
	
}
