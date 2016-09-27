package practice.c4.com.popularmovies.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import practice.c4.com.popularmovies.R;
import practice.c4.com.popularmovies.entites.Movie;
import practice.c4.com.popularmovies.infrastructure.BaseFragmentActivity;

public class MovieDetailFragment extends BaseFragment {

    @Bind(R.id.fragment_movie_details_Poster)
    ImageView moviePoster;

    @Bind(R.id.fragment_movie_details_popularVote)
    TextView movieRelaseDate;

    @Bind(R.id.fragment_movie_details_ratingVote)
    TextView movieRatingVote;


    @Bind(R.id.fragment_movie_details_title)
    TextView movieTitle;

    @Bind(R.id.fragment_movie_summary)
    TextView movieSummary;

    private Movie movie;
    public final static String EXTRA_MOVIE = "EXTRA_MOVIE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movie = getArguments().getParcelable(EXTRA_MOVIE);

    }

    public static MovieDetailFragment newInstance(Movie movie){
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_MOVIE, movie);
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        movieDetailFragment.setArguments(args);
        return movieDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);

        ButterKnife.bind(this, rootView);

        float width = convertDptoPixel(180,getContext());
        Picasso.with(getActivity().getApplicationContext()).load(movie.getMovieImage())
                .resize(Math.round(width),Math.round(width))
                .onlyScaleDown()
                .centerInside()
                .into(moviePoster);
        movieTitle.setText(movie.getMovieTitle());
        movieTitle.setPaintFlags(movieTitle.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        movieRelaseDate.setText("Release Date: " +movie.getMovieRelaseDate());
        movieRatingVote.setText("Rating " + Double.toString(movie.getVoteAverage()) + "/10");
        movieSummary.setText(movie.getMovieSynopsis());
        return rootView;
    }


    public static float convertDptoPixel(int dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi/DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }
}
