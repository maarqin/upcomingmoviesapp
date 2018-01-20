package com.thomaz.upcomingmoviesapp.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thomaz.upcomingmoviesapp.R;
import com.thomaz.upcomingmoviesapp.common.Constants;
import com.thomaz.upcomingmoviesapp.dto.Movie;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by thomaz on 01/20/2018.
 */
public class MovieAdapter<TPI> extends BaseAdapter<TPI, MovieAdapter.ViewHolder> {

    private Activity activity;

    public MovieAdapter(@NonNull List<TPI> tpis, Activity activity) {
        super(tpis, R.layout.line_movie);
        this.activity = activity;
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        Movie movie = (Movie) tList.get(position);

        Glide.with(activity)
                .load(String.format(new Locale("pt", "br"), "%s%s",
                        Constants.BASE_URI_IMAGE, movie.getBackdropPath()))
                .into(holder.ivMoviePic);

        holder.tvMovieName.setText(movie.getTitle());
        holder.tvMovieRelease.setText(movie.getReleaseDate());
    }

    @Override
    protected MovieAdapter.ViewHolder getViewHolder(View v) {
        return new MovieAdapter.ViewHolder(v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_movie_pic)
        protected ImageView ivMoviePic;

        @BindView(R.id.tv_movie_name)
        protected TextView tvMovieName;

        @BindView(R.id.tv_movie_genre)
        protected TextView tvMovieGenre;

        @BindView(R.id.tv_movie_release)
        protected TextView tvMovieRelease;

        public ViewHolder(View v) {
            super(v);

            ButterKnife.bind(this, v);
        }
    }
}
