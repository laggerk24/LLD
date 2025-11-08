package Strategy.Pricing;

import Models.Ticket;
import Enum.*;
import java.util.Map;

public class VehicleBasedPriceStrategy implements PricingStrategy{
    // HashMap doesnt support Map.of
    private final Map<VehicleType,Double> vehicleBasedPrice = Map.of(
            VehicleType.TWOWHEELER,10.00,
            VehicleType.FOURWHEELER,20.00,
            VehicleType.TRUCK,40.00
    );

    @Override
    public double calculateParkingFee(Ticket ticket) {
        long timeDIfference = ticket.getExitTime().getTime() - ticket.getEntryTime().getTime();
        long hours = (timeDIfference/(1000*60*60))+1;
        double price = vehicleBasedPrice.get(ticket.getVehicle().getVehicleType());
        return hours*price;
    }
}
