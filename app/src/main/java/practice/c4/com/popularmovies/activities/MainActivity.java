package practice.c4.com.popularmovies.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import practice.c4.com.popularmovies.R;
import practice.c4.com.popularmovies.fragments.MovieListFragment;
import practice.c4.com.popularmovies.infrastructure.BaseFragmentActivity;

public class MainActivity extends BaseFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MovieListFragment();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movielist_fragment_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.movieList_menu_setting){
            Intent intent = new Intent(this,SettingActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
