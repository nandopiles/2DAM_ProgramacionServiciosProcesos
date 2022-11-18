import java.util.*;

public class Autobus {
	private String matricula;
	private int velocidad;

	public Autobus(String matricula) {
		this.matricula = matricula;
		velocidad = 50;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int nuevavelo) {
		velocidad = nuevavelo;
	}

}
