package com.example.quanlichitieu.apdaptermanagement;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlichitieu.R;
import com.example.quanlichitieu.managementdata.Logins;

import java.util.ArrayList;
import java.util.List;

public class AdapterDataInvoice extends BaseAdapter {
    private List<Logins> items;
    private Activity activity;
    public  AdapterDataInvoice(Activity activity, List<Logins> items){
        this.activity = activity;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        convertView = layoutInflater.inflate(R.layout.activity_home,null);

//        TextView tvName1 = (TextView) convertView.findViewById(R.id.textView1);
//        TextView tvName2 = (TextView) convertView.findViewById(R.id.textView2);

//        tvName1.setText(items.get(position).getID());
//        tvName2.setText(items.get(position).getPasswords());
        return convertView;
    }
}
