package Models;

import java.util.concurrent.atomic.AtomicBoolean;
import Enum.*;
import Factory.Vehicle;

public class ParkingSpot {
    private int id;
    private AtomicBoolean isAvailable;
    private VehicleType vehicleType;
    // made it volatile so many threads can see its value
    private volatile Vehicle vehicleParked;

    public ParkingSpot(int id,VehicleType vehicleType) {
        this.id = id;
        this.vehicleType = vehicleType;
        isAvailable = new AtomicBoolean(true);
    }

    public boolean getIsAvailable() {
        return isAvailable.get();
    }

    public boolean parkVehicle(Vehicle vehicle){
        // If I dont use compare and Set then we are having race conditions
        if (!isAvailable.compareAndSet(true, false)) {
            System.err.println("Spot " + id + " is already occupied.");
            return false;
        }
        vehicleParked = vehicle;
        return true;
    }

    public void unParkVehicle(){
        vehicleParked = null;
        isAvailable.set(true);
    }

    public VehicleType getPakringType() {
        return vehicleType;
    }

    public int getId() {
        return id;
    }
}
