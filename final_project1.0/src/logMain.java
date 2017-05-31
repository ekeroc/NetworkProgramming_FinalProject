
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.*;
public class logMain extends Application {
	@Override 
	public void start(Stage primaryStage) {
		try {
			//parent 相當 Object
			Parent fxmlRoot =  FXMLLoader.load(getClass().getResource("logPage.fxml"));
			
//			Button logButton = (Button)fxmlRoot.lookup("#logButton");
//            logButton.setOnAction(e->logButton.setText("登入了"));
			Scene scene = new Scene(fxmlRoot);
			primaryStage.setTitle("Quick Chat");
			primaryStage.sizeToScene();
			primaryStage.setScene(scene);
			
			//Show Stage
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
