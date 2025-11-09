package Strategy;

import Models.ElevatorController;
import Models.Request;

import java.util.HashMap;

public class NearestElevatorStrategy implements ElevatorSelectionStrategy{
    @Override
    public int getElevatorController(Request request, HashMap<Integer, ElevatorController> controllers) {
        return 0;
    }
}
