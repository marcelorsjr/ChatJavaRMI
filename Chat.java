
import java.util.List;

/**
 *
 * @author 886846
 */
public interface Chat extends java.rmi.Remote {
    public void sendMessage(Message message) throws java.rmi.RemoteException;
    public List<Message> receiveMessages() throws java.rmi.RemoteException;
    public List<Client> getOnlineClients() throws java.rmi.RemoteException;
    public Client connect(String nickname) throws java.rmi.RemoteException;
    public void disconnect(int client) throws java.rmi.RemoteException;
    
}
