public class Board {
    public int totalLength;
    public Cell[] cells;
    public Board(int length){
        this.totalLength = length;
        cells = new Cell[length];
    }

    public boolean isWinner(int position){
        return position > totalLength;
    }
}
