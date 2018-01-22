public class Tester {

    public static void main(String[] args) throws InvalidSquareException {
        try {
            new Square("a");
        } catch (InvalidSquareException e) {
            System.out.println("InvalidSquareException for invalid square: "
                        + e.getMessage());
        }


        Square s = new Square("c5");
        Square s2 = new Square('a','1');
        System.out.println('c' == s.getFile());
        System.out.println('5' == s.getRank());
        System.out.println("a1".equals(s2.toString()));


    }

}
