package com.example.earthquake.POJO;

import com.google.gson.annotations.SerializedName;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Earthquake {

    @SerializedName("features")
    private List<Feature> features;

    public List<Feature> getFeatures() {
        return features;
    }


    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
}