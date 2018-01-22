/** Represents the knight chess piece
* @author lgonzalez35
* @version 1.0
*/
public class Knight extends Piece {

    /** Creates a Knight with all required parameters.
    * @param c the color of the piece
    */
    public Knight(Color c) {
        super(c);
    }

    @Override public String algebraicName() {
        return "N";
    }

    @Override public String fenName() {
        return getColor() == Color.WHITE ? "N" : "n";
    }

    @Override public Square[] movesFrom(Square square) {
        Square[] sq = new Square[8];
        int counter = 0;
        char rank = square.getRank();
        char file = square.getFile();
        char[] ranks = new char[]{(char) (rank - 2), (char) (rank - 1),
            (char) (rank + 1), (char) (rank + 2)};
        char[] files = new char[]{(char) (file - 2), (char) (file - 1),
            (char) (file + 1), (char) (file + 2)};

        if (isInBoard(files[0], ranks[1])) {
            sq[counter++] = new Square(files[0], ranks[1]);
        }
        if (isInBoard(files[0], ranks[2])) {
            sq[counter++] = new Square(files[0], ranks[2]);
        }
        if (isInBoard(files[1], ranks[0])) {
            sq[counter++] = new Square(files[1], ranks[0]);
        }
        if (isInBoard(files[1], ranks[3])) {
            sq[counter++] = new Square(files[1], ranks[3]);
        }
        if (isInBoard(files[2], ranks[0])) {
            sq[counter++] = new Square(files[2], ranks[0]);
        }
        if (isInBoard(files[2], ranks[3])) {
            sq[counter++] = new Square(files[2], ranks[3]);
        }
        if (isInBoard(files[3], ranks[1])) {
            sq[counter++] = new Square(files[3], ranks[1]);
        }
        if (isInBoard(files[3], ranks[2])) {
            sq[counter++] = new Square(files[3], ranks[2]);
        }

        Square[] full = new Square[counter];
        for (int i = 0; i < counter; i++) {
            full[i] = sq[i];
        }

        return full;
    }
}
