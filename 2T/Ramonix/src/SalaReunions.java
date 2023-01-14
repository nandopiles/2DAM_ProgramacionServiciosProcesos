import java.io.Serializable;
import java.util.ArrayList;

class SalaReunions implements Serializable {

    private boolean neoHasCome;
    private final ArrayList<String> members;

    public SalaReunions() {
        this.neoHasCome = false;
        this.members = new ArrayList<>();
    }

    public void addMember(String name) {
        this.members.add(name);
    }

    public ArrayList<String> getMembers() {
        return this.members;
    }

    public boolean isNeoHasCome() {
        return neoHasCome;
    }

    public void setNeoHasCome() {
        this.neoHasCome = true;
    }

    @Override
    public String toString() {
        return "SalaReunions{" + "neo=" + neoHasCome + '}';
    }
}