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

public class ClickHouseConnection {

    public Connection connect() {
        String url = "jdbc:clickhouse://localhost:8123/tutorial";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "", "");
            System.out.println("Connected to the ClickHouse server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public void liquibase() throws Exception{
        Map<String, Object> config = new HashMap<>();
//        config.put("liquibase.url", "jdbc:clickhouse://localhost:8123/default");
        config.put("liquibase.url", "jdbc:clickhouse://localhost:8123");
        config.put("driver", "com.clickhouse.jdbc.ClickHouseDriver");
        config.put("databaseClass", "liquibase.ext.clickhouse.database.ClickHouseDatabase");
        config.put("strict", "true");
//        config.put("defaultSchemaName", "default");
//        config.put("liquibaseSchemaName", "default");

        Scope.child(config, () -> {
            Connection connection = connect(); //your openConnection logic

            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
//            database.setLiquibaseSchemaName("default");
            database.setDefaultSchemaName("DUMMY");
//            database.setDatabaseChangeLogTableName("NEWCHANGE");
            Liquibase liquibase = new liquibase.Liquibase("db/clickhouse/00-registry.yaml", new ClassLoaderResourceAccessor(), database);
            liquibase.update(new Contexts(), new LabelExpression());
            //Liquibase calls will go here
        });
    }

    public static void main(String[] args) throws Exception {
        ClickHouseConnection app = new ClickHouseConnection();
        Connection connect = app.connect();
        PreparedStatement st = connect.prepareStatement("select * from simple");
        ResultSet resultSet = st.executeQuery();
        boolean next = resultSet.next();
        String string = resultSet.getString(3);
        System.out.println("end: " + string);
        connect.close();
        app.liquibase();
    }
}
