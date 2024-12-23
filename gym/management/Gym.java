/**
 * @author Roy Meoded
 * @author Noa Agassi
 *
 * This class represents a Gym in the system.
 * Its masnaged using the design pattern Singelton-to ensure that there is only one
 * gym object throughout the life of the progrem.
 * The class includes the management of all the main elements:
 * customers,instructors,secretary,and activity history.
 */

package gym.management;

import gym.Sessions.Session;
import gym.customers.*;
import java.util.ArrayList;
import java.util.List;

public class Gym{

    //Lists for management different activities:
    private static Gym gym;//private static field of gym type-used to create Singelton object.
    protected List<Session> sessions = new ArrayList<>();
    protected List<Client> clients = new ArrayList<>();
    protected List<Instructor> instructors = new ArrayList<>();
    protected List<String> getHistory = new ArrayList<>();
    protected static int gymBalance;

    private static Secretary secretary;
    private String name;


    //An empty constructor-as part of the Singelton:
    private Gym(){}

    //A static method that returns the only object of gym:
    public static Gym getInstance() {
        if (gym == null) {
            gym = new Gym();
        }
        return gym;
    }

    public Secretary getSecretary() {
        return secretary;
    }

    /**
     * This method allows to change a secretary in the gym:
     * if there is no secretary-it creates one.
     * If there is a previous secretary-mark it as inactive and create a new one,
     * while maintaining the data of the previous one.
     * Writing on the changing secretary.
     */
    public void setSecretary(Person p1, int i) {
        if (secretary == null) {
            secretary = new Secretary(p1,i,this);
        }
        else {
            secretary.setActive(false);
            secretary = new Secretary(p1, i, secretary,this);

        }
        secretary.addToHistory("A new secretary has started working at the gym: " + p1.getName());

    }

    public void setName(String CrossFit) {
        this.name = CrossFit;
    }

    // Method to print Gym Information
    public String toString() {
        StringBuilder gymInfo = new StringBuilder();

        gymInfo.append(String.format("Gym Name: %s\n", name));
        gymInfo.append(String.format("Gym Secretary: %s\n", secretary));
        gymInfo.append(String.format("Gym Balance: %d\n\n", gymBalance));

        // Clients Data
        gymInfo.append("Clients Data:\n");
        for (Client client :clients ) {
            gymInfo.append(client).append("\n");
        }
        gymInfo.append("\n");

        // Employees Data
        gymInfo.append("Employees Data:\n");
        for (Instructor instructor : instructors ) {
            gymInfo.append(instructor).append("\n");
        }
        gymInfo.append(secretary).append("\n\n");

        // Sessions Data
        gymInfo.append("gym.Sessions Data:\n");
        for (Session session : sessions) {
            gymInfo.append(session).append("\n");
        }
        gymInfo.setLength(gymInfo.length()-1);
        return gymInfo.toString();
    }


}