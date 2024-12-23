/**
 * @author Roy Meoded
 * @author Noa Agassi
 *
 *This class represents a client in the gym system, extends from Person,and implements
 * from Observer.
 * The cleint supports recieving alerts-using the design pattern Observer,
 * and uses featurs that defined in the Person class.
 *
 */

package gym.customers;

import gym.management.Observer;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Client extends Person implements Observer {

    //A list to store alert messages that receive from the system:
    private  List<String>notifications=new ArrayList<>();

    //Constructor:
    public Client(Person p ) {
        super(p.getName(),p.getBalanceWrapper(),p.getGender(),p.getBirthDate().toString(),p.getId());
        this.notifications=new ArrayList<>();
    }

    public List<String> getNotifications() {
        return super.getNotifications();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Client ) {
            Client c=(Client) obj;
            return this.getId() == c.getId(); // השוואה לפי id בלבד
        }
        return false;
    }


    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return String.format("ID: %d | Name: %s | Gender: %s | Birthday: %s | Age: %d | Balance: %d",
                super.getId(), super.getName(), super.getGender(), super.getBirthDate().format(dateFormatter), super.getAge(), super.getBalance());
    }
    //Giving the client to receive messages,and save them on the notification list
    @Override
    public void update(String message){
        getNotifications().add(message);
    }

}