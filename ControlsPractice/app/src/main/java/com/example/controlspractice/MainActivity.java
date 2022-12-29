package com.example.controlspractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public  EditText TimeText;
    public  EditText DateText;
    public TextView ProgressText;
    public TextView PressedText;
    private Button counter;
    private Button constbutton;
    private Button pressbutton;
    private int count=0;
    private Switch aSwitch;
    private SeekBar
            sk;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter = findViewById(R.id.button2);
        pressbutton = findViewById(R.id.button);
        constbutton = findViewById(R.id.button6);
        DateText = findViewById(R.id.editTextTextPersonName2);
        TimeText = findViewById(R.id.editTextTextPersonName3);
        ProgressText = findViewById(R.id.textView2);
        PressedText = findViewById(R.id.textView);
        sk = findViewById(R.id.seekBar);
        aSwitch = findViewById(R.id.switch1);

        Context activityContext = this;
        if (!Settings.System.canWrite(this)) changeWriteSettingsPermission(this);
        try {
            sk.setProgress(Settings.System.getInt(this.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS) / 40);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        ProgressText.setText("" + sk.getProgress());


        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub


                changeScreenBrightness(activityContext, progress * 40);

                ProgressText.setText("" + progress);


            }
        });

        ArrayList<String> array = new ArrayList<String>();

        array.add("Gay");
        array.add("Website");


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (aSwitch.isChecked()) {
                aSwitch.setText("Включен");
            } else {

                aSwitch.setText("Выключен");
            }
        });

        constbutton.setOnTouchListener((buttonView, isChecked) -> {
            constbutton.setPressed(true);
            return true;

        });


        pressbutton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    PressedText.setText("Нажато");
                }
                if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    PressedText.setText("Отпущено");
                }
                return false;
            }
        });
    }
    public void onClickTime(View v) {

        TimePickerFragment timeFragment = new TimePickerFragment(TimeText);
        timeFragment.show(timeFragment.getChildFragmentManager() , "timePicker");

    }
    public void onClickCounter(View v) {
        count++;
        counter.setText(""+count);
    }




    public void onClickDate(View v) {

        DatePickerFragment dateFragment = new DatePickerFragment(DateText);
        dateFragment.show(dateFragment.getChildFragmentManager(), "DatePicker");

    }

    private void changeWriteSettingsPermission(Context context) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
        context.startActivity(intent);
    }

    private void changeScreenBrightness(Context context, int screenBrightnessValue) {
        // Change the screen brightness change mode to manual.
        Settings.System.putInt(
                context.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
        );
        // Apply the screen brightness value to the system, this will change
        // the value in Settings ---> Display ---> Brightness level.
        // It will also change the screen brightness for the device.
        Settings.System.putInt(
                context.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS, screenBrightnessValue
        );
    }

}


