package com.livgreen.greenliv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements LocationListener {
    Button cal, carbon, university, application, btn_Submit_Data;
    EditText treeLat, treeLong, treeName, treeTrunk, treeHeight;
    Double Trunk, Height, wa, wt, wd, wc, wco2, Sequestration, Lat, Long;
    String Name;
    String Id;
    String uid;


    FirebaseDatabase RootNode;

    DatabaseReference reference;

    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        treeName = findViewById(R.id.treeName);
        treeTrunk = findViewById(R.id.treeTrunk);
        treeHeight = findViewById(R.id.treeHeight);
        btn_Submit_Data = findViewById(R.id.btn_Submit_Data);


        btn_Submit_Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (validateform()) {

                    calculateCarbonSequestraton();
                }


            }

            private void calculateCarbonSequestraton() {


                Name = treeName.getText().toString();
                Trunk = Double.valueOf(treeTrunk.getText().toString());  //D
                Height = Double.valueOf(treeHeight.getText().toString()); //H

                if (Trunk < 11) {
                    wa = 0.25 * (Math.pow(Trunk, 2)) * Height;
                } else if (Trunk > 11) {
                    wa = 0.15 * (Math.pow(Trunk, 2)) * Height;
                }

                wt = 1.2 * wa;
                wd = 0.725 * wt;
                wc = 0.50 * wd;
                wco2 = 3.67 * wc;
                Sequestration = 0.453592 * wco2;


                RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
                reference = RootNode.getReference("users");

                SharedPreferences preferences = getSharedPreferences("userID", MODE_PRIVATE);

                String userID = preferences.getString("userID", "");

                Id = reference.push().getKey();
                uid=Id;
//                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
//                SharedPreferences.Editor myEdit = sharedPreferences.edit();
//                myEdit.putString("id",Id);
//                myEdit.commit();


                TreeHelperClass treeHelper = new TreeHelperClass(Name, Id, Trunk, Height, Sequestration, wa, wt, wd, wc, wco2);

                reference.child(userID).child("treeData").child(Id).setValue(treeHelper);

                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
                } else {

                    getLocation();

                }




//                finish();



            }

            private boolean validateform() {

                String Trunk = treeTrunk.getText().toString();
                String Height = treeHeight.getText().toString();
                String Name = treeName.getText().toString();

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

            @SuppressLint("MissingPermission")
            private void getLocation() {
                try {
                    locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5000, (LocationListener) MainActivity.this);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }





});
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();


        Toast.makeText(getApplicationContext()," "+lat+" "+lng, Toast.LENGTH_SHORT).show();

        SharedPreferences preferences = getSharedPreferences("userID", MODE_PRIVATE);

        String userID = preferences.getString("userID", "");
//
//        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//
//
//        String uid = sh.getString("id", "");


        Log.e("id",uid);
//        SharedPreferences preferences2 = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//
//        String uid = preferences2.getString("id", "");







        reference.child(userID).child("treeData").child(uid).child("latitude").setValue(lat);  //17.319401181464258, 78.40302230454013
        reference.child(userID).child("treeData").child(uid).child("longitude").setValue(lng);

//        Intent submit = new Intent(getApplicationContext(), AddAnotherResponse.class);
//
//        startActivity(submit);
//        finish();



    }
}