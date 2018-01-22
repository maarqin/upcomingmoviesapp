package com.thomaz.upcomingmoviesapp.mvp.genre.list;

import com.thomaz.upcomingmoviesapp.network.BaseGenreApi;

import retrofit2.Response;

/**
 * Created by thomaz on 1/22/18.
 */

public class GenreModel implements IGenreModel {

    private IGenrePresenter genrePresenter;

    public GenreModel(IGenrePresenter genrePresenter) {
        this.genrePresenter = genrePresenter;
    }

    @Override
    public void all() {
        new BaseGenreApi.All(genrePresenter.getActivity(), "en-US") {
            @Override
            public void onSuccess(Response<BaseGenreApi.Genres> response) {
                genrePresenter.successOnAll(response.body());
            }
        };
    }
}
