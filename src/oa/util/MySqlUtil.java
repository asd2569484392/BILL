package oa.util;

import java.sql.DriverManager;
import java.sql.SQLException;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.DatabaseMetaData;

public class MySqlUtil {
    
    private final static String DRIVER = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "1234";
    
    public static DatabaseMetaData getMetaData() throws ClassNotFoundException, SQLException{
        Class.forName(DRIVER);
        Connection con = (Connection) DriverManager.getConnection(URL,USERNAME,PASSWORD);
        DatabaseMetaData metaData = (DatabaseMetaData) con.getMetaData();
        return metaData;
    }
    
    
    
}
