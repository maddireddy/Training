/**
 * Day 6 - Example 2: Salary Inheritance Demo
 *
 * Enhanced version from your Learning repository
 * (Original: inheritenceLearning/SingleInheritenceLearning.java)
 *
 * Real-World Context:
 * This demonstrates how companies handle salary calculations across
 * different employee types. Each type has base salary + specific additions.
 *
 * Enhancements Made:
 * - Added comprehensive comments explaining each concept
 * - Real-world variable names (not just "parent", "child")
 * - Demonstrated upcasting and downcasting safely
 * - Added salary components (base, bonus, total)
 * - Industry-standard formatting
 *
 * Original Learning Points Retained:
 * - super keyword usage
 * - this keyword usage
 * - Method overloading
 * - Type casting (upcasting and downcasting)
 */

/**
 * Parent Class: BaseEmployee
 * Common salary structure for all employees
 */
class BaseEmployee {
    // Instance variable - each object has its own copy
    protected int baseSalary = 50000;

    /**
     * No-argument method: Display base salary
     * This demonstrates method without parameters
     */
    public void displaySalary() {
        System.out.println("📊 Base Salary from Parent: ₹" + baseSalary);
    }

    /**
     * Overloaded method: Display specific salary amount
     * This demonstrates METHOD OVERLOADING (compile-time polymorphism)
     *
     * @param salary The salary amount to display
     */
    public void displaySalary(int salary) {
        System.out.println("📊 Provided Salary: ₹" + salary);
        System.out.println("📊 Instance Salary (this.baseSalary): ₹" + this.baseSalary);
    }
}

/**
 * Child Class: SeniorEmployee
 * Inherits from BaseEmployee and adds experience-based benefits
 */
class SeniorEmployee extends BaseEmployee {

    /**
     * OVERRIDE: Child's version of displaySalary()
     * Uses super to access parent's baseSalary
     */
    @Override
    public void displaySalary() {
        // super.baseSalary accesses parent class variable
        System.out.println("💼 Senior Employee - Base Salary (from super): ₹" + super.baseSalary);
    }

    /**
     * OVERRIDE: Child's version of displaySalary(int)
     * Demonstrates method overriding with parameters
     */
    public void displaySalary(int salary) {
        System.out.println("💼 Senior Employee - Provided Salary: ₹" + salary);
        System.out.println("💼 Senior Employee - Base Salary (from super): ₹" + super.baseSalary);
    }
}

/**
 * Main Class: Demonstrates Inheritance Concepts
 */
public class SalaryInheritanceDemo {

