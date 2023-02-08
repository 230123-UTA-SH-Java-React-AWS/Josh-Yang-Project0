package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// This class is responsible for connecting to our database
public class ConnectionUtil {
    // We want only one connection to the database the entire time
    private static Connection con;

    // Private constructor to prevent anyone from making an object
    private ConnectionUtil() {
        con = null;
    }

    // This method will give us a connection to the database
    // OR it will give the existing connection
    public static Connection getConnection() {
        // Determine if we already have a connection and if so give the current connection
        try {
            if (con != null && !con.isClosed()) {
                return con;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String url, user, pass;

        // System.getenv() method will check your System Variables and will find a key that is exactly like what you specified in the parameter
        // Then, it will use the value of the key from System Variables
        url = System.getenv("url");
        user = System.getenv("user");
        pass = System.getenv("pass");

        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("WRONG PASSWORD OR URL");
        }
        return con;
    }
}
