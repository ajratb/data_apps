/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ayrat
 */
public class PostgresStudy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/smsdb","sms",
                        "0c32STVWYUX1Za");
        System.out.println(conn.getMetaData().getUserName());
        conn.close();
    }
    
}
