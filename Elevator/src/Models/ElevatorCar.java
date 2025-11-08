package Models;

import java.util.ArrayList;
import java.util.List;
import Enum.*;
import Observers.ElevatorDisplay;
import Observers.ElevatorDoor;
import Observers.ElevatorObservers;

public class ElevatorCar {
    private int id;
    private int currentFloor;
    private Direction direction;
    private List<ElevatorObservers> observers;

    public ElevatorCar(int id, int currentFloor, Direction direction) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.direction = direction;
        observers = new ArrayList<>();
        observers.add(new ElevatorDisplay());
        observers.add(new ElevatorDoor());
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void notifyObservers(int floor){
        for(ElevatorObservers observer: observers){
            observer.notifyObservers(this);
        }
    }
}
