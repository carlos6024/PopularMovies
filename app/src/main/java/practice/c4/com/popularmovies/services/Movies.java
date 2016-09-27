package practice.c4.com.popularmovies.services;

import java.util.List;

import practice.c4.com.popularmovies.entites.Movie;

public class Movies {

    private Movies(){
    }

    public static class SearchMoviesRequest{
        public String searchType;
        public SearchMoviesRequest(String searchType) {
            this.searchType = searchType;
        }
    }


    public static class SearchMoviesResponse {
        public List<Movie> Movies;

    }


}
