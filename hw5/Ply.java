import java.util.Optional;

/**
 * A class representing each ply or individual player move(white or black)
 * @author lgonzalez35
 * @version 1.0
 */
public class Ply {

    private Piece piece;
    private Square fromSq;
    private Square toSq;
    private Optional<String> comment;

    /**
     * [Ply description]
     * @param  p the piece moved in the ply
     * @param  f the square the piece moved from
     * @param  t the square the piece moved to
     * @param  c an Optional of string type for the moves comment
     */
    public Ply(Piece p, Square f, Square t, Optional<String> c) {
        piece = p;
        fromSq = f;
        toSq = t;
        comment = c;
    }

    /**
     * Getter method for the ply's piece
     * @return a Piece object
     */
    public Piece getPiece() {
        return this.piece;
    }

    /**
     * Getter method for the ply's origin sq
     * @return a Square object
     */
    public Square getFrom() {
        return this.fromSq;
    }

    /**
     * Getter method for the ply's final sq
     * @return a Square object
     */
    public Square getTo() {
        return this.toSq;
    }

    /**
     * Getter method for the ply's Optional comment
     * @return an Optional object
     */
    public Optional<String> getComment() {
        return this.comment;
    }
}
