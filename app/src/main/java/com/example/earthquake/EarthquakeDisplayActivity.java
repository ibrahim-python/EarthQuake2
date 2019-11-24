package com.example.earthquake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.earthquake.POJO.Earthquake;
import com.example.earthquake.POJO.Feature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EarthquakeDisplayActivity extends AppCompatActivity {

    private static final String TAG = EarthquakeDisplayActivity.class.getSimpleName();
    private TextView textViewResult;
    private EathquakeAdapter adapter;
    private RecyclerView list;
    List<Feature> featureList;
    private EarthQuakeAPI earthQuakeAPI;
    private ProgressBar spinner;
    public static final String EXTRA_MAX ="max";
    public static final String EXTRA_MIN ="min";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake_display);
        Intent receivedIntent = getIntent();

        String maxMag = receivedIntent.getStringExtra(EXTRA_MAX);
        String minMag = receivedIntent.getStringExtra(EXTRA_MAX);

        Log.v(TAG, maxMag);
        String limit = "50";
        Map<String,String> parameters = new HashMap<>();
        parameters.put("format", "geojson");
        parameters.put("minmagnitude", minMag);
        parameters.put("maxmagnitude", maxMag);
        parameters.put("limit", limit);




        spinner = (ProgressBar)findViewById(R.id.progress_loader);
        spinner.setVisibility(View.VISIBLE);

        featureList =new ArrayList<>();
        list = (RecyclerView) findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        adapter = new EathquakeAdapter(featureList, this);
        list.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov/fdsnws/event/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        earthQuakeAPI = retrofit.create(EarthQuakeAPI.class);
        Call<Earthquake> call = earthQuakeAPI.getEarthQuakes(parameters);
        call.enqueue(new Callback<Earthquake>() {
            @Override
            public void onResponse(Call<Earthquake> call, Response<Earthquake> response) {
                if (!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Code: " + response.code(),Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                Earthquake earthquakes = response.body();

                featureList = earthquakes.getFeatures();
                spinner.setVisibility(View.GONE);
                adapter.setFeatureList(featureList);



            }

            @Override
            public void onFailure(Call<Earthquake> call, Throwable t) {
                textViewResult.setText(t.getMessage());
                Toast toast = Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
}
