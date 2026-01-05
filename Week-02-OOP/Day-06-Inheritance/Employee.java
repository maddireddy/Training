/**
 * Day 6 - Example 1: Employee Hierarchy (Single Inheritance)
 *
 * Real-World Context:
 * This is how HR/Payroll systems model employees in companies like:
 * - TCS, Infosys, Wipro (Employee management)
 * - Banking systems (Staff hierarchy)
 * - Any organization with employees
 *
 * IS-A Relationship:
 * - Manager IS-A Employee
 * - Developer IS-A Employee
 * - Every Manager has employee properties (name, id, salary) + managerial properties
 *
 * Learning Objectives:
 * - Understand code reusability
 * - Learn super keyword
 * - Method overriding
 * - Constructor chaining
 */

/**
 * Parent Class: Employee
 * Base class containing common properties of all employees
 *
 * In real companies, this would be in a separate file: Employee.java
 */
class Employee {
    // Protected: Accessible in child classes
    protected String employeeId;
    protected String name;
    protected double baseSalary;
    protected String department;

    /**
     * Constructor
     * Called when Employee object is created
     */
    public Employee(String employeeId, String name, double baseSalary, String department) {
        this.employeeId = employeeId;
        this.name = name;
        this.baseSalary = baseSalary;
        this.department = department;

        System.out.println("Employee constructor called");
    }

    /**
     * Calculate annual salary
     * Basic formula: base salary * 12
     */
    public double calculateAnnualSalary() {
        return baseSalary * 12;
    }

    /**
     * Display employee information
     * Can be overridden by child classes
     */
    public void displayInfo() {
        System.out.println("╔" + "═".repeat(50) + "╗");
        System.out.println("║" + center("EMPLOYEE INFORMATION", 50) + "║");
        System.out.println("╠" + "═".repeat(50) + "╣");
        System.out.printf("║ ID          : %-37s ║%n", employeeId);
        System.out.printf("║ Name        : %-37s ║%n", name);
        System.out.printf("║ Department  : %-37s ║%n", department);
        System.out.printf("║ Base Salary : ₹%-36.2f ║%n", baseSalary);
        System.out.printf("║ Annual      : ₹%-36.2f ║%n", calculateAnnualSalary());
        System.out.println("╚" + "═".repeat(50) + "╝");
    }

    /**
     * Give raise to employee
     * @param percentage Percentage increase (e.g., 10 for 10%)
     */
    public void giveRaise(double percentage) {
        double oldSalary = baseSalary;
        baseSalary += baseSalary * (percentage / 100);
        System.out.println("💰 Salary increased from ₹" + oldSalary + " to ₹" + baseSalary);
    }

    // Helper method for formatting
    private String center(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - text.length() - padding);
    }
}

/**
 * Child Class: Manager
 * Inherits from Employee and adds managerial features
 *
 * Real-world: This is how companies differentiate managers from regular employees
 */
class Manager extends Employee {
    private int teamSize;
    private double bonus;

    /**
     * Constructor
     * Notice: super() calls parent constructor FIRST
     */
    public Manager(String employeeId, String name, double baseSalary,
                   String department, int teamSize) {
        // MUST call parent constructor first
        super(employeeId, name, baseSalary, department);

        this.teamSize = teamSize;
        this.bonus = 0;

        System.out.println("Manager constructor called");
    }

    /**
     * Calculate bonus based on team size
     * Real logic: Managers get bonus based on team performance
     */
    public void calculateBonus() {
        // Real formula: ₹5000 per team member
        this.bonus = teamSize * 5000;
        System.out.println("✅ Bonus calculated: ₹" + bonus);
    }

    /**
     * OVERRIDE: Enhanced annual salary calculation
     * Manager salary = (Base * 12) + Bonus
     *
     * @return Annual salary including bonus
     */
    @Override  // Annotation tells compiler we're overriding
    public double calculateAnnualSalary() {
        double baseSalary = super.calculateAnnualSalary(); // Call parent method
        return baseSalary + bonus;
    }

