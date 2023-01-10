package com.example.emenu;

import java.io.Serializable;


    public class ClientMessage implements Serializable {
        public String sender;
        public String type;
        public String message;
        public byte File[];
        public Order order;

       public ClientMessage(String sndr, String msg_type, String msg)
        {
            sender = sndr;
            message = msg;
            type = msg_type;
            File = new byte[1024];
        }
    }

