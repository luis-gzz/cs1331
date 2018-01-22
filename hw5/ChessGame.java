import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * A class for a chess game
 * @author lgonzalez35
 * @version 1.0
 */
public class ChessGame {

    private List<Move> moves = new ArrayList<Move>();

    /**
     * The constructor for the ChessGame with all required parameters
     * @param list the list of moves
     */
    public ChessGame(List<Move> list) {
        moves.addAll(list);

    }

    /**
     * A getter mevod for the move at 'n' index
     * @param n the index for the move
     * @return Move
     */
    public Move getMove(int n) {
        return moves.get(n);
    }

    /**
     * Getter method for the list of moves
     * @return the list of moves
     */
    public List<Move> getMoves() {
        return this.moves;
    }

    /**
     * Returns a filtered List of moves containing moves
     * @return a list of moves with comments
     */
    public List<Move> getMovesWithComment() {
        return filter((move) -> move.getWhitePly().getComment().isPresent()
            || move.getBlackPly().getComment().isPresent());
    }

    /**
     * Returns a filtered List of moves not containing moves
     * @return a list of moves without comments
     */
    public List<Move> getMovesWithoutComment() {
        return filter(new Predicate<Move>() {
            @Override public boolean test(Move move) {
                return !move.getWhitePly().getComment().isPresent()
                    && !move.getBlackPly().getComment().isPresent();
            }
        });
    }

    /**
     * Returns a filtered List of moves containing passed piece
     * @param p the piece to filter by
     * @return a list of moves with said piece
     */
    public List<Move> getMovesWithPiece(Piece p) {
        return filter(new PiecePredicate(p));
    }

    /**
     * A function to filter the list with a Predicate
     * @param  filter a filtering predicate
     * @return a list of filtered moves
     */
    private List<Move> filter(Predicate<Move> filter) {
        List<Move> newList = new ArrayList<Move>();
        newList.addAll(moves);
        newList.removeIf(filter.negate());

        return newList;
    }

    private class PiecePredicate implements Predicate<Move> {
        private Piece p;

        public PiecePredicate(Piece p) {
            this.p = p;
        }

        @Override public boolean test(Move move) {
            String moveWType = move.getWhitePly().getPiece().algebraicName();
            String moveBType = move.getBlackPly().getPiece().algebraicName();
            String pType = p.algebraicName();

            return moveWType.equals(pType) || moveBType.equals(pType);
        }
    }

}
