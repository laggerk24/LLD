package Models;

import Factory.Seat;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    int screenId;
    List<Seat> seats = new ArrayList<>();

    public Screen(int screenId, List<Seat> seats) {
        this.screenId = screenId;
        this.seats = seats;
    }

    public int getScreenId() {
        return screenId;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
