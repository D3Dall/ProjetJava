package ihm;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JTable;

import core.Element;
import core.Entreprise;

public class JTableauElements extends JTableau {

	public JTableauElements() {
		String[] titre = {"Code", "Nom", "Quantité", "Prix d'achat", "Prix de Vente"};
		this.tableau = new JTable(this.construireData(), titre) ;
		
		this.setLayout(new BorderLayout());
		this.add(this.tableau.getTableHeader(), BorderLayout.NORTH);
		this.add(this.tableau, BorderLayout.CENTER);
	}
	
	/**
	 * Construit le tableau d'élément en fonction de la liste se trouvant dans Entreprise
	 * @return un tableau de données décrivant les éléments (code, nom, quantité, prix à l'achat, prix de vente)
	 */
	private Object[][] construireData(){ 
		ArrayList<Element> listeElem = Entreprise.enteprise.getListeElement();
		Object[][] tab=new Object[listeElem.size()][5];
		for(int i=0; i<listeElem.size(); i++) {
			Element e = listeElem.get(i);
			tab[i][0] = e.getCodeElement();
			tab[i][1] = e.getNom();
			tab[i][2] = e.getStock().toString();
			if(e.getPrixAchat()==-1) {
				tab[i][3] = "Non connue";
			}else {
				tab[i][3] = e.getPrixAchat() + " €";
			}
			if(e.getPrixVente()==-1) {
				tab[i][4] = "Non connue";
			}else {
				tab[i][4] = e.getPrixVente() + " €";
			}
			
		}
		return tab;
	}

}
