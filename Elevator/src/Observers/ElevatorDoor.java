package Observers;

import Models.ElevatorCar;

public class ElevatorDoor implements ElevatorObservers {

    @Override
    public void notifyObservers(ElevatorCar elevatorCar){
        System.out.println("Elevator " + elevatorCar.getId() + " Opening Door at floor "  + elevatorCar.getCurrentFloor());
        //Action to open the door
        System.out.println("Elevator " + elevatorCar.getId() + " Closing the dooor ");
        //Action to close the door
    }
}
