package com.sep.carsharingbusiness.model;

import java.math.BigDecimal;
import java.util.Date;

public class Listing {
    private Date listedDate;
    private BigDecimal price;
    private String location;
    private Date dateFrom;
    private Date dateTo;

    public Vehicle vehicle;

    public Listing(Date listedDate, BigDecimal price, String location, Date dateFrom, Date dateTo, Vehicle vehicle) {
        this.listedDate = listedDate;
        this.price = price;
        this.location = location;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.vehicle = vehicle;
    }

    public Date getListedDate() {
        return listedDate;
    }

    public void setListedDate(Date listedDate) {
        this.listedDate = listedDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
