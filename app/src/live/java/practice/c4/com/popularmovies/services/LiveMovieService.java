package practice.c4.com.popularmovies.services;

import android.util.Log;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import practice.c4.com.popularmovies.entites.Movie;
import practice.c4.com.popularmovies.infrastructure.PopularMoviesApplication;
import practice.c4.com.popularmovies.model.Children;
import practice.c4.com.popularmovies.model.MovieInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveMovieService extends BaseLiveService {
    private final String TAG = LiveMovieService.class.getSimpleName();

    public LiveMovieService(PopularMoviesApplication application, MovieWebService api) {
        super(application, api);
    }


    @Subscribe
    public void loadMovies(Movies.SearchMoviesRequest request){
        final Movies.SearchMoviesResponse movieResponse = new Movies.SearchMoviesResponse();
        movieResponse.Movies = new ArrayList<>();
        Call<Children> call = api.loadMovies(request.searchType,application.getApiKey());
        call.enqueue(new Callback<Children>() {
            @Override
            public void onResponse(Call<Children> call, Response<Children> response) {
                int size = response.body().movieInfo.size();
                int i =0;
                String[] MovieTitles = new String[size];
                String[] MoviePosters = new String[size];
                String[] MovieSummarys = new String[size];
                String[] MovieRelaseDate= new String[size];
                Double[] MovieVotes = new Double[size];
                for(MovieInfo movieInfo: response.body().movieInfo){
                    MovieTitles[i] = movieInfo.getMovieTitle();
                    MoviePosters[i] = application.MOVIE_IMAGE_BASE_URL + movieInfo.getMoviePoster();
                    MovieSummarys[i] = movieInfo.getMovieSummary();
                    MovieRelaseDate[i] = movieInfo.getMovieRelaseDate();
                    MovieVotes[i] = movieInfo.getMovieVotes();
                    Log.v(TAG,MoviePosters[i]);
                    i++;
                }

                for(int i2 =0 ;i2<size;i2++){
                    movieResponse.Movies.add(new Movie(i2,
                            MovieTitles[i2],
                            MoviePosters[i2],
                            MovieSummarys[i2],
                            MovieRelaseDate[i2],
                            MovieVotes[i2]));
                }

                bus.post(movieResponse);

            }

            @Override
            public void onFailure(Call<Children> call, Throwable t) {
                Log.e(TAG,t.getMessage());
            }
        });
    }

}
