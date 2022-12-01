package sc_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author 7J
 */
public class Client {

    public static void main(String[] args) {
        DatagramSocket dSocket = null;

        if (args.length < 3) {
            System.out.println("Utilización: java blabla<missatge> <Host> <Port>");
            System.exit(1);
        }

        try {
            int socket_no = Integer.parseInt(args[0]);
            dSocket = new DatagramSocket(socket_no);
            byte[] missatgeRebut = new byte[1000];

            while (true) {
                DatagramPacket dpRebut = new DatagramPacket(missatgeRebut, missatgeRebut.length);
                dSocket.receive(dpRebut);
                System.out.println("Rep del Client: " + new String(dpRebut.getData()));

                DatagramPacket dpResposta = new DatagramPacket(dpRebut.getData(), dpRebut.getLength(), dpRebut.getAddress(), dpRebut.getPort());
                dSocket.send(dpResposta);
            }
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