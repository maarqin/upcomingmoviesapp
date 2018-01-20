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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration;
import com.thomaz.upcomingmoviesapp.R;
import com.thomaz.upcomingmoviesapp.mvp.IMoviePresenter;
import com.thomaz.upcomingmoviesapp.mvp.IMovieView;
import com.thomaz.upcomingmoviesapp.mvp.MoviePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by thomaz on 01/20/2018.
 */
public class MainActivity extends AppCompatActivity implements
        IMovieView,
        SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.list_simple_view)
    protected RecyclerView rvResults;
    @BindView(R.id.tv_no_results)
    protected TextView tvNoResults;
    @BindView(R.id.pb_loading)
    protected ProgressBar pbLoading;

    @BindView(R.id.sr_layout)
    protected SwipeRefreshLayout srLayout;

    private IMoviePresenter moviePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvResults.setLayoutManager(mLayoutManager);
        setDecorationLine(rvResults);

        rvResults.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (moviePresenter.getIsLoading()) return;

                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {

                    //End of list
                    if (moviePresenter.getIsGetMoreItens()) {
                        moviePresenter.setIsLoading(true);

                        moviePresenter.all(moviePresenter.getPage());
                    }
                }
            }
        });

        srLayout.setOnRefreshListener(this);

        moviePresenter = new MoviePresenter(this);
        moviePresenter.all(MoviePresenter.FIRST_PAGE);
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
        }
        return super.onCreateOptionsMenu(menu);
    }
}
