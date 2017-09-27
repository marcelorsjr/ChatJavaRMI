import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcelorsjr
 */
public class Server extends UnicastRemoteObject implements Chat{
    
    private List<Client> clients;
    private List<Message> messages;
    private long nextClientId;
    private long maxUser;

    public Server(int port, int maxUser) throws RemoteException, MalformedURLException, AlreadyBoundException {
        nextClientId = 0;
        clients = new ArrayList<>();
        messages = new ArrayList<>();
        this.maxUser = maxUser;
        Registry registry = LocateRegistry.createRegistry(port);
        registry.bind("ChatServer", this);

    }
    
    @Override
    public void sendMessage(Message message) throws RemoteException {
        messages.add(message);
        System.out.println(message.getSender().getNome()+" -> "+message.getText());
    }

    @Override
    public List<Message> receiveMessages() throws RemoteException {
        return messages; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Client> getOnlineClients() throws RemoteException {
        return clients;
    }

    @Override
    public Client connect(String nickname) throws RemoteException {
        if (clients.size() == maxUser) {
            throw new RemoteException("Numero maximo de clientes excedido.");
        }

        Client client = new Client(nickname, ++nextClientId);
        clients.add(client);
        return client;
    }

    @Override
    public void disconnect(int client) throws RemoteException {
        clients.remove(client);
    }
    
    public static void main(String args[]) throws RemoteException, MalformedURLException {
       
        try {
            new Server(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
