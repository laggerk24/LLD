import java.util.Random;

public class Dice {
    int countOfDice;
    public Dice(int count){
        this.countOfDice = count;
    }

    public int Roll(){
        Random random = new Random();
        return random.ints(countOfDice,1,7).reduce(0,Integer::sum);
    }
}
