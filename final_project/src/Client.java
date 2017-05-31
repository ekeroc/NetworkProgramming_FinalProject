
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

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Client extends UnicastRemoteObject implements ClientInterface {
	public Main		ui	= new Main();
	public String	userName;
	ControllerLobby controllerLobby;
	
	public Client() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub

	}

	public void tell(String message) throws RemoteException {
		System.out.println(message);
		controllerLobby.writeToAll(message);
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
	
	public void setLobby(ControllerLobby controllerLobby) {
		this.controllerLobby = controllerLobby;
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