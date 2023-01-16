import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Principal implements Comu {
    public static ArrayList<Thread> hackersList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        SalaReunions meetingRoom = new SalaReunions();

        for (Object[] h :
                HACKERS) {
            String name = (String) h[0];
            int strength = (int) h[1];
            int cadence = (int) h[2];

            Hacker hacker = new Hacker(name, strength, cadence, meetingRoom);
            hackersList.add(new Thread(hacker));
            System.out.printf("Cree: %s\n", hacker.getName());
        }

        for (Thread h :
                hackersList) {
            h.start();
        }
    }
}
