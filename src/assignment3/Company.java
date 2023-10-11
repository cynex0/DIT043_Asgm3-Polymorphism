package assignment3;
import java.util.*;

/*
The company begins with no Employee registered and should: i) register, ii) remove, iii) retrieve,
iv) update its employees, calculate the total expenses by paying v) gross salaries and vi) net salaries, and, finally,
vii) inform the total number of employees registered. In addition, be aware of the following cases:
*/
public class Company {
    private static final String EOL = System.lineSeparator();

    private LinkedHashMap<String, Employee> employees;

    public Company() {
        this.employees = new LinkedHashMap<>();
    }

    public String createEmployee(String id, String name, double grossSalary) {
        Employee newEmployee = new RegularEmployee(id, name, grossSalary);
        employees.put(id, newEmployee);

        // Employee <ID> was registered successfully.
        return String.format("Employee %s was registered successfully.", id);
    }

    public String createEmployee(String id, String name, double grossSalary, String degree) {
        Employee newEmployee = new Manager(id, name, grossSalary, degree);
        employees.put(id, newEmployee);

        // Employee <ID> was registered successfully.
        return String.format("Employee %s was registered successfully.", id);
    }

    public String createEmployee(String id, String name, double grossSalary, String degree, String dept) {
        Employee newEmployee = new Director(id, name, grossSalary, degree, dept);
        employees.put(id, newEmployee);

        // Employee <ID> was registered successfully.
        return String.format("Employee %s was registered successfully.", id);
    }

    public String createEmployee(String id, String name, double grossSalary, int gpa) {
        Employee newEmployee = new Intern(id, name, grossSalary, gpa);
        employees.put(id, newEmployee);

        // Employee <ID> was registered successfully.
        return String.format("Employee %s was registered successfully.", id);
    }

    public double getNetSalary(String id) {
        Employee employee = employees.get(id); // TODO: Exception
        return employee.getNetSalary();
    }
    
    private Employee retrieveEmployee(String id) {
//        if (!this.employees.containsKey(id)) {
//            throw ; // TODO: Exception
//        }
        Employee empl = employees.get(id);
        return empl;
    }

    public String removeEmployee(String id) {
        this.employees.remove(id); // TODO: catch exception
        return "Employee " + id + " was successfully removed.";
    }

    public String printEmployee(String id) {
        Employee empl = this.retrieveEmployee(id);
        return empl.toString();
    }

    public String printAllEmployees() {
        String message = "All registered employees:" + EOL;

        for (Employee empl : this.employees.values()) {
            message += empl.toString() + EOL;
        }

        return message;
    }

    public double getTotalNetSalary() {
        double total = 0.0;
        for (Employee empl : this.employees.values()) {
            total = total + empl.getNetSalary();
        }
        return total;
    }

    public String printSortedEmployees() {
        String message = "Employees sorted by gross salary (ascending order):" + EOL;
        Collection<Employee> values = this.employees.values();
        ArrayList<Employee> sortedEmployees = new ArrayList<>(values);
        Collections.sort(sortedEmployees);

        for (Employee empl : sortedEmployees) {
            message += empl.toString() + EOL;
        }

        return message;
    }


    public Map<String, Integer> mapEachDegree() {
        HashMap<String, Integer> degreesMap = new HashMap<>();
        degreesMap.put("BSc", 0);
        degreesMap.put("MSc", 0);
        degreesMap.put("PhD", 0);

        for (Employee empl : this.employees.values()) {
            if (empl instanceof Manager) {
                Manager manager = (Manager)empl;
                String degree = manager.getDegree();
                degreesMap.put(degree, degreesMap.get(degree) + 1);
            }
        }

        // clean up empty keys
        Iterator<Map.Entry<String, Integer>> iterator = degreesMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getValue() == 0){
                iterator.remove();
            }
        }

        return degreesMap;
    }

    public String printByDegree() { // never used, but required in specification
        String message = "Academic background of employees:" + EOL;
        Map<String, Integer> degreesMap = mapEachDegree();

        for (Map.Entry<String, Integer> entry : degreesMap.entrySet()) {
            message += entry.getKey() + ": => " + entry.getValue();
        }
        return message;
    }

    public String updateEmployeeName(String id, String newName) {
        Employee empl = this.retrieveEmployee(id);
        empl.setName(newName);
        return "Employee " + id + " was updated successfully";
    }

    public String updateGrossSalary(String id, double newGross) {
        Employee empl = this.retrieveEmployee(id);
        empl.updateRawSalary(newGross);
        return "Employee " + id + " was updated successfully";
    }

    public String updateInternGPA(String id, int newGpa) {
        if (!this.employees.containsKey(id)) {
            return "Error"; // TODO: Exception
        }
        Employee empl = employees.get(id);
        if (!(empl instanceof Intern)) {
            return "Error"; // TODO: Exception
        }

        Intern intern = (Intern)empl;
        intern.updateGpa(newGpa);
        return "Employee " + id + " was updated successfully";
    }

    public String updateManagerDegree(String id, String newDegree) {
        if (!this.employees.containsKey(id)) {
            return "Error"; // TODO: Exception
        }
        Employee empl = employees.get(id);
        if (!(empl instanceof Manager)) {
            return "Error"; // TODO: Exception
        }

        Manager manager = (Manager)empl;
        manager.updateDegree(newDegree);
        return "Employee " + id + " was updated successfully";
    }

    public String updateDirectorDept(String id, String newDept) {
        if (!this.employees.containsKey(id)) {
            return "Error"; // TODO: Exception
        }
        Employee empl = employees.get(id);
        if (!(empl instanceof Director)) {
            return "Error"; // TODO: Exception
        }

        Director director = (Director)empl;
        director.updateDepartment(newDept);
        return "Employee " + id + " was updated successfully";
    }

    public String promoteToManager(String id, String degree) {
        Employee empl = retrieveEmployee(id);
        String name = empl.getName();
        double rawSalary = empl.getRawSalary();

        Manager manager = new Manager(id, name, rawSalary, degree);

        this.employees.remove(id);
        this.employees.put(id, manager);

        return id + " promoted successfully to Manager.";
    }

    public String promoteToDirector(String id, String degree, String dept) {
        Employee empl = retrieveEmployee(id);
        String name = empl.getName();
        double rawSalary = empl.getRawSalary();

        Director director = new Director(id, name, rawSalary, degree, dept);

        this.employees.remove(id);
        this.employees.put(id, director);

        return id + " promoted successfully to Director.";
    }

    public String promoteToIntern(String id, int gpa) {
        Employee empl = retrieveEmployee(id);
        String name = empl.getName();
        double rawSalary = empl.getRawSalary();

        Intern intern = new Intern(id, name, rawSalary, gpa);

        this.employees.remove(id);
        this.employees.put(id, intern);

        return id + " promoted successfully to Intern.";
    }


}
