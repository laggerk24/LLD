import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class Game {
    Deque<Player> players;
    Board board;
    Dice dice;
    int snakeCount;
    int ladderCount;

    public Game()  {
        initializeGame();
    }

    public void initializeGame(){
        players = new LinkedList<>();
        players.add(new Player("Player 1", Color.GREEN));
        players.add(new Player("Player 2", Color.YELLOW));
        players.add(new Player("Player 3", Color.RED));
        board = new Board(100);
        dice = new Dice(1);
        snakeCount = 5;
        ladderCount = 6;
        Random random = new Random();
        generateSnakes(random);
        generateLadders(random);
    }

    public void run() throws InterruptedException {
        System.out.println("Welcome to LODU");
        while(true){
            Player currentPlayer = players.removeFirst();
            System.out.println(currentPlayer.name + "'s turn ");
            System.out.println(currentPlayer.name + "'s rolling Dice ");
            int num = dice.Roll();
            Thread.sleep(500);
            System.out.println("Number in Dice " + num);
            int updatedPosition = currentPlayer.position + num;
            if(board.isWinner(updatedPosition)) {
                System.out.println(currentPlayer.name + " is winner with color " + currentPlayer.color);
                break;
            }
            if(board.cells[updatedPosition] != null){
                int start = board.cells[updatedPosition].jump.start;
                int end = board.cells[updatedPosition].jump.end;
                if(start > end){
                    System.out.println("Snake found at " + start);
                    System.out.println("Moving player to " + end);
                }
                else {
                    System.out.println("Ladder found at " + start);
                    System.out.println("Moving player to " + end);
                }
                updatedPosition = end;
            }
            currentPlayer.position = updatedPosition;
            players.addLast(currentPlayer);
        }
        System.out.println("Game Finished");
    }

    private void generateSnakes(Random random){
        int count = 0;
        while(count<ladderCount){
            int start = random.nextInt(0,100);
            int end = random.nextInt(0,100);
            if(start > end){
                board.cells[start] = new Cell(new Jump(start,end));
                count++;
            }
        }
    }

    private void generateLadders(Random random){
        int count = 0;
        while(count<snakeCount){
            int start = random.nextInt(0,100);
            int end = random.nextInt(0,100);
            if(start<end && board.cells[start] == null && board.cells[end] == null){
                board.cells[start] = new Cell(new Jump(start,end));
                count++;
            }
        }
    }
}
