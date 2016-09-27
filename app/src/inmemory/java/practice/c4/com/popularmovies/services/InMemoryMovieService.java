package practice.c4.com.popularmovies.services;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import practice.c4.com.popularmovies.entites.Movie;
import practice.c4.com.popularmovies.infrastructure.PopularMoviesApplication;

public class InMemoryMovieService extends BaseInMemeoryService {

    public InMemoryMovieService(PopularMoviesApplication application) {
        super(application);
    }


    @Subscribe
    public void searchMovies(Movies.SearchMoviesRequest request){
        Movies.SearchMoviesResponse response = new Movies.SearchMoviesResponse();
        response.Movies = new ArrayList<>();
        for(int i = 0;i<10;i++)
            response.Movies.add(new Movie(i,
                    "Movie " + i,
                    "http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg",
                    "This is where the Summary of movie " +  i + " Will go",
                    "1999",
                    i+1.5));

        bus.post(response);
    }


}
