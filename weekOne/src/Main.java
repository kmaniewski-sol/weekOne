
public class Main {

    public static void main(String[] args){
        String address = "https://bootcamp-training-files.cfapps.io/week1/week1-stocks.json";
        HandleURL.URLtoMySql(address);
        String date = "2018-06-22";
        StockInfo.dailyView(date);
    }
}
