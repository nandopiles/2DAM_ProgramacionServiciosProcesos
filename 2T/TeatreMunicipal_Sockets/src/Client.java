import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int PORT = 5000;

    public Client(String host) throws IOException {
        Scanner eb = new Scanner(System.in);
        Socket sCliente;
        String cadena;

        sCliente = new Socket(host, PORT);
        System.out.println("(*) Conectant al servidor...");
        System.out.println("(+) Conectat");

        DataInputStream disEntrada = new DataInputStream(sCliente.getInputStream());
        DataOutputStream dosEixida = new DataOutputStream(sCliente.getOutputStream());

        do {
            System.out.print("Introdueix:\n-> ");
            cadena = eb.nextLine();
            dosEixida.writeUTF(cadena);
            System.out.println("(Servidor): " + disEntrada.readUTF());
        } while (!cadena.equals("Aw"));

        System.out.println("(+) Tancant connexió...");
        sCliente.close();
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("(-) Es necessita nom del servidor...");
            return; //S'acaba el programa
        }
        new Client(args[0]);
    }
}
