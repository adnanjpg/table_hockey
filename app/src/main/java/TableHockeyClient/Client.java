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
public class Client extends Thread {

    Socket socket;
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;
    ClientCallbacks callbacks;

    public Client(Socket s, ClientCallbacks callbacks) {
        try {
            this.callbacks = callbacks;

            this.socket = s;

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            this.start();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void requestMatch(String name) {
        try {
            Map params = new HashMap();
            params.put(Consts.paramsPlayerName, name);

            SocketMessage msg = new SocketMessage((Consts.requestMatchCommand), params);
            outputStream.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {
        try {

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
            callbacks.disconnect();
        }

    }

    String handleRequest(SocketMessage msg) {
        if (msg.title.equals(Consts.startGameCommand)) {
            startGame();
        }

        return null;
    }

    void startGame() {
        callbacks.startGame();
    }

    void handleResponse(SocketMessage msg) {
        if (msg.title.equals(Consts.requestMatchCommand)) {
            callbacks.setRequestedMatchStatus(msg.isSuccess());
        }
    }

}
