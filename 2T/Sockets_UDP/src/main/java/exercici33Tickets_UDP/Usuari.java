package exercici33Tickets_UDP;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Usuari {

    private static Scanner eb = new Scanner(System.in);
    private static Scanner fr = new Scanner(System.in);
    private static final int PORT = 3000;
    private static final String HOST = "localhost";

    public static int menu() {
        System.out.println("""
                ====================
                ----TICKETSERVER----
                ====================
                1- Lista butacas
                2- Reservar butacas
                3- Anular butacas
                4- Salir""");

        return fr.nextInt();
    }

    public static String solicitarInfo() {
        System.out.print("[*] Nombre:\n>");

        return eb.nextLine();
    }

    public static void main(String[] args) throws IOException {
        DatagramSocket dSocket;
        int opc;
        String nombre;

        do {
            opc = menu();
            switch (opc) {
                case 1:

                    break;
                case 2:
                    nombre = solicitarInfo();
                    break;
                case 3:
                    nombre = solicitarInfo();
                    break;
                case 4:
                    System.out.println("[*] Aw");
                    break;
                case 123:
                    System.out.println("[root@root] Apagando Servidor...\n");
                    break;
                default:
                    System.out.println("[-] Opc no válida...\n");
                    break;
            }

            //ENVIAMENT DATAGRAMA
            /*dSocket = new DatagramSocket();
            byte[] peticioClient = (opc + " ").getBytes();
            InetAddress aHost = InetAddress.getByName(HOST);
            DatagramPacket dpEnviament = new DatagramPacket(peticioClient, peticioClient.length, aHost, PORT);
            dSocket.send(dpEnviament); //datagrama enviat

            //==RECEPCIÓ DATAGRAMA==
            byte[] missatgeRebut = new byte[1000];
            DatagramPacket dpResposta = new DatagramPacket(missatgeRebut, missatgeRebut.length);
            dSocket.receive(dpResposta); //rep el datagrama
            System.out.printf("Resposta: %s\n", new String(dpResposta.getData()));*/
        } while (opc != 4);
        //dSocket.close();
    }
}
