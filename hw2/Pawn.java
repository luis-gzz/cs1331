/** Represents the Pawn chess piece
* @author lgonzalez35
* @version 1.0
*/
public class Pawn extends Piece {

    /** Creates an Pawn with all required parameters.
    * @param color the color of the piece
    */
    public Pawn(Color color) {
        super(color);
    }

    @Override public String algebraicName() {
        return "";
    }

    @Override public String fenName() {
        String fen = "";
        if (getColor() == Color.BLACK) {
            fen = "p";
        } else if (getColor() == Color.WHITE) {
            fen =  "P";
        }
        return fen;
    }

    @Override public Square[] movesFrom(Square square) {
        int r = rowChess2Arr(square.getRank());
        int c = colChess2Arr(square.getFile());

        String goodSquares = "";
        if (getColor() == Color.BLACK) {
            //Check to see if its at the end of the board
            if (r == 7) {
                Square[] s = {};
                return s;
            }

            //Check to see if its the initial move
            if (r == 1) {
                String rank = Character.toString(rowArr2Chess(r + 2));
                String file = Character.toString(colArr2Chess(c));

                goodSquares = goodSquares + file + rank + " ";
            }
            // Any other move
            String rank = Character.toString(rowArr2Chess(r + 1));
            String file = Character.toString(colArr2Chess(c));

            goodSquares = goodSquares + file + rank + " ";

        } else if (getColor() == Color.WHITE) {
            //Check to see if its at the end of the board
            if (r == 0) {
                Square[] s = {};
                return s;
            }

            //Check to see if its the initial move
            if (r == 6) {
                String rank = Character.toString(rowArr2Chess(r - 2));
                String file = Character.toString(colArr2Chess(c));

                goodSquares = goodSquares + file + rank + " ";
            }
            // Any other move
            String rank = Character.toString(rowArr2Chess(r - 1));
            String file = Character.toString(colArr2Chess(c));

            goodSquares = goodSquares + file + rank + " ";

        }

        return stringToArray(goodSquares);

    }

}
