package com.sep.carsharingbusiness.graphQLServices.serviceImpl;


import com.sep.carsharingbusiness.graphQLServices.IListingService;
import com.sep.carsharingbusiness.model.Listing;
import com.sep.carsharingbusiness.queries.QueryEnum;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class ListingService implements IListingService {


    @SessionScope
    public ArrayList<Listing> getListing(String location, LocalDateTime dateFrom, LocalDateTime dateTo) throws IOException, InterruptedException {
        return GraphQLService.createListQuery(
                String.format(
                        GraphQLService.getQueryFromFile( QueryEnum.ListingsByLocationAndDates.get() ),
                        location,
                        dateFrom,
                        dateTo
                ),
                "listing"
        );
    }
}
