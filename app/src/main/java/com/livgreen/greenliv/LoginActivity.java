package com.livgreen.greenliv;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText txtfname;
    TextInputLayout txtPwd;
    Button btnReg ,btnLogin;

    FirebaseDatabase RootNode;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtfname = (EditText) findViewById(R.id.txtfname);
        txtPwd= (TextInputLayout) findViewById(R.id.txtPwd);
        btnReg = findViewById(R.id.btnReg);
        btnLogin = findViewById(R.id.btnLogin);








        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Register = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(Register);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateform())
                {

                    RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
                    reference = RootNode.getReference("users");




                    //get all values from edit Text
                    String fname = txtfname.getText().toString().trim() ;
                    String password = txtPwd.getEditText().getText().toString().trim();


                    Query checkuser = reference.orderByChild("userid").equalTo(fname);

                 //   Query checkuser = reference.orderByChild("fname").equalTo(fname);
                  //  Query checkuser = reference.orderByChild("id").equalTo(id);
                  //  Query pass = reference.orderByChild("password").equalTo(password);

                               checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.exists())
                                        {

                                            //if data exist remove error msg from edit text
                                            txtfname.setError(null);
                                            txtfname.setError(null);

                                            String passFromDB =snapshot.child(fname).child("password").getValue(String.class);
                                            if(passFromDB.equals(password)){
                                                Toast.makeText(getApplicationContext(), "login success", Toast.LENGTH_SHORT).show();



                                                SharedPreferences preferences=getSharedPreferences("userID", MODE_PRIVATE);
                                                SharedPreferences.Editor editor=preferences.edit();

                                                editor.putString("userID",fname);
                                                editor.commit();

                                                startActivity(new Intent(getApplicationContext(),OptionsActivity.class));



                                                finish();

                                            }
                                            else{
                                                txtPwd.setError("Wrong Password");
                                                txtPwd.requestFocus();
                                            }
                                        }
                                        else
                                        {
                                            txtfname.setError("Invalid user!");
                                            txtfname.requestFocus();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });








                }
            }
        });
    }



    private boolean validateform(){
        String fname = txtfname.getText().toString() ;
        String password = txtPwd.getEditText().getText().toString() ;

        if(fname.isEmpty()){
            txtfname.setError("Field cannot be empty");
            return false;
        }

        if(password.isEmpty()){
            txtPwd.setError("Field cannot be empty");
            return false;
        }
        else if (password.length() < 8) {
            txtPwd.setError("Password must be minimum 8 characters");
            return false;
        }

        // after all validation return true.
        return true;

    }


 }

