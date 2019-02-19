package ihm;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import core.Entreprise;
import core.Prevision;
import erreurs.FichierCSVManquant;
import gestionfichier.FichierCSVChaineDeProduction;
import gestionfichier.FichierCSVElement;
import gestionfichier.FichierCSVPersonnel;
import gestionfichier.Import;

public class FenetreApplication extends Fenetre{
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFichier = new JMenu("Fichier");
	
	private JMenuItem itemOuvrir = new JMenuItem("Ouvrir");
	private JMenuItem itemRemettre0 = new JMenuItem("Remettre à zéro");
	private JMenuItem prevision = new JMenuItem("Prévision");
	private JMenuItem itemQuitter = new JMenuItem("Quitter");
	
	private JMenuA menu;
	private JPanel panel;
	
	private JSplitPane split;
	
	private JScrollPane scrollmenu;
	private JScrollPane scrollpanel;
	
	public FenetreApplication() {
		super("Gestion");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Creation des deux sous-panel
		this.menu = new JMenuA(this);
		this.panel = new JPanel();
		
		//Creation des scroll
		this.scrollmenu = new JScrollPane(this.menu);
		this.scrollpanel = new JScrollPane(this.panel);
		
		//creation du split
		this.split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.scrollmenu, this.scrollpanel);
				
		//ajout du split au content
		this.getContentPane().add(this.split);	
		
		this.panel.setBackground(new Color(255, 255, 255));
		this.menu.setBackground(new Color(255, 255, 255));
		
		constructionMenu();		
		this.setVisible(true);
	}
	
	public void constructionMenu() {
		this.menuFichier.add(this.itemOuvrir);
		this.menuFichier.add(this.itemRemettre0);
		this.menuFichier.add(this.prevision);
		this.menuFichier.add(this.itemQuitter);
		this.menuBar.add(this.menuFichier);
		this.setJMenuBar(this.menuBar);
		
		this.itemOuvrir.addActionListener(new java.awt.event.ActionListener() {
			/**
			 * Ouvrir et charger les fichiers
			 */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	JFileChooser dialogue = new JFileChooser(new File("."));
    			dialogue.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            	int  ret = dialogue.showOpenDialog(null);    
    			
    			if(ret == JFileChooser.APPROVE_OPTION) {
    				File dir = dialogue.getSelectedFile();
    				
    				try {
    					Import.Importer(dir);
    				}catch(FichierCSVManquant e) {
    					new FenetreErr(e.getMessage() + " " + e);
    				}
    			
    			}
            }
        });
		
		this.itemQuitter.addActionListener(new java.awt.event.ActionListener() {
			/**
			 * Quitte l'application
			 */
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		});
		
		this.itemRemettre0.addActionListener(new java.awt.event.ActionListener() {
			/**
			 * Remise à zero de l'application
			 */
			public void actionPerformed(ActionEvent e) {
				Entreprise.entreprise.remettreA0();
			}
			
		});
		this.prevision.addActionListener(new java.awt.event.ActionListener() {
			/**
			 * Remise à zero de l'application
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					Prevision.Prevision(Entreprise.entreprise);
					new FenetrePrevision();
				} catch (Exception erreur) {
					new FenetreErr(erreur.getMessage());
				}
				
			}
			
		});
		
		
	}
	
	public void setPanel(JPanel jp) {
		this.scrollpanel.setViewportView(jp);
    	this.repaint();
    	this.revalidate();
	}
	
	public static void main(String[] args) {
		Fenetre app = new FenetreApplication();
	}
	
	
	
	
	
	
	
	
	
}
