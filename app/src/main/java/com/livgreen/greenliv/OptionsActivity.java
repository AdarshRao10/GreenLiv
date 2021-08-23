package com.livgreen.greenliv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OptionsActivity extends AppCompatActivity {

    Button abtApp,abtUni,mapUni,carbon,qrCodes,qrScanner,greenArea,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        abtApp=findViewById(R.id.abtApp);
        abtUni=findViewById(R.id.abtUni);
        mapUni=findViewById(R.id.mapUni);
        carbon=findViewById(R.id.carbon);
        qrCodes=findViewById(R.id.qrCodes);
        qrScanner=findViewById(R.id.qrScanner);
        greenArea=findViewById(R.id.greenArea);
        logout=findViewById(R.id.logout);


        abtApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),About_Application.class));
            }
        });

        abtUni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),About_University.class));
            }
        });

        mapUni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(), "Maps for Adars", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));
            }
        });

        carbon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),About_Sequestration.class));
            }
        });

        qrCodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(), "QR code list for Adarsh", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Qr_codes.class));

            }
        });

        qrScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Qr_scanner.class));
            }
        });

        greenArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences=getSharedPreferences("userID", MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();

                editor.remove("userID");
                editor.clear();
                editor.commit();

                Toast.makeText(getApplicationContext(), "Cleared", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });



    }
}