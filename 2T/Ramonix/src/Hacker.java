import java.io.IOException;

public class Hacker implements Runnable, Comu {
    private String name;
    private int strength;
    private int cadence;
    private final SalaReunions meetingRoom;

    public Hacker(String name, int strength, int cadence, SalaReunions meetingRoom) {
        this.name = name;
        this.strength = strength;
        this.cadence = cadence;
        this.meetingRoom = meetingRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getCadence() {
        return cadence;
    }

    public void setCadence(int cadence) {
        this.cadence = cadence;
    }

    public SalaReunions getMeetingRoom() {
        return meetingRoom;
    }

    /**
     * Comprova si Neo ha arribat per a avisar a tot el món que pot començar a atacar
     */
    public void hasNeoArrive() throws InterruptedException {
        synchronized (meetingRoom) {
            meetingRoom.addMember(this.name);

            if (this.getName().equals("Neo")) {
                this.meetingRoom.setNeoHasCome();
                System.out.println("****** Neo-: Bon dia. Anem a destruir RAMONIX! ******");
                System.out.printf("JA ESTEM TOTS. %s COMENÇA L'ATAC!!!\n", this.getName());
                this.meetingRoom.notifyAll();
            } else {
                if (!meetingRoom.isNeoHasCome()) {
                    System.out.println("Encara no està Neo...");
                    meetingRoom.wait();
                }
                System.out.printf("JA ESTEM TOTS. %s COMENÇA L'ATAC!!!\n", this.getName());
            }
        }
    }

    public void run() {
        try {
            hasNeoArrive();
            //Hacker ataca... => puede que haya que poner aquí los mensajes de "JA ESTEM TOTS. %s ATACA..."
            Atac.ferAtac(this.getName(), this.getStrength(), this.getCadence());
        } catch (IOException | InterruptedException a) {
        }
    }

    @Override
    public String toString() {
        return "Hacker{" +
                "name='" + name + '\'' +
                ", strength=" + strength +
                ", cadence=" + cadence +
                ", meetingRoom=" + meetingRoom +
                '}';
    }
}