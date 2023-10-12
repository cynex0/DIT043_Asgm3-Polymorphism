package assignment3;

public abstract class Employee implements Comparable<Employee> {
    public static final double BASE_TAX = 0.1;

    private final String id;
    private String name;
    private double rawSalary;

    // constructor
    public Employee(String id, String name, double grossSalary) throws Exception {
        if (id.isBlank()) {
            throw new Exception("ID cannot be blank.");
        }
        if (name.isBlank()) {
            throw new Exception("Name cannot be blank.");
        }
        if (grossSalary <= 0) {
            throw new Exception("Salary must be greater than zero.");
        }

        this.id = id;
        this.name = name;
        this.rawSalary = truncateSalary(grossSalary);
    }

    protected double truncateSalary(double salary) {
        int salaryInt = (int)(salary * 100);
        double truncatedSalary = salaryInt / 100.0;
        return truncatedSalary;
    }

    // getters
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getRawSalary() {
        return this.rawSalary;
    }

    public abstract double getGrossSalary();

    public abstract double getNetSalary();
    // setters

    public void setName(String name) throws Exception {
        if (name.isBlank()) {
            throw new Exception("Name cannot be blank.");
        }
        this.name = name;
    }

    public void updateRawSalary(double newSalary) throws Exception {
        if (newSalary <= 0) {
            throw new Exception("Salary must be greater than zero.");
        }
        this.rawSalary = truncateSalary(newSalary);
    }

    // overrides
    @Override
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

    @Override
    public String toString() {
        // <name>’s gross salary is <gross_salary> SEK per month.
        double truncatedSalary = truncateSalary(this.rawSalary);
        return String.format("%s's gross salary is %.2f SEK per month.", this.name, truncatedSalary);
    }

    @Override
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
