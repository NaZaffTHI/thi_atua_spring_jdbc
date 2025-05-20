package de.thi.winfo.model;

import java.time.LocalDate;

public class Student {
    private final String id;
    private String firstName;
    private String lastName;
    private final LocalDate dateOfBirth;

    public Student() {
        this("default", "default", LocalDate.now());
    }

    public Student(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;

        this.id = generateId();
    }

    public Student(String id, String firstName, String lastName, LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    private String generateId() {
        return "" + this.firstName.charAt(0)
                + this.lastName.charAt(0)
                + this.getDateOfBirth().getYear();
    }
}
