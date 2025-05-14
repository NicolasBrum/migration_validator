package Database;

import Exceptions.DbException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection postgresConnection = null;
    private static Connection sqlServerConnection = null;

    public static Connection getPostgresConnection() {
        if(postgresConnection == null){
            try {
                var props = loadPostgresProperties();
                var url = props.getProperty("dburl");
                postgresConnection = DriverManager.getConnection(url,props);
            }catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return postgresConnection;
    }

    public static Connection getSqlServerConnection() {
        if(sqlServerConnection == null){
            try {
                var props = loadSqlServerProperties();
                var url = props.getProperty("dburl");
                sqlServerConnection = DriverManager.getConnection(url,props);
            }catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return sqlServerConnection;
    }

    public static void closeConnections () {
        try {
            if(postgresConnection != null){
                postgresConnection.close();
            }
            if(sqlServerConnection != null){
                sqlServerConnection.close();
            }
        }catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    private static Properties loadSqlServerProperties(){
        try(FileInputStream fs = new FileInputStream("mssql.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        }
        catch(IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    private static Properties loadPostgresProperties(){
        try(FileInputStream fs = new FileInputStream("pg.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        }
        catch(IOException e) {
            throw new DbException(e.getMessage());
        }
    }
}
