/**
 * @author Roy Meoded
 * @author Noa Agassi
 *
 * This Observer interface represents the viewer in the design pattern Observer.
 * This is an important part of the pattern,which defines how objects that listen
 * to changes in another object(Subject) are updated.
 */

package gym.management;

public interface Observer {
    void update(String message);
}
