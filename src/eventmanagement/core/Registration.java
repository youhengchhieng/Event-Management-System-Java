package eventmanagement.core;

public interface Registration {
    boolean registerParticipant(String eventId, String participantId);
    boolean cancelRegistration(String eventId, String participantId);
    int getRegisteredCount(String eventId);
    void displayRegisteredParticipants(String eventId);
}
