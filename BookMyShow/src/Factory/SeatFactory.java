package Factory;

import Enums.SeatCategory;

public class SeatFactory {
    public static Seat getSeats(int id, SeatCategory seatCategory){
        switch (seatCategory){
            case SILVER -> {
                return new SilverSeat(id);
            }
            case GOLD -> {
                return new GoldSeat(id);
            }
            case PLATINUM -> {
                return new PlatinumSeat(id);
            }
            default -> throw new RuntimeException("Incorrect Seat Category");
        }
    }
}
