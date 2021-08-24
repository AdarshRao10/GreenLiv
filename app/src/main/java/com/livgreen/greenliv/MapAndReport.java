package com.livgreen.greenliv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MapAndReport extends AppCompatActivity {

    Button mapUni, sequesReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_and_report);

        mapUni=findViewById(R.id.mapUni);
        sequesReport=findViewById(R.id.sequesReport);

        mapUni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(), "Maps for Adars", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));
            }
        });


        sequesReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(),BiodiversityActivity.class));
            }
        });
    }
}