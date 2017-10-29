package io.zipcoder.stockapi.controller;

import io.zipcoder.stockapi.model.BadInputException;
import io.zipcoder.stockapi.model.QueryResult;
import io.zipcoder.stockapi.model.TimeSlice;
import io.zipcoder.stockapi.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
public class StockController {
    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseEntity<QueryResult> query(@RequestParam(value = "timeSlice") String timeSlice,
                                             @RequestParam(value = "ticker") String ticker) {
        if(!TimeSlice.contains(timeSlice)) {
            throw new IllegalArgumentException("'timeSlice' must be in " + Arrays.toString(TimeSlice.values()));
        } else {
            TimeSlice ts = TimeSlice.valueOf(timeSlice.toUpperCase());
            return new ResponseEntity<QueryResult>(stockService.getStocksWithTimeSlice(ts, ticker.toUpperCase()), HttpStatus.OK);
        }
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<BadInputException> handleBadInput(Exception e) {
        return new ResponseEntity<>(new BadInputException(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
