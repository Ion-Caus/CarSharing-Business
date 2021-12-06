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
                "listing",
                Listing.class
        );
    }

    @SessionScope
    public Listing getListingById(int id) throws IOException, InterruptedException {
        return GraphQLService.createObjQuery(
                String.format(
                        GraphQLService.getQueryFromFile( QueryEnum.ListingById.get(), false ),
                        id
                ),
                "listingById",
                Listing.class
        );
    }

    @Override
    public ArrayList<Listing> getListingsByVehicle(String licenseNo) throws IOException, InterruptedException {
        return GraphQLService.createListQuery(
                String.format(
                        GraphQLService.getQueryFromFile( QueryEnum.ListingsByVehicle.get(), false ),
                        licenseNo
                ),
                "listingsByVehicle",
                Listing.class
        );
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
                        listing.vehicle.getLicenseNo()
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
                        listing.getLocation(),
                        listing.getDateFrom(),
                        listing.getDateTo(),
                        listing.vehicle.getLicenseNo()
                ),
                "updateListing",
                Listing.class
        );
    }

    @SessionScope
    public boolean removeListing(int id) throws IOException, InterruptedException {
        return GraphQLService.createBooleanResponse(
                GraphQLService.sendQuery(
                        String.format(
                                GraphQLService.getQueryFromFile( MutationEnum.RemoveListing.get(), true),
                                id
                        )
                ),
                "removeListing"
        );
    }
}
