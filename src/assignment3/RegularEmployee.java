package assignment3;

public class RegularEmployee extends Employee {

    public RegularEmployee(String id, String name, double grossSalary) throws EmployeeAttributeException {
        super(id, name, grossSalary);
    }

    @Override
    public double getGrossSalary() {
        return this.getBaseSalary();
    }

    @Override
    public double getNetSalary() {
        double rawSalary = this.getBaseSalary();
        return (rawSalary - (rawSalary * BASE_TAX));
    }

    @Override
    public String toString() {
        // <name>'s gross salary is <gross_salary> SEK per month.
        return String.format("%s's gross salary is %.2f SEK per month.", this.getName(), this.getGrossSalary());
    }
}
