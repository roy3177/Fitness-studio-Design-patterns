/**
 * @author Roy Meoded
 * @author Noa Agassi
 *
 * This class represents the secretary's rule in the gym system.
 * The class extends from the Person class-contains general detalis such as:
 * name,birthdate and balance.
 * In addition-the secretary responsible on the management clients,sessions and instructors.
 * This class works in cooperation with the Gym class.
 * The class uses design patterns such as:
 * Singelton-for the Gym
 * Factory-for creating sessions
 * Observer-for sends messages to the clients
 */

package gym.management;
import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;
import gym.Sessions.ForumType;
import gym.Sessions.Session;
import gym.Sessions.SessionType;
import gym.Sessions.SessionFactory;
import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Secretary extends Person implements Subject {

    private int salary;
    private boolean active;
    protected Gym gym;

    //Constructor:
    public Secretary(Person person, int salary,Gym gym) {
        super(person.getName(), person.getBalance(), person.getGender(),person.getBirthDate().toString());
        this.gym = gym;
        this.salary = salary;
        active = true;
    }
    //Constructor:
    public Secretary(Person person,int salary, Secretary secretary,Gym gym) {
        super(person.getName(), person.getBalanceWrapper(), person.getGender(),person.getBirthDate().toString(), person.getId());
        this.salary = salary;
        this.active = true;
        this.gym = gym;

    }
    //Adding new client to the system:
    public Client registerClient(Person p2) throws InvalidAgeException, DuplicateClientException,NullPointerException {
        if (!active) { //If the secretary is inactive
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        return RegisterClientAction.registerClient(p2,this);
    }
    //Canceling registry :
    public void unregisterClient(Client c2)throws ClientNotRegisteredException {
        if (!gym.clients.contains(c2)) {
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
        }
        RegisterClientAction.unregisterClient(c2,this);
    }

    //Register client to session:
    public void registerClientToLesson(Client c1, Session s1) throws DuplicateClientException,ClientNotRegisteredException,NullPointerException {
        if (active) {
            RegisterClientAction.registerClientToLesson(c1,s1,this);
        }
        else {
            NullPointerException e = new NullPointerException("Error: Former secretaries are not permitted to perform actions");
            System.out.println(e.getMessage());
        }
    }

    //Pay salaries to all the employees:
    public void paySalaries() {
        super.getBalanceWrapper().reduceBalance(salary);
        gym.gymBalance -= salary;
        for (Instructor i : gym.instructors) {
            int numOfSessions = i.getAllSessions().size();
            int salaryIn = numOfSessions*i.getSalary();
            i.getBalanceWrapper().addToBalance(salaryIn);
            gym.gymBalance -= salaryIn;
        }

        gym.getHistory.add("Salaries have been paid to all employees");
    }

    //Prints the history of the activities that made by the secretary:
    public void printActions() {
        for (int i = 0; i < gym.getHistory.size(); i++) {
            System.out.println(gym.getHistory.get(i));
        }
    }

    //Adding new session to the gym:
    public Session addSession(SessionType sessionType, String s, ForumType forumType, Instructor i2) throws InstructorNotQualifiedException {
        Session newSession = SessionFactory.createSession(sessionType, s, forumType, i2);
        if (!i2.isQualifiedFor(sessionType)) {
            throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.");
        }
        // Adding session to the list:
        gym.sessions.add(newSession);
        i2.getAllSessions().add(newSession);

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        //Converts the date to the right format:
        String formattedDateTime = LocalDateTime.parse(s, inputFormatter).format(outputFormatter);


        gym.getHistory.add("Created new session: " +sessionType +" on "+ formattedDateTime + " with instructor: " + i2.getName() );

        return newSession;
    }

    //Hires new instructor:
    public Instructor hireInstructor(Person p5, int i, ArrayList<SessionType> sessionTypes) {

        Instructor instructor = new Instructor(p5, i, sessionTypes);
        gym.instructors.add(instructor);
        gym.getHistory.add("Hired new instructor: " + instructor.getName() + " with salary per hour: " + instructor.getSalary());

        return instructor;
    }



    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return String.format("ID: %d | Name: %s | Gender: %s | Birthday: %s | Age: %d | Balance: %d | Role: Secretary | Salary per Month: %d",
                super.getId(), super.getName(), super.getGender(), super.getBirthDate().format(dateFormatter), super.getAge(), super.getBalance(), salary);
    }
    //Change the active mode of the secretary:
    public void setActive(boolean active) {
        this.active = active;
    }

    //Sends message  to all participants of particular session:
    public void notify(Session s4, String s) throws NullPointerException {
        if (!active) {//If the secretary is inactive
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        for(Client c : s4.getParticipants()){
            c.update(s);
        }
        gym.getHistory.add("A message was sent to everyone registered for session " + s4.getSessionType() + " on " + s4.getDataTime().toString() + " : " + s);

    }

    //Sends message  to all participants of particular session+particular day:
    public void notify(String date, String message) throws NullPointerException{
        if (!active) {
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Convert the date to LocalDate:
        LocalDate targetDate = LocalDate.parse(date, inputFormatter);
        for (Session session : gym.sessions) {
            // Convert session's datetime to LocalDate (ignoring time part)
            LocalDate sessionDate = session.getDataTime().toLocalDate();

            if (sessionDate.equals(targetDate)) {  // Compare only the date part
                for(Client c : session.getParticipants()){
                    c.update(message);
                }

            }
        }
        gym.getHistory.add("A message was sent to everyone registered for a session on "+ targetDate +" : "+message);
    }
    //Sends general message to all clients:
    public void notify(String s) {
        if (!active) {
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        notifyObserver(s);
        gym.getHistory.add("A message was sent to all gym clients: " + s);
    }

    @Override
    public void notifyObserver(String message) {
        for (Client c : gym.clients) {
            c.update(message);
        }
    }
    public void addToHistory(String s) {
        gym.getHistory.add(s);
    }

}