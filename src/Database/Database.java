package Database;

import Exceptions.DatabaseException;
import Helpers.SupportedDatabases;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

    private static Connection postgresConnection = null;
    private static Connection sqlServerConnection = null;

    public static Connection getPostgresConnection() {
        if(postgresConnection == null){
            try {
                var props = loadProperties(SupportedDatabases.POSTGRES.getFileProperties());
                var url = props.getProperty("dburl");
                postgresConnection = DriverManager.getConnection(url,props);
            }catch (SQLException e) {
                System.out.println("could not get connection.");
            }
        }
        return postgresConnection;
    }

    public static Connection getSqlServerConnection() {
        if(sqlServerConnection == null){
            try {
                var props = loadProperties(SupportedDatabases.SQLSERVER.getFileProperties());
                var url = props.getProperty("dburl");
                sqlServerConnection = DriverManager.getConnection(url,props);
            }catch (SQLException exception) {
                System.out.println("could not get connection.");
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
            throw new DatabaseException("could not close database connection");
        }
    }

    private static Properties loadProperties(String filePath){
        try(var fs = new FileInputStream(filePath)) {
            var props = new Properties();
            props.load(fs);
            return props;
        }
        catch(IOException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
