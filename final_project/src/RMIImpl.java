
// java -cp mysql-connector-java.jar;. ArithmeticServer

// *******************************************************************
// * Network Programming - Unit 5 Remote Method Invocation *
// * Program Name: ArithmeticRMIImpl *
// * The program implements the services defended in the interface, *
// * ArithmeticInterface.java, for Java RMI. *
// * 2014.02.26 *
// *******************************************************************
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

import javax.management.JMException;
import javax.xml.transform.Templates;

import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

import java.io.*;
import java.net.MalformedURLException;
import java.text.*;
import java.sql.*;

public class RMIImpl extends UnicastRemoteObject implements RMIInterface {
	// This implementation must have a public constructor.
	// The constructor throws a RemoteException.
	FileReader						fr;
	FileWriter						fw;
	BufferedReader					br;
	int								QUIT			= 0, FIND = 0;
	String							response;
	Connection						conn			= null;
	Statement						st;
	int								subject_Id		= 0, reply_Id = 20;
	java.sql.Timestamp				current_Time	= new java.sql.Timestamp(System.currentTimeMillis());	//sql teimestamp get now time.
	private Vector<ClientInterface>	vectorClient	= new Vector<>();
	private ArrayList<String>		userList;

	public RMIImpl() throws java.rmi.RemoteException {
		super(); // Use constructor of parent class
	}

	public void connect() throws java.rmi.RemoteException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String datasource = "jdbc:mysql://localhost/network?user=root&password=Qjwkel456";
			conn = DriverManager.getConnection(datasource);
			System.out.println("Connect to MySQL");
			st = conn.createStatement();

		} catch (Exception e) {
			System.out.println("error");
		}
	}

	// Implementation of the service defended in the interface
	public String singIn(ClientInterface clientInterface, String name) throws java.rmi.RemoteException {
		response = "";
		try {
			connect();
			st.execute("SELECT * FROM user WHERE username='" + name + "'");
			ResultSet rs = st.getResultSet();
			if (rs.next() == false) {
				response = "The Username isn't exist.";
			} else if (rs.getString("username").compareTo(name) == 0) {
				response = rs.getString("username") + "\tSign in success.";
				clientInterface.setName(name);

				for (ClientInterface tmp : vectorClient)
					tmp.updateUserList(name, 0);

				vectorClient.add(clientInterface);
				for (ClientInterface tmp2 : vectorClient)
					clientInterface.updateUserList(tmp2.getName(), 0);

				System.out.println(clientInterface + "===" + clientInterface.getName());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return response;
	}

	public String register(String name) throws java.rmi.RemoteException {
		try {
			connect();
			st.execute("SELECT * FROM user WHERE username='" + name + "'");
			ResultSet rs = null;

			rs = st.getResultSet();
			if (rs.next() == false) {
				response = name + "\tregister Success";
				System.out.println(response);

				String sql = "INSERT INTO user " + "VALUES (" + name + ")";
				st.executeUpdate(sql);
			} else if (rs.getString("username").compareTo(name) == 0) {
				response = "Already have the same username.";

				System.out.println(response);
				System.out.println(rs.getString("username"));
			}
		} catch (Exception e) {
			System.out.println("error");
		}
		return response;
	}

	public void sentToAll(String name, String message) throws java.rmi.RemoteException {
		System.out.println("HALLLLLLLLLLLLLLLLLLLLLLLLLLl");
		for (int i = 0; i < vectorClient.size(); i++) {
			ClientInterface tmp = vectorClient.get(i);
			tmp.tell(name + ": " + message);
			System.out.println(tmp.toString() + "," + tmp);
		}
		System.out.println("Send message to Client: " + message);

	}

	public ArrayList<String> showUsers() throws java.rmi.RemoteException {
		ArrayList<String> userList = new ArrayList<String>();
		for (ClientInterface tmp : vectorClient) {
			userList.add(tmp.getName());
			System.out.println(tmp.getName());
		}
		return userList;
	}

	public void removeUser(ClientInterface c) throws java.rmi.RemoteException {
		for (ClientInterface tmp : vectorClient)
			tmp.updateUserList(c.getName(), 1);

		vectorClient.remove(c);
		System.out.println(c.getName() + " off line...");
		for (ClientInterface tmp : vectorClient) {
			System.out.println(tmp.getName());
		}
	}

	public String sendToClient(String name, String message, ClientInterface c) throws java.rmi.RemoteException {
		int i = 0, hasFlag = 0;
		String response = "";
		for (ClientInterface tmp : vectorClient) {
			if (name.equals(tmp.getName())) {
				hasFlag = 1;
				tmp.tellToClient("From " + c.getName() + " message: " + message);
			}
		}
		if (hasFlag != 1) // windows event
			response = "User" + name + " off line...";
		else // message windows event
			response = c.getName() + " send to " + name + message;

		return response;
	}

	public void paintToClient(String name, double x, double y) throws java.rmi.RemoteException {
		for (ClientInterface tmp : vectorClient) {
			if (name.equals(tmp.getName())) {
				tmp.paintToClient(x, y);
			}
		}
	}

	public void setPaintLabel(String name, double value) throws java.rmi.RemoteException {
		for (ClientInterface tmp : vectorClient) {
			if (name.equals(tmp.getName())) {
				System.out.println("name = " + tmp.getName());
				tmp.setPaintLabel(value);
			}
		}
	}

	public void newPtvMessage(String name, String selfName) throws RemoteException {
		for (ClientInterface tmp : vectorClient) {
			if (name.equals(tmp.getName())) {
				System.out.println("newPtvMessage found the name is OK!");
				//TODO tmp.newPtvMessage(selfName);
			}
		}
	}
}
