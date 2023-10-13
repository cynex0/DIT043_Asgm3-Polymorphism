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
        return truncateSalary(rawSalary - (rawSalary * BASE_TAX));
    }
}
