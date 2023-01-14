import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Atac implements Comu {
    public static void ferAtac(String attackersName, int strength, int cadence) throws InterruptedException, IOException {
        Socket sClient = new Socket(HOST, PORT);
        DataOutputStream dosOutput;

        int a = Ramonix.getLife();

        while (Ramonix.getLife() >= 0) {
            Thread.sleep(cadence * 1000L);
            dosOutput = new DataOutputStream(sClient.getOutputStream());
            switch (attackersName) {
                case "Neo":
                    System.out.printf(ANSI_RED + "%s atacant...\n" + ANSI_RESET, attackersName);
                    break;
                case "P4q1T0":
                case "PaU3T":
                    System.out.printf(ANSI_LIGHT_BROWN + "%s atacant...\n" + ANSI_RESET, attackersName);
                    break;
                case "Ab4$t0$":
                    System.out.printf(ANSI_GREEN + "%s atacant\n" + ANSI_RESET, attackersName);
                    break;
            }
            dosOutput.writeUTF(strength + " " + attackersName);
        }
    }
}
