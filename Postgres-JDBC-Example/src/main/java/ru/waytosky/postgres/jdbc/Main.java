/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.postgres.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ayrat
 */
public class Main {
    public static void main(String[] args) throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:postgresql:"
                + "//localhost:5432/testdb", "test", "test");
        System.out.println("Connection: "+conn.getMetaData());
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from books");
        while(rs.next()){
            System.out.println(rs.getString(1));
        }
        rs.close();
        st.close();
        conn.close();
    }
}
