package com.sep.carsharingbusiness.restControllers;

import com.google.gson.Gson;
import com.sep.carsharingbusiness.graphQLServices.IListingService;
import com.sep.carsharingbusiness.graphQLServices.serviceImpl.ListingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class ListingController {
    private IListingService listingService;

    private Gson gson;

    public ListingController() {
        listingService = ListingService.getInstance();
        gson = new Gson();
    }

    @GetMapping(value = "/listings")
    public synchronized String getListing(
            @RequestParam(value = "location") String location,
            @RequestParam(value = "dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom,
            @RequestParam(value = "dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTo
    ) {
        try {
            return gson.toJson( listingService.getListing(location, dateFrom, dateTo) );

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

}
