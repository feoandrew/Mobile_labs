package com.example.emenu;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.InputStreamReader;

import static java.lang.Thread.sleep;


public class MainActivity extends AppCompatActivity {
    private TextView Text, Text2;






   // private Menu menu;











    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test);
       
       Text = findViewById(R.id.warning);

        //Text2 = findViewById(R.id.textView1);
       // ImageView imageView = findViewById(R.id.imageView);
       // imageView.setImageURI(Uri.parse("file:///data/data/com.example.lesson_1/files/Sepia.png"));







            //NETWORK.Download("Menu.txt", getApplicationContext());


        // Menu menu=new Menu(getApplicationContext());
            //  if(menu.Build("Menu.txt"));
            //  Text.setText(menu.GetCategory(0).getName());
      // } catch (InterruptedException | NetworkOnMainThreadException e) {
      //     Text2.setText("Download FAILED" + NETWORK.LastFileSize);
     // }

       //Text.setText("Downloaded" + NETWORK.LastFileSize);
    }




        //setContentView(R.layout.auntification);
    @Override
    protected void onResume()
    {
        super.onResume();
       // NETWORK = new NetWork("192.168.0.105", 22441);
      // menu=new Menu(getApplicationContext());
      //  if(NETWORK.StartNetwork())
      //  {
      //      NETWORK.GetContext(getApplicationContext());

     //       NETWORK.Download("Menu.txt");



            //if (NETWORK.Download(FILENAME).compareTo("0") != 0) {
             //   Text.setText("Download success");
           //}
      //  }
      //  menu.Build("Menu.txt");

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
      //



    }

    public void onClickSettings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
        Text.setText("");

    }
    public void onClickStart(View view) {
        try{
           InputStreamReader r = new InputStreamReader(openFileInput("Config.txt"));
            r.close();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startLockTask();
            }
            Intent intent = new Intent(this, FullscreenActivity.class);
            startActivity(intent);
            Text.setText("");

        }
        catch (IOException e)
        {
            Text.setText("Необоходима первоначальная настройка!!!");
        }


    }
}