/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableHockeyServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adnanfahed
 */
public class Server extends Thread {

    public ServerSocket serverSocket;

    ArrayList<ClientModel> connectedClients = new ArrayList<>();
    ClientModel queuedClient;

    ArrayList<GameModel> ongoingGames = new ArrayList<>();

    public Server() {
        try {
            serverSocket = new ServerSocket(0);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startServer() {
        this.start();
        printReleaventInfo();
    }

    @Override
    public void run() {

        try {

            while (!serverSocket.isClosed()) {
                Socket s = serverSocket.accept(); //blocking

                ClientModel newClient = new ClientModel(s, new ClientCallbacks() {
                    @Override
                    public void requestQueue(ClientModel client) {
                        // if the first player reuqests to get in queue,
                        // then assign them to queuedClient
                        if (queuedClient == null) {
                            queuedClient = client;
                            return;
                        }

                        // if another player is queued, then we're gonna
                        // create a game using both of them
                        GameModel game = new GameModel(queuedClient, client);
                        ongoingGames.add(game);
                    }
                }
                );

                System.out.println("A new client on " + newClient.getLogInfo());

                this.connectedClients.add(newClient);
            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void printReleaventInfo() {
        System.out.println("Listening on " + serverSocket.getInetAddress().getHostAddress() + ":" + serverSocket.getLocalPort());
    }

}
