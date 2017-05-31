
import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.print.attribute.standard.MediaName;

import org.omg.CORBA.PUBLIC_MEMBER;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerLogin  {

	@FXML
	public Button			btnLogin;
	@FXML
	public Button			btnSubmit;

	@FXML
	public TextField		txAccount;


	
	Main					main;
	Client					c;
	String					name;


	public void setMain(Main main) {
		this.main = main;
	}

	public void setClient(Client c) {
		this.c = c;
	}

	@FXML
	public void actLogin(ActionEvent event) throws IOException, RemoteException, SQLException {

		System.out.println(txAccount.getText());
		name = txAccount.getText();
		main.signIn(name);
		//TODO main.primaryStage.hide();
		
		Stage anotherStage = new Stage();
		FXMLLoader anotherLoader = new FXMLLoader(); // FXML for second stage
		anotherLoader.setLocation(Main.class.getResource("mainPage.fxml"));
		try {
			Parent anotherRoot = anotherLoader.load();
			Scene anotherScene = new Scene(anotherRoot);
			anotherStage.setScene(anotherScene);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControllerLobby controllerLobby = anotherLoader.getController();
		controllerLobby.setMain(main);
		c.setLobby(controllerLobby);
		
		anotherStage.show();
	}




}
