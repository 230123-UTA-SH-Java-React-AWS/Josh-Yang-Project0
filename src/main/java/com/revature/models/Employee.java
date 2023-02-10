package com.revature.models;

/*
 * Model layer is repsonsible for holding stateful objects
 * Objects that have information that differs from another object from the same class
*/
public class Employee {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role1 = "MANAGER";
    private String role2 = "EMPLOYEE";

    public Employee() {
        super();
    }

    public Employee(String email, String role1) {
        this.email = email;
        this.role2 = role1;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public String getRole1() {
        return role1;
    }

    public void setRole1(String role1) {
        this.role1 = role1;
    }

    public String getRole2() {
        return role2;
    }

    public void setRole2(String role2) {
        this.role2 = role2;
    }
}