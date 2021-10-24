package com.sep.carsharingbusiness.mediator;

import java.time.LocalDateTime;

public class FilterParam {
    public String Location;
    public LocalDateTime DateFrom;
    public LocalDateTime DateTo;

    public FilterParam(String location, LocalDateTime dateFrom, LocalDateTime dateTo) {
        Location = location;
        DateFrom = dateFrom;
        DateTo = dateTo;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public LocalDateTime getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(LocalDateTime dateFrom) {
        DateFrom = dateFrom;
    }

    public LocalDateTime getDateTo() {
        return DateTo;
    }

    public void setDateTo(LocalDateTime dateTo) {
        DateTo = dateTo;
    }
}
