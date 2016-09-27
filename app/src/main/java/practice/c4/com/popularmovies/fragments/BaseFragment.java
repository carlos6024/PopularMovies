package practice.c4.com.popularmovies.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.squareup.otto.Bus;

import practice.c4.com.popularmovies.infrastructure.PopularMoviesApplication;

public abstract class BaseFragment extends Fragment {
    protected PopularMoviesApplication application;
    protected Bus bus;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (PopularMoviesApplication) getActivity().getApplication();

        bus = application.getBus();
        bus.register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bus.unregister(this);
    }

}
