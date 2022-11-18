import java.util.*;

public class Fil extends Thread {
	private ArrayList<Passatger> lista = new ArrayList<Passatger>();

	public Fil(String nombre, ArrayList<Passatger> lista) {
		super(nombre);
		this.lista = lista;
	}

	public void run() {
		int cont1 = 0;
		int cont2 = 0;
		int cont3 = 0;
		int survivor1_si = 0;
		int survivor1_no = 0;
		int survivor2_si = 0;
		int survivor2_no = 0;
		int survivor3_si = 0;
		int survivor3_no = 0;

		Iterator iter = lista.iterator();
		
		while (iter.hasNext()) {
			Passatger Paux = (Passatger)iter.next();
			if (Paux.getclase() == 1) {
				cont1++;
				if (currentThread().getName().equals("Primera classe")){
					if (Paux.getsurvivor() == 'N') {
						survivor1_no++;
					}else {
						survivor1_si++;
					}
				}

			}//fin del if de la primera clase

			if (Paux.getclase() == 2) {
				cont2++;
				if (currentThread().getName().equals("Segona classe")){
					if (Paux.getsurvivor() == 'N') {
						survivor2_no++;
					}else {
						survivor2_si++;
					}
				}
			}//fin del if de la segunda clase
			if (Paux.getclase() == 3) {
				cont3++;
				if (currentThread().getName().equals("Tercera classe")){
					if (Paux.getsurvivor() == 'N') {
						survivor3_no++;
					}else {
						survivor3_si++;
					}
				}
			}//fin del if de la tercera clase
		}//while
		if (currentThread().getName().equals("Primera classe"))
			System.out.println("+En primera classe viatjaven "+cont1+" passatgers. Varen sobreviure "+survivor1_si+"("+((float)survivor1_si/cont1)*100+"%) i van morir "+survivor1_no+"("+((float)survivor1_no/cont1)*100+"%) passatgers.");

		if (currentThread().getName().equals("Segona classe"))
			System.out.println("+En segona classe viatjaven "+cont2+" passatgers. Varen sobreviure "+survivor2_si+"("+((float)survivor2_si/cont2)*100+"%) i van morir "+survivor2_no+"("+((float)survivor2_no/cont2)*100+"%) passatgers.");

		if (currentThread().getName().equals("Tercera classe"))
			System.out.println("+En tercera classe viatjaven "+cont3+" passatgers. Varen sobreviure "+survivor3_si+"("+((float)survivor3_si/cont3)*100+"%) i van morir "+survivor3_no+"("+((float)survivor3_no/cont3)*100+"%) passatgers.");
	}
}
