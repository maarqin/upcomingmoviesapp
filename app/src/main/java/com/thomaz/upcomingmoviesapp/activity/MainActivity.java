package com.thomaz.upcomingmoviesapp.activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration;
import com.thomaz.upcomingmoviesapp.R;
import com.thomaz.upcomingmoviesapp.common.RecyclerItemClickListener;
import com.thomaz.upcomingmoviesapp.dto.Genre;
import com.thomaz.upcomingmoviesapp.mvp.genre.list.GenrePresenter;
import com.thomaz.upcomingmoviesapp.mvp.genre.list.IGenrePresenter;
import com.thomaz.upcomingmoviesapp.mvp.movie.list.IMoviePresenter;
import com.thomaz.upcomingmoviesapp.mvp.movie.list.IMovieView;
import com.thomaz.upcomingmoviesapp.mvp.movie.list.MoviePresenter;
import com.thomaz.upcomingmoviesapp.network.BaseGenreApi;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;


/**
 * Created by thomaz on 01/20/2018.
 */
public class MainActivity extends AppCompatActivity implements
        IMovieView,
        SwipeRefreshLayout.OnRefreshListener,
        SearchView.OnQueryTextListener,
        SearchView.OnCloseListener,
        MenuItem.OnActionExpandListener,
        RecyclerItemClickListener.OnItemClickListener {

    @BindView(R.id.list_simple_view)
    protected RecyclerView rvResults;
    @BindView(R.id.tv_no_results)
    protected TextView tvNoResults;
    @BindView(R.id.pb_loading)
    protected ProgressBar pbLoading;

    @BindView(R.id.sr_layout)
    protected SwipeRefreshLayout srLayout;

    private IMoviePresenter moviePresenter;
    private IGenrePresenter genrePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvResults.setLayoutManager(mLayoutManager);
        setDecorationLine(rvResults);

        // add pagination into the list
        rvResults.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (moviePresenter.getIsLoading()) return;

                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {

                    // on end of list, calls a request again
                    if (moviePresenter.getIsGetMoreItens()) {
                        moviePresenter.setIsLoading(true);

                        moviePresenter.all(moviePresenter.getPage());
                    }
                }
            }
        });

        rvResults.addOnItemTouchListener(new RecyclerItemClickListener(this, this));

        srLayout.setOnRefreshListener(this);

        moviePresenter = new MoviePresenter(this);
        genrePresenter = new GenrePresenter(this, moviePresenter);

        genrePresenter.all();
    }

    @Override
    public RecyclerView getRecyclerView() {
        return rvResults;
    }

    @Override
    public TextView getTextView() {
        return tvNoResults;
    }

    @Override
    public ProgressBar getProgressBar() {
        return pbLoading;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public SwipeRefreshLayout getSwipe() {
        return srLayout;
    }

    private void setDecorationLine(RecyclerView rv) {
        Drawable dividerDrawable = ContextCompat.getDrawable(getActivity(), R.drawable.divider_line);
        rv.addItemDecoration(new DividerItemDecoration(dividerDrawable));
    }

    @Override
    public void onRefresh() {
        moviePresenter.all(MoviePresenter.FIRST_PAGE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_searchview);

        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
            searchView.setOnQueryTextListener(this);
            searchItem.setOnActionExpandListener(this);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        moviePresenter.onSearchMovie(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        moviePresenter.resetDataList();
        return true;
    }

    @Override
    public boolean onClose() {
        return false;
    }

    @Override
    public void onItemClick(View view, int position) {
        moviePresenter.openMovie(position);
    }
}
