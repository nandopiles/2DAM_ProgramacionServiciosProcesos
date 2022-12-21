package espiesExercici31_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Espia {
    private static final int PORT = 3000;
    private final String HOST = "localhost";
    public Espia() throws IOException {
        Scanner eb = new Scanner(System.in);
        String missatge;

        Socket sCliente = new Socket(HOST, PORT);
        System.out.println("[*] Conectant al servidor...");
        System.out.println("[+] Conectat");

        DataInputStream disEntrada = new DataInputStream(sCliente.getInputStream());
        DataOutputStream dosEixida = new DataOutputStream(sCliente.getOutputStream());

        System.out.print("Introdueix missatge a codificar:\n> ");
        missatge = eb.nextLine();
        dosEixida.writeUTF(missatge);
        System.out.println("\n[Traductor]: " + disEntrada.readUTF());

        System.out.println("[*] Tancant Client...");
        sCliente.close();
    }

    public static void main(String[] args) throws IOException {
        new Espia();
    }
}
