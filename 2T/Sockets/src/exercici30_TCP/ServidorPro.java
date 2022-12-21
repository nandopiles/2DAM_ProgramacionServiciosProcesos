package exercici30_TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ServidorPro {

    private static final int PORT = 3000;

    private static String getDataActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();

        return sdf.format(date);
    }

    public ServidorPro() throws IOException {
        String producte;
        File llistaCompra;
        PrintWriter pw;

        ServerSocket ssServidor = new ServerSocket(PORT);
        System.out.printf("[*] Escolte al port %d...\n", PORT);

        while (true) {
            Socket sCliente = ssServidor.accept();
            System.out.println("[+] Serveisc al client\n");

            DataInputStream disEntrada = new DataInputStream(sCliente.getInputStream());
            DataOutputStream dosEixida = new DataOutputStream(sCliente.getOutputStream());

            producte = disEntrada.readUTF();
            if (!Objects.equals(producte, "eixir")) {
                llistaCompra = new File("./src/exercici30_TCP/output/" + getDataActual() + ".txt");
                pw = new PrintWriter(new FileWriter(llistaCompra, true));
                pw.write(producte + "\n");
                pw.close();
                System.out.printf("[Client] Producte afegit => \"%s\"\n", producte.toUpperCase());
                dosEixida.writeUTF("Producte \"" + producte.toUpperCase() + "\" afegit en èxit\n");
            } else {
                System.out.printf("[Client] Ordre => \"%s\"\n", producte.toUpperCase());
                dosEixida.writeUTF("Servidor tancat\n");
                System.out.println("\n[*] Tancant Servidor...");
                sCliente.close();
                ssServidor.close();
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ServidorPro();
    }
}
