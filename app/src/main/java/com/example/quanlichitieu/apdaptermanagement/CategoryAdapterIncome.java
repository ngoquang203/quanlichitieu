package com.example.quanlichitieu.apdaptermanagement;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlichitieu.R;
import com.example.quanlichitieu.managementdata.ServiceCollect;
import com.google.android.material.textfield.TextInputEditText;

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

            if(serviceapp.getIDservicecollect().equals("IC04")){
                Dialog dialog = new Dialog(getContext());
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.layout_dialog_insertsevice);
                TextInputEditText textInputEditText = (TextInputEditText) dialog.findViewById(R.id.dialog_service);
                Button button = (Button) dialog.findViewById(R.id.dialog_buttonAddService);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        title.setText(textInputEditText.getText());
                        serviceapp.setNameservice(textInputEditText.getText().toString());
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }else{
                title.setText(serviceapp.getNameservice());
            }

        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);

        TextView title = convertView.findViewById(R.id.item_titleCategory);
        TextView suptext = convertView.findViewById(R.id.item_suptextCategory);

        ServiceCollect serviceapp = this.getItem(position);
        if(serviceapp != null){
            title.setText(serviceapp.getNameservice());
            suptext.setText(serviceapp.getExplain());
        }
        return convertView;
    }
}
