package com.sep.carsharingbusiness.graphQLServices.serviceImpl;

import com.sep.carsharingbusiness.graphQLServices.ILeaseService;
import com.sep.carsharingbusiness.model.Lease;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class LeaseService implements ILeaseService {

    // TODO: 03.12.2021 by Ion - Create the Queries and Mutations
    //      and send the requests
    @SessionScope
    public ArrayList<Lease> getLeasesByListing(int listingId) throws IOException, InterruptedException {
        return null;
    }

    @SessionScope
    public Lease getLeaseById(int id) throws IOException, InterruptedException {
        return null;
    }

    @SessionScope
    public ArrayList<Lease> getLeasesByCustomer(String cpr) throws IOException, InterruptedException {
        return null;
    }

    @SessionScope
    public Lease addLease(Lease Lease) throws IOException, InterruptedException {
        return null;
    }

    @SessionScope
    public Lease updateLease(Lease Lease) throws IOException, InterruptedException {
        return null;
    }

    @SessionScope
    public boolean removeLease(int id) throws IOException, InterruptedException {
        return false;
    }
}
