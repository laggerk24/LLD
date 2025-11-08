package Strategy;

import Models.*;

import java.util.HashMap;

public interface ElevatorSelectionStrategy {
    // return elevatorControllerId/Elevator Id
    int getElevatorController(Request request, HashMap<Integer, ElevatorController> controllers);
}
