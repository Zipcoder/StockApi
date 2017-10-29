package io.zipcoder.stockapi.model;

public enum TimeSlice {
    DAY("DAY"),
    WEEK("WEEK"),
    MONTH("MONTH");

    private final String timeString;

    TimeSlice(final String timeString) {
        this.timeString = timeString;
    }

    public static boolean contains(String s) {
        for(TimeSlice ts : values()) {
            if(ts.timeString.equals(s.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public static String getTimeString(TimeSlice ts) {
        return ts.timeString;
    }

    @Override
    public String toString() {
        return timeString;
    }
}
