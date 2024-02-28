package com.example.quanlichitieu.apdaptermanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlichitieu.R;
import com.example.quanlichitieu.managementdata.ServiceSpent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Adapter_bill extends BaseAdapter {
    Context context;
    ArrayList<Bill_data> arrayList;
    LayoutInflater layoutInflater;

    public Adapter_bill(Context context,ArrayList<Bill_data> arrayList){
        this.context = context;
        this.arrayList = arrayList;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.item_bill,null);
        ImageButton imageButton = convertView.findViewById(R.id.bill_images);
        TextView title = convertView.findViewById(R.id.bill_title);
        TextView supText = convertView.findViewById(R.id.bill_supText);
        TextView money = convertView.findViewById(R.id.bill_money);
        TextView time = convertView.findViewById(R.id.bill_time);
        TextView date = convertView.findViewById(R.id.bill_date);

        Bill_data billData = arrayList.get(position);
        if(billData != null){
            Context context = imageButton.getContext();
            int id = context.getResources().getIdentifier(billData.getImages(), "drawable", context.getPackageName());
            imageButton.setImageResource(id);
            title.setText(billData.getNameservice());
            supText.setText(billData.getContent());
//            money.setText(String.valueOf(billData.getPrice()));
            time.setText((billData.getDates().toString()));
            date.setText((billData.getDates().toString()));
        }
        return convertView;
    }
}
