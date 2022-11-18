public class Filsuma extends Thread {
	private Comptador comptador;
	private int cantidad;

	public Filsuma(String n, Comptador c, int cantidad) {
		super(n);
		comptador = c;
		this.cantidad = cantidad;
	}

	public void run() {
		synchronized(comptador) {
			for (int j=0; j < cantidad; j++) {
				comptador.incrementa();
				try {
					sleep(1000);
				}catch(InterruptedException e) {}
				System.out.println(getName() + " comptador val " + comptador.valor());
			}
		}//synchronized
	}
}
