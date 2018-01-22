/** Represents the knight chess piece
* @author lgonzalez35
* @version 1.0
*/
public class Knight extends Piece {

    /** Creates a Knight with all required parameters.
    * @param color the color of the piece
    */
    public Knight(Color color) {
        super(color);
    }

    @Override public String algebraicName() {
        return "N";
    }

    @Override public String fenName() {
        String fen = "";
        if (getColor() == Color.BLACK) {
            fen = "n";
        } else if (getColor() == Color.WHITE) {
            fen =  "N";
        }
        return fen;
    }

    @Override public Square[] movesFrom(Square square) {
        int r = rowChess2Arr(square.getRank());
        int c = colChess2Arr(square.getFile());

        int[][] arrPoss = {{r + 1, c + 2}, {r - 1, c + 2}, {r - 2, c + 1}, {r
                    - 2, c - 1}, {r - 1, c - 2}, {r + 1, c - 2}, {r + 2, c
                    - 1}, {r + 2, c + 1}};

        String goodSquares = "";

        for (int i = 0; i < arrPoss.length; i++) {
            if (arrPoss[i][0] >= 0 && arrPoss[i][0] <= 7 && arrPoss[i][1]
                        >= 0 && arrPoss[i][1] <= 7) {

                String file = Character.toString(colArr2Chess(arrPoss[i][1]));
                String rank = Character.toString(rowArr2Chess(arrPoss[i][0]));
                goodSquares = goodSquares + file + rank + " ";

            }
        }

        return stringToArray(goodSquares);
    }

}
