import java.sql.*;

class StockInfo {
    private static DBConnection dbConnection = DBConnection.getInstance();
    private static Connection conn = dbConnection.getConnection();

    private StockInfo(){}

    static void dailyView(String date){
        try {
            String query = "SELECT DISTINCT symbol FROM stocks";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String symbol = resultSet.getString("symbol");
                stockInfo(symbol, date);
            }
        }
        catch (SQLException sqlE){
            System.out.println("Issue with SQL");
        }
    }

    private static void stockInfo(String symbol, String date){
        double maxPrice = StockQueries.maxPrice(symbol, date);
        double minPrice = StockQueries.minPrice(symbol, date);
        int sumVolume = StockQueries.sumVolume(symbol, date);
        double closingPrice = StockQueries.closingPrice(symbol, date);
        double maxMonthPrice = StockQueries.maxMonthPrice(symbol, date);
        double minMonthPrice = StockQueries.minMonthPrice(symbol, date);
        int sumMonthVolume = StockQueries.sumMonthVolume(symbol, date);
        StockObject stockObject = new StockObject(symbol, date,
                maxPrice, minPrice, sumVolume, closingPrice,
                maxMonthPrice, minMonthPrice, sumMonthVolume);
        stockObject.getStockInfo();
    }
}
