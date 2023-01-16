import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class Ramonix implements Comu, Serializable {
    private static int life;
    private static ServerSocket ssServer;
    private static Socket sClient;

    public Ramonix(int life) throws IOException {
        Ramonix.life = life;
        System.out.println(ANSI_GREEN + "Benvinguts a RAMONIX!" + ANSI_RESET);
        ssServer = new ServerSocket(PORT);

        while (getLife() > 0) {
            sClient = ssServer.accept();
            isDestroyed();
            receiveAttack();
            sClient.close();
            if (getLife() <= 0) {
                sClient = ssServer.accept();
                isDestroyed();
                sClient.close();
            }
        }
        System.out.println("COMUNIQUE AL CLIENT QUE TANQUE");

        ssServer.close();
    }

    public static int getLife() {
        return life;
    }

    public void setLife(int life) {
        Ramonix.life = life;
    }

    public static ServerSocket getSsServer() {
        return ssServer;
    }

    public static void setSsServer(ServerSocket ssServer) {
        Ramonix.ssServer = ssServer;
    }

    public static Socket getsClient() {
        return sClient;
    }

    public static void setsClient(Socket sClient) {
        Ramonix.sClient = sClient;
    }

    /**
     * Mana al Hacker si Ramonix segueix en vida
     */
    public static void isDestroyed() throws IOException {
        DataOutputStream dosOutput = new DataOutputStream(sClient.getOutputStream());
        dosOutput.writeBoolean(getLife() <= 0);
    }

    /**
     * Rep el nom del Hacker i la quantitat de dany que li fa a Ramonix
     */
    public void receiveAttack() throws IOException {
        DataInputStream disInput = new DataInputStream(sClient.getInputStream());
        String[] strengthAttackWithName;
        String attackersName;
        int attack;

        strengthAttackWithName = disInput.readUTF().split(" ");
        attack = Integer.parseInt(strengthAttackWithName[0]);
        attackersName = strengthAttackWithName[1];
        switch (attackersName) {
            case "Neo":
                System.out.printf(ANSI_RED + "Atac des de: %s => (%d)\n" + ANSI_RESET, attackersName, attack);
                break;
            case "P4q1T0":
            case "PaU3T":
                System.out.printf(ANSI_LIGHT_BROWN + "Atac des de: %s => (%d)\n" + ANSI_RESET, attackersName, attack);
                break;
            case "Ab4$t0$":
                System.out.printf(ANSI_GREEN + "Atac des de: %s => (+%d)\n" + ANSI_RESET, attackersName, attack);
                break;
        }
        this.setLife(life + attack);
        System.out.printf("**** Energia: %d\n", getLife());
    }

    public static void main(String[] args) throws IOException {
        new Ramonix(100);
    }
}