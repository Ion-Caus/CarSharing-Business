package com.sep.carsharingbusiness;

import com.sep.carsharingbusiness.graphQLServices.ListingService;
import com.sep.carsharingbusiness.graphQLServices.VehicleService;
import com.sep.carsharingbusiness.model.Listing;
import com.sep.carsharingbusiness.model.Vehicle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
public class CarSharingBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarSharingBusinessApplication.class, args);
        try {
            VehicleService v = new VehicleService();
            Vehicle vehicle = v.getVehicle("XZ 01 334");
            System.out.println(vehicle.getLicenseNo() + " " + vehicle.getBrand());

            ListingService lv = new ListingService();
            ArrayList<Listing> l = lv.getListing(
                    "Aarhus",
                    LocalDateTime.of(2021,10, 20, 10, 45, 0),
                    LocalDateTime.of(2021, 10, 30, 21, 12, 0)
                    );
            System.out.println(l.get(0).getLocation() + " " + l.get(0).vehicle.getType());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
