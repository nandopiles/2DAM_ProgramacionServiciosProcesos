import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Nando
 */
public class Servidor {
    public static void main(String[] args) throws IOException {
        final int PORT = 60000;
        ServerSocket server = new ServerSocket(PORT);

        System.out.println("[*] Servidor actiu => P: " + PORT);
        while (true) {
            Socket client = new Socket();
            client = server.accept();
            FilServidor fil = new FilServidor(client);
            fil.start();
            System.out.println("[*] Inici FilServidor");
        }
    }
}
