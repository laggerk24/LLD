package Strategy;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import Enum.*;
import Models.*;

public class FirstComeFirstServed implements ElevatorMovementStrategy {

    Queue<Integer> requestQueue;

    public FirstComeFirstServed() {
        this.requestQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void move(ElevatorCar elevatorCar) {
        while(true){
            if(requestQueue.isEmpty()) {
                if (elevatorCar.getDirection() != Direction.IDLE) {
                    System.out.println("Elevator " + elevatorCar.getId() + " is Idle");
                    elevatorCar.setDirection(Direction.IDLE);
                }
            } else {
                int requestedFloor = requestQueue.peek();
                if(requestedFloor > elevatorCar.getCurrentFloor()){
                    elevatorCar.setDirection(Direction.Up);
                    elevatorCar.setCurrentFloor(elevatorCar.getCurrentFloor() + 1);
                    System.out.println("Elevator " + elevatorCar.getId() + " going up ");
                } else if(requestedFloor < elevatorCar.getCurrentFloor()){
                    elevatorCar.setDirection(Direction.DOWN);
                    elevatorCar.setCurrentFloor(elevatorCar.getCurrentFloor() - 1);
                    System.out.println("Elevator " + elevatorCar.getId() + " going down ");
                } else {
                    requestQueue.poll();
                    elevatorCar.notifyObservers(requestedFloor);
                }
            }
        }
    }

    @Override
    public void acceptRequest(Request request) {
        if(request == null) {
            System.err.println("Invalid request ");
        } else {
            requestQueue.offer(request.getTargetFloor());
        }
    }
}
