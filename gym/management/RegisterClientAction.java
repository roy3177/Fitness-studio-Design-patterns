/**
 * @author Roy Meoded
 * @author Noa Agassi
 *
 * This class is an abstract class, that contains static actions for managing client
 * in the gym ,including registering new clients, registering clients for sessions,
 * and deregistering clients.
 * The class serves as a kind of service that simplifies customer management through
 * the secretary's class.
 */

package gym.management;

import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InvalidAgeException;
import gym.Sessions.Session;
import gym.customers.Client;
import gym.customers.Person;

import java.time.LocalDateTime;

public abstract class RegisterClientAction {

    public static Client registerClient(Person p2,Secretary secretary) throws InvalidAgeException, DuplicateClientException{
        //Checking the age:
        if (p2.getAge() < 18) {
            throw new InvalidAgeException("Error: Client must be at least 18 years old to register");
        }
        //Checking if the customer is on the list:
        for (Client c : secretary.gym.clients) {
            if (c.equals(new Client(p2))) {
                throw new DuplicateClientException("Error: The client is already registered");
            }
        }
        //Creating new client and add him to the clients list:
        Client c = new Client(p2);
        secretary.gym.clients.add(c);

        //Adding the action to the history:
        secretary.gym.getHistory.add("Registered new client: " + c.getName());
        return c;
    }

    /**
     *The client was added to the session.
     * The client's balance is updated according to the cost of the session.
     * The gym's balance sheet is up to date.
     * The action is recorded in the action history.
     *
     */
    public static void registerClientToLesson(Client c1, Session s1,Secretary secretary) throws DuplicateClientException,ClientNotRegisteredException{
        int count = 0;

        if (s1.getParticipants().contains(c1)) {
            throw new DuplicateClientException("Error: The client is already registered for this lesson");
        }
        if (!secretary.gym.clients.contains(c1)) {
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
        }
        if (s1.getParticipants().size() == s1.getMaxClients()) {
            secretary.gym.getHistory.add("Failed registration: No available spots for session");
            count++;
        }

        if (!s1.getDataTime().isAfter(LocalDateTime.now())) { //The session is not in the future
            secretary.gym.getHistory.add("Failed registration: Session is not in the future");
            count++;
        }
        if (s1.getForumType().toString().equals("Seniors") && c1.getAge() < 65) { //The client not stand with the age rule.
            secretary.gym.getHistory.add("Failed registration: Client doesn't meet the age requirements for this session (" + s1.getForumType().toString() + ")");
            count++;
        }

        if ((s1.getForumType().toString().equals("Female") && c1.getGender().toString().equals("Male")) || (s1.getForumType().toString().equals("Male") && c1.getGender().toString().equals("Female"))) { //The client cant stand with the gender.
            secretary.gym.getHistory.add("Failed registration: Client's gender doesn't match the session's gender requirements");
            count++;
        }

        if (c1.getBalance() - s1.getPriceSession() < 0) {
            secretary.gym.getHistory.add("Failed registration: Client doesn't have enough balance");
            count++;
        }
        if (count == 0) {
            s1.addParticipant(c1);
            c1.getBalanceWrapper().reduceBalance(s1.getPriceSession());
            secretary.gym.gymBalance += s1.getPriceSession();
            secretary.gym.getHistory.add("Registered client: " + c1.getName() + " to session: " + s1.getSessionType().toString() +
                    " on " + s1.getDataTime() + " for price: " + s1.getPriceSession());
        }
    }
    //Deleting client from the list:
    public static void unregisterClient(Client c2,Secretary secretary)throws ClientNotRegisteredException {

        secretary.gym.clients.remove(c2);
        secretary.gym.getHistory.add("Unregistered client: " + c2.getName());

    }

}