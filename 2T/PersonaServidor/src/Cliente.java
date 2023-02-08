import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {
    private Socket client = null;
    private ObjectOutputStream outputStream = null;
    private ObjectInputStream inputStream = null;
    //private boolean estaConnectat = false;
    private static final String HOST = "localhost";
    private static final int PORT = 4445;

    public Cliente() {
    }

    public void communicate() {
        //while (!estaConnectat) {
        try {
            client = new Socket(HOST, PORT);
            System.out.println("[*] Connectat");
            //estaConnectat = true;

            outputStream = new ObjectOutputStream(client.getOutputStream());

            Persona persona = new Persona("Pepe", 25);
            System.out.printf("Objecte a enviar: %s - %d\n", persona.getNom(), persona.getEdad());

            outputStream.flush();

            outputStream.writeObject(persona);
            System.out.println("[+] Enviat");

            inputStream = new ObjectInputStream(client.getInputStream());
            Persona persona1 = (Persona) inputStream.readObject();

            System.out.printf("Objecte rebut: %s - %d\n", persona1.getNom(), persona1.getEdad());

            outputStream.close();
            inputStream.close();
            client.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //}

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.communicate();
    }
}
