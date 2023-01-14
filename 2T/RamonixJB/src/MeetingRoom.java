import java.io.Serializable;
import java.util.ArrayList;

class MeetingRoom implements Serializable {

    private boolean neo;
    private final ArrayList<String> participants;

    public MeetingRoom() {
        this.neo = false;
        this.participants = new ArrayList<>();
    }

    public MeetingRoom(boolean isNeo) {
        this.neo = isNeo;
        this.participants = new ArrayList<>();
    }

    public void addParticipant(String name) {
        this.participants.add(name);
    }

    public ArrayList<String> getParticipants() {
        return this.participants;
    }

    public boolean isNeo() {
        return neo;
    }

    public void setNeo() {
        this.neo = true;
    }

    @Override
    public String toString() {
        return "MeetingRoom{" + "neo=" + neo + '}';
    }
}
