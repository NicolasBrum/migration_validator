package Helpers;

import Interfaces.IDatabaseDao;

public class CompareDatabases {

    private final IDatabaseDao origin;
    private final IDatabaseDao target;

    public CompareDatabases(IDatabaseDao origin, IDatabaseDao target) {
        this.origin = origin;
        this.target = target;
    }

    public static void startComparison(){

    }

    private void compareTotalSchemas(){

    }
}
