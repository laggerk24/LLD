package Models;

import Enum.*;
import Strategy.CastelingMoveStrategy;
import Strategy.KingMoveStrategy;

public class King extends Piece{
    protected King(Color color) {
        super(color);
        strategies.add(new KingMoveStrategy());
        strategies.add(new CastelingMoveStrategy());
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KING;
    }
}
