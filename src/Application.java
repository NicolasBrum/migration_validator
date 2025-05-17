import Database.Database;
import Helpers.DatabaseComparator;
import Helpers.DaoFactory;
import Helpers.SupportedDatabases;

public class Application {

    public static void main(String[] args) {

        var postgresConnection = Database.getPostgresConnection();
        var sqlServerConnection = Database.getSqlServerConnection();

        var sqlServerDao = DaoFactory.createDatabaseDao(SupportedDatabases.SQLSERVER,sqlServerConnection);
        var postgresDao = DaoFactory.createDatabaseDao(SupportedDatabases.POSTGRES,postgresConnection);
        var comparator = new DatabaseComparator(postgresDao,sqlServerDao);

        comparator.startComparison();
        Database.closeConnections();
    }
}
