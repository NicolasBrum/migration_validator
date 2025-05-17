package Helpers;

import Interfaces.IDatabaseMetaDataDao;

public class DatabaseComparator {

    private final IDatabaseMetaDataDao databaseOrigin;
    private final IDatabaseMetaDataDao databaseTarget;

    public DatabaseComparator(IDatabaseMetaDataDao databaseOrigin, IDatabaseMetaDataDao databaseTarget) {
        this.databaseOrigin = databaseOrigin;
        this.databaseTarget = databaseTarget;
    }

    public void startComparison(){
        compareSchemas();
    }

    private void compareSchemas(){
        System.out.println("Comparing schemas...");

        var originSchemas = databaseOrigin.getSchemas();
        var targetSchemas = databaseTarget.getSchemas();

        var commonSchemas = originSchemas.stream()
                .filter(targetSchemas::contains)
                .toList();

        System.out.println("The two bases contains: " + commonSchemas);
    }
}
