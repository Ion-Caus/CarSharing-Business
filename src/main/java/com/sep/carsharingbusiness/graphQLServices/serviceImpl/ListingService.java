package com.sep.carsharingbusiness.graphQLServices.serviceImpl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.sep.carsharingbusiness.graphQLServices.IListingService;
import com.sep.carsharingbusiness.model.Listing;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ListingService implements IListingService {
    private final Gson gson;

    private static volatile ListingService instance;
    private static final Object lock = new Object();

    private ListingService() {
        gson = new Gson();
    }

    public static ListingService getInstance()
    {
        if (instance == null)
        {
            synchronized (lock){
                if (instance == null) {
                    instance = new ListingService();
                }
            }
        }
        return instance;
    }

    // TODO: 31.10.2021 By Ion - research HttpClient and HttpRequest in java
    // store the queries in file to easy edit or add queries
    // set the uri in the constructor
    // create generic method to return T obj
    public ArrayList<Listing> getListing(String location, LocalDateTime dateFrom, LocalDateTime dateTo) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5004/graphql"))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(
                        String.format("{\"query\":\"query {\\n  listing(location: \\\"%s\\\", dateFrom: \\\"%s\\\", dateTo: \\\"%s\\\") {\\n    price\\n    location\\n    vehicle {\\n      licenseNo\\n      brand\\n      model\\n      type\\n    }\\n  }\\n}\"}",
                                location,
                                dateFrom.format(DateTimeFormatter.ISO_DATE_TIME),
                                dateTo.format(DateTimeFormatter.ISO_DATE_TIME)
                        )))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        JsonObject obj = gson.fromJson(response.body(), JsonObject.class);
        JsonArray arr = obj.get("data").getAsJsonObject().get("listing").getAsJsonArray();
        return gson.fromJson(arr, new TypeToken<ArrayList<Listing>>() {}.getType());
    }
}
