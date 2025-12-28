package Models;

import Enum.*;

public class BotPlayer extends Player{

    public BotPlayer(String name, Color color) {
        super(name, color);
    }

    @Override
    public PlayerType getPlayerType() {
        return null;
    }

    @Override
    public String[] decideMove(Board board) {
        // can write a util method which will take board and return 2 different
        // random moves
        return new String[0];
    }
}
