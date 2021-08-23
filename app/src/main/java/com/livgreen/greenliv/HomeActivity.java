package com.livgreen.greenliv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    ImageView fortCampus,kalinaCampus,ratnaSub,thaneSub,kalyanSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fortCampus=findViewById(R.id.fortCampus);
        kalinaCampus=findViewById(R.id.kalinaCampus);
        ratnaSub=findViewById(R.id.ratnaSub);
        thaneSub=findViewById(R.id.thaneSub);
        kalyanSub=findViewById(R.id.kalynSub);


        fortCampus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "fort", Toast.LENGTH_SHORT).show();
            }
        });

        kalinaCampus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "kalina", Toast.LENGTH_SHORT).show();
            }
        });

        ratnaSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "ratnagiri", Toast.LENGTH_SHORT).show();
            }
        });

        thaneSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "thane", Toast.LENGTH_SHORT).show();
            }
        });

        kalyanSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "kalyan", Toast.LENGTH_SHORT).show();
            }
        });

    }
}