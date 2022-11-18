
import java.io.*;

public class u2p1e3ExempleLectura {

    public static void main(String[] args) {
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        String texte;

        try {
            System.out.println("Introduix una cadena...");
            texto = br.readLine();
            System.out.println("Cadena escrita: " + texte);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//fi main
}//fin u2p1e3ExempleLectura
