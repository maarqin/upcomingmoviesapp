package com.thomaz.upcomingmoviesapp.network;

import com.thomaz.upcomingmoviesapp.dto.Movie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by thomaz on 01/20/2018.
 */
public interface RESTfulClient {

    String API_URL = "https://api.themoviedb.org/3/";

    @GET("movie/upcoming")
    Call<Result<ArrayList<Movie>>> getMovies(@Query("api_key") String key,
                                             @Query("language") String lang,
                                             @Query("page") int page);
    @GET("genre/movie/list")
    Call<BaseGenreApi.Genres> getGenres(@Query("api_key") String key,
                                        @Query("language") String lang);

}