package com.livgreen.greenliv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomTreeAdapter extends BaseAdapter {

    Context context;
    ArrayList<itemModel> arrayList;

    public CustomTreeAdapter(Context context, ArrayList<itemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public  View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView ==  null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);

        }
        TextView no,common_name,botanical_name,family;
        no = (TextView) convertView.findViewById(R.id.seqNo);
        common_name = (TextView) convertView.findViewById(R.id.commonName);
        botanical_name = (TextView) convertView.findViewById(R.id.botanicalName);
        family = (TextView) convertView.findViewById(R.id.family);
        no.setText(arrayList.get(position).getNo());
        common_name.setText(arrayList.get(position).getCommon_name());
        botanical_name.setText(arrayList.get(position).getBotanical_name());
        family.setText(arrayList.get(position).getFamily());

        return convertView;
    }
}