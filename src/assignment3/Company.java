package assignment3;

import java.util.HashMap;

/*
The company begins with no Employee registered and should: i) register, ii) remove, iii) retrieve,
iv) update its employees, calculate the total expenses by paying v) gross salaries and vi) net salaries, and, finally,
vii) inform the total number of employees registered. In addition, be aware of the following cases:
*/
public class Company {
    private HashMap<String, Employee> employees;

    public Company() {
        this.employees = new HashMap<>();
    }

    public String createEmployee(String id, String name, double grossSalary) {
        Employee newEmployee = new Employee(id, name, grossSalary);
        employees.put(id, newEmployee);

        // Employee <ID> was registered successfully.
        return String.format("Employee %s was registered successfully.", id);
    }

    public String printEmployee(String id) {
        Employee employee = employees.get(id); // TODO: Exception
        return employee.toString();
    }

    public double getNetSalary(String id) {
        Employee employee = employees.get(id); // TODO: Exception
        return employee.getNetSalary();
    }
}
