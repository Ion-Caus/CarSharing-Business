package com.sep.carsharingbusiness.graphQLServices;

import com.sep.carsharingbusiness.model.Vehicle;

import java.io.IOException;

public interface IVehicleService {
    Vehicle getVehicle(String licenseNo) throws IOException, InterruptedException;
}
