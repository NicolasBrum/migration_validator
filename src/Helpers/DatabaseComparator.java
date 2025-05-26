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
        compareTables();
    }

    private void compareSchemas(){
        System.out.println("Comparing schemas...");
        var originSchemas = databaseOrigin.getSchemas();
        var targetSchemas = databaseTarget.getSchemas();
        var commonSchemas = originSchemas.stream()
                .filter(targetSchemas::contains)
                .toList();

        if(commonSchemas.isEmpty()){
            System.out.println("No schemas found.");
            return;
        }

        System.out.println("The two bases contains: " + commonSchemas);
    }

    private void compareTables(){
        System.out.println("Comparing tables...");
        var originTables = databaseOrigin.getTables();
        var targetTables = databaseTarget.getTables();
        var commonTables = originTables.stream()
                .filter(targetTables::contains)
                .toList();

        if(commonTables.isEmpty()){
            System.out.println("No tables found.");
            return;
        }

        System.out.println("The two bases contains: " + commonTables);
    }
}
