package model;
public class User
{
    
    private int id;         //Unique identifier for the user
    private String name;     // User's full name
    private String email;    // User's email address
    private String password; //Password for user authentication

    public User()
    {
        
    }

    public User(int id, String name , String email , String password)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //Private Data Members so create Getters and Setters (This makes data members safe and secure)

    //Getters
    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public String getEmail()
    {
        return email;
    }
    public String getPassword()
    {
        return password;
    }

    //Setters
    public void setName(String name)
    {
        this.name = name;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    
}