package assignment3;

public enum Department {
    HUMAN_RESOURCES("Human Resources"), TECHNICAL("Technical"), BUSINESS("Business");

    private final String DEPT_NAME;
    Department(String dept) {
        this.DEPT_NAME = dept;
    }

    public String toString() {
        return DEPT_NAME;
    }
}
