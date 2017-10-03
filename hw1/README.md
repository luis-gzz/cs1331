# Solution Description

Write a class called PgnReader that contains the following public static methods:

**tagValue** takes two String arguments: a tag name and a String which contains a PGN-formatted game, and returns a String containing the value from the tag name tag pair in the PGN game text. If there is no tag name tag pair, return "NOT GIVEN".

**finalPosition** takes a single String argument which contains a PGN-formatted game and returns a String containing the game’s final position in Forsyth-Edwards Notation (FEN).

Write a **main method** that reads the file named in the PgnReader’s first command-line argument into a String and uses that String as the argument to each method above in order to print game information to the console. First, print the tag names and associated values for the core seven tags of the PGN standard: Event, Site, Date, Round, White, Black, Result. Then print a line reading “Final Position:” and a line displaying the final game position in FEN. Note that in this assignment we only care about the piece placement data, not the other elements of FEN such as active color or castling availability.

Each PGN file will contain a single game and you may assume that the PGN files are valid, and the move text contains only moves, no annotation text. Moves may end in check symbols (‘+’) or strength judgements (‘!’, ‘?’).

As your program reads the moves in a game it will need to maintain the state of the board, which you should store in a 2-d array. You will also need to translate between the algebraic notation used to represent moves in PGN, and the internal representation you use for board state, e.g., array indices.

**more at: http://cs1331.gatech.edu/fall2017/hw1/hw1-pgn-reader.html**
