package Models;

import Interfaces.IDatabaseDao;

import java.sql.Connection;

public class MssqlDAO implements IDatabaseDao {
    private final Connection connection;

    public MssqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer getTotalSchemas() {
        return 0;
    }
}
