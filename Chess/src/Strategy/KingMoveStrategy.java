package Strategy;

import Models.*;

import java.util.Optional;

public class KingMoveStrategy implements MoveStrategy {
    @Override
    public boolean isValidMove(Piece piece, Cell from, Cell to, Cell[][] board) {
        return false;
    }

    @Override
    public Optional<Move> move(Piece piece, Cell from, Cell to, Cell[][] board) {
        return Optional.empty();
    }
}
