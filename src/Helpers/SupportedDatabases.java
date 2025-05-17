package Helpers;

public enum SupportedDatabases {
    POSTGRES("pg.properties"),
    SQLSERVER("mssql.properties"),
    ;

    private final String fileProperties;

    SupportedDatabases(String fileProperties) {
        this.fileProperties = fileProperties;
    }

    public String getFileProperties() {
        return fileProperties;
    }
}
