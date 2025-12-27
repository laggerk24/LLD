package Models;

import Factory.Vehicle;

import java.sql.Timestamp;
import java.util.UUID;

public class Ticket {
    private String id;
    private Vehicle vehicle;
    private Timestamp entryTime;
    private Timestamp exitTime;
    private ParkingSpot parkingSpot;

    public Ticket(Vehicle vehicle, ParkingSpot parkingSpot) {
        this.id = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.entryTime = new Timestamp(System.currentTimeMillis());
        this.parkingSpot = parkingSpot;
    }

    public String getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Timestamp getEntryTime() {
        return entryTime;
    }

    public Timestamp getExitTime() {
        return exitTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setExitTime() {
        this.exitTime = new Timestamp(System.currentTimeMillis());
    }
}
