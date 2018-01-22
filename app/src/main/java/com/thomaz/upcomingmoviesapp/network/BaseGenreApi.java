package com.thomaz.upcomingmoviesapp.network;

import android.app.Activity;

import com.google.gson.annotations.SerializedName;
import com.thomaz.upcomingmoviesapp.dto.Genre;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thomaz on 01/20/2018.
 */
public class BaseGenreApi {


    public abstract static class All extends SuccessCallback<Genres> {

        public All(Activity activity, String lang) {
            super(activity);

            rest.getGenres(getToken(), lang).enqueue(this);
        }
    }


    public class Genres implements Serializable {

        @SerializedName("genres")
        private ArrayList<Genre> genres;

        public ArrayList<Genre> getGenres() {
            return genres;
        }
    }
}