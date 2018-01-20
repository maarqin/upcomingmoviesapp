package com.thomaz.upcomingmoviesapp.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thomaz.upcomingmoviesapp.R;
import com.thomaz.upcomingmoviesapp.common.IAdapter;

import java.util.List;


/**
 * Created by thomaz on 01/20/2018.
 */
public class PartnerAdapter<TPI extends IAdapter> extends BaseAdapter<TPI, PartnerAdapter.ViewHolder> {

    private Activity activity;
    private DisplayMetrics metrics;

    public PartnerAdapter(@NonNull List<TPI> tpis, Activity activity) {
        super(tpis, /*R.layout.line_tap*/-1);
        this.activity = activity;

        metrics = activity.getResources().getDisplayMetrics();
    }

    @Override
    public void onBindViewHolder(PartnerAdapter.ViewHolder holder, int position) {

    }

    @Override
    protected PartnerAdapter.ViewHolder getViewHolder(View v) {
        return new PartnerAdapter.ViewHolder(v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivBeer;
        private TextView tvNameBeer;

        public ViewHolder(View v) {
            super(v);

        }
    }
}
