package com.livgreen.greenliv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddAnotherResponse extends AppCompatActivity {

    Button btn_yes,btn_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_another_response);

        btn_yes=findViewById(R.id.btn_yes);
        btn_no=findViewById(R.id.btn_no);


        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent SendAnotherResponse = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(SendAnotherResponse);
                finish();

            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent report = new Intent(getApplicationContext(), UserResults.class);
                startActivity(report);

                finish();
            }
        });
    }
}