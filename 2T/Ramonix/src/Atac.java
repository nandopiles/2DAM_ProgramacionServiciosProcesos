import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLOutput;

public class Atac implements Comu {
    public static String messageTangoDown() {
        return "d8888b.  .d8b.  .88b  d88.  .d88b.  d8b   db d888888b db    db d888888b  .d8b.  d8b   db  d888b   .d88b.  d8888b.  .d88b.  db   d8b   db d8b   db \n" +
                "88  `8D d8' `8b 88'YbdP`88 .8P  Y8. 888o  88   `88'   `8b  d8' `~~88~~' d8' `8b 888o  88 88' Y8b .8P  Y8. 88  `8D .8P  Y8. 88   I8I   88 888o  88 \n" +
                "88oobY' 88ooo88 88  88  88 88    88 88V8o 88    88     `8bd8'     88    88ooo88 88V8o 88 88      88    88 88   88 88    88 88   I8I   88 88V8o 88 \n" +
                "88`8b   88~~~88 88  88  88 88    88 88 V8o88    88     .dPYb.     88    88~~~88 88 V8o88 88  ooo 88    88 88   88 88    88 Y8   I8I   88 88 V8o88 \n" +
                "88 `88. 88   88 88  88  88 `8b  d8' 88  V888   .88.   .8P  Y8.    88    88   88 88  V888 88. ~8~ `8b  d8' 88  .8D `8b  d8' `8b d8'8b d8' 88  V888 \n" +
                "88   YD YP   YP YP  YP  YP  `Y88P'  VP   V8P Y888888P YP    YP    YP    YP   YP VP   V8P  Y888P   `Y88P'  Y8888D'  `Y88P'   `8b8' `8d8'  VP   V8P ";
    }

    /**
     * Mana un Atac al servidor
     *
     * @param hacker Hacker que està realitzant l'atac
     * @return si Ramonix segueix en vida
     */
    public static boolean ferAtac(Hacker hacker) throws InterruptedException {
        Socket sClient = null;
        DataOutputStream dosOutput = null;
        DataInputStream disInput;
        boolean isDestroyed;

        try {
            sClient = new Socket(HOST, PORT);
        } catch (IOException e) {
            return true;
        }

        try {
            disInput = new DataInputStream(sClient.getInputStream());
            isDestroyed = disInput.readBoolean();
        } catch (IOException e) {
            return true;
        }

        Thread.sleep(hacker.getCadence() * 1000L);
        try {
            dosOutput = new DataOutputStream(sClient.getOutputStream());
        } catch (IOException e) {
            return true;
        }
        if (!isDestroyed) {
            switch (hacker.getNameHacker()) {
                case "Neo":
                    System.out.printf(ANSI_RED + "%s atacant...\n" + ANSI_RESET, hacker.getNameHacker());
                    break;
                case "P4q1T0":
                case "PaU3T":
                    System.out.printf(ANSI_LIGHT_BROWN + "%s atacant...\n" + ANSI_RESET, hacker.getNameHacker());
                    break;
                case "Ab4$t0$":
                    System.out.printf(ANSI_GREEN + "%s atacant\n" + ANSI_RESET, hacker.getNameHacker());
                    break;
            }
        }
        try {
            dosOutput.writeUTF(hacker.getStrength() + " " + hacker.getNameHacker());
            sClient.close();
        } catch (IOException e) {
            return true;
        }

        if (isDestroyed)
            System.out.println(ANSI_BLUE + messageTangoDown() + ANSI_RESET);

        return isDestroyed;
    }
}