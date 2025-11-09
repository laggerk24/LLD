package Factory;

import Enums.SeatCategory;

public class PlatinumSeat extends Seat{
    public PlatinumSeat(int seatId) {
        super(seatId, SeatCategory.PLATINUM);
    }
}
