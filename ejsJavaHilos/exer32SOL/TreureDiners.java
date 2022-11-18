public class TreureDiners extends Thread {

	private Compte c;
	String nombre;

	public TreureDiners(String n, Compte c) {
		this.nombre = n;
		this.c = c;
	}

	public void run() {
		for (int x = 0; x < 3; x++) {
			c.RetirarDinero(10, nombre);
		}
	}
}
