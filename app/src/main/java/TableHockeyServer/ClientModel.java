/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableHockeyServer;

import java.net.Socket;

/**
 *
 * @author adnanfahed
 */
public class ClientModel {

    private int iddd = 0;

    Socket socket;

    ClientCallbacks callbacks;

    public ClientModel(Socket s, ClientCallbacks callbacks) {
        iddd++;

        this.socket = s;
        this.callbacks = callbacks;
    }

    String getIp() {
        return socket.getInetAddress().getHostAddress();
    }

}
