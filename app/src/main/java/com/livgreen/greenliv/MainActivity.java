package com.livgreen.greenliv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button cal,carbon,university,application;
    EditText treeLat,treeLong,treeName,treeTrunk,treeHeight;
    Double Trunk,Height,wa,wt,wd,wc,wco2,Sequestration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cal = findViewById(R.id.cal);
        treeLat = findViewById(R.id.treeLat);
        treeLong = findViewById(R.id.treeLong);
        treeName = findViewById(R.id.treeName);
        treeTrunk = findViewById(R.id.treeTrunk);
        treeHeight = findViewById(R.id.treeHeight);
        carbon = findViewById(R.id.carbon);
        university = findViewById(R.id.university);
        application = findViewById(R.id.application);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateform()) {

                    String Lat = treeLat.getText().toString();
                    String Long = treeLong.getText().toString();
                    String Name = treeName.getText().toString();
                    Trunk =  Double.valueOf(treeTrunk.getText().toString());  //D
                    Height = Double.valueOf(treeHeight.getText().toString()); //H

                    if(Trunk < 11)
                    {
                      wa = 0.25*(Math.pow(Trunk,2))*Height;
                    }
                    else if(Trunk > 11)
                    {
                        wa = 0.15*(Math.pow(Trunk,2))*Height;
                    }

                    wt = 1.2*wa ;
                    wd = 0.725*wt;
                    wc = 0.50*wd;
                    wco2 = 3.67*wc ;
                    Sequestration = 0.453592*wco2;

                    Toast.makeText(getApplicationContext(), " "+Sequestration, Toast.LENGTH_SHORT).show();



                    
                }
            }
        });

        carbon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Login = new Intent(getApplicationContext(), About_Sequestration.class);
                startActivity(Login);
            }
        });
        university.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Login = new Intent(getApplicationContext(), About_University.class);
                startActivity(Login);
            }
        });
        application.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Login = new Intent(getApplicationContext(), About_Application.class);
                startActivity(Login);
            }
        });
    }

    private boolean validateform() {
        String Lat = treeLat.getText().toString();
        String Long = treeLong.getText().toString();
        String Trunk = treeTrunk.getText().toString();
        String Height = treeHeight.getText().toString();
        String Name = treeName.getText().toString();

        if (Lat.isEmpty()) {
            treeLat.setError("Field cannot be empty");
            return false;
        }
        if (Long.isEmpty()) {
            treeLong.setError("Field cannot be empty");
            return false;
        }
        if (Trunk.isEmpty()) {
            treeTrunk.setError("Field cannot be empty");
            return false;
        }
        if (Height.isEmpty()) {
            treeHeight.setError("Field cannot be empty");
            return false;
        }
        if (Name.isEmpty()) {
            treeName.setError("Field cannot be empty");
            return false;
        }

        return true;
    }
}