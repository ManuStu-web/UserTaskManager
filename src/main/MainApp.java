package main;
import util.DBConnection;
import java.sql.Connection;

public class MainApp 
{
    public static void main(String[] args) {
        Connection con = DBConnection.getConnection();
        
        if(con!=null)
        {
            System.out.println("Database connection succesfull");
        }
        else
        {
            System.out.println("Connection Failed");
        }
    }
}
