package Models;

import Factory.Seat;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Show {

    int showId;
    Movie movie;
    Screen screen;
    int showStartTime;
    Set<Integer> bookedSeatIds;

    public Show(int showId, Movie movie, Screen screen, int showStartTime) {
        this.showId = showId;
        this.movie = movie;
        this.screen = screen;
        this.showStartTime = showStartTime;
        bookedSeatIds = ConcurrentHashMap.newKeySet();
    }

    public int getShowId() {
        return showId;
    }


    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public int getShowStartTime() {
        return showStartTime;
    }

    public Set<Integer> getBookedSeatIds() {
        return bookedSeatIds;
    }

    // Reason to move the logic of booking here is :
    // moved the locking to smallest object so least blocking happens in our system
    // Can further be optimized using locking at seat level than show level
    public synchronized boolean tryBookingAllSeats(List<Integer> seats){
        for(int seat:seats){
            if(bookedSeatIds.contains(seat)){
                System.err.println("Your booking failed ");
                System.err.println("Seat Id " + seat + " is already booked");
                return false;
            }
        }
        bookedSeatIds.addAll(seats);
        return true;
    }

    public void getAllAvaiableSeats(){
        for(Seat seat: screen.getSeats()){
            if(!bookedSeatIds.contains(seat.getSeatId())){
                System.out.println(seat.getSeatId()+ ". " + seat.getSeatCategory().toString());
            }
        }
    }
}

