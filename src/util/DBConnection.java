package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
    private static final String URL = "jdbc:mysql://localhost:3306/task_management?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Password_here";

    public static Connection connection;

    private DBConnection()
    {
         //To prevent object creation
    }

    public static Connection getConnection()
    {
         if(connection == null)
         {
            try{
                connection = DriverManager.getConnection(URL,USER,PASSWORD);
            }
            catch(SQLException e)
            {
                System.out.println("DATABASE CONNECTION FAILED!!");
                e.printStackTrace();
            }
         }

         return connection;
    }
}