    /**
     * OVERRIDE: Enhanced display with manager-specific info
     */
    @Override
    public void displayInfo() {
        System.out.println("╔" + "═".repeat(50) + "╗");
        System.out.println("║" + center("MANAGER INFORMATION", 50) + "║");
        System.out.println("╠" + "═".repeat(50) + "╣");
        System.out.printf("║ ID          : %-37s ║%n", employeeId);
        System.out.printf("║ Name        : %-37s ║%n", name);
        System.out.printf("║ Department  : %-37s ║%n", department);
        System.out.printf("║ Base Salary : ₹%-36.2f ║%n", baseSalary);
        System.out.printf("║ Team Size   : %-37d ║%n", teamSize);
        System.out.printf("║ Bonus       : ₹%-36.2f ║%n", bonus);
        System.out.printf("║ Annual Total: ₹%-36.2f ║%n", calculateAnnualSalary());
        System.out.println("╚" + "═".repeat(50) + "╝");
    }

    /**
     * Manager-specific method
     * Only managers can conduct meetings
     */
    public void conductMeeting(String topic) {
        System.out.println("🗣️  Manager " + name + " conducting meeting on: " + topic);
    }

    // Helper method
    private String center(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - text.length() - padding);
    }
}

/**
 * Child Class: Developer
 * Inherits from Employee and adds developer-specific features
 */
class Developer extends Employee {
    private String programmingLanguage;
    private int projectsCompleted;

    public Developer(String employeeId, String name, double baseSalary,
                     String department, String programmingLanguage) {
        super(employeeId, name, baseSalary, department);
        this.programmingLanguage = programmingLanguage;
        this.projectsCompleted = 0;
    }

    /**
     * Developer completes a project
     */
    public void completeProject() {
        projectsCompleted++;
        System.out.println("🎉 Project completed! Total: " + projectsCompleted);

        // Give automatic raise after every 5 projects
        if (projectsCompleted % 5 == 0) {
            giveRaise(10); // 10% raise
        }
    }

    @Override
    public void displayInfo() {
        super.displayInfo(); // Use parent's display
        System.out.println("Programming Language: " + programmingLanguage);
        System.out.println("Projects Completed  : " + projectsCompleted);
    }
}

/**
 * Main Class: Test Inheritance
 */
public class EmployeeHierarchyDemo {

    public static void main(String[] args) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("       EMPLOYEE MANAGEMENT SYSTEM - INHERITANCE DEMO");
        System.out.println("=".repeat(60));

        // ==========================================
        // 1. CREATE REGULAR EMPLOYEE
        // ==========================================
        System.out.println("\n1. CREATING REGULAR EMPLOYEE:");
        System.out.println("-".repeat(60));

        Employee emp1 = new Employee("E001", "Rajesh Kumar", 50000, "IT");
        emp1.displayInfo();

        // ==========================================
        // 2. CREATE MANAGER (INHERITS FROM EMPLOYEE)
        // ==========================================
        System.out.println("\n2. CREATING MANAGER (CHILD OF EMPLOYEE):");
        System.out.println("-".repeat(60));

        Manager mgr1 = new Manager("M001", "Priya Sharma", 80000, "Engineering", 10);
        mgr1.calculateBonus();
        mgr1.displayInfo();

        System.out.println("\nManager-specific action:");
        mgr1.conductMeeting("Q1 Planning");

        // ==========================================
        // 3. CREATE DEVELOPER
        // ==========================================
        System.out.println("\n3. CREATING DEVELOPER:");
        System.out.println("-".repeat(60));

        Developer dev1 = new Developer("D001", "Amit Patel", 60000, "Backend", "Java");
        dev1.displayInfo();

        System.out.println("\nDeveloper completes projects:");
        for (int i = 1; i <= 5; i++) {
            dev1.completeProject();
        }

