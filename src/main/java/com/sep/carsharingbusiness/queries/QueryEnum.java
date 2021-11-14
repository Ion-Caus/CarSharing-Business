package com.sep.carsharingbusiness.queries;

public enum QueryEnum {

    ListingsByLocationAndDates("GetListingByLocationAndDates.graphql"),
    VehicleByLicenseNo("GetVehicleByLicenseNo.graphql");

    private final String fileName;

    QueryEnum(String fileName) {
        this.fileName = fileName;
    }

    public String get() {
        return fileName;
    }

}
