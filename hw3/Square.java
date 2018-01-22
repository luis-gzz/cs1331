/** Represents a chessboard square
* @author lgonzalez35
*/
public class Square {

    private char file = ' ';
    private char rank = ' ';
    private String strFileRank = "";

    /** Creates an Square using a file and rank char
    *
    * @param file the file(char) of the square on the board
    * @param rank the rank(char) of the square on the board
    * @throws InvalidSquareException Handles any unsupported squares
    */
    public Square(char file, char rank) throws InvalidSquareException {
        this(Character.toString(file) + Character.toString(rank));

    }

    /** Creates an Square using a string containing the rank and file
    *
    * @param name a string representation of chess square
    * @throws InvalidSquareException Handles any unsupported squares
    */
    public Square(String name) throws InvalidSquareException {
        char f;
        char r;

        if (name.length() != 2) {
            throw new InvalidSquareException(name);

        } else {
            f = name.charAt(0);
            r = name.charAt(1);
        }

        if (!(f >= 'a' && f <= 'h') || !(r >= '1' && r <= '8')) {
            throw new InvalidSquareException(name);

        } else {
            this.file = f;
            this.rank = r;
            this.strFileRank = toString();
        }
    }

    /**
    * @return a string representation of the rank and file of the square
    */
    @Override public String toString() {
        return String.valueOf(file) + String.valueOf(rank);
    }

    /**
    * @return true if the two squares share a rank and file else false
    */
    @Override public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Square)) {
            return false;
        }

        Square sq = (Square) obj;
        if (this.getFile() == sq.getFile()
            && this.getRank() == sq.getRank()) {
            return true;
        }

        return false;
    }

    /**
    * @return the square's file char
    */
    public char getFile() {
        return file;
    }

    /**
    * @return the square's rank char
    */
    public char getRank() {
        return rank;
    }

    public int hashCode() {
        return 1;
    }


}
