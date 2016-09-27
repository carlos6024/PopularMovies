package practice.c4.com.popularmovies.infrastructure;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.otto.Bus;

public class BaseActivity extends AppCompatActivity {
    protected PopularMoviesApplication application;
    protected Bus bus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (PopularMoviesApplication) getApplication();
        bus = application.getBus();
        bus.register(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }
}
