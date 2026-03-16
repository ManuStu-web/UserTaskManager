## Setup Instructions

This project uses MySQL Connector/J.

Download MySQL Connector/J from:
https://dev.mysql.com/downloads/connector/j/

Add the JAR to the `lib` folder and configure it in your IDE.

# User & Task Management System

![Java](https://img.shields.io/badge/Java-17-orange)
![MySQL](https://img.shields.io/badge/Database-MySQL-blue)
![JDBC](https://img.shields.io/badge/Connectivity-JDBC-green)
![Status](https://img.shields.io/badge/Project-Completed-brightgreen)

A **Java console-based application** for managing users and their tasks.  
This project demonstrates **Object-Oriented Programming (OOP), JDBC database connectivity, and the DAO design pattern**.

The system allows users to create accounts, assign tasks, update tasks, and manage them using a menu-driven console interface.

---

## Project Overview

The **User & Task Management System** is designed to help manage tasks assigned to different users.

Users can:

- Create new users
- Assign tasks
- View all tasks
- Update existing tasks
- Delete tasks

The application follows a **layered architecture** using separate packages for models, DAO classes, and utilities.

---

## Features

- User creation and management
- Add new tasks
- View all tasks
- Update task details
- Delete tasks
- Menu-driven console interface
- MySQL database integration
- Organized project structure using packages

Task details include:

- Task Title
- Description
- Category
- Due Date

---

## Tech Stack

| Technology | Purpose |
|---|---|
| Java | Core programming language |
| JDBC | Database connectivity |
| MySQL | Database storage |
| OOP | Object-oriented programming |
| DAO Pattern | Data access abstraction |

---

## Project Structure

```
UserTaskManager
│
├── dao
│   ├── UserDAO.java
│   └── TaskDAO.java
│
├── model
│   ├── User.java
│   └── Task.java
│
├── util
│   └── DBConnection.java
│
└── MainApp.java
```

---

## Database Schema

### Users Table

| Column | Type |
|---|---|
| id | INT (Primary Key) |
| name | VARCHAR |
| email | VARCHAR |

### Tasks Table

| Column | Type |
|---|---|
| id | INT (Primary Key) |
| title | VARCHAR |
| description | TEXT |
| category | VARCHAR |
| due_date | DATE |
| user_id | INT (Foreign Key) |

---

## Example Console Menu

```
1. Add User
2. Add Task
3. View Tasks
4. Update Task
5. Delete Task
6. Exit
```

---

## Installation & Setup

### 1 Clone the Repository

```bash
git clone https://github.com/ManuStu-web/Java-Projects.git
```

### 2 Open the Project

Open the project in any Java IDE:

- IntelliJ IDEA
- Eclipse
- VS Code

---

### 3 Setup Database

Create a MySQL database:

```sql
CREATE DATABASE task_manager;
```

Create tables for users and tasks.

---

### 4 Configure Database Connection

Update the database credentials inside:

```
DBConnection.java
```

Example:

```java
Connection conn = DriverManager.getConnection(
"jdbc:mysql://localhost:3306/task_manager",
"root",
"password"
);
```

---

### 5 Run the Application

Run:

```
MainApp.java
```

The console menu will appear and you can start managing users and tasks.

---

## Example Workflow

1. Create a user  
2. Add tasks for that user  
3. View tasks stored in the database  
4. Update task details  
5. Delete completed tasks  

---

## Concepts Demonstrated

This project demonstrates:

- Java **Object-Oriented Programming**
- **Encapsulation and abstraction**
- **DAO design pattern**
- **JDBC database operations**
- CRUD operations
- Backend project organization using packages

---

## Future Improvements

Possible improvements for the project:

- Add **task priority levels**
- Add **task status (Pending / Completed)**
- Implement **task search and filters**
- Create a **GUI version using Java Swing or JavaFX**
- Convert to a **web application using Spring Boot**
- Add user authentication

---

## Author

**Manu Sharma**  
B.Tech Computer Science Student

GitHub:  
https://github.com/ManuStu-web
