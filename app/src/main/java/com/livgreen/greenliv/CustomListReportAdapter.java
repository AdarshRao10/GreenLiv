package com.livgreen.greenliv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomListReportAdapter extends BaseAdapter {

    private Context context; //context
    private ArrayList<ReportItem> reportItems; //data source of the list adapter

    public CustomListReportAdapter(Context context, ArrayList<ReportItem> reportItems) {
        this.context = context;
        this.reportItems = reportItems;
    }

    @Override
    public int getCount() {
        return reportItems.size();
    }

    @Override
    public Object getItem(int position) {
        return reportItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_report_layout, parent, false);
        }

        // get current item to be displayed
        ReportItem currentItem = (ReportItem) getItem(position);

        TextView treeName = (TextView)
                convertView.findViewById(R.id.tv_treeName);
        TextView seqValue = (TextView)
                convertView.findViewById(R.id.tv_sequestration);

        treeName.setText(currentItem.getName());
        seqValue.setText(currentItem.getSeqestrationVal().toString());

        // returns the view for the current row
        return convertView;
    }
}
