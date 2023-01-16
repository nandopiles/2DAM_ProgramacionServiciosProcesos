import java.util.ArrayList;

public class Principal implements Comu {
    public static ArrayList<Thread> hackersList = new ArrayList<>();

    public static void main(String[] args) {

        SalaReunions meetingRoom = new SalaReunions();

        for (Object[] h :
                HACKERS) {
            String name = (String) h[0];
            int strength = (int) h[1];
            int cadence = (int) h[2];

            Hacker hacker = new Hacker(name, strength, cadence, meetingRoom);
            hackersList.add(new Thread(hacker));
        }

        for (Thread h :
                hackersList) {
            h.start();
        }

    }
}
