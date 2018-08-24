class StockObject {
    private String symbol;
    private String date;
    private double maxPrice;
    private double minPrice;
    private int sumVolume;
    private double closingPrice;
    private double maxMonthPrice;
    private double minMonthPrice;
    private int sumMonthVolume;

    StockObject(String sym, String dateI, double maxP, double minP, int sumV,
                double closeP, double maxMP, double minMP, int sumMV){
        symbol = sym;
        date = dateI;
        maxPrice = maxP;
        minPrice = minP;
        sumVolume = sumV;
        closingPrice = closeP;
        maxMonthPrice = maxMP;
        minMonthPrice = minMP;
        sumMonthVolume = sumMV;
    }

    void getStockInfo(){
        System.out.println("Stock: " + symbol);
        System.out.println("Date: " + date);
        System.out.println("Highest Price: " + maxPrice);
        System.out.println("Lowest Price: " + minPrice);
        System.out.println("Total Volume: " + sumVolume);
        System.out.println("Closing Price: " + closingPrice);
        System.out.println("Highest Price of Month: " + maxMonthPrice);
        System.out.println("Lowest Price of Month: " + minMonthPrice);
        System.out.println("Total Volume of Month: " + sumMonthVolume);
        System.out.println("---------------------------------------");
    }
}
