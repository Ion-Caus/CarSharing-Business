package com.sep.carsharingbusiness;

import com.sep.carsharingbusiness.mediator.BDSocket;
import com.sep.carsharingbusiness.model.Vehicle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CarSharingBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarSharingBusinessApplication.class, args);
        try {
            BDSocket socket = new BDSocket();
            Vehicle v = socket.getVehicle();
            System.out.println(v.Brand + " " + v.Model + " " + v.ManufactureYear);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
