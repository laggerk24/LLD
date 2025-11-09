package Factory;

import Enums.SeatCategory;

public class GoldSeat extends Seat{

    public GoldSeat(int seatId) {
        super(seatId, SeatCategory.GOLD);
    }
}
