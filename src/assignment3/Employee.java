package assignment3;

public abstract class Employee implements Comparable<Employee> {
    protected static final double BASE_TAX = 0.1;

    private final String id;
    private String name;
    private double baseSalary;

    // constructor
    public Employee(String id, String name, double baseSalary) throws EmployeeAttributeException {
        if (id.isBlank()) {
            throw new EmployeeAttributeException("ID cannot be blank.");
        }
        if (name.isBlank()) {
            throw new EmployeeAttributeException("Name cannot be blank.");
        }
        if (baseSalary <= 0) {
            throw new EmployeeAttributeException("Salary must be greater than zero.");
        }

        this.id = id;
        this.name = name;
        this.baseSalary = SalaryTruncator.truncateSalary(baseSalary);
    }

    public String getName() {
        return this.name;
    }

    public double getBaseSalary() {
        return this.baseSalary;
    }

    public abstract double getGrossSalary();

    public abstract double getNetSalary();
    // setters

    public void setName(String name) throws EmployeeAttributeException {
        if (name.isBlank()) {
            throw new EmployeeAttributeException("Name cannot be blank.");
        }
        this.name = name;
    }

    public void setBaseSalary(double newSalary) throws EmployeeAttributeException {
        if (newSalary <= 0) {
            throw new EmployeeAttributeException("Salary must be greater than zero.");
        }
        this.baseSalary = SalaryTruncator.truncateSalary(newSalary);
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

    public abstract String toString();

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
