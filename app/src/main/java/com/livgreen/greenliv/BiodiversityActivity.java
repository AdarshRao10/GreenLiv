package com.livgreen.greenliv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.IslamicCalendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class BiodiversityActivity extends AppCompatActivity {
    Button sequesReport;

    ArrayList<Item> itemsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodiversity);

        sequesReport=findViewById(R.id.sequesReport);

        sequesReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CalculationReport.class));
            }
        });

        try {
            JSONObject object = new JSONObject(readJSON());
            JSONArray array = object.getJSONArray("Table");
            for (int i = 0; i < array.length(); i++) {

                JSONObject jsonObject = array.getJSONObject(i);
                String no = jsonObject.getString("Sr.no.");
                String common_name = jsonObject.getString("Common name");
                String botanical_name = jsonObject.getString("Botanical name");
                String family = jsonObject.getString("Family");

                Item model = new Item(no,common_name,botanical_name,family);


                itemsArrayList.add(model);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CustomListAdapter adapter = new CustomListAdapter(this, itemsArrayList);
        ListView itemsListView  = (ListView) findViewById(R.id.list_view_items);
        itemsListView.setAdapter(adapter);


    }

    public String readJSON() {
        String json = null;
        try {
            // Opening data.json file
            InputStream inputStream = getAssets().open("treeinfo.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            // read values in the byte array
            inputStream.read(buffer);
            inputStream.close();
            // convert byte to string
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return json;
        }
        return json;
    }
}