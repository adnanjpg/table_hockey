package TableHockey;

import TableHockey.Position;
import java.io.Serializable;

/**
 *
 * @author adnanfahed
 */
public class BallModel implements Serializable {
    private final Position position;
    
    public int getX() {
        return position.getX();
    }
    
    public int getY() {
        return position.getY();
    }

    public BallModel() {
        position = new Position(0, 0, 0);
    }
    
    public void nextMove() {
        position.nextMove();
    }

    
}
