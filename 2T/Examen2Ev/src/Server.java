import util.Comu;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Nando
 */
public class Server implements Comu {
    public static void main(String[] args) throws IOException {
        //creem un servidor y l'activem
        ServerSocket serverSocket = new ServerSocket(PORT);

        System.out.println(ANSI_LIGHT_BROWN + "[*] ACTIVE SERVER => Port: " + PORT + ANSI_RESET);
        while (true) {
            //acceptem al client
            Socket clientSocket = serverSocket.accept();
            //creem un Fil, el qual administrarà a ixe client connectat, després l'iniciem
            ThreadServer filServidor = new ThreadServer(clientSocket);
            filServidor.start();
            System.out.println(ANSI_GREEN + "\t[+] Thread Starts...\n" + ANSI_RESET);
        }
    }
}
