import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;

/**
 *  A basic GUI to display information about chess getGames
 *
 * @author lgonzalez35
 * @version 1.0
 */
public class ChessGui extends Application {

    @Override public void start(Stage mainStage) {

        //Creating ObservableList from the database
        ChessDb database = new ChessDb();
        ObservableList<ChessGame> games
                = FXCollections.observableArrayList(database.getGames());
        TableView<ChessGame> mainTable = createTable(games);

        Button viewBtn = new Button();
        viewBtn.setText("View Game");
        viewBtn.disableProperty().bind(Bindings.isEmpty(mainTable
                .getSelectionModel().getSelectedItems()));
        viewBtn.setOnAction(event -> {
                gameView(mainTable.getSelectionModel().getSelectedItem());
            });

        Button dismissBtn = new Button();
        dismissBtn.setText("Dismiss");
        dismissBtn.setOnAction(event -> Platform.exit());

        HBox buttons = new HBox();
        buttons.getChildren().addAll(viewBtn, dismissBtn);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(mainTable, buttons);


        mainStage.setTitle("Chess Game Deluxe");

        Scene scene = new Scene(vbox, 850, 400);
        mainStage.setScene(scene);
        mainStage.show();

    }

    private void gameView(ChessGame game) {
        Stage metadataStage = new Stage();

        Label t = new Label("EVENT: " + game.getEvent() + "\n\n"
                + "SITE: " + game.getSite() + "\n\n"
                + "DATE: " + game.getDate() + "\n\n"
                + "WHITE: " + game.getWhite() + "\n\n"
                + "BLACK: " + game.getBlack() + "\n\n"
                + "RESULT: " + game.getResult() + "\n\n"
                + "MOVES:");
        t.setMaxWidth(385);
        t.setWrapText(true);


        ListView<String> listOfMoves =
                new ListView<String>(createMoveList(game));

        Button dismissBtn = new Button();
        dismissBtn.setText("Dismiss");
        dismissBtn.setOnAction(event -> metadataStage.close());

        HBox hbox = new HBox();
        hbox.getChildren().addAll(dismissBtn);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(t, listOfMoves, hbox);

        metadataStage.setTitle("Game Info");
        Scene scene = new Scene(vbox, 400, 750);
        metadataStage.setScene(scene);
        metadataStage.show();

    }

    private TableView<ChessGame> createTable(ObservableList<ChessGame> games) {
        // Create the table
        TableView<ChessGame> mainTable = new TableView<ChessGame>();

        // Create the columns
        TableColumn<ChessGame, String> eventCol
                = new TableColumn<ChessGame, String>("Event");
        eventCol.setCellValueFactory(new PropertyValueFactory("event"));
        TableColumn<ChessGame, String> siteCol
                = new TableColumn<ChessGame, String>("Site");
        siteCol.setCellValueFactory(new PropertyValueFactory("site"));
        TableColumn<ChessGame, String> dateCol
                = new TableColumn<ChessGame, String>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory("date"));
        TableColumn<ChessGame, String> whiteCol
                = new TableColumn<ChessGame, String>("White");
        whiteCol.setCellValueFactory(new PropertyValueFactory("white"));
        TableColumn<ChessGame, String> blackCol
                = new TableColumn<ChessGame, String>("Black");
        blackCol.setCellValueFactory(new PropertyValueFactory("black"));
        TableColumn<ChessGame, String> resultCol
                = new TableColumn<ChessGame, String>("Result");
        resultCol.setCellValueFactory(new PropertyValueFactory("result"));

        // Put it all together
        mainTable.setItems(games);
        mainTable.getColumns().setAll(eventCol, siteCol, dateCol, whiteCol,
                blackCol, resultCol);
        mainTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return mainTable;
    }

    private ObservableList<String> createMoveList(ChessGame game) {
        List<String> moves = new ArrayList<>();
        try {
            int i = 1;
            while (true) {
                moves.add(game.getMove(i));
                i++;
            }
        } catch (IndexOutOfBoundsException exception) {
            //System.out.print(exception.getMessage());
        }

        return FXCollections.observableArrayList(moves);
    }

    /**
     * The man method used for launching the application
     * @param args A string array with console parameters
     */
    public static void main(String[] args) {
        launch(args);
    }


}
