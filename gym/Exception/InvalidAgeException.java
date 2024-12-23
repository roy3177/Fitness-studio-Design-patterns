/**
 * @author Roy Meoded
 * @author Noa Agassi
 *
 * This class is an Exception class, and designed to handle situations where an
 * invalid operation related to the age of the user occurs in the system.
 * For example,when trying to register a customer who is too young for a service
 * that requires a minimum age.
 *
 */

package gym.Exception;

public class InvalidAgeException extends Exception{
    public InvalidAgeException(String message){
        super((message));
    }
}
