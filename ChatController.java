
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 886846
 */
public class ChatController{
    private boolean clientIsConnected;
    private Client currentClient;
    private Chat chatService;

    
    public ChatController(String address) throws NotBoundException, MalformedURLException, RemoteException {
        this.clientIsConnected = false;
        try {
            this.chatService = (Chat) Naming.lookup (address);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

   

    public Client connect(String nickname) {
        Client client = null;
        try {
            for (Client cli : this.chatService.getOnlineClients()) {
                if (cli.getNome().equals(nickname)) {
                    JOptionPane.showMessageDialog(null, "Esse nickname ja está em uso. Por favor, escolha outro.", "ERRO", JOptionPane.ERROR_MESSAGE, null);
                }
            }
            client = this.chatService.connect(nickname);
            if (client == null) {
                this.clientIsConnected = false;
            } else {
                this.clientIsConnected = true;
            }
            this.currentClient = client;
            
        } catch (RemoteException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return client;
    }

    public void disconnect() {
        try {
            int cont = 0;
            for (Client cli : this.chatService.getOnlineClients()) {
                if (cli.getNome().equals(currentClient.getNome())) {
                    this.chatService.disconnect(cont);
                }
                cont++;
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void sendMessage(Message message) {
        
        try {
            this.chatService.sendMessage(message);
        } catch (RemoteException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Message> receiveMessages() {
        try {
            return this.chatService.receiveMessages();
        } catch (RemoteException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    }
    
    public List<Client> getOnlineClients() {

        try {
            return this.chatService.getOnlineClients();
        } catch (RemoteException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
            
    }
    

}