
import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.print.attribute.standard.MediaName;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ControllerLogin {

	@FXML
	public Button		btnLogin;
	@FXML
	public Button		btnSubmit;

	@FXML
	public TextField	txAccount;

	@FXML
	public TextField	txNotic;

	Main				main;
	Client				c;
	String				name;
	Stage				anotherStage	= new Stage();

	public void setMain(Main main) {
		this.main = main;
	}

	public void setClient(Client c) {
		this.c = c;
	}

	public Stage getStage() {
		return anotherStage;
	}

	@FXML
	public void actLogin(ActionEvent event) throws IOException, RemoteException, SQLException {

		FXMLLoader anotherLoader = new FXMLLoader(); // FXML for second stage
		anotherLoader.setLocation(Main.class.getResource("mainPage.fxml"));
		try {
			Parent anotherRoot = anotherLoader.load();
			Scene anotherScene = new Scene(anotherRoot);
			anotherStage.setScene(anotherScene);
			anotherStage.setTitle(txAccount.getText());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControllerLobby controllerLobby = anotherLoader.getController();
		controllerLobby.setMain(main);
		controllerLobby.setClient(c);
		c.setLobby(controllerLobby);

		anotherStage.show();

		System.out.println(txAccount.getText());
		name = txAccount.getText();
		main.signIn(name);
	}

	@FXML
	public void actCreateAcc(ActionEvent event) throws IOException, RemoteException, SQLException {
		String result;
		System.out.println(txAccount.getText());
		name = txAccount.getText();
		result = main.register(name);
		txNotic.setText(result);
	}

}
