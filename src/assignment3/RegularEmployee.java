package assignment3;

public class RegularEmployee extends Employee {

    public RegularEmployee(String id, String name, double grossSalary) throws Exception {
        super(id, name, grossSalary);
    }

    @Override
    public double getGrossSalary() {
        return this.getRawSalary();
    }

    @Override
    public double getNetSalary() {
        double rawSalary = this.getRawSalary();
        return truncateSalary(rawSalary - (rawSalary * BASE_TAX));
    }
}
