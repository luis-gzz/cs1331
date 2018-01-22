/** The parent class to different chess pieces
* @author lgonzalez35
* @version 1.0
*/
public abstract class Piece {
    private Color color;

    /**
     * Creates an Piece with all required parameters.
     * @param c the color of the piece
     */
    public Piece(Color c) {
        color = c;
    }

    /**
    * @return the color of the piece
    */
    public Color getColor() {
        return color;
    }

    /**
     * checks if the passed in file and rank are within the bounds of the board
     * @param  file          the file to check
     * @param  rank          the rank to check
     * @return boolean; true if within bounds otherwise false
     */
    public boolean isInBoard(char file, char rank) {
        return file >= 'a' && file <= 'h' && rank >= '1' && rank <= '8';
    }

    /**
    * @return piece's algebraic name
    */
    public abstract String algebraicName();

    /**
    * @return piece's FEN notation name
    */
    public abstract String fenName();

    /** Takes a Square object and calculates the possible moves on the board
    * @return squares the pieces can move to in a Square[]
    * @param square object of pieces location
    */
    public abstract Square[] movesFrom(Square square);

    /**
     * A function to get a string representation of the Piece's color and type
     * @return String
     */
    public String toString() {
        return color.toString() + " " + this.getClass();
    }
}
