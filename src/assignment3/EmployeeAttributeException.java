package assignment3;

public class EmployeeAttributeException extends Exception {
    public EmployeeAttributeException() {
        super("Could not update employee attribute.");
    }

    public EmployeeAttributeException(String message) {
        super(message);
    }
}
