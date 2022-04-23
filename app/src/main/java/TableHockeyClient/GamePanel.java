/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableHockeyClient;

import TableHockey.BallModel;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author adnanfahed
 */
public class GamePanel extends JPanel {

    BallModel ball;

    void setBall(BallModel b) {
        ball = b;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (ball != null) {

            g.drawRect(ball.getX(), ball.getY(), 100, 100);

        }

    }

}
