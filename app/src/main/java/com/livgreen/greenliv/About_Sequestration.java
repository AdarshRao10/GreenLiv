package com.livgreen.greenliv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class About_Sequestration extends AppCompatActivity {
    TextView tv_abtSeq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_sequestration);
        tv_abtSeq = findViewById(R.id.tv_abtSeq);
        tv_abtSeq.setMovementMethod(new ScrollingMovementMethod());
    }
}