package assignment3;

/* Each employee has an ID (String), a name and a gross salary specified during the Employee’s creation,
and can be later retrieved. The name and salary of an employee can be changed, but their ID cannot change.
Two employees are equals if they have the same ID. Employees should be represented as the following String:
 */

public class Employee implements Comparable<Employee> {
    public static final double INCOME_TAX = 0.1;

    private final String id;
    private String name;
    private double baseSalary;

    // constructor
    public Employee(String id, String name, double grossSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = truncateSalary(grossSalary);
    }

    // getters
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getGrossSalary() {
        return this.baseSalary;
    }

    public double getNetSalary() {
        return truncateSalary(this.baseSalary - (this.baseSalary * INCOME_TAX));
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setGrossSalary(double grossSalary) {
        this.baseSalary = truncateSalary(grossSalary);
    }

    protected double truncateSalary(double salary) {
        int salaryInt = (int)(salary * 100);
        double truncatedSalary = salaryInt / 100.0;
        return truncatedSalary;
    }

    // overrides
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!(object instanceof Employee)) {
            return false;
        }

        Employee other = (Employee)object;
        return this.id.equals(other.id);
    }

    public String toString() {
        // <name>’s gross salary is <gross_salary> SEK per month.
        double truncatedSalary = truncateSalary(this.baseSalary);
        return String.format("%s's gross salary is %.2f SEK per month.", this.name, truncatedSalary);
    }

    public int compareTo(Employee empl) {
        if (this.getGrossSalary() > empl.getGrossSalary()) {
            return 1;
        }
        if (this.getGrossSalary() < empl.getGrossSalary()) {
            return -1;
        }
        return 0;
    }
}
