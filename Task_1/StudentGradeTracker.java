import java.util.ArrayList;
import java.util.Scanner;

class Student {
   String usn;
   String name;
   int grade;

   Student(String usn, String name, int grade) {
      this.usn = usn;
      this.name = name;
      this.grade = grade;
   }
}

public class StudentGradeTracker {
   private static ArrayList<Student> students = new ArrayList<>();
   private static Scanner sc = new Scanner(System.in);

   public static void main(String[] args) {
      int choice;
      do {
         System.out.println("\n===== Student Grade Tracker =====");
         System.out.println("1. Add Student");
         System.out.println("2. Remove Student (by USN)");
         System.out.println("3. Search Student (by USN)");
         System.out.println("4. View All Students");
         System.out.println("5. Show Summary (Average, Highest, Lowest)");
         System.out.println("6. Exit");
         System.out.print("Enter choice: ");
         choice = sc.nextInt();
         sc.nextLine(); 

         switch (choice) {
               case 1 -> addStudent();
               case 2 -> removeStudent();
               case 3 -> searchStudent();
               case 4 -> viewStudents();
               case 5 -> showSummary();
               case 6 -> System.out.println("Exiting... Goodbye!");
               default -> System.out.println("Invalid choice! Try again.");
         }
      } while (choice != 5);
   }

   private static void addStudent() {
      System.out.print("Enter USN: ");
      String usn = sc.nextLine();

      
      for (Student s : students) {
         if (s.usn.equalsIgnoreCase(usn)) {
               System.out.println("Error: Student with USN " + usn + " already exists.");
               return;
         }
      }

      System.out.print("Enter student name: ");
      String name = sc.nextLine();
      System.out.print("Enter grade for " + name + ": ");
      int grade = sc.nextInt();
      sc.nextLine();

      students.add(new Student(usn, name, grade));
      System.out.println(name + " (" + usn + ") added successfully.");
   }

   private static void removeStudent() {
      System.out.print("Enter USN of student to remove: ");
      String usn = sc.nextLine();

      boolean removed = students.removeIf(s -> s.usn.equalsIgnoreCase(usn));
      if (removed) {
         System.out.println("Student with USN " + usn + " removed successfully.");
      } else {
         System.out.println("Student with USN " + usn + " not found.");
      }
   }
   
   private static void searchStudent() {
      if (students.isEmpty()) {
         System.out.println("No students available.");
         return;
      }

      System.out.print("Enter USN to search: ");
      String usn = sc.nextLine();

      for (Student s : students) {
         if (s.usn.equalsIgnoreCase(usn)) {
            System.out.println("\n--- Student Found ---");
            System.out.println("USN: " + s.usn);
            System.out.println("Name: " + s.name);
            System.out.println("Grade: " + s.grade);
            return;
         }
      }

      System.out.println("Student with USN " + usn + " not found.");
   }

   private static void viewStudents() {
      if (students.isEmpty()) {
         System.out.println("No students available.");
         return;
      }
      System.out.println("\n--- Student List ---");
      for (Student s : students) {
         System.out.println("USN: " + s.usn + " | Name: " + s.name + " | Grade: " + s.grade);
      }
   }

   private static void showSummary() {
      if (students.isEmpty()) {
         System.out.println("No data to summarize.");
         return;
      }

      int total = 0, highest = Integer.MIN_VALUE, lowest = Integer.MAX_VALUE;
      String highestStudent = "", lowestStudent = "";

      for (Student s : students) {
         total += s.grade;
         if (s.grade > highest) {
               highest = s.grade;
               highestStudent = s.name + " (" + s.usn + ")";
         }
         if (s.grade < lowest) {
               lowest = s.grade;
               lowestStudent = s.name + " (" + s.usn + ")";
         }
      }

      double average = (double) total / students.size();
      System.out.println("\n--- Summary Report ---");
      System.out.printf("Average Score: %.2f\n", average);
      System.out.println("Highest Score: " + highest + " - " + highestStudent);
      System.out.println("Lowest Score: " + lowest + " - " + lowestStudent);
   }
}
