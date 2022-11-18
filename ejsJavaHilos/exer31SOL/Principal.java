import java.util.*;

public class Principal {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Comptador cont = new Comptador(100);
		System.out.println("Tenim donat d'alta un comptador = 100");
		System.out.println("Introduix quantitat a sumar:");
		int cant_sumar = s.nextInt();
		System.out.println("Introduix quantitat a restar:");
		int cant_restar = s.nextInt();
		Filsuma hsuma = new Filsuma("Filsuma",cont,cant_sumar);
		Filresta hresta = new Filresta("Filresta",cont,cant_restar);
		hsuma.start();
		hresta.start();
	}

}
