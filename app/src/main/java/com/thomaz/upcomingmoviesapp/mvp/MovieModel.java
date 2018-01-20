package com.thomaz.upcomingmoviesapp.mvp;

import com.thomaz.upcomingmoviesapp.dto.Movie;
import com.thomaz.upcomingmoviesapp.network.BaseMovieApi;
import com.thomaz.upcomingmoviesapp.network.Result;

import java.util.ArrayList;

import retrofit2.Response;

/**
 * Created by thomaz on 1/20/18.
 */
public class MovieModel implements IMovieModel {

    private IMoviePresenter moviePresenter;

    public MovieModel(IMoviePresenter moviePresenter) {
        this.moviePresenter = moviePresenter;
    }

    @Override
    public void all(int page) {

        new BaseMovieApi.All(
                moviePresenter.getActivity(),
                "en-US",
                page,
                moviePresenter.getIBaseCustomRecycleView()) {

            @Override
            public void onSuccess(Response<Result<ArrayList<Movie>>> response) {
                Result<ArrayList<Movie>> result = response.body();

                moviePresenter.successOnAll(result);
            }
        };
    }
}
