package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelErr extends JPanel {
	
	private JLabel jl;
	private JButton bouton;
	private JFrame frame;
	
	public JPanelErr(String name, JFrame frame) {
		jl = new JLabel(name, JLabel.CENTER);
		this.frame = frame;
		bouton = new JButton("OK");
		bouton.setPreferredSize(new Dimension());
		bouton.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
			
		});
		this.setLayout(new GridLayout(2,1));
		this.add(jl);
		this.add(bouton);
	}

	
}
