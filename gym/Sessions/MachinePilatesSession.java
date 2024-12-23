/**
 * @author Roy Meoded
 * @author Noa Agassi
 *
 * This class introduces a session of type "Machine Pilates" in the gym.
 * Its inherites from the session class.
 * It gives us what is the maximum number of participents,and the price for this session.
 */

package gym.Sessions;

import gym.customers.Instructor;

public class MachinePilatesSession extends Session{
    private static final int MAX_MACHINE_PILATES=10;
    private static final int PRICE=80;

    public MachinePilatesSession(Instructor instructor,String dateTime, ForumType forum) {
        super(SessionType.MachinePilates, instructor,dateTime,forum);
        this.maxClients=MAX_MACHINE_PILATES;
        this.priceSession=PRICE;
    }


    }



