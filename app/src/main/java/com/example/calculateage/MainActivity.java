package com.example.calculateage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView tv_birthDay, tv_remain, tv_age, tv_vis_birth, tv_vis_age, tv_vis_remain;
    Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_remain = findViewById(R.id.main_tv_remain);
        tv_birthDay = findViewById(R.id.main_tv_birthday);
        tv_vis_birth = findViewById(R.id.tv_vis_birth);
        tv_vis_remain = findViewById(R.id.tv_vis_remain);
        tv_vis_age = findViewById(R.id.tv_vis_age);
        tv_age = findViewById(R.id.main_tv_age);
    }

    public void showCalender(View view) {
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                tv_birthDay.setText(dayOfMonth + " / " + month + " / " + year);
                tv_vis_birth.setVisibility(View.VISIBLE);
                calcRemain(year, month, dayOfMonth);
                calcAge(year, month, dayOfMonth);
            }
        },
                currentYear,
                currentMonth,
                currentDay
        );
        datePickerDialog.show();
    }


    private void calcRemain(int year, int month, int day) {
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        if (year != 0 && month != 0 && day != 0 && currentYear > year) {
            int _year = currentYear - year;
            int _month = currentMonth - month;
            int _day = currentDay - day;
            _day = _day < 0 ? (_day * -1) : _day;
            String resultMessage = "";

            if (_month < 0) {
                if (((_month * -1) - 1) == 0) {
                    resultMessage = _day + " days to complete\n" + _year + " years old";

                } else {
                    resultMessage = ((_month * -1) - 1) + " month\nand " + _day + " days to complete\n" + _year + " years old.";
                }
            }
            tv_remain.setText(resultMessage);
            tv_vis_remain.setVisibility(View.VISIBLE);

        } else {
            Toast.makeText(MainActivity.this, "Enter real date", Toast.LENGTH_SHORT).show();
        }
    }


    private void calcAge(int year, int month, int day) {
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int _day = currentDay - day;
        if (_day < 0) {
            _day += 30;
            currentMonth = currentMonth - 1;
        }
        int _month = currentMonth - month;
        if (_month < 0) {
            _month += 12;
            currentYear = currentYear - 1;
        }

        int _year = currentYear - year;

        String y = getResources().getString(R.string.year);
        String m = getResources().getString(R.string.month);
        String d = getResources().getString(R.string.day);
        String birthDay;
        if (_month == 11) {
            birthDay = (_year + 1) + " " + y + ", " + 0 + " " + m + ", " + (_day) + " " + d + ".";

        } else {
            birthDay = _year + " " + y + ", " + (_month + 1) + " " + m + ", " + (_day + 1) + " " + d + ".";

        }
        tv_age.setText(birthDay);
        tv_vis_age.setVisibility(View.VISIBLE);

    }


}