/** The parent class to different chess pieces
* @author lgonzalez35
*/
public abstract class Piece {

    private Color color;

    /** Creates an Piece with all required parameters.
    * @param color the color of the piece
    */
    public Piece(Color color) {
        this.color = color;
    }

    /**
    * @return the color of the piece
    */
    public Color getColor() {
        return color;
    }

    /**
    * @return piece's algebraic name
    */
    abstract String algebraicName();

    /**
    * @return piece's FEN notation name
    */
    abstract String fenName();

    /** Takes a Square object and calculates the possible moves on the board
    * @return squares the pieces can move to in a Square[]
    * @param square object of pieces location
    */
    abstract Square[] movesFrom(Square square);

    /** Changes file char to an int for use in array
    * @param moveSq file char based on chessboard
    * @return file int based on array board
    */
    public int colChess2Arr(char fileChar) {
        int num = 0;

        if (fileChar == 'a') {
            num = 0;
        } else if (fileChar == 'b') {
            num = 1;
        } else if (fileChar == 'c') {
            num = 2;
        } else if (fileChar == 'd') {
            num = 3;
        } else if (fileChar == 'e') {
            num = 4;
        } else if (fileChar == 'f') {
            num = 5;
        } else if (fileChar == 'g') {
            num = 6;
        } else if (fileChar == 'h') {
            num = 7;
        }

        return num;
    }

    /** Changes file int used in array to file char
    * @param moveSq file int based on array board
    * @return file char based on chessboard
    */
    public char colArr2Chess(int fileNum) {
        char letter = ' ';

        if (fileNum == 0) {
            letter = 'a';
        } else if (fileNum == 1) {
            letter = 'b';
        } else if (fileNum == 2) {
            letter = 'c';
        } else if (fileNum == 3) {
            letter = 'd';
        } else if (fileNum == 4) {
            letter = 'e';
        } else if (fileNum == 5) {
            letter = 'f';
        } else if (fileNum == 6) {
            letter = 'g';
        } else if (fileNum == 7) {
            letter = 'h';
        }

        return letter;
    }

    /** Translates row char to row int
    * @param moveChar row char based on chessboard
    * @return row int based on array board
    */
    public int rowChess2Arr(char rowChar) {
        int num = Character.getNumericValue(rowChar);
        return (8 - num);
    }

    /** Translates row int to row char
    * @param moveNum row int based on array board
    * @return row char based on chessboard
    */
    public char rowArr2Chess(int rowNum) {
        int tempNum = Math.abs(rowNum - 8);
        String tempStr = Integer.toString(tempNum);
        return tempStr.charAt(0);
    }

    /** converts string holding possible origins to an array of squares
    * @param str string containing squares in file/rank notation(a1 a2 b1 b2...)
    * @return an array of Square objects
    */
    public Square[] stringToArray(String str) {
        String[] temp = str.split(" ");
        Square[] moves = new Square[temp.length];

        for (int i = 0; i < temp.length; i++) {
            moves[i] = new Square(temp[i]);
        }

        return moves;
    }




}
