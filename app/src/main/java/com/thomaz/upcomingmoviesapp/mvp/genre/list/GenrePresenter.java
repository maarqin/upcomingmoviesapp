package com.thomaz.upcomingmoviesapp.mvp.genre.list;

import android.app.Activity;
import android.util.SparseArray;

import com.thomaz.upcomingmoviesapp.dto.Genre;
import com.thomaz.upcomingmoviesapp.mvp.movie.list.IMoviePresenter;
import com.thomaz.upcomingmoviesapp.mvp.movie.list.MoviePresenter;
import com.thomaz.upcomingmoviesapp.network.BaseGenreApi;


/**
 * Created by thomaz on 1/22/18.
 */
public class GenrePresenter implements IGenrePresenter {

    private Activity activity;
    private IMoviePresenter moviePresenter;

    private IGenreModel genreModel;

    public GenrePresenter(Activity activity, IMoviePresenter moviePresenter) {
        this.activity = activity;
        this.moviePresenter = moviePresenter;

        genreModel = new GenreModel(this);
    }

    @Override
    public void all() {
        genreModel.all();
    }

    @Override
    public Activity getActivity() {
        return activity;
    }

    @Override
    public void successOnAll(BaseGenreApi.Genres body) {
        SparseArray<Genre> genreMap = new SparseArray<>();

        for (Genre genre : body.getGenres()) {
            genreMap.put(genre.getId(), genre);
        }

        moviePresenter.setGenreMap(genreMap);
        moviePresenter.all(MoviePresenter.FIRST_PAGE);
    }
}
