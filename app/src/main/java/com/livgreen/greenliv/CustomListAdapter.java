package com.livgreen.greenliv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<Item> items; //data source of the list adapter

    //public constructor
    public CustomListAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_layout, parent, false);
        }

        // get current item to be displayed
        Item currentItem = (Item) getItem(position);

        // get the TextView for item name and item description
        TextView no = (TextView)
                convertView.findViewById(R.id.no);
        TextView name = (TextView)
                convertView.findViewById(R.id.name);
        TextView botanical = (TextView)
                convertView.findViewById(R.id.botanical);
        TextView family = (TextView)
                convertView.findViewById(R.id.family);

        //sets the text for item name and item description from the current item object
        no.setText(currentItem.getNo());
        name.setText(currentItem.getCommon_name());
        botanical.setText(currentItem.getBotanical_name());
        family.setText(currentItem.getFamily());

        // returns the view for the current row
        return convertView;
    }
}