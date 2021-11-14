package com.sep.carsharingbusiness.graphQLServices.serviceImpl;


import com.sep.carsharingbusiness.graphQLServices.IVehicleService;
import com.sep.carsharingbusiness.model.Vehicle;
import com.sep.carsharingbusiness.queries.QueryEnum;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.IOException;

@Service
public class VehicleService implements IVehicleService {

    @SessionScope
    public Vehicle getVehicle(String licenseNo) throws IOException, InterruptedException {
        return GraphQLService.createObjQuery(
                String.format(
                        GraphQLService.getQueryFromFile( QueryEnum.VehicleByLicenseNo.get() ),
                        licenseNo
                ),
                "vehicle",
                Vehicle.class
        );
    }
}
