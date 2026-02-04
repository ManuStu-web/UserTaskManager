package dao;
import model.User;
import util.DBConnection;
import java.sql.*;
import util.PasswordUtil;
public class UserDAO 
{
    public boolean createUser(User user)
    {
        String sql="INSERT INTO users(name,email,password) VALUES(?,?,?)";
        Connection con = DBConnection.getConnection(); 
        try(PreparedStatement ps = con.prepareStatement(sql))
        {
          ps.setString(1, user.getName());
          ps.setString(2, user.getEmail());
          ps.setString(3, PasswordUtil.hashPassword(user.getPassword()));

          return ps.executeUpdate()>0;

        }
        catch(SQLException e)
        {
           System.out.println("User creation failed");
        //    e.printStackTrace();
        System.out.println("Reason: " + e.getMessage());
        }
        return false;
    }

    public User getUserByEmail(String email)
    {
        String sql="SELECT * FROM users WHERE email = ?";
        Connection con = DBConnection.getConnection(); 
        try(PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                return extractUser(rs);
            }
        } catch(SQLException e)
        {
            System.out.println("!! Login Failed !!");
            // e.printStackTrace();
            System.out.println("Reason: " + e.getMessage());
        }

        return null;
    }

    public static User loginUser(String email,String password)
    {
        String sql="SELECT * FROM users WHERE email=?";
        Connection con = DBConnection.getConnection(); 
        try(PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1,email);
            // ps.setString(2,password);
            
            ResultSet rs =ps.executeQuery();

            if (rs.next()) {
            String hashedPassword = rs.getString("password");

            if (PasswordUtil.checkPassword(password, hashedPassword)) {
                return extractUser(rs);
            }
        }
        } catch(SQLException e)
        {
            System.out.println("!! Login Failed !!");
            // e.printStackTrace();
            System.out.println("Reason: " + e.getMessage());
        }

        return null;
    }

    public User getUserById(int id)
    {
        String sql = "SELECT * FROM users WHERE id = ?";
        Connection con = DBConnection.getConnection(); 
        try(PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                return extractUser(rs);
            }
        } catch(SQLException e) {
            System.out.println("!! Login By ID Failed !!");
            System.out.println("Reason: " + e.getMessage());
        }

        return null;
    }

    private static User extractUser(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));

        return user;
    }


}
