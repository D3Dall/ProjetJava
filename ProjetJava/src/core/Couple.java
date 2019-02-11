package core;

public class Couple<A, B> {
	
	private A objeta;
	private B objetb;
	
	public Couple(A objeta, B objetb) {
		this.objeta = objeta;
		this.objetb = objetb;
	}

	public A getObjeta() {
		return objeta;
	}

	public B getObjetb() {
		return objetb;
	}
}
