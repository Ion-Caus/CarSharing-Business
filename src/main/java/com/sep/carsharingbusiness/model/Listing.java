package com.sep.carsharingbusiness.model;

import java.math.BigDecimal;
import java.util.Date;

public class Listing {
    public Date ListedDate;
    public BigDecimal Price;
    public String Location;
    public Date DateFrom;
    public Date DateTo;

    public Vehicle Vehicle;

    public Listing(Date listedDate, BigDecimal price, String location, Date dateFrom, Date dateTo, Vehicle vehicle) {
        ListedDate = listedDate;
        Price = price;
        Location = location;
        DateFrom = dateFrom;
        DateTo = dateTo;
        Vehicle = vehicle;
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

    public Vehicle getVehicle() {
        return Vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        Vehicle = vehicle;
    }
}
