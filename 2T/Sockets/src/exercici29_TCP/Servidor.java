package exercici29_TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Servidor {

    private static final int PORT = 3000;

    private static String getDataActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();

        return sdf.format(date);
    }

    public Servidor() throws IOException {
        ServerSocket ssServidor = new ServerSocket(PORT);
        String producte;
        File llistaCompra;
        PrintWriter pw;

        System.out.printf("[*] Escolte al port %d...\n", PORT);

        while (true) {
            Socket sCliente = ssServidor.accept();
            System.out.println("[+] Serveisc al client");

            DataInputStream disEntrada = new DataInputStream(sCliente.getInputStream());
            DataOutputStream dosEixida = new DataOutputStream(sCliente.getOutputStream());

            producte = disEntrada.readUTF();
            llistaCompra = new File("./src/exercici29_TCP/output/" + getDataActual() + ".txt");
            pw = new PrintWriter(new FileWriter(llistaCompra, true));
            pw.write(producte + "\n");
            pw.close();
            System.out.printf("[Client] Producte afegit => \"%s\"\n", producte.toUpperCase());
            dosEixida.writeUTF("Producte \"" + producte.toUpperCase() + "\" afegit en èxit\n");
        }
    }

    public static void main(String[] args) throws IOException {
        new Servidor();
    }
}
