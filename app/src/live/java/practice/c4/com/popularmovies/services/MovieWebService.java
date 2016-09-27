package practice.c4.com.popularmovies.services;

import practice.c4.com.popularmovies.model.Children;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieWebService {
    @GET("/3/movie/{parameter}")
    Call<Children> loadMovies(
            @Path("parameter") String requestType,
            @Query("api_key") String APIKey);
}
