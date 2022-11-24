
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 *
 * @author 7J
 */
public class Client {

    static final String HOST = "localhost";
    static final int PORT = 5000;

    public Client() {
        try {
            //es crea el socket
            Socket sCliente = new Socket(HOST, PORT);
            //cree un contenidor aon vaig a rebre la info que me mana el servidor
            DataInputStream flux = new DataInputStream(sCliente.getInputStream());
            //llegim la cadena del fluix and readUTF i mostre per pantalla
            System.out.println(flux.readUTF());
            sCliente.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
    }
}
