package com.livgreen.greenliv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Treeinfo extends AppCompatActivity {

    ListView TreelistView;
    ArrayList<itemModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TreelistView = findViewById(R.id.TreelistView);
        arrayList = new ArrayList<>();



        try {
            JSONObject object = new JSONObject(readJSON());
            JSONArray array = object.getJSONArray("Table");
            for (int i = 0; i < array.length(); i++) {

                JSONObject jsonObject = array.getJSONObject(i);
                String no = jsonObject.getString("Sr.no.");
                String common_name = jsonObject.getString("Common name");
                String botanical_name = jsonObject.getString("Botanical name");
                String family = jsonObject.getString("Family");

                itemModel model = new itemModel();
                model.setNo(no);
                model.setCommon_name(common_name);
                model.setBotanical_name(botanical_name);
                model.setFamily(family);

                arrayList.add(model);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CustomTreeAdapter adapter = new CustomTreeAdapter(this, arrayList);
        TreelistView.setAdapter(adapter);
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