package exercici29_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private final int PORT = 3000;
    private final String HOST = "localhost";

    public Client() throws IOException {
        Scanner eb = new Scanner(System.in);
        String producte;

        Socket sCliente = new Socket(HOST, PORT);
        System.out.println("[*] Conectant al servidor...");
        System.out.println("[+] Conectat");

        DataInputStream disEntrada = new DataInputStream(sCliente.getInputStream());
        DataOutputStream dosEixida = new DataOutputStream(sCliente.getOutputStream());

        System.out.println("Introdueix producte a la llista:");
        producte = eb.nextLine();
        dosEixida.writeUTF(producte);
        System.out.println("[Servidor]: " + disEntrada.readUTF());

        System.out.println("[*] Tancant connexió...");
        sCliente.close();
    }

    public static void main(String[] args) throws IOException {
        new Client();
    }
}
