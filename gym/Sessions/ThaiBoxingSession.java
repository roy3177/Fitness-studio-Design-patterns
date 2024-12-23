/**
 * @author Roy Meoded
 * @author Noa Agassi
 *
 * This class introduces a session of type "Thai Boxing" in the gym.
 * Its inherites from the session class.
 * Its inherites from the session class.
 * It gives us what is the maximum number of participents,and the price for this session.
 */
package gym.Sessions;

import gym.customers.Instructor;

public class ThaiBoxingSession extends Session{
    private static final int MAX_THAI_BOXING=20;
    private static final int PRICE=100;

    public ThaiBoxingSession(Instructor instructor,String dateTime, ForumType forum) {
        super(SessionType.ThaiBoxing, instructor,dateTime,forum);
        this.maxClients=MAX_THAI_BOXING;
        this.priceSession=PRICE;
    }
}
