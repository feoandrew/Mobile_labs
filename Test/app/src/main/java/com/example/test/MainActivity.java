package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int Count=1;
    Dialog dialog;
    TextView text;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new Dialog(MainActivity.this);

        // Установите заголовок
        dialog.setTitle("Измените колличество");
        // Передайте ссылку на разметку
        dialog.setContentView(R.layout.dialog);
        // Найдите элемент TextView внутри вашей разметки
        // и установите ему соответствующий текст
        text = (TextView) dialog.findViewById(R.id.textView6);
        text.setText(""+Count);
        dialog.show();
    }
    public void onMinus(View v)
    {
        if(Count>1)
            Count--;
        text.setText(""+Count);

    }
    public void onPlus(View v)
    {
        Count++;
        text.setText(""+Count);
    }
    public void onOk(View v)
    {
        dialog.hide();

    }
    public void onDelete(View v)
    {
        dialog.hide();
    }

}