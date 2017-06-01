
import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.print.attribute.standard.MediaName;

import org.omg.CORBA.PUBLIC_MEMBER;

import javafx.application.Platform;
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

public class ControllerNotic implements Initializable {

	@FXML
	public TextField	txNotic;

	String				name;
	Stage				stage	= new Stage();

	public void setMain(String name) {
		this.name = name;
	}

	public void start() {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("noticPage.fxml"));
			Scene scene = new Scene(parent, 80, 80);
			stage.setScene(scene);

			stage.setTitle(name + " has come in ! Welcome!");

			stage.show();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}
