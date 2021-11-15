package com.sep.carsharingbusiness.restControllers;

import com.google.gson.Gson;
import com.sep.carsharingbusiness.graphQLServices.IVehicleService;
import com.sep.carsharingbusiness.log.Log;
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
    public VehicleController(IVehicleService vehicleService, Log log) {
        this.vehicleService = vehicleService;
        gson = new Gson();
    }


    @GetMapping(value = "/vehicles")
    public synchronized String getVehicle(@RequestParam(value = "licenseNo") String licenseNo) {
        try {
            Vehicle vehicle = vehicleService.getVehicle(licenseNo);
            String result = gson.toJson(vehicle);
            Log.addLog("|restControllers/VehicleController.getVehicle| : Request : " + licenseNo);
            return result;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Log.addLog("|restControllers/VehicleController.getVehicle| : Error : " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @PostMapping(value = "/vehicles")
    public synchronized String addVehicle(@RequestBody String json) {
        try {
            Vehicle vehicle = gson.fromJson(json, Vehicle.class);
            String result = gson.toJson(vehicleService.addVehicle(vehicle));
            Log.addLog("|restControllers/VehicleController.addVehicle| : Request : " + json);
            return result;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Log.addLog("|restControllers/VehicleController.addVehicle| : Error : " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @PatchMapping("/vehicles")
    public synchronized String updateVehicle(@RequestBody Vehicle vehicle, @RequestParam(value = "licenseNo") String licenseNo) {
        if (!vehicle.getLicenseNo().equals(licenseNo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The licenceNo from param does not match with the vehicle licenseNo.");
        }
        try {
            String result = gson.toJson(vehicleService.updateVehicle(vehicle));
            Log.addLog("|restControllers/VehicleController.updateVehicle| : Reply :  " + result);
            return result;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Log.addLog("|restControllers/VehicleController.updateVehicle| : Error : " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/vehicles")
    public synchronized HttpStatus removeVehicle(@RequestParam(value = "licenseNo") String licenseNo) {
        try {
            vehicleService.removeVehicle(licenseNo);
            Log.addLog("|restControllers/VehicleController.removeVehicle| : Request : " + licenseNo);
            return HttpStatus.OK;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Log.addLog("|restControllers/VehicleController.removeVehicle| : Error : " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }
}
