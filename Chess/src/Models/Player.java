package Models;
import Enum.*;

public abstract class Player {
    protected final String name;
    protected final Color color;


    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public abstract PlayerType getPlayerType();
    public abstract String[] decideMove(Board board);
}
