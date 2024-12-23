/**
 * @author Roy Meoded
 * @author Noa Agassi
 *
 * This class is a facotry class,that is used to create objects of different
 * types of sessions in the system.
 * It uses a single static method:createSession,which get parameters to create
 * the lesson and returns an appropriate instance of the subtype class,based on
 * the class type.
 *
 */

package gym.Sessions;

import gym.customers.Instructor;

public class SessionFactory {

    public static Session createSession(SessionType sessionType, String dateTime, ForumType forumType,Instructor instructor) {

        switch (sessionType) {
            case Pilates:
                return new PilatesSession( instructor,dateTime, forumType);
            case MachinePilates:
                return new MachinePilatesSession(instructor,dateTime, forumType);
            case ThaiBoxing:
                return new ThaiBoxingSession(instructor,dateTime, forumType);
            case Ninja:
                return new NinjaSession(instructor,dateTime, forumType);
            default:
                throw new IllegalArgumentException("Invalid session type.");
        }
    }
}