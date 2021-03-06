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

    ArrayList<ServerClient> connectedClients = new ArrayList<>();
    PlayerModel queuedPlayer;

    ArrayList<GameModel> ongoingGames = new ArrayList<>();

    public Server() {
        try {
            serverSocket = new ServerSocket(5003);
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

                ServerClient newClient = new ServerClient(s, new ServerClientCallbacks() {
                    @Override
                    public void requestQueue(ServerClient client, String name) {
                        PlayerModel player = new PlayerModel(client,name);
                        
                        // if the first player reuqests to get in queue,
                        // then assign them to queuedPlayer
                        if (queuedPlayer == null) {
                            queuedPlayer = player;
                            return;
                        }

                        // if another player is queued, then we're gonna
                        // create a game using both of them
                        GameModel game = new GameModel(queuedPlayer, player);
                        ongoingGames.add(game);
                        
                        queuedPlayer = null;
                       
                    }

                    @Override
                    public void removeClient(ServerClient client) {
                        connectedClients.remove(client);
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
