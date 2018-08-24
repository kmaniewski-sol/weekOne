import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConnection {
    private static DBConnection uniqueInstance;
    private static Connection conn;

    private DBConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/stock_collection?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "root");
            conn.setAutoCommit(false);
        } catch (SQLException sqlE){
            System.out.println("Issue with SQL");
        } catch (ClassNotFoundException cnfE){
            System.out.println("Driver Class for JDBC was not found.");
        }
    }

    static synchronized DBConnection getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new DBConnection();
        }
        return uniqueInstance;
    }

    Connection getConnection(){
        return conn;
    }
}
