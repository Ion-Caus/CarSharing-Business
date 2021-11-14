package com.sep.carsharingbusiness.graphQLServices.serviceImpl;


import com.sep.carsharingbusiness.graphQLServices.IVehicleService;
import com.sep.carsharingbusiness.model.Vehicle;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class VehicleService implements IVehicleService {

    public Vehicle getVehicle(String licenseNo) throws IOException, InterruptedException {
        return GraphQLService.createObjQuery(
                String.format(
                        GraphQLService.getQueryFromFile("GetVehicleByLicenseNo.graphql"),
                        licenseNo
                ),
                "vehicle",
                Vehicle.class
        );
    }
}
