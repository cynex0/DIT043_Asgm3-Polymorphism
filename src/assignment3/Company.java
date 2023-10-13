package assignment3;
import java.util.*;

public class Company {
    private static final String EOL = System.lineSeparator();

    private LinkedHashMap<String, Employee> employees;

    public Company() {
        this.employees = new LinkedHashMap<>();
    }

    public String createEmployee(String id, String name, double grossSalary) throws EmployeeAttributeException {
        Employee newEmployee = EmployeeFactory.createRegularEmployee(id, name, grossSalary);
        return this.putEmployee(id, newEmployee);
    }

    public String createEmployee(String id, String name, double grossSalary, String degree) throws EmployeeAttributeException {
        Employee newEmployee = EmployeeFactory.createManager(id, name, grossSalary, degree);
        return this.putEmployee(id, newEmployee);
    }

    public String createEmployee(String id, String name, double grossSalary, String degree, String dept) throws EmployeeAttributeException {
        Employee newEmployee = EmployeeFactory.createDirector(id, name, grossSalary, degree, dept);
        return this.putEmployee(id, newEmployee);
    }

    public String createEmployee(String id, String name, double grossSalary, int gpa) throws EmployeeAttributeException {
        Employee newEmployee = EmployeeFactory.createIntern(id, name, grossSalary, gpa);
        return this.putEmployee(id, newEmployee);
    }

    private String putEmployee(String id, Employee empl) throws EmployeeAttributeException {
        if (this.employees.containsKey(id)) {
            throw new EmployeeAttributeException("Cannot register. ID " + id + " is already registered.");
        }

        employees.put(id, empl);
        return String.format("Employee %s was registered successfully.", id); // Employee <ID> was registered successfully.
    }

    private Employee retrieveEmployee(String id) throws EmployeeNotFoundException {
        if (!this.employees.containsKey(id)) {
            throw new EmployeeNotFoundException("Employee " + id + " was not registered yet.");
        }

        Employee empl = employees.get(id);
        return empl;
    }

    public String removeEmployee(String id) throws EmployeeNotFoundException {
        if (!this.employees.containsKey(id)) {
            throw new EmployeeNotFoundException("Employee " + id + " was not registered yet.");
        }

        this.employees.remove(id);
        return "Employee " + id + " was successfully removed.";
    }

    public double getNetSalary(String id) throws EmployeeNotFoundException {
        Employee employee = retrieveEmployee(id);
        return SalaryTruncator.truncateSalary(employee.getNetSalary());
    }

    public double getTotalNetSalary() throws EmployeeNotFoundException {
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("No employees registered yet.");
        }

        double total = 0.0;
        for (Employee empl : this.employees.values()) {
            total = total + SalaryTruncator.truncateSalary(empl.getNetSalary());
        }
        return total;
    }

    public Map<String, Integer> mapEachDegree() throws EmployeeNotFoundException {
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("No employees registered yet.");
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

    public String printEachDegree() throws EmployeeNotFoundException { // never used, but required in specification
        String message = "Academic background of employees:" + EOL;
        Map<String, Integer> degreesMap = mapEachDegree();

        for (Map.Entry<String, Integer> entry : degreesMap.entrySet()) {
            message += entry.getKey() + ": => " + entry.getValue();
        }
        return message;
    }

    public String printEmployee(String id) throws EmployeeNotFoundException {
        Employee empl = this.retrieveEmployee(id);
        return empl.toString();
    }

    public String printAllEmployees() throws EmployeeNotFoundException {
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("No employees registered yet.");
        }

        String message = "All registered employees:" + EOL;
        for (Employee empl : this.employees.values()) {
            message += empl.toString() + EOL;
        }

        return message;
    }

    public String printSortedEmployees() throws EmployeeNotFoundException {
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("No employees registered yet.");
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

    public String updateEmployeeName(String id, String newName) throws EmployeeNotFoundException, EmployeeAttributeException {
        Employee empl = this.retrieveEmployee(id);
        empl.setName(newName);
        return "Employee " + id + " was updated successfully";
    }

    public String updateGrossSalary(String id, double newGross) throws EmployeeNotFoundException, EmployeeAttributeException {
        Employee empl = this.retrieveEmployee(id);
        empl.setBaseSalary(newGross);
        return "Employee " + id + " was updated successfully";
    }

    public String updateManagerDegree(String id, String newDegree) throws EmployeeNotFoundException, EmployeeAttributeException {
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

    public String updateInternGPA(String id, int newGpa) throws Exception {
        Employee empl = retrieveEmployee(id);

        if (!(empl instanceof Intern)) {
            return "Error"; // TODO: Exception
        }

        Intern intern = (Intern)empl;
        intern.updateGpa(newGpa);
        return "Employee " + id + " was updated successfully";
    }

    public String promoteToManager(String id, String degree) throws Exception {
        Employee empl = retrieveEmployee(id);

        String name = empl.getName();
        double rawSalary = empl.getBaseSalary();

        Employee manager = EmployeeFactory.createManager(id, name, rawSalary, degree);

        this.employees.remove(id);
        this.employees.put(id, manager);

        return id + " promoted successfully to Manager.";
    }

    public String promoteToDirector(String id, String degree, String dept) throws Exception {
        Employee empl = retrieveEmployee(id);

        String name = empl.getName();
        double rawSalary = empl.getBaseSalary();

        Employee director = EmployeeFactory.createDirector(id, name, rawSalary, degree, dept);

        this.employees.remove(id);
        this.employees.put(id, director);

        return id + " promoted successfully to Director.";
    }

    public String promoteToIntern(String id, int gpa) throws Exception {
        Employee empl = retrieveEmployee(id);

        String name = empl.getName();
        double rawSalary = empl.getBaseSalary();

        Employee intern = EmployeeFactory.createIntern(id, name, rawSalary, gpa);

        this.employees.remove(id);
        this.employees.put(id, intern);

        return id + " promoted successfully to Intern.";
    }
}
