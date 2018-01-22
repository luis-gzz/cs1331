/** An Exception incase an improper square is entered.
*
* This exception is an unexpected exception
*
* @author lgonzalez35
* @version 1.1
*/
public class InvalidSquareException extends RuntimeException {

    /**
     * Creates an InvalidSquareException and passes the square string
     * to the Exception super class
     * @param name the String representation of an incorrect square
     */
    public InvalidSquareException(String name) {
        super(name);
    }
}
