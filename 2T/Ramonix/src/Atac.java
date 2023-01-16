import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Atac implements Comu {

    private static Hacker hacker;

    public Atac(Hacker hacker) {
        Atac.hacker = hacker;
    }

    public static boolean ferAtac() throws InterruptedException, IOException {
        Socket sClient = new Socket(HOST, PORT);
        DataOutputStream dosOutput;
        DataInputStream disInput;
        boolean isDestroyed;

        disInput = new DataInputStream(sClient.getInputStream());
        isDestroyed = disInput.readBoolean();

        Thread.sleep(hacker.getCadence() * 1000L);
        dosOutput = new DataOutputStream(sClient.getOutputStream());
        switch (hacker.getName()) {
            case "Neo":
                System.out.printf(ANSI_RED + "%s atacant...\n" + ANSI_RESET, hacker.getName());
                break;
            case "P4q1T0":
            case "PaU3T":
                System.out.printf(ANSI_LIGHT_BROWN + "%s atacant...\n" + ANSI_RESET, hacker.getName());
                break;
            case "Ab4$t0$":
                System.out.printf(ANSI_GREEN + "%s atacant\n" + ANSI_RESET, hacker.getName());
                break;
        }
        dosOutput.writeUTF(hacker.getStrength() + " " + hacker.getName());
        sClient.close();

        return isDestroyed;
    }
}
