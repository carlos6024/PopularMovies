package practice.c4.com.popularmovies.entites;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

public class Movie implements Parcelable{
    private int movieId;
    private String movieTitle;
    private String movieImage;
    private String movieSynopsis;
    private String movieRelaseDate;
    private double voteAverage;


    public Movie(int movieId,String movieTitle, String movieImage, String movieSynopsis, String relaseDate, double voteAverage) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieImage = movieImage;
        this.movieSynopsis = movieSynopsis;
        this.movieRelaseDate = relaseDate;
        this.voteAverage = voteAverage;
    }

    protected Movie(Parcel in) {
        movieId = in.readInt();
        movieTitle = in.readString();
        movieImage = in.readString();
        movieSynopsis = in.readString();
        movieRelaseDate = in.readString();
        voteAverage = in.readDouble();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(movieId);
        dest.writeString(movieTitle);
        dest.writeString(movieImage);
        dest.writeString(movieSynopsis);
        dest.writeString(movieRelaseDate);
        dest.writeDouble(voteAverage);
    }


    /*
    public static Comparator<Movie> popularityComparator = new Comparator<Movie>() {
        @Override
        public int compare(Movie movie1, Movie movie2) {
            double movie1PopularRating = movie1.getPopularity();
            double movie2PopularRating = movie2.getPopularity();
            int returnValue = 0;

            if(movie1PopularRating<movie2PopularRating){
                returnValue = 1;
            }

            if(movie1PopularRating>movie2PopularRating){
                returnValue= -1;
            }
            return returnValue;
        }};
*/

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public String getMovieSynopsis() {
        return movieSynopsis;
    }

    public void setMovieSynopsis(String movieSynopsis) {
        this.movieSynopsis = movieSynopsis;
    }


    public String getMovieRelaseDate() {
        return movieRelaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
