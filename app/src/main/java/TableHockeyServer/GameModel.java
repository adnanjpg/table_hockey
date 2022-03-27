/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableHockeyServer;

/**
 *
 * @author adnanfahed
 */
public class GameModel extends Thread {

    ClientModel a, b;

    public GameModel(ClientModel a, ClientModel b) {
        this.a = a;
        this.b = b;
    }

    public void runGame() {
        this.start();
    }

    @Override
    public void run() {
        
    }

}
