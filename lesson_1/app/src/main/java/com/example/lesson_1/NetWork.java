package com.example.lesson_1;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class NetWork {
    NetWork(String host, int hostport){
        adress = host;
        port = hostport;

    };

    private static Socket Client;
    private static InputStream in;
    private static BufferedWriter out;
    public String LastServerAnswer=" ";
    public int LastFileSize = 0;
    private boolean Connected=false;
    private String adress;
    private int port;

    private Thread NET;
    private Thread NETIO;

    public void StartNetwork(){
        NET=new Thread(() -> {
            try {
                StartNet();
            } catch (IOException e) {

            }
        });
        NET.start();
    }
    private void StartNet() throws IOException {

            Client = new Socket(adress, port);
            NETIO = new Thread(new Runnable() {
                @Override
                public void run() {

                }
            });
            NETIO.start();

        if(Client.isConnected()) {
            in=Client.getInputStream();
            out=new BufferedWriter(new OutputStreamWriter(Client.getOutputStream()));
            while (in.available()>0)
            {
                in.skip(1);
            }

            while (Client.isConnected()) {
                Connected = true;


            }
            Connected = false;
        }
    }
    ;



    private String Send(String content) throws IOException {
        String answer = "";
        if(Client.isConnected()) {

            out.write(content);

            out.flush();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            answer = rd.readLine();


            LastServerAnswer = answer;
            out.close();
            rd.close();
            return answer;
        }
        return "NOT CONNECTED";

    }
    private String Send(Recept res) throws IOException {
        String answer = "";
        if(Client.isConnected()) {

            out.write("Recept\n"+res.GetString());
            out.write(res.getSum());

            out.flush();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            answer = rd.readLine();


            LastServerAnswer = answer;

            return answer;
        }
        return "NOT CONNECTED";

    }
    private boolean load(String FILENAME, Context fileContext) throws IOException {

        String answer="";
        answer = Send("Download\n" + FILENAME + "\n\0");
        LastServerAnswer = answer;
        if(answer.compareTo("Sending")==0) {

            byte temp[];
            temp = new byte[1024];
            OutputStream Fout = fileContext.openFileOutput(FILENAME, 0);

            int len = 0;
            len = in.read(temp, 0, 4);
            int FILE_SIZE = 0;

            for (int i = 0; i < 4; i++) {
                int ByteNum = 0;
                //Text.setText(FILE_SIZE+" ");

                ByteNum = (int) temp[i] & 0xff;

                FILE_SIZE = FILE_SIZE + ByteNum * (int) Math.pow(2, (8 * i));

            }
            LastFileSize=FILE_SIZE;


            int Remain = FILE_SIZE;
            while (Client.isConnected() && Remain >0) {

                len = in.read(temp, 0, 1024);
                //Remain = Remain - len;
                // Text2.setText(Accepted+"/"+FILE_SIZE);

                if((Remain-len)>0) {
                    Fout.write(temp, 0, len);
                }
                else
                {
                    Fout.write(temp, 0, Remain);
                }
                Fout.flush();
                Remain = Remain - len;
            }
            Fout.close();
            while (in.available() > 0) {
                in.skip(1);
            }

            if (Remain < 0) {
                return true;
            } else {
                return false;
            }
        }
        else
        {

            return false;
        }

    }

    public String SendReqvest(String content) throws InterruptedException {
        String answer;
      //  if(NETIO.isAlive())
      // NETIO.join();
        NETIO = new Thread(() -> {
           try {
               Send(content+"\n\0");
           } catch (IOException e) {
                Connected = false;
            }
        });
        NETIO.start();
        NETIO.join();
        return LastServerAnswer;


    }
    public boolean Download(String FILENAME, Context context) throws InterruptedException {
        final boolean[] success = new boolean[1];
      //  if(NETIO.isAlive())
       //     NETIO.join();
        NETIO = new Thread(() -> {
            try {
              success[0] =load(FILENAME, context);
            } catch (IOException e) {
                success[0]=false;
            }
        });
        NETIO.start();
        NETIO.join();
        return success[0];

    }

    public void Destruct() throws IOException {
        if(NETIO.isAlive())
            NETIO.interrupt();
        if(Client.isConnected())
            Client.close();
    }
    public boolean SendRecept(Recept res) throws InterruptedException {
        if(NETIO.isAlive())
            NETIO.join();
        NETIO = new Thread(() -> {
            try {
                Send(res);
            } catch (IOException e) {
                Connected = false;
            }
        });
        NETIO.start();
        NETIO.join();
        if(LastServerAnswer.compareTo("Accepted")==0)
        return true;
        return false;
    }

}