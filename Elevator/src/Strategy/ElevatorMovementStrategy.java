package Strategy;


import Models.*;

public interface ElevatorMovementStrategy {
    void move(ElevatorCar elevatorCar);
    void acceptRequest(Request request);
}
