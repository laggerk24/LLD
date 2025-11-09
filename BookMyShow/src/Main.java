import Enums.City;
import Models.BookMyShow;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        BookMyShow bookMyShow = BookMyShow.getInstance();
        bookMyShow.moviesInCity(City.Bangalore);
        bookMyShow.getAllShowsOfAMovieInCity(City.Bangalore,"BAAHUBALI");
        bookMyShow.getAllAvaibleSeatsOfAShow(1,2);

        //user1
        Thread t1 = new Thread(()->{
            bookMyShow.createBooking(1,2, List.of(0,1,2));
        });
        //user2
        Thread t2 = new Thread(()->{
            bookMyShow.createBooking(1,2, List.of(0,1,2));
        });
//
//        // user 3
        Thread t3 = new Thread(()->{
            bookMyShow.createBooking(1,2, List.of(0,1,2));
        });
//
//        // user 4
        Thread t4 = new Thread(()->{
            bookMyShow.createBooking(1,2, List.of(0,1,2));
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}