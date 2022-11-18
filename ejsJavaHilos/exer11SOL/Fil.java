public class Fil extends Thread{
	
	public Fil (String nombre) {
		super(nombre);
	}
	
	public void run() {
		for (int i=0; i<5; i++)
		System.out.println(getName() + " amb valor=" +i);
	}
	
}
