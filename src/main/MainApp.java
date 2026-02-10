package main;

import dao.UserDAO;
import dao.TaskDAO;
import model.User;
import model.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    private static final String LINE = "========================================";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        System.out.println(LINE);
        System.out.println("WELCOME TO TASK MANAGEMENT SYSTEM");
        System.out.println(LINE);

        System.out.println("Press 1 to Login");
        System.out.println("Press 2 to Register");

        System.out.print("Choice: ");
        int LR = sc.nextInt();
        sc.nextLine();

        if (LR == 1) {
            login(sc);
        } else {

            System.out.println(LINE);
            System.out.println("USER REGISTRATION");
            System.out.println(LINE);

            System.out.print("Enter Your Name     : ");
            String userName = sc.nextLine();

            System.out.print("Enter Your Email    : ");
            String email = sc.nextLine();

            System.out.print("Enter Your Password : ");
            String password = sc.nextLine();

            User newUser = new User(userName, email, password);

            if (userDAO.createUser(newUser)) {
                System.out.println("\nRegistered Successfully! Please Login Now.\n");
                login(sc);
            } else {
                System.out.println("\nERROR: Registration Failed!");
            }
        }

        sc.close();
    }

    // ================= LOGIN METHOD =================
    private static void login(Scanner sc) {

        UserDAO userDAO = new UserDAO();
        TaskDAO taskDAO = new TaskDAO();

        System.out.println(LINE);
        System.out.println("USER LOGIN");
        System.out.println(LINE);

        System.out.print("Email    : ");
        String email = sc.nextLine();

        System.out.print("Password : ");
        String password = sc.nextLine();

        User user = userDAO.loginUser(email, password);

        if (user == null) {
            System.out.println("\nERROR: Invalid Email or Password!");
            return;
        }

        System.out.println("\nLogin Successful!");
        System.out.println("Welcome, " + user.getName());

        // ================= MAIN MENU =================
        while (true) {

            System.out.println("\n" + LINE);
            System.out.println("MAIN MENU");
            System.out.println(LINE);

            System.out.println("1) Add Task");
            System.out.println("2) View Tasks");
            System.out.println("3) Complete Task");
            System.out.println("4) Delete Task");
            System.out.println("5) Search Task");
            System.out.println("6) Filter by Category");
            System.out.println("7) Logout");

            System.out.print("\nChoose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                // ================= ADD TASK =================
                case 1:
                    System.out.println("\nADD NEW TASK");
                    System.out.println(LINE);

                    System.out.print("Title       : ");
                    String title = sc.nextLine();

                    System.out.print("Description : ");
                    String desc = sc.nextLine();

                    System.out.print("Category    : ");
                    String category = sc.nextLine();

                    System.out.print("Due Date (YYYY-MM-DD): ");
                    LocalDate dueDate = LocalDate.parse(sc.nextLine());

                    Task task = new Task(title, desc, "PENDING",
                            user.getId(), dueDate, category);

                    if (taskDAO.createTask(task)) {
                        System.out.println("\nTask Added Successfully!");
                    } else {
                        System.out.println("\nERROR: Failed to Add Task!");
                    }
                    break;

                // ================= VIEW TASKS =================
                case 2:
                    System.out.println("\nYOUR TASKS");
                    System.out.println(LINE);

                    List<Task> tasks = taskDAO.getTasksByUserId(user.getId());

                    if (tasks.isEmpty()) {
                        System.out.println("No Tasks Found!");
                    } else {
                        for (Task t : tasks) {
                            System.out.println(
                                    "ID: " + t.getId() +
                                            " | Title: " + t.getTitle() +
                                            " | Status: " + t.getStatus() +
                                            " | Due: " + t.getDueDate() +
                                            " | Category: " + t.getCategory()
                            );
                        }
                    }
                    break;

                // ================= COMPLETE TASK =================
                case 3:
                    System.out.print("\nEnter Task ID to mark COMPLETED: ");
                    int completeId = sc.nextInt();

                    if (taskDAO.updateTaskStatus(completeId, "COMPLETED")) {
                        System.out.println("Task Marked Completed!");
                    } else {
                        System.out.println("ERROR: Could Not Update Task!");
                    }
                    break;

                // ================= DELETE TASK (SECURE) =================
                case 4:
                    System.out.print("\nEnter Task ID to delete: ");
                    int deleteId = sc.nextInt();

                    if (taskDAO.deleteTask(deleteId, user.getId())) {
                        System.out.println("Task Deleted Successfully!");
                    } else {
                        System.out.println("ERROR: You cannot delete this task!");
                    }
                    break;

                // ================= SEARCH TASK =================
                case 5:
                    System.out.print("\nEnter keyword to search: ");
                    String keyword = sc.nextLine();

                    List<Task> searched = taskDAO.searchTask(user.getId(), keyword);

                    if (searched.isEmpty()) {
                        System.out.println("No Matching Tasks Found!");
                    } else {
                        for (Task t : searched) {
                            System.out.println("ID: " + t.getId() +
                                    " | " + t.getTitle());
                        }
                    }
                    break;

                // ================= FILTER CATEGORY =================
                case 6:
                    System.out.print("\nEnter category name: ");
                    String cat = sc.nextLine();

                    List<Task> filtered = taskDAO.filterByCategory(user.getId(), cat);

                    if (filtered.isEmpty()) {
                        System.out.println("No Tasks Found in this Category!");
                    } else {
                        for (Task t : filtered) {
                            System.out.println("ID: " + t.getId() +
                                    " | " + t.getTitle());
                        }
                    }
                    break;


                // ================= LOGOUT =================
                case 7:
                    System.out.println("\nLogged Out Successfully!");
                    return;

                default:
                    System.out.println("\nInvalid Choice. Try Again.");
            }
        }
    }
}
