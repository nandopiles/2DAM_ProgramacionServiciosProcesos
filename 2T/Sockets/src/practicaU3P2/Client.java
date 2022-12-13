package practicaU3P2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class Client {

    static final int PORT = 5000;

    public Client(String host) {
        Scanner eb = new Scanner(System.in);
        try {
            // ES CREA EL SOCKET
            Socket sCliente = new Socket(host, PORT);
            System.out.println("Conectant al servidor...");
            System.out.println("Conectat.");

            // ASSOCIE FLUX D'ENTRADA AL SOCKET DEL CLIENT
            DataInputStream disEntrada = new DataInputStream(sCliente.getInputStream());
            // ASSOCIE FLUX EIXIDA DE DADES AL SOCKET DEL CLIENT
            DataOutputStream dosEixida = new DataOutputStream(sCliente.getOutputStream());

            String cadena;
            do {
                System.out.println("Introdueix el que vullgues enviar al servidor");
                cadena = eb.nextLine();
                dosEixida.writeUTF(cadena);
                System.out.println("Servidor: " + disEntrada.readUTF());
            } while (!cadena.equals("Adeu"));

            // TANQUE EL SOCKET
            System.out.println("Tancant connexió...");
            sCliente.close();
        } catch (IOException e) {
            System.out.println("Error: no s'ha pogut connectar a " + host + ":" + PORT);
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: deus introduir un parametre que es el HOST");
            return;
        }
        new Client(args[0]);
        return;
    }
}
