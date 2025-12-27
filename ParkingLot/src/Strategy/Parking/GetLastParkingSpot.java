package Strategy.Parking;

import Factory.Vehicle;
import Models.ParkingFloor;
import Models.ParkingSpot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetLastParkingSpot implements ParkingStrategy{
    @Override
    public ParkingSpot getParkingSpot(List<ParkingFloor> parkingFloors, Vehicle vehicle) {
        List<ParkingFloor> reverseParkingFloors = new ArrayList<>(parkingFloors);
        Collections.reverse(reverseParkingFloors);

        for(ParkingFloor floor: reverseParkingFloors){
            for(ParkingSpot parkingSpot: floor.getParkingSpots().values()){
                if(parkingSpot.getIsAvailable() && parkingSpot.getPakringType() == vehicle.getVehicleType()){
                    return parkingSpot;
                }
            }
        }

        return  null;
    }
}
