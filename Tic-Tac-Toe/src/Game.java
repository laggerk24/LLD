import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {
    Deque<Player> players;
    Board board;

    public Game(){
        intializeGame();
    }

    public void runGame(){
        boolean isWinner = false;
        board.printBoard();
        while(!isWinner){
            Player currentPlayer = players.removeFirst();
            System.out.println(currentPlayer.name + "'s Move");
            Scanner inputScanner = new Scanner(System.in);
            String s = inputScanner.nextLine();
            String[] input = s.split(",");
            int row = Integer.parseInt(input[0]);
            int column = Integer.parseInt(input[1]);
            boolean isMoveSuccess = board.addMove(row,column,currentPlayer.pieceType);
            if(!isMoveSuccess){
                System.out.println("Invalid move by " + currentPlayer.name+ ", try again");
                players.addFirst(currentPlayer);
                continue;
            }
            board.printBoard();
            if(isWinner(currentPlayer.pieceType.piece, row, column)){
                board.printBoard();
                System.out.println("We have a winner " + currentPlayer.name);
                isWinner = true;
                break;
            }
            if(!board.isEmpty()) break;
            players.addLast(currentPlayer);
        }
        if(!isWinner) System.out.println("It's a tie");
    }

    private void intializeGame(){
        this.players = new LinkedList<>();
        players.add(new Player("Player 1", new PieceX()));
        players.add(new Player("Player 2", new PieceO()));
        board = new Board(3);
    }

    private boolean isWinner(Pieces piece, int row, int column){
        //checking row
        boolean isRowSame = true, isColumnSame = true, isFirstDiagonalSame = true, isSecondDiagonalSame = true;
        int size = board.size;
        // row check
        for(int j=0;j<size;j++){
            if(board.board[row][j] == null || board.board[row][j].piece != piece){
                isRowSame = false;
                break;
            }
        }
        // column check
        for(int i=0;i<size;i++){
            if(board.board[i][column] == null || board.board[i][column].piece != piece){
                isColumnSame = false;
                break;
            }
        }

        // diagonal
        for(int i=0;i<size;i++){
            if(board.board[i][i] == null || board.board[i][i].piece != piece){
                isFirstDiagonalSame = false;
            }
            if(board.board[i][size-i-1] == null || board.board[i][size-i-1].piece != piece){
                isSecondDiagonalSame = false;
            }
        }

        return isRowSame || isColumnSame || isFirstDiagonalSame || isSecondDiagonalSame;
    }
}
