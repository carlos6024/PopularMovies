package practice.c4.com.popularmovies.services;


import com.squareup.otto.Bus;

import practice.c4.com.popularmovies.infrastructure.PopularMoviesApplication;

public class BaseLiveService {
    protected final Bus bus;
    protected final PopularMoviesApplication application;
    protected final MovieWebService api;

    public BaseLiveService(PopularMoviesApplication application,MovieWebService api) {
        this.application = application;
        this.bus = application.getBus();
        this.api = api;
        bus.register(this);
    }


}
