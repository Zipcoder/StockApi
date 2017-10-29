package io.zipcoder.stockapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TemporalResult extends StockResult {
    @JsonIgnore
    private Integer year;
    @JsonIgnore
    private Integer temporalValue;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getTemporalValue() {
        return temporalValue;
    }

    public void setTemporalValue(Integer temporalValue) {
        this.temporalValue = temporalValue;
    }
}
