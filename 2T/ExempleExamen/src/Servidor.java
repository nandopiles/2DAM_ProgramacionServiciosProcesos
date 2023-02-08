import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Nando
 */
public class Servidor {
    public static final int PORT = 60000;

    public static void main(String[] args) throws IOException {
        //creem un servidor y l'activem
        ServerSocket serverSocket = new ServerSocket(PORT);

        System.out.println("[*] Servidor Actiu => Port: " + PORT);
        while (true) { //bucle infinit perquè sempre responga
            //acceptem al client
            Socket clientSocket = serverSocket.accept();
            //creem un Fil el qual administrará a ixe client connectat i l'iniciem
            FilServidor filServidor = new FilServidor(clientSocket);
            filServidor.start();
            System.out.println("[+] Inici FilServidor");
        }
        //si el bucle acabara hauria de tancar-se el serverSocket
    }
}
