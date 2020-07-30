package com.gudyna.bookproject.model.sqlconnect;

import com.gudyna.bookproject.model.exception.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SqlConnector {
    private static final String HOST =
            "jdbc:mysql://localhost:3306/book_warehouse";
    private static final Properties PROPERTIES;

    static {
        PROPERTIES = new Properties();
        PROPERTIES.put("user", "root");
        PROPERTIES.put("password", "70agotuz");
        PROPERTIES.put("autoreconnect", "true");
        PROPERTIES.put("characterEncoding", "UTF-8");
        PROPERTIES.put("useUnicode", "true");
        PROPERTIES.put("serverTimezone", "UTC");
        PROPERTIES.put("verifyServerCertificate", "false");
        PROPERTIES.put("useSSL", "false");
        PROPERTIES.put("requireSSL", "false");
        PROPERTIES.put("allowPublicKeyRetrieval","true");
    }

    private SqlConnector() {
    }

    public static Connection connect() throws DAOException {
        try {
            return DriverManager.getConnection(HOST, PROPERTIES);
        } catch (SQLException e) {
            throw new DAOException("Unable to get connection!", e);
        }
    }
}
