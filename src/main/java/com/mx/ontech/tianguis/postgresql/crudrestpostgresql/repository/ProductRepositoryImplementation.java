package com.mx.ontech.tianguis.postgresql.crudrestpostgresql.repository;

import com.mx.ontech.tianguis.postgresql.crudrestpostgresql.configuration.DBParameters;
import com.mx.ontech.tianguis.postgresql.crudrestpostgresql.models.Products;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImplementation implements ProductRepository {

    private static final Logger log = (Logger) LoggerFactory.getLogger(ProductRepositoryImplementation.class);
    private DataSource ds;

    @Autowired
    private DBParameters db;

    private void configureDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(String.format("jdbc:postgresql:///%s", db.getName()));
        config.setUsername(db.getUser());
        config.setPassword(db.getPass());
        ds = new HikariDataSource(config);
    }

    @Override
    public String create(Products product) {
        String response = "";

        configureDataSource();
        try (Connection con = ds.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement("INSERT INTO products (fName, lName, age) VALUES (?, ?, ?)")) {
                    statement.setString(1, product.getFname());
                    statement.setString(2, product.getlName());
                    statement.setInt(3, product.getAge());
                    int resp = statement.executeUpdate();
                    if (resp != 0) {
                        response = "ok";
                    }
                }
            } catch (SQLException e) {
            log.error("Algo falló en la consulta: {}", e.getMessage());
            response = e.getMessage();
        }
        return response;
    }
}
