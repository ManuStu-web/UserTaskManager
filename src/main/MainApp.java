package main;
import util.DBConnection;
import java.sql.Connection;
import dao.UserDAO;
import model.User;

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

        UserDAO userDAO = new UserDAO();
        User user = new User("ManuSH" , "manuSH@gmail.com" , "12345");

        if(userDAO.createUser(user))
        {
            System.out.println("User created succesfully");
        }
        else{
            System.out.println("User creation failed");
        }
    }
}
