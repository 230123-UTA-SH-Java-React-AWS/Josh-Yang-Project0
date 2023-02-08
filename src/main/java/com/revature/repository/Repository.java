package com.revature.repository;

import com.revature.models.Employee;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
    Repository layer is responsible for interacting with a database and sending/receiving information from the database
*/
public class Repository {
    // Verifying to see if employee's email and password is/isn't in the database
    public boolean loginVerification(Employee isEmployee) {
        // User has not been verified
        boolean verified = false;

        try (Connection con = ConnectionUtil.getConnection()) { // Repository connecting to our database
            // Using PreparedStatement to execute our SQL statement to the database
            PreparedStatement prstmt = con.prepareStatement("SELECT * FROM employee WHERE email = ? AND password = ?");

            // Fill in user's info into the question marks in our SQL statement above
            prstmt.setString(1, isEmployee.getEmail());
            prstmt.setString(2, isEmployee.getPassword());

            // The object that will represent the result
            ResultSet rs = prstmt.executeQuery();

            while(rs.next()) {
                // If column "email" and "password" equals the correct user information
                if (rs.getString("email").equals(isEmployee.getEmail()) && rs.getString("password").equals(isEmployee.getPassword())) {
                    // Then the user has been verified
                    verified = true;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // return verification
        return verified;
    }

    // Registering an employee
    public String registerEmployee(Employee employee) {
        // If the employee has been verified
        if (!loginVerification(employee)) {
            try (Connection con = ConnectionUtil.getConnection()) { // Repository will connect to our database
                // then use the PreparedStatement to execute our SQL statement to the database
                PreparedStatement prstmt = con.prepareStatement("INSERT INTO employee (email, password) values (?, ?)");

                // Fills in user's info into the question marks in our SQL statement above
                prstmt.setString(1, employee.getEmail());
                prstmt.setString(2, employee.getPassword());

                // Execute our SQL statement
                prstmt.execute();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            // Return a String to tell the user that the email is invalid
            return "This email has already been used. Please try a different email.";
        }
        // Return a String to tell the user that their account has been registered
        return "You've successfully registered an account.";
    }

    // Successfully grabs all existing employees (similar from lecture)
    public List<Employee> getAllEmployees() {
        List<Employee> listOfEmployees = new ArrayList<Employee>();

        try (Connection con = ConnectionUtil.getConnection()) { // Repository will connect to our database
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM employee");

            while (rs.next()) {
                Employee allEmployees = new Employee();

                allEmployees.setEmail(rs.getString(1));
                allEmployees.setPassword(rs.getString(2));

                listOfEmployees.add(allEmployees);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfEmployees;
    }
}
