package com.example;

import java.sql.*;

public class ClickHouseConnection {

    private final String url = "jdbc:clickhouse://localhost/tutorial";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "", "");
            System.out.println("Connected to the ClickHouse server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public static void main(String[] args) throws Exception {
        ClickHouseConnection app = new ClickHouseConnection();
        Connection connect = app.connect();
        PreparedStatement st = connect.prepareStatement("select * from simple");
        ResultSet resultSet = st.executeQuery();
        boolean next = resultSet.next();
        String string = resultSet.getString(3);
        System.out.println("end");
        connect.close();
    }
}
