package Models;

import Enum.*;
import Strategy.MoveStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Getter
public abstract class Piece {
    protected int numOfMoves;
    protected final Color color;
    protected final List<MoveStrategy> strategies = new ArrayList<>();

    protected Piece(Color color) {
        this.color = color;
    }

    public void incrementMove(){ numOfMoves++;}
    public void decrementMove(){ numOfMoves--;}

    public boolean isValidMove(Cell from,Cell to, Cell[][] board){
        for(MoveStrategy strategy : strategies){
            if(strategy.isValidMove(this,from,to,board)) return true;
        }
        return false;
    }

    public abstract PieceType getPieceType();

    public Optional<Move> move(Cell from,Cell to, Cell[][] board){
        for(MoveStrategy strategy: strategies){
            Optional<Move> move = strategy.move(this,from,to,board);
            if(move.isPresent()) return move;
        }
        return Optional.empty();
    }
}
