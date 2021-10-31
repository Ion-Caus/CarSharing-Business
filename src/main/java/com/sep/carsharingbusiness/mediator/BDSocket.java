package com.sep.carsharingbusiness.mediator;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.sep.carsharingbusiness.model.Listing;
import com.sep.carsharingbusiness.model.Vehicle;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.AbstractList;
import java.util.ArrayList;

public class BDSocket {
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 2910;

    private Socket socket;
    private InputStream in;
    private OutputStream out;
    private Gson gson;

    public BDSocket() throws IOException {
        this.socket = new Socket(HOST, PORT);
        this.in = socket.getInputStream();
        this.out = socket.getOutputStream();
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
    }

    public Vehicle getVehicle() throws IOException {
        // get Vehicle by licenseNo Ab 00 123

        //send request
        RequestReply request = new RequestReply("GetVehicle", "String", "MK 99 222");
        String requestJson = gson.toJson(request);
        byte[] requestBytes = requestJson.getBytes();
        out.write(requestBytes, 0, requestBytes.length);

        //receive reply
        byte[] replyBytes = new byte[2048];
        int bytesRead = in.read(replyBytes,0,replyBytes.length);
        String replyJson = new String(replyBytes,0,bytesRead);
        RequestReply reply = gson.fromJson(replyJson, RequestReply.class);

        Vehicle vehicle = null;
        if (reply.ObjType.equals("Vehicle")) {
            vehicle = gson.fromJson(reply.ObjJson, Vehicle.class);
            System.out.println("Received from server: \n" + gson.toJson(vehicle));
        }
        socket.close();
        return vehicle;
    }

    public ArrayList<Listing> getListing() throws IOException {
        // getListing Location = Aarhus, DateFrom = 20 Oct 2021, DateTo = 30 Oct 2021

        //using Calendar with Date class, because gson throws warning on LocalDateTime
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2021, Calendar.OCTOBER, 20, 10, 45, 0);
//        Date dateFrom = calendar.getTime();
//        calendar.set(2021, Calendar.OCTOBER, 30, 21, 12, 0);
//        Date dateTo = calendar.getTime();


        //send request
        FilterParam filterParam
                = new FilterParam(
                "Aarhus",
                LocalDateTime.of(2021,10, 20, 10, 45, 0),
                LocalDateTime.of(2021, 10, 30, 21, 12, 0)
        );
        RequestReply request
                = new RequestReply(
                        "GetListing",
                "FilterParam",
                gson.toJson(filterParam)
        );
        byte[] requestBytes = gson.toJson(request).getBytes();
        out.write(requestBytes, 0, requestBytes.length);

        //receive reply
        byte[] replyBytes = new byte[2048];
        int bytesRead = in.read(replyBytes,0,replyBytes.length);
        String replyJson = new String(replyBytes,0,bytesRead);
        RequestReply reply = gson.fromJson(replyJson, RequestReply.class);

        ArrayList<Listing> listings = null;
        if (reply.ObjType.equals("Listing")) {
            listings = gson.fromJson(reply.ObjJson, new TypeToken<ArrayList<Listing>>(){}.getType());
            System.out.println("Received from server: \n" + gson.toJson(listings));
        }
        socket.close();
        return listings;
    }

}
