package com.sep.carsharingbusiness.logic.logicImpl;

import com.sep.carsharingbusiness.graphQLServices.IListingService;
import com.sep.carsharingbusiness.logic.IListingLogic;
import com.sep.carsharingbusiness.model.Listing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class ListingLogic implements IListingLogic {

    private final IListingService listingService;

    @Autowired
    public ListingLogic(IListingService listingService) {
        this.listingService = listingService;
    }

    @SessionScope
    public ArrayList<Listing> getListings(String location, LocalDateTime dateFrom, LocalDateTime dateTo) throws IOException, InterruptedException {
        // TODO: 29.11.2021 by Ion - check is vehicle is not rented on this interval 
        return listingService.getListings(location, dateFrom, dateTo);
    }

    @SessionScope
    public Listing getListingById(int id) throws IOException, InterruptedException {
        return listingService.getListingById(id);
    }

    @Override
    public ArrayList<Listing> getListingsByVehicle(String licenseNo) throws IOException, InterruptedException {
        return listingService.getListingsByVehicle(licenseNo);
    }

    @SessionScope
    public Listing addListing(Listing listing) throws IOException, InterruptedException, IllegalArgumentException {
        ArrayList<Listing> listings =  listingService.getListingsByVehicle(listing.vehicle.getLicenseNo());
        if (!listings.isEmpty()) {
            throw new IllegalArgumentException("A listing is already created for the vehicle with licenseNo " + listing.vehicle.getLicenseNo());
        }
        return listingService.addListing(listing);
    }

    @SessionScope
    public Listing updateListing(Listing listing) throws IOException, InterruptedException {
        return listingService.updateListing(listing);
    }

    @SessionScope
    public boolean removeListing(int id) throws IOException, InterruptedException {
        return listingService.removeListing(id);
    }
}
