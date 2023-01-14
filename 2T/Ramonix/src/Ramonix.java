import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Ramonix implements Comu, Serializable {
    private static int life;
    private static ServerSocket ssServer;
    private static Socket sClient;

    public Ramonix(int life) throws IOException {
        Ramonix.life = life;
        System.out.println(ANSI_GREEN + "Benvinguts a RAMONIX!" + ANSI_RESET);
        startServer();
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
     * @return nombre de Hackers que li envia el Client
     */
    public int receiveNumberOfHackers() throws IOException {
        sClient = ssServer.accept();

        DataInputStream disEntrada = new DataInputStream(sClient.getInputStream());
        return disEntrada.readInt();
    }

    /**
     * Acceptem a tots els Hackers
     *
     * @param numberOfHackers nombre de Hackers
     */
    public void acceptsHackers(int numberOfHackers) throws IOException {
        for (int i = 0; i < numberOfHackers; i++) {
            sClient = ssServer.accept();
        }
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
                System.out.printf(ANSI_GREEN + "Atac des de: %s => (%d)\n" + ANSI_RESET, attackersName, attack);
                break;
        }
        this.setLife(life + attack);
        System.out.printf("**** Energia: %d\n", getLife());
    }

    public void startServer() throws IOException {
        ssServer = new ServerSocket(PORT);
        int numberOfHackers;

        numberOfHackers = receiveNumberOfHackers();
        acceptsHackers(numberOfHackers);
        while (getLife() > 0) {
            receiveAttack();
        }
        System.out.println("COMUNIQUE AL CLIENT QUE TANQUE");
        ssServer.close();
    }

    public static void main(String[] args) throws IOException {
        new Ramonix(100);
    }
}