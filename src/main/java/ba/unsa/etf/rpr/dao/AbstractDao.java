package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;

import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * Abstract class that implements CRUD methods
 */

public abstract class AbstractDao<T extends Idable> implements  Dao<T>{
    private static Connection connection = null;
    private String tableName;

    public AbstractDao(String tableName){
        try {
            this.tableName = tableName;
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("database.properties").openStream());
            this.connection = DriverManager.getConnection(p.getProperty("db.connection_string"), p.getProperty("db.username"), p.getProperty("db.password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
