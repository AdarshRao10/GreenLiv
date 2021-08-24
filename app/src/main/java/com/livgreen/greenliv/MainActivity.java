package com.livgreen.greenliv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
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


    FirebaseDatabase RootNode;

    DatabaseReference reference;

    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //cal = findViewById(R.id.cal);
        //treeLat = findViewById(R.id.treeLat);
        //treeLong = findViewById(R.id.treeLong);
        treeName = findViewById(R.id.treeName);
        treeTrunk = findViewById(R.id.treeTrunk);
        treeHeight = findViewById(R.id.treeHeight);
        //carbon = findViewById(R.id.carbon);
        // university = findViewById(R.id.university);
        //application = findViewById(R.id.application);
        btn_Submit_Data = findViewById(R.id.btn_Submit_Data);


//        cal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (validateform()) {
//
//
//                    calculateCarbonSequestraton();
//
//                    Intent SendAnotherResponse = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(SendAnotherResponse);
//
//
////                    String Lat = treeLat.getText().toString();
////                    String Long = treeLong.getText().toString();
////                    String Name = treeName.getText().toString();
////                    Trunk =  Double.valueOf(treeTrunk.getText().toString());  //D
////                    Height = Double.valueOf(treeHeight.getText().toString()); //H
////
////                    if(Trunk < 11)
////                    {
////                      wa = 0.25*(Math.pow(Trunk,2))*Height;
////                    }
////                    else if(Trunk > 11)
////                    {
////                        wa = 0.15*(Math.pow(Trunk,2))*Height;
////                    }
////
////                    wt = 1.2*wa ;
////                    wd = 0.725*wt;
////                    wc = 0.50*wd;
////                    wco2 = 3.67*wc ;
////                    Sequestration = 0.453592*wco2;
////
////                    Toast.makeText(getApplicationContext(), " "+Sequestration, Toast.LENGTH_SHORT).show();
//
//
//
//
//                }
//            }
//        });

        btn_Submit_Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
                } else {
                    calculateCarbonSequestraton();
                    getLocation();

                }


               // Intent submit = new Intent(getApplicationContext(), AddAnotherResponse.class);
//                submit.putExtra("treeName", Name);
//                submit.putExtra("treeHeight", Height);
//                submit.putExtra("treeTrunk", Trunk);
//                submit.putExtra("Seq", Sequestration);
//                submit.putExtra("Latitude", Name);
//                submit.putExtra("Longitude", Height);
//                submit.putExtra("wa", wa);
//                submit.putExtra("wt", wt);
//                submit.putExtra("wd", wd);
//                submit.putExtra("wc", wc);
//                submit.putExtra("wco2", wco2);

             //   startActivity(submit);



            }
        });

//        carbon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent Login = new Intent(getApplicationContext(), About_Sequestration.class);
//                startActivity(Login);
//            }
//        });
//        university.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent Login = new Intent(getApplicationContext(), About_University.class);
//                startActivity(Login);
//            }
//        });
//        application.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent Login = new Intent(getApplicationContext(), About_Application.class);
//                startActivity(Login);
//            }
//        });
    }

    private void calculateCarbonSequestraton() {

        //    String Lat = treeLat.getText().toString();
        //   String Long = treeLong.getText().toString();
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

        SharedPreferences preferences = getSharedPreferences("userID", MODE_PRIVATE);
        String userID = preferences.getString("userID", "");
        Toast.makeText(getApplicationContext(), "userID " + userID, Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "Sequestration" + Sequestration, Toast.LENGTH_SHORT).show();

      //  RootNode = FirebaseDatabase.getInstance();

        //reference = RootNode.getReference("treeData");

      //  reference = RootNode.getReference("users");
        // read the index key
       //  Id = reference.push().getKey();
//        SharedPreferences pref=getSharedPreferences("generatedID", MODE_PRIVATE);
//        SharedPreferences.Editor editor=pref.edit();
//
//        editor.putString("generatedID",Id);
//        editor.commit();



//        TreeHelperClass treeHelper = new TreeHelperClass(Name, Id, Trunk, Height, Sequestration, wa, wt, wd, wc, wco2);
//
//        reference.child(userID).child("treeData").child(Id).setValue(treeHelper);


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

    private void getLocation() {
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5000, (LocationListener) MainActivity.this);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();

        double lat = location.getLatitude();
        double lng= location.getLongitude();
//                            latitude.setText(String.format("%s", lat));
//                            longitude.setText(String.format("%s",longitude1));

        //Toast.makeText(getApplicationContext()," "+lat+" "+longitude1, Toast.LENGTH_SHORT).show();

        SharedPreferences preferences=getSharedPreferences("userID", MODE_PRIVATE);

        String userID = preferences.getString("userID", "");

        RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
        reference = RootNode.getReference("users");
        Id = reference.push().getKey();
        TreeHelperClass treeHelper = new TreeHelperClass(Name, Id, Trunk, Height, Sequestration, wa, wt, wd, wc, wco2);

        reference.child(userID).child("treeData").child(Id).setValue(treeHelper);

        reference.child(userID).child("treeData").child(Id).setValue(lat);  //17.319401181464258, 78.40302230454013
        reference.child(userID).child("treeData").child(Id).setValue(lng);


        Intent submit = new Intent(getApplicationContext(), AddAnotherResponse.class);

        startActivity(submit);


        finish();

    }
}






