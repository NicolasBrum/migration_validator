package Helpers;

import Interfaces.IDatabaseDao;
import Models.MssqlDAO;
import Models.PostgresDAO;

import java.sql.Connection;

public class DaoFactory {

    public static IDatabaseDao createPostgresDAO(Connection connection) {
        return new PostgresDAO(connection);
    }

    public static IDatabaseDao createMssqlDAO(Connection connection) {
        return new MssqlDAO(connection);
    }
}
