package com.example.earthquake;

import com.example.earthquake.POJO.Earthquake;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

interface EarthQuakeAPI {
    @GET("/fdsnws/event/1/query?")
    Call<Earthquake> getEarthQuakes(
            @Query("format") String format,
            @Query("minmagnitude") String minMagnitude,
            @Query("maxmagnitude") String maxMagnitude);

    @GET("/fdsnws/event/1/query?")
    Call<Earthquake> getEarthQuakes(@QueryMap Map<String,String> parameters);
}
