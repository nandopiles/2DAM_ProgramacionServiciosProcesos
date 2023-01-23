import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private ServerSocket server = null;
    private Socket client = null;
    private ObjectInputStream inStream = null;
    private ObjectOutputStream outputStream = null;
    public static final int PORT = 4445;

    public void comunica() {
        try {
            server = new ServerSocket(PORT);
            System.out.println("Servidor en espera de connexió...");
            client = server.accept();

            inStream = new ObjectInputStream(client.getInputStream());
            Persona persona = (Persona) inStream.readObject();

            System.out.println("[+] Rebut");
            System.out.printf("Objecte rebut: %s - %d\n", persona.getNom(), persona.getEdad());

            outputStream = new ObjectOutputStream(client.getOutputStream());
            persona.setNom(persona.getNom() + " ha estat al servidor");
            System.out.printf("Objecte modificat: %s - %d\n", persona.getNom(), persona.getEdad());

            outputStream.writeObject(persona);
            System.out.println("[+] Enviat");

            inStream.close();
            outputStream.close();
            client.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.comunica();
    }
}
