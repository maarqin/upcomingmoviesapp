package com.thomaz.upcomingmoviesapp.mvp.movie.list;

import android.app.Activity;
import android.content.Intent;
import android.util.SparseArray;

import com.thomaz.upcomingmoviesapp.activity.DetailMovieActivity;
import com.thomaz.upcomingmoviesapp.adapter.MovieAdapter;
import com.thomaz.upcomingmoviesapp.common.IBaseCustomRecycleView;
import com.thomaz.upcomingmoviesapp.dto.Genre;
import com.thomaz.upcomingmoviesapp.dto.Movie;
import com.thomaz.upcomingmoviesapp.network.Result;

import java.util.ArrayList;
import java.util.List;

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
    private List<Movie> tList;
    private List<Movie> moviesFiltered;
    private SparseArray<Genre> genreMap;

    public MoviePresenter(IMovieView movieView) {
        this.movieView = movieView;

        this.movieModel = new MovieModel(this);
    }

    @Override
    public void all(int page) {
        // when first page, reset objects
        if( page == FIRST_PAGE ) {
            this.page = FIRST_PAGE;
            isGetMoreItens = true;
            isLoading = false;

            tList = new ArrayList<>();
            moviesFiltered = new ArrayList<>();

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

        // bug on webservice
        if( page + 1 >= totalPages ) {
            isGetMoreItens = false;
        } else {
            ArrayList<Movie> list = listMovies.getResult();

            for (Movie movie : list) {

                if (movie.getGenreIds().length > 0) {
                    StringBuilder genres = new StringBuilder();
                    for (Integer i : movie.getGenreIds()) {
                        Genre genre = genreMap.get(i);
                        genres.append(genre.getName()).append(", ");
                    }
                    genres.setLength(genres.length() - 2);

                    movie.setGenres(genres.toString());
                }
            }

            tList.addAll(list);

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

    @Override
    public void onSearchMovie(String query) {
        isGetMoreItens = false;
        isLoading = false;

        moviesFiltered = new ArrayList<>();

        // filter list
        for (Movie movie : tList) {
            if( movie.getTitle().toLowerCase().contains(query.toLowerCase()) ) {
                moviesFiltered.add(movie);
            }
        }

        adapter = new MovieAdapter<>(moviesFiltered, getActivity());
        movieView.getRecyclerView().setAdapter(adapter);
    }

    @Override
    public void resetDataList() {
        adapter = new MovieAdapter<>(tList, getActivity());
        movieView.getRecyclerView().setAdapter(adapter);

        isGetMoreItens = true;
        isLoading = false;
    }

    @Override
    public void openMovie(int position) {

        Movie movie = tList.get(position);
        if( !moviesFiltered.isEmpty() ) {
            movie = moviesFiltered.get(position);
        }

        Intent intent = new Intent(getActivity(), DetailMovieActivity.class);
        intent.putExtra(DetailMovieActivity.MOVIE, movie);

        getActivity().startActivity(intent);
    }

    @Override
    public void setGenreMap(SparseArray<Genre> genreMap) {
        this.genreMap = genreMap;
    }

}
