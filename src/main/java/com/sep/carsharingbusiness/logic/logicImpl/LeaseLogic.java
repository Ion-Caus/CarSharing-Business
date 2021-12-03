package com.sep.carsharingbusiness.logic.logicImpl;

import com.sep.carsharingbusiness.graphQLServices.ILeaseService;
import com.sep.carsharingbusiness.logic.ILeaseLogic;
import com.sep.carsharingbusiness.model.Lease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class LeaseLogic implements ILeaseLogic {
    private final ILeaseService leaseService;
    private final DateTimeFormatter formatter;

    @Autowired
    public LeaseLogic(ILeaseService leaseService) {
        this.leaseService = leaseService;
        this.formatter = DateTimeFormatter.ofPattern("yyyy MMM dd HH:mm");
    }

    @SessionScope
    public Lease getLeaseById(int id) throws IOException, InterruptedException {
        return leaseService.getLeaseById(id);
    }

    @SessionScope
    public ArrayList<Lease> getLeasesByListing(int listingId) throws IOException, InterruptedException {
        return leaseService.getLeasesByListing(listingId);
    }

    @SessionScope
    public ArrayList<Lease> getLeasesByCustomer(String cpr) throws IOException, InterruptedException {
        return leaseService.getLeasesByCustomer(cpr);
    }

    private boolean isValidLeasingDates(Lease lease, Lease newLease) {
        // TODO: 03.12.2021 By Ion Check the intervals.
        return true;
    }

    @SessionScope
    public Lease addLease(Lease lease) throws IOException, InterruptedException, IllegalArgumentException {
        // TODO: 03.12.2021 By Ion Check if the Listing/Vehicle is available in that specific interval
        // get all the leases for that listing
        // check the datetime From and To
        ArrayList<Lease> leasesForThisListing = leaseService.getLeasesByListing(lease.listing.getId());
        for (Lease l : leasesForThisListing) {
            if (!isValidLeasingDates(l, lease)) {
                throw new IllegalArgumentException(
                        String.format(
                                "The listing of the vehicle with licenseNo of %s is not available in the interval of '%s' and '%s'",
                                lease.listing.vehicle.getLicenseNo(),
                                lease.getLeasedFrom().format(formatter),
                                lease.getLeasedTo().format(formatter)
                        ));
            }
        }
        return null;
    }

    @SessionScope
    public boolean cancelLease(int id) throws IOException, InterruptedException {
        try {
            Lease lease = leaseService.getLeaseById(id);
            lease.setCanceled(true);
            leaseService.updateLease(lease);
            return true;
        } catch (InternalError e) {
            return false;
        }

    }
}
