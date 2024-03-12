package com.example.quanlichitieu;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PlanMoney extends AppCompatActivity {
    TextView txt_dateStart,txt_dateEnd;
    EditText edtThu,edtChi,edtghichu;
    ImageButton btndate1,btndate2;
    Button btnxacnhan;
    ImageButton buttonBack;
    private Date date1,date2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_money);

        Init();

        final Calendar c = Calendar.getInstance();

        clickBack();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        final Calendar ca = Calendar.getInstance();
        // xu ly click ngay bat dau
        btndate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //khai bao bien c chua lich

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                // tao dialog chua ngay thang
                DatePickerDialog datePickerDialog = new DatePickerDialog(PlanMoney.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txt_dateStart.setText(dayOfMonth+"/"+(month + 1)+"/"+year);
                        c.set(year,month,dayOfMonth);
                        try {
                            date1 = sdf.parse(txt_dateStart.getText().toString());
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        // xu ly click ngay ket thuc
        btndate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int year = ca.get(Calendar.YEAR);
                int month = ca.get(Calendar.MONTH);
                int day = ca.get(Calendar.DAY_OF_MONTH);
                // tao dialog chua ngay thang
                DatePickerDialog datepickerdialog = new DatePickerDialog(PlanMoney.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int isyear, int monthOfyear, int dayOfmonth) {
                        txt_dateEnd.setText(dayOfmonth+"/"+(monthOfyear + 1)+"/"+isyear);
                        ca.set(isyear,monthOfyear,dayOfmonth);
                        try {
                            date2 = sdf.parse(txt_dateEnd.getText().toString());
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },year,month,day);
                datepickerdialog.show();
            }
        });
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tienthu = edtThu.getText().toString();
                String tienchi = edtChi.getText().toString();
                String ghichu = edtghichu.getText().toString();

                if (date1 == null || date2 == null) {
                    Toast.makeText(PlanMoney.this, "Vui lòng chọn cả ngày bắt đầu và ngày kết thúc", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Định dạng ngày tháng năm
                String date1Str = new SimpleDateFormat("dd/MM/yyyy").format(date1);
                String date2Str = new SimpleDateFormat("dd/MM/yyyy").format(date2);

                String tonghop = "Ngày bắt đầu: " + date1Str +
                        "\nNgày kết thúc: " + date2Str +
                        "\nSố tiền thu: " + tienthu +
                        "\nSố tiền chi: " + tienchi +
                        "\nGhi chú: " + ghichu;

                int check = date1.compareTo(date2);
                if (check > 0) {
                    Toast.makeText(PlanMoney.this, "Ngày bắt đầu > Ngày kết thúc", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(tienchi.isEmpty()|| tienthu.isEmpty()){
                    Toast.makeText(PlanMoney.this,"Vui lòng nhập cả số tiền thu và chi",Toast.LENGTH_LONG).show();
                    return;
                }
                AlertDialog.Builder mydialog = new AlertDialog.Builder(PlanMoney.this);
                mydialog.setTitle("KẾ HOẠCH CHI TIÊU");
                mydialog.setMessage(tonghop);
                mydialog.setPositiveButton("XÁC NHẬN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                mydialog.create().show();
            }
        });

    }

    private void clickBack() {
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanMoney.this,Home.class);
                startActivity(intent);
            }
        });
    }

    private void Init() {
        txt_dateStart = findViewById(R.id.txt_dateStart);
        txt_dateEnd = findViewById(R.id.txt_dateEnd);
        edtThu = findViewById(R.id.edtThu);
        edtChi = findViewById(R.id.edtChi);
        edtghichu = findViewById(R.id.edtghichu);
        btndate1 = findViewById(R.id.btndate1);
        btndate2 = findViewById(R.id.btndate2);
        btnxacnhan = findViewById(R.id.btnxacnhan);
        buttonBack = findViewById(R.id.planMoney_back);
    }
}