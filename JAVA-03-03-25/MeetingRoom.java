import java.util.*;

enum RoomFeature {
    PROJECTOR, VIDEO_CONFERENCING, WHITEBOARD, CONFERENCE_PHONE, AIR_CONDITIONING;
}

class MeetingRoom {
    private String roomId;
    private String roomName;
    private int capacity;
    private EnumSet<RoomFeature> features;

    public MeetingRoom(String roomId, String roomName, EnumSet<RoomFeature> features) {
        this.roomId = roomId;
        this.roomName = roomName;
       // this.capacity = capacity;
        this.features = features;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public EnumSet<RoomFeature> getFeatures() {
        return features;
    }

    @Override
    public String toString() {
        return roomName;
    }
}


