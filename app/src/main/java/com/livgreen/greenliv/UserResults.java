package com.livgreen.greenliv;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserResults extends AppCompatActivity {

    FirebaseDatabase RootNode;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_results);

        RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
        reference = RootNode.getReference("users");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.child("gajare2").child("treeData").getChildren()) {

                           Log.e("data", String.valueOf(snapshot.child("sequestration").getValue()));
                           String sequestration = String.valueOf(snapshot.child("sequestration").getValue());
                           String TreeName = String.valueOf(snapshot.child("name").getValue());

                             Toast.makeText(getApplicationContext(), "sequestration for "+TreeName+" is "+sequestration, Toast.LENGTH_SHORT).show();









                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });


    }
}