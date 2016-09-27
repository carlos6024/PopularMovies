package practice.c4.com.popularmovies.model;

import com.google.gson.annotations.SerializedName;

public class MovieInfo {
    @SerializedName("original_title")
    String movieTitle;

    @SerializedName("backdrop_path")
    String moviePoster;

    @SerializedName("overview")
    String movieSummary;

    @SerializedName("release_date")
    String movieRelaseDate;

    @SerializedName("vote_average")
    double movieVotes;


    public MovieInfo(String movieTitle, String moviePoster) {
            this.movieTitle = movieTitle;
            this.moviePoster = moviePoster;
    }

    public String getMovieTitle() {
            return movieTitle;
    }

    public String getMoviePoster() {
            return moviePoster;
    }


    public String getMovieSummary() {
        return movieSummary;
    }

    public String getMovieRelaseDate() {
        return movieRelaseDate;
    }

    public double getMovieVotes() {
        return movieVotes;
    }
}

