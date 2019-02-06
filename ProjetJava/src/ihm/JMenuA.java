package ihm;

import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;

public class JMenuA extends JPanel{
	
	private FenetreApplication fenetre;

	private JButton prevision;
	private JTree arbre;
	
	
	public JMenuA(FenetreApplication fenetre) {
		this.fenetre=fenetre;
		initialiserComposants();
		this.add(this.arbre);
	}
	
	private void initialiserComposants() {
		this.prevision = new JButton();
		
		
		//Gestion Elements/Stocks
		DefaultMutableTreeNode noeudStock = new DefaultMutableTreeNode("Gestion des stocks");
		DefaultMutableTreeNode feuilleStockListe = new DefaultMutableTreeNode("Etat des stocks");
		noeudStock.add(feuilleStockListe);
		
		//Gestion des Chaines de production
		DefaultMutableTreeNode noeudCProduction = new DefaultMutableTreeNode("Gestion des chaînes de production");
		DefaultMutableTreeNode feuilleCProductionRech = new DefaultMutableTreeNode("Rechercher une chaine de production");
		DefaultMutableTreeNode feuilleCProductionListe = new DefaultMutableTreeNode("Liste des chaines de production");
		noeudCProduction.add(feuilleCProductionRech);
		noeudCProduction.add(feuilleCProductionListe);
		
		//Gestion des achats
		DefaultMutableTreeNode noeudListeAchat = new DefaultMutableTreeNode("Gestion des Achats");
		DefaultMutableTreeNode feuilleAchatListe = new DefaultMutableTreeNode("Liste des achats");
		noeudListeAchat.add(feuilleAchatListe);
		
		//Racine
		DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Entreprise");
		racine.add(noeudStock);
		racine.add(noeudCProduction);
		racine.add(noeudListeAchat);
		
		this.arbre = new JTree(racine);
		
		//Ajout du listener
		this.arbre.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(java.awt.event.MouseEvent evt) {
				 changerPanel();
			 }
		});	
	}
	
	public void changerPanel() {
		try{
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.arbre.getSelectionPath().getLastPathComponent();
            String choix = node.getUserObject().toString();
            javax.swing.JPanel jp;
            switch(choix){
                case "Etat des stocks" :
                	this.fenetre.setPanel(new JEtatStock());
                    break;
                case "Liste des chaines de production" :
                	this.fenetre.setPanel(new JListeProductions(this.fenetre));
            }
	        }catch(NullPointerException e){
	            
	        }
        }
}
