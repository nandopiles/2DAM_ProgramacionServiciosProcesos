import util.Comu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Nando
 */
public class Client implements Comu {
    static Scanner eb = new Scanner(System.in);
    static Scanner fr = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        boolean stop = false;
        int numberOfStringsSent = 0;
        String word;

        Socket clientSocket = new Socket(HOST, PORT);
        System.out.println(ANSI_GREEN + "\t[+] Connected\n" + ANSI_RESET);

        //enllacem tots els fluxos en el servidor
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);

        do {
            System.out.print("Numbers of Strings to send => ");
            try {
                numberOfStringsSent = fr.nextInt();
                if (numberOfStringsSent > 0)
                    stop = true;
                else
                    System.out.println(ANSI_RED + "\t[-] Value MUST be grater than => 0" + ANSI_RESET);
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "\t[-] Value MUST be a number!" + ANSI_RESET);
                fr.nextLine(); //buidem el buffer
            }
        } while (!stop);

        //li manem el NOMBRE de Strings que passarà el client
        printWriter.println(numberOfStringsSent);

        //bucle que mana els Strings
        for (int i = 0; i < numberOfStringsSent; i++) {
            System.out.printf("String %02d => ", i + 1);
            word = eb.next(); //soles ens pot passar 1 paraula
            eb.nextLine(); //buidem el buffer
            printWriter.println(word);
        }

        //bucle que rep la suma ASCII dels valors
        for (int i = 0; i < numberOfStringsSent; i++) {
            System.out.printf(ANSI_LIGHT_BROWN + "======= ASCII Sum, word %02d => %d =======\n" + ANSI_RESET, i + 1, Integer.parseInt(bufferedReader.readLine()));
        }

        //tanque tot
        bufferedReader.close();
        printWriter.close();
        clientSocket.close();
    }
}
