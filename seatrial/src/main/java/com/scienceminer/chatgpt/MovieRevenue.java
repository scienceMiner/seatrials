package com.scienceminer.chatgpt;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MovieRevenue {

    public static void main(String[] args) {
        String url = "jdbc:mariadb://192.168.0.159:3306/erc";
        String user = "collopet";
        String password = "richard1";

        try {
            // Extract table data using Jsoup
         //   Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_highest-grossing_films").get();
       //     Element table = doc.select("table.wikitable").get(0); // Select the first table with class 'wikitable'
        //    Elements rows = table.select("tr");
            
            Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_highest-grossing_films").get();
            Element table = null;
            Elements captions = doc.select("caption");
            for (Element caption : captions) {
            	System.out.println(caption.text());
                if (caption.text().contains("Highest-grossing films")) {
                    table = caption.parent();
                    break;
                }
            }
            if (table == null) {
                throw new RuntimeException("Table with caption 'Highest-grossing films' not found");
            }
            Elements rows = table.select("tr");

            // Create JDBC connection
            Connection conn = DriverManager.getConnection(url, user, password);

            // Create table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS MovieRevenue ("
                    + "Rank INT(10) NOT NULL, "
                    + "Peak INT(10) NOT NULL, "
                    + "Title VARCHAR(255) NOT NULL, "
                    + "WorldwideGross VARCHAR(255) NOT NULL, "
                    + "Year INT(10) NOT NULL, "
                    + "PRIMARY KEY (Rank)"
                    + ")";
            PreparedStatement createTableStmt = conn.prepareStatement(createTableSQL);
            createTableStmt.execute();

            // Insert data into table
            String insertSQL = "INSERT INTO MovieRevenue (Rank, Peak, Title, WorldwideGross, Year) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertSQL);

            for (int i = 1; i < rows.size(); i++) { // Start at 1 to skip header row
                Element row = rows.get(i);
                Elements cols = row.select("td,th");
System.out.println(cols.size());
System.out.println(cols.get(2).text());
System.out.println(cols.get(5).text());

                if (cols.size() == 6) { // Ignore rows without 6 columns
                    int rank = Integer.parseInt(cols.get(0).text());
                    int peak = Integer.parseInt(cols.get(1).text());
                    String title = cols.get(2).text();
                    String worldwideGross = cols.get(3).text().replaceAll(",", ""); // Remove commas from number
                    int year = Integer.parseInt(cols.get(4).text());
                    System.out.println(rank);
                    System.out.println(peak);
                    System.out.println(title);
                    System.out.println(worldwideGross);
                    System.out.println(year);

                    insertStmt.setInt(1, rank);
                    insertStmt.setInt(2, peak);
                    insertStmt.setString(3, title);
                    insertStmt.setString(4, worldwideGross);
                    insertStmt.setInt(5, year);
                    insertStmt.execute();
                }
            }

            // Close JDBC resources
            insertStmt.close();
            createTableStmt.close();
            conn.close();

            System.out.println("Table data extracted and inserted into MovieRevenue table.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
