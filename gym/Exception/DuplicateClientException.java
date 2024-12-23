/**
 * @author Roy Meoded
 * @author Noa Agassi
 *
 * This class is an Exception class,that designed to handle cases where a duplicate
 * client issue occurs in the system.
 * For example, when you try to add customer that already exsits in the customer list.
 */

package gym.Exception;

public class DuplicateClientException extends Exception{
    public DuplicateClientException(String message){
        super(message);
    }
}
