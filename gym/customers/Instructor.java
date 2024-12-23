/**
 * @author Roy Meoded
 * @author Noa Agassi
 *
 * This class represents an instrucor in the gym,and inherits from the class Person.
 * The insrucor is responsible for teaching sessions and recievs a salary accordingly.
 *This class includes relevant details such as the types of the sessions the instructor
 * is authorized to teach, a list of all the sessions he or she teaches,and other
 * personal information.
 */
package gym.customers;

import gym.Sessions.Session;
import gym.Sessions.SessionType;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Instructor extends Person {

    private final int salary;
    private List<SessionType> qualifiedSessions;//Types of sessions certified instructor to train.
    private List<Session> allSessions; //List of the sessions that the instructor train.

    // Constructor:
    public Instructor(Person person, int salary, List<SessionType> qualifiedSessions) {
        super(person.getName(), person.getBalanceWrapper(), person.getGender(),person.getBirthDate().toString(), person.getId());
        this.salary = salary;
        this.qualifiedSessions = qualifiedSessions;
        allSessions = new ArrayList<>();
    }

    // Getters:
    public int getSalary() {
        return salary;
    }

    public List<Session> getAllSessions() {
        return allSessions;
    }

    // Check if instructor is qualified for a specific session type
    public boolean isQualifiedFor(SessionType sessionType) {
        return qualifiedSessions.contains(sessionType);
    }


    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return String.format("ID: %d | Name: %s | Gender: %s | Birthday: %s | Age: %d | Balance: %d | Role: %s | Salary per Hour: %d | Certified Classes: %s",
                super.getId(),
                super.getName(),
                super.getGender(),
                super.getBirthDate().format(dateFormatter),
                super.getAge(),
                super.getBalance(),
                "Instructor",
                salary,
                qualifiedSessions.stream().map(SessionType::toString) // המרת כל אובייקט SessionType למחרוזת
                        .collect(Collectors.joining(", "))); // חיבור המחרוזות עם פסיקים
    }





}