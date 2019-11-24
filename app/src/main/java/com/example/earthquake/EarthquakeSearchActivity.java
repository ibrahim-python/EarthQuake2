package com.example.earthquake;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EarthquakeSearchActivity extends AppCompatActivity {
    private EditText max_mag, min_mag;
    private Button searchButton;

    private String maxMag,minMag;
    private Intent sendIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake_detailed);

        max_mag = (EditText) findViewById(R.id.max_mag);
        min_mag = (EditText) findViewById(R.id.min_mag);
        searchButton = (Button) findViewById(R.id.search_button1);


        maxMag = max_mag.getText().toString();
        minMag = min_mag.getText().toString();

        sendIntent = new Intent(this, EarthquakeDisplayActivity.class);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendIntent.putExtra(EarthquakeDisplayActivity.MAXMAG,maxMag);
                sendIntent.putExtra(EarthquakeDisplayActivity.MINMAG,minMag);
                startActivity(sendIntent);
            }
        });



    }
}
