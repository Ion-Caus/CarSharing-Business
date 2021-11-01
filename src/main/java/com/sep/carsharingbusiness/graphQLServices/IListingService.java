package com.sep.carsharingbusiness.graphQLServices;

import com.sep.carsharingbusiness.model.Listing;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IListingService {
    ArrayList<Listing> getListing(String location, LocalDateTime dateFrom, LocalDateTime dateTo) throws IOException, InterruptedException;
}
