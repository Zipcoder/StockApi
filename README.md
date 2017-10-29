# StockAPI
This is the stock API that needs to be used to complete the final project.

Note: This runs on port `8081` so that it doesn't collide with your running project.

Also, *DO NOT* mess with the schema file.  There's 2GB of data in this database.  Rebuilding it is not fun.

The endpoint will end up being:
`http://localhost:8081/query?timeSlice={DAY, WEEK, MONTH}&ticker={some ticker}`

In your query, `timeSlice` must either be `day`, `week`, or `month` depending on how you want your data to be returned,

`ticker` must be a valid stock ticker.

The response will look something like the following, just with more stockResults
(request = `http://localhost:8081/query?timeSlice=week&ticker=A`)
```
{
    "metaData": {
        "timeSlice": "WEEK",
        "ticker": "A",
        "startDay": "2017-10-23",
        "endDate": "1999-12-27"
    },
    "stockResults": [
        {
            "ticker": "A",
            "high": 68.52,
            "low": 66.88,
            "volume": 6241377,
            "day": "2017-10-23",
            "open": 67.45,
            "close": 67.71
        },
        {
            "ticker": "A",
            "high": 67.53,
            "low": 65.99,
            "volume": 7193722,
            "day": "2017-10-16",
            "open": 67.2,
            "close": 67.25
        },...
       ]}
```