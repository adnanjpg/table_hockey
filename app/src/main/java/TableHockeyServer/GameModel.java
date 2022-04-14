/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableHockeyServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adnanfahed
 */
public class GameModel extends Thread {

    PlayerModel a;
    PlayerModel b;
    ServerSocket serverSocket;

    public GameModel(PlayerModel a, PlayerModel b) {
        this.a = a;
        this.b = b;

        try {
            this.serverSocket = new ServerSocket(0);

            
            int port = serverSocket.getLocalPort();
                    
            a.client.startGame(port);
            b.client.startGame(port);
            
            runGame();
        } catch (IOException ex) {
            Logger.getLogger(GameModel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void runGame() {
        this.start();
    }

    @Override
    public void run() {

        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept(); //blocking

            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
