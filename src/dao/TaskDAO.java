package dao;
import model.Task;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class TaskDAO 
{
    public boolean createTask(Task task)
    {
        String sql="INSERT INTO tasks(user_id,title,description,status,due_date,category) VALUES(?,?,?,?,?,?)";

        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,task.getUserId());
            ps.setString(2,task.getTitle());
            ps.setString(3,task.getDescription());
            ps.setString(4,task.getStatus());
            ps.setDate(5, Date.valueOf(task.getDueDate()));
            ps.setString(6,task.getCategory());

            return ps.executeUpdate()>0;
        } catch(SQLException e)
        {
            System.out.println("Task Creation Failed: " +e.getMessage());
        }

        return false;
    }

    public List<Task> getTasksByUserId(int userId)
    {
        String sql="SELECT * FROM tasks WHERE user_id = ?";
        List<Task> tasks = new ArrayList<>();

        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                tasks.add(extractTask(rs));
            }
        } catch(SQLException e) {
            System.out.println("Fetching Failed: "+ e.getMessage());
        }

        return tasks; 
    }

    public boolean updateTaskStatus(int taskId,String status)
    {
      String sql = "UPDATE tasks SET status = ? WHERE id = ?";

      try{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1,status);
        ps.setInt(2,taskId);

        return ps.executeUpdate()>0;
      } catch(SQLException e)
      {
        System.out.println("Task Updation Failed "+ e.getMessage());
      }

      return false;
      
    }

    public boolean deleteTask(int taskId,int userId)
    {
        String sql = "DELETE FROM tasks WHERE id = ? AND user_id = ?";
        try{
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1,taskId);
        ps.setInt(2,userId);

        return ps.executeUpdate() >0;
        } catch(SQLException e)
        {
            System.out.println("Task deletion failed" +e.getMessage());
        }
        return false;
    }

    public Task extractTask(ResultSet rs) throws SQLException
    {
      Task task = new Task();
      task.setId(rs.getInt("id"));
      task.setUserId(rs.getInt("user_id"));
      task.setTitle(rs.getString("title"));
      task.setDescription(rs.getString("description"));
      task.setStatus(rs.getString("status"));

      java.sql.Date sqlDate = rs.getDate("due_date");
      if(sqlDate != null)
      {
        task.setDueDate(sqlDate.toLocalDate());
      }

      task.setCategory(rs.getString("category"));

      return task;
    }

    public List<Task> searchTask(int userId,String keyword)
    {
        String sql="SELECT * FROM tasks WHERE user_id=? AND title LIKE ?";

        List<Task> tasks = new ArrayList<>();
        

        try
        {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,userId);
            ps.setString(2,"%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                tasks.add(extractTask(rs));
            }

        } 
        catch(SQLException e)
        {
                System.out.println("Search Failed!");
        }
        return tasks;
    }

    public List<Task> filterByCategory(int userId, String  category)
    {
        String sql = "SELECT * FROM tasks WHERE user_id = ? AND category = ?";

        List<Task> tasks = new ArrayList<>();

        try
        {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,userId);
            ps.setString(2,category);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                tasks.add(extractTask(rs));
            }
        }catch(SQLException e)
        {
            System.out.println("Filter Failed!");
        }

        return tasks;
    }
    

}
