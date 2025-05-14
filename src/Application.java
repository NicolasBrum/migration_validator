import Database.DB;
import Helpers.DaoFactory;

public class Application {

    public static void main(String[] args) {
        var pgConnection = DB.getPostgresConnection();
        //var sqlServerConnection = DB.getSqlServerConnection();

        //var ssDao = DaoFactory.createMssqlDAO(sqlServerConnection);
        var pgDao = DaoFactory.createPostgresDAO(pgConnection);
        System.out.println(pgDao.getTotalSchemas());

        DB.closeConnections();
    }
}
