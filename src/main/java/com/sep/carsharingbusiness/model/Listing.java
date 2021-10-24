package com.sep.carsharingbusiness.model;

import java.math.BigDecimal;
import java.util.Date;

public class Listing {
    public Date ListedDate;
    public BigDecimal Price;
    public String Location;
    public Date DateFrom;
    public Date DateTo;

    public String VehicleLicenseNo;

    public Listing(Date listedDate, BigDecimal price, String location, Date dateFrom, Date dateTo, String vehicleLicenseNo) {
        ListedDate = listedDate;
        Price = price;
        Location = location;
        DateFrom = dateFrom;
        DateTo = dateTo;
        VehicleLicenseNo = vehicleLicenseNo;
    }

    public Date getListedDate() {
        return ListedDate;
    }

    public void setListedDate(Date listedDate) {
        ListedDate = listedDate;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        Price = price;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Date getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        DateFrom = dateFrom;
    }

    public Date getDateTo() {
        return DateTo;
    }

    public void setDateTo(Date dateTo) {
        DateTo = dateTo;
    }

    public String getVehicleLicenseNo() {
        return VehicleLicenseNo;
    }

    public void setVehicle(String vehicleLicenseNo) {
        VehicleLicenseNo = vehicleLicenseNo;
    }
}
