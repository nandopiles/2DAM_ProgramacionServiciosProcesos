public class Hacker extends Thread implements Comu {
    private String name;
    private int strength;
    private int cadence;
    private final SalaReunions meetingRoom;

    public Hacker(String name, int strength, int cadence, SalaReunions meetingRoom) {
        this.name = name;
        this.strength = strength;
        this.cadence = cadence;
        this.meetingRoom = meetingRoom;
        System.out.printf("Cree: %s\n", this.getNameHacker());
    }

    public String getNameHacker() {
        return name;
    }

    public void setNameHacker(String name) {
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

            if (this.getNameHacker().equals("Neo")) {
                this.meetingRoom.setNeoHasCome();
                System.out.printf("****** %s-: Bon dia. Anem a destruir RAMONIX! ******\n", this.getNameHacker());
                this.meetingRoom.notifyAll();
            } else if (!meetingRoom.isNeoHasCome()) {
                System.out.println("Encara no està Neo...");
                meetingRoom.wait();
            }
        }
    }


    public void run() {
        boolean isDestroyed = false;

        try {
            hasNeoArrive();
        } catch (InterruptedException ignored) {
        }
        System.out.printf("JA ESTEM TOTS. %s COMENÇA L'ATAC!!!\n", this.getNameHacker());
        do {
            try {
                isDestroyed = Atac.ferAtac(this);
            } catch (InterruptedException ignored) {
            }
        } while (!isDestroyed);
    }

    @Override
    public String toString() {
        return "Hacker{" + "name='" + name + '\'' + ", strength=" + strength + ", cadence=" + cadence + ", meetingRoom=" + meetingRoom + '}';
    }
}