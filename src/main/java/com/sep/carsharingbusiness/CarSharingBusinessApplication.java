package com.sep.carsharingbusiness;

import com.sep.carsharingbusiness.graphQLServices.serviceImpl.ListingService;
import com.sep.carsharingbusiness.graphQLServices.serviceImpl.VehicleService;
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
            Vehicle vehicle = VehicleService.getInstance().getVehicle("XZ01334");
            System.out.println(vehicle.getLicenseNo() + " " + vehicle.getBrand());

            ArrayList<Listing> l = ListingService.getInstance().getListing(
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
