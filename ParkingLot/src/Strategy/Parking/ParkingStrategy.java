package Strategy.Parking;

import Factory.Vehicle;
import Models.ParkingFloor;
import Models.ParkingSpot;

import java.util.List;

public interface ParkingStrategy {
    ParkingSpot getParkingSpot(List<ParkingFloor> parkingFloors, Vehicle vehicle);
}
