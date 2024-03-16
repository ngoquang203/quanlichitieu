package com.example.quanlichitieu.apdaptermanagement;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlichitieu.R;
import com.example.quanlichitieu.managementdata.ServiceSpent;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
        TextView title = convertView.findViewById(R.id.bill_title);
        TextView money = convertView.findViewById(R.id.bill_money);
        TextView time = convertView.findViewById(R.id.bill_time);
        TextView date1 = convertView.findViewById(R.id.bill_date1);
        DecimalFormat df = new DecimalFormat("###,###,###.## VND");
        Bill_data billData = arrayList.get(position);
        Log.e("Test",billData.getNameservice());
        if(billData != null){
            if(billData.getIdDataCategory()%2==0){
                money.setText("-"+df.format(billData.getPrice()));
                money.setTextColor(Color.RED);
            }
            else{

                money.setText("+"+ df.format(billData.getPrice()));
                money.setTextColor(Color.GREEN);
            }
            title.setText(billData.getNameservice());
            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm");
            time.setText(simpleTimeFormat.format(billData.getTimes()));
            if(position!=0 && billData.getDates().equals(arrayList.get(position -1).getDates())){
                date1.setVisibility(View.GONE);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            date1.setText((simpleDateFormat.format(billData.getDates().getTime())));
        }
        return convertView;
    }
}
