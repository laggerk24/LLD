package Models;

import Enums.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreController {

    Map<City, List<Theatre>> cityVsTheatre;
    List<Theatre> allTheatre;

    TheatreController() {
        cityVsTheatre = new HashMap<>();
        allTheatre = new ArrayList<>();
    }

    void addTheatre(Theatre theatre, City city) {

        allTheatre.add(theatre);

        List<Theatre> theatres = cityVsTheatre.getOrDefault(city, new ArrayList<>());
        theatres.add(theatre);
        cityVsTheatre.put(city, theatres);
    }


    Map<Theatre, List<Show>> getAllShow(Movie movie, City city) {
        //get all the theater of this city
        Map<Theatre, List<Show>> theatreVsShows = new HashMap<>();
        List<Theatre> theatres = cityVsTheatre.get(city);

        //filter the theatres which run this movie
        for(Theatre theatre : theatres) {
            List<Show> givenMovieShows = new ArrayList<>();
            for(Show show : theatre.getShows()) {
                if(show.movie.getMovieId() == movie.getMovieId()) {
                    givenMovieShows.add(show);
                }
            }
            if(!givenMovieShows.isEmpty()) {
                theatreVsShows.put(theatre, givenMovieShows);
            }
        }

        return theatreVsShows;
    }

    public Show getShowByTheatreIdAndShowId(int theaterId, int showId){
        Theatre theatre = allTheatre.stream()
                .filter(t -> t.getTheatreId() == theaterId)
                .findFirst()
                .orElse(null);

        if(theatre == null) {
            System.err.println("Invalid theater Id " + theaterId);
            return null;
        }

        return theatre.getShows().stream().filter(show1 -> show1.getShowId() == showId).findAny().orElse(null);
    }
}

