package com.thomaz.upcomingmoviesapp.network;

import android.app.Activity;

import com.thomaz.upcomingmoviesapp.dto.Movie;

import java.util.ArrayList;

/**
 * Created by thomaz on 01/20/2018.
 */
public class BaseMovieApi {


    public abstract static class All extends SuccessCallback<Result<ArrayList<Movie>>> {

        public All(Activity activity, String lang, int page) {
            super(activity);

            rest.getMovies(getToken(), lang, page).enqueue(this);
        }
    }

}