package Models;

import java.util.concurrent.ConcurrentHashMap;

public class ParkingFloor {
    private int floor;
    private ConcurrentHashMap<Integer,ParkingSpot> parkingSpots;

    public ParkingFloor(int floor) {
        this.floor = floor;
        this.parkingSpots = new ConcurrentHashMap<>();
    }

    public void addParkingSpot(ParkingSpot parkingSpot){
        this.parkingSpots.put(parkingSpot.getId(), parkingSpot);
    }

    public int getFloor() {
        return floor;
    }

    public ConcurrentHashMap<Integer,ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void displayAvaiablility(){
        for(int parkingSpotId: parkingSpots.keySet()){
            ParkingSpot parkingSpot = parkingSpots.get(parkingSpotId);
            if(parkingSpot.getIsAvailable()){
                System.out.println("Floor " + floor + " Parking spot Type " + parkingSpot.getPakringType() + " available " + " Spot Id - " + parkingSpot.getId());
            }
        }
    }
}
