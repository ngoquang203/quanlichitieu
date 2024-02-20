package com.example.quanlichitieu.apdaptermanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlichitieu.R;
import com.example.quanlichitieu.managementdata.ServiceCollect;

import java.util.List;

public class CategoryAdapterIncome extends ArrayAdapter<ServiceCollect> {
    public CategoryAdapterIncome(@NonNull Context context, int resource, @NonNull List<ServiceCollect> objects) {
        super(context, resource, objects);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected,parent,false);

        TextView title = convertView.findViewById(R.id.selected_text);

        ServiceCollect serviceapp = this.getItem(position);
        if(serviceapp != null){
            title.setText(serviceapp.getNameservice());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);

        TextView title = convertView.findViewById(R.id.item_titleCategory);
        TextView suptext = convertView.findViewById(R.id.item_suptextCategory);
        ImageView images = convertView.findViewById(R.id.item_imageCategory);

        ServiceCollect serviceapp = this.getItem(position);
        if(serviceapp != null){
            title.setText(serviceapp.getNameservice());
            suptext.setText(serviceapp.getExplain());
            Context context = images.getContext();
            int id = context.getResources().getIdentifier(serviceapp.getImages(), "drawable", context.getPackageName());
            images.setImageResource(id);
        }
        return convertView;
    }
}
