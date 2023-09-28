package com.scienceminer.chatgpt;

import java.sql.*;

/**
 * A Java MySQL SELECT statement example.
 * Demonstrates the use of a SQL SELECT statement against a
 * MySQL database, called from a Java program.
 * 
 * Created by Alvin Alexander, http://alvinalexander.com
 */
public class TableQuery
{

  public static void main(String[] args)
  {
    try
    {
      // create our mysql database connection
      String myDriver = "org.mariadb.jdbc.Driver";
      String myUrl = "jdbc:mariadb://192.168.0.159:3306/erc";
      Class.forName(myDriver);
    //  Connection conn = DriverManager.getConnection(myUrl, "collopet", "richard1");
      
      Connection conn = DriverManager.getConnection("jdbc:mariadb://192.168.0.159:3306/erc?user=collopet&password=richard1");
      
      // our SQL SELECT query. 
      // if you only need a few columns, specify them by name instead of using "*"
      String query = "SELECT * FROM MOVIES";

      // create the java statement
      Statement st = conn.createStatement();	
      
      // execute the query, and get a java resultset
      ResultSet rs = st.executeQuery(query);
      
      // iterate through the java resultsetß
      while (rs.next())
      {
       // int id = rs.getInt("id");
        String firstName = rs.getString("Director");
        String lastName = rs.getString("Title");
        //Date dateCreated = rs.getDate("date_created");
        //boolean isAdmin = rs.getBoolean("is_admin");
       // int numPoints = rs.getInt("num_points");
        
        // print the results
        System.out.format("%s, %s\n", firstName, lastName);
      }
      st.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }
  }
}