package Models;

import Factory.Seat;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Booking {
    String bookingId;
    Show show;
    List<Seat> bookedSeats = new ArrayList<>();
    Payment payment;

    public Booking(Show show, List<Seat> bookedSeats) {
        this.bookingId = UUID.randomUUID().toString();
        this.show = show;
        this.bookedSeats = bookedSeats;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getBookingId() {
        return bookingId;
    }
}

