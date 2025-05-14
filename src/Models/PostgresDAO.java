package Models;

import Exceptions.DbException;
import Helpers.MetaDataPrinter;
import Interfaces.IDatabaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresDAO implements IDatabaseDao {
    private final Connection connection;

    public PostgresDAO(Connection postgresConnection) {
        connection = postgresConnection;
    }

    @Override
    public Integer getTotalSchemas() {
        ResultSet result = null;
        try{
            result = connection.getMetaData().getSchemas();
            MetaDataPrinter.printMetaData(result);
            return 0;
        }catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            closeResultSet(result);
        }
    }

    public void closeResultSet(ResultSet resultSet) {
        try{
            if(resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
