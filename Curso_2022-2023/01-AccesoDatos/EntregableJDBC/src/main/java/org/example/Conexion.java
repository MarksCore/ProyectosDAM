package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Conexion {
    private static HikariConfig hikariConfig = new HikariConfig();
    private static HikariDataSource hikariDataSource;

    static {
        hikariConfig.setJdbcUrl("jdbc:sqlserver://localhost:1433;");
        hikariConfig.setUsername("sa");
        hikariConfig.setPassword("ADMIN0");
        hikariConfig.addDataSourceProperty("database", "EntregableJDBC");
        hikariConfig.addDataSourceProperty("encrypt", true);
        hikariConfig.addDataSourceProperty("trustServerCertificate", true);
        hikariConfig.addDataSourceProperty("loginTimeout", "30");
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public static Connection obtenerConexion() throws SQLException {
        return hikariDataSource.getConnection();
    }
}
