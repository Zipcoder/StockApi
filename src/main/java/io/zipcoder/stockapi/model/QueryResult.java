package io.zipcoder.stockapi.model;

import java.util.List;

public class QueryResult {
    private MetaData metaData;
    private List<StockResult> stockResults;

    public QueryResult(MetaData metaData, List<StockResult> stockResults) {
        this.metaData = metaData;
        this.stockResults = stockResults;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<StockResult> getStockResults() {
        return stockResults;
    }

    public void setStockResults(List<StockResult> stockResults) {
        this.stockResults = stockResults;
    }
}
