package Helpers;

import Interfaces.IDatabaseMetaDataDao;
import Models.MssqlMetaDataDAO;
import Models.PostgresMetaDataDAO;

import java.sql.Connection;

public class DaoFactory {

    private DaoFactory() {
        throw new AssertionError();
    }

    public static IDatabaseMetaDataDao createDatabaseDao(SupportedDatabases database, Connection connection){
        if (connection == null) {
            throw new IllegalArgumentException("Connection cannot be null for database: " + database);
        }

        return switch (database){
            case POSTGRES -> new PostgresMetaDataDAO(connection);
            case SQLSERVER -> new MssqlMetaDataDAO(connection);
        };
    }
}
