package com.livgreen.greenliv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SequestrationReport extends AppCompatActivity {


    FirebaseDatabase RootNode;
    DatabaseReference reference;

    String treeName;
    Double Sequestration;

    ListView reportListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequestration_report);


        reportListView = findViewById(R.id.reportListView);



        RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
        reference = RootNode.getReference("users");


        SharedPreferences preferences=getSharedPreferences("userID", MODE_PRIVATE);
        String userID = preferences.getString("userID", "");

        Toast.makeText(getApplicationContext(), "started"+userID, Toast.LENGTH_SHORT).show();

        Query checkuser = reference.orderByChild("users").equalTo(userID);

        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){


//                    treeName = snapshot.child(userID).child("treeData").child("name").getValue(String.class);
//
//                    Sequestration = snapshot.child(userID).child("treeData").child("sequestration").getValue(Double.class);
                    Toast.makeText(getApplicationContext(), "userID in seqReport"+userID, Toast.LENGTH_SHORT).show();
                    for (DataSnapshot ds : snapshot.child(userID).child("treeData").getChildren()) {

                        String name = ds.child("name").getValue(String.class);

                        Log.d("TAG", name);

                        // array.add(name);


                        Toast.makeText(getApplicationContext(), "TreeName " + treeName + " Sequestration ", Toast.LENGTH_SHORT).show();
                    }



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });












    }
}