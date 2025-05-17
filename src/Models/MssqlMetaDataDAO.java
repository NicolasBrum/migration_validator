package Models;

import Exceptions.DatabaseException;
import Interfaces.IDatabaseMetaDataDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MssqlMetaDataDAO implements IDatabaseMetaDataDao {
    private final Connection connection;

    public MssqlMetaDataDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Set<String> getSchemas() {
        Set<String> schemasSet;

        try(ResultSet queryResult = connection.getMetaData()
                .getSchemas()){

            schemasSet = new HashSet<>();

            while (queryResult.next()) {
                schemasSet.add(queryResult.getString("TABLE_SCHEM"));
            }
        }catch (SQLException exception) {
            throw new DatabaseException("Could not get database schema.");
        }

        return schemasSet;
    }
}
