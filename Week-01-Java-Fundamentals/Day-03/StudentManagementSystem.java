/**
 * Day 03: Student Management System
 * Real-World College/School Application
 *
 * This program simulates a production student management system
 * used in schools and colleges worldwide.
 *
 * Features:
 * - Add students with marks in multiple subjects
 * - Display all students with grades
 * - Search student by roll number
 * - Find class topper
 * - Find subject-wise toppers
 * - Calculate class statistics
 * - Generate grade sheets
 * - Sort students by performance
 *
 * Learning Outcomes:
 * - Arrays (single and multi-dimensional)
 * - String manipulation
 * - Array algorithms (search, sort, find max/min)
 * - Data validation
 * - CRUD operations
 */

import java.util.Scanner;
import java.util.Arrays;

public class StudentManagementSystem {

    // Maximum capacity
    private static final int MAX_STUDENTS = 100;
    private static final int NUM_SUBJECTS = 3;
    private static final String[] SUBJECT_NAMES = {"Mathematics", "Science", "English"};

    // Student data arrays
    private static String[] studentNames = new String[MAX_STUDENTS];
    private static int[] rollNumbers = new int[MAX_STUDENTS];
    private static double[][] marks = new double[MAX_STUDENTS][NUM_SUBJECTS];
    private static int studentCount = 0;

    // Grade boundaries
    private static final double GRADE_A = 90.0;
    private static final double GRADE_B = 75.0;
    private static final double GRADE_C = 60.0;
    private static final double GRADE_D = 40.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Add some sample data
        initializeSampleData();

