import java.util.*;

public class Compte {
	private int id;
	static int ident_global=0;
	private ArrayList<Client> beneficiarios;
	private int saldo;
	private boolean deudora;

	public Compte() {
		beneficiarios = new ArrayList<Client>();
		saldo = 100;
		deudora = false;
		ident_global++;
		id = ident_global;
		System.out.println("Anem a donar d'alta el compte: "+id);
	}

	public void anyadir_beneficiario(Client c) {
		beneficiarios.add(c);
	}

	public int getSaldo() {
		return saldo;
	}

	public int getId() {
		return id;
	}

	public boolean getDeudora() {
		return deudora;
	}

	public synchronized void RetirarDinero(int cantidad, String nombre) {
		if (getSaldo() >= cantidad) {
			System.out.println(nombre + ": ES VA A RETIRAR SALDO. (Actual es: "+getSaldo()+"€).");

			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {}

			saldo = saldo - cantidad;
			System.out.println("\t" + nombre + " retira "+cantidad+"€. Saldo actualitzat es: ("+getSaldo()+" €).");
		}else {
			System.out.println(nombre + " no pot retirar diners. No hi ha saldo ("+getSaldo()+" €.)");
		}

		if (getSaldo() < 0) {
			System.out.println("Saldo negatiu=>"+ getSaldo());
			deudora = true;
		}
	}

	public void info_cuenta() {
		if (deudora == false) {
			System.out.println("*** COMPTE: "+id+", ESTAT: ACTIVA, SALDO: "+saldo+" €.***");
		}else {
			System.out.println("*** COMPTE: "+id+", ESTAT: DEUDORA, SALDO: "+saldo+" €.***");
		}
		System.out.println("+Beneficiaris:");

		for (int i=0; i < beneficiarios.size();i++) {
			Client cli = (Client)beneficiarios.get(i);
			cli.info_client();
		}
	}

	public ArrayList<Client> devolver_beneficiarios(){
		return beneficiarios;
	}
}
