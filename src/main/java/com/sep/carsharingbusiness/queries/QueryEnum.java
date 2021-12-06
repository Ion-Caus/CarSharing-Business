package com.sep.carsharingbusiness.queries;

public enum QueryEnum {

    LeaseById("GetLeaseById.graphql"),
    LeasesByListing("GetLeasesByListing.graphql"),
    LeasesByCustomer("GetLeasesByCustomer.graphql"),

    ListingsByLocationAndDates("GetListingByLocationAndDates.graphql"),
    ListingsByVehicle("GetListingsByVehicle.graphql"),
    ListingById("GetListingById.graphql"),

    VehicleByLicenseNo("GetVehicleByLicenseNo.graphql"),
    VehicleByOwnerCpr("GetVehicleByOwnerCpr.graphql"),

    CustomerByCpr("GetCustomerByCpr.graphql"),

    AccountByUsername("GetAccountByUsername.graphql");

    private final String fileName;

    QueryEnum(String fileName) {
        this.fileName = fileName;
    }

    public String get() {
        return fileName;
    }

}
