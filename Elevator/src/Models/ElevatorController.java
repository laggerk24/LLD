package Models;

import Strategy.ElevatorMovementStrategy;

public class ElevatorController implements Runnable{
    ElevatorCar elevatorCar;
    ElevatorMovementStrategy strategy;

    public ElevatorController(ElevatorCar elevatorCar) {
        this.elevatorCar = elevatorCar;
    }

    public ElevatorCar getElevatorCar() {
        return elevatorCar;
    }

    public ElevatorMovementStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(ElevatorMovementStrategy strategy) {
        this.strategy = strategy;
    }

    public void moveElevator(){
        strategy.move(elevatorCar);
    }

    public void acceptRequest(Request request){
        strategy.acceptRequest(request);
    }

    @Override
    public void run() {
        moveElevator();
    }
}
