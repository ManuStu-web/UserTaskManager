package model;
import java.time.LocalDate;
public class Task
{
    private int id;
    private String title;
    private String description;
    private String status;
    private int userId;
    private LocalDate dueDate;
    private String category;

    public Task()
    {
        
    }

    public Task(String title , String description , String status , int userId, LocalDate dueDate, String category)
    {
        this.title=title;
        this.description=description;
        this.status=status;
        this.userId=userId;
        this.dueDate=dueDate;
        this.category=category;
    }

    //Getters
    public int getId()
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
    public LocalDate getDueDate()
    {
        return dueDate;
    }
    public String getCategory()
    {
        return category;
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
    public void setCategory(String category)
    {
        this.category=category;
    }
    public void setDueDate(LocalDate dueDate)
    {
        this.dueDate=dueDate;
    }
    public boolean isOverDue()
    {
        return status.equalsIgnoreCase("pending") && dueDate.isBefore(LocalDate.now());
    }
}