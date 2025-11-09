package Strategy;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import Enum.*;
import Models.*;

public class LookAlgorithmStrategy implements ElevatorMovementStrategy {

    private final Set<Integer> upRequests;
    private final Set<Integer> downRequests;
    private int currentFloor;


    public LookAlgorithmStrategy() {
        this.upRequests = new ConcurrentSkipListSet<>();
        this.downRequests = new ConcurrentSkipListSet<>(Collections.reverseOrder());
        currentFloor = 0;
    }

    @Override
    public void move(ElevatorCar elevatorCar) {
        while (true) {
            if (upRequests.isEmpty() && downRequests.isEmpty()) {
                if (elevatorCar.getDirection() != Direction.IDLE) {
                    System.out.println("Elevator " + elevatorCar.getId() + " is Idle");
                    elevatorCar.setDirection(Direction.IDLE);
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                currentFloor = elevatorCar.getCurrentFloor();
                continue;
            }

            if (elevatorCar.getDirection() == Direction.IDLE) {
                if (!upRequests.isEmpty()) {
                    elevatorCar.setDirection(Direction.Up);
                } else {
                    elevatorCar.setDirection(Direction.DOWN);
                }
            }

            if (elevatorCar.getDirection() == Direction.Up) {
                Optional<Integer> nextStop = upRequests.stream()
                        .filter(f -> f >= elevatorCar.getCurrentFloor())
                        .findFirst();

                if (nextStop.isPresent()) {
                    int target = nextStop.get();
                    if (elevatorCar.getCurrentFloor() < target) {
                        elevatorCar.setCurrentFloor(elevatorCar.getCurrentFloor() + 1);
                        System.out.println("Elevator " + elevatorCar.getId() + " going up to floor " + elevatorCar.getCurrentFloor());
                    } else {
                        upRequests.remove(target);
                        elevatorCar.notifyObservers(target);
                        System.out.println("Elevator " + elevatorCar.getId() + " served floor " + target);
                    }
                } else {
                    // LOOK: reverse direction immediately (don’t go to top floor)
                    elevatorCar.setDirection(Direction.DOWN);
                }

            } else if (elevatorCar.getDirection() == Direction.DOWN) {
                Optional<Integer> nextStop = downRequests.stream()
                        .filter(f -> f <= elevatorCar.getCurrentFloor())
                        .findFirst();

                if (nextStop.isPresent()) {
                    int target = nextStop.get();
                    if (elevatorCar.getCurrentFloor() > target) {
                        elevatorCar.setCurrentFloor(elevatorCar.getCurrentFloor() - 1);
                        System.out.println("Elevator " + elevatorCar.getId() + " going down to floor " + elevatorCar.getCurrentFloor());
                    } else {
                        downRequests.remove(target);
                        elevatorCar.notifyObservers(target);
                        System.out.println("Elevator " + elevatorCar.getId() + " served floor " + target);
                    }
                } else {
                    // LOOK: reverse immediately (no need to go to bottom floor)
                    elevatorCar.setDirection(Direction.Up);
                }
            }
            currentFloor = elevatorCar.getCurrentFloor();
        }
    }

    @Override
    public void acceptRequest(Request request) {
        if (request == null) {
            System.err.println("Invalid request");
            return;
        }

        int floor = request.getTargetFloor();
        Direction dir = request.getDirection();
        if (request.getRequestSource() == RequestSource.EXTERNAL) {
            // External request — direction known
            if (dir == Direction.Up) {
                upRequests.add(floor);
            } else if (dir == Direction.DOWN) {
                downRequests.add(floor);
            }
        } else {
            // Internal request — infer direction using last known floor
            if (floor > currentFloor) {
                upRequests.add(floor);
            } else if (floor < currentFloor) {
                downRequests.add(floor);
            } else {
                System.out.println("Already at requested floor " + floor);
            }
        }
    }
}
