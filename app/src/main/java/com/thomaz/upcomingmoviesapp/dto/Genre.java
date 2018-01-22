package com.thomaz.upcomingmoviesapp.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by thomaz on 1/22/18.
 */

public class Genre implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
