package espiesExercici31_TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Traductor {
    private static final int PORT = 3000;

    private String codificarMissatge(String missatge) {
        return missatge.replaceAll(" el area 51", " Murcia");
    }

    public Traductor() throws IOException {
        String missatge, missatgeCodificat;

        ServerSocket ssServidor = new ServerSocket(PORT);
        System.out.printf("[*] Escolte al port %d...\n", PORT);

        Socket sCliente = ssServidor.accept();
        System.out.println("[+] Serveisc al Client\n");

        DataInputStream disEntrada = new DataInputStream(sCliente.getInputStream());
        DataOutputStream dosEixida = new DataOutputStream(sCliente.getOutputStream());

        missatge = disEntrada.readUTF();
        missatgeCodificat = codificarMissatge(missatge);
        dosEixida.writeUTF(missatgeCodificat);
        System.out.println("[+] Missatge Codificat en èxit");

        System.out.println("\n[*] Tancant Servidor...");
        sCliente.close();
        ssServidor.close();
    }

    public static void main(String[] args) throws IOException {
        new Traductor();
    }
}
