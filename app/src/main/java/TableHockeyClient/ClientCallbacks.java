/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableHockeyClient;

import java.net.Socket;

/**
 *
 * @author adnanfahed
 */
public interface ClientCallbacks {

    public void setRequestedMatchStatus(boolean b);

    public void disconnect();
    
    public void startGame(Socket gameSocket);
}
