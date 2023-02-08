package com.revature.models;

// import java.util.ArrayList;
// import java.util.List;

/*
 * Model layer is repsonsible for holding stateful objects
 * Objects that have information that differs from another object from the same class
*/
public class Employee {
    // private String firstName;
    // private String lastName;
    // private String dateOfBirth;
    private String email;
    private String password;
    // private String isManager;

    public Employee() {
        super();
    }

    // public String getFirstName() {
    //     return firstName;
    // }

    // public void setFirstName(String firstName) {
    //     this.firstName = firstName;
    // }

    // public String getLastName() {
    //     return lastName;
    // }

    // public void setLastName(String lastName) {
    //     this.lastName = lastName;
    // }

    // public String getDateOfBirth() {
    //     return dateOfBirth;
    // }

    // public void setDateOfBirth(String dateOfBirth) {
    //     this.dateOfBirth = dateOfBirth;
    // }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // public String getIsManager() {
    //     return isManager;
    // }

    // public void setIsManager(String isManager) {
    //     this.isManager = isManager;
    // }
}