
/** An Exception incase an improper square is entered.
*
* This exception is a checked exception because if a wrong square is entered
* the user still has a chance to recover and enter a correct square. We also
* want the compiler to check that we are watching out for an incorrect square.
*
* @author lgonzalez35
*/
public class InvalidSquareException extends Exception {

    /**
     * Creates an InvalidSquareException and passes the square string
     * to the Exception super class
     * @param name the String representation of an incorrect square
     */
    public InvalidSquareException(String name) {
        super(name);
    }
}
