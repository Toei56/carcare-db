package carcare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnect {

    public static Connection getConnection () {
        try {
            Class.forName(com.mysql.cj.jdbc.Driver.class.getName());
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost/new_schema?useUnicode=true&characterEncoding=utf-8",
                    "root","Tonmysql04");
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
