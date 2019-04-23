package com.example.testtaskitunesapi.Retrofit;

import com.example.testtaskitunesapi.Model.TrackModel;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIService {

    @GET("search")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Observable<TrackModel> getTracks(@Query("term") String term);
}