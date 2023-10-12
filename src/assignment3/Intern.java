package assignment3;

public class Intern extends Employee {
    private static final double BENEFIT = 1000;

    private int gpa;

    public Intern(String id, String name, double grossSalary, int gpa) throws Exception {
        super(id, name, grossSalary);
        updateGpa(gpa);
    }

    public void updateGpa(int newGpa) throws Exception {
        if (newGpa >= 0 && newGpa <= 10) {
            this.gpa = newGpa;
        }
        else {
            throw new Exception(newGpa + " outside range. Must be between 0-10.");
        }
    }

    public double getGrossSalary() {
        if (this.gpa <= 5) {
            return 0;
        }
        else if (gpa < 8) {
            return this.getBaseSalary();
        }
        else {
            return this.getBaseSalary() + BENEFIT;
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
