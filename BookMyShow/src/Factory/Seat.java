package Factory;

import Enums.SeatCategory;

public abstract class Seat {
    int seatId;
    SeatCategory seatCategory;
    int row;

    public Seat(int seatId, SeatCategory seatCategory) {
        this.seatId = seatId;
        this.seatCategory = seatCategory;
    }

    public int getSeatId() {
        return seatId;
    }

    public SeatCategory getSeatCategory() {
        return seatCategory;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
