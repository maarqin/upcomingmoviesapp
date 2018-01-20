package com.thomaz.upcomingmoviesapp.network;

import com.thomaz.upcomingmoviesapp.dto.Movie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by thomaz on 01/20/2018.
 */
public interface RESTfulClient {

    String API_URL = "https://api.themoviedb.org/3/";

    @GET("movie/upcoming")
    Call<Result<ArrayList<Movie>>> getMovies(@Query("api_key") String key,
                                             @Query("language") String lang,
                                             @Query("page") int page);
//
//    @Headers({"PrivateToken: " + PRIVATE_URL})
//    @POST("user/create")
//    Call<User> createUser(@Body User user);
//
//    @Headers({"PrivateToken: " + PRIVATE_URL})
//    @POST("user/facebook-login")
//    Call<User> loginFacebook(@Body User user);
//
//    @Headers({"PrivateToken: " + PRIVATE_URL})
//    @POST("user/login")
//    Call<User> loginDefault(@Body User user);
//
//    @Headers({"PrivateToken: " + PRIVATE_URL})
//    @Multipart
//    @POST("user/upload-avatar")
//    Call<User> uploadAvatar(@Header("Authorization") String bearer, @Part MultipartBody.Part avatar);
//
//    @Headers({"PrivateToken: " + PRIVATE_URL})
//    @POST("user/reset-password-request")
//    Call<Void> resetPassword(@Body User user);
//
//    @Headers({"PrivateToken: " + PRIVATE_URL})
//    @PUT("user/update")
//    Call<User> udpateUser(@Header("Authorization") String bearer, @Body User user);
//
//    @Headers({"PrivateToken: " + PRIVATE_URL})
//    @GET("user/view")
//    Call<User> viewUser(@Header("Authorization") String bearer);
//
//    @Headers({"PrivateToken: " + PRIVATE_URL})
//    @GET("partner/list")
//    Call<ArrayList<Partner>> getPartners(@Header("Authorization") String bearer, @Query("lat") double lat,
//                                         @Query("lng") double lng, @Query("type") String type, @Query("city") String city,
//                                         @Query("name") String name, @Query("range") double range);
//
//    @Headers({"PrivateToken: " + PRIVATE_URL})
//    @GET("user/credit-out-history")
//    Call<ArrayList<Historic>> getHistorical(@Header("Authorization") String bearer);
//
//    @Headers({"PrivateToken: " + PRIVATE_URL})
//    @POST("user/add-review")
//    Call<Void> setReview(@Header("Authorization") String bearer, @Query("id") long id, @Body Review review);
//
//    @Headers({"PrivateToken: " + PRIVATE_URL})
//    @GET("admin/taps")
//    Call<ArrayList<PartnerTap>> getTapList(@Header("Authorization") String bearer);
}