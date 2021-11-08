package com.sep.carsharingbusiness.graphQLServices.serviceImpl;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sep.carsharingbusiness.graphQLServices.IVehicleService;
import com.sep.carsharingbusiness.model.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class VehicleService implements IVehicleService {
    private final String GRAPHQL_URI = "http://localhost:5004/graphql?=";
    private final Gson gson;

    public VehicleService() {
        gson = new Gson();
    }

    // TODO: 30.10.2021 By Ion - research HttpClient and HttpRequest in java
    // store the queries in file to easy edit or add queries
    // set the uri in the constructor
    // create generic method to return T obj
    public Vehicle getVehicle(String licenseNo) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GRAPHQL_URI))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(
                        String.format("{\"query\":\"query {\\n  vehicle(licenseNo: \\\"%s\\\") {\\n    licenseNo\\n    type\\n    brand\\n    model\\n  }\\n}\"}",
                                licenseNo
                        )))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject obj = gson.fromJson(response.body(), JsonObject.class);
        obj = obj.get("data").getAsJsonObject().get("vehicle").getAsJsonObject();
        return gson.fromJson(obj, Vehicle.class);
    }
}
