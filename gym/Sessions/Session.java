/**
 * @author Roy Meoded
 * @author Noa Agassi
 *
 * This class represents a session in a class management system.
 * The session includes information about the type of the session,
 * the instructor, the participants, the pricing, the forum
 * and the date of the class.
 * The class serves as a central object in the system for managing
 * sessions and checking the eligibility of participants to join.
 *
 */

package gym.Sessions;

import gym.customers.Client;
import gym.customers.Instructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Session  {

    //The fields:
    protected LocalDateTime dateTime;
    protected Instructor instructor;
    protected List<Client> participants;
    protected int priceSession;
    protected int maxClients;
    protected SessionType sessionType;
    protected ForumType forumType;

    //Constructor:
    public Session(SessionType sessionType, Instructor instructor, String dateTime, ForumType forumType) {
        this.dateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        this.instructor = instructor;
        this.participants = new ArrayList<>();
        this.sessionType = sessionType;
        this.forumType = forumType;
    }
    //Getters:
    public List<Client> getParticipants() {
        return participants;
    }

    public int getPriceSession() {
        return priceSession;
    }

    public LocalDateTime getDataTime() {
        return dateTime;
    }

    public ForumType getForumType() {
        return forumType;
    }

    public int getMaxClients() {
        return maxClients;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return String.format(
                "Session Type: %s | Date: %s | Forum: %s | Instructor: %s | Participants: %d/%d",
                sessionType,
                dateTime.format(dateFormatter),
                forumType,
                instructor.getName(),
                participants.size(),
                maxClients
        );
    }


    public void addParticipant(Client client) {
        participants.add(client);
    }
}