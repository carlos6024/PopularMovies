package practice.c4.com.popularmovies.services;


import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import practice.c4.com.popularmovies.infrastructure.PopularMoviesApplication;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Module {
    private static final String TAG = Module.class.getSimpleName();
    public static void register (PopularMoviesApplication application){
        MovieWebService api = createMovieService(application);
        new LiveMovieService(application,api);
        Log.i(TAG,"LIVE REGISTER METHOD CALLED");
    }


    private static MovieWebService createMovieService(PopularMoviesApplication application){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        String Api_endpoint = application.getApiEndpoint();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api_endpoint)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(MovieWebService.class);
    }
}
