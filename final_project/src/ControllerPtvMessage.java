
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerPtvMessage implements Initializable {

	@FXML
	public TextField	txPtvInput;
	@FXML
	public TextArea		txPtvOutput;

	Main				main;
	Client				c;
	String				message;
	Stage				anotherStage	= new Stage();
	String				nameClient			= "";

	public void setMain(Main main) {
		this.main = main;
	}

	public void setClient(Client c) {
		this.c = c;
	}

	public void setName(String nameClient) {
		this.nameClient = nameClient;
	}

	@FXML
	public void actPtvSubmit(ActionEvent event) throws IOException, RemoteException, SQLException {
		System.out.println(main);
		message = txPtvInput.getText();
		System.out.println("message:" + message);
		System.out.println("Ptv submit's Main" + main);
		
		txPtvOutput.setText(txPtvOutput.getText() + "\n" + c.userName + ": " +  message);
		main.sendToClient(nameClient, message);
	}

	public void sendToClient(String message) {
		txPtvOutput.setText(txPtvOutput.getText() + "\n" + message);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}
