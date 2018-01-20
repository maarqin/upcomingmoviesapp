package com.thomaz.upcomingmoviesapp.mvp;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;

import com.thomaz.upcomingmoviesapp.common.IBaseCustomRecycleView;

/**
 * Created by thomaz on 1/20/18.
 */

public interface IMovieView extends IBaseCustomRecycleView {
    Activity getActivity();

    SwipeRefreshLayout getSwipe();
}
