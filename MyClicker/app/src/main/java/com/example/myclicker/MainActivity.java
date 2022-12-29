package com.example.myclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int count = 0;
    private TextView Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Text = findViewById(R.id.textView);
    }

    public void onClickMinus(View v)
    {
        count=count-2;
        Text.setText(""+count);
    }
    public void onClickPlus(View v)
    {
        count=count+2;
        Text.setText(""+count);
    }
}