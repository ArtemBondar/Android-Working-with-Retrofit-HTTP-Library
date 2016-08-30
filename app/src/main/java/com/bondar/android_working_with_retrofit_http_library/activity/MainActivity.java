package com.bondar.android_working_with_retrofit_http_library.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bondar.android_working_with_retrofit_http_library.R;
import com.bondar.android_working_with_retrofit_http_library.model.Movie;
import com.bondar.android_working_with_retrofit_http_library.model.MovieResponse;
import com.bondar.android_working_with_retrofit_http_library.rest.ApiClient;
import com.bondar.android_working_with_retrofit_http_library.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String API_KEY = "4019d14f1775a3f6fe23f3b065fca63c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (API_KEY.isEmpty()) {
            Toast.makeText(this, "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
        }
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = apiInterface.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                TextView textView = (TextView)findViewById(R.id.text_view);
                textView.setText(movies.get(0).getTitle());
                Log.v(TAG, "Numbers of movies: " + movies.size());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.v(TAG, t.toString());
            }
        });
    }
}
