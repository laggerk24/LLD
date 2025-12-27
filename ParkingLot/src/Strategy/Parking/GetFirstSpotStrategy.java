package Strategy.Parking;

import Factory.Vehicle;
import Models.ParkingFloor;
import Models.ParkingSpot;

import java.util.List;

public class GetFirstSpotStrategy implements ParkingStrategy{

    // Concurrency handled in this strategy
    @Override
    public ParkingSpot getParkingSpot(List<ParkingFloor> parkingFloors, Vehicle vehicle) {
//        for(ParkingFloor parkingFloor: parkingFloors){
//            for(ParkingSpot parkingSpot: parkingFloor.getParkingSpots().values()){
//                if(parkingSpot.getIsAvailable() && parkingSpot.getPakringType() == vehicle.getVehicleType()){
//                    return parkingSpot;
//                }
//            }
//        }
        // This is non blocking and It will keep on trying till spots are completed
        for(ParkingFloor parkingFloor: parkingFloors){
            for(ParkingSpot parkingSpot: parkingFloor.getParkingSpots().values()){
                if(parkingSpot.getPakringType() == vehicle.getVehicleType()){
                    if(parkingSpot.parkVehicle(vehicle)){
                        return parkingSpot;
                    }
                }
            }
        }
        return null;
    }
}
