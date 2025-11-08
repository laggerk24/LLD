package Strategy;

import Models.*;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class OddEvenSelectionStrategy implements ElevatorSelectionStrategy {
    @Override
    public int getElevatorController(Request request, HashMap<Integer, ElevatorController> controllers) {
        boolean needEven = request.getTargetFloor() % 2 == 0;
        Random random = new Random();
        List<Integer> validIds = controllers.keySet().stream().filter(id -> needEven ? id%2==0 : id%2 == 1).toList();
        if(validIds.isEmpty()){
            System.err.println("No Elevator Found");
            return -1;
        }
        int elevatorId = validIds.get(random.nextInt(validIds.size()));
        return elevatorId;
    }
}
