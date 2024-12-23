/**
 * @aurhor Roy Meoded
 * @author Noa Agassi
 *
 * This class is an interface,that is used as part of the design
 * pattern Observer.
 * This interface defines the basic actions, that each class that serves
 * as s "Subject" must perform .
 * This class is responsible for managing a list of "observers" ,and
 * updating them when changes occures.
 *
 */



package gym.management;

import gym.Sessions.Session;

public interface Subject {

    void notify(Session s4, String s); //
    void notify(String s); //
    void notifyObserver(String message); //Sends message to all the observers.
}