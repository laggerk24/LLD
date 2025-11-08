package Observers;

import Models.*;

public class ElevatorDisplay implements ElevatorObservers {
    @Override
    public void notifyObservers(ElevatorCar elevatorCar) {
        System.out.println("Elevator " + elevatorCar.getId() + " Reached at floor " + elevatorCar.getCurrentFloor());
    }
}
