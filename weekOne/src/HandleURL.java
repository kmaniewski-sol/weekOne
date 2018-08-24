import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class HandleURL {

    private HandleURL() {}

    static void URLtoMySql(String address){
        String websiteContents = transformURL(address);
        if(websiteContents != null) {
            readJSONtoMySQL(websiteContents);
        } else {
            System.out.println("Issue with provided URL address.");
        }
    }

    private static String transformURL(String address) {
        try {
            URL url = new URL(address);
            URLConnection connection = url.openConnection();
            BufferedReader readerIn = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder websiteContents = new StringBuilder();
            String currentLine = readerIn.readLine();
            while (currentLine != null) {
                websiteContents.append(currentLine);
                currentLine = readerIn.readLine();
            }
            readerIn.close();
            return websiteContents.toString();
        } catch (IOException ioE) {
            return null;
        }
    }

    private static void readJSONtoMySQL(String websiteContents) {
        try {
            DBConnection dbConnection = DBConnection.getInstance();
            Connection conn = dbConnection.getConnection();
            String query = "insert into stocks (symbol, price, volume, timestamp) values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            JSONArray jsonArray = new JSONArray(websiteContents);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                preparedStatement.setString(1, object.getString("symbol"));
                preparedStatement.setDouble(2, object.getDouble("price"));
                preparedStatement.setInt(3, object.getInt("volume"));
                preparedStatement.setTimestamp(4, convertTimeStamp(object.getString("date")));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            conn.commit();
        } catch (JSONException jsonE) {
            System.out.println("Issue with JSON");
        } catch (SQLException sqlE) {
            System.out.println("Issue with SQL");
        }
    }

    private  static Timestamp convertTimeStamp(String longDate){
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+'SSSS");
            Date date = dateFormat.parse(longDate);
            return new java.sql.Timestamp(date.getTime());
        } catch (ParseException pE) {
            return null;
        }
    }
}
