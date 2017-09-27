

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author marcelorsjr
 */
public class Main {
    
    public static void main(String args[]) {
        Client client = null;
        ChatController chatController = null;
        
        JTextField serverAddress = new JTextField();
        JTextField nickname = new JTextField();
        Object[] message = {
        "Server Address:", serverAddress,
        "Nickname:", nickname
        };


        
        while (serverAddress.getText() == null || serverAddress.getText().equals("") || client == null) {
            int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {

            } else {
                continue;
            }

            try {
                chatController = new ChatController(serverAddress.getText());
                client = chatController.connect(nickname.getText());
                chatController.setCurrentClient(client);
            } catch (NotBoundException | MalformedURLException | RemoteException ex) {
                JOptionPane.showMessageDialog(null, ex, "ERRO", JOptionPane.ERROR_MESSAGE, null);
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                chatController = null;
            }
        }
     
        
        new ChatView(chatController).setVisible(true);
    }
    
}
