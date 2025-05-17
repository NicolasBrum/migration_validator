package Models;

import Exceptions.DatabaseException;
import Interfaces.IDatabaseMetaDataDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class PostgresMetaDataDAO implements IDatabaseMetaDataDao {
    private final Connection connection;

    public PostgresMetaDataDAO(Connection postgresConnection) {
        connection = postgresConnection;
    }

    @Override
    public Set<String> getSchemas() {
        Set<String> schemasSet;

        try(var queryResult = connection.getMetaData()
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
