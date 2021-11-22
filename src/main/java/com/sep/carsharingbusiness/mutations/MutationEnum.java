package com.sep.carsharingbusiness.mutations;

public enum MutationEnum {
    AddVehicle("AddVehicle.graphql"),
    UpdateVehicle("UpdateVehicle.graphql"),
    RemoveVehicle("RemoveVehicle.graphql"),

    AddListing("AddListing.graphql"),
    UpdateListing("UpdateListing.graphql"),
    RemoveListing("RemoveListing.graphql"),

    AddCustomer("AddCustomer.graphql"),
    UpdateCustomer("UpdateCustomer.graphql"),
    RemoveCustomer("RemoveCustomer.graphql"),

    AddAccount("AddAccount.graphql");

    private final String fileName;

    MutationEnum(String fileName) {
        this.fileName = fileName;
    }

    public String get() {
        return fileName;
    }
}
