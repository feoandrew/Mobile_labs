package com.example.lesson_1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;

import java.io.*;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;


public class MainActivity extends AppCompatActivity {
    private TextView Text, Text2;
    private static Socket Client;
    private static InputStream in; // поток чтения из сокета
    private static BufferedWriter out;
    public boolean send=false;
    public boolean NET=false;
    final  String LOG_TAG = "myLogs";
    private NetWork NETWORK;
    final String FILENAME = "Miku.png";

    final String DIR_SD = "MyFiles";
    final String FILENAME_SD = "fileSD";





    void ReadFile(String Dest) {
        try {
            // отрываем поток для записи
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(FILENAME)));

            // пишем данные
            Dest=br.readLine();
            // закрываем поток
            br.close();
            Log.d(LOG_TAG, "Файл прочитан");
        } catch (FileNotFoundException e) {
            Text.setText("NO FILE");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Text = findViewById(R.id.textView);

        Text2 = findViewById(R.id.textView1);
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageURI(Uri.parse("file:///data/data/com.example.lesson_1/files/Sepia.png"));

        NETWORK = new NetWork("192.168.0.105", 24441);
        NETWORK.StartNetwork();
        String str = " " + 0;
        Text.setText(str);

        try {
           NETWORK.SendReqvest("LOL");
            //NETWORK.Download("Menu.txt", getApplicationContext());


        // Menu menu=new Menu(getApplicationContext());
            //  if(menu.Build("Menu.txt"));
            //  Text.setText(menu.GetCategory(0).getName());
       } catch (InterruptedException | NetworkOnMainThreadException e) {
            Text2.setText("Download FAILED" + NETWORK.LastFileSize);
       }

        Text.setText("Downloaded" + NETWORK.LastFileSize);
    }




        //setContentView(R.layout.auntification);

        public void onClickSend (View view)  {















               //if(NETWORK.Download(FILENAME, getBaseContext()))//  Text.setText("File Downloaded")l
        }
    @Override
    protected void onDestroy(){

        try {
            NETWORK.Destruct();

        } catch (IOException e) {

        }


        super.onDestroy();
    }
}