package io.zipcoder.stockapi.repository;

import io.zipcoder.stockapi.model.StockResult;
import io.zipcoder.stockapi.model.TemporalResult;
import io.zipcoder.stockapi.model.TimeSlice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("stockRepository")
public class StockRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<StockResult> getDailyResults(String ticker) {
        String query = "SELECT * FROM stocks WHERE ticker = ? ORDER BY day DESC";
        return jdbcTemplate.query(query, new RowMapper<StockResult>() {
            @Override
            public StockResult mapRow(ResultSet resultSet, int i) throws SQLException {
                StockResult stockResult = new StockResult();
                stockResult.setTicker(resultSet.getString("ticker"));
                stockResult.setHigh(resultSet.getDouble("high"));
                stockResult.setLow(resultSet.getDouble("low"));
                stockResult.setVolume(resultSet.getDouble("volume"));
                stockResult.setDay(resultSet.getDate("day"));
                stockResult.setOpen(resultSet.getDouble("open"));
                stockResult.setClose(resultSet.getDouble("close"));
                return stockResult;
            }
        }, ticker);
    }

    public List<StockResult> getResultsWithTime(String ticker, TimeSlice timeSlice) {
        List<TemporalResult> temporalResults = getTemporalResult(ticker, timeSlice);
        List<StockResult> results = new ArrayList<>();
        for(TemporalResult tr : temporalResults) {
            results.add(getStockResultFromTemporal(tr, timeSlice));
        }
        return results;
    }

    private List<TemporalResult> getTemporalResult(String ticker, TimeSlice timeSlice) {
        String queryToGetBaseValues =
                "SELECT ticker, MAX(high) high, MIN(low) low, SUM(volume) volume, YEAR(day) year, " +
                        timeSlice.toString() + "(day) timeSlice " +
                        "FROM stocks " +
                        "WHERE ticker = ? " +
                        "GROUP BY YEAR(day), " + timeSlice.toString() +"(day) " +
                        "ORDER BY YEAR(day) DESC, " + timeSlice.toString() + "(day) DESC";

        List<TemporalResult> temporalResults = jdbcTemplate.query(queryToGetBaseValues, new RowMapper<TemporalResult>() {
            @Override
            public TemporalResult mapRow(ResultSet resultSet, int i) throws SQLException {
                TemporalResult tr = new TemporalResult();
                tr.setTicker(resultSet.getString("ticker"));
                tr.setHigh(resultSet.getDouble("high"));
                tr.setLow(resultSet.getDouble("low"));
                tr.setVolume(resultSet.getDouble("volume"));
                tr.setYear(resultSet.getInt("year"));
                tr.setTemporalValue(resultSet.getInt("timeSlice"));
                return tr;
            }
        }, ticker);

        return temporalResults;
    }

    private StockResult getStockResultFromTemporal(TemporalResult tr, TimeSlice timeSlice) {
        String dayOpenQuery = "SELECT day, open FROM stocks " +
                "WHERE ticker = ? " +
                "AND YEAR(day) = ? " +
                "AND " + timeSlice.toString() + "(day) = ? " +
                "ORDER BY day ASC " +
                "LIMIT 1";

        String closeQuery = "SELECT close FROM stocks " +
                "WHERE ticker = ? " +
                "AND YEAR(day) = ? " +
                "AND " + timeSlice.toString() + "(day) = ? " +
                "ORDER BY day DESC " +
                "LIMIT 1";

        jdbcTemplate.query(dayOpenQuery, new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                tr.setOpen(resultSet.getDouble("open"));
                tr.setDay(resultSet.getDate("day"));
                return tr;
            }
        }, tr.getTicker(), tr.getYear(), tr.getTemporalValue());

        Double close = jdbcTemplate.queryForObject(closeQuery, Double.class, tr.getTicker(), tr.getYear(), tr.getTemporalValue());
        tr.setClose(close);
        return tr;
    }
}
