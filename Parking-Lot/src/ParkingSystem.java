import Enum.*;
import Factory.Vehicle;
import Factory.VehicleFactory;
import Models.ParkingFloor;
import Models.ParkingSpot;
import Models.Ticket;
import Strategy.Parking.*;
import Strategy.Pricing.PricingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingSystem {
    private static ParkingSystem parkingSystem;
    private ParkingStrategy parkingStrategy;
    private PricingStrategy pricingStrategy;
    private List<ParkingFloor> parkingFloors;
    private ConcurrentHashMap<String, Ticket> parkingTickets;

    public static ParkingSystem getInstance(){
        if(parkingSystem == null){
            synchronized (ParkingSystem.class){
                if(parkingSystem == null){
                    parkingSystem = new ParkingSystem();
                }
            }
        }
        return parkingSystem;
    }

    private ParkingSystem(){
        parkingStrategy = new GetFirstSpotStrategy();
        parkingFloors = new ArrayList<>();
        parkingTickets = new ConcurrentHashMap<>();

        int count = 0;
        for(int i=1;i<=1;i++){
            ParkingFloor floor = new ParkingFloor(i);
            for(int j=0;j<6;j++){
                if(j <= 1){
                    floor.addParkingSpot(new ParkingSpot(count++,VehicleType.TRUCK));
                } else if(j <3){
                    floor.addParkingSpot(new ParkingSpot(count++,VehicleType.TWOWHEELER));
                } else {
                    floor.addParkingSpot(new ParkingSpot(count++,VehicleType.FOURWHEELER));
                }
            }
            parkingFloors.add(floor);
        }
    }

    public void showAllParkingSpots(){
        for(ParkingFloor parkingFloor: parkingFloors){
            parkingFloor.displayAvaiablility();
        }
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    // If I write syncronised here then it will block the thread for all the vehicles, which is slow
    public Ticket parkVehicle(String vehicleNumber, VehicleType vehicleType){
        Vehicle vehicle = VehicleFactory.getVehicle(vehicleNumber,vehicleType);
        ParkingSpot spot = parkingStrategy.getParkingSpot(parkingFloors,vehicle);
        if(spot == null) {
            System.err.println("No Parking spot available");
            return null;
        }
        Ticket ticket = new Ticket(vehicle,spot);
        parkingTickets.put(ticket.getId(),ticket);
        System.out.println("Vehicle Parked Successfully Ticket Id " + ticket.getId());
        return ticket;
    }

    public Double unParkVehicle(Ticket ticket){
        if(ticket == null) {
            System.err.println("Invalid Ticket");
            return 0.00;
        }
        ticket.setExitTime();
        ticket.getParkingSpot().unParkVehicle();
        return pricingStrategy.calculateParkingFee(ticket);
    }
}
