package practice.c4.com.popularmovies.infrastructure;

import android.app.Application;

import com.squareup.otto.Bus;

import practice.c4.com.popularmovies.services.Module;

public class PopularMoviesApplication extends Application {
    private Bus bus;
    public static final String API_ENDPOINT = "https://api.themoviedb.org";
    public static final String MOVIE_IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185/";
    public static final String API_KEY = "de796ac332138ada3a3d8c79b4171513";

    public PopularMoviesApplication(){
        bus = new Bus();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Module.register(this);
    }

    public Bus getBus() {
        return bus;
    }

    public  String getApiKey() {
        return API_KEY;
    }

    public  String getMovieImageBaseUrl() {
        return MOVIE_IMAGE_BASE_URL;
    }

    public  String getApiEndpoint() {
        return API_ENDPOINT;
    }
}
