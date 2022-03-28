/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableHockeyServer;

import TableHockey.Consts;
import TableHockey.SocketMessage;
import TableHockey.StatusCodes;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adnanfahed
 */
public class ServerClient extends Thread {

    private int iddd = 0;

    Socket socket;
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;

    String id;

    ServerClientCallbacks callbacks;

    public ServerClient(Socket s, ServerClientCallbacks callbacks) {
        try {
            iddd++;

            id = String.valueOf(iddd);

            this.socket = s;

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            this.callbacks = callbacks;

            this.start();
        } catch (IOException ex) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    String getIp() {
        return socket.getInetAddress().getHostAddress();
    }

    String getLogInfo() {
        return socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
    }

    @Override
    public void run() {

        try {

            while (socket.isConnected()) {
                SocketMessage value = (SocketMessage) inputStream.readObject(); //blocking
                System.out.println(this.id + ": " + value);

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
            callbacks.removeClient(this);
        }

    }

    String handleRequest(SocketMessage inp) {

        // stupid java does not allow us to use
        // switch case for inconstant values for
        // some reason...
        if (inp.title.equals(Consts.requestMatchCommand)) {
            return handleMatchRequest();
        }

        return StatusCodes.notFound;

    }

    void handleResponse(SocketMessage inp) {
    }

    String handleMatchRequest() {
        callbacks.requestQueue(this);
        return StatusCodes.success;
    }

}
