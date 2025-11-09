package Models;

import Enums.City;
import Enums.SeatCategory;
import Factory.Seat;
import Factory.SeatFactory;

import java.util.*;

public class BookMyShow {
    private static BookMyShow instance;

    MovieController movieController;
    TheatreController theatreController;
    // map to manage the Booking
    HashMap<String,Booking> allBookings;

    private BookMyShow() {
        movieController = new MovieController();
        theatreController = new TheatreController();
        allBookings = new HashMap<>();
        initialize();
    }

    public static BookMyShow getInstance(){
        if(instance == null) {
            synchronized (BookMyShow.class){
                if(instance == null){
                    instance = new BookMyShow();
                }
            }
        }
        return instance;
    }

    public void moviesInCity(City city){
        List<Movie> movies = movieController.getMoviesByCity(city);
        if(movies.isEmpty()){
            System.out.println("No movies Present in the city");
        }
        int count = 1;
        for(Movie movie: movies){
            System.out.println(count++ + "." + movie.getMovieName() + " duration - " + movie.getMovieDuration());
        }
    }

    public void getAllShowsOfAMovieInCity(City city,String movieName){
        Movie movie = movieController.getMovieByName(movieName);
        if(movie == null){
            System.out.println("Movie" + movieName + " not present in the system ");
            return;
        }
        Map<Theatre, List<Show>> shows = theatreController.getAllShow(movie,city);
        if(shows.isEmpty()){
            System.out.println("No movie is present in the city " + city.toString());
            return;
        }

        for(Theatre theatre: shows.keySet()){
            int count = 0;
            for(Show show : shows.get(theatre)){
                if(show.getMovie().getMovieName().equals(movie.getMovieName())){
                    if(count == 0){
                        System.out.println("Theater Id : " + theatre.getTheatreId());
                        count++;
                    }
                    System.out.println("Show Id : " + show.getShowId() + ", movie : " +
                            show.getMovie().getMovieName() + ", show timing : " + show.getShowStartTime());
                }
            }
        }
    }

    public void getAllAvaibleSeatsOfAShow(int theaterId, int showId){
        Show show = theatreController.getShowByTheatreIdAndShowId(theaterId,showId);
        if(show == null){
            System.err.println("Invalid show Id " + showId);
            return;
        }
        show.getAllAvaiableSeats();
    }

    public void createBooking(int theaterId, int showId, List<Integer> seatsRequested) {
        Show show = theatreController.getShowByTheatreIdAndShowId(theaterId,showId);
        if(show == null){
            System.err.println("Invalid show Id " + showId);
            return;
        }

        boolean seatsAreBooked = show.tryBookingAllSeats(seatsRequested);
        if(!seatsAreBooked){
            return;
        }
        List<Seat> myBookedSeats = new ArrayList<>();
        for(int seatId : seatsRequested){
            for(Seat seat: show.getScreen().getSeats()){
                if(seat.getSeatId() == seatId){
                    myBookedSeats.add(seat);
                }
            }
        }
        Booking booking = new Booking(show,myBookedSeats);
        Payment payment = new Payment(UUID.randomUUID().toString());
        booking.setPayment(payment);
        allBookings.put(booking.bookingId, booking);
        System.out.println("Your booking is created Successfully " + booking.getBookingId());
    }

    private void initialize() {
        //create movies
        createMovies();
        //create theater with screens, seats and shows
        createTheatre();
    }

    //creating 2 theatre
    private void createTheatre() {

        Movie avengerMovie = movieController.getMovieByName("AVENGERS");
        Movie baahubali = movieController.getMovieByName("BAAHUBALI");

        Theatre inoxTheatre = new Theatre(1,City.Bangalore);
        inoxTheatre.setScreen(createScreen());

        List<Show> inoxShows = new ArrayList<>();
        Show inoxMorningShow = createShows(1, inoxTheatre.getScreen().get(0), avengerMovie, 8);
        Show inoxEveningShow = createShows(2, inoxTheatre.getScreen().get(0), baahubali, 16);

        inoxShows.add(inoxMorningShow);
        inoxShows.add(inoxEveningShow);
        inoxTheatre.setShows(inoxShows);


        Theatre pvrTheatre = new Theatre(2,City.Hyderabad);
        pvrTheatre.setScreen(createScreen());

        List<Show> pvrShows = new ArrayList<>();

        Show pvrMorningShow = createShows(3, pvrTheatre.getScreen().get(0), avengerMovie, 13);
        Show pvrEveningShow = createShows(4, pvrTheatre.getScreen().get(0), baahubali, 20);

        pvrShows.add(pvrMorningShow);
        pvrShows.add(pvrEveningShow);
        pvrTheatre.setShows(pvrShows);

        theatreController.addTheatre(inoxTheatre, City.Bangalore);
        theatreController.addTheatre(pvrTheatre, City.Hyderabad);

    }

    private List<Screen> createScreen() {
        List<Screen> screens = new ArrayList<>();
        Screen screen1 = new Screen(1,createSeats());
        screens.add(screen1);

        return screens;
    }

    private Show createShows(int showId, Screen screen, Movie movie, int showStartTime) {
        return new Show(showId,movie,screen,showStartTime);
    }

    //creating 15 seats
    private List<Seat> createSeats() {
        //creating 100 seats for testing purpose, this can be generalised
        List<Seat> seats = new ArrayList<>();
        //0 to 4 : SILVER
        for (int i = 0; i < 5; i++) {
            seats.add(SeatFactory.getSeats(i,SeatCategory.SILVER));
        }
        //5 to 9 : SILVER
        for (int i = 5; i < 10; i++) {
            seats.add(SeatFactory.getSeats(i,SeatCategory.GOLD));
        }
        //10 to 14 : SILVER
        for (int i = 10; i < 15; i++) {
            seats.add(SeatFactory.getSeats(i,SeatCategory.PLATINUM));
        }
        return seats;
    }

    private void createMovies() {

        //create Movies1
        Movie avengers = new Movie(1,"AVENGERS",128);
        //create Movies2
        Movie baahubali = new Movie(2,"BAAHUBALI",180);

        //add movies against the cities
        movieController.addMovie(avengers, City.Bangalore);
        movieController.addMovie(avengers, City.Hyderabad);
        movieController.addMovie(baahubali, City.Bangalore);
        movieController.addMovie(baahubali, City.Hyderabad);
    }
}
