package practice.c4.com.popularmovies.services;

import android.util.Log;

import practice.c4.com.popularmovies.infrastructure.PopularMoviesApplication;

public class Module {
    private static final String TAG = Module.class.getSimpleName();
    public static void register(PopularMoviesApplication application){
        Log.v(TAG,"IN MEMORY REGISTER METHOD CALLED");
        new InMemoryMovieService(application);
    }

}
