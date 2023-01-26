import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Nando
 */
public class Client {
    public static void main(String[] args) {
        String cadena, cadenaRebuda;
        try {
            Socket client = new Socket("localhost", 60000);

            PrintWriter printWriter = new PrintWriter(client.getOutputStream(), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));

            BufferedReader inTeclat = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Introdueix cadenes i rep en majúscula. Introdueix * per a finalitzar:");
            do {
                System.out.print("Introdueix cadena => ");
                cadena = inTeclat.readLine();
                printWriter.println(cadena);
                cadenaRebuda = bufferedReader.readLine();
                System.out.println("[+] Rep => " + cadenaRebuda);
            } while (!(cadena.contentEquals("*")));

            System.out.println("[+] Fi comunicació");
            printWriter.close();
            bufferedReader.close();
            inTeclat.close();
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
