package com.sep.carsharingbusiness.restControllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sep.carsharingbusiness.graphQLServices.IListingService;
import com.sep.carsharingbusiness.log.Log;
import com.sep.carsharingbusiness.model.Listing;
import com.sep.carsharingbusiness.extentions.DoubleJsonAdapter;
import com.sep.carsharingbusiness.extentions.LocalDateTimeJsonAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class ListingController {
    private final IListingService listingService;

    private final Gson gson;

    @Autowired
    public ListingController(IListingService listingService) {
        this.listingService = listingService;
        gson = new GsonBuilder()
                .registerTypeAdapter(Double.class,  new DoubleJsonAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonAdapter())
                .create();
    }

    @GetMapping(value = "/listings")
    public synchronized String getListing(
            @RequestParam(value = "location") String location,
            @RequestParam(value = "dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom,
            @RequestParam(value = "dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTo
    ) {
        try {
            Log.addLog("|restControllers/ListingController.getListing| : Request : Location: " + location + ", DateFrom: "+ dateFrom + ", DateTo: " + dateTo);
            return gson.toJson(listingService.getListings(location, dateFrom, dateTo));

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Log.addLog("|restControllers/ListingController.getListing| : Error : " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @GetMapping(value = "/listings/{id}")
    public synchronized String getListingById(@PathVariable int id) {
        try {
            Log.addLog("|restControllers/ListingController.getListingById| : Request : Id:" + id );
            return gson.toJson(listingService.getListingById(id));

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Log.addLog("|restControllers/ListingController.getListingById| : Error : " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @PostMapping(value = "/listings")
    public synchronized String addListing(@RequestBody String json) {
        try {
            Log.addLog("|restControllers/ListingController.addListing| : Request : " + json);
            Listing listing = gson.fromJson(json, Listing.class);
            return gson.toJson(listingService.addListing(listing));

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Log.addLog("|restControllers/ListingController.addListing| : Error : " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @PatchMapping("/listings/{id}")
    public synchronized String updateListing(@RequestBody Listing listing, @PathVariable int id) {
        if (listing.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The id from param does not match with the listing's id.");
        }
        try {
            String result = gson.toJson(listingService.updateListing(listing));
            Log.addLog("|restControllers/ListingController.updateListing| : Reply : " + result);
            return result;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Log.addLog("|restControllers/ListingController.updateListing| : Error : " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/listings/{id}")
    public synchronized HttpStatus removeListing(@PathVariable int id) {
        try {
            Log.addLog("|restControllers/ListingController.removeListing| : Request : " + id);
            listingService.removeListing(id);
            return HttpStatus.OK;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Log.addLog("|restControllers/ListingController.removeListing| : Error : " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

}
