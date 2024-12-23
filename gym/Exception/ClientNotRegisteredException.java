/**
 * @author Roy Meoded
 * @author Noa Agassi
 *
 * This class is an Exception class,that designed to handle cases where an activity
 * is attempted on the client-that is not registered to the system.
 * This class represents situation where the activity is invalid or not possible,
 * because the customer does not exsist in the list of registered customers.
 */

package gym.Exception;

public class ClientNotRegisteredException extends Exception {
    public ClientNotRegisteredException(String message){
        super(message);
    }
}
