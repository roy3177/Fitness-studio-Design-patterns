/**
 * @author Roy Meoded
 * @author Noa Agassi
 *
 * This class is an Exception class,designed to handle situations where instructor
 * is not qualified to teach particular type of session.
 * The purpose of the exception is to alert when an incorrect attemp is made to
 * associate an instructor with a session that he is not qualified to insturt.
 */
package gym.Exception;

public class InstructorNotQualifiedException extends Exception{
    public InstructorNotQualifiedException(String message){
        super(message);
    }
}
