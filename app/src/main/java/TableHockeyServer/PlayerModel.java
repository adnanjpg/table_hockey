
package TableHockeyServer;

import TableHockey.Position;

/**
 *
 * @author adnanfahed
 */
public class PlayerModel {
    static private int idd = 0;

    String id;
    String name;
    private Position position;

    public PlayerModel(String name) {
        id = String.valueOf(idd++);
        this.name = name;
    }

    private void setPosition(Position position) {
        this.position = position;
    }

    // the player can only move at x position
    public void setPosition(int x) {
        setPosition(new Position(x, 0, 0));
    }

    private Position getPosition() {
        return position;
    }

    public int getX() {
        return getPosition().getX();
    }
}
