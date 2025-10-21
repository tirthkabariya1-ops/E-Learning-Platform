import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.*;

public class ELearningPlatform extends Application {
private static Map<String, User> users = new HashMap<>(); // username -> User object
private static List<Course> courses = new ArrayList<>();
private static Map<String, List<Course>> enrollments = new HashMap<>(); // username -> enrolled courses
private static Map<String, Double> payments = new HashMap<>(); // username -> payment amount

@Override
public void start(Stage primaryStage) {
    VBox root = new VBox(10);

    Button adminLogin = new Button("Admin Login");
    adminLogin.setOnAction(e -> showAdminDashboard(primaryStage));

    Button instructorLogin = new Button("Instructor Login");
    instructorLogin.setOnAction(e -> showInstructorDashboard(primaryStage));

    Button studentLogin = new Button("Student Login");
    studentLogin.setOnAction(e -> showStudentDashboard(primaryStage));

    root.getChildren().addAll(adminLogin, instructorLogin, studentLogin);

    Scene scene = new Scene(root, 400, 200);
    primaryStage.setTitle("E-Learning Platform");
    primaryStage.setScene(scene);
    primaryStage.show();
}

// Admin Dashboard
private void showAdminDashboard(Stage stage) {
    VBox root = new VBox(10);

    Button manageUsers = new Button("Manage Users");
    Button manageCourses = new Button("Manage Courses");
    Button trackEnrollments = new Button("Track Enrollments");
    Button generateReports = new Button("Generate Reports");

    manageUsers.setOnAction(e -> manageUsers());
    manageCourses.setOnAction(e -> manageCourses());
    trackEnrollments.setOnAction(e -> trackEnrollments());
    generateReports.setOnAction(e -> generateReports());

    root.getChildren().addAll(manageUsers, manageCourses, trackEnrollments, generateReports);
    Scene scene = new Scene(root, 400, 300);
    stage.setScene(scene);
}

// Instructor Dashboard
private void showInstructorDashboard(Stage stage) {
    VBox root = new VBox(10);

    Button createCourse = new Button("Create Course");
    Button manageAssignments = new Button("Manage Assignments");
    Button trackStudentProgress = new Button("Track Student Progress");
    Button manageProfile = new Button("Manage Profile");

    createCourse.setOnAction(e -> createCourse("New Course", "Instructor"));
    manageAssignments.setOnAction(e -> System.out.println("Managing assignments and exams..."));
    trackStudentProgress.setOnAction(e -> System.out.println("Tracking student progress..."));
    manageProfile.setOnAction(e -> System.out.println("Managing instructor profile and availability..."));

    root.getChildren().addAll(createCourse, manageAssignments, trackStudentProgress, manageProfile);
    Scene scene = new Scene(root, 400, 300);
    stage.setScene(scene);
}

// Student Dashboard
private void showStudentDashboard(Stage stage) {
    VBox root = new VBox(10);

    Button enrollCourse = new Button("Enroll in Course");
    Button viewProgress = new Button("View Progress");
    Button makePayment = new Button("Make Payment");
    Button manageAccount = new Button("Manage Account");

    enrollCourse.setOnAction(e -> enrollInCourse("Student", "New Course"));
    viewProgress.setOnAction(e -> viewLearningProgress("Student"));
    makePayment.setOnAction(e -> makePayment("Student"));
    manageAccount.setOnAction(e -> System.out.println("Updating personal details and account settings..."));

    root.getChildren().addAll(enrollCourse, viewProgress, makePayment, manageAccount);
    Scene scene = new Scene(root, 400, 300);
    stage.setScene(scene);
}

// Admin Functionalities
private void manageUsers() {
    System.out.println("Managing users: Add, Update, Delete instructor, student, and admin accounts.");
    users.put("newUser", new User("newUser", "password", "Student"));
    System.out.println("User added: newUser (Student)");
}

private void manageCourses() {
    System.out.println("Managing courses: Add, Update, Delete course content, pricing, and schedules.");
}

private void trackEnrollments() {
    System.out.println("Tracking enrollments and payments:");
    enrollments.forEach((user, courses) -> {
        System.out.println(user + " is enrolled in: " + courses);
    });
    payments.forEach((user, amount) -> {
        System.out.println(user + " has paid: $" + amount);
    });
}

private void generateReports() {
    System.out.println("Generating reports on course performance and student progress...");
}

// Instructor Functionalities
private void createCourse(String courseName, String instructorName) {
    courses.add(new Course(courseName, instructorName, 100.0, "Weekdays 10AM-12PM"));
    System.out.println("Course created: " + courseName + " by " + instructorName);
}

// Student Functionalities
private void enrollInCourse(String username, String courseName) {
    Course course = courses.stream()
            .filter(c -> c.getCourseName().equals(courseName))
            .findFirst()
            .orElse(null);

    if (course != null) {
        enrollments.computeIfAbsent(username, k -> new ArrayList<>()).add(course);
        System.out.println(username + " enrolled in " + courseName);
    } else {
        System.out.println("Course not found: " + courseName);
    }
}

private void viewLearningProgress(String username) {
    List<Course> userCourses = enrollments.getOrDefault(username, new ArrayList<>());
    System.out.println(username + " is enrolled in: " + userCourses);
}

private void makePayment(String username) {
    payments.put(username, 100.0); // Example payment
    System.out.println(username + " made a payment of $100.");
}

// Course class
private static class Course {
    private String courseName;
    private String instructorName;
    private double price;
    private String schedule;

    public Course(String courseName, String instructorName, double price, String schedule) {
        this.courseName = courseName;
        this.instructorName = instructorName;
        this.price = price;
        this.schedule = schedule;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public String toString() {
        return "Course: " + courseName + ", Instructor: " + instructorName + ", Price: $" + price + ", Schedule: " + schedule;
    }
}

// User class
private static class User {
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

public static void main(String[] args) {
    users.put("admin", new User("admin", "admin123", "Admin"));
    users.put("instructor", new User("instructor", "inst123", "Instructor"));
    users.put("student", new User("student", "stud123", "Student"));

    launch(args);
}
}
