package com.scienceminer.chatgpt;

import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.client.fluent.Request;
import org.json.JSONObject;

public class StockPriceDownloader {

    private static final String DB_URL = "jdbc:mariadb://192.168.0.159:3306/erc";
    private static final String DB_USER = "collopet";
    private static final String DB_PASSWORD = "richard1";
    private static final String SYMBOL = "AMZN";

    public static void main(String[] args) {
        createTable();
        Timer timer = new Timer();
        timer.schedule(new DownloadTask(), 0, 900000); // Download every 15 minutes
    }

    private static void createTable() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "CREATE TABLE IF NOT EXISTS STOCKS (id INT AUTO_INCREMENT PRIMARY KEY, symbol VARCHAR(10) NOT NULL, price DOUBLE NOT NULL, timestamp TIMESTAMP NOT NULL)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class DownloadTask extends TimerTask {
        @Override
        public void run() {
            try {
                String url = String.format("https://finance.yahoo.com/quote/%s", SYMBOL);
                String response = Request.Get(url).execute().returnContent().asString();
                //JSONObject data = new JSONObject(response.split("root.App.main = ")[1].split("\\}\\}\\};")[0]).getJSONObject("context").getJSONObject("dispatcher").getJSONObject("stores").getJSONObject("StreamDataStore").getJSONObject("quoteData").getJSONObject(SYMBOL);
                JSONObject data = new JSONObject(response.split("root.App.main = ")[1].split("\\}\\}\\};")[0]).getJSONObject("context").getJSONObject("dispatcher").getJSONObject("quoteData").getJSONObject(SYMBOL);
                double price = data.getDouble("regularMarketPrice");
                long timestamp = System.currentTimeMillis();

                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    String sql = "INSERT INTO STOCKS (symbol, price, timestamp) VALUES (?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, SYMBOL);
                    pstmt.setDouble(2, price);
                    pstmt.setTimestamp(3, new Timestamp(timestamp));
                    pstmt.executeUpdate();
                }
                System.out.println("Downloaded stock price for " + SYMBOL + ": " + price);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
