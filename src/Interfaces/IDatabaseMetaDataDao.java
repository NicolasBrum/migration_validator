package Interfaces;

import java.util.Set;

public interface IDatabaseMetaDataDao {
    Set<String> getSchemas();
    Set<String> getTables();
}
