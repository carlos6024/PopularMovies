package practice.c4.com.popularmovies.services;

import com.squareup.otto.Bus;

import practice.c4.com.popularmovies.infrastructure.PopularMoviesApplication;

public class BaseInMemeoryService {
    protected final Bus bus;
    protected final PopularMoviesApplication application;

    public BaseInMemeoryService(PopularMoviesApplication application) {
        this.application = application;
        this.bus = application.getBus();
        bus.register(this);
    }
}
