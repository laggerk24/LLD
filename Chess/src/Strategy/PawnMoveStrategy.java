package Strategy;

import Models.Cell;
import Models.Move;
import Models.Piece;

import java.util.Optional;

public class PawnMoveStrategy implements MoveStrategy {
    @Override
    public boolean isValidMove(Piece piece, Cell from, Cell to, Cell[][] board) {
        return false;
    }

    @Override
    public Optional<Move> move(Piece piece, Cell from, Cell to, Cell[][] board) {
        return Optional.empty();
    }
}
