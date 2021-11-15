package com.sep.carsharingbusiness.restControllers;

import com.google.gson.Gson;
import com.sep.carsharingbusiness.graphQLServices.IListingService;
import com.sep.carsharingbusiness.model.Listing;
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

    @PostMapping(value = "/listings")
    public synchronized String addListing(@RequestBody String json) {
        try {
            Listing listing = gson.fromJson(json, Listing.class);
            return gson.toJson( listingService.addListing(listing) );

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @PatchMapping("/listings")
    public synchronized String updateListing(@RequestBody Listing listing, @RequestParam(value = "id") int id) {
        if (listing.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The id from param does not match with the listing's id.");
        }
        try {
            return gson.toJson( listingService.updateListing(listing) );

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/listings")
    public synchronized HttpStatus removeListing(@RequestParam(value = "id") int id) {
        try {
            listingService.removeListing(id);
            return HttpStatus.OK;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }

}
