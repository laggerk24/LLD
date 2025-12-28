package Models;

public class Actions {
    private final Piece piece;
    private final Cell from;
    private final Cell to;
    private final Piece capturedPiece;
    private final int moveNumber;

    public Actions(Piece piece, Cell from, Cell cell, Piece capturedPiece, int moveNumber) {
        this.piece = piece;
        this.from = from;
        to = cell;
        this.capturedPiece = capturedPiece;
        this.moveNumber = moveNumber;
    }

    public Piece getPiece() {
        return piece;
    }

    public Cell getFrom() {
        return from;
    }

    public Cell getTo() {
        return to;
    }

    public Piece getCapturedPiece() {
        return capturedPiece;
    }

    public int getMoveNumber() {
        return moveNumber;
    }
}
