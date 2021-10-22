package com.sep.carsharingbusiness.model;

// TODO: 22.10.2021 by Ion: Change the naming to lowercase
public class Vehicle {
    public String LicenseNo;
    public String Brand;
    public String Model;
    public String Type;
    public String Transmission;
    public String FuelType;
    public int Seats;
    public int ManufactureYear;
    public double Mileage;

    public String OwnerCpr;

    public Vehicle(String licenseNo, String brand, String model, String type, String transmission, String fuelType, int seats, int manufactureYear, double mileage, String ownerCpr) {
        LicenseNo = licenseNo;
        Brand = brand;
        Model = model;
        Type = type;
        Transmission = transmission;
        FuelType = fuelType;
        Seats = seats;
        ManufactureYear = manufactureYear;
        Mileage = mileage;
        OwnerCpr = ownerCpr;
    }

    public String getLicenseNo() {
        return LicenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        LicenseNo = licenseNo;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getTransmission() {
        return Transmission;
    }

    public void setTransmission(String transmission) {
        Transmission = transmission;
    }

    public String getFuelType() {
        return FuelType;
    }

    public void setFuelType(String fuelType) {
        FuelType = fuelType;
    }

    public int getSeats() {
        return Seats;
    }

    public void setSeats(int seats) {
        Seats = seats;
    }

    public int getManufactureYear() {
        return ManufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        ManufactureYear = manufactureYear;
    }

    public double getMileage() {
        return Mileage;
    }

    public void setMileage(double mileage) {
        Mileage = mileage;
    }

    public String getOwnerCpr() {
        return OwnerCpr;
    }

    public void setOwnerCpr(String ownerCpr) {
        OwnerCpr = ownerCpr;
    }
}
