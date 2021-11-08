package com.sep.carsharingbusiness.restControllers;

import com.google.gson.Gson;
import com.sep.carsharingbusiness.graphQLServices.IVehicleService;
import com.sep.carsharingbusiness.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
public class VehicleController {
    private IVehicleService vehicleService;

    private Gson gson;

    @Autowired
    public VehicleController(IVehicleService vehicleService) {
        this.vehicleService = vehicleService;
        gson = new Gson();
    }


    @GetMapping(value = "/vehicle")
    public synchronized String getVehicle(@RequestParam(value = "licenseNo") String licenseNo) {
        try {
            Vehicle v = vehicleService.getVehicle(licenseNo);
            System.out.println(v.getLicenseNo());
            return gson.toJson( v );

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }
}
