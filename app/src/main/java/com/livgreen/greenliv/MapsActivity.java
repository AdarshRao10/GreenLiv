package com.livgreen.greenliv;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.livgreen.greenliv.databinding.ActivityMapsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    LatLng sydney;
    String[] QueryType;
    Spinner spinner1,spinner2;
    Button Apply;
    EditText trunkSize,treeHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Apply = findViewById(R.id.Apply);
        treeHeight = findViewById(R.id.treeHeight);
        trunkSize = findViewById(R.id.trunkSize);

        // Spinner element
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);

        //get array list from string.xml
        QueryType = getResources().getStringArray(R.array.filter);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.item_drop_down, QueryType);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.item_drop_down);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapter);

        Apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                    if(validateform())
                    {
                        String treeinfo = spinner1.getSelectedItem().toString();
                        String trunkinfo = spinner2.getSelectedItem().toString();
                        String trunk = trunkSize.getText().toString();
                        String height = treeHeight.getText().toString();
                        double Apxheight = Double.parseDouble(height);
                        double Apxtrunk = Double.parseDouble(trunk);

                        if(treeinfo.equals("less than"))
                        {
                            try{
                                JSONObject object = new JSONObject(readJSON());
                                JSONArray array = object.getJSONArray("tree");
                                for (int i = 0; i < array.length(); i++) {

                                    JSONObject jsonObject = array.getJSONObject(i);
                                    String h1 = jsonObject.getString("Approximate_Height");
                                    if(!h1.isEmpty()) {
                                        try {
                                            double result = Double.parseDouble(h1);
                                            if(Apxheight > result)
                                            {
                                                String latitude = jsonObject.getString("Location:Latitude");
                                                String longitude = jsonObject.getString("Location:Longitude");
                                                if(!latitude.isEmpty() && !longitude.isEmpty())
                                                {
                                                    try{
                                                        double lat = Double.parseDouble(latitude);
                                                        double lng = Double.parseDouble(longitude);
                                                        sydney = new LatLng(lat,lng);
                                                        mMap.addCircle(new CircleOptions()
                                                                .center(sydney).radius(1).strokeColor(Color.YELLOW).fillColor(Color.parseColor("#88008E22")));
                                                    }
                                                    catch (NumberFormatException e)
                                                    {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }

                                        } catch (NumberFormatException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else if(treeinfo.equals("greater than"))
                        {
                            try{
                                JSONObject object = new JSONObject(readJSON());
                                JSONArray array = object.getJSONArray("tree");
                                for (int i = 0; i < array.length(); i++) {

                                    JSONObject jsonObject = array.getJSONObject(i);
                                    String h1 = jsonObject.getString("Approximate_Height");
                                    if(!h1.isEmpty()) {
                                        try {
                                            double result = Double.parseDouble(h1);
                                            if(Apxheight < result)
                                            {
                                                String latitude = jsonObject.getString("Location:Latitude");
                                                String longitude = jsonObject.getString("Location:Longitude");
                                                if(!latitude.isEmpty() && !longitude.isEmpty())
                                                {
                                                    try{
                                                        double lat = Double.parseDouble(latitude);
                                                        double lng = Double.parseDouble(longitude);
                                                        sydney = new LatLng(lat,lng);
                                                        mMap.addCircle(new CircleOptions()
                                                                .center(sydney).radius(1).strokeColor(Color.YELLOW).fillColor(Color.parseColor("#88008E22")));
                                                    }
                                                    catch (NumberFormatException e)
                                                    {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }

                                        } catch (NumberFormatException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else if(treeinfo.equals("equals"))
                        {
                            try{
                                JSONObject object = new JSONObject(readJSON());
                                JSONArray array = object.getJSONArray("tree");
                                for (int i = 0; i < array.length(); i++) {

                                    JSONObject jsonObject = array.getJSONObject(i);
                                    String h1 = jsonObject.getString("Approximate_Height");
                                    if(!h1.isEmpty()) {
                                        try {
                                            double result = Double.parseDouble(h1);
                                            if(Apxheight == result)
                                            {
                                                String latitude = jsonObject.getString("Location:Latitude");
                                                String longitude = jsonObject.getString("Location:Longitude");
                                                if(!latitude.isEmpty() && !longitude.isEmpty())
                                                {
                                                    try{
                                                        double lat = Double.parseDouble(latitude);
                                                        double lng = Double.parseDouble(longitude);
                                                        sydney = new LatLng(lat,lng);
                                                        mMap.addCircle(new CircleOptions()
                                                                .center(sydney).radius(1).strokeColor(Color.YELLOW).fillColor(Color.parseColor("#88008E22")));
                                                    }
                                                    catch (NumberFormatException e)
                                                    {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }

                                        } catch (NumberFormatException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else if(trunkinfo.equals("less than"))
                        {
                            try{
                                JSONObject object = new JSONObject(readJSON());
                                JSONArray array = object.getJSONArray("tree");
                                for (int i = 0; i < array.length(); i++) {

                                    JSONObject jsonObject = array.getJSONObject(i);
                                    String h1 = jsonObject.getString("Trunk_Size");
                                    if(!h1.isEmpty()) {
                                        try {
                                            double result = Double.parseDouble(h1);
                                            if(Apxtrunk > result)
                                            {
                                                String latitude = jsonObject.getString("Location:Latitude");
                                                String longitude = jsonObject.getString("Location:Longitude");
                                                if(!latitude.isEmpty() && !longitude.isEmpty())
                                                {
                                                    try{
                                                        double lat = Double.parseDouble(latitude);
                                                        double lng = Double.parseDouble(longitude);
                                                        sydney = new LatLng(lat,lng);
                                                        mMap.addCircle(new CircleOptions()
                                                                .center(sydney).radius(1).strokeColor(Color.YELLOW).fillColor(Color.parseColor("#88008E22")));
                                                    }
                                                    catch (NumberFormatException e)
                                                    {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }

                                        } catch (NumberFormatException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else if(trunkinfo.equals("greater than"))
                        {
                            try{
                                JSONObject object = new JSONObject(readJSON());
                                JSONArray array = object.getJSONArray("tree");
                                for (int i = 0; i < array.length(); i++) {

                                    JSONObject jsonObject = array.getJSONObject(i);
                                    String h1 = jsonObject.getString("Trunk_Size");
                                    if(!h1.isEmpty()) {
                                        try {
                                            double result = Double.parseDouble(h1);
                                            if(Apxtrunk < result)
                                            {
                                                String latitude = jsonObject.getString("Location:Latitude");
                                                String longitude = jsonObject.getString("Location:Longitude");
                                                if(!latitude.isEmpty() && !longitude.isEmpty())
                                                {
                                                    try{
                                                        double lat = Double.parseDouble(latitude);
                                                        double lng = Double.parseDouble(longitude);
                                                        sydney = new LatLng(lat,lng);
                                                        mMap.addCircle(new CircleOptions()
                                                                .center(sydney).radius(1).strokeColor(Color.YELLOW).fillColor(Color.parseColor("#88008E22")));
                                                    }
                                                    catch (NumberFormatException e)
                                                    {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }

                                        } catch (NumberFormatException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else if(trunkinfo.equals("equals"))
                        {
                            try{
                                JSONObject object = new JSONObject(readJSON());
                                JSONArray array = object.getJSONArray("tree");
                                for (int i = 0; i < array.length(); i++) {

                                    JSONObject jsonObject = array.getJSONObject(i);
                                    String h1 = jsonObject.getString("Trunk_Size");
                                    if(!h1.isEmpty()) {
                                        try {
                                            double result = Double.parseDouble(h1);
                                            if(Apxtrunk == result)
                                            {
                                                String latitude = jsonObject.getString("Location:Latitude");
                                                String longitude = jsonObject.getString("Location:Longitude");
                                                if(!latitude.isEmpty() && !longitude.isEmpty())
                                                {
                                                    try{
                                                        double lat = Double.parseDouble(latitude);
                                                        double lng = Double.parseDouble(longitude);
                                                        sydney = new LatLng(lat,lng);
                                                        mMap.addCircle(new CircleOptions()
                                                                .center(sydney).radius(1).strokeColor(Color.YELLOW).fillColor(Color.parseColor("#88008E22")));
                                                    }
                                                    catch (NumberFormatException e)
                                                    {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }

                                        } catch (NumberFormatException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }


            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);



        try {
            JSONObject object = new JSONObject(readJSON());
            JSONArray array = object.getJSONArray("tree");
            for (int i = 0; i < array.length(); i++) {

                JSONObject jsonObject = array.getJSONObject(i);
                String latitude = jsonObject.getString("Location:Latitude");
                String longitude = jsonObject.getString("Location:Longitude");

                if(!latitude.isEmpty() && !longitude.isEmpty())
                {
                    try{
                        double lat = Double.parseDouble(latitude);
                        double lng = Double.parseDouble(longitude);
                        sydney = new LatLng(lat,lng);
                        mMap.addCircle(new CircleOptions()
                                .center(sydney).radius(1).strokeColor(Color.GREEN).fillColor(Color.parseColor("#88008E22")));
                        // mMap.addMarker(new MarkerOptions().position(sydney));
                    }
                    catch (NumberFormatException e)
                    {
                        e.printStackTrace();
                    }
                }



            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        LatLng Mumbai = new LatLng(19.0714562,72.8589833);
        mMap.addMarker(new MarkerOptions().position(Mumbai).title("Kalina Campus"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Mumbai,16)); //19.07373218412279, 72.85840585026412

    }
    public String readJSON() {
        String json = null;
        try {
            // Opening data.json file
            InputStream inputStream = getAssets().open("data.json");
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

    private boolean validateform() {

        String trunk = trunkSize.getText().toString();
        String height = treeHeight.getText().toString();
        double Apxheight = Double.parseDouble(height);
        double Apxtrunk = Double.parseDouble(trunk);



        if (Apxtrunk < 10) {
            trunkSize.setError("trunk size must be greater than 10cm");
            return false;
        }
        if (Apxheight < 1) {
            treeHeight.setError("tree height must be greater than 0 cm");
            return false;
        }
        if(trunk.isEmpty() && height.isEmpty()){
            Toast.makeText(getApplicationContext(), "enter at least 1 field", Toast.LENGTH_SHORT).show();
            return false;
        }




        return true;
    }
}