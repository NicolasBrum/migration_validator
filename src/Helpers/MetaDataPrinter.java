package Helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MetaDataPrinter {

    public static void printMetaData(ResultSet resultSet) {
        try{
            while (resultSet.next()) {
                String schema = resultSet.getString("TABLE_SCHEM");
                System.out.println("Schema: " + schema);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
