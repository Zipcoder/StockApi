package io.zipcoder.stockapi.service;

import io.zipcoder.stockapi.model.MetaData;
import io.zipcoder.stockapi.model.QueryResult;
import io.zipcoder.stockapi.model.StockResult;
import io.zipcoder.stockapi.model.TimeSlice;
import io.zipcoder.stockapi.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public QueryResult getStocksWithTimeSlice(TimeSlice timeSlice, String ticker) {
        List<StockResult> stocks;
        switch(timeSlice) {
            case DAY:
                stocks = stockRepository.getDailyResults(ticker);
                break;
            case WEEK:
                stocks = stockRepository.getResultsWithTime(ticker, TimeSlice.WEEK);
                break;
            case MONTH:
                stocks = stockRepository.getResultsWithTime(ticker, TimeSlice.MONTH);
                break;
            default:
                throw new IllegalArgumentException("Invalid timeslice");
        }

        if(stocks.isEmpty()) {
            throw new IllegalArgumentException("'" + ticker + "' is not a valid ticker.");
        }
        MetaData metadata = new MetaData(timeSlice, ticker, stocks.get(0).getDay(), stocks.get(stocks.size()-1).getDay());
        return new QueryResult(metadata, stocks);
    }

}