package model;
public class Task
{
    private int id;
    private String title;
    private String description;
    private String status;
    private int userId;

    public Task(int id,String title , String description , String status , int userId)
    {
        this.id=id;
        this.title=title;
        this.description=description;
        this.status=status;
        this.userId=userId;
    }

    //Getters
    public int gerId()
    {
        return id;
    }
    public String getTitle()
    {
        return title;
    }
    public String getDescription()
    {
        return description;
    }
    public String getStatus()
    {
        return status;
    }
    public int getUserId()
    {
        return userId;
    }

    //Setters
    public void setId(int id)
    {
        this.id=id;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public void setUserId(int userId)
    {
        this.userId = userId;
    }
}