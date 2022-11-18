public class Passatger {
	private int clase;
	private String apellido;
	private String nombre;
	private int edad;
	private String embarque;
	private char survivor;

	public Passatger(int clase, String apellido, String nombre, int edad, String embarque, char survivor) {
		this.clase = clase;
		this.apellido = apellido;
		this.nombre = nombre;
		this.edad = edad;
		this.embarque = embarque;
		this.survivor = survivor;
	}

	public int getclase() {
		return clase;
	}

	public char getsurvivor() {
		return survivor;
	}

}
