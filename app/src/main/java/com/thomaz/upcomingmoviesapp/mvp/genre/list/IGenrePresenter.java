package com.thomaz.upcomingmoviesapp.mvp.genre.list;

import android.app.Activity;

import com.thomaz.upcomingmoviesapp.network.BaseGenreApi;

/**
 * Created by thomaz on 1/22/18.
 */

public interface IGenrePresenter {

    void all();

    Activity getActivity();

    void successOnAll(BaseGenreApi.Genres body);
}
