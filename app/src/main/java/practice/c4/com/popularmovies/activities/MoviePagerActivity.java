package practice.c4.com.popularmovies.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import practice.c4.com.popularmovies.R;
import practice.c4.com.popularmovies.entites.Movie;
import practice.c4.com.popularmovies.fragments.MovieDetailFragment;
import practice.c4.com.popularmovies.infrastructure.BaseActivity;
import practice.c4.com.popularmovies.services.Movies;


public class MoviePagerActivity extends BaseActivity {


    private List<Movie> movies;
    private ViewPager mViewPager;
    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    private static final String TAG = MoviePagerActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_pager);
        movies = new ArrayList<>();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        String sortType = sharedPreferences.getString(getString(R.string.preference_order_key),
                getString(R.string.preference_order_default));

        bus.post(new Movies.SearchMoviesRequest(sortType));
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager = (ViewPager) findViewById(R.id.activity_movie_pager_view_pager);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Movie movie = movies.get(position);
                return MovieDetailFragment.newInstance(movie);
            }

            @Override
            public int getCount() {
                return movies.size();
            }
        });

        /* Uncomment For imMemory Debug to work

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        int movieId = movie.getMovieId();
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getMovieId() == movieId) {
                mViewPager.setCurrentItem(i);
                break;
            }


         */

    }




    @Subscribe
    public void onMoviesLoaded(final Movies.SearchMoviesResponse response) {
        movies.clear();
        movies.addAll(response.Movies);

        //Comment out the rest of this method for inmemory debug to work
        mViewPager.getAdapter().notifyDataSetChanged();


        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        int movieId = movie.getMovieId();
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getMovieId() == movieId) {
                mViewPager.setCurrentItem(i);
                break;
            }

        }
    }


    public static Intent newIntent(Context packageContext, Movie movie){
        Intent intent = new Intent(packageContext,MoviePagerActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }
}
