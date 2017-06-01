
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerLobby implements Initializable {
	ObservableList<String>	data			= FXCollections.observableArrayList();

	@FXML
	public TextField		txInput;
	@FXML
	public TextArea			txOutput;
	@FXML
	public TextField		txNotic;
	@FXML
	public ListView<String>	lvUserList;
	@FXML
	public Label			lbNotic;

	Main					main;
	Client					c;
	String					message;
	Stage					anotherStage	= new Stage();
	String					name			= "";

	public void setMain(Main main) {
		this.main = main;
	}

	public void setClient(Client c) {
		this.c = c;
	}

	@FXML
	public void actSubmit(ActionEvent event) throws IOException, RemoteException, SQLException {
		message = txInput.getText();
		main.sendToAll(message);
		System.out.println("Lobby submit's Main" + main);
	}

	@FXML
	public void actConnect(ActionEvent event) throws IOException, RemoteException, SQLException {
		name = lvUserList.getSelectionModel().getSelectedItem();
		System.out.println("listview item = " + name);
		newPtvMessage(name);

		// TODO main.newPtvMessage(name, c.getName());
	}

	@FXML
	public void actPaint(ActionEvent event) throws IOException, RemoteException, SQLException {
		name = lvUserList.getSelectionModel().getSelectedItem();
		System.out.println("paint listview item = " + name);

		paintBB paintBB = new paintBB();
		paintBB.setClient(c);
		paintBB.setMain(main);

		paintBB.setName(name);
		paintBB.Start();
	}

	@FXML
	public void actGame(ActionEvent event) throws IOException, RemoteException, SQLException {
		name = lvUserList.getSelectionModel().getSelectedItem();
		System.out.println("paint listview item = " + name);
		
		TicTacTT ticTacTT =new TicTacTT();
		
		ticTacTT.setClient(c);
		ticTacTT.setMain(main);
		ticTacTT.setName(name);
		ticTacTT.Start();
	}
	public void newPtvMessage(String name) throws RemoteException {
		FXMLLoader anotherLoader = new FXMLLoader(); // FXML for second stage
		anotherLoader.setLocation(Main.class.getResource("pvtMessage.fxml"));
		try {
			Parent anotherRoot = anotherLoader.load();
			Scene anotherScene = new Scene(anotherRoot);
			anotherStage.setScene(anotherScene);
			anotherStage.setTitle(name);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControllerPtvMessage controllerPtvMessage = anotherLoader.getController();
		System.out.println("newPtvMessage main :" + main);

		main = c.getUi();

		controllerPtvMessage.setMain(main);
		controllerPtvMessage.setName(name);
		controllerPtvMessage.setClient(c);
		System.out.println("newPtvMessage main :" + main + "," + name + "," + c);

		c.setPtvMessage(controllerPtvMessage);

		anotherStage.show();

		System.out.println("newPtvMessage Successful.");
	}

	public void writeToAll(String message) {
		txOutput.setText(txOutput.getText() + "\n" + message);
	}

	public void updateUserList(String name) {
		data.add(name);
		lvUserList.setItems(data);

		Thread thread = new Thread(() -> {
			try {
				Platform.runLater(() -> {
					txNotic.setText("Member " + name + " is on line!!!!");
				});
				Thread.sleep(3000);
				Platform.runLater(() -> {
					txNotic.setText("");
				});

			} catch (Exception exp) {
				exp.printStackTrace();
			}

		});
		thread.setDaemon(true);
		thread.start();

		//TODO newPtvWindows 
		//		Platform.runLater(() -> {
		//			ControllerNotic controllerNotic= new ControllerNotic();
		//			controllerNotic.setMain(name);
		//			controllerNotic.start();
		//		});
	}

	public void removeUserList(String name) {
		data.remove(name);
		lvUserList.setItems(data);

		Thread thread = new Thread(() -> {
			try {
				Platform.runLater(() -> {
					txNotic.setText("Member " + name + " is off line!!!!");
				});
				Thread.sleep(2000);
				Platform.runLater(() -> {
					txNotic.setText("");
				});

			} catch (Exception exp) {
				exp.printStackTrace();
			}

		});
		thread.setDaemon(true);
		thread.start();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
