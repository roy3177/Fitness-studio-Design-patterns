/**
 * @author Roy Meoded
 * @author Noa Agassi
 *
 *This class represents a person in the gym system.
 *This class supports the design pattern Observer-since it implements the Observer interface.
 *It contains features such as name,date of birth,gender,unique identifier,balance and message list.
 */

package gym.customers;

import gym.management.Observer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Person implements Observer {
    private  Balance balanceWrapper;
    private String name;
    private Gender gender;
    private LocalDate birthDate;
    private static int nextId = 1111;
    private int id;
    private List<String> notifications;

    //Constructor:
    public Person(String name, int balance, Gender gender, String birthDate) {
        this.name = name;
        this.gender = gender;
        //Changing the string to a real date:
        this.birthDate = parseBirthDate(birthDate);
        this.id = nextId++;
        notifications = new ArrayList<>();
        this.balanceWrapper = new Balance(balance);
    }
    //Constructor
    public Person(String name, Balance balance, Gender gender, String birthDate, int id) {
        this.name = name;
        this.gender = gender;
        //Changing the string to a real date:
        this.birthDate = parseBirthDate(birthDate);
        notifications = new ArrayList<>();
        this.balanceWrapper = balance;
        this.id = id;
    }

    private LocalDate parseBirthDate(String birthDate) {
        //Checking if the format is yyyy-MM-dd:
        if (birthDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(birthDate, formatter);
        }
        //Checking if the format is dd-MM-yyyy:
        else if (birthDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return LocalDate.parse(birthDate, formatter);
        }
        //If the format is invalid:
        else {
            throw new IllegalArgumentException("Invalid birth date format: " + birthDate);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person ) {
                Person c=(Person) obj;
            return this.id == c.getId();
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balanceWrapper.getBalance();
    }
    public Balance getBalanceWrapper() {
        return balanceWrapper;
    }

    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", balance=" + balanceWrapper  + ", gender=" + gender +
                ", birthDate=" + birthDate + "}";
    }

    public int getAge() {
        LocalDate today = LocalDate.now();
        return today.getYear() - birthDate.getYear();
    }

    public List<String> getNotifications() {
        return notifications;
    }

    //Support of the designed pattern Observer:
    @Override
    public void update(String message) {
        notifications.add(message);
    }

    /**
     *
     *This class is designed to manage the financial balance of the users in the system,
     * such as customers,instructors,and secretary.
     */
    public class Balance {
        private int balance;

        public Balance(int balance) {
            this.balance = balance;
        }

        public int getBalance() {
            return balance;
        }

        public void addToBalance(int amount) {
            this.balance += amount;
        }

        public void reduceBalance(int amount) {
            this.balance -= amount;
        }
    }



}