/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableHockeyClient;

import TableHockey.Consts;
import TableHockey.SocketMessage;
import TableHockeyServer.ServerClient;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adnanfahed
 */
public class GameClient extends Thread {

    Socket socket;
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;

    public GameClient(Socket s) {

        this.socket = s;

        this.start();

    }

    @Override
    public void run() {
        try {

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            while (socket.isConnected()) {
                SocketMessage value = (SocketMessage) inputStream.readObject(); //blocking

                if (value.status == null) {

                    String stat = handleRequest(value);

                    if (stat != null) {
                        SocketMessage out = new SocketMessage(value.title, stat);
                        this.outputStream.writeObject(out);
                    }

                } else {
                    handleResponse(value);
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

    }

    String handleRequest(SocketMessage msg) {
        return null;
    }

    void handleResponse(SocketMessage msg) {
    }

}
