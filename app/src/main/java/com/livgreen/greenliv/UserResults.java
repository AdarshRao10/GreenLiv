package com.livgreen.greenliv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserResults extends AppCompatActivity {

    ArrayList<ReportItem> reportItemsArrayList = new ArrayList<>();


    Double TotalSequestrationValue = 0.0;
    FirebaseDatabase RootNode;

    DatabaseReference reference;
    TextView totalSeq;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_results);

        reportItemsArrayList.clear();



        SharedPreferences preferences = getSharedPreferences("userID", MODE_PRIVATE);

        String userID = preferences.getString("userID", "");

        RootNode = FirebaseDatabase.getInstance();//gets all the elements in db from that select 1 element from tree struc
        reference = RootNode.getReference("users");
        totalSeq = findViewById(R.id.totalSeq);
      //  ListView reportListView  = (ListView) findViewById(R.id.reportListView);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.child(userID).child("treeData").getChildren()) {

                           Log.e("data", String.valueOf(snapshot.child("sequestration").getValue()));
                           String sequestration = String.valueOf(snapshot.child("sequestration").getValue());
                           double seq = Double.parseDouble(sequestration);

                           TotalSequestrationValue = TotalSequestrationValue + Double.parseDouble(sequestration);

                           String TreeName = String.valueOf(snapshot.child("name").getValue());

                           ReportItem reportItem = new ReportItem(TreeName,String.format("%.2f",seq));
                           reportItemsArrayList.add(reportItem);
                          // Toast.makeText(getApplicationContext(), "sequestration for "+TreeName+" is "+sequestration, Toast.LENGTH_SHORT).show();


                        }

                        CustomListReportAdapter adapter = new CustomListReportAdapter(UserResults.this,reportItemsArrayList);
                        ListView reportListView  = (ListView) findViewById(R.id.reportListView);
                        reportListView.setAdapter(adapter);



                        //Toast.makeText(getApplicationContext(), "Total Sequestration "+TotalSequestrationValue, Toast.LENGTH_LONG).show();
                        Log.e("TotalSeq", TotalSequestrationValue.toString());


                        totalSeq.setText(String.format("%.2f",TotalSequestrationValue));


//                        for(ReportItem item:reportItemsArrayList){
//                            Log.e("name2",item.getName());
//                            Log.e("name2",item.getSeqestrationVal());
//                        }



                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });




      // Toast.makeText(getApplicationContext(), "Total Sequestration "+TotalSequestrationValue, Toast.LENGTH_LONG).show();









       // Log.e("ArrayList",reportItemsArrayList.)

//    ListView reportListView  = (ListView) findViewById(R.id.reportListView);
//        CustomListReportAdapter adapter = new CustomListReportAdapter(this,reportItemsArrayList);
//       //ListView reportListView  = (ListView) findViewById(R.id.reportListView);
//       reportListView.setAdapter(adapter);





    }
}