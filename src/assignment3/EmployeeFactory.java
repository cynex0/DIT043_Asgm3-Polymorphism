package assignment3;

public class EmployeeFactory {
    public static Employee createRegularEmployee(String id, String name, double baseSalary) throws Exception {
        return new RegularEmployee(id, name, baseSalary);
    }

    public static Employee createManager(String id, String name, double grossSalary, String degree) throws Exception {
        return new Manager(id, name, grossSalary, degree);
    }

    public static Employee createDirector(String id, String name, double grossSalary, String degree, String dept) throws Exception {
        return new Director(id, name, grossSalary, degree, dept);
    }

    public static Employee createIntern(String id, String name, double grossSalary, int gpa) throws Exception {
        return new Intern(id, name, grossSalary, gpa);
    }
}
