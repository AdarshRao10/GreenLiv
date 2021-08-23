package com.livgreen.greenliv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Field;

public class Qr_codes extends AppCompatActivity {
    ListView listView;
    int[] resArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_codes);

        Field[] ID_Fields = R.raw.class.getFields();
        resArray = new int[ID_Fields.length];
        for(int i = 0; i < ID_Fields.length; i++) {
            try {
                resArray[i] = ID_Fields[i].getInt(null);
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i < resArray.length; i++)
            Log.e("array",""+resArray[i]);

        //finding listview
        listView = findViewById(R.id.listview);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return resArray.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data,null);

            String res="";
            //getting view in row_data
            TextView name = view1.findViewById(R.id.treeName);
            ImageView image = view1.findViewById(R.id.treeQR);


//            Log.e("imgDesc",""+imgname);

            image.setImageResource(resArray[i]);
//            int id=getResources().getIdentifier("image_name","raw",getPackageName());
//            Log.e("imgID",""+id);
            String imgname = getResources().getResourceName(resArray[i]);
            String treeName=imgname.substring(25);


            if(treeName.contains("_")) {
                res = treeName.replace("_", " ");
            }else{
                res=treeName;
            }
            Log.e("imgDesc",res);
            name.setText(res);
            return view1;
        }
    }
}