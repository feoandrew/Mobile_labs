package com.example.layoutsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class AnimationActivity extends AppCompatActivity {

    private TextView Text;
    private Animation anim;
    private Button DialogButtonOK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        Text = findViewById(R.id.textView4);
        anim = AnimationUtils.loadAnimation(this, R.anim.myrotare);
        Thread thread = new Thread(() ->{
                Text.startAnimation(anim);
        });

        Dialog  dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialoglayout);
        DialogButtonOK = dialog.findViewById(R.id.button3);

        DialogButtonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        thread.start();








    }
}