/**
 * @author Roy Meoded
 * @author Noa Agassi
 *
 * This class introduces a session of type "Ninja" in the gym.
 * Its inherites from the session class.
 * It gives us what is the maximum number of participents,and the price for this session.
 */

package gym.Sessions;

import gym.customers.Instructor;

public class NinjaSession extends Session{
    private static final int MAX_NINJA=5;
    private static final int PRICE=150;

    public NinjaSession(Instructor instructor,String dateTime, ForumType forum) {
        super(SessionType.Ninja, instructor,dateTime,forum);
        this.maxClients=MAX_NINJA;
        this.priceSession=PRICE;
    }
}
