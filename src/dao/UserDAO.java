package dao;
import model.User;
import util.DBConnection;
import java.sql.*;
public class UserDAO 
{
    public boolean createUser(User user)
    {
        String sql="INSERT INTO users(name,email,password) VALUES(?,?,?)";
        try(Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql))
        {
          ps.setString(1, user.getName());
          ps.setString(2, user.getEmail());
          ps.setString(3, user.getPassword());

          return ps.executeUpdate()>0;

        }
        catch(SQLException e)
        {
           System.out.println("User creation failed");
           e.printStackTrace();
        }
        return false;
    }

    public User getUserByEmail(String email)
    {
        String sql="SELECT * FROM users WHERE email = ?";

        try(Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql))
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
            e.printStackTrace();
        }

        return null;
    }

    public User loginUser(String email,String password)
    {
        String sql="SELECT * FROM users WHERE email=? AND password=?";

        try(Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1,email);
            ps.setString(2,password);
            
            ResultSet rs =ps.executeQuery();

            if(rs.next())
            {
                return extractUser(rs);
            }

        } catch(SQLException e)
        {
            System.out.println("!! Login Failed !!");
            e.printStackTrace();
        }

        return null;
    }

    public User getUserById(int id)
    {
        String sql = "SELECT * FROM users WHERE id = ?";

        try(Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                return extractUser(rs);
            }
        } catch(SQLException e) {
            System.out.println("!! Login By ID Failed !!");
            e.printStackTrace();
        }

        return null;
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));

        return user;
    }


}
