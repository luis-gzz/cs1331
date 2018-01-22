/** Represents the King chess piece
* @author lgonzalez35
*/
public class King extends Piece {

    /** Creates a King with all required parameters.
    * @param color the color of the piece
    */
    public King(Color color) {
        super(color);
    }

    @Override public String algebraicName() {
        return "K";
    }

    @Override public String fenName() {
        String fen = "";
        if (getColor() == Color.BLACK) {
            fen = "k";
        } else if (getColor() == Color.WHITE) {
            fen =  "K";
        }
        return fen;
    }

    @Override public Square[] movesFrom(Square square) {
        int r = rowChess2Arr(square.getRank());
        int c = colChess2Arr(square.getFile());

        int loops = 2;
        String goodSquares = "";
        for (int i = 1; i < loops; i++) {

            //Check bottom right path
            if (r + i <= 7 && r + i >= 0 && c + i <= 7 && c + i >= 0) {
                goodSquares = goodSquares + stringify(r + i, c + i) + " ";
            }
            //Check top right path
            if (r - i <= 7 && r - i >= 0 && c + i <= 7 && c + i >= 0) {
                goodSquares = goodSquares + stringify(r - i, c + i) + " ";
            }
            //check bottom left path
            if (r + i <= 7 && r + i >= 0 && c - i <= 7 && c - i >= 0) {
                goodSquares = goodSquares + stringify(r + i, c - i) + " ";
            }
            //check top left path
            if (r - i <= 7 && r - i >= 0 && c - i <= 7 && c - i >= 0) {
                goodSquares = goodSquares + stringify(r - i, c - i) + " ";
            }

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
