package io.zipcoder.stockapi.model;

import java.util.Date;

public class StockResult {
    private String ticker;
    private Double high;
    private Double low;
    private Double volume;
    private Date day;
    private Double open;
    private Double close;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public static StockResult buildFromTemporal(TemporalResult tr) {
        StockResult sr = new StockResult();
        sr.setTicker(tr.getTicker());
        sr.setHigh(tr.getHigh());
        sr.setLow(tr.getLow());
        sr.setVolume(tr.getVolume());
        return sr;
    }
}
