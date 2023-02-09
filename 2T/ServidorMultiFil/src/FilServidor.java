import java.io.*;
import java.net.Socket;

/**
 * @author Nando
 */
public class FilServidor extends Thread {
    private Socket client;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public FilServidor(Socket client) throws IOException {
        System.out.println("[+] Cree FilServidor");
        this.client = client;

        printWriter = new PrintWriter(client.getOutputStream(), true);
        bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    @Override
    public void run() {
        String cadena = "";
        boolean stop = false;

        while (!cadena.trim().equals("*") && !stop) {
            System.out.println("[+] En fil: comunique en => " + client);
            try {
                cadena = bufferedReader.readLine();
                System.out.println("[+] En fil: llig cadena => " + cadena);
                printWriter.println(cadena.trim().toUpperCase());
            } catch (IOException e) {
                System.out.println("[-] ERROR => client desconectat");
                stop = true;
            }
        }
        System.out.println("[+] FI AMB " + client);
    }
}
