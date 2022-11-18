/*
 * Realitza modificacions en Exemple5 perquè realitze l'anterior i li
 * passe la cadena "Hola món". Guarda'l com Exemple6.java
*/

import java.io.*;

public class u2p1e3Exemple6 {

    public static void main(String[] args) {
        Runtime r = Runtime.getRuntime();
        String comando = "java u2p1e3ExempleLectura"; //comando a executar
        Process p = null;

        try {
            p = r.exec(comando);

            //escriptura - envie entrada al procés
            OutputStream os = p.getOutputStream();
            os.write("Hola món\n".getBytes());
            os.flush(); //buide buffer

			//lectura - obté la eixida del procés
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String linia;
            while ((linia = br.readLine()) != null)//llegix una línia
                System.out.println(linia);
            br.close();

        } catch (Exception e) {
            System.out.println("Error en " + comando);
            e.printStackTrace();
        }

		try {
			InputStream er = p.getErrorStream();
			BufferedReader brer = new BufferedReader(new InputStreamReader(er));
			String liner = null;
			while ((liner = brer.readLine()) != null)
				System.out.println("ERROR >" + liner);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

        //comprobació d'error: 0 bé - 1 malament
        int exitVal;
        try {
            exitVal = p.waitFor();
            System.out.println("Valor d'eixida " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }//fi main
}//fi u2p1e3Exemple6
