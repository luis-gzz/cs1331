/** Represents the Rook chess piece
* @author lgonzalez35
* @version 1.0
*/
public class Rook extends Piece {

    /** Creates an Rook with all required parameters.
    * @param c the color of the piece
    */
    public Rook(Color c) {
        super(c);
    }

    @Override public String algebraicName() {
        return "R";
    }

    @Override public String fenName() {
        return getColor() == Color.WHITE ? "R" : "r";
    }

    @Override public Square[] movesFrom(Square square) {
        Square[] sq = new Square[27];
        int counter = 0;
        char rank = square.getRank();
        char file = square.getFile();
        for (int i = 1; i <= 8; i++) {
            char[] ranks = new char[]{(char) (rank + i), (char) (rank - i)};
            char[] files = new char[]{(char) (file + i), (char) (file - i)};
            if (isInBoard(files[0], rank)) {
                sq[counter++] = new Square(files[0], rank);
            }
            if (isInBoard(files[1], rank)) {
                sq[counter++] = new Square(files[1], rank);
            }
            if (isInBoard(file, ranks[0])) {
                sq[counter++] = new Square(file, ranks[0]);
            }
            if (isInBoard(file, ranks[1])) {
                sq[counter++] = new Square(file, ranks[1]);
            }
        }

        Square[] full = new Square[counter];
        for (int i = 0; i < counter; i++) {
            full[i] = sq[i];
        }

        return full;
    }
}
