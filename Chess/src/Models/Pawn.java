package Models;

import Enum.*;
import Strategy.PawnMoveStrategy;

public class Pawn extends Piece{
    protected Pawn(Color color) {
        super(color);
        strategies.add(new PawnMoveStrategy());
        strategies.add(new PawnPromotionStrategy());
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }
}
