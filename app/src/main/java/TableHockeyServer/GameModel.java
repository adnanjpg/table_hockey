/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableHockeyServer;

import TableHockey.BallModel;
import TableHockey.Consts;
import TableHockey.SocketMessage;
import java.io.IOException;
import java.net.ServerSocket;
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

    BallModel ball;

    PlayerModel[] getPlayers() {
        return new PlayerModel[]{a, b};
    }

    public GameModel(PlayerModel a, PlayerModel b) {
        this.a = a;
        this.b = b;

        ball = new BallModel();

        try {
            this.serverSocket = new ServerSocket(5006);

            runGame();

            Thread.sleep(1000);

            int port = serverSocket.getLocalPort();

            a.client.startGame(port);
            b.client.startGame(port);

        } catch (IOException ex) {
            Logger.getLogger(GameModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameModel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void runGame() {
        this.start();
    }

    int i = 0;

    void controlBallPosition() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!serverSocket.isClosed()) {
                    ball.nextMove();

                    var msg = new SocketMessage(Consts.ballPosition);
                    
                    // we already have a constructor with title and data,
                    // however, java for some reason is remapping this to
                    // the constructor with title and map in the runtime
                    msg.data = ball;

                    for (PlayerModel player : getPlayers()) {
                        try {
                            player.client.outputStream.writeObject(msg);
                        } catch (IOException ex) {
                            Logger.getLogger(GameModel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
        t1.start();

    }

    @Override
    public void run() {
        controlBallPosition();
        try {
            while (!serverSocket.isClosed()) {
                var socket = serverSocket.accept(); //blocking
                System.out.println("i is" + i++);
            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
