package com.thomaz.upcomingmoviesapp.network;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by thomaz on 1/20/18.
 */

public class Result<D> implements Serializable {

    @SerializedName("results")
    private D result;

    @SerializedName("total_pages")
    private int totalPages;

    public D getResult() {
        return result;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
