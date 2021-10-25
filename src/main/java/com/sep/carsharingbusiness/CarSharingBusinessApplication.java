package com.sep.carsharingbusiness;

import com.sep.carsharingbusiness.mediator.BDSocket;
import com.sep.carsharingbusiness.model.Listing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class CarSharingBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarSharingBusinessApplication.class, args);
        try {
            BDSocket socket = new BDSocket();
//            Vehicle v = socket.getVehicle();
//            System.out.println(v.Brand + " " + v.Model + " " + v.ManufactureYear);

            ArrayList<Listing> listings = socket.getListing();
            System.out.println(listings.get(0).Location + " " + listings.get(0).Vehicle.Brand);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
