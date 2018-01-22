package com.thomaz.upcomingmoviesapp.mvp.movie.list;

import android.app.Activity;
import android.util.SparseArray;

import com.thomaz.upcomingmoviesapp.common.IBaseCustomRecycleView;
import com.thomaz.upcomingmoviesapp.dto.Genre;
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

    void onSearchMovie(String query);

    void resetDataList();

    void openMovie(int position);

    void setGenreMap(SparseArray<Genre> genreMap);
}
