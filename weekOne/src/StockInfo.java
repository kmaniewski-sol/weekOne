import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class StockInfo {
    private static DBConnection dbConnection = DBConnection.getInstance();
    private static Connection conn = dbConnection.getConnection();

    private StockInfo(){}

    static void dailyView(String date){
        try {
            String query = "SELECT DISTINCT symbol FROM stocks";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
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
        System.out.println("Stock: " + symbol);
        System.out.println("Date: " + date);
        System.out.println("Highest Price: " + maxPrice);
        System.out.println("Lowest Price: " + minPrice);
        System.out.println("Total Volume: " + sumVolume);
        System.out.println("Closing Price: " + closingPrice);
        System.out.println("Highest Price of Month: " + maxMonthPrice);
        System.out.println("Lowest Price of Month: " + minMonthPrice);
        System.out.println("Total Volume of Month: " + sumMonthVolume);
    }
}
