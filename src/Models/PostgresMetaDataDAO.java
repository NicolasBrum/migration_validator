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
            throw new DatabaseException(exception.getMessage());
        }

        return schemasSet;
    }

    @Override
    public Set<String> getTables() {
        Set<String> tablesSet;
        String[] types = {"TABLE"};

        try(var queryResult = connection.getMetaData().getTables(null,null,"%",types)){

            tablesSet = new HashSet<>();

            while (queryResult.next()) {
                tablesSet.add(queryResult.getString("TABLE_NAME"));
            }
        }catch (SQLException exception){
            throw new DatabaseException("Could not get database tables.");
        }

        return tablesSet;
    }
}
