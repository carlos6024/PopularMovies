package practice.c4.com.popularmovies.infrastructure;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.squareup.otto.Bus;

import practice.c4.com.popularmovies.R;

public abstract class BaseFragmentActivity extends AppCompatActivity {
    protected PopularMoviesApplication application;
    protected Bus bus;

    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        application = (PopularMoviesApplication) getApplication();
        bus = application.getBus();
        bus.register(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }


}
