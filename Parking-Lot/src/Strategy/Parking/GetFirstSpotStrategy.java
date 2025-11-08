package Strategy.Parking;

import Factory.Vehicle;
import Models.ParkingFloor;
import Models.ParkingSpot;

import java.util.List;

public class GetFirstSpotStrategy implements ParkingStrategy{

    @Override
    public ParkingSpot getParkingSpot(List<ParkingFloor> parkingFloors, Vehicle vehicle) {
        for(ParkingFloor parkingFloor: parkingFloors){
            for(ParkingSpot parkingSpot: parkingFloor.getParkingSpots().values()){
                if(parkingSpot.getIsAvailable() && parkingSpot.getPakringType() == vehicle.getVehicleType()){
                    return parkingSpot;
                }
            }
        }
        return null;
    }
}
