package com.sep.carsharingbusiness.restControllers;

import com.google.gson.Gson;
import com.sep.carsharingbusiness.graphQLServices.IVehicleService;
import com.sep.carsharingbusiness.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
public class VehicleController {
    private final IVehicleService vehicleService;

    private final Gson gson;

    @Autowired
    public VehicleController(IVehicleService vehicleService) {
        this.vehicleService = vehicleService;
        gson = new Gson();
    }


    @GetMapping(value = "/vehicles")
    public synchronized String getVehicle(@RequestParam(value = "licenseNo") String licenseNo) {
        try {
            return gson.toJson( vehicleService.getVehicle(licenseNo) );

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @PostMapping(value = "/vehicles")
    public synchronized String addVehicle(@RequestBody String json) {
        try {
            Vehicle vehicle = gson.fromJson(json, Vehicle.class);
            return gson.toJson( vehicleService.addVehicle(vehicle) );

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @PatchMapping("/vehicles")
    public synchronized String updateVehicle(@RequestBody Vehicle vehicle, @RequestParam(value = "licenseNo") String licenseNo) {
        if (!vehicle.getLicenseNo().equals(licenseNo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The licenceNo from param does not match with the vehicle licenseNo.");
        }
        try {
            return gson.toJson( vehicleService.updateVehicle(vehicle) );

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/vehicles")
    public synchronized HttpStatus removeVehicle(@RequestParam(value = "licenseNo") String licenseNo) {
        try {
            vehicleService.removeVehicle(licenseNo);
            return HttpStatus.OK;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }
}
