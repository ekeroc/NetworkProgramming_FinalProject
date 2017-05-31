
// *******************************************************************
// * Network Programming - Unit 5 Remote Method Invocation *
// * Program Name: ArithmeticInterface *
// * The program defines the interface for Java RMI. *
// * 2014.02.26 *
// *******************************************************************
import java.rmi.Remote;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RMIInterface extends Remote {
	public String singIn(ClientInterface clientInterface, String name) throws java.rmi.RemoteException, SQLException;

	public String register(String name) throws java.rmi.RemoteException;

	public void sentToAll(String name, String message) throws java.rmi.RemoteException;

	public ArrayList<String> showUsers() throws java.rmi.RemoteException;

	public void removeUser(ClientInterface clientInterface) throws java.rmi.RemoteException;

	public String sendToClient(String name, String reply, ClientInterface clientInterface) throws java.rmi.RemoteException;


}
