package com.example.earthquake;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EarthquakeSearchActivity extends AppCompatActivity {
    private Button searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake_detailed);

        searchButton = (Button) findViewById(R.id.search_button1);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText max_mag = (EditText) findViewById(R.id.max_mag);
                EditText min_mag = (EditText) findViewById(R.id.min_mag);
                Intent sendIntent = new Intent(getApplicationContext(), EarthquakeDisplayActivity.class);
                String maxMag = max_mag.getText().toString();
                String minMag = min_mag.getText().toString();

                sendIntent.putExtra(EarthquakeDisplayActivity.EXTRA_MAX,maxMag);
                sendIntent.putExtra(EarthquakeDisplayActivity.EXTRA_MIN,minMag);

                startActivity(sendIntent);
            }
        });



    }
}
