package assignment3;

public class SalaryTruncator {
    public static double truncateSalary(double salary) {
        int salaryInt = (int)(salary * 100);
        double truncatedSalary = salaryInt / 100.0;
        return truncatedSalary;
    }
}
