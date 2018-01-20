package com.thomaz.upcomingmoviesapp.activity;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.thomaz.upcomingmoviesapp.R;
import com.thomaz.upcomingmoviesapp.dto.Movie;
import com.thomaz.upcomingmoviesapp.network.BaseMovieApi;
import com.thomaz.upcomingmoviesapp.network.Result;

import java.util.ArrayList;

import retrofit2.Response;

/**
 * Created by thomaz on 01/20/2018.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new BaseMovieApi.All(this, "en-US", 1) {
            @Override
            public void onSuccess(Response<Result<ArrayList<Movie>>> response) {
                Result<ArrayList<Movie>> result = response.body();
                ArrayList<Movie> listMovies = result.getResult();

                String a = "";

            }
        };
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_searchview);

        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);
    }
}
