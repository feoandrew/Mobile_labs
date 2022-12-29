package com.example.svetofor;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {

    private Animation anim;
    private Animation revanim;
    private Animation textanim;
    private ImageView Morshu;
    private ImageView Morshu2;
    private MediaPlayer player;
    boolean pressed=false;
    ValueAnimator valueAnimator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView red = findViewById(R.id.textView3);
        TextView yellow = findViewById(R.id.textView2);
        TextView green = findViewById(R.id.textView4);
        TextView presstext = findViewById(R.id.textView5);
        player= MediaPlayer.create(this,R.raw.steps);
        Morshu = findViewById(R.id.imageView);
        Morshu2 = findViewById(R.id.imageView2);
        anim = AnimationUtils.loadAnimation(this, R.anim.myanim);
        revanim = AnimationUtils.loadAnimation(this, R.anim.myanim_reverse);
        textanim = AnimationUtils.loadAnimation(this, R.anim.textanim);
        textanim.setRepeatMode(Animation.REVERSE);
        Context main = this;



        Morshu2.setImageAlpha(0);
        Morshu.setImageAlpha(255);

        Thread svet = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    red.setBackgroundColor(Color.RED);
                    green.setBackgroundColor(Color.GRAY);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    red.setBackgroundColor(Color.GRAY);
                    yellow.setBackgroundColor(Color.YELLOW);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    yellow.setBackgroundColor(Color.GRAY);
                    green.setBackgroundColor(Color.GREEN);
                    if (Morshu.getImageAlpha() > 0) {
                        Morshu.startAnimation(anim);

                    } else {
                        Morshu2.startAnimation(revanim);

                    }
                    player= MediaPlayer.create(main,R.raw.steps);
                    player.start();

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    player.stop();
                    if (Morshu.getImageAlpha() > 0) {
                        Morshu.setImageAlpha(0);
                        Morshu2.setImageAlpha(255);
                    } else {
                        Morshu2.setImageAlpha(0);
                        Morshu.setImageAlpha(255);
                    }


                }
            }
        });


        svet.start();

        PropertyValuesHolder rotate = PropertyValuesHolder.ofFloat("rotate", 0, 180);
        PropertyValuesHolder down = PropertyValuesHolder.ofFloat("down", 0, 1000);
        PropertyValuesHolder color = PropertyValuesHolder.ofFloat("color", 0, 1);
        valueAnimator = ValueAnimator.ofPropertyValuesHolder(rotate, down, color);

//2
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //3
                float value = (float) animation.getAnimatedValue("down");

                    presstext.setTranslationY(value);
                    value = (float) animation.getAnimatedValue("rotate");
                    presstext.setRotation(value);
                    float val = (float) animation.getAnimatedValue("color");

                    presstext.setTextColor(Color.argb(1, 0, Math.abs(val), Math.abs(val)));


            }


        });

//5
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(3000);
//6


        presstext.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    pressed = true;
                    valueAnimator.start();


                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    pressed = false;

                    valueAnimator.reverse();


                }
                return false;
            }


        });
    }
    public void onClickText(View v) {

        valueAnimator.reverse();

    }




}