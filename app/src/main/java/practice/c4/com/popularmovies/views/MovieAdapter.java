package practice.c4.com.popularmovies.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import practice.c4.com.popularmovies.R;
import practice.c4.com.popularmovies.entites.Movie;
import practice.c4.com.popularmovies.infrastructure.BaseFragmentActivity;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> implements View.OnClickListener {
    private final LayoutInflater layoutInflater;
    private final BaseFragmentActivity activity;
    private final OnMovieClickedListener listener;
    private final ArrayList<Movie> movies;


    public MovieAdapter(BaseFragmentActivity activity, OnMovieClickedListener listener) {
        this.activity = activity;
        this.listener = listener;
        movies = new ArrayList<>();
        layoutInflater = activity.getLayoutInflater();
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_movie,parent,false);
        view.setOnClickListener(this);
        MovieViewHolder viewHolder = new MovieViewHolder(view,activity);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.popluate(activity,movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public void onClick(View view) {
        if (view.getTag() instanceof Movie){
            Movie movie = (Movie) view.getTag();
            listener.onMovieClickedListener(movie);
        }
    }


    public interface OnMovieClickedListener{
        void onMovieClickedListener(Movie movie);
    }
}
