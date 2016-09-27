package practice.c4.com.popularmovies.views;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import practice.c4.com.popularmovies.R;
import practice.c4.com.popularmovies.entites.Movie;
import practice.c4.com.popularmovies.infrastructure.BaseFragmentActivity;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.list_item_movie_avatar)
    ImageView movieAvatar;

    @Bind(R.id.list_item_movie_ProgressBar)
    ProgressBar movieProgressBar;


    private final BaseFragmentActivity activity;


    public MovieViewHolder(View view, BaseFragmentActivity activity) {
        super(view);
        ButterKnife.bind(this, view);
        this.activity = activity;

    }

    public void popluate(Context context, Movie movie){
        itemView.setTag(movie);

        float width = convertDptoPixel(240,context);
        float height = convertDptoPixel(260,context);

        movieProgressBar.setVisibility(View.VISIBLE);
        Log.i(MovieViewHolder.class.getSimpleName(),movie.getMovieTitle());
        Picasso.with(movieAvatar.getContext()).load(movie.getMovieImage())
                .into(movieAvatar, new Callback() {
                    @Override
                    public void onSuccess() {
                        movieProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }



    public static float convertDptoPixel(int dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi/DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }
}
