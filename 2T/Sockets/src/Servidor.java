
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author 7J
 */
public class Servidor {

    static final int PORT = 5000;

    public Servidor() {
        try {
            //crea socket i espera
            ServerSocket ssServidor = new ServerSocket(PORT);
            System.out.println("Escolte al port " + PORT);

            for (int numCli = 0; numCli < 3; numCli++) {
                Socket sCliente = ssServidor.accept();
                System.out.println("Serveisc al client");
                //crea contenidor per a escriure lo que va a ixir al client
                DataOutputStream flux = new DataOutputStream(sCliente.getOutputStream());
                //envie al client
                flux.writeUTF("Hola client");
                sCliente.close();
            }
            System.out.println("Massa clients per hui");
            ssServidor.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
    }
}
