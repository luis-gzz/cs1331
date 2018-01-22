/**
 * A class representing each chess moves
 * @author lgonzalez35
 * @version 1.0
 */
public class Move {

    private Ply whitePly;
    private Ply blackPly;

    /**
     * Creates a move with all the proper parameters
     * @param whitePly      the white half of a move
     * @param blackPly      the black half of a move
     */
    public Move(Ply whitePly, Ply blackPly) {
        this.whitePly = whitePly;
        this.blackPly = blackPly;
    }

    /**
     * Getter method for the white ply
     * @return the whitePly
     */
    public Ply getWhitePly() {
        return this.whitePly;
    }

    /**
     * Getter method for the black ply
     * @return the blackPly
     */
    public Ply getBlackPly() {
        return this.blackPly;
    }
}
