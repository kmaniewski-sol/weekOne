import java.sql.*;

class StockQueries {
    private static DBConnection dbConnection = DBConnection.getInstance();
    private static Connection conn = dbConnection.getConnection();

    static double maxPrice(String symbol, String date){
    try {
        String maxQuery = "SELECT MAX(price) as price FROM stocks WHERE symbol = ?" +
                " AND DATE(timestamp) = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(maxQuery);
        preparedStatement.setString(1, symbol);
        preparedStatement.setString(2, date);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getDouble("price");
    } catch (SQLException sqlE){
        return 0.0;
    }
}
    static double minPrice(String symbol, String date){
        try {
            String minQuery = "SELECT MIN(price) as price FROM stocks WHERE symbol = ?" +
                    " AND DATE(timestamp) = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(minQuery);
            preparedStatement.setString(1, symbol);
            preparedStatement.setString(2, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getDouble("price");
        } catch (SQLException sqlE){
            return 0.0;
        }
    }

    static int sumVolume(String symbol, String date){
        try {
            String sumQuery = "SELECT SUM(volume) as volume FROM stocks WHERE symbol = ?" +
                    " AND DATE(timestamp) = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sumQuery);
            preparedStatement.setString(1, symbol);
            preparedStatement.setString(2, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("volume");
        } catch (SQLException sqlE){
            return 0;
        }
    }

    static double closingPrice(String symbol, String date){
        try {
            String closeQuery = "SELECT * FROM stocks WHERE symbol = ? AND DATE(timestamp) = ?" +
                    " ORDER BY timestamp DESC LIMIT 1";
            PreparedStatement preparedStatement = conn.prepareStatement(closeQuery);
            preparedStatement.setString(1, symbol);
            preparedStatement.setString(2, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getDouble("price");
        } catch (SQLException sqlE){
            return 0;
        }
    }

    static double maxMonthPrice(String symbol, String date){
        try {
            String maxMonthQuery = "SELECT MAX(price) as price FROM stocks WHERE symbol = ?" +
                    " AND MONTH(DATE(timestamp)) = MONTH(?)";
            PreparedStatement preparedStatement = conn.prepareStatement(maxMonthQuery);
            preparedStatement.setString(1, symbol);
            preparedStatement.setString(2, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getDouble("price");
        } catch (SQLException sqlE){
            return 0.0;
        }
    }
    static double minMonthPrice(String symbol, String date){
        try {
            String minMonthQuery = "SELECT MIN(price) as price FROM stocks WHERE symbol = ?" +
                    " AND MONTH(DATE(timestamp)) = MONTH(?)";
            PreparedStatement preparedStatement = conn.prepareStatement(minMonthQuery);
            preparedStatement.setString(1, symbol);
            preparedStatement.setString(2, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getDouble("price");
        } catch (SQLException sqlE){
            return 0.0;
        }
    }

    static int sumMonthVolume(String symbol, String date){
        try {
            String sumMonthQuery = "SELECT SUM(volume) as volume FROM stocks WHERE symbol = ?" +
                    " AND MONTH(DATE(timestamp)) = Month(?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sumMonthQuery);
            preparedStatement.setString(1, symbol);
            preparedStatement.setString(2, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("volume");
        } catch (SQLException sqlE){
            return 0;
        }
    }
}
