package practice.c4.com.popularmovies.infrastructure;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetroFitCallbackPost<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
}
