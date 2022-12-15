import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static String plenarInfo() {
        Scanner fr = new Scanner(System.in);
        String info = "";
        int dia, mes, any;

        System.out.println("\tIntroducción de la data:");
        do {
            System.out.println("Introducció del dia (1/31):");
            dia = fr.nextInt();
            if (dia < 1 || dia > 31) {
                System.out.println("(-) Dia no vàlid...");
            }
        } while (dia < 1 || dia > 31);
        info += dia;
        do {
            System.out.println("Introducció del mes (1/12):");
            mes = fr.nextInt();
            if (mes < 1 || mes > 12) {
                System.out.println("(-) Mes no vàlid...");
            }
        } while (mes < 1 || mes > 31);
        info += "-" + mes;
        do {
            System.out.println("Introducció de l'any (>1800):");
            any = fr.nextInt();
            if (any < 1800) {
                System.out.println("(-) Any no vàlid...");
            }
        } while (any < 1800);
        info += "-" + any;
        return info;
    }

    public static void main(String[] args) {
        DatagramSocket dSocket = null;
        byte[] infoData, missatgeRebut = new byte[1000];

        try {
            //ENVIAMENT DATAGRAM
            dSocket = new DatagramSocket();
            infoData = plenarInfo().getBytes();
            InetAddress host = InetAddress.getByName("localhost");
            int serverPort = 3000;
            DatagramPacket dpEnviament = new DatagramPacket(infoData, infoData.length, host, serverPort);
            dSocket.send(dpEnviament);

            //RECEPCIÓ
            DatagramPacket dpResposta = new DatagramPacket(missatgeRebut, missatgeRebut.length);
            dSocket.receive(dpResposta);
            System.out.printf("[Servidor]: %s\n", new String(dpResposta.getData()));
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e1) {
            System.out.println("IO: " + e1.getMessage());
        } finally {
            if (dSocket != null) {
                dSocket.close();
            }
        }
    }
}
