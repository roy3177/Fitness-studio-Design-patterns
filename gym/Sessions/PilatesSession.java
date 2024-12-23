/**
 * @author Roy Meoded
 * @author Noa Agassi
 *
 * This class introduces a session of type "Pilates" in the gym.
 * Its inherites from the session class.
 * It gives us what is the maximum number of participents,and the price for this session.
 */

package gym.Sessions;

import gym.customers.Instructor;

public class PilatesSession extends Session {
    private static final int MAX_PILATES = 30;
    private static final int PRICE = 60;

    public PilatesSession(Instructor instructor,String dateTime, ForumType forum) {
        super(SessionType.Pilates, instructor,dateTime,forum);
        this.maxClients = MAX_PILATES;
        this.priceSession = PRICE;
    }


}
