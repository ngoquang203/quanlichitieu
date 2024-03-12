package com.example.quanlichitieu.apdaptermanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlichitieu.R;

import java.util.ArrayList;

public class Adapter_table extends BaseAdapter {
    Context context;
    ArrayList<Table_data> tableData;
    LayoutInflater layoutInflater;

    public Adapter_table(Context context, ArrayList<Table_data> tableData) {
        this.context = context;
        this.tableData = tableData;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return tableData.size();
    }
    @Override
    public Object getItem(int position) {
        return tableData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.item_table,null);
        TextView date = convertView.findViewById(R.id.itemTable_dates);
        TextView name = convertView.findViewById(R.id.itemTable_name);
        TextView money = convertView.findViewById(R.id.itemTable_money);

        Table_data data = tableData.get(position);
        if(data != null){
            date.setText(data.getDates().toString());
            name.setText(data.getNameservice());
            money.setText(String.valueOf(data.getPrice()));
        }
        return convertView;
    }
}
