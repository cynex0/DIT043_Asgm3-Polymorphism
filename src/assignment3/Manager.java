package assignment3;

public class Manager extends Employee {
    private String degree;
    private double bonusMultiplier;

    public Manager(String id, String name, double grossSalary, String degree) throws EmployeeAttributeException {
        super(id, name, grossSalary);
        updateDegree(degree);
    }

    public void updateDegree(String newDegree) throws EmployeeAttributeException {
        if (newDegree.equals("BSc") || newDegree.equals("MSc") || newDegree.equals("PhD")) {
            this.degree = newDegree;
        } else {
            throw new EmployeeAttributeException("Degree must be one of the options: BSc, MSc or PhD.");
        }

        switch (degree) {
            case "BSc" -> bonusMultiplier = 0.1;
            case "MSc" -> bonusMultiplier = 0.2;
            case "PhD" -> bonusMultiplier = 0.35;
            default -> bonusMultiplier = 1;
        }
    }

    public double getGrossSalary() {
        double rawSalary = this.getBaseSalary();
        return rawSalary + (rawSalary * bonusMultiplier);
    }

    public double getNetSalary() {
        double grossSalary = this.getGrossSalary();
        return grossSalary - (grossSalary * BASE_TAX);
    }

    public String getDegree() {
        return this.degree;
    }

    public String toString() {
        // <degree> <name>'s gross salary is <gross_salary> SEK per month.
        return String.format("%s %s's gross salary is %.2f SEK per month.",
                             this.degree, this.getName(), SalaryTruncator.truncateSalary(this.getGrossSalary()));
    }
}
