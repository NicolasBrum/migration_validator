package Helpers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MetaDataPrinter {

    public static void printMetaData(ResultSet resultSet) {
        try{
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(i);
                    System.out.print(columnName + ": " + value + "\t");
                }
                System.out.println();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