        boolean exit = false;
        while (!exit) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = getValidInt(scanner);

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    searchStudent(scanner);
                    break;
                case 4:
                    displayClassTopper();
                    break;
                case 5:
                    displaySubjectWiseToppers();
                    break;
                case 6:
                    displayClassStatistics();
                    break;
                case 7:
                    generateGradeSheet(scanner);
                    break;
                case 8:
                    displayStudentsSorted();
                    break;
                case 9:
                    updateStudentMarks(scanner);
                    break;
                case 10:
                    deleteStudent(scanner);
                    break;
                case 11:
                    exit = true;
                    System.out.println("\n✅ Thank you for using Student Management System!");
                    break;
                default:
                    System.out.println("\n❌ Invalid choice! Please try again.");
            }

            if (!exit) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    /**
     * Initialize with sample data for demonstration
     */
    private static void initializeSampleData() {
        // Student 1
        studentNames[0] = "Alice Johnson";
        rollNumbers[0] = 101;
        marks[0][0] = 95; // Math
        marks[0][1] = 88; // Science
        marks[0][2] = 92; // English

        // Student 2
        studentNames[1] = "Bob Smith";
        rollNumbers[1] = 102;
        marks[1][0] = 78; // Math
        marks[1][1] = 85; // Science
        marks[1][2] = 80; // English

        // Student 3
        studentNames[2] = "Carol Davis";
        rollNumbers[2] = 103;
        marks[2][0] = 82; // Math
        marks[2][1] = 90; // Science
        marks[2][2] = 87; // English

        studentCount = 3;
        System.out.println("✅ System initialized with " + studentCount + " sample students.");
    }

    /**
     * Display main menu
     */
    private static void displayMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("        STUDENT MANAGEMENT SYSTEM - MAIN MENU");
        System.out.println("=".repeat(60));
        System.out.println("1.  Add New Student");
        System.out.println("2.  Display All Students");
        System.out.println("3.  Search Student by Roll Number");
        System.out.println("4.  Display Class Topper");
        System.out.println("5.  Display Subject-wise Toppers");
        System.out.println("6.  Display Class Statistics");
        System.out.println("7.  Generate Grade Sheet for Student");
        System.out.println("8.  Display Students Sorted by Marks");
        System.out.println("9.  Update Student Marks");
        System.out.println("10. Delete Student");
        System.out.println("11. Exit");
        System.out.println("=".repeat(60));
    }

    /**
     * Add a new student
     */
    private static void addStudent(Scanner scanner) {
        if (studentCount >= MAX_STUDENTS) {
            System.out.println("\n❌ Student limit reached! Cannot add more students.");
            return;
        }

        System.out.println("\n" + "-".repeat(60));
        System.out.println("                   ADD NEW STUDENT");
        System.out.println("-".repeat(60));

        // Get student name
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("❌ Name cannot be empty!");
            return;
        }

        // Get roll number
        System.out.print("Enter roll number: ");
        int rollNumber = getValidInt(scanner);

        // Check if roll number already exists
        if (findStudentIndex(rollNumber) != -1) {
            System.out.println("❌ Roll number already exists!");
            return;
        }

        // Get marks for each subject
        double[] studentMarks = new double[NUM_SUBJECTS];
        boolean validMarks = true;

        for (int i = 0; i < NUM_SUBJECTS; i++) {
            System.out.print("Enter marks for " + SUBJECT_NAMES[i] + " (0-100): ");
            double mark = getValidDouble(scanner);

            if (mark < 0 || mark > 100) {
                System.out.println("❌ Invalid marks! Must be between 0 and 100.");
                validMarks = false;
                break;
            }
            studentMarks[i] = mark;
        }

        if (!validMarks) {
            return;
        }

        // Add student
        studentNames[studentCount] = name;
        rollNumbers[studentCount] = rollNumber;
        marks[studentCount] = studentMarks;
        studentCount++;

        System.out.println("\n✅ Student added successfully!");
        System.out.println("-".repeat(60));
    }

    /**
     * Display all students
     */
    private static void displayAllStudents() {
        if (studentCount == 0) {
            System.out.println("\n❌ No students found!");
            return;
        }

        System.out.println("\n" + "=".repeat(100));
        System.out.println("                              ALL STUDENTS");
        System.out.println("=".repeat(100));
        System.out.printf("%-10s %-25s %-12s %-12s %-12s %-12s %-8s%n",
                         "Roll No", "Name", SUBJECT_NAMES[0], SUBJECT_NAMES[1],
                         SUBJECT_NAMES[2], "Average", "Grade");
        System.out.println("-".repeat(100));

        for (int i = 0; i < studentCount; i++) {
            double average = calculateAverage(marks[i]);
            String grade = calculateGrade(average);

            System.out.printf("%-10d %-25s %-12.2f %-12.2f %-12.2f %-12.2f %-8s%n",
                             rollNumbers[i], studentNames[i],
                             marks[i][0], marks[i][1], marks[i][2],
                             average, grade);
        }
        System.out.println("=".repeat(100));
        System.out.println("Total Students: " + studentCount);
    }

    /**
     * Search student by roll number
     */
    private static void searchStudent(Scanner scanner) {
        System.out.print("\nEnter roll number to search: ");
        int rollNumber = getValidInt(scanner);

        int index = findStudentIndex(rollNumber);

        if (index == -1) {
            System.out.println("❌ Student not found!");
            return;
        }

        displayStudentDetails(index);
    }

    /**
     * Display student details
     */
    private static void displayStudentDetails(int index) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                  STUDENT DETAILS");
        System.out.println("=".repeat(60));
        System.out.println("Roll Number   : " + rollNumbers[index]);
        System.out.println("Name          : " + studentNames[index]);
        System.out.println("-".repeat(60));
        System.out.println("MARKS:");

        double total = 0;
        for (int i = 0; i < NUM_SUBJECTS; i++) {
            System.out.printf("%-15s : %.2f / 100%n", SUBJECT_NAMES[i], marks[index][i]);
            total += marks[index][i];
        }

        double average = total / NUM_SUBJECTS;
        String grade = calculateGrade(average);

        System.out.println("-".repeat(60));
        System.out.printf("Total Marks   : %.2f / 300%n", total);
        System.out.printf("Percentage    : %.2f%%%n", average);
        System.out.println("Grade         : " + grade);
        System.out.println("Status        : " + (average >= 40 ? "PASS ✓" : "FAIL ✗"));
        System.out.println("=".repeat(60));
    }

    /**
     * Display class topper
     */
    private static void displayClassTopper() {
        if (studentCount == 0) {
            System.out.println("\n❌ No students found!");
            return;
        }

        int topperIndex = 0;
        double maxAverage = calculateAverage(marks[0]);

        for (int i = 1; i < studentCount; i++) {
            double average = calculateAverage(marks[i]);
            if (average > maxAverage) {
                maxAverage = average;
                topperIndex = i;
            }
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("                  CLASS TOPPER 🏆");
        System.out.println("=".repeat(60));
        displayStudentDetails(topperIndex);
    }

    /**
     * Display subject-wise toppers
     */
    private static void displaySubjectWiseToppers() {
        if (studentCount == 0) {
            System.out.println("\n❌ No students found!");
            return;
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("              SUBJECT-WISE TOPPERS 🏆");
        System.out.println("=".repeat(60));

        for (int subject = 0; subject < NUM_SUBJECTS; subject++) {
            int topperIndex = 0;
            double maxMarks = marks[0][subject];

            for (int i = 1; i < studentCount; i++) {
                if (marks[i][subject] > maxMarks) {
                    maxMarks = marks[i][subject];
                    topperIndex = i;
                }
            }

            System.out.printf("\n%s:%n", SUBJECT_NAMES[subject]);
            System.out.printf("  Student: %s (Roll: %d)%n",
                             studentNames[topperIndex], rollNumbers[topperIndex]);
            System.out.printf("  Marks: %.2f / 100%n", maxMarks);
        }
        System.out.println("\n" + "=".repeat(60));
    }

    /**
     * Display class statistics
     */
    private static void displayClassStatistics() {
        if (studentCount == 0) {
            System.out.println("\n❌ No students found!");
            return;
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("                CLASS STATISTICS");
        System.out.println("=".repeat(60));

        // Calculate statistics for each subject
        for (int subject = 0; subject < NUM_SUBJECTS; subject++) {
            double sum = 0, min = marks[0][subject], max = marks[0][subject];

            for (int i = 0; i < studentCount; i++) {
                double mark = marks[i][subject];
                sum += mark;
                if (mark < min) min = mark;
                if (mark > max) max = mark;
            }

            double average = sum / studentCount;

            System.out.printf("\n%s:%n", SUBJECT_NAMES[subject]);
            System.out.printf("  Average   : %.2f%n", average);
            System.out.printf("  Highest   : %.2f%n", max);
            System.out.printf("  Lowest    : %.2f%n", min);
        }

        // Overall statistics
        double classTotal = 0;
        for (int i = 0; i < studentCount; i++) {
            classTotal += calculateAverage(marks[i]);
        }
        double classAverage = classTotal / studentCount;

        System.out.println("\n" + "-".repeat(60));
        System.out.printf("Overall Class Average: %.2f%%%n", classAverage);
        System.out.println("Total Students: " + studentCount);

        // Grade distribution
        int gradeA = 0, gradeB = 0, gradeC = 0, gradeD = 0, gradeF = 0;
        for (int i = 0; i < studentCount; i++) {
            double avg = calculateAverage(marks[i]);
            String grade = calculateGrade(avg);
            switch (grade) {
                case "A": gradeA++; break;
                case "B": gradeB++; break;
                case "C": gradeC++; break;
                case "D": gradeD++; break;
                case "F": gradeF++; break;
            }
        }

        System.out.println("\nGrade Distribution:");
        System.out.println("  A Grade: " + gradeA + " students");
        System.out.println("  B Grade: " + gradeB + " students");
        System.out.println("  C Grade: " + gradeC + " students");
        System.out.println("  D Grade: " + gradeD + " students");
        System.out.println("  F Grade: " + gradeF + " students");
        System.out.println("=".repeat(60));
    }

    /**
     * Generate grade sheet for a student
     */
    private static void generateGradeSheet(Scanner scanner) {
        System.out.print("\nEnter roll number: ");
        int rollNumber = getValidInt(scanner);

        int index = findStudentIndex(rollNumber);

        if (index == -1) {
            System.out.println("❌ Student not found!");
            return;
        }

        // Generate professional grade sheet
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                  GRADE SHEET");
        System.out.println("           EXCELLENCE PUBLIC SCHOOL");
        System.out.println("=".repeat(60));
        System.out.println("Student Name: " + studentNames[index]);
        System.out.println("Roll Number : " + rollNumbers[index]);
        System.out.println("Class       : X-A");
        System.out.println("Academic Year: 2025-2026");
        System.out.println("=".repeat(60));

        double total = 0;
        System.out.printf("%-20s %10s %10s %10s%n", "Subject", "Marks", "Max", "Grade");
        System.out.println("-".repeat(60));

        for (int i = 0; i < NUM_SUBJECTS; i++) {
            String subjectGrade = calculateGrade(marks[index][i]);
            System.out.printf("%-20s %10.2f %10s %10s%n",
                             SUBJECT_NAMES[i], marks[index][i], "100", subjectGrade);
            total += marks[index][i];
        }

        System.out.println("-".repeat(60));
        double average = total / NUM_SUBJECTS;
        String overallGrade = calculateGrade(average);

        System.out.printf("%-20s %10.2f %10s %10s%n", "TOTAL", total, "300", "");
        System.out.printf("%-20s %10.2f%% %10s %10s%n", "PERCENTAGE", average, "", "");
        System.out.printf("%-20s %10s %10s %10s%n", "OVERALL GRADE", "", "", overallGrade);
        System.out.printf("%-20s %10s %10s %10s%n", "RESULT",
                         average >= 40 ? "PASS ✓" : "FAIL ✗", "", "");

        System.out.println("=".repeat(60));
        System.out.println("Grade Key: A (90+) | B (75-89) | C (60-74) | D (40-59) | F (<40)");
        System.out.println("=".repeat(60));
    }

    /**
     * Display students sorted by average marks
     */
    private static void displayStudentsSorted() {
        if (studentCount == 0) {
            System.out.println("\n❌ No students found!");
            return;
        }

        // Create arrays for sorting
        int[] sortedIndices = new int[studentCount];
        double[] averages = new double[studentCount];

        for (int i = 0; i < studentCount; i++) {
            sortedIndices[i] = i;
            averages[i] = calculateAverage(marks[i]);
        }

        // Bubble sort (descending order)
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i - 1; j++) {
                if (averages[j] < averages[j + 1]) {
                    // Swap averages
                    double tempAvg = averages[j];
                    averages[j] = averages[j + 1];
                    averages[j + 1] = tempAvg;

                    // Swap indices
                    int tempIndex = sortedIndices[j];
                    sortedIndices[j] = sortedIndices[j + 1];
                    sortedIndices[j + 1] = tempIndex;
                }
            }
        }

        System.out.println("\n" + "=".repeat(80));
        System.out.println("               STUDENTS SORTED BY PERFORMANCE (Highest to Lowest)");
        System.out.println("=".repeat(80));
        System.out.printf("%-6s %-10s %-25s %-12s %-8s%n",
                         "Rank", "Roll No", "Name", "Average", "Grade");
        System.out.println("-".repeat(80));

        for (int i = 0; i < studentCount; i++) {
            int index = sortedIndices[i];
            String grade = calculateGrade(averages[i]);

            System.out.printf("%-6d %-10d %-25s %-12.2f %-8s%n",
                             i + 1, rollNumbers[index], studentNames[index],
                             averages[i], grade);
        }
        System.out.println("=".repeat(80));
    }

    /**
     * Update student marks
     */
    private static void updateStudentMarks(Scanner scanner) {
        System.out.print("\nEnter roll number: ");
        int rollNumber = getValidInt(scanner);

        int index = findStudentIndex(rollNumber);

        if (index == -1) {
            System.out.println("❌ Student not found!");
            return;
        }

        System.out.println("\nCurrent marks for " + studentNames[index] + ":");
        for (int i = 0; i < NUM_SUBJECTS; i++) {
            System.out.printf("%s: %.2f%n", SUBJECT_NAMES[i], marks[index][i]);
        }

        System.out.print("\nEnter subject number to update (1-3): ");
        int subjectNum = getValidInt(scanner);

        if (subjectNum < 1 || subjectNum > NUM_SUBJECTS) {
            System.out.println("❌ Invalid subject number!");
            return;
        }

        System.out.print("Enter new marks (0-100): ");
        double newMarks = getValidDouble(scanner);

        if (newMarks < 0 || newMarks > 100) {
            System.out.println("❌ Invalid marks! Must be between 0 and 100.");
            return;
        }

        marks[index][subjectNum - 1] = newMarks;
        System.out.println("✅ Marks updated successfully!");
    }

    /**
     * Delete a student
     */
    private static void deleteStudent(Scanner scanner) {
        System.out.print("\nEnter roll number to delete: ");
        int rollNumber = getValidInt(scanner);

        int index = findStudentIndex(rollNumber);

        if (index == -1) {
            System.out.println("❌ Student not found!");
            return;
        }

        System.out.print("Are you sure you want to delete " + studentNames[index] + "? (Y/N): ");
        String confirm = scanner.nextLine().trim().toUpperCase();

        if (!confirm.equals("Y")) {
            System.out.println("❌ Deletion cancelled.");
            return;
        }

        // Shift elements left
        for (int i = index; i < studentCount - 1; i++) {
            studentNames[i] = studentNames[i + 1];
            rollNumbers[i] = rollNumbers[i + 1];
            marks[i] = marks[i + 1];
        }

        studentCount--;
        System.out.println("✅ Student deleted successfully!");
    }

    // ==================== UTILITY METHODS ====================

    /**
     * Find student index by roll number
     */
    private static int findStudentIndex(int rollNumber) {
        for (int i = 0; i < studentCount; i++) {
            if (rollNumbers[i] == rollNumber) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Calculate average marks
     */
    private static double calculateAverage(double[] studentMarks) {
        double sum = 0;
        for (double mark : studentMarks) {
            sum += mark;
        }
        return sum / studentMarks.length;
    }

    /**
     * Calculate grade based on marks
     */
    private static String calculateGrade(double marks) {
        if (marks >= GRADE_A) return "A";
        if (marks >= GRADE_B) return "B";
        if (marks >= GRADE_C) return "C";
        if (marks >= GRADE_D) return "D";
        return "F";
    }

    /**
     * Get valid integer input
     */
    private static int getValidInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Invalid input! Please enter a number: ");
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return value;
    }

    /**
     * Get valid double input
     */
    private static double getValidDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            scanner.nextLine();
            System.out.print("Invalid input! Please enter a number: ");
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        return value;
    }
}
