package Strategy.Pricing;

import Models.Ticket;

public interface PricingStrategy {
    double calculateParkingFee(Ticket ticket);
}
