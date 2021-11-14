package com.sep.carsharingbusiness.graphQLServices.serviceImpl;


import com.sep.carsharingbusiness.graphQLServices.IListingService;
import com.sep.carsharingbusiness.model.Listing;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class ListingService implements IListingService {


    public ArrayList<Listing> getListing(String location, LocalDateTime dateFrom, LocalDateTime dateTo) throws IOException, InterruptedException {
        return GraphQLService.createListQuery(
                String.format(
                        GraphQLService.getQueryFromFile("GetListingByLocationAndDates.graphql"),
                        location,
                        dateFrom,
                        dateTo
                ),
                "listing"
        );
    }
}
