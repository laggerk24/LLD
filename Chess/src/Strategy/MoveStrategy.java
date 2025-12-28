package Strategy;

import Models.*;

import java.util.Optional;

public interface MoveStrategy {
    boolean isValidMove(Piece piece, Cell from, Cell to, Cell[][] board);
    Optional<Move> move(Piece piece, Cell from, Cell to, Cell[][] board);
}
