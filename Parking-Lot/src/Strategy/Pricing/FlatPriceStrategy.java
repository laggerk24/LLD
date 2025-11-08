package Strategy.Pricing;

import Models.Ticket;

public class FlatPriceStrategy implements PricingStrategy{
    private static final double price = 20.00;


    @Override
    public double calculateParkingFee(Ticket ticket) {
        long timeDifference = ticket.getExitTime().getTime()-ticket.getEntryTime().getTime();
        long hours = (timeDifference/(1000*60*60))+1;
        return hours*price;
    }
}
