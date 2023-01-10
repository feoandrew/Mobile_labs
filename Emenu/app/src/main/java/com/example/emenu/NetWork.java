package com.example.emenu;

import android.content.Context;
import android.os.NetworkOnMainThreadException;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
;
import java.net.Socket;

public class NetWork {




    Socket Client;
    ObjectInputStream in;
    ObjectOutputStream out;
    public String LastServerAnswer = " ";
    public int LastFileSize = 0;
    private boolean Connected = false;
    private String adress;
    private int port;
    private Context cont;
    private byte[] temp;
    private String name;

    private boolean ConnectedUpdated = false;


    private Thread NET;

    private Thread NETIO;


    NetWork(String host, int hostport, String Name) {
        adress = host;
        port = hostport;
        name = Name;
        temp = new byte[1024];

    }



    public void GetContext(Context context) {
        cont = context;
    }
    public boolean check()
    {
        return Client.isConnected();
    }
    public boolean StartNetwork() throws IOException, InterruptedException {
        Client = new Socket(adress, port);
        while (!Client.isConnected())
        {
            Client = new Socket(adress, port);
        }


        try
        {


                ObjectOutputStream oos = new ObjectOutputStream(Client.getOutputStream());

                Connected = true;


                out = oos;
            } catch (IOException e) {
                e.printStackTrace();
            }







        return Connected;
    }

    public String Auntification(String code) throws IOException {
        Send("Password", code);
        try {
            ClientMessage answer = (ClientMessage) in.readObject();
            return answer.message;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "0";
    }
    public void Send(String msg_type, String content) throws IOException {
        ClientMessage message = new ClientMessage(name, msg_type, content);
        out.writeObject(message);
        out.flush();
        if(in ==null)
        {
            in = new ObjectInputStream(Client.getInputStream());
        }
    }



    public void Destruct() throws IOException {

        try {


            if (Connected) {
                Client.close();
                in.close();
                out.close();
            }
        } catch (NetworkOnMainThreadException e) {

        }
    }


    public String Download(String filename, String storageDir) throws IOException {
        Send("DOWNLOAD", filename);
        try {
            ClientMessage answer = (ClientMessage) in.readObject();
            FileOutputStream stream = new FileOutputStream(storageDir+"/"+filename);
            stream.write(answer.File);
            stream.flush();
            stream.close();
            return "COMPLETE";
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "FAILED";
    }
    public boolean SendOrder(Order order) {

        ClientMessage msg = new ClientMessage(name, "ORDER", "ORDER");
        msg.order = order;
        try {
            out.writeObject(msg);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}