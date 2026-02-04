package main;

import dao.UserDAO;
import dao.TaskDAO;
import model.User;
import model.Task;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    private static final String LINE = "========================================";
    

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        System.out.println(LINE);
        System.out.println("WELCOME");
        System.out.println("Press 1 to Login");
        System.out.println("Press 2 to Register");
        int LR = sc.nextInt();
        sc.nextLine();

        if (LR == 1) 
        {
            Login();
        }
        else
        {
            System.out.print("Enter Your Name: ");
            String userName = sc.nextLine();

            System.out.print("\nEnter your Email: ");
            String email = sc.nextLine();

            System.out.print("\nEnter Your Password: ");
            String password = sc.nextLine();

            User newUser = new User(userName,email,password);
            if(userDAO.createUser(newUser))
            {
                System.out.println("Successfully Registered , You can Login Now");
                 Login();
            }
            else
            {
                System.out.println("Registeration Unsucessfull");
            }
            
            return;
            
        }
    }

    private static void Login()
    {
        Scanner sc = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();
        TaskDAO taskDAO = new TaskDAO();

        // ===== LOGIN =====
            System.out.println(LINE);
            System.out.println("USER LOGIN");
            System.out.println(LINE);

            System.out.print("Email    : ");
            String email = sc.nextLine();

            System.out.print("Password : ");
            String password = sc.nextLine();

            User user = userDAO.loginUser(email, password);

            if (user == null) {
                System.out.println();
                System.out.println("ERROR: Invalid Email or Password");
                System.out.println(LINE);
                sc.close();
                return;
            }

            System.out.println();
            System.out.println("Login Successful");
            System.out.println("Welcome, " + user.getName());
            System.out.println(LINE);

            // ===== MAIN MENU =====
            while (true) {
                System.out.println();
                System.out.println("MAIN MENU");
                System.out.println(LINE);
                System.out.println("1) Add Task");
                System.out.println("2) View Your Tasks");
                System.out.println("3) Complete Task");
                System.out.println("4) Delete Task");
                System.out.println("5) Logout");
                System.out.println(LINE);

                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                System.out.println();

                switch (choice) {

                    case 1:
                        // ===== ADD TASK =====
                        System.out.println("ADD NEW TASK");
                        System.out.println(LINE);

                        System.out.print("Title       : ");
                        String title = sc.nextLine();

                        System.out.print("Description : ");
                        String desc = sc.nextLine();

                        Task task = new Task(title, desc, "PENDING", user.getId());

                        if (taskDAO.createTask(task)) {
                            System.out.println();
                            System.out.println("Task Added Successfully");
                        } else {
                            System.out.println();
                            System.out.println("ERROR: Failed to Add Task");
                        }

                        break;

                    case 2:
                        // ===== VIEW TASKS =====
                        System.out.println("YOUR TASKS");
                        System.out.println(LINE);

                        List<Task> tasks = taskDAO.getTasksByUserId(user.getId());

                        if (tasks.isEmpty()) {
                            System.out.println("No Tasks Found");
                        } else {
                            for (Task t : tasks) {
                                System.out.println(
                                        "ID: " + t.getId() +
                                                " | Title: " + t.getTitle() +
                                                " | Status: " + t.getStatus());
                            }
                        }
                        break;

                    case 3:
                        // ===== COMPLETE TASK =====
                        System.out.println("COMPLETE TASK");
                        System.out.println(LINE);

                        List<Task> tasksToComplete = taskDAO.getTasksByUserId(user.getId());

                        if (tasksToComplete.isEmpty()) {
                            System.out.println("No Tasks Found");
                            break;
                        }

                        for (Task t : tasksToComplete) {
                            System.out.println(
                                    "ID: " + t.getId() +
                                            " | Title: " + t.getTitle() +
                                            " | Status: " + t.getStatus());
                        }

                        System.out.println();
                        System.out.print("Enter Task ID to mark COMPLETED: ");
                        int completeId = sc.nextInt();

                        if (taskDAO.updateTaskStatus(completeId, "COMPLETED")) {
                            System.out.println("Task Marked as COMPLETED");
                        } else {
                            System.out.println("ERROR: Failed to Update Task");
                        }
                        break;

                    case 4:
                        // ===== DELETE TASK =====
                        System.out.println("DELETE TASK");
                        System.out.println(LINE);

                        System.out.print("Enter Task ID to delete: ");
                        int deleteId = sc.nextInt();

                        if (taskDAO.deleteTask(deleteId)) {
                            System.out.println("Task Deleted Successfully");
                        } else {
                            System.out.println("ERROR: Failed to Delete Task");
                        }
                        break;

                    case 5:
                        // ===== LOGOUT =====
                        System.out.println();
                        System.out.println(LINE);
                        System.out.println("Logged Out Successfully");
                        System.out.println("Thank you for using Task Manager");
                        System.out.println(LINE);
                        sc.close();
                        return;

                    default:
                        System.out.println("WARNING: Invalid Choice. Please Try Again.");
                }
            }
    }
}
