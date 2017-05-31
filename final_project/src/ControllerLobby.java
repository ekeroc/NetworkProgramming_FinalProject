
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerLobby implements Initializable {
	@FXML
	public TextField		txInput;
	@FXML
	public TextArea			txOutput;
	@FXML
	public ListView<String>	lvUserList;

	Main					main;
	Client					c;
	String message;

	public void setMain(Main main) {
		this.main = main;
	}

	public void setClient(Client c) {
		this.c = c;
	}

	ObservableList<String> data = FXCollections.observableArrayList("chocolate", "salmon", "gold", "coral",
			"darkorchid", "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue", "blueviolet", "brown");

	@FXML
	public void actSubmit(ActionEvent event) throws IOException, RemoteException, SQLException {
		message = txInput.getText();
		main.sendToAll(message);
	}

	public void writeToAll(String message) {
		txOutput.setText(txOutput.getText() + "\n" + message);
	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lvUserList.setItems(data);

	}
}
