package com.example;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.Scope;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PostgresConnection {

    private final String url = "jdbc:postgresql://localhost/testdb";
    private final String user = "postgres";
    private final String password = "h3EHJi";


    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public void liquibase() throws Exception{
        Map<String, Object> config = new HashMap<>();
        config.put("liquibase.url", "jdbc:postgresql://localhost:5432/testdb");
        config.put("liquibase.username", "postgres");
        config.put("liquibase.password", "h3EHJi");

        Scope.child(config, () -> {
            Connection connection = connect(); //your openConnection logic

            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

            Liquibase liquibase = new liquibase.Liquibase("changelog.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update(new Contexts(), new LabelExpression());
            //Liquibase calls will go here
        });
    }

    public static void main(String[] args) throws Exception {
        PostgresConnection app = new PostgresConnection();
        Connection connect = app.connect();
        PreparedStatement st = connect.prepareStatement("select * from faq.databasechangelog");
        ResultSet resultSet = st.executeQuery();
        boolean next = resultSet.next();
        String string = resultSet.getString(1);
        System.out.println("end");
        connect.close();
        app.liquibase();
    }
}
