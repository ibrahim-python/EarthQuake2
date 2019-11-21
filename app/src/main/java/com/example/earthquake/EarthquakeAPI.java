package com.example.earthquake;

import com.example.earthquake.POJO.Earthquake;
import com.example.earthquake.POJO.Feature;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface EarthQuakeAPI {
    @GET("/fdsnws/event/1/query?")
    Call<Earthquake> getEarthQuakes(
            @Query("format") String format,
            @Query("minmagnitude") String minMagnitude);



}
