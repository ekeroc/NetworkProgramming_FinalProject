
import java.awt.image.ColorModel;
import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Main extends Application {
	public Stage		stageLogin;

	public Stage		primaryStage;
	public BorderPane	rootLayout;

	static Client		client;
	static RMIInterface	o	= null;
	String				name, result;

	public Main() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client getClient() {
		return client;
	}

	@FXML
	public TextField txOutput;

	@Override
	public void start(Stage primaryStage) throws RemoteException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");

		initRootLayout();
		showPersonOverview();
		stageLogin.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(final WindowEvent arg0) {
				try {
					o.removeUser(client);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("root.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows the person overview inside the root layout.
	 * 
	 * @throws RemoteException
	 */
	public void showPersonOverview() throws RemoteException {
		// Load person overview.
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("logPage.fxml"));
		AnchorPane personOverview = null;
		try {
			personOverview = (AnchorPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Set person overview into the center of root layout.
		rootLayout.setCenter(personOverview);

		ControllerLogin controllerLogin = loader.getController();
		controllerLogin.setMain(this);
		client.setUi(this);

		controllerLogin.setClient(client);
		stageLogin = controllerLogin.getStage();

		System.out.println(this);

	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void signIn(String name) throws RemoteException, SQLException {
		result = o.singIn(client, name);
		System.out.println(result);

		primaryStage.hide();

	}

	public String register(String name) throws RemoteException, SQLException {
		result = o.register(name);
		System.out.println(result);

		return result;
	}

	public void sendToAll(String message) throws RemoteException {
		o.sentToAll(client.getName(), message);
	}

	public void sendToClient(String name, String message) throws RemoteException {
		System.out.println("sendToClient" + message);
		o.sendToClient(name, message, client);
	}

	public void paintToClient(String name, double x, double y) throws RemoteException {
		o.paintToClient(name, x, y);
	}

	public void setPaintLabel(String name, double value) throws RemoteException {
		o.setPaintLabel(name, value);
	}

	public void newPtvMessage(String name, String selfName) throws RemoteException {
		o.newPtvMessage(name, selfName);
	}

	public void removeUser(ClientInterface c) throws RemoteException {
		o.removeUser(c);
	}

	public static void main(String[] args) throws RemoteException {
		client = new Client();

		try {
			o = (RMIInterface) Naming.lookup("rmi://127.0.0.1/arithmetic");

			System.out.println("RMI server connected");

		} catch (Exception e) {
			System.out.println("Server lookup exception: " + e.getMessage());
		}
		launch(args);
	}

}
