package Models;

import Enum.*;

public class Rook extends Piece{
    protected Rook(Color color) {
        super(color);
        strategies.add(new HorizontalMoveStrategy());
        strategies.add(new VerticalMoveStrategy());
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.ROOK;
    }
}
