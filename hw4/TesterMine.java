import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;

public class TesterMine {

    static class EvilSquare extends Square {
        private char r;
        private char f;
        public EvilSquare(char rank, char file) throws InvalidSquareException {
            super('a', '1');
            r = rank;
            f = file;
        }
        @Override
        public char getRank() {
            return r;
        }
        @Override
        public char getFile() {
            return f;
        }
        @Override
        public String toString() {
            return "" + f + r;
        }
    }

    public static void main(String[] args) throws InvalidSquareException {
        // try {
        //     Square s = new Square("c9");
        // } catch (InvalidSquareException e) {
        //     System.out.println("InvalidSquareException for invalid square: "
        //                 + e.getMessage());
        // }



        // Square s2 = new Square('a','1');
        // System.out.println('c' == s.getFile());
        // System.out.println('5' == s.getRank());
        // System.out.println("a1".equals(s2.toString()));
        Set squares = new SquareSet();
        try {
            squares.add(new Square("a1"));
            squares.add(new Square("b2"));
            squares.add(new Square("d2"));
            squares.add(new Square("e2"));
            squares.add(new Square("b2"));
            squares.add(new Square('h','1'));
            squares.add(new Square('g','1'));
            squares.add(new Square('g','2'));
            //squares.add(new Square('a','9'));
        } catch (InvalidSquareException e) {
            System.out.println("InvalidSquareException for invalid square: "
                        + e.getMessage());
        }

        System.out.println("First set" + Arrays.toString(squares.toArray()));

        EvilSquare[] strArr = new EvilSquare[5];
        Object[] test = squares.toArray(strArr);
        System.out.println(Arrays.toString(test));


        // squares.add(new EvilSquare('a','9'));
        // squares.add(new Object());
        System.out.println(squares);
        // squares.add(null);
        // squares.add(null);
        // squares.add(null);

        // SquareSet sq = new SquareSet(squares);
        // Square[] sqArr = new Square[1];
        //
        // //sqArr = sq.toArray(sqArr);
        // System.out.print("The Final Array: ");
        // System.out.println(sq.toArray(new Square[1]));
        // System.out.println(sq.size());

        //
        // Set<Square> squares2 = new SquareSet<Square>();
        // squares2.add(new Square("b1"));
        // squares2.add(new Square("b2"));
        // squares2.add(new Square("b3"));
        //
        // Square[] sqArr = new Square[1];
        //
        // sqArr = squares.toArray(sqArr);
        // System.out.println(Arrays.toString(sqArr));
        // System.out.println(squares.size());
        //
        //
        //
        // Square[] sqArr2 = new Square[10];
        //
        // sqArr2 = squares2.toArray(sqArr2);
        // System.out.println(Arrays.toString(sqArr2));
        //
        // squares2.clear();
        // sqArr2 = squares2.toArray(sqArr2);
        // System.out.println(Arrays.toString(sqArr2));
        //
        //
        // System.out.println(squares.contains(new Square('a','7')));
        // System.out.println(squares.size());


    }

}
