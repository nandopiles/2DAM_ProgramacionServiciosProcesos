public class Comptador {
	private int c;

	public Comptador(int c) {
		this.c = c;
	}

	public void incrementa() {
		c++;
	}

	public void decrementa() {
		c--;
	}

	public int valor() {
		return c;
	}

}
