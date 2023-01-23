import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final int PORT = 3000;
    private final String HOST = "localhost";

    public Client() throws IOException {
        Socket sCliente = new Socket(HOST, PORT);

        System.out.println("[+] Connectat");
        ObjectOutputStream dosEntrada = new ObjectOutputStream(sCliente.getOutputStream());

        Vehicul vehicul = new Vehicul("21009570J", "43213HFD", "Opel",
                "Corsa", "Gasolina", 2002);

        dosEntrada.flush();
        dosEntrada.writeObject(vehicul);
        System.out.printf("[+] Enviat vehícul de \"%s\"\n", vehicul.getDniClient());

        File file = new File("vehicul.txt");
        PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));
        printWriter.println(vehicul);
        System.out.println("[+] Vehicul escrit al fitxer " + file.getName());

        dosEntrada.close();
        printWriter.close();
        sCliente.close();
    }

    public static void main(String[] args) throws IOException {
        new Client();
    }
}
