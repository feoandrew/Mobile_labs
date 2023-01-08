package com.example.emenu;

import java.io.Serializable;


    public class ClientMessage implements Serializable {
        String sender;
        String message;

        ClientMessage(String sndr, String msg)
        {
            sender = sndr;
            message = msg;
        }
    }

