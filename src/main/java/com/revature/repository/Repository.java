package com.revature.repository;

import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.Tickets;
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
    // Field
    private final Employee employee = new Employee();
    private final Tickets ticket = new Tickets();

    /* 
     *
     * Successful 
     * 
    */
    // Verifying to see if an email_address is/isn't in the database
    public boolean loginVerification(Employee isEmployee) {
        // User has not been verified
        boolean verified = false;

        try (Connection con = ConnectionUtil.getConnection()) { // Repository connecting to our database
            // Using PreparedStatement to execute our SQL statement to the database
            PreparedStatement prstmt = con.prepareStatement("SELECT * FROM employees WHERE email_address = ? and password = ?");

            // Fill in user's info into the question marks in our SQL statement above
            prstmt.setString(1, isEmployee.getEmail());
            prstmt.setString(2, isEmployee.getPassword());

            // The object that will represent the result
            ResultSet rs = prstmt.executeQuery();

            // Checking each result
            while(rs.next()) {
                // If column "email_address" and "password" does equals to the user's input information
                if (rs.getString("email_address").equals(isEmployee.getEmail()) && rs.getString("password").equals(isEmployee.getPassword())) {
                    // Then the user is verified
                    verified = true;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // System.out.println(verified);
        // return verification
        return verified;
    }

    /* 
     *
     * Successful 
     * 
    */
    // Registering an employee
    public String registerEmployee(Employee employee) {
        // Checks to see if the user tried to input an empty value or values
        if (employee.getFirstName().isEmpty() || employee.getLastName().isEmpty() || employee.getEmail().isEmpty() || employee.getPassword().isEmpty()) {
            // If they did, then let them know
            return "Please fill out your information.";
        }
        
        if (!employee.getEmail().contains("@") || !employee.getEmail().contains(".com")) {
            return "Please fil out your email in a proper format. \nEx: email_example123@gmail.com";
        }

        // If the employee has been verified
        if (!loginVerification(employee)) {
            try (Connection con = ConnectionUtil.getConnection()) { // Repository will connect to our database
                // then use the PreparedStatement to execute our SQL statement to the database
                PreparedStatement prstmt = con.prepareStatement("INSERT INTO employees (first_name, last_name, email_address, password, employee_role) VALUES (?, ?, ?, ?, ?)");

                // Fills in the user's information into the question marks in our SQL statement above
                prstmt.setString(1, employee.getFirstName());
                prstmt.setString(2, employee.getLastName());
                prstmt.setString(3, employee.getEmail().toLowerCase());
                prstmt.setString(4, employee.getPassword());
                prstmt.setString(5, employee.getRole2());

                // Otherwise, execute our SQL statement to insert the user's information (they essentially become an employee)
                prstmt.execute();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                return "This email has already been used. Please try a different email.";
            }
        // However, if the user never passed the verification process
        } else {
            // Then return a String to tell the user that the email is invalid
            return "This email has already been used. Please try a different email.";
        }
        // Return a String to tell the user that their account has been registered
        return "You've successfully registered an account.";
    }

    /* 
     *
     * Successful 
     * 
    */
    // Submitting a ticket
    public String ticketSubmission(Tickets ticket) {
        String message = "";

        // doesEmailExist();

        // If employee didn't enter any information
        if (ticket.getEmail().isEmpty() && ticket.getDescription().isEmpty() && ticket.getAmount() < 1) {
            message = "Please fill out your ticket before submitting it.";

        // If employee didn't include either a description or amount
        } else if (ticket.getDescription().isEmpty() && ticket.getAmount() < 1) {
        message = "Please enter a description and an amount above 0.";

        // If employee didn't include an email
        } else if (ticket.getEmail().isEmpty()) {
            message = "Please enter your email.";
        
        // If employee didn't include a description
        } else if (ticket.getDescription().isEmpty()) {
            message = "Please enter a description.";

        // If employee didn't include a valid amount
        } else if (ticket.getAmount() < 1) {
            message = "Please enter an amount above 0.";

        } else {
            try (Connection con = ConnectionUtil.getConnection()) { // Repository will connect to our database
                PreparedStatement prstmt = con.prepareStatement("INSERT INTO tickets (email_address, amount, description, status) VALUES (?, ?, ?, ?)");
    
                prstmt.setString(1, ticket.getEmail());
                prstmt.setDouble(2, ticket.getAmount());
                prstmt.setString(3, ticket.getDescription());
                prstmt.setString(4, ticket.getStatus());

                prstmt.execute();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                return message = "Invalid email. Please use your registered email.";
            }
            message = "Your ticket has been submitted.";
        }
        return message;
    }

    /* 
     *
     * Successful 
     * 
    */
    // Viewing all the tickets
    public List<Tickets> getAllTickets() {
        List<Tickets> listOfTickets = new ArrayList<Tickets>();

        try (Connection con = ConnectionUtil.getConnection()) {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM tickets");

            while (rs.next()) {
                Tickets someTickets = new Tickets();

                someTickets.setTicketId(rs.getInt(1));
                someTickets.setEmail(rs.getString(2));
                someTickets.setAmount(rs.getDouble(3));
                someTickets.setDescription(rs.getString(4));
                someTickets.setStatus(rs.getString(5));

                listOfTickets.add(someTickets);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listOfTickets;
    }

    /* 
     *
     * Successful 
     * 
    */
    // Filtering out the tickets by their current status - PENDING
    public List<Tickets> pendingTickets() {
        List<Tickets> listOfTickets = new ArrayList<Tickets>();

        try (Connection con = ConnectionUtil.getConnection()) {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM tickets WHERE status = 'pending'");

            while (rs.next()) {
                Tickets someTickets = new Tickets();

                someTickets.setTicketId(rs.getInt("ticket_id"));
                someTickets.setEmail(rs.getString("email_address"));
                someTickets.setAmount(rs.getDouble("amount"));
                someTickets.setDescription(rs.getString("description"));
                someTickets.setStatus(rs.getString("status"));
                
                listOfTickets.add(someTickets);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listOfTickets;
    }

    /* 
     *
     * Successful 
     * 
    */
    // Filtering out the tickets by their current status - APPROVED
    public List<Tickets> approvedTickets() {
        List<Tickets> listOfTickets = new ArrayList<Tickets>();

        try (Connection con = ConnectionUtil.getConnection()) {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM tickets WHERE status = 'approved'");

            while (rs.next()) {
                Tickets someTickets = new Tickets();

                someTickets.setTicketId(rs.getInt("ticket_id"));
                someTickets.setEmail(rs.getString("email_address"));
                someTickets.setAmount(rs.getDouble("amount"));
                someTickets.setDescription(rs.getString("description"));
                someTickets.setStatus(rs.getString("status"));
                
                listOfTickets.add(someTickets);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listOfTickets;
    }

    /* 
     *
     * Successful 
     * 
    */
    // Filtering out the tickets by their current status - DENIED
    public List<Tickets> deniedTickets() {
        List<Tickets> listOfTickets = new ArrayList<Tickets>();

        try (Connection con = ConnectionUtil.getConnection()) {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM tickets WHERE status = 'denied'");

            while (rs.next()) {
                Tickets someTickets = new Tickets();

                someTickets.setTicketId(rs.getInt("ticket_id"));
                someTickets.setEmail(rs.getString("email_address"));
                someTickets.setAmount(rs.getDouble("amount"));
                someTickets.setDescription(rs.getString("description"));
                someTickets.setStatus(rs.getString("status"));
                
                listOfTickets.add(someTickets);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listOfTickets;
    }

    /* 
     *
     * Successful 
     * 
    */
    // Verifying to see if an email_address is/isn't in the database; They must have permission to register as a manager as well
    public boolean isManager(Employee isManager) {
        // User has not been verified
        boolean verified = false;

        try (Connection con = ConnectionUtil.getConnection()) { // Repository connecting to our database
            // Using PreparedStatement to execute our SQL statement to the database
            PreparedStatement prstmt = con.prepareStatement("SELECT * FROM employees WHERE email_address = ? and password = ?");

            // Fill in user's info into the question marks in our SQL statement above
            prstmt.setString(1, isManager.getEmail());
            prstmt.setString(2, isManager.getPassword());

            // The object that will represent the result
            ResultSet rs = prstmt.executeQuery();

            // Checking each result
            while(rs.next()) {
                // If column "email_address" and "password" does equals to the user's input information
                if (rs.getString("email_address").equals(isManager.getEmail()) && rs.getString("password").equals(isManager.getPassword()) && rs.getString("employee_role").equals(isManager.getRole1())) {
                    // Then the user is verified
                    verified = true;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // System.out.println(verified);
        // return verification
        return verified;
    }



    /*
     * 
     * Need to implement
     * 
    */
    // public String changeTicketStatus() {
    //     try (Connection con = ConnectionUtil.getConnection()) {
    //         PreparedStatement prstmt = con.prepareStatement("update tickets set status = '?' where ticket_id = ? and status = 'PENDING'");

    //         prstmt.setString(1, ticket.getStatus());
    //         prstmt.setInt(2, ticket.getTicketId());

    //         prstmt.execute();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return null;
    // }
}