        System.out.println("\nAfter 5 projects:");
        dev1.displayInfo();

        // ==========================================
        // 4. POLYMORPHISM (PARENT REFERENCE, CHILD OBJECT)
        // ==========================================
        System.out.println("\n4. POLYMORPHISM - PARENT REFERENCE:");
        System.out.println("-".repeat(60));

        // This is allowed: Parent reference can hold child object
        Employee emp2 = new Manager("M002", "Vikram Singh", 90000, "Sales", 15);

        // Runtime polymorphism: Calls Manager's displayInfo(), not Employee's
        emp2.displayInfo();

        // emp2.conductMeeting(); // ERROR! Parent reference can't call child methods
        // Need to cast to Manager first
        if (emp2 instanceof Manager) {
            Manager mgr2 = (Manager) emp2;
            mgr2.conductMeeting("Sales Strategy");
        }

        // ==========================================
        // 5. GIVE RAISES TO ALL EMPLOYEES
        // ==========================================
        System.out.println("\n5. ANNUAL APPRAISAL - GIVING RAISES:");
        System.out.println("-".repeat(60));

        emp1.giveRaise(12); // 12% raise
        mgr1.giveRaise(15); // 15% raise for managers
        dev1.giveRaise(10); // 10% raise

        // ==========================================
        // 6. DEMONSTRATION OF SUPER KEYWORD
        // ==========================================
        System.out.println("\n6. SUPER KEYWORD DEMONSTRATION:");
        System.out.println("-".repeat(60));
        System.out.println("When Manager.calculateAnnualSalary() is called:");
        System.out.println("1. It calls super.calculateAnnualSalary() (Employee's method)");
        System.out.println("2. Then adds bonus on top");
        System.out.println("Result: " + mgr1.calculateAnnualSalary());

        System.out.println("\n" + "=".repeat(60));
        System.out.println("End of Inheritance Demo");
        System.out.println("=".repeat(60));
    }
}

/*
 * KEY CONCEPTS DEMONSTRATED:
 *
 * 1. INHERITANCE (extends):
 *    - Manager extends Employee
 *    - Developer extends Employee
 *    - Reuses code, avoids duplication
 *
 * 2. SUPER KEYWORD:
 *    - super() calls parent constructor
 *    - super.method() calls parent method
 *    - Must be first statement in child constructor
 *
 * 3. THIS KEYWORD:
 *    - Refers to current object
 *    - Used to differentiate parameter from instance variable
 *
 * 4. METHOD OVERRIDING:
 *    - Child redefines parent method
 *    - @Override annotation (best practice)
 *    - Runtime polymorphism
 *
 * 5. ACCESS MODIFIERS:
 *    - protected: Accessible in child classes
 *    - private: Not accessible in child classes
 *    - public: Accessible everywhere
 *
 * 6. POLYMORPHISM:
 *    - Employee emp = new Manager(); (Allowed)
 *    - Manager mgr = new Employee(); (Not allowed)
 *
 * 7. INSTANCEOF:
 *    - Check object type before casting
 *    - Prevents ClassCastException
 *
 * REAL-WORLD APPLICATIONS:
 *
 * - HR/Payroll systems (TCS, Infosys use similar hierarchy)
 * - Banking (Account types)
 * - E-commerce (Product categories)
 * - Netflix (Content types: Movie, Series, Documentary)
 * - Social Media (User types: Regular, Premium, Admin)
 *
 * INTERVIEW TIPS:
 *
 * Q: Why use inheritance?
 * A: Code reusability, maintainability, polymorphism
 *
 * Q: Can we achieve multiple inheritance?
 * A: No, but use interfaces
 *
 * Q: What's the difference between IS-A and HAS-A?
 * A: IS-A = Inheritance (Manager IS-A Employee)
 *    HAS-A = Composition (Employee HAS-A Address)
 */
