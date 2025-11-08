package Factory;

import Enum.*;

public class VehicleFactory {
    public static Vehicle getVehicle(String number, VehicleType vehicleType){
        switch (vehicleType) {
            case TWOWHEELER -> {
                return new TwoWheeler(number,VehicleType.TWOWHEELER);
            }
            case FOURWHEELER -> {
                return new FourWheeler(number,VehicleType.FOURWHEELER);
            }
            case TRUCK -> {
                return new Truck(number,VehicleType.TRUCK);
            }
            default -> throw new IllegalArgumentException("Wrong Vehile type provided");
        }
    }
}
