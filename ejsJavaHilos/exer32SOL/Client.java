public class Client {
	private String nombre;
	private int edad;
	private char spanish;

	public Client(String nombre, int edad, char spanish) {
		this.nombre = nombre;
		this.edad = edad;
		this.spanish = spanish;
	}

	public void info_client() {
		System.out.println("\t-"+nombre+"("+spanish+"), "+edad);
	}

	public String getNombre() {
		return nombre;
	}
}
