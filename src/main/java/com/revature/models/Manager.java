package com.revature.models;

/*
 * Model layer is repsonsible for holding stateful objects
 * Objects that have information that differs from another object from the same class
*/
public class Manager extends Employee {
    public Manager(String email, String role1) {
        super(email, role1);
    }
}