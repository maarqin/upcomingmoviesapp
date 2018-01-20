package com.thomaz.upcomingmoviesapp.network;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.thomaz.upcomingmoviesapp.R;
import com.thomaz.upcomingmoviesapp.common.IBaseCustomRecycleView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by thomaz on 01/20/2018.
 */
abstract class SuccessCallback<T> extends BaseCallBack<T> implements Callback<T> {

    private Activity activity;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RESTfulClient.API_URL)
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .create()))
            .client(new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build())
            .build();

    RESTfulClient rest = retrofit.create(RESTfulClient.class);

    private IBaseCustomRecycleView iBaseCustomRecycleView;

    public SuccessCallback(Activity activity, IBaseCustomRecycleView iBaseCustomRecycleView) {
        this(activity);

        this.iBaseCustomRecycleView = iBaseCustomRecycleView;

        iBaseCustomRecycleView.getProgressBar().setVisibility(View.VISIBLE);
        iBaseCustomRecycleView.getTextView().setVisibility(View.GONE);
    }

    public SuccessCallback(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        switch ( response.code() ) {
            case HttpURLConnection.HTTP_OK :
            case HttpURLConnection.HTTP_ACCEPTED :
            case HttpURLConnection.HTTP_NOT_AUTHORITATIVE :
            case HttpURLConnection.HTTP_NO_CONTENT :
            case HttpURLConnection.HTTP_RESET :
            case HttpURLConnection.HTTP_PARTIAL :
                onSuccess(response);

                if( iBaseCustomRecycleView != null ) {
                    Result<ArrayList<T>> result = (Result) response.body();

                    ctrViewsOnSuccess(!result.getResult().isEmpty());
                }
                break;
            case 422 :
                onFailureValidation(response);
                break;
            case HttpURLConnection.HTTP_NOT_FOUND :
            case HttpURLConnection.HTTP_BAD_METHOD :
            case HttpURLConnection.HTTP_INTERNAL_ERROR :
            case HttpURLConnection.HTTP_NOT_IMPLEMENTED :
            case HttpURLConnection.HTTP_BAD_GATEWAY :
            case HttpURLConnection.HTTP_UNAVAILABLE :
            case HttpURLConnection.HTTP_GATEWAY_TIMEOUT :
            case HttpURLConnection.HTTP_VERSION :
                onFailure(response);
                break;
            case HttpURLConnection.HTTP_UNAUTHORIZED :
                //

                break;
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t){
        if( t instanceof UnknownHostException) {
            Toast.makeText(activity, R.string.no_internet, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailure(Response<T> response) {
        try {
            String e = response.errorBody().string();

            System.out.println("e = " + e);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailureValidation(Response<T> response) {
        try {
            JSONObject object = new JSONObject(response.errorBody().string());
            JSONArray errors = object.getJSONArray("errors");

            for (int i = 0; i < errors.length(); i++) {
                Toast.makeText(activity, String.valueOf(errors.get(i)), Toast.LENGTH_SHORT).show();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param moreThanOne boolean
     */
    private void ctrViewsOnSuccess(boolean moreThanOne) {

        iBaseCustomRecycleView.getProgressBar().setVisibility(View.GONE);

        if( moreThanOne ) {
            iBaseCustomRecycleView.getTextView().setVisibility(View.GONE);
            iBaseCustomRecycleView.getRecyclerView().setVisibility(View.VISIBLE);
        } else {
            iBaseCustomRecycleView.getTextView().setVisibility(View.VISIBLE);
            iBaseCustomRecycleView.getRecyclerView().setVisibility(View.GONE);
        }
    }

}