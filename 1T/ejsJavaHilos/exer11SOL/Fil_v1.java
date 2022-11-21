public class Fil_v1 extends Thread{

	public Fil_v1 (String nombre) {
		super(nombre);
	}
	
	public void run() {
		for (int i=0; i<5; i++)
		System.out.println(getName() + " amb valor=" +i);
	}
	public static void main(String[] args) {
		Fil h1 = new Fil("Fil 1");
		Fil h2 = new Fil("Fil 2");
		Fil h3 = new Fil("Fil 3");
		
		h1.start();
		h2.start();
		h3.start();
		
		System.out.println("3 fils iniciats...");
	}
}
