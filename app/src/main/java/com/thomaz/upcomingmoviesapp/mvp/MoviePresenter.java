package com.thomaz.upcomingmoviesapp.mvp;

import android.app.Activity;

import com.thomaz.upcomingmoviesapp.adapter.MovieAdapter;
import com.thomaz.upcomingmoviesapp.common.IBaseCustomRecycleView;
import com.thomaz.upcomingmoviesapp.dto.Movie;
import com.thomaz.upcomingmoviesapp.network.Result;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by thomaz on 1/20/18.
 */

public class MoviePresenter implements IMoviePresenter {

    static public final int FIRST_PAGE = 1;

    private int page = FIRST_PAGE;
    private int totalPages;

    private boolean isLoading = false;
    private boolean isGetMoreItens = true;

    private IMovieView movieView;
    private IMovieModel movieModel;

    private MovieAdapter<Movie> adapter;

    public MoviePresenter(IMovieView movieView) {
        this.movieView = movieView;

        this.movieModel = new MovieModel(this);
    }

    @Override
    public void all(int page) {
        if( page == FIRST_PAGE ) {
            this.page = FIRST_PAGE;
            isGetMoreItens = true;
            isLoading = false;

            adapter = new MovieAdapter<>(new ArrayList<Movie>(), getActivity());
            movieView.getRecyclerView().setAdapter(adapter);
        }
        movieModel.all(page);
    }

    @Override
    public Activity getActivity() {
        return movieView.getActivity();
    }

    @Override
    public void successOnAll(Result<ArrayList<Movie>> listMovies) {
        totalPages = listMovies.getTotalPages();

        if( page + 1 == totalPages ) {
            isGetMoreItens = false;
        } else {
            ArrayList<Movie> list = listMovies.getResult();

            // Collections.sort(list, Collections.<Movie>reverseOrder());

            adapter.add(list);
            movieView.getSwipe().setRefreshing(false);

            page++;
            isLoading = false;
        }
    }

    @Override
    public IBaseCustomRecycleView getIBaseCustomRecycleView() {
        return movieView;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public boolean getIsLoading() {
        return isLoading;
    }

    @Override
    public boolean getIsGetMoreItens() {
        return isGetMoreItens;
    }

    @Override
    public void setIsLoading(boolean b) {
        this.isLoading = b;
    }

}
