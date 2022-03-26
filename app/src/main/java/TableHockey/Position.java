package TableHockey;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author adnanfahed
 */
public class Position {

    private int x;
    private int y;
    private int degree;

    public Position(int x, int y, int degree) {
        this.x = x;
        this.y = y;
        this.degree = degree;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDegree() {
        return degree;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public Direction getDirection() {

        //              
        //
        // UpLeft           0       UpRight
        //
        // 270                      90
        //              
        // DownLeft         180     DownRight
        //
        if (degree >= 0 && degree < 180) {
            if (degree < 90) {
                return Direction.UpRight;
            }
            return Direction.DownRight;
        }

        if (degree < 270) {
            return Direction.DownLeft;
        }
        
        return Direction.UpLeft;

    }
    
    public void nextMove() {
        //              y++
        //
        //               0
        //
        // x--    270            90    x++
        //              
        //              180 
        //
        //              y--
        
        Direction dir = getDirection();
        
        switch (dir) {
            case UpRight -> {
                y++;
                x++;
            }
            case DownRight -> {
                y--;
                x++;
            }
            case DownLeft -> {
                y--;
                x--;
            }
            case UpLeft -> {
                y++;
                x--;
            }
            default -> throw new AssertionError();
        }

    }

}
