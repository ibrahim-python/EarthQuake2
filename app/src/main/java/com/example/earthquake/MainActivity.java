package com.example.earthquake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.earthquake.POJO.Earthquake;
import com.example.earthquake.POJO.Feature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private EathquakeAdapter adapter;
    private RecyclerView list;
    List<Feature> featureList;
    private EarthQuakeAPI earthQuakeAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        featureList =new ArrayList<>();
        list = (RecyclerView) findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        adapter = new EathquakeAdapter(featureList, this);
        list.setAdapter(adapter);

        Gson gson = new GsonBuilder().serializeNulls().create();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov/fdsnws/event/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        earthQuakeAPI = retrofit.create(EarthQuakeAPI.class);
        Call<Earthquake> call = earthQuakeAPI.getEarthQuakes("geojson","4");
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
