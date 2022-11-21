import java.util.*;

public class Principal {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int numCotxes=0;
		String marcaCotxe="";
		ArrayList<Cotxe> lista_Cotxes = new ArrayList<Cotxe>();

		System.out.println("Introduix numero de cotxes a competir:");
		numCotxes = s.nextInt();
		s.nextLine();
		for (int i=1; i <= numCotxes; i++) {
			System.out.println("Introduix marca del cotxe "+i+": ");
			marcaCotxe = s.nextLine();
			Cotxe c = new Cotxe(marcaCotxe,i);
			lista_Cotxes.add(c);
		}

		System.out.println("Que comence la carrera!");
		Iterator iter = lista_Cotxes.iterator();
		while (iter.hasNext()) {
			Cotxe caux = (Cotxe)iter.next();
			caux.start();
		}
	}
}
