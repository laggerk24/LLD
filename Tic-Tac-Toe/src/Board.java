import jdk.jshell.spi.ExecutionControl;

public class Board {
    int size;
    PieceType[][] board;
    public Board(int size){
        this.size = size;
        board = new PieceType[size][size];
    }

    public boolean addMove(int row, int column, PieceType pieceType) {
        if(row - 1 < size && column - 1 < size && row >= 0 && column >= 0 && board[row][column] == null){
            board[row][column] = pieceType;
            return true;
        }
        return false;
    }

    public void printBoard(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j] == null ){
                    System.out.print(" "+" "+" ");
                }
                else {
                    System.out.print(" "+board[i][j].piece+" ");
                }
                if(j < size-1){
                    System.out.print("|");
                }
            }
            System.out.println();
            for(int j=0;j<size && i<size-1;j++){
                System.out.print("----");
            }
            System.out.println();
        }
    }

    public boolean isEmpty(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j] == null) return true;
            }
        }
        return false;
    }
}
