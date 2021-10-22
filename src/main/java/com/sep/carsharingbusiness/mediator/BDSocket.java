package com.sep.carsharingbusiness.mediator;

import com.google.gson.Gson;
import com.sep.carsharingbusiness.model.Vehicle;

import java.io.*;
import java.net.Socket;

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
        this.gson = new Gson();
    }

    public Vehicle getVehicle() throws IOException {
        // get Vehicle by licenseNo Ab 00 123

        //send request
        RequestReply request = new RequestReply("GetVehicle", "String", "Ab 00 123");
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
}
