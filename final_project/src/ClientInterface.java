import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

public interface ClientInterface extends Remote {
	public void tell(String message) throws java.rmi.RemoteException;

	public void tellToClient(String message) throws java.rmi.RemoteException;

	public void paintToClient(double x, double y) throws java.rmi.RemoteException;

	public void setPaintLabel(double value) throws java.rmi.RemoteException;

	public void newPtvMessage(String name) throws RemoteException;

	public void setName(String userName) throws java.rmi.RemoteException;

	public void updateUserList(String name, int op) throws java.rmi.RemoteException;

	public String getName() throws java.rmi.RemoteException;
}
