package ihm;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import core.Entreprise;
import erreurs.FichierCSVManquant;
import gestionfichier.FichierCSVEmploiDuTemps;
import gestionfichier.FichierExport;
import gestionfichier.Import;

public class FenetrePrevision extends Fenetre {
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFichier = new JMenu("Exporter");
	private JMenuItem itemimpr = new JMenuItem("Imprimer");
	
	private JPrevision panelprevision;
	private JScrollPane scroll;
	
	
	public FenetrePrevision() {
		super("Prévision");
		
		this.scroll = new JScrollPane();
		this.panelprevision = new JPrevision();
		
		this.menuFichier.add(this.itemimpr);
		this.menuBar.add(this.menuFichier);
		this.setJMenuBar(this.menuBar);
		
		this.itemimpr.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser dialogue = new JFileChooser(new File("."));
    			dialogue.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            	int  ret = dialogue.showOpenDialog(null);    
    			
    			if(ret == JFileChooser.APPROVE_OPTION) {
    				File dir = dialogue.getSelectedFile();
    				
    				try {
    					FichierExport fe = new FichierExport(dir.getPath()+ "/compte_rendu.txt");
    					fe.ecriture();
    					FichierCSVEmploiDuTemps fEDT_personnels = new  FichierCSVEmploiDuTemps(dir.getPath() + "/emploi_du_temps_personnels.csv");
    					FichierCSVEmploiDuTemps fEDT_chaines = new  FichierCSVEmploiDuTemps(dir.getPath() + "/emploi_du_temps_chaines.csv");;
    					fEDT_personnels.ecriture(panelprevision.getModeleTabPEDT());
    					fEDT_chaines.ecriture(panelprevision.getModeleCPEDT());
    				}catch(Exception e) {
    					new FenetreErr(e.getMessage() + " " + e);
    				}
    			
    			}
				
			}
			
		});
		
		
		this.scroll.setViewportView(this.panelprevision);
		this.add(this.scroll);
		
	}
	
	
	
	
	
	

}
