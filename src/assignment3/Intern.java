package assignment3;

public class Intern extends Employee {
    private static final double BENEFIT = 1000;

    private int gpa;

    public Intern(String id, String name, double grossSalary, int gpa) {
        super(id, name, grossSalary);
        this.gpa = gpa;
    }

    public void updateGpa(int newGpa) {
        if (newGpa >= 0 && newGpa <= 10) {
            this.gpa = newGpa;
        }
    }

    public double getGrossSalary() {
        if (this.gpa <= 5) {
            return 0;
        }
        else if (gpa < 8) {
            return this.getRawSalary();
        }
        else {
            return this.getRawSalary() + BENEFIT;
        }
    }

    public double getNetSalary() {
        return this.getGrossSalary();
    }

    public String toString() {
        // <name>’s gross salary is <gross_salary> SEK per month. GPA: <gpa>
        return String.format("%s's gross salary is %.2f SEK per month. GPA: %d",
                             this.getName(), this.getGrossSalary(), this.gpa);
    }
}
