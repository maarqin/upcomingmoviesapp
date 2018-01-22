package com.thomaz.upcomingmoviesapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thomaz.upcomingmoviesapp.R;
import com.thomaz.upcomingmoviesapp.common.Constants;
import com.thomaz.upcomingmoviesapp.dto.Movie;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thomaz on 1/21/18.
 */
public class DetailMovieActivity extends AppCompatActivity {

    public static final String MOVIE = "MOVIE";

    @BindView(R.id.iv_movie_pic)
    protected ImageView ivMoviePic;

    @BindView(R.id.tv_movie_name)
    protected TextView tvName;

    @BindView(R.id.tv_movie_genre)
    protected TextView tvGenre;

    @BindView(R.id.tv_movie_overview)
    protected TextView tvOverview;

    @BindView(R.id.tv_movie_release)
    protected TextView tvRelease;

    private Movie movie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ButterKnife.bind(this);

        movie = (Movie) getIntent().getExtras().getSerializable(MOVIE);

        setTitle(movie.getTitle());
    }

    @Override
    protected void onResume() {
        super.onResume();

        if( movie.getBackdropPath() != null ) {
            Glide.with(this)
                    .load(String.format(new Locale("pt", "br"), "%s%s",
                            Constants.BASE_URI_IMAGE, movie.getBackdropPath()))
                    .into(ivMoviePic);
        }

        tvName.setText(movie.getTitle());
        tvGenre.setText(movie.getGenres());
        tvOverview.setText(movie.getOverview());
        tvRelease.setText(movie.getReleaseDate());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
