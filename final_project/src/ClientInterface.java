import java.rmi.Remote;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ClientInterface extends Remote {
	public void tell (String message)throws java.rmi.RemoteException ;
	public void setName (String userName)throws java.rmi.RemoteException ;
	public String getName ()throws java.rmi.RemoteException ;

}
