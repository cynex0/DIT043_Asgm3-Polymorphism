package assignment3;

public class Director extends Manager {
    private static final double BENEFIT = 5000.0;

    private Department department;

    public Director(String id, String name, double grossSalary, String degree, String dept) {
        super(id, name, grossSalary, degree);
        updateDepartment(dept);
    }

    public double getGrossSalary() {
        return super.getGrossSalary() + BENEFIT;
    }

    public double getNetSalary() {
        double grossSalary = this.getGrossSalary();
        if (grossSalary < 30000.0) {
            return super.getNetSalary();
        } else if (grossSalary <= 50000.0) {
            return truncateSalary(grossSalary - (grossSalary * 0.2));
        } else {
            return grossSalary - (0.2 * 30000.0) - (0.4 * (this.getGrossSalary() - 30000.0));
        }
    }

    public void updateDepartment(String newDept) {
        switch (newDept) {
            case "Human Resources" -> this.department = Department.HUMAN_RESOURCES;
            case "Technical" -> this.department = Department.TECHNICAL;
            case "Business" -> this.department = Department.BUSINESS;
        }
    }

    public String toString() {
        // <degree> <name>’s gross salary is <gross_salary> SEK per month. Dept: <department>
        return super.toString() + " Dept: " + this.department.toString();
    }
}
