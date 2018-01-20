package com.thomaz.upcomingmoviesapp.network;

import retrofit2.Response;

/**
 * Created by thomaz on 01/20/2018.
 */

abstract class BaseCallBack<T> {

    public abstract void onSuccess(Response<T> response);
    public abstract void onFailure(Response<T> response);
    public abstract void onFailureValidation(Response<T> response);

    String getToken() {
        return "1f54bd990f1cdfb230adb312546d765d";
    }
}
