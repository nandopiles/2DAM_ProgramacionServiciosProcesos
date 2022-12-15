import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Servidor {

    public static String getDayOfWeek(String dataString) throws ParseException {
        GregorianCalendar dataCalendari = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date dataActual = df.parse(dataString);
        int diaSetmana;

        dataCalendari.setTime(dataActual);
        dataString = dataCalendari.get(Calendar.DAY_OF_MONTH) + "-"
                + (dataCalendari.get(Calendar.MONTH) + 1) + "-"
                + dataCalendari.get(Calendar.YEAR);
        diaSetmana = dataCalendari.get(Calendar.DAY_OF_WEEK);
        switch (diaSetmana) {
            case 1 -> dataString += "-Diumenge";
            case 2 -> dataString += "-Dilluns";
            case 3 -> dataString += "-Dimarts";
            case 4 -> dataString += "-Dimecres";
            case 5 -> dataString += "-Dijous";
            case 6 -> dataString += "-Divendres";
            case 7 -> dataString += "-Dissabte";
        }
        return dataString;
    }

    public static void main(String[] args) {
        DatagramSocket dSocket;
        int socket_no = 3000;
        byte[] missatgeRebut = new byte[1000];
        String resposta;

        try {
            dSocket = new DatagramSocket(socket_no);
            while (true) {
                //rep
                DatagramPacket dpRebut = new DatagramPacket(missatgeRebut, missatgeRebut.length);
                dSocket.receive(dpRebut); //rep el datagrama
                System.out.printf("[Client]: %s\n", new String(dpRebut.getData()));
                //envia resposta
                resposta = getDayOfWeek(new String(dpRebut.getData()));
                DatagramPacket dpResposta = new DatagramPacket(resposta.getBytes(), resposta.length(), dpRebut.getAddress(), dpRebut.getPort());
                dSocket.send(dpResposta);
            }
        } catch (SocketException e) {
            System.out.printf("Socket: %s\n", e.getMessage());
        } catch (IOException e1) {
            System.out.printf("IO: %s\n", e1.getMessage());
        } catch (ParseException e2) {
            System.out.printf("Parse: %s\n", e2.getMessage());
        }
    }
}
