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
    private String email;
    private String password;

    public Employee() {
        super();
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
}