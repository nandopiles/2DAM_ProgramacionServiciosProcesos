import util.Comu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nando
 */
public class ThreadServer extends Thread implements Comu {
    private final Socket clientSocket;
    private final BufferedReader bufferedReader;
    private final PrintWriter printWriter;

    public ThreadServer(Socket clientSocket) throws IOException {
        //client que anem a administrar
        this.clientSocket = clientSocket;

        //fluxos
        bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        printWriter = new PrintWriter(clientSocket.getOutputStream(), true);

        System.out.println(ANSI_GREEN + "\t[+] Thread Created => " + ANSI_LIGHT_BROWN + clientSocket + ANSI_RESET);
    }

    @Override
    public void run() {
        int numberStringsSent;
        String receivedWord;
        List<char[]> listOfWords = new ArrayList<>();

        try {
            //rebem el nombre de Strings que ens manarà el client
            numberStringsSent = Integer.parseInt(bufferedReader.readLine());

            //bucle que rep els Strings
            for (int i = 0; i < numberStringsSent; i++) {
                receivedWord = bufferedReader.readLine();
                System.out.printf(ANSI_GREEN + "[+] Received word (%s) => %s\n" + ANSI_RESET, clientSocket, receivedWord);
                listOfWords.add(receivedWord.toCharArray()); //convertisc la paraula en un Array de tipus Char
            }
            //bucle que envia la suma dels valors ASCII
            System.out.println("\nSums of => " + ANSI_RED + clientSocket + ANSI_RESET);
            for (char[] word : listOfWords) {
                int castedASCII = 0;
                for (char letter : word) {
                    castedASCII += letter;
                }
                System.out.printf(ANSI_BLUE + "======= Sum => %d =======\n" + ANSI_RESET, castedASCII);
                printWriter.println(castedASCII);
            }
            System.out.println(ANSI_LIGHT_BROWN + "[*] END => " + clientSocket + ANSI_RESET);

            //tanque tot
            bufferedReader.close();
            printWriter.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(ANSI_RED + "[-] ERROR => Client Disconnected" + ANSI_RESET);
        }
    }
}
