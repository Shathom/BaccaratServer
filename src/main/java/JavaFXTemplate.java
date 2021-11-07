import java.util.HashMap;

import com.sun.prism.paint.Color;

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
import javafx.geometry.Pos;

public class JavaFXTemplate extends Application {

	private TextField portNumberField;
	private Text portNumberPrompt;
	private HBox buttons;
	private VBox portNumber, butAndNumber, listViewAndLeave;
	private Button startServer, leaveServer, exit;
	private HashMap<String, Scene> sceneMap;
	public Server serverConnection;
	private EventHandler<ActionEvent> startServerHandler, exitHandler, leaveServerhandler;
	ListView<String> serverUpdates;
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Baccarat Server");
		
//		this.startServer = new Button("Start Server");
//		this.startServer.setStyle("-fx-pref-width: 300px");
//		this.startServer.setStyle("-fx-pref-height: 300px");
//		
//		this.startServer.setOnAction(e->{ primaryStage.setScene(sceneMap.get("startServer"));
//											primaryStage.setTitle("This is the Server");
//				serverConnection = new Server(data -> {
//					Platform.runLater(()->{
//						serverUpdates.getItems().add(data.toString());
//					});
//
//				});
//											
//		});
		
		
		serverUpdates = new ListView<String>();
		
		sceneMap = new HashMap<String, Scene>();
		
		sceneMap.put("startServer",  createPortNumberScene());
		sceneMap.put("gamesInProgress",  gamesInProgessScene());
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
		
		primaryStage.setScene(sceneMap.get("gamesInProgress"));

		primaryStage.show(); 
		
		
	}
	
	public Scene createPortNumberScene() {
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
	
		startServer = new Button("Start Server");
		exit = new Button("Exit");
		exit.setStyle("-fx-font-size: 1.5em;");
		startServer.setStyle("-fx-font-size: 1.5em;");
		buttons = new HBox(30, startServer, exit);
		buttons.setAlignment(Pos.CENTER);
		portNumberPrompt = new Text("Please Enter Port Number:");
		portNumberPrompt.setFill(javafx.scene.paint.Color.WHITE);
		portNumberPrompt.setStyle("-fx-font-size: 1.5em;");
		portNumberField = new TextField();
		portNumberField.setStyle("-fx-font-size: 1.5em;");
		portNumberField.setFocusTraversable(false);
		portNumber = new VBox(30,portNumberPrompt, portNumberField);
		portNumber.setAlignment(Pos.CENTER);
		butAndNumber = new VBox(40, portNumber, buttons);
		butAndNumber.setAlignment(Pos.CENTER);
		pane.setCenter(butAndNumber);
		
		
	
		
		Scene scene = new Scene(pane, 700, 600);
		scene.getRoot().setStyle("-fx-background-color: #008000 ;" + "-fx-font-family: 'serif'");
		return scene;
	}
	
	public Scene gamesInProgessScene() {
		
		
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		
		
		leaveServer = new Button("Leave Server");
		leaveServer.setStyle("-fx-font-size: 1.5em;");
		listViewAndLeave = new VBox(30, serverUpdates, leaveServer);
		listViewAndLeave.setAlignment(Pos.CENTER);
		pane.setCenter(listViewAndLeave);
		
	
		
		
		Scene scene = new Scene(pane, 700, 600);
		scene.getRoot().setStyle("-fx-background-color: #008000 ;" + "-fx-font-family: 'serif'");
		return scene;
		
		
	}
	

}
