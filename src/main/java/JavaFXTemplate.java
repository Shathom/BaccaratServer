import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class JavaFXTemplate extends Application {

	private TextField portNumberField;
	private Text portNumberPrompt;
	private HBox buttons;
	private VBox portNumber, butAndNumber, listViewAndLeave;
	private Button startServer, leaveServer, exit;
	private HashMap<String, Scene> sceneMap;
	Server serverConnection;
	private EventHandler<ActionEvent> startServerHandler, exitHandler, leaveServerhandler;
	ListView<String> serverUpdates;
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Baccarat Server");
		
		this.startServer = new Button("Start Server");
		this.startServer.setStyle("-fx-pref-width: 300px");
		this.startServer.setStyle("-fx-pref-height: 300px");
		
		this.startServer.setOnAction(e->{ primaryStage.setScene(sceneMap.get("startServer"));
											primaryStage.setTitle("This is the Server");
				serverConnection = new Server(data -> {
					Platform.runLater(()->{
						serverUpdates.getItems().add(data.toString());
					});

				});
											
		});
		
		
		serverUpdates = new ListView<String>();
		
		sceneMap = new HashMap<String, Scene>();
		
		sceneMap.put("startServer",  createPortNumberScene());
		sceneMap.put("gameInProgress",  gamesInProgessScene());
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
		
		
	}
	
	public Scene createPortNumberScene() {
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		pane.setStyle("-fx-background-color: coral");
	
	
		return new Scene(pane, 500, 400);
		
		
	}
	
	public Scene gamesInProgessScene() {
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		pane.setStyle("-fx-background-color: coral");
		
		pane.setCenter(serverUpdates);
	
		return new Scene(pane, 500, 400);
		
		
	}
	

}
