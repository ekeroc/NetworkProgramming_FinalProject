
// *******************************************************************
// * Network Programming - Unit 5 Remote Method Invocation *
// * Program Name: ArithmeticServer *
// * The program is the RMI server. It binds the ArithmeticRMIImpl *
// * with name server. *
// * 2014.02.26 *
// *******************************************************************
import java.rmi.*;
import java.rmi.server.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.w3c.dom.NamedNodeMap;

public class Server {
	// Bind Server and Registry
	public static void main(String args[]) {
		// System.setSecurityManager(new RMISecurityManager());
		try {
			RMIImpl name = new RMIImpl();
			System.out.println("Registering ...");
			Naming.rebind("arithmetic", name); // arithmetic is the name of the service
			System.out.println("Register success");
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

}