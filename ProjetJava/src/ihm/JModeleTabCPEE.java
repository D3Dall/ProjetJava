package ihm;

import java.util.ArrayList;

import core.Couple;
import core.Element;

public class JModeleTabCPEE extends JModeleTabCPE{
	private final String[] entetes =  {"Code", "Nom", "Quantité disponible", "Quantité necessaire"};
	
	public JModeleTabCPEE(ArrayList<Couple<Element, Float>> dic) {
		super(dic);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.entetes.length;
	}
	
	public String getColumnName(int col){ 
	      return this.entetes[col];
 }

}
