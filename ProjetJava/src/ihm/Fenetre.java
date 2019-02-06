package ihm;
import javax.swing.JFrame;

public class Fenetre extends JFrame{

	
	public Fenetre(int width, int height, String name) {
		super();
		this.setSize(width, height);
		this.setResizable(true);
		this.setAlwaysOnTop(false);
		this.setLocationRelativeTo(null);
		this.setTitle(name);
		this.setVisible(true);
	}
	public Fenetre(String name) {
		this((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2, (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2, name);
	}
	
	public static void main(String[] args) {
		
	}
	
}
