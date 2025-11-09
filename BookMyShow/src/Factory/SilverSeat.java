package Factory;

import Enums.SeatCategory;

public class SilverSeat extends Seat{
    public SilverSeat(int seatId) {
        super(seatId, SeatCategory.SILVER);
    }
}
