package practice.c4.com.popularmovies.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Collections;

import practice.c4.com.popularmovies.R;
import practice.c4.com.popularmovies.activities.MoviePagerActivity;
import practice.c4.com.popularmovies.entites.Movie;
import practice.c4.com.popularmovies.infrastructure.BaseFragmentActivity;
import practice.c4.com.popularmovies.services.Movies;
import practice.c4.com.popularmovies.views.MovieAdapter;

public class MovieListFragment extends BaseFragment implements MovieAdapter.OnMovieClickedListener {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    public static ArrayList<Movie> movies;
    private final String LOG_TAG = MovieListFragment.class.getSimpleName();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies,container,false);
        adapter = new MovieAdapter((BaseFragmentActivity) getActivity(),this);
        movies = adapter.getMovies();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.movie_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        setUpAdapter();
        return rootView;
    }


    private void setUpAdapter(){
        if(isAdded()){
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String sortType = sharedPreferences.getString(getString(R.string.preference_order_key),
                getString(R.string.preference_order_default));
        Log.v(LOG_TAG,sortType);

        bus.post(new Movies.SearchMoviesRequest(sortType));
    }

    @Override
    public void onMovieClickedListener(final Movie movie) {
        Intent intent = MoviePagerActivity.newIntent(getActivity(),movie);
        Log.i(LOG_TAG,movie.getMovieTitle());
        startActivity(intent);

    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.movielist_fragment_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.movieList_menu_setting);
    }

    @Subscribe
    public void onMoviesLoaded(final Movies.SearchMoviesResponse response){
        movies.clear();
        movies.addAll(response.Movies);
        setUpAdapter();
        adapter.notifyDataSetChanged();
    }
}