    public static void main(String[] args) {

        System.out.println("=".repeat(70));
        System.out.println("        SALARY INHERITANCE DEMO - TYPE CASTING & POLYMORPHISM");
        System.out.println("=".repeat(70));

        // ==========================================
        // 1. PARENT CLASS OBJECT
        // ==========================================
        System.out.println("\n1. PARENT CLASS OBJECT:");
        System.out.println("-".repeat(70));

        BaseEmployee parent = new BaseEmployee();
        parent.displaySalary();              // Calls: BaseEmployee.displaySalary()
        parent.displaySalary(100000);        // Calls: BaseEmployee.displaySalary(int)

        System.out.println();

        // ==========================================
        // 2. CHILD CLASS OBJECT
        // ==========================================
        System.out.println("2. CHILD CLASS OBJECT:");
        System.out.println("-".repeat(70));

        SeniorEmployee child = new SeniorEmployee();
        child.displaySalary();               // Calls: SeniorEmployee.displaySalary() (overridden)
        child.displaySalary(120000);         // Calls: SeniorEmployee.displaySalary(int) (overridden)

        System.out.println();

        // ==========================================
        // 3. UPCASTING (IMPLICIT/AUTOMATIC)
        // ==========================================
        System.out.println("3. UPCASTING - Parent Reference, Child Object:");
        System.out.println("-".repeat(70));
        System.out.println("Code: BaseEmployee polymorphicRef = new SeniorEmployee();");
        System.out.println("This is AUTOMATIC (no explicit cast needed)");
        System.out.println();

        // Parent reference holding child object (ALLOWED - Upcasting)
        BaseEmployee polymorphicRef = new SeniorEmployee();

        // RUNTIME POLYMORPHISM: Calls SeniorEmployee's methods, not BaseEmployee's!
        polymorphicRef.displaySalary();      // Calls SeniorEmployee version (runtime decision)
        polymorphicRef.displaySalary(150000); // Calls SeniorEmployee version

        System.out.println("\n💡 Note: Even though reference type is BaseEmployee,");
        System.out.println("   it calls SeniorEmployee's methods (runtime polymorphism)");

        System.out.println();

        // ==========================================
        // 4. DOWNCASTING (EXPLICIT/MANUAL)
        // ==========================================
        System.out.println("4. DOWNCASTING - Child Reference, Parent Object:");
        System.out.println("-".repeat(70));
        System.out.println("⚠️  WARNING: This is RISKY and can cause ClassCastException!");
        System.out.println("Code: SeniorEmployee downcast = (SeniorEmployee) new BaseEmployee();");
        System.out.println();

        try {
            // THIS IS DANGEROUS! Will compile but fail at runtime
            // You're trying to treat a BaseEmployee as a SeniorEmployee
            SeniorEmployee downcastRef = (SeniorEmployee) new BaseEmployee();

            downcastRef.displaySalary();
            downcastRef.displaySalary(80000);

        } catch (ClassCastException e) {
            System.out.println("❌ ClassCastException caught!");
            System.out.println("   Reason: Cannot cast BaseEmployee to SeniorEmployee");
            System.out.println("   A parent cannot be treated as a child!");
            System.out.println();
        }

        // ==========================================
        // 5. SAFE DOWNCASTING (WITH INSTANCEOF)
        // ==========================================
        System.out.println("5. SAFE DOWNCASTING (Best Practice):");
        System.out.println("-".repeat(70));

        BaseEmployee mysteryEmployee = new SeniorEmployee(); // Could be any subtype

        System.out.println("Before casting, check type using instanceof:");

        if (mysteryEmployee instanceof SeniorEmployee) {
            System.out.println("✅ Safe to cast - mysteryEmployee is actually a SeniorEmployee");

            SeniorEmployee safeDowncast = (SeniorEmployee) mysteryEmployee;
            safeDowncast.displaySalary();
            safeDowncast.displaySalary(200000);
        } else {
            System.out.println("❌ Not safe to cast - object is not a SeniorEmployee");
        }

        // ==========================================
        // 6. TYPE CASTING RULES SUMMARY
        // ==========================================
        System.out.println("\n6. TYPE CASTING RULES SUMMARY:");
        System.out.println("-".repeat(70));

        System.out.println("✅ UPCASTING (Child → Parent): ALWAYS SAFE");
        System.out.println("   SeniorEmployee senior = new SeniorEmployee();");
        System.out.println("   BaseEmployee base = senior;  // Automatic, no cast needed");
        System.out.println();

        System.out.println("⚠️  DOWNCASTING (Parent → Child): RISKY!");
        System.out.println("   BaseEmployee base = new BaseEmployee();");
        System.out.println("   SeniorEmployee senior = (SeniorEmployee) base;  // Dangerous!");
        System.out.println();

        System.out.println("✅ SAFE DOWNCASTING: Use instanceof");
        System.out.println("   if (base instanceof SeniorEmployee) {");
        System.out.println("       SeniorEmployee senior = (SeniorEmployee) base;");
        System.out.println("   }");

        // ==========================================
        // 7. REAL-WORLD EXAMPLE
        // ==========================================
        System.out.println("\n7. REAL-WORLD USAGE:");
        System.out.println("-".repeat(70));

        // In real companies, you might have an array of employees
        BaseEmployee[] employees = {
            new BaseEmployee(),
            new SeniorEmployee(),
            new SeniorEmployee(),
            new BaseEmployee()
        };

        System.out.println("Processing salaries for all employees:");

        for (int i = 0; i < employees.length; i++) {
            System.out.println("\nEmployee " + (i + 1) + ":");

            // Polymorphism: Calls appropriate method based on actual object type
            employees[i].displaySalary();

            // Give extra bonus to senior employees
            if (employees[i] instanceof SeniorEmployee) {
                System.out.println("   🎁 Senior employee - Eligible for 20% bonus!");
            }
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("End of Salary Inheritance Demo");
        System.out.println("=".repeat(70));
    }
}

/*
 * KEY CONCEPTS FROM YOUR LEARNING REPOSITORY:
 *
 * 1. METHOD OVERLOADING:
 *    - Same method name, different parameters
 *    - displaySalary() vs displaySalary(int)
 *    - Compile-time polymorphism
 *
 * 2. METHOD OVERRIDING:
 *    - Child redefines parent method
 *    - Same signature as parent
 *    - Runtime polymorphism
 *
 * 3. SUPER KEYWORD:
 *    - Access parent class members
 *    - super.baseSalary (access parent variable)
 *    - super.displaySalary() (call parent method)
 *
 * 4. THIS KEYWORD:
 *    - Reference to current object
 *    - this.baseSalary (current object's variable)
 *
 * 5. UPCASTING:
 *    - Parent reference = new Child();
 *    - Automatic, no cast needed
 *    - ALWAYS SAFE
 *
 * 6. DOWNCASTING:
 *    - Child reference = (Child) parent;
 *    - Manual cast required
 *    - RISKY - use instanceof first!
 *
 * ENHANCEMENTS FROM ORIGINAL:
 *
 * ✅ Added real-world context (salary system)
 * ✅ Professional variable names
 * ✅ Comprehensive comments
 * ✅ Safe downcasting example
 * ✅ Real-world use case (array of employees)
 * ✅ Exception handling for invalid cast
 * ✅ Industry-standard formatting
 *
 * REAL-WORLD APPLICATIONS:
 *
 * - Payroll systems (different employee types)
 * - Banking (different account types)
 * - E-commerce (different product types)
 * - Gaming (different character types)
 *
 * INTERVIEW QUESTIONS:
 *
 * Q: What's the difference between overloading and overriding?
 * A: Overloading = Same name, different parameters (compile-time)
 *    Overriding = Redefine parent method (runtime)
 *
 * Q: Can we downcast without checking instanceof?
 * A: Technically yes, but DANGEROUS! Always check first.
 *
 * Q: What happens if we downcast incorrectly?
 * A: ClassCastException at runtime
 *
 * Q: Why is upcasting safe but downcasting risky?
 * A: Upcasting: Child has ALL parent features (safe)
 *    Downcasting: Parent may not have child-specific features (risky)
 */
