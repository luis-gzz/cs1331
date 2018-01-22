/** Represents the knight chess piece
* @author lgonzalez35
* @version 1.0
*/
public class King extends Piece {

    /** Creates a Knight with all required parameters.
    * @param c the color of the piece
    */
    public King(Color c) {
        super(c);
    }

    @Override public String algebraicName() {
        return "K";
    }

    @Override public String fenName() {
        return getColor() == Color.WHITE ? "K" : "k";
    }

    @Override public Square[] movesFrom(Square square) {
        Square[] sq = new Square[8];
        int counter = 0;
        char rank = square.getRank();
        char file = square.getFile();
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                if (r == 0 && c == 0) {
                    continue;
                }
                if (isInBoard((char) (file + c), (char) (rank + r))) {
                    sq[counter++] = new Square((char) (file + c), (char) (rank
                            + r));
                }
            }
        }

        Square[] full = new Square[counter];
        for (int i = 0; i < counter; i++) {
            full[i] = sq[i];
        }

        return full;
    }
}
