package com.sep.carsharingbusiness.restControllers;

import com.google.gson.*;
import com.sep.carsharingbusiness.graphQLServices.IVehicleService;
import com.sep.carsharingbusiness.log.Log;
import com.sep.carsharingbusiness.model.Vehicle;
import com.sep.carsharingbusiness.restControllers.extentions.DoubleJsonAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

@RestController
public class VehicleController {
    private final IVehicleService vehicleService;

    private final Gson gson;

    @Autowired
    public VehicleController(IVehicleService vehicleService) {
        this.vehicleService = vehicleService;
        gson = new GsonBuilder()
                .registerTypeAdapter(Double.class,  new DoubleJsonAdapter())
                .create();
    }


    @GetMapping(value = "/vehicles")
    public synchronized String getVehicle(@RequestParam(value = "licenseNo") String licenseNo) {
        try {
            Log.addLog("|restControllers/VehicleController.getVehicle| : Request : LicenseNo:" + licenseNo);
            Vehicle vehicle = vehicleService.getVehicle(licenseNo);
            return gson.toJson(vehicle);

        } catch (IOException | InterruptedException e) {
            Log.addLog("|restControllers/VehicleController.getVehicle| : Error : " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @GetMapping(value = "/vehicles/owner")
    public synchronized String getVehiclesByOwnerCpr(@RequestParam(value = "cpr") String cpr) {
        try {
            Log.addLog("|restControllers/VehicleController.getVehicleByOwnerCpr| : Request : Cpr:" + cpr);
            ArrayList<Vehicle> vehicles = vehicleService.getVehiclesByOwnerCpr(cpr);
            return gson.toJson(vehicles);

        } catch (IOException | InterruptedException e) {
            Log.addLog("|restControllers/VehicleController.getVehicleByOwnerCpr| : Error : " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @PostMapping(value = "/vehicles")
    public synchronized String addVehicle(@RequestBody String json) {
        try {
            Log.addLog("|restControllers/VehicleController.addVehicle| : Request : " + json);
            Vehicle vehicle = gson.fromJson(json, Vehicle.class);
            return gson.toJson(vehicleService.addVehicle(vehicle));

        } catch (IOException | InterruptedException e) {
            Log.addLog("|restControllers/VehicleController.addVehicle| : Error : " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @PatchMapping("/vehicles/{licenseNo}")
    public synchronized String updateVehicle(@RequestBody Vehicle vehicle, @PathVariable String licenseNo) {
        if (!vehicle.getLicenseNo().equals(licenseNo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The licenceNo from param does not match with the vehicle licenseNo.");
        }
        try {
            String result = gson.toJson(vehicleService.updateVehicle(vehicle));
            Log.addLog("|restControllers/VehicleController.updateVehicle| : Reply :  " + result);
            return result;

        } catch (IOException | InterruptedException e) {
            Log.addLog("|restControllers/VehicleController.updateVehicle| : Error : " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/vehicles/{licenseNo}")
    public synchronized HttpStatus removeVehicle(@PathVariable String licenseNo) {
        try {
            Log.addLog("|restControllers/VehicleController.removeVehicle| : Request : " + licenseNo);
            vehicleService.removeVehicle(licenseNo);
            return HttpStatus.OK;

        } catch (IOException | InterruptedException e) {
            Log.addLog("|restControllers/VehicleController.removeVehicle| : Error : " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }
}
