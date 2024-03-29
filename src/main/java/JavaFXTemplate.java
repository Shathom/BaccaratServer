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
import javafx.scene.control.RadioButton;
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
	private EventHandler<ActionEvent> startServerHandler;
	ListView<String> serverUpdates;
	public BaccaratInfo data;
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Baccarat Server");

		serverUpdates = new ListView<String>();
		
		sceneMap = new HashMap<String, Scene>();
		
		sceneMap.put("startServer",  createPortNumberScene(primaryStage));
		sceneMap.put("gamesInProgress",  gamesInProgessScene());
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
		
		
	    leaveServer.setOnAction(e -> primaryStage.close());
	    
		primaryStage.setScene(sceneMap.get("startServer"));

		primaryStage.show(); 
		
		
	}
	
	public Scene createPortNumberScene(Stage primaryStage) {
		
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
	
		startServer = new Button("Start Server");
		startServer.setStyle("-fx-font-size: 1.5em;" +"-fx-background-color: linear-gradient(#ffd65b, #e68400),linear-gradient(#ffef84, #f2ba44), linear-gradient(#ffea6a, #efaa22), linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%), linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9),rgba(255,255,255,0));" + "-fx-background-radius: 30;" + "-fx-background-insets: 0,1,2,3,0;" + "-fx-text-fill: #654b00;" + "-fx-font-weight: bold;");

		
		exit = new Button("Exit");
		exit.setStyle("-fx-font-size: 1.5em;" +"-fx-background-color: linear-gradient(#ffd65b, #e68400),linear-gradient(#ffef84, #f2ba44), linear-gradient(#ffea6a, #efaa22), linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%), linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9),rgba(255,255,255,0));" + "-fx-background-radius: 30;" + "-fx-background-insets: 0,1,2,3,0;" + "-fx-text-fill: #654b00;" + "-fx-font-weight: bold;");

		
		buttons = new HBox(30, startServer, exit);
		buttons.setAlignment(Pos.CENTER);
		
		portNumberPrompt = new Text("Please Enter Port Number:");
		portNumberPrompt.setFill(javafx.scene.paint.Color.WHITE);
		portNumberPrompt.setStyle("-fx-font-size: 1.5em;" + "-fx-font-weight: bold;");
		
		portNumberField = new TextField();
		portNumberField.setStyle("-fx-font-size: 1.5em;" + "-fx-background-radius: 20;");
		portNumberField.setFocusTraversable(false);
		
		portNumber = new VBox(30,portNumberPrompt, portNumberField);
		portNumber.setAlignment(Pos.CENTER);
		butAndNumber = new VBox(40, portNumber, buttons);
		butAndNumber.setAlignment(Pos.CENTER);
		pane.setCenter(butAndNumber);

		
		startServerHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				int portNumber = Integer.parseInt(portNumberField.getText());
				primaryStage.setScene(sceneMap.get("gamesInProgress"));
				serverConnection = new Server(data -> {
					Platform.runLater(()->{
						serverUpdates.getItems().add(data.toString());
					});

				}, portNumber);
											
			}
		};
		
		
		startServer.setOnAction(startServerHandler);
		exit.setOnAction(f->Platform.exit());
		
		Scene scene = new Scene(pane, 700, 600);
		scene.getRoot().setStyle("-fx-background-color: #008000 ;" + "-fx-font-family: 'serif'");
		return scene;
	}
	
	public Scene gamesInProgessScene() {
		
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		
		
		leaveServer = new Button("Leave Server");
		leaveServer.setStyle("-fx-font-size: 1.5em;" +"-fx-background-color: linear-gradient(#ffd65b, #e68400),linear-gradient(#ffef84, #f2ba44), linear-gradient(#ffea6a, #efaa22), linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%), linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9),rgba(255,255,255,0));" + "-fx-background-radius: 30;" + "-fx-background-insets: 0,1,2,3,0;" + "-fx-text-fill: #654b00;" + "-fx-font-weight: bold;");

		listViewAndLeave = new VBox(30, serverUpdates, leaveServer);
		listViewAndLeave.setAlignment(Pos.CENTER);
		pane.setCenter(listViewAndLeave);
		

		Scene scene = new Scene(pane, 700, 600);
		scene.getRoot().setStyle("-fx-background-color: #008000 ;" + "-fx-font-family: 'serif'");
		return scene;
		
		
	}
	

}
