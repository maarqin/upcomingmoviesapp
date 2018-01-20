package com.thomaz.upcomingmoviesapp.common;

import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by thomaz on 01/20/2018.
 */
public interface IBaseCustomRecycleViewFragment {

    RecyclerView getRecyclerView();

    TextView getTextView();

    ProgressBar getProgressBar();
}
