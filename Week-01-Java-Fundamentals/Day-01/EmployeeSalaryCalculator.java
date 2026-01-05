/**
 * Day 1 - REAL-WORLD CHALLENGE: Employee Salary Calculator
 *
 * This program simulates a real payroll system used in companies.
 * It calculates employee salary with various components like:
 * - Basic Salary
 * - HRA (House Rent Allowance)
 * - DA (Dearness Allowance)
 * - PF (Provident Fund - Deduction)
 * - Professional Tax (Deduction)
 * - Income Tax (Deduction)
 *
 * Learning Outcomes:
 * - Real-world application of arithmetic operators
 * - User input handling
 * - Formatted output
 * - Conditional logic
 * - Professional code documentation
 *
 * Industry Context:
 * This is similar to actual payroll systems used in companies like:
 * - Infosys, TCS, Wipro, Accenture
 * - Any organization with employees
 */

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeSalaryCalculator {

    // Constants for salary components (percentages)
    private static final double HRA_PERCENTAGE = 20.0;     // 20% of basic
    private static final double DA_PERCENTAGE = 15.0;      // 15% of basic
    private static final double PF_PERCENTAGE = 12.0;      // 12% of basic
    private static final double PROFESSIONAL_TAX = 200.0;  // Fixed ₹200

    // Tax slabs (simplified Indian tax system)
    private static final double TAX_SLAB_1 = 250000;  // Up to 2.5L - 0%
    private static final double TAX_SLAB_2 = 500000;  // 2.5L to 5L - 5%
    private static final double TAX_SLAB_3 = 1000000; // 5L to 10L - 20%
    // Above 10L - 30%

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display header
        printHeader();

        // Get employee details
        System.out.print("Enter Employee Name: ");
        String empName = scanner.nextLine();

        System.out.print("Enter Employee ID: ");
        String empId = scanner.nextLine();

        System.out.print("Enter Department: ");
        String department = scanner.nextLine();

        System.out.print("Enter Designation: ");
        String designation = scanner.nextLine();

        System.out.print("Enter Basic Salary (₹): ");
        double basicSalary = scanner.nextDouble();

        System.out.print("Enter Bonus/Incentive (₹) [Enter 0 if none]: ");
        double bonus = scanner.nextDouble();

        System.out.println("\n" + "=".repeat(70));
        System.out.println("Processing salary calculation...");
        System.out.println("=".repeat(70));

        // Calculate salary components
        double hra = calculateHRA(basicSalary);
        double da = calculateDA(basicSalary);
        double grossSalary = basicSalary + hra + da + bonus;

        // Calculate deductions
        double pf = calculatePF(basicSalary);
        double professionalTax = PROFESSIONAL_TAX;
        double annualGross = grossSalary * 12;
        double incomeTax = calculateIncomeTax(annualGross);
        double monthlyIncomeTax = incomeTax / 12;

        double totalDeductions = pf + professionalTax + monthlyIncomeTax;
        double netSalary = grossSalary - totalDeductions;

        // Annual calculations
        double annualNet = netSalary * 12;
        double totalTaxPerYear = incomeTax;

        // Display salary slip
        printSalarySlip(empName, empId, department, designation, basicSalary,
                hra, da, bonus, grossSalary, pf, professionalTax,
                monthlyIncomeTax, totalDeductions, netSalary,
                annualGross, annualNet, totalTaxPerYear);

        // Additional information
        printTaxBreakdown(annualGross, incomeTax);

        scanner.close();
    }

    /**
     * Calculate HRA (House Rent Allowance)
     * Typically 20-40% of basic salary
     */
    private static double calculateHRA(double basicSalary) {
        return (basicSalary * HRA_PERCENTAGE) / 100;
    }

    /**
     * Calculate DA (Dearness Allowance)
     * Typically 10-20% of basic salary
     */
    private static double calculateDA(double basicSalary) {
        return (basicSalary * DA_PERCENTAGE) / 100;
    }

    /**
     * Calculate PF (Provident Fund)
     * Typically 12% of basic salary (employer + employee contribution)
     */
    private static double calculatePF(double basicSalary) {
        return (basicSalary * PF_PERCENTAGE) / 100;
    }

    /**
     * Calculate Income Tax based on Indian tax slabs (simplified)
     * Annual income tax divided by 12 for monthly deduction
     */
    private static double calculateIncomeTax(double annualIncome) {
        double tax = 0;

        if (annualIncome <= TAX_SLAB_1) {
            // No tax up to 2.5L
            tax = 0;
        } else if (annualIncome <= TAX_SLAB_2) {
            // 5% for income between 2.5L and 5L
            tax = (annualIncome - TAX_SLAB_1) * 0.05;
        } else if (annualIncome <= TAX_SLAB_3) {
            // 5% for 2.5L to 5L + 20% for 5L to 10L
            tax = (TAX_SLAB_2 - TAX_SLAB_1) * 0.05;
            tax += (annualIncome - TAX_SLAB_2) * 0.20;
        } else {
            // 5% for 2.5L to 5L + 20% for 5L to 10L + 30% above 10L
            tax = (TAX_SLAB_2 - TAX_SLAB_1) * 0.05;
            tax += (TAX_SLAB_3 - TAX_SLAB_2) * 0.20;
            tax += (annualIncome - TAX_SLAB_3) * 0.30;
        }

        return tax;
    }

    /**
     * Print application header
     */
    private static void printHeader() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("          EMPLOYEE SALARY CALCULATOR - PAYROLL SYSTEM");
        System.out.println("                    XYZ CORPORATION LTD.");
        System.out.println("=".repeat(70));
        System.out.println();
    }

    /**
     * Print detailed salary slip
     */
    private static void printSalarySlip(String empName, String empId,
                                        String department, String designation,
                                        double basicSalary, double hra, double da,
                                        double bonus, double grossSalary,
                                        double pf, double professionalTax,
                                        double incomeTax, double totalDeductions,
                                        double netSalary, double annualGross,
                                        double annualNet, double totalTax) {

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.println("\n" + "╔" + "═".repeat(68) + "╗");
        System.out.println("║" + center("SALARY SLIP", 68) + "║");
        System.out.println("║" + center("Month: " + currentDate.getMonth() + " " +
                currentDate.getYear(), 68) + "║");
        System.out.println("╠" + "═".repeat(68) + "╣");

        // Employee Details
        System.out.printf("║ Employee Name    : %-48s ║%n", empName);
        System.out.printf("║ Employee ID      : %-48s ║%n", empId);
        System.out.printf("║ Department       : %-48s ║%n", department);
        System.out.printf("║ Designation      : %-48s ║%n", designation);
        System.out.printf("║ Pay Date         : %-48s ║%n", currentDate.format(formatter));

        System.out.println("╠" + "═".repeat(68) + "╣");

        // Earnings
        System.out.println("║" + center("EARNINGS", 68) + "║");
        System.out.println("╠" + "═".repeat(68) + "╣");
        System.out.printf("║ Basic Salary                                  ₹%,15.2f ║%n", basicSalary);
        System.out.printf("║ HRA (%.0f%%)                                       ₹%,15.2f ║%n",
                HRA_PERCENTAGE, hra);
        System.out.printf("║ DA (%.0f%%)                                        ₹%,15.2f ║%n",
                DA_PERCENTAGE, da);
        if (bonus > 0) {
            System.out.printf("║ Bonus/Incentive                               ₹%,15.2f ║%n", bonus);
        }
        System.out.println("╠" + "─".repeat(68) + "╣");
        System.out.printf("║ GROSS SALARY                                  ₹%,15.2f ║%n", grossSalary);

        System.out.println("╠" + "═".repeat(68) + "╣");

        // Deductions
        System.out.println("║" + center("DEDUCTIONS", 68) + "║");
        System.out.println("╠" + "═".repeat(68) + "╣");
        System.out.printf("║ Provident Fund (%.0f%%)                           ₹%,15.2f ║%n",
                PF_PERCENTAGE, pf);
        System.out.printf("║ Professional Tax                              ₹%,15.2f ║%n",
                professionalTax);
        System.out.printf("║ Income Tax (Monthly)                          ₹%,15.2f ║%n",
                incomeTax);
        System.out.println("╠" + "─".repeat(68) + "╣");
        System.out.printf("║ TOTAL DEDUCTIONS                              ₹%,15.2f ║%n",
                totalDeductions);

        System.out.println("╠" + "═".repeat(68) + "╣");

        // Net Salary
        System.out.printf("║ NET SALARY (Take Home)                        ₹%,15.2f ║%n",
                netSalary);

        System.out.println("╠" + "═".repeat(68) + "╣");

        // Annual Summary
        System.out.println("║" + center("ANNUAL SUMMARY", 68) + "║");
        System.out.println("╠" + "═".repeat(68) + "╣");
        System.out.printf("║ Annual Gross Salary                           ₹%,15.2f ║%n",
                annualGross);
        System.out.printf("║ Annual Tax                                    ₹%,15.2f ║%n",
                totalTax);
        System.out.printf("║ Annual Net Salary                             ₹%,15.2f ║%n",
                annualNet);

        System.out.println("╚" + "═".repeat(68) + "╝");

        // Summary statistics
        double savingsRate = (netSalary / grossSalary) * 100;
        double taxRate = (totalTax / annualGross) * 100;

        System.out.println("\n📊 QUICK STATS:");
        System.out.println("-".repeat(70));
        System.out.printf("💰 Take-home percentage: %.1f%%%n", savingsRate);
        System.out.printf("📉 Tax burden: %.1f%% of gross income%n", taxRate);
        System.out.printf("💵 Daily income: ₹%.2f%n", netSalary / 30);
        System.out.printf("⏰ Hourly rate (8hrs/day): ₹%.2f%n", netSalary / 30 / 8);
    }

    /**
     * Print tax breakdown
     */
    private static void printTaxBreakdown(double annualGross, double tax) {
        System.out.println("\n💡 TAX BREAKDOWN:");
        System.out.println("-".repeat(70));
        System.out.printf("Annual Gross Income: ₹%,.2f%n", annualGross);

        if (tax == 0) {
            System.out.println("✅ No income tax! (Income below ₹2,50,000)");
        } else {
            System.out.println("Tax calculated as per slabs:");
            if (annualGross > TAX_SLAB_1) {
                System.out.println("  • Up to ₹2,50,000: 0% (₹0)");
            }
            if (annualGross > TAX_SLAB_2) {
                double tax1 = (TAX_SLAB_2 - TAX_SLAB_1) * 0.05;
                System.out.printf("  • ₹2,50,001 to ₹5,00,000: 5%% (₹%,.2f)%n", tax1);
            } else if (annualGross > TAX_SLAB_1) {
                double tax1 = (annualGross - TAX_SLAB_1) * 0.05;
                System.out.printf("  • ₹2,50,001 to ₹%.0f: 5%% (₹%,.2f)%n",
                        annualGross, tax1);
            }
            if (annualGross > TAX_SLAB_3) {
                double tax2 = (TAX_SLAB_3 - TAX_SLAB_2) * 0.20;
                System.out.printf("  • ₹5,00,001 to ₹10,00,000: 20%% (₹%,.2f)%n", tax2);
                double tax3 = (annualGross - TAX_SLAB_3) * 0.30;
                System.out.printf("  • Above ₹10,00,000: 30%% (₹%,.2f)%n", tax3);
            } else if (annualGross > TAX_SLAB_2) {
                double tax2 = (annualGross - TAX_SLAB_2) * 0.20;
                System.out.printf("  • ₹5,00,001 to ₹%.0f: 20%% (₹%,.2f)%n",
                        annualGross, tax2);
            }
            System.out.printf("  Total Annual Tax: ₹%,.2f%n", tax);
        }
    }

    /**
     * Helper method to center text
     */
    private static String center(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - text.length() - padding);
    }
}

/*
 * SAMPLE OUTPUT:
 * ===========================================================================
 *           EMPLOYEE SALARY CALCULATOR - PAYROLL SYSTEM
 *                     XYZ CORPORATION LTD.
 * ===========================================================================
 *
 * Enter Employee Name: Rajesh Kumar
 * Enter Employee ID: EMP001
 * Enter Department: Software Development
 * Enter Designation: Senior Developer
 * Enter Basic Salary (₹): 60000
 * Enter Bonus/Incentive (₹) [Enter 0 if none]: 5000
 *
 * [Displays formatted salary slip with all calculations]
 *
 * REAL-WORLD APPLICATION:
 * This program demonstrates:
 * 1. Professional input/output formatting
 * 2. Business logic implementation
 * 3. Calculations similar to actual HR/Payroll systems
 * 4. Tax calculation based on government rules
 * 5. Code organization and documentation
 *
 * INDUSTRY SKILLS DEMONSTRATED:
 * ✓ Understanding of Indian payroll components
 * ✓ Tax calculation logic
 * ✓ User-friendly output formatting
 * ✓ Professional code documentation
 * ✓ Constants and maintainable code
 * ✓ Real-world problem solving
 */
