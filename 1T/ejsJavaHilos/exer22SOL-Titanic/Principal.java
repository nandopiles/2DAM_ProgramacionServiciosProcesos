import java.io.*;
import java.util.*;

public class Principal {

	public static void main(String[] args) {
		File file_entrada= new File("Titanic.csv");
		ArrayList<Passatger> lista_passatgers = new ArrayList<Passatger>();

		System.out.println("BENVINGUT/DA AL TITANIC");

		//PRIMERA PART: "PARSEAMOS" EL FITXER CSV A UN ARRAYLIST
		try {
			String linea="";
			Scanner sc = new Scanner(file_entrada);
			sc.nextLine(); //leemos la cabecera
			while (sc.hasNext()){
				linea = sc.nextLine();
				String[] partes = linea.split(",");
				Passatger p = new Passatger(Integer.parseInt(partes[0]),partes[1],partes[2],Integer.parseInt(partes[3]),partes[4],partes[5].charAt(0));
				lista_passatgers.add(p);
			}
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}

		//SEGONA PART: LLANCEM ELS 3 FilS
		Fil h1 = new Fil("Primera classe",lista_passatgers);
		Fil h2 = new Fil("Segona classe",lista_passatgers);
		Fil h3 = new Fil("Tercera classe",lista_passatgers);
		h1.setPriority(Thread.MAX_PRIORITY);
		h2.setPriority(Thread.MIN_PRIORITY);
		h3.setPriority(Thread.MIN_PRIORITY);
		h1.start();
		h2.start();
		h3.start();

//		try {
			//HE LLEVAT EL join!!!
/*			h1.join();
			h2.join();
			h3.join();*/
//		}catch(InterruptedException e) {}

		System.out.println("FI DE LA TRAVESIA");
	}
}
