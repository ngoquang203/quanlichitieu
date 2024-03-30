package com.example.quanlichitieu.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlichitieu.R;
import com.example.quanlichitieu.apdaptermanagement.Adapter_table;
import com.example.quanlichitieu.apdaptermanagement.Table_data;
import com.example.quanlichitieu.databinding.LayoutDialogSortBinding;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.Array;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BudgetFragment extends Fragment {
    private View view;
    private TextView buttonIncome,buttonExpnece;
    private TextView selectedDate,sumMoneyText,textErron;
    private String dateStartInit,dateEndInit;
    private SharedPreferences sharedPreferences;
    private int IDcollect,IDspent;

    private boolean checkButton;
    private  Adapter_table adapterTable;
    private ArrayList<Table_data> tableData;
    private boolean clickButtonDialog;
    private PieChart pieChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_budget, container, false);
        Init();



        setDateInit();
        String date1 = changDate(dateStartInit);
        String date2 = changDate(dateEndInit);

        try {
            tableData = Table_data.getuserListCollect(IDcollect,date1,date2);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        adapterTable = new Adapter_table(getContext(),tableData);
//
//        listView.setAdapter(adapterTable);
        setTextViewErron();
        SumMoney();
        clickButton();
        clickSelectedDate();
        setInitDataPieChart();
        getDataOfCollect();
        return view;
    }
    private void getDataOfCollect(){
        List<PieEntry> entries = new ArrayList<>();
        long[] dataCollect = new long[5];
        String[] NameCollect = {"","Lương tháng","Tiền thưởng","Tiền lãi ngân hàng","Khác"};
        Arrays.fill(dataCollect,0);
        for(int i = 0;i<tableData.size();++i){
            String str = tableData.get(i).getIDservice().toString();
            dataCollect[Integer.valueOf(str.charAt(str.length() - 1)) -48] += tableData.get(i).getPrice();
            Log.e("dataCollect[" + String.valueOf(Integer.valueOf(str.charAt(str.length() - 1))-48) + "]",String.valueOf(tableData.get(i).getPrice()) );
        }
        for(int i = 0;i < dataCollect.length;++i){
            if(dataCollect[i] != 0){
                entries.add(new PieEntry(dataCollect[i],NameCollect[i]));
            }
        }
        PieDataSet pieDataSet = new PieDataSet(entries, "Pie Chart");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextSize(20);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }
    private void getDataOfSpent(){
        List<PieEntry> entries = new ArrayList<>();
        long[] dataSpent = new long[9];
        String[] NameSpent = {"","Sinh hoạt","Giải trí","Học tập","Sức khỏe","Quần áo","Ăn uống","Đi lại","Khác"};
        Arrays.fill(dataSpent,0);
        for(int i = 0;i<tableData.size();++i){
            String str = tableData.get(i).getIDservice().toString();
            dataSpent[Integer.valueOf(str.charAt(str.length() - 1)) -48] += tableData.get(i).getPrice();
            Log.e("dataCollect[" + String.valueOf(Integer.valueOf(str.charAt(str.length() - 1))-48) + "]",String.valueOf(tableData.get(i).getPrice()) );
        }
        for(int i = 0;i < dataSpent.length;++i){
            if(dataSpent[i] != 0){
                entries.add(new PieEntry(dataSpent[i],NameSpent[i]));
            }
        }
        PieDataSet pieDataSet = new PieDataSet(entries, "Pie Chart");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextSize(20);



        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }

    private void setInitDataPieChart() {
        List<PieEntry> entries = new ArrayList<>();



    }

    private void setTextViewErron(){
        if(tableData.isEmpty()){
            textErron.setVisibility(View.VISIBLE);
            pieChart.setVisibility(View.GONE);
        }else{
            textErron.setVisibility(View.GONE);
            pieChart.setVisibility(View.VISIBLE);
        }
    }

    private void updateDataClickButtonDialog(boolean clickButtonDialog) {
        String date1 = changDate(dateStartInit);
        String date2 = changDate(dateEndInit);
        if(clickButtonDialog == true){
            if(checkButton == true){
                try {
                    tableData = Table_data.getuserListCollect(IDcollect,date1,date2);


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                getDataOfCollect();
                setTextViewErron();
                clickButtonDialog = false;
            }
            else{
                try {
                    tableData = Table_data.getuserListSpent(IDspent,date1,date2);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                getDataOfSpent();
                setTextViewErron();
                clickButtonDialog = false;
            }
        }
        SumMoney();
    }

    private String changDate(String date){
        SimpleDateFormat sdfInput = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = null;
        try {
            date1 = sdfInput.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // Định dạng đầu ra
        SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdfOutput.format(date1);
        return formattedDate;
    }
    private void clickButton() {

        buttonIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkButton = true;
                buttonIncome.setBackgroundColor(getResources().getColor(R.color.blue80));
                buttonExpnece.setBackgroundColor(Color.WHITE);
                try {
                    tableData = Table_data.getuserListCollect(IDcollect,changDate(dateStartInit),changDate(dateEndInit));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                getDataOfCollect();
                setTextViewErron();

            }
        });
        buttonExpnece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkButton = false;
                buttonExpnece.setBackgroundColor(getResources().getColor(R.color.blue80));
                buttonIncome.setBackgroundColor(Color.WHITE);
                try {
                    tableData = Table_data.getuserListSpent(IDspent,changDate(dateStartInit),changDate(dateEndInit));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                getDataOfSpent();
                setTextViewErron();
            }
        });
        SumMoney();

    }
    private void SumMoney(){
        DecimalFormat df = new DecimalFormat("###,###,###.## VND");
        long sum = 0;
        for(int i = 0;i<tableData.size();++i){
            sum+=tableData.get(i).getPrice();
        }
        sumMoneyText.setText(df.format(sum));
    }

    private void setDateInit() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-7);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateStartInit = simpleDateFormat.format(calendar.getTime());
        dateEndInit = simpleDateFormat.format(Calendar.getInstance().getTime());
        selectedDate.setText(dateStartInit + " - " + dateEndInit);
    }

    private void clickSelectedDate() {
        selectedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.layout_selected_date);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(true);
                ImageButton imageButton = dialog.findViewById(R.id.selected_date_buttonClose);
                TextView dateStart = dialog.findViewById(R.id.selected_date_textDateStart);
                TextView dateEnd = dialog.findViewById(R.id.selected_date_textDateEnd);
                Button button = dialog.findViewById(R.id.selected_date_buttonInsert);
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                clickEditText(dateStart,dateEnd);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedDate.setText(dateStart.getText().toString() + " - " + dateEnd.getText().toString());
                        dateStartInit = dateStart.getText().toString();
                        dateEndInit = dateEnd.getText().toString();
                        dialog.dismiss();
                        clickButtonDialog = true;
                        updateDataClickButtonDialog(clickButtonDialog);
                    }
                });
                dialog.show();
            }
        });

    }

    private void clickEditText(TextView dateStart,TextView dateEnd){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date datetimeStart,datetimeEnd;
        try {
            datetimeStart = simpleDateFormat.parse(dateStartInit);
            datetimeEnd = simpleDateFormat.parse(dateEndInit);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        calendarStart.setTime(datetimeStart);
        calendarEnd.setTime(datetimeEnd);

        dateStart.setText(dateStartInit);
        dateEnd.setText(dateEndInit);


        dateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDateStart(dateStart,calendarStart,simpleDateFormat);
            }
        });
        dateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDateEnd(dateEnd,calendarEnd,simpleDateFormat);
            }
        });

    }
    private void selectedDateStart(TextView date,Calendar calendar,SimpleDateFormat simpleDateFormat){

        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);

                date.setText(simpleDateFormat.format(calendar.getTime()));
                dateStartInit = date.getText().toString();
            }
        }, year,month,day);
        datePickerDialog.show();
    }
    private void selectedDateEnd(TextView date,Calendar calendar,SimpleDateFormat simpleDateFormat){

        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);

                date.setText(simpleDateFormat.format(calendar.getTime()));
                dateEndInit = date.getText().toString();
            }
        }, year,month,day);
        datePickerDialog.show();

    }

    private void Init() {
        buttonIncome = view.findViewById(R.id.budget_textIncome);
        buttonExpnece = view.findViewById(R.id.budget_textExpence);
        selectedDate = view.findViewById(R.id.budget_textSelectedDate);

        sharedPreferences = getActivity().getSharedPreferences("loginData",MODE_PRIVATE);
        IDcollect = sharedPreferences.getInt("IDcollect", 0);
        IDspent = sharedPreferences.getInt("IDspent",0);

        sumMoneyText = view.findViewById(R.id.budget_sumMoneyText);
        tableData = new ArrayList<>();
        clickButtonDialog = false;

        textErron = view.findViewById(R.id.budget_textViewErron);
        pieChart = view.findViewById(R.id.budget_pieChart);
    }
}