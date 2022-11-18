import java.util.*;

public class Principal {
	public static void main(String[] args) {
		ArrayList<Compte> cuentas = new ArrayList<Compte>();
		Scanner s = new Scanner(System.in);
		int opcion=0;

		while (opcion != 4) {
			opcion = leer_opcion(s);
			switch(opcion) {
				case 1: Alta_cuenta(s, cuentas);
						break;
				case 2: Resumen_cuentas(cuentas);
						break;
				case 3: Sacar_dinero(s, cuentas);
						break;
			}
		}
		System.out.println("Gracies, continue endeutant-se...");
	}

	public static int leer_opcion(Scanner s) {
		int opcion=0;

		System.out.println("------------------------------");
		System.out.println("1-Donar d'alta un compte");
		System.out.println("2-Resum de comptes");
		System.out.println("3-Traure diners d'un compte");
		System.out.println("4-Eixir");
		System.out.println("------------------------------");

		opcion = s.nextInt();
		return opcion;
	}

	public static void Alta_cuenta(Scanner s, ArrayList<Compte> cuentas) {
		Compte c = new Compte();
		int numbene;
		String nombene;
		int edadbene;
		char spanishbene;

		System.out.println("Introduïsca nombre de beneficiaris:");
		numbene = s.nextInt();
		s.nextLine();

		for (int i=1; i <= numbene; i++) {
			System.out.println("Introduïsca nom del beneficiari "+i+":");
			nombene = s.nextLine();
			System.out.println("Introduïsca edat del beneficiari "+i+":");
			edadbene = s.nextInt();
			s.nextLine();
			System.out.println("¿"+nombene+" té nacionalitat espanyola (s/n)?");
			spanishbene = s.nextLine().charAt(0);
			Client cli = new Client(nombene, edadbene, spanishbene);
			c.anyadir_beneficiario(cli);
		}
		cuentas.add(c);
	}

	public static void Resumen_cuentas(ArrayList<Compte> cuentas) {
		System.out.println("----Resum global de comptes----");
		Iterator iter = cuentas.iterator();
		while (iter.hasNext()) {
			Compte cuen = (Compte)iter.next();
			cuen.info_cuenta();
		}
		System.out.println("");
	}
	

	public static void Sacar_dinero(Scanner s, ArrayList<Compte> cuentas) {
		boolean cuenta_encontrada = false;
		int IdLeido = 0;
		Compte caux = null;
		TreureDiners h1 = null;

		//paso1 - validamos que la cuenta exista
		while (cuenta_encontrada == false) {
			System.out.println("Introduïsca ID del compte d'on vol traure diners:");
			IdLeido = s.nextInt();
			Iterator iter = cuentas.iterator();
			while (iter.hasNext()) {
				caux = (Compte)iter.next();
				if (caux.getId() == IdLeido) {
					cuenta_encontrada = true;
					break;
				}
			}
			if (cuenta_encontrada == false) {
				System.out.println("El compte amb ID "+IdLeido+" no existeix...");
			}
		}

		//paso2 - hacer que todos los beneficiarios saquen dinero (cada uno 30 euros en billetes de 10 €)
		ArrayList<Client> lista_beneficiarios = caux.devolver_beneficiarios();
		for (int i=0; i < lista_beneficiarios.size(); i++) {
			h1 = new TreureDiners(lista_beneficiarios.get(i).getNombre(),caux);
			h1.start();
		}

		//paso3 - espero a que los clientes saquen el dinero (que finalicen los hilos) para mostrar el menu
		try {
			h1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
