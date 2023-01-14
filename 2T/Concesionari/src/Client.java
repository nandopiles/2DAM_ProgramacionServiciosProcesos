import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final int PORT = 3000;
    private final String HOST = "localhost";

    public Client() throws IOException {
        Socket sCliente = new Socket(HOST, PORT);

        System.out.println("[+] Conectat");
        ObjectOutputStream dosEntrada = new ObjectOutputStream(sCliente.getOutputStream());

        Vehicul v = new Vehicul("21009570J", "43213HFD", "Opel",
                "Corsa", "Gasolina", 2002);

        dosEntrada.flush();
        dosEntrada.writeObject(v);
        System.out.printf("[+] Enviat vehícul de \"%s\"\n", v.getDniClient());
    }

    public static void main(String[] args) throws IOException {
        new Client();
    }
}
