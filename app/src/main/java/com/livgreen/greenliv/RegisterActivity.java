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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity implements LocationListener {

    EditText txtfname, txtlname, txtEmail, txtAge, txtGender, txtQualify, txtprof, txtPurpose, et_day, et_month, et_year, txtUserId, txtUniName, txtInsti,txtPincode;
//    TextInputLayout txtPwd;
    Button btnReg, btnLogin;
    String[] GenderType;
    Spinner spinner;

    FirebaseDatabase RootNode;
    DatabaseReference reference;

    LocationManager locationManager;


    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtfname = findViewById(R.id.txtfname);
        txtlname = findViewById(R.id.txtlname);
        txtEmail = findViewById(R.id.txtEmail);
        txtAge = findViewById(R.id.txtAge);
        // txtGender=findViewById(R.id.txtGender);
        txtQualify = findViewById(R.id.txtQualify);
        txtprof = findViewById(R.id.txtprof);
        txtPurpose = findViewById(R.id.txtPurpose);
//        txtPwd = findViewById(R.id.txtPwd);
        btnReg = findViewById(R.id.btnReg);
        et_day = findViewById(R.id.et_day);
        et_month = findViewById(R.id.et_month);
        et_year = findViewById(R.id.et_year);
        btnLogin = findViewById(R.id.btnLogin);
        txtUserId = findViewById(R.id.txtUserId);
        txtUniName=findViewById(R.id.txtUniName);
        txtInsti=findViewById(R.id.txtInsti);
        txtPincode=findViewById(R.id.txtPincode);

        // Spinner element
        spinner = findViewById(R.id.spinner);

        //get array list from string.xml
        GenderType = getResources().getStringArray(R.array.gender);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.item_drop_down, GenderType);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.item_drop_down);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (validateform()) {
                    String userid = txtUserId.getText().toString();

                    RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
                    reference = RootNode.getReference("users");

                    SharedPreferences preferences=getSharedPreferences("userID", MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();

                    editor.putString("userID",userid);
                    editor.commit();



                    Query checkuser = reference.orderByChild("userid").equalTo(userid);
                    checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists())
                            {
                                txtUserId.setError("choose unique userid");
                            }else{
                                //get all values from edit Text
                                String fname = txtfname.getText().toString();
                                String lname = txtlname.getText().toString();
                                String email = txtEmail.getText().toString();
                                String age = txtAge.getText().toString();
                                String gender = spinner.getSelectedItem().toString();
                                String qualification = txtQualify.getText().toString();
                                String profession = txtprof.getText().toString();
                                String purpose = txtPurpose.getText().toString();
                                String day = et_day.getText().toString();
                                String month = et_month.getText().toString();
                                String year = et_year.getText().toString();
                                String password = year + month + day;
                                String university=txtUniName.getText().toString();
                                String institute=txtInsti.getText().toString();
                                String pincode=txtPincode.getText().toString();
//                    String userid = txtUserId.getText().toString();
                                //String password = txtPwd.getEditText().getText().toString() ;




                                UserHelperClass helperClass = new UserHelperClass(fname,lname,email,age,gender,qualification,profession,purpose,password,userid,university,institute,pincode);

                                reference.child(userid).setValue(helperClass);

                                if(ContextCompat.checkSelfPermission(RegisterActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED &&
                                        ContextCompat.checkSelfPermission(RegisterActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                                    ActivityCompat.requestPermissions(RegisterActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},0);
                                }else {
                                    getLocation();
                                }
                                // reference.child(fname).setValue(helperClass);
                                // reference.push().setValue(helperClass);




                            }

                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(Login);
                finish();
            }
        });


    }



    @SuppressLint("MissingPermission")
    private void getLocation()
    {
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,5000,5000,RegisterActivity.this);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean validateform() {
        String fname = txtfname.getText().toString();
        String lname = txtlname.getText().toString();
        String email = txtEmail.getText().toString();
        String age = txtAge.getText().toString();
        String gender = spinner.getSelectedItem().toString();
        String qualification = txtQualify.getText().toString();
        String profession = txtprof.getText().toString();
        String purpose = txtPurpose.getText().toString();
        String day = et_day.getText().toString();
        String month = et_month.getText().toString();
        String year = et_year.getText().toString();
        String password = day + month + year;
        String userid = txtUserId.getText().toString();
        // String password = txtPwd.getEditText().getText().toString() ;

        if (fname.isEmpty()) {
            txtfname.setError("Field cannot be empty");
            return false;
        }
        if (lname.isEmpty()) {
            txtlname.setError("Field cannot be empty");
            return false;
        }
        if (email.isEmpty()) {
            txtEmail.setError("Field cannot be empty");
            return false;
        }

        if (!email.trim().matches(emailPattern)) {
            //Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
            txtEmail.setError("Invalid Email Address");
        }

        if (age.isEmpty()) {
            txtAge.setError("Field cannot be empty");
            return false;
        }
        if (gender.isEmpty()) {
            txtGender.setError("Field cannot be empty");
            return false;
        }
        if (qualification.isEmpty()) {
            txtQualify.setError("Field cannot be empty");
            return false;
        }
        if (profession.isEmpty()) {
            txtprof.setError("Field cannot be empty");
            return false;
        }
        if (purpose.isEmpty()) {
            txtPurpose.setError("Field cannot be empty");
            return false;
        }

        if (year.isEmpty()) {
            et_year.setError("Year cannot be empty");
        } else if (year.length() != 4) {
            et_year.setError("Enter correct year");
        }

        if (month.isEmpty()) {
            et_year.setError("Year cannot be empty");
        } else if (Integer.parseInt(month) > 12 && Integer.parseInt(month) < 0) {
            et_year.setError("Enter correct month");
        }

        if (day.isEmpty()) {
            et_year.setError("day cannot be empty");
        } else if (Integer.parseInt(day) > 31 && Integer.parseInt(day) < 0) {
            et_year.setError("Enter correct day");
        }
        if (userid.isEmpty()) {
            txtUserId.setError("User id field empty!");
            return false;
        }

        return true;
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
      //  Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();

        double lat = location.getLatitude();
        double lng= location.getLongitude();
//                            latitude.setText(String.format("%s", lat));
//                            longitude.setText(String.format("%s",longitude1));

        //Toast.makeText(getApplicationContext()," "+lat+" "+longitude1, Toast.LENGTH_SHORT).show();

        SharedPreferences preferences=getSharedPreferences("userID", MODE_PRIVATE);
        String userID = preferences.getString("userID", "");
        RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
        reference = RootNode.getReference("users");

        reference.child(userID).child("latitude").setValue(lat);  //17.319401181464258, 78.40302230454013
        reference.child(userID).child("longitude").setValue(lng);

        Toast.makeText(getApplicationContext(),"Registration successfull!!",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();

    }
}