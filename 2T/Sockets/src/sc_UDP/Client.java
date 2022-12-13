package sc_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author 7J
 */
public class Client {

    public static void main(String[] args) {
        DatagramSocket dSocket = null;

        if (args.length < 3) {
            System.out.println("Utilización: java nom_fitxer <missatge> <Host> <Port>");
            System.exit(1);
        }

        try {
            //ENVIAMENT DATAGRAMA
            dSocket = new DatagramSocket();
            byte[] missatgeEnviat = args[0].getBytes();
            InetAddress aHost = InetAddress.getByName(args[1]); //Recupere el HOST des de l'argument
            int serverPort = Integer.parseInt(args[2]); //Recupere el PORT des de l'argument
            DatagramPacket dpEnviament = new DatagramPacket(missatgeEnviat, missatgeEnviat.length, aHost, serverPort); //Datagrama a enviar
            dSocket.send(dpEnviament); //datagrama enviat

            //RECEPCIÓ DATAGRAMA
            byte[] missatgeRebut = new byte[1000];
            DatagramPacket dpResposta = new DatagramPacket(missatgeRebut, missatgeRebut.length);
            dSocket.receive(dpResposta); //rep el datagrama
            System.out.printf("Resposta: %s\n", new String (dpResposta.getData()));
        } catch (SocketException e1) {
            System.out.println("Socket: " + e1.getMessage());
        } catch (IOException e2) {
            System.out.println("IO: " + e2.getMessage());
        } finally {
            if (dSocket != null) {
                dSocket.close();
            }
        }
    }
}
