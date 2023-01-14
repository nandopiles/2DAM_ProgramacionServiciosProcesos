import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Principal {

    private static final Object[][] HACKERS = {{"Neo", 20, 2, true, false}, {"P4q1T0", 10, 1, true, false}, {"PaU3T", 10, 1, true, false}, {"Ab4$t0$", 10, 1, false, false}};
    private static final ArrayList<Thread> hackerList = new ArrayList<>();
    private static final MeetingRoom meetingRoom = new MeetingRoom();
    private static final String HOST = "localhost";
    private static final int PORT = 5000;

    public static void main(String[] args) throws IOException {

        for (Object[] objects : HACKERS) {
            String name = (String) objects[0];
            int strength = (int) objects[1];
            int cadence = (int) objects[2];
            boolean side = (boolean) objects[3];
            boolean attacking = (boolean) objects[4];

            Hacker hacker = new Hacker(name, strength, cadence, side, attacking, meetingRoom);
            hackerList.add(new Thread(hacker));
        }

        sendNumberOfHackers();

        for (int i = 0; i < HACKERS.length; i++) {
            hackerList.get(i).start();
        }
    }

    private static void sendNumberOfHackers() throws IOException {
        Socket socket = new Socket(HOST, PORT);

        System.out.println("---------------------------");
        System.out.println("CLIENTE ACTIVO (Principal)");
        System.out.println(socket.getLocalSocketAddress());
        System.out.println("---------------------------");

        OutputStream auxOut = socket.getOutputStream();
        DataOutputStream fluxOut = new DataOutputStream(auxOut);
        fluxOut.writeInt(hackerList.size());

        socket.close();
        System.out.println("\n---------------------------");
        System.out.println("CLIENTE CERRADO");
        System.out.println("---------------------------");
    }
}
