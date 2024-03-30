package com.example.quanlichitieu.apdaptermanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.quanlichitieu.PlanMoney;
import com.example.quanlichitieu.R;
import com.example.quanlichitieu.managementdata.PlanMonney;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Adapter_PlanMoney extends BaseAdapter {
    Context context;
    ArrayList<PlanMonney> arrayList;
    LayoutInflater layoutInflater;

    public Adapter_PlanMoney(Context context,ArrayList<PlanMonney> arrayList){
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
        convertView = layoutInflater.inflate(R.layout.item_planmoney,null);
        TextView textNamePlan = convertView.findViewById(R.id.item_planmoney_idselec);
        TextView textMoneyPlan = convertView.findViewById(R.id.item_planmoney_moneyplan);
        TextView textDateStart = convertView.findViewById(R.id.item_planmoney_datestart);
        TextView textDateEnd = convertView.findViewById(R.id.item_planmoney_dateend);
        TextView textMoneyNow = convertView.findViewById(R.id.item_planmoney_moneynow);
        ProgressBar progressBar = convertView.findViewById(R.id.item_planmoney_progressBar);
        TextView supProgressBar = convertView.findViewById(R.id.item_planmoney_supProgressbar);

        PlanMonney planMoney = arrayList.get(position);
        if(planMoney != null){
            DecimalFormat df = new DecimalFormat("###,###,###.## VND");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            if(planMoney.getIDplan()%2 == 0){
                textNamePlan.setText("Chi");
                textNamePlan.setTextColor(convertView.getResources().getColor(R.color.red100));
            }
            else{
                textNamePlan.setText("Thu");
                textNamePlan.setTextColor(convertView.getResources().getColor(R.color.green100));
            }
            textMoneyPlan.setText(df.format(planMoney.getSummoney()));
            textDateStart.setText(simpleDateFormat.format(planMoney.getDateStart()));
            textDateEnd.setText(simpleDateFormat.format(planMoney.getDateEnd()));
            textMoneyNow.setText(df.format(planMoney.getMoneyNow()));
            int value = (int) (Float.valueOf(planMoney.getMoneyNow()) / Float.valueOf(planMoney.getSummoney()) * 100);
            progressBar.setProgress(value);
            supProgressBar.setText(value + "%");
        }
        return convertView;
    }
}
