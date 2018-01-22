/** Represents a chessboard square
* @author lgonzalez35
*/
public class Square {

    private char file = ' ';
    private char rank = ' ';
    private String strFileRank = "";

    /** Creates an Square with all required parameters.
    * @param file the file of the square on the board
    * @param rank the rank of the square on the board
    */
    public Square(char file, char rank) {
        this.file = file;
        this.rank = rank;
        this.strFileRank = toString();
    }

    /**
    * Creates an Square using a string containing the rank and file
    */
    public Square(String name) {
        this(name.charAt(0), name.charAt(1));

    }

    /**
    * @return a string of the rank and file of the square
    */
    public String toString() {
        return String.valueOf(file) + String.valueOf(rank);
    }

    /**
    * @return true if the two sqaures share rank and file else return false
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
    * @return the square's file
    */
    public char getFile() {
        return file;
    }

    /**
    * @return the square's rank
    */
    public char getRank() {
        return rank;
    }

    public int hashCode() {
        return 1;
    }


}
