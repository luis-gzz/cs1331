/** Represents the Rook chess piece
* @author lgonzalez35
* @version 1.0
*/
public class Rook extends Piece {

    /** Creates an Rook with all required parameters.
    * @param color the color of the piece
    */
    public Rook(Color color) {
        super(color);
    }

    @Override public String algebraicName() {
        return "R";
    }

    @Override public String fenName() {
        String fen = "";
        if (getColor() == Color.BLACK) {
            fen = "r";
        } else if (getColor() == Color.WHITE) {
            fen =  "R";
        }
        return fen;
    }

    @Override public Square[] movesFrom(Square square) {
        int r = rowChess2Arr(square.getRank());
        int c = colChess2Arr(square.getFile());

        int loops = 8;
        String goodSquares = "";
        for (int i = 1; i < loops; i++) {

            //Check down
            if (r - i <= 7 && r - i >= 0) {
                goodSquares = goodSquares + stringify(r - i, c) + " ";
            }
            //Check up
            if (r + i <= 7 && r + i >= 0) {
                goodSquares = goodSquares + stringify(r + i, c) + " ";
            }
            //Check Left
            if (c - i <= 7 && c - i >= 0) {
                goodSquares = goodSquares + stringify(r, c - i) + " ";
            }
            //Check right
            if (c + i <= 7 && c + i >= 0) {
                goodSquares = goodSquares + stringify(r, c + i) + " ";

            }
        }

        return stringToArray(goodSquares);
    }

    private String stringify(int rank, int file) {
        //Converts chars for file and rank to a string to then be used to
        //create the final Square[]
        char r = rowArr2Chess(rank);
        char f = colArr2Chess(file);
        String rankS = Character.toString(r);
        String fileS = Character.toString(f);

        return fileS + rankS;
    }

}
