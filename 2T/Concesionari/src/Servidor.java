import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static final int PORT = 3000;

    public Servidor() throws IOException, ClassNotFoundException {
        ServerSocket ssServidor = new ServerSocket(PORT);

        System.out.println("[*] Servidor en espera de conexió");
        Socket sCliente = ssServidor.accept();
        System.out.println("[+] Conectat\n");

        ObjectInputStream disEntrada = new ObjectInputStream(sCliente.getInputStream());
        System.out.println("[+] Rebut");
        Vehicul v = (Vehicul) disEntrada.readObject();
        System.out.println("Objecte rebut: " + v.toString());

        disEntrada.close();
        ssServidor.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Servidor();
    }
}
