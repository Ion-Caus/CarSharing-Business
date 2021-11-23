package com.sep.carsharingbusiness.graphQLServices.serviceImpl;


import com.sep.carsharingbusiness.graphQLServices.IListingService;
import com.sep.carsharingbusiness.model.Listing;
import com.sep.carsharingbusiness.mutations.MutationEnum;
import com.sep.carsharingbusiness.queries.QueryEnum;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class ListingService implements IListingService {


    @SessionScope
    public ArrayList<Listing> getListings(String location, LocalDateTime dateFrom, LocalDateTime dateTo) throws IOException, InterruptedException {
        return GraphQLService.createListQuery(
                String.format(
                        GraphQLService.getQueryFromFile( QueryEnum.ListingsByLocationAndDates.get(), false ),
                        location,
                        dateFrom,
                        dateTo
                ),
                "listing"
        );
    }

    @SessionScope
    public Listing getListingById(int id) throws IOException, InterruptedException {
        // TODO: 23.11.2021 by Ion - Create the Query to get a listing by id || also implemented it in DAL
        return null;
//        return GraphQLService.createListQuery(
//                String.format(
//                        GraphQLService.getQueryFromFile( QueryEnum.ListingById.get(), false ),
//                        id
//                ),
//                "listing"
//        );
    }

    @SessionScope
    public Listing addListing(Listing listing) throws IOException, InterruptedException {
        return GraphQLService.createObjQuery(
                String.format(
                        GraphQLService.getQueryFromFile( MutationEnum.AddListing.get(), true),
                        listing.getPrice(),
                        listing.getListedDate(),
                        listing.getLocation(),
                        listing.getDateFrom(),
                        listing.getDateTo(),
                        listing.getVehicle().getLicenseNo(),
                        listing.vehicle.getBrand(),
                        listing.vehicle.getModel(),
                        listing.vehicle.getTransmission(),
                        listing.vehicle.getFuelType(),
                        listing.vehicle.getType(),
                        listing.vehicle.getSeats(),
                        listing.vehicle.getMileage(),
                        listing.vehicle.getManufactureYear()
                ),
                "addListing",
                Listing.class
        );
    }

    @SessionScope
    public Listing updateListing(Listing listing) throws IOException, InterruptedException {
        return GraphQLService.createObjQuery(
                String.format(
                        GraphQLService.getQueryFromFile( MutationEnum.UpdateListing.get(), true),
                        listing.getId(),
                        listing.getPrice(),
                        listing.getListedDate(),
                        listing.getLocation(),
                        listing.getDateFrom(),
                        listing.getDateTo(),
                        listing.getVehicle().getLicenseNo(),
                        listing.vehicle.getBrand(),
                        listing.vehicle.getModel(),
                        listing.vehicle.getTransmission(),
                        listing.vehicle.getFuelType(),
                        listing.vehicle.getType(),
                        listing.vehicle.getSeats(),
                        listing.vehicle.getMileage(),
                        listing.vehicle.getManufactureYear()
                ),
                "updateListing",
                Listing.class
        );
    }

    @SessionScope
    public void removeListing(int id) throws IOException, InterruptedException {
        GraphQLService.sendQuery(
                String.format(
                        GraphQLService.getQueryFromFile( MutationEnum.RemoveListing.get(), true),
                        id
                )
        );
    }
}
