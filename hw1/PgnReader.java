import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class PgnReader {

    private static char[][] chessBoard = new char[8][8];

    public static String finalPosition(String fmtdGame) {

        //printBoard();
        prepareToMove(getArrayOfMoves(fmtdGame));

        String fenStr = "";

        //Put the board into FEN notation
        for (int row = 0; row < 8; row++) {

            for (int col = 0; col < chessBoard[row].length; col++) {
                fenStr = fenStr + String.valueOf(chessBoard[row][col]);
            }
            fenStr = fenStr + "/";
        }

        //Remove * from the FEN notation
        int atCounter = 0;
        for (int i = 0; i < fenStr.length(); i++) {
            if (fenStr.charAt(i) == '*') {
                atCounter++;

            } else if (fenStr.charAt(i) != '*' && atCounter > 0) {
                fenStr = fenStr.substring(0, i - 1) + atCounter
                        + fenStr.substring(i, fenStr.length());
                atCounter = 0;
            }
        }
        fenStr = fenStr.replace("*", ""); //get rid of the * symbols

        return fenStr.substring(0, fenStr.length() - 1); // get rid of extra '/'
        //return Arrays.toString(getArrayOfMoves(fmtdGame));
    }

    public static String[] getArrayOfMoves(String fmtdGame) {
        String theMoves = "";
        try {
            Scanner fileInput = new Scanner(new File("./" + fmtdGame));

            while (fileInput.hasNextLine()) {
                String newLine = fileInput.nextLine();

                if (newLine.length() > 0 && '[' != newLine.charAt(0)) {
                    if (' ' != newLine.charAt(newLine.length() - 1)) {
                        newLine = newLine + " ";
                    }
                    theMoves = theMoves + newLine;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        theMoves = theMoves.substring(2, theMoves.length());
        String[] movesArray
            = theMoves.split("[0-9][.] | [0-9][0-9][.] | [0-9][0-9][0-9][.]");

        return movesArray;
    }

    public static void prepareToMove(String[] movesArr) {
        char type = ' ';
        char moveSq = ' ';
        int moveN = 0;

        //counter to keep track of white or black(odd - w/even - b)
        int counter = 1;
        for (int i = 0; i < movesArr.length; i++) {
            // Split the string of moves into array of individual moves
            String data = movesArr[i];
            String dataTrim = data.trim();
            String[] parts  = dataTrim.split(" ");

            for (int j = 0; j < parts.length; j++) {
                // Store individual moves to analyze
                boolean castle = false;
                boolean capture = false;
                String strMove = parts[j];
                char pawnOrigin = ' ';
                char promo = ' ';

                //Ignore quality markers...
                if (parts[j].indexOf("?") != -1) {
                    parts[j] = parts[j].replace("?", "");
                } else if (parts[j].indexOf("!") != -1) {
                    parts[j] = parts[j].replace("!", "");
                } else if (parts[j].indexOf("#") != -1) {
                    parts[j] = parts[j].replace("#", "");
                } else if (parts[j].indexOf("+") != -1) {
                    parts[j] = parts[j].replace("+", "");
                }

                // Check if there is a pawn promotion in this move
                if (parts[j].indexOf("=") != -1) {
                    String temp1 = parts[j].substring(0, parts[j].indexOf("="));
                    String temp2 = parts[j].substring(parts[j].indexOf("="),
                            parts[j].length());
                    promo = temp2.charAt(1);

                    parts[j] = temp1;
                }

                // Check if there is a capture of castle in this move
                if (parts[j].indexOf("x") != -1) {
                    capture = true;
                    String[] bits = parts[j].split("x");

                    if (Character.isLowerCase(bits[0].charAt(0))) {
                        strMove = bits[1];
                        pawnOrigin = bits[0].charAt(0);
                    } else {
                        strMove = bits[0] + bits[1];
                    }
                } else if (parts[j].equals("O-O")) {
                    castle = true;
                    type = counter % 2 != 0 ? 'R' : 'r';
                    moveSq = 'f';
                    moveN = counter % 2 != 0 ? 1 : 8;

                } else if (parts[j].equals("O-O-O")) {
                    castle = true;
                    type = counter % 2 != 0 ? 'R' : 'r';
                    moveSq = 'd';
                    moveN = counter % 2 != 0 ? 1 : 8;

                }

                // Extract move details from the move
                int k = 0;
                while (!castle && k < strMove.length()) {
                    if (Character.isLowerCase(strMove.charAt(k))) {
                        if (k == 0) {
                            type = counter % 2 != 0 ? 'P' : 'p';
                            //System.out.print(type);
                        }

                        moveSq = strMove.charAt(k);
                        //System.out.print(moveSq);

                    } else if (Character.isUpperCase(strMove.charAt(k))) {
                        type = counter % 2 != 0 ? strMove.charAt(k)
                                : Character.toLowerCase(strMove.charAt(k));
                        //System.out.print(type);

                    } else if (Character.isDigit(strMove.charAt(k))) {
                        moveN = Character.getNumericValue(strMove.charAt(k));
                        //System.out.print(moveN);
                    }

                    k++;
                }

                if (!castle) {
                    movePicker(type, moveSq, moveN, capture, pawnOrigin, promo);
                } else {
                    castler(type, moveSq, moveN, parts[j]);
                }

                counter++;
            }
            //System.out.print(Arrays.toString(parts));

        }
    }

    public static void castler(char type, char moveSq, int moveN,
        String castleType) {
        //Set the rook to castle position
        int row = translateRow(moveN);
        int col = translateCol(moveSq);

        chessBoard[row][col] = type;

        // Set the rooks old spot to blank
        char oldSq = castleType.equals("O-O") ? 'h' : 'a';
        int colOld = translateCol(oldSq);
        chessBoard[row][colOld] = '*';

        //Move the king
        int kingCol = castleType.equals("O-O") ? translateCol('g')
                : translateCol('c');
        char kingType = type == 'R' ? 'K' : 'k';
        chessBoard[row][kingCol] = kingType;

        //Set kings old spot to empty
        int oldKingCol = translateCol('e');
        int oldKingRow = type == 'R' ? translateRow(1) : translateRow(8);
        chessBoard[oldKingRow][oldKingCol] = '*';



    }

    public static void movePicker(char type, char moveSq, int moveN,
        boolean cap, char pOrigin, char promo) {
        int row = translateRow(moveN);
        int col = translateCol(moveSq);

        if (type == 'P') {
            pawnMove(row, col, type, cap, pOrigin, promo);

        } else if (type == 'p') {
            pawnMove(row, col, type, cap, pOrigin, promo);

        } else if (type == 'N') {
            knightMove(row, col, type, cap);

        } else if (type == 'n') {
            knightMove(row, col, type, cap);

        } else if (type == 'R') {
            rookMove(row, col, type, cap);

        } else if (type == 'r') {
            rookMove(row, col, type, cap);

        } else if (type == 'B') {
            bishopMove(row, col, type, cap);

        } else if (type == 'b') {
            bishopMove(row, col, type, cap);

        } else if (type == 'Q') {
            queenMove(row, col, type, cap);

        } else if (type == 'q') {
            queenMove(row, col, type, cap);

        } else if (type == 'K') {
            kingMove(row, col, type, cap);

        } else if (type == 'k') {
            kingMove(row, col, type, cap);
        }
    }

    public static void pawnMove(int r, int c, char type, boolean cap,
                                char origin, char promo) {
        // The bounds for moves
        boolean bounds1White = (r + 1 <= 7 && r + 1 >= 0 && c <= 7 && c >= 0);
        boolean bounds2White = (r + 2 <= 7 && r + 2 >= 0 && c <= 7 && c >= 0);

        boolean bounds1Black = (r - 1 <= 7 && r - 1 >= 0 && c <= 7 && c >= 0);
        boolean bounds2Black = (r - 2 <= 7 && r - 2 >= 0 && c <= 7 && c >= 0);

        // The bounds for captures
        boolean boundLCapW = (r + 1 <= 7 && r + 1 >= 0 && c + 1 <= 7
                && c + 1 >= 0);
        boolean boundRCapW = (r + 1 <= 7 && r + 1 >= 0 && c - 1 <= 7
                && c - 1 >= 0);

        boolean boundLCapB = (r - 1 <= 7 && r - 1 >= 0 && c + 1 <= 7
                && c + 1 >= 0);
        boolean boundRCapB = (r - 1 <= 7 && r - 1 >= 0 && c - 1 <= 7
                && c - 1 >= 0);

        // the if else checks whether a black or white piece is moving
        // and if its a capture or the new square isnt taken
        if (type == 'P' && chessBoard[r][c] == ('*') && !cap) {
            //is white
            if (bounds1White && chessBoard[r + 1][c] == (type)) {

                chessBoard[r + 1][c] = '*';
                chessBoard[r][c] = type;
            } else if (bounds2White && chessBoard[r + 2][c] == (type)) {
                //Only move to spaces if the first isnt blocked
                if (chessBoard[r + 1][c] == ('*')) {
                    chessBoard[r + 2][c] = '*';
                    chessBoard[r][c] = type;
                }
            }
        } else if (type == 'p' && chessBoard[r][c] == ('*') && !cap) {
            //is black
            if (bounds1Black && chessBoard[r - 1][c] == (type)) {

                chessBoard[r - 1][c] = '*';
                chessBoard[r][c] = type;
            } else if (bounds2Black && chessBoard[r - 2][c] == (type)) {
                //Only move to spaces if the first isnt blocked
                if (chessBoard[r - 1][c] == ('*')) {
                    chessBoard[r - 2][c] = '*';
                    chessBoard[r][c] = type;
                }
            }
        } else if (type == 'P' && cap && chessBoard[r][c] != ('*')) {
            //Capture for white pawn

            if (origin != ' ') {
                int c2 = translateCol(origin);
                chessBoard[r + 1][c2] = '*';
                chessBoard[r][c] = type;

            } else if (boundLCapW && chessBoard[r + 1][c + 1] == (type)) {
                //Left Capture
                chessBoard[r + 1][c + 1] = '*';
                chessBoard[r][c] = type;
            } else if (boundRCapW && chessBoard[r + 1][c - 1] == (type)) {
                //Right capture
                chessBoard[r + 1][c - 1] = '*';
                chessBoard[r][c] = type;
            }

        } else if (type == 'p' && cap && chessBoard[r][c] != ('*')) {
            //Capture for black pawn
            if (origin != ' ') {
                int c2 = translateCol(origin);
                chessBoard[r - 1][c2] = '*';
                chessBoard[r][c] = type;

            } else if (boundRCapB && chessBoard[r - 1][c - 1] == (type)) {
                //Right capture
                chessBoard[r - 1][c - 1] = '*';
                chessBoard[r][c] = type;
            } else if (boundLCapB && chessBoard[r - 1][c + 1] == (type)) {

                chessBoard[r - 1][c + 1] = '*';
                chessBoard[r][c] = type;
            }
        } else if (type == 'P' && cap && chessBoard[r][c] == ('*')) {
            //en passant move for white
            if (origin != ' ') {
                int c2 = translateCol(origin);
                chessBoard[r + 1][c2] = '*';
                chessBoard[r][c] = type;
                chessBoard[r + 1][c] = '*';
            }

        } else if (type == 'p' && cap && chessBoard[r][c] == ('*')) {
            //en passant move for black
            if (origin != ' ') {
                int c2 = translateCol(origin);
                chessBoard[r - 1][c2] = '*';
                chessBoard[r][c] = type;

                chessBoard[r - 1][c] = '*';
            }

        }

        // Promote the pawn if it reached the top or bottom
        if (chessBoard[r][c] == 'P' && r == 0) {
            chessBoard[r][c] = promo;

        } else if (chessBoard[r][c] == 'p' && r == 7) {
            promo = Character.toLowerCase(promo);
            chessBoard[r][c] = promo;
        }
    }

    public static void rookMove(int r, int c, char type, boolean cap) {
        boolean notFound = true;
        boolean blocked = false;
        int atR = 10;
        int atC = 10;

        boolean topBlocked = false;
        boolean btmBlocked = false;
        boolean rightBlocked = false;
        boolean leftBlocked = false;
        int loops = 0;

        //Since the K,Q, and R use this we must know how many squares to checks
        //the king can only move one space
        if (type == ('K') || type == ('k')) {
            loops = 2;
        } else {
            loops = 16;
        }

        for (int i = 0; i < loops; i++) {

            // the if else checks whether a black or white piece is moving
            // and if its a capture or the new square isnt taken
            //and wether the checked space is in bounds
            if (r - i <= 7 && r - i >= 0 && !btmBlocked) {
                if (((chessBoard[r - i][c] != ('*')//checks for any piece
                        && chessBoard[r - i][c] != (type))//other than type
                        || chessBoard[r][c] != ('*'))//square not taken
                        && !cap) { //not a capture

                    btmBlocked = true;
                } else if (chessBoard[r - i][c] == (type)) {
                    atR = r - i;
                    atC = c;
                    chessBoard[atR][atC] = '*';
                    chessBoard[r][c] = type;
                    notFound = false;
                    break;
                }
            }
            if (r + i <= 7 && r + i >= 0 && !topBlocked) {
                if (((chessBoard[r + i][c] != ('*') && chessBoard[r
                        + i][c] != (type))
                        || chessBoard[r][c] != ('*'))  && !cap) {
                    topBlocked = true;
                } else if (chessBoard[r + i][c] == (type)) {
                    atR = r + i;
                    atC = c;
                    chessBoard[atR][atC] = '*';
                    chessBoard[r][c] = type;
                    notFound = false;
                    break;
                }

            }
            if (c - i <= 7 && c - i >= 0 && !rightBlocked) {
                if (((chessBoard[r][c - i] != ('*') && chessBoard[r][c
                        - i] != (type))
                        || chessBoard[r][c] != ('*'))  && !cap) {
                    rightBlocked = true;
                } else if (chessBoard[i][c - i] == (type)) {
                    atR = r;
                    atC = c - i;
                    chessBoard[atR][atC] = '*';
                    chessBoard[r][c] = type;
                    notFound = false;
                    break;
                }

            }
            if (c + i <= 7 && c + i >= 0 && !leftBlocked) {
                if (((chessBoard[r][c + i] != ('*') && chessBoard[r][c
                        + i] != (type))
                        || chessBoard[r][c] != ('*'))  && !cap) {
                    leftBlocked = true;
                } else if (chessBoard[r][c + i] == (type)) {
                    atR = r;
                    atC = c + i;
                    chessBoard[atR][atC] = '*';
                    chessBoard[r][c] = type;
                    notFound = false;
                    break;
                }
            }
        }
    }

    public static void bishopMove(int r, int c, char type, boolean cap) {
        boolean notFound = true;
        int atR = 10;
        int atC = 10;

        boolean topRBlocked = false;
        boolean btmRBlocked = false;
        boolean btmLBlocked = false;
        boolean topLBlocked = false;
        int loops = 0;

        //Since the K,Q, and R use this we must know how many squares to checks
        //the king can only move one space
        if (type == 'K' || type == 'k') {
            loops = 2;
        } else {
            loops = 16;
        }

        for (int i = 1; i < loops; i++) {

            // the if else checks whether a black or white piece is moving
            // and if its a capture or the new square isnt taken
            // and wether the checked space is in bounds

            //Check Top right path
            if (r + i <= 7 && r + i >= 0 && c + i <= 7 && c + i >= 0
                    && !topRBlocked) {

                if ((((chessBoard[r + i][c + i] != ('*')
                        && chessBoard[r + i][c + i] != (type)))
                        || chessBoard[r][c] != ('*'))
                        && !cap) {

                    topRBlocked = true;
                } else if (chessBoard[r + i][c + i] == (type)) {
                    atR = r + i;
                    atC = c + i;
                    chessBoard[atR][atC] = '*';
                    chessBoard[r][c] = type;
                    notFound = false;
                    break;
                }
            }
            //Check bottom right path
            if (r - i <= 7 && r - i >= 0 && c + i <= 7 && c + i >= 0
                    && !btmRBlocked) {

                if (((chessBoard[r - i][c + i] != ('*') && chessBoard[r
                        - i][c + i] != (type))
                        || chessBoard[r][c] != ('*'))  && !cap) {
                    btmRBlocked = true;
                } else if (chessBoard[r - i][c + i] == (type)) {
                    atR = r - i;
                    atC = c + i;
                    chessBoard[atR][atC] = '*';
                    chessBoard[r][c] = type;
                    notFound = false;
                    break;
                }

            }
            //check top left path
            if (r + i <= 7 && r + i >= 0 && c - i <= 7 && c - i >= 0
                    && !topLBlocked) {
                if (((chessBoard[r + i][c - i] != ('*') && chessBoard[r
                        + i][c - i] != (type))
                        || chessBoard[r][c] != ('*'))  && !cap) {
                    topLBlocked = true;
                } else if (chessBoard[r + i][c - i] == (type)) {
                    atR = r + i;
                    atC = c - i;
                    chessBoard[atR][atC] = '*';
                    chessBoard[r][c] = type;
                    notFound = false;
                    break;
                }

            }
            //check bottom left path
            if (r - i <= 7 && r - i >= 0 && c - i <= 7 && c - i >= 0
                    && !btmLBlocked) {
                if (((chessBoard[r - i][c - i] != ('*') && chessBoard[r
                        - i][c - i] != (type))
                        || chessBoard[r][c] != ('*')) && !cap) {
                    btmLBlocked = true;
                } else if (chessBoard[r - i][c - i] == (type)) {
                    atR = r - i;
                    atC = c - i;
                    chessBoard[atR][atC] = '*';
                    chessBoard[r][c] = type;
                    notFound = false;
                    break;
                }
            }
        }

    }

    public static void knightMove(int r, int c, char type, boolean cap) {
        int[][] arrPoss = {{r + 1, c + 2}, {r - 1, c + 2}, {r - 2, c + 1}, {r
                    - 2, c - 1}, {r - 1, c - 2}, {r + 1, c - 2}, {r + 2, c
                    - 1}, {r + 2, c + 1}};

        for (int i = 0; i < arrPoss.length; i++) {
            if (arrPoss[i][0] >= 0 && arrPoss[i][0] <= 7 && arrPoss[i][1]
                        >= 0 && arrPoss[i][1] <= 7) {
                if ((type == 'N' || type == 'Q')) {

                    if (chessBoard[arrPoss[i][0]][arrPoss[i][1]] == (type)
                        && (chessBoard[r][c] != (type) || !cap)) {
                        chessBoard[arrPoss[i][0]][arrPoss[i][1]] = '*';
                        chessBoard[r][c] = type;
                    }

                } else if (type == 'n' || type == 'q') {

                    if (chessBoard[arrPoss[i][0]][arrPoss[i][1]] == (type)
                        && (chessBoard[r][c] != (type) || !cap)) {
                        chessBoard[arrPoss[i][0]][arrPoss[i][1]] = '*';
                        chessBoard[r][c] = type;
                    }
                }
            }
        }

    }

    public static void queenMove(int r, int c, char type, boolean cap) {
        if (type == 'Q') {
            //knightMove(r , c, 'Q', cap);
            rookMove(r , c, 'Q', cap);
            bishopMove(r , c, 'Q', cap);

        } else if (type == 'q') {
            //knightMove(r , c, 'q', cap);
            rookMove(r , c, 'q', cap);
            bishopMove(r , c, 'q', cap);
        }

    }

    public static void kingMove(int r, int c, char type, boolean cap) {
        rookMove(r, c, type, cap);
        bishopMove(r, c, type, cap);

    }

    public static int translateCol(char moveSq) {
        int num = 0;

        if (moveSq == 'a') {
            num = 0;
        } else if (moveSq == 'b') {
            num = 1;
        } else if (moveSq == 'c') {
            num = 2;
        } else if (moveSq == 'd') {
            num = 3;
        } else if (moveSq == 'e') {
            num = 4;
        } else if (moveSq == 'f') {
            num = 5;
        } else if (moveSq == 'g') {
            num = 6;
        } else if (moveSq == 'h') {
            num = 7;
        }

        return num;
    }

    public static int translateRow(int moveNum) {
        return (8 - moveNum);
    }

    public static String tagValue(String tagName, String fmtdGame) {
        String currentLine = "";
        String currentEvent = "";
        String currentInfo = "NOT GIVEN";


        try {
            Scanner fileInput = new Scanner(new File("./" + fmtdGame));

            while (!currentEvent.equals(tagName) && fileInput.hasNextLine()) {
                currentLine = fileInput.nextLine();

                if (currentLine.length() > 2) {
                    currentLine = currentLine.substring(1, currentLine.length()
                                    - 1);
                }
                String[] parts = currentLine.split(" ", 2);
                currentEvent = parts[0];
                if (currentEvent.equals(tagName)) {
                    currentInfo = parts[1].substring(1, parts[1].length() - 1);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return currentInfo;
    }

    public static void initBoard() {
        char[][] tempBoard = new char[8][8];
        for (int row = 0; row < tempBoard.length; row++) {
            for (int col = 0; col < tempBoard[row].length; col++) {
                if (row == 0 || row == 7) {
                    // Blacks are at the top here
                    if (col == 0 || col == 7) {
                        //Set the Rooks
                        tempBoard[row][col] = (row == 7) ? 'R' : 'r';
                    } else if (col == 1 || col == 6) {
                        //set the knights
                        tempBoard[row][col] = (row == 7) ? 'N' : 'n';
                    } else if (col == 2 || col == 5) {
                        //set the bishops
                        tempBoard[row][col] = (row == 7) ? 'B' : 'b';
                    } else if (col == 3) {
                        //set the queens
                        tempBoard[row][col] = (row == 7) ? 'Q' : 'q';
                    } else if (col == 4) {
                        //set the kings
                        tempBoard[row][col] = (row == 7) ? 'K' : 'k';
                    }
                } else if (row == 1 || row == 6) {
                    // Set pawn to P just to keep track of them
                    tempBoard[row][col] = (row == 6) ? 'P' : 'p';
                } else {
                    tempBoard[row][col] = '*';
                }
            }
        }

        setBoard(tempBoard);
    }

    public static void printBoard() {
        for (char[] row : chessBoard) {
            for (char i : row) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void setBoard(char[][] arr) {
        chessBoard = arr;
    }

    public static void main(String[] args) {
        String fileName = args[0];
        String[] allTags = {"Event", "Site", "Date", "Round", "White", "Black",
            "Result"};
        initBoard();

        for (int i = 0; i < allTags.length; i++) {
            System.out.format("%s: %s%n", allTags[i], tagValue(allTags[i],
                fileName));
        }
        System.out.println("Final Position:");
        System.out.println(finalPosition(fileName));

        //printBoard();
    }
}
