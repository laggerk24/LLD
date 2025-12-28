package Models;

import Enum.*;

public class Queen extends Piece{
    protected Queen(Color color) {
        super(color);
        strategies.add(new DiagonalMoveStrategy());
        strategies.add(new HorizontalMoveStrategy());
        strategies.add(new VerticalMoveStrategy());

    }

    @Override
    public PieceType getPieceType() {
        return PieceType.QUEEN;
    }
}
