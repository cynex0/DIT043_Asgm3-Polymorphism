package assignment3;
import java.util.*;

public class Company {
    private static final String EOL = System.lineSeparator();

    private LinkedHashMap<String, Employee> employees;

    public Company() {
        this.employees = new LinkedHashMap<>();
    }

    public String createEmployee(String id, String name, double grossSalary) throws Exception {
        Employee newEmployee = new RegularEmployee(id, name, grossSalary);
        return this.putEmployee(id, newEmployee);
    }

    public String createEmployee(String id, String name, double grossSalary, String degree) throws Exception {
        Employee newEmployee = new Manager(id, name, grossSalary, degree);
        return this.putEmployee(id, newEmployee);
    }

    public String createEmployee(String id, String name, double grossSalary, String degree, String dept) throws Exception {
        Employee newEmployee = new Director(id, name, grossSalary, degree, dept);
        return this.putEmployee(id, newEmployee);
    }

    public String createEmployee(String id, String name, double grossSalary, int gpa) throws Exception {
        Employee newEmployee = new Intern(id, name, grossSalary, gpa);
        return this.putEmployee(id, newEmployee);
    }

    private Employee retrieveEmployee(String id) throws Exception {
        if (!this.employees.containsKey(id)) {
            throw new Exception("Employee " + id + " was not registered yet.");
        }
        Employee empl = employees.get(id);
        return empl;
    }

    private String putEmployee(String id, Employee empl) throws Exception {
        if (this.employees.containsKey(id)) {
            throw new Exception("Cannot register. ID " + id + " is already registered.");
        }

        employees.put(id, empl);
        return String.format("Employee %s was registered successfully.", id); // Employee <ID> was registered successfully.
    }

    public double getNetSalary(String id) throws Exception {
        Employee employee = retrieveEmployee(id); // TODO: Exception
        return employee.getNetSalary();
    }

    public String removeEmployee(String id) throws Exception {
        if (!this.employees.containsKey(id)) {
            throw new Exception("Employee " + id + " was not registered yet.");
        }

        this.employees.remove(id);
        return "Employee " + id + " was successfully removed.";
    }

    public String printEmployee(String id) throws Exception {
        Employee empl = this.retrieveEmployee(id);
        return empl.toString();
    }

    public String printAllEmployees() throws Exception {
        if (employees.isEmpty()) {
            throw new Exception("No employees registered yet.");
        }

        String message = "All registered employees:" + EOL;
        for (Employee empl : this.employees.values()) {
            message += empl.toString() + EOL;
        }

        return message;
    }

    public double getTotalNetSalary() throws Exception {
        if (employees.isEmpty()) {
            throw new Exception("No employees registered yet.");
        }

        double total = 0.0;
        for (Employee empl : this.employees.values()) {
            total = total + empl.getNetSalary();
        }
        return total;
    }

    public String printSortedEmployees() throws Exception {
        if (employees.isEmpty()) {
            throw new Exception("No employees registered yet.");
        }

        String message = "Employees sorted by gross salary (ascending order):" + EOL;
        Collection<Employee> values = this.employees.values();
        ArrayList<Employee> sortedEmployees = new ArrayList<>(values);
        Collections.sort(sortedEmployees);

        for (Employee empl : sortedEmployees) {
            message += empl.toString() + EOL;
        }

        return message;
    }


    public Map<String, Integer> mapEachDegree() throws Exception {
        if (employees.isEmpty()) {
            throw new Exception("No employees registered yet.");
        }

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

    public String printByDegree() throws Exception { // never used, but required in specification
        String message = "Academic background of employees:" + EOL;
        Map<String, Integer> degreesMap = mapEachDegree();

        for (Map.Entry<String, Integer> entry : degreesMap.entrySet()) {
            message += entry.getKey() + ": => " + entry.getValue();
        }
        return message;
    }

    public String updateEmployeeName(String id, String newName) throws Exception {
        Employee empl = this.retrieveEmployee(id);
        empl.setName(newName);
        return "Employee " + id + " was updated successfully";
    }

    public String updateGrossSalary(String id, double newGross) throws Exception {
        Employee empl = this.retrieveEmployee(id);
        empl.updateRawSalary(newGross);
        return "Employee " + id + " was updated successfully";
    }

    public String updateInternGPA(String id, int newGpa) throws Exception {
        Employee empl = retrieveEmployee(id);

        if (!(empl instanceof Intern)) {
            return "Error"; // TODO: Exception
        }

        Intern intern = (Intern)empl;
        intern.updateGpa(newGpa);
        return "Employee " + id + " was updated successfully";
    }

    public String updateManagerDegree(String id, String newDegree) throws Exception {
        Employee empl = retrieveEmployee(id);

        if (!(empl instanceof Manager)) {
            return "Error"; // TODO: Exception
        }

        Manager manager = (Manager)empl;
        manager.updateDegree(newDegree);
        return "Employee " + id + " was updated successfully";
    }

    public String updateDirectorDept(String id, String newDept) throws Exception {
        Employee empl = retrieveEmployee(id);

        if (!(empl instanceof Director)) {
            return "Error"; // TODO: Exception
        }

        Director director = (Director)empl;
        director.updateDepartment(newDept);
        return "Employee " + id + " was updated successfully";
    }

    public String promoteToManager(String id, String degree) throws Exception {
        Employee empl = retrieveEmployee(id);

        String name = empl.getName();
        double rawSalary = empl.getRawSalary();

        Manager manager = new Manager(id, name, rawSalary, degree);

        this.employees.remove(id);
        this.employees.put(id, manager);

        return id + " promoted successfully to Manager.";
    }

    public String promoteToDirector(String id, String degree, String dept) throws Exception {
        Employee empl = retrieveEmployee(id);

        String name = empl.getName();
        double rawSalary = empl.getRawSalary();

        Director director = new Director(id, name, rawSalary, degree, dept);

        this.employees.remove(id);
        this.employees.put(id, director);

        return id + " promoted successfully to Director.";
    }

    public String promoteToIntern(String id, int gpa) throws Exception {
        Employee empl = retrieveEmployee(id);

        String name = empl.getName();
        double rawSalary = empl.getRawSalary();

        Intern intern = new Intern(id, name, rawSalary, gpa);

        this.employees.remove(id);
        this.employees.put(id, intern);

        return id + " promoted successfully to Intern.";
    }


}
