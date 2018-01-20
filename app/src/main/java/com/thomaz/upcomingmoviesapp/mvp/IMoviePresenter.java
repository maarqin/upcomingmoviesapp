package com.thomaz.upcomingmoviesapp.mvp;

import android.app.Activity;

import com.thomaz.upcomingmoviesapp.common.IBaseCustomRecycleView;
import com.thomaz.upcomingmoviesapp.dto.Movie;
import com.thomaz.upcomingmoviesapp.network.Result;

import java.util.ArrayList;

/**
 * Created by thomaz on 1/20/18.
 */

public interface IMoviePresenter {
    void all(int page);

    Activity getActivity();

    void successOnAll(Result<ArrayList<Movie>> listMovies);

    IBaseCustomRecycleView getIBaseCustomRecycleView();

    int getPage();

    boolean getIsLoading();

    boolean getIsGetMoreItens();

    void setIsLoading(boolean b);
}
