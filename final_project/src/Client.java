
// *******************************************************************
// * Network Programming - Unit 5 Remote Method Invocation *
// * Program Name: CalculatorRMIClient *
// * The program is a RMI client. *
// * Usage: java CalculatorRMIClient op num1 num2, *
// * op = add, sub, mul, div *
// * 2014.02.26 *
// *******************************************************************
import java.io.*;
import java.rmi.*;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

import javax.naming.spi.DirStateFactory.Result;
import javax.security.auth.Subject;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Cell;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Client extends UnicastRemoteObject implements ClientInterface {
	public Main				ui	= new Main();
	public String			userName;
	ControllerLobby			controllerLobby;
	ControllerPtvMessage	controllerPtvMessage;
	paintBB					paintBB;
	TicTacTT				ticTacTT;
	TicTacTT.Cell			cell;

	public Client() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub

	}

	public void updateUserList(String name, int op) {
		if (op == 0) {
			System.out.println("client updateUserList");
			controllerLobby.updateUserList(name);
		} else {
			System.out.println("client remove");
			controllerLobby.removeUserList(name);
		}

	}

	public void tell(String message) throws RemoteException {
		System.out.println(message);
		controllerLobby.writeToAll(message);
	}

	public void tellToClient(String message) throws RemoteException {
		System.out.println(message);
		controllerPtvMessage.sendToClient(message);
	}

	public void paintToClient(double x, double y) throws RemoteException {
		paintBB.setGc(x, y);
	}

	public void setPaintLabel(double value) throws RemoteException {
		paintBB.setLabel(value);
	}


	public void newPtvMessage(String name) throws RemoteException {
		System.out.println("client main~~~~~~~~~~~~~~~~~~~~~" + ui);
	}

	public void setName(String userName) throws RemoteException {
		this.userName = userName;
	}

	public String getName() throws RemoteException {
		return this.userName;
	}

	public void setUi(Main ui) throws RemoteException {
		this.ui = ui;
	}

	public Main getUi() throws RemoteException {
		return ui;
	}

	public void setLobby(ControllerLobby controllerLobby) {
		this.controllerLobby = controllerLobby;
	}

	public void setPtvMessage(ControllerPtvMessage controllerPtvMessage) {
		this.controllerPtvMessage = controllerPtvMessage;
	}

	public void setPaintBB(paintBB paintBB) {
		this.paintBB = paintBB;
	}




	//public static void main(String args[]) throws RemoteException {
	//		RMIInterface o = null;
	//		int op = 0; // add=0, sub=1, mul = 2, div = 3
	//		int QUIT = 0;
	//		String result = "";
	//		String name = "", subject = "";
	//		ArrayList<String> userList = new ArrayList<String>();
	//
	//		Scanner scan = new Scanner(System.in);
	//		// System.setSecurityManager(new RMISecurityManager());
	//		// Connect to RMIServer
	//		try {
	//			o = (RMIInterface) Naming.lookup("rmi://127.0.0.1/arithmetic");
	//
	//			System.out.println("RMI server connected");
	//		} catch (Exception e) {
	//			System.out.println("Server lookup exception: " + e.getMessage());
	//		}

	//		Client client = new Client();
	//		try {
	//			while (QUIT == 0) {
	//				result = "";
	//
	//				System.out.println("(0)sing in (1)register(7)QUIT");
	//				op = scan.nextInt();
	//				switch (op) {
	//				case 0:
	//					System.out.println("Input your UserName:");
	//					name = scan.next();
	//					result = o.singIn(client, name);
	//					System.out.println(result + "port = " + client.toString());
	//					break;
	//				case 1:
	//					System.out.println("Register your UserName:");
	//					name = scan.next();
	//					result = o.register(name);
	//					System.out.println(result);
	//					break;
	//				case 3:
	//					o.sentToAll(name, "HIIII");
	//					break;
	//				case 4:
	//					userList = o.showUsers();
	//					for (int i = 0; i < userList.size(); i++) {
	//						System.out.println(userList.get(i));
	//					}
	//					break;
	//				case 5:
	//					String message = "", clientName = "";
	//					System.out.println("Input the clientName:");
	//					clientName = scan.next();
	//					System.out.println("Input the message");
	//					message = scan.next();
	//
	//					result = o.sendToClient(clientName, message, client);
	//					System.out.println(result);
	//					break;
	//				case 7:
	//					QUIT = 1;
	//					o.removeUser(client);
	//					unexportObject(client, true);
	//					break;
	//				}
	//			}
	//		} catch (Exception e) {
	//			System.out.println("Server exception: " + e.getMessage());
	//			e.printStackTrace();
	//		}
	//}
}