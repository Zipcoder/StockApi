package io.zipcoder.stockapi.model;

import java.util.Date;

public class MetaData {
    TimeSlice timeSlice;
    String ticker;
    Date startDay;
    Date endDate;

    public MetaData(TimeSlice timeSlice, String ticker, Date startDay, Date endDate) {
        this.timeSlice = timeSlice;
        this.ticker = ticker;
        this.startDay = startDay;
        this.endDate = endDate;
    }

    public TimeSlice getTimeSlice() {
        return timeSlice;
    }

    public void setTimeSlice(TimeSlice timeSlice) {
        this.timeSlice = timeSlice;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
