import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A basic class to hold ChessGame database
 *
 * @author lgonzalez35
 * @version 1.0
 */
public class ChessGame {

    private StringProperty event = new SimpleStringProperty(this, "NA");
    private StringProperty site = new SimpleStringProperty(this, "NA");
    private StringProperty date = new SimpleStringProperty(this, "NA");
    private StringProperty white = new SimpleStringProperty(this, "NA");
    private StringProperty black = new SimpleStringProperty(this, "NA");
    private StringProperty result = new SimpleStringProperty(this, "NA");
    private List<String> moves;

    /**
     * ChessGame constructor with all the proper arguments
     * @param  event         name of event
     * @param  site          location of event
     * @param  date          date of event
     * @param  white         white player
     * @param  black         black player
     * @param  result        result of game
     */
    public ChessGame(String event, String site, String date,
                     String white, String black, String result) {
        this.event.set(event);
        this.site.set(site);
        this.date.set(date);
        this.white.set(white);
        this.black.set(black);
        this.result.set(result);
        moves = new ArrayList<>();
    }

    /**
     * Add a move to the list
     * @param move the move to add
     */
    public void addMove(String move) {
        moves.add(move);
    }

    /**
     * Get the move at n index
     * @param  n   index of wanted move
     * @return move at n index
     */
    public String getMove(int n) {
        return moves.get(n - 1);
    }

    /**
     * Get name of event
     * @return event name
     */
    public String getEvent() {
        return event.get();
    }

    /**
     * Get site of event
     * @return event site
     */
    public String getSite() {
        return site.get();
    }

    /**
     * Get date of event
     * @return event date
     */
    public String getDate() {
        return date.get();
    }

    /**
     * Get white player
     * @return white player name
     */
    public String getWhite() {
        return white.get();
    }

    /**
     * Get balck player
     * @return black player name
     */
    public String getBlack() {
        return black.get();
    }

    /**
     * Get result of game
     * @return results
     */
    public String getResult() {
        return result.get();
    }
}